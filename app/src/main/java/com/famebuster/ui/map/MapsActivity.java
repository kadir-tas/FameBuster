package com.famebuster.ui.map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.famebuster.BuildConfig;
import com.famebuster.R;
import com.famebuster.data.Resource;
import com.famebuster.data.local.entities.MapItem;
import com.famebuster.data.local.entities.NewsOnMap;
import com.famebuster.data.remote.ApiConstants;
import com.famebuster.util.AppConstants;
import com.famebuster.util.GpsUtils;
import com.famebuster.util.receivers.GPSBroadcastReceiver;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ClusterManager.OnClusterClickListener<MapItem>, ClusterManager.OnClusterInfoWindowClickListener<MapItem>, ClusterManager.OnClusterItemClickListener<MapItem>, ClusterManager.OnClusterItemInfoWindowClickListener<MapItem> {

    private static final int Request_Code = 101;
    private static final float DEFAULT_ZOOM = 9f;
    private static final float MIN_ZOOM = 4f;
    private static final int REQUEST_FREQUENCY_FACTOR = 100;

    private List<MapItem> mapItems = new ArrayList<>();

    private GPSBroadcastReceiver gpsBroadcastReceiver = new GPSBroadcastReceiver();
    private ClusterManager<MapItem> mClusterManager;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationCallback mLocationCallback;
    private LocationRequest mLocationRequest;
    private Location mLocation;
    private LocationSettingsRequest.Builder builder;

    private boolean isGPS = false;
    private boolean mIsRestore;
    private boolean isLocationPermissionGranted;

    private boolean isItemAdded;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public static MapsActivityViewModel viewModel;

    public Class<MapsActivityViewModel> getViewModel() {
        return MapsActivityViewModel.class;
    }

    public int getLayoutRes() {
        return R.layout.activity_maps;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        mIsRestore = savedInstanceState != null;

        viewModel = new ViewModelProvider(this, viewModelFactory).get(getViewModel());
        viewModel.init();
            if(!gpsBroadcastReceiver.isOrderedBroadcast()){
                registerReceiver(gpsBroadcastReceiver, new IntentFilter("android.location.PROVIDERS_CHANGED"));
            }
            setUpMap();
    }

    private void setUpMap() {
        //Calls the below onMapReady function to init location
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        mMap.setMinZoomPreference(MIN_ZOOM);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        new GpsUtils(this).turnGPSOn(isGPSEnable -> isGPS = isGPSEnable);

        //I checked isGPS in these methods separately for multiple usage
        //For the fast initialization of the map
        getLastLocation();

        initLocationRequest();

        initLocationCallback();

        init();
//        mMap.getUiSettings().setMyLocationButtonEnabled(false);

    }



    private void init(){
        if(isLocationPermissionGranted) {

            builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
            checkLocationSetting(builder);

            mMap.setMyLocationEnabled(true);

        }
    }

    private void checkLocationSetting(LocationSettingsRequest.Builder builder) {

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                requestLocation();
                return;
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull final Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MapsActivity.this);
                    builder1.setTitle("Continious Location Request");
                    builder1.setMessage("This request is essential to get location update continiously");
                    builder1.create();
                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            try {
                                resolvable.startResolutionForResult(MapsActivity.this,
                                        AppConstants.REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MapsActivity.this, R.string.toastMsgLocPermission, Toast.LENGTH_LONG).show();
                        }
                    });
                    builder1.show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!gpsBroadcastReceiver.isOrderedBroadcast()){
            registerReceiver(gpsBroadcastReceiver, new IntentFilter("android.location.PROVIDERS_CHANGED"));
        }
        if(isLocationPermissionGranted)
            getLastLocation();
//        if(!isGPS)
//            new GpsUtils(this).turnGPSOn(isGPSEnable -> isGPS = isGPSEnable);

//        getLastLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(gpsBroadcastReceiver.isOrderedBroadcast()) {
            unregisterReceiver(gpsBroadcastReceiver);
        }
        if(mFusedLocationProviderClient != null){
            mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mFusedLocationProviderClient != null){
            mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
        }
        unregisterReceiver(gpsBroadcastReceiver);
    }


    private void initLocationRequest() {
        if(isGPS) {
            mLocationRequest = LocationRequest.create();
            mLocationRequest.setInterval(30000);
            mLocationRequest.setFastestInterval(10000);
            mLocationRequest.setSmallestDisplacement(30);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }
    }

    private void initLocationCallback() {
        if(isGPS) {
            mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        Toast.makeText(MapsActivity.this, R.string.toastMsgLocPermission, Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (locationResult.getLastLocation() != null) {
                        //This automatically saves to the last location I dont need to set mLocation but I set for possible features
                        mLocation = locationResult.getLastLocation();
                    } else {
                        Toast.makeText(MapsActivity.this, R.string.toastMsgLocPermission, Toast.LENGTH_SHORT).show();
                        new GpsUtils(MapsActivity.this).turnGPSOn(isGPSEnable -> isGPS = isGPSEnable);
                    }
                }
            };
        } else{
            Toast.makeText(MapsActivity.this, R.string.toastMsgTurnOnGps, Toast.LENGTH_SHORT).show();
        }
    }

    private void getLastLocation() {
        if(isGPS) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, AppConstants.LOCATION_REQUEST);
                return;
            }
            isLocationPermissionGranted = true;
            Task<Location> task = mFusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(location -> {
                if (location != null /* && ((location.getAccuracy() > 0 && location.getAccuracy() < 20) || (location.getTime() - System.currentTimeMillis() < 120000))*/) {
                    mLocation = location;
                    Toast.makeText(MapsActivity.this, mLocation.getLatitude() + ", " + mLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    if (!mIsRestore) {
                        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()), DEFAULT_ZOOM));
                        startClustering();
                    }
                }
            });
        }else {
            Toast.makeText(MapsActivity.this, R.string.toastMsgTurnOnGps, Toast.LENGTH_LONG).show();
        }
    }

    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, AppConstants.LOCATION_REQUEST);

        } else {
            if(mLocationCallback == null){
                initLocationCallback();
            }
            mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.getMainLooper());
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case AppConstants.LOCATION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isLocationPermissionGranted = true;
                    getLastLocation();
                    init();
//                    Task<Location> task = mFusedLocationProviderClient.getLastLocation();
//                    task.addOnSuccessListener(location -> {
//                        if (location != null /*&& ((location.getAccuracy() > 0 && location.getAccuracy() < 20) || (location.getTime() - System.currentTimeMillis() < 120000))*/) {
//                            mLocation = location;
//                            Toast.makeText(MapsActivity.this, mLocation.getLatitude() + ", " + mLocation.getLongitude(), Toast.LENGTH_SHORT).show();
//                            startClustering(new LatLng(location.getLatitude(),location.getLongitude()), DEFAULT_ZOOM);
//                        }
//                    });
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                    isLocationPermissionGranted = false;
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppConstants.GPS_REQUEST) {
                isGPS = true; // flag maintain before get location
            }
        }
        if (requestCode == AppConstants.REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
                requestLocation();
            }
            else {
                checkLocationSetting(builder);
            }
        }
    }

    protected void startClustering(){
        if(!isItemAdded) {
            isItemAdded = true;
            mClusterManager = new ClusterManager<MapItem>(this, getMap());
            mClusterManager.setRenderer(new NewsOnMapRenderer());
            getMap().setOnMarkerClickListener(mClusterManager);
            getMap().setOnInfoWindowClickListener(mClusterManager);
            mClusterManager.setOnClusterClickListener(this);
            mClusterManager.setOnClusterInfoWindowClickListener(this);
            mClusterManager.setOnClusterItemClickListener(this);
            mClusterManager.setOnClusterItemInfoWindowClickListener(this);

            Location oldCenter = new Location("oldCenter");
            Location newCenter = new Location("newCenter");
            //init old center to req optimization
            oldCenter.setLatitude(mLocation.getLatitude());
            oldCenter.setLongitude(mLocation.getLongitude());

            mMap.setOnCameraIdleListener(() -> {
                Log.v("ZOOM"," " + mMap.getCameraPosition().zoom);
                VisibleRegion vr = mMap.getProjection().getVisibleRegion();
                //init new center for distance between new and old
                newCenter.setLatitude(vr.latLngBounds.getCenter().latitude);
                newCenter.setLongitude(vr.latLngBounds.getCenter().longitude);

                float dist = oldCenter.distanceTo(newCenter);

                // This if check controls the change of camera pos according to zoom level. I did this because I don't want to send latlngbounds each time
                if(dist > (156543.03392 * Math.cos(mMap.getCameraPosition().target.latitude * Math.PI / 180) / Math.pow(2, mMap.getCameraPosition().zoom)) * REQUEST_FREQUENCY_FACTOR){
                    Log.d("DISTINSIDE", "" + dist + "     " + (156543.03392 * Math.cos(mMap.getCameraPosition().target.latitude * Math.PI / 180) / Math.pow(2, mMap.getCameraPosition().zoom)) * 100);
                    oldCenter.setLatitude( vr.latLngBounds.getCenter().latitude);
                    oldCenter.setLongitude( vr.latLngBounds.getCenter().longitude);
                    viewModel.setMapBounds(vr.latLngBounds);
                }
                Log.d("LOCDIST", "" + dist);
                mClusterManager.onCameraIdle();
            });

            viewModel.getVisibleNewsOnMap().observe(this, listResource -> {
                switch (listResource.status) {
                    case SUCCESS:
                        if (listResource.data != null && listResource.data.size() > 0) {
                            mapItems.clear();
                            if(listResource.data.size() > 200){
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    listResource.data.sort((o1, o2) -> {
                                        Location l1 = new Location("l1");
                                        Location l2 = new Location("l2");
                                        l1.setLatitude(Double.parseDouble(o1.getNewsLat()));
                                        l1.setLongitude(Double.parseDouble(o1.getNewsLon()));
                                        l2.setLatitude(Double.parseDouble(o2.getNewsLat()));
                                        l2.setLongitude(Double.parseDouble(o2.getNewsLon()));
                                        return Float.compare(l1.distanceTo(newCenter),l2.distanceTo(newCenter));
                                    });
                                }
                                for(int i = 0 ; i < 200 ; i++){
                                    mapItems.add(new MapItem(new LatLng(Double.parseDouble(listResource.data.get(i).getNewsLat()), Double.parseDouble(listResource.data.get(i).getNewsLon())), listResource.data.get(i).getNewsMapImageUrl(), listResource.data.get(i).getNewsHeader()));
                                }
                            }else {
                                for (NewsOnMap i : listResource.data) {
                                    mapItems.add(new MapItem(new LatLng(Double.parseDouble(i.getNewsLat()), Double.parseDouble(i.getNewsLon())), i.getNewsMapImageUrl(), i.getNewsHeader()));
                                }
                            }
                            mClusterManager.clearItems();
                            mClusterManager.addItems(mapItems);
                            mClusterManager.cluster();
                        }
                        break;
                    case ERROR:
                        break;
                    case LOADING:
                        break;
                }
            });
            viewModel.setMapBounds(mMap.getProjection().getVisibleRegion().latLngBounds);
        }
    }

    public class NewsOnMapRenderer extends DefaultClusterRenderer<MapItem> {
        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final ImageView mClusterImageView;
        private final int mDimension;

        public NewsOnMapRenderer() {
            super(getApplicationContext(), getMap(), mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);

            mImageView = new ImageView(getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);
        }

        @Override
        protected void onBeforeClusterItemRendered(MapItem mapItem, MarkerOptions markerOptions) {
            // Draw a single person
            // Set the info window to show their name
            Picasso.get()
                    .load(BuildConfig.BASE_URL + ApiConstants.MAP_IMAGES_ENDPOINT + mapItem.getNewsMapImageUrl())
                    .networkPolicy(NetworkPolicy.OFFLINE).into(mImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d("CACHEEE","CACHE");
                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get()
                                    .load(BuildConfig.BASE_URL + ApiConstants.MAP_IMAGES_ENDPOINT + mapItem.getNewsMapImageUrl())
                                    .placeholder(R.drawable.placeholder)
                                    .into(mImageView);
                            Log.d("CACHEEE","NETWORK");
                        }
                    });
            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(mapItem.getNewsHeader());
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<MapItem> cluster, MarkerOptions markerOptions) {
            // Note: this method runs on the UI thread. Don't spend too much time in here.
            String headPhotoUrl = cluster.getItems().iterator().next().getNewsMapImageUrl();
            Picasso.get()
                    .load(BuildConfig.BASE_URL + ApiConstants.MAP_IMAGES_ENDPOINT + headPhotoUrl)
                    .networkPolicy(NetworkPolicy.OFFLINE).into(mImageView, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("CACHEEE","CACHE");
                }

                @Override
                public void onError(Exception e) {
                    Picasso.get()
                            .load(BuildConfig.BASE_URL + ApiConstants.MAP_IMAGES_ENDPOINT + headPhotoUrl)
                            .placeholder(R.drawable.placeholder)
                            .into(mImageView);
                    Log.d("CACHEEE","NETWORK");
                }
            });

            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
//            if(getMap().getCameraPosition().zoom > 15) {
//                return false;
//            }
            return cluster.getSize() > 4;
        }
    }

    @Override
    public boolean onClusterClick(Cluster<MapItem> cluster) {
        // Show a toast with some info when the cluster is clicked.
        String header = cluster.getItems().iterator().next().getNewsHeader();
        Toast.makeText(this, cluster.getSize() + " (including " + header + ")", Toast.LENGTH_SHORT).show();

        // Create the builder to collect all essential cluster items for the bounds.
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }
        final LatLngBounds bounds = builder.build();

        // Animate camera to the bounds
        try {
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }

        getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(
                cluster.getPosition(), (float) Math.floor(getMap()
                        .getCameraPosition().zoom + 1)), 300,
                null);
        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<MapItem> cluster) {
    }

    @Override
    public boolean onClusterItemClick(MapItem item) {
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(MapItem item) {
    }

    protected GoogleMap getMap() {
        return mMap;
    }

}
