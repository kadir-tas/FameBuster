package com.famebuster.ui.map;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.famebuster.data.Resource;
import com.famebuster.data.local.entities.NewsOnMap;
import com.famebuster.data.remote.repositories.NewsOnMapRepository;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class MapsActivityViewModel extends ViewModel {

    private final NewsOnMapRepository newsOnMapRepository;

    private MutableLiveData<LatLngBounds> mapBounds = new MutableLiveData<>();
    private LiveData<Resource<List<NewsOnMap>>> newsOnMaps;


    @Inject
    MapsActivityViewModel(NewsOnMapRepository newsOnMapRepository) {
        this.newsOnMapRepository = newsOnMapRepository;
    }

    void init(){
        newsOnMaps = Transformations.switchMap(mapBounds, latLngBounds -> {
            HashMap<String, Double> latLonFields = new HashMap<>();
            latLonFields.put("lat1", latLngBounds.southwest.latitude);
            latLonFields.put("lat2", latLngBounds.northeast.latitude);
            latLonFields.put("lon1", latLngBounds.northeast.longitude);
            latLonFields.put("lon2", latLngBounds.southwest.longitude);
            return newsOnMapRepository.getNewsOnMap(latLonFields);
        });
    }

    LiveData<Resource<List<NewsOnMap>>> getVisibleNewsOnMap() {
        return newsOnMaps;
    }

    void setMapBounds(LatLngBounds latLngBounds) {
        this.mapBounds.setValue(latLngBounds);
    }

    public List<NewsOnMap> filterDisplayedCamerasFor(LatLngBounds bounds, List<NewsOnMap> newsOnMaps) {
        ArrayList<NewsOnMap> newsOnMapArrayList = new ArrayList<>();
        for (NewsOnMap n : newsOnMaps) {
            LatLng newsOnMapLocation = new LatLng(Double.parseDouble(n.getNewsLat()), Double.parseDouble(n.getNewsLon()));
            if (bounds.contains(newsOnMapLocation)) {
//                displayedCameraValues.add(camera);
                newsOnMapArrayList.add(n);
            }
        }
        return newsOnMapArrayList;
    }
}
