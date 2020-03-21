package com.famebuster.databinding;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.famebuster.BuildConfig;
import com.famebuster.R;
import com.famebuster.data.remote.ApiConstants;
import com.famebuster.util.AppConstants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public final class ImageBindingAdapters {
    //TODO: This code will be improved
    //TODO: Each binding adapter should follow this guide. Modify this code and use for yours.
//    @BindingAdapter({ "The layout string should be same with here Example:(bind:YOURSTRING)" })
//    public static void loadImage(ImageView imageView, String /* "imageUrl" image url here */) {
//
//        if (imageUrl != null && !imageUrl.equals("")) {
//            Picasso.get()
//                    .load(BuildConfig.BASE_URL + /*ApiConstant.Endpoint prefix to here */+imageUrl)
//                    .networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
//                @Override
//                public void onSuccess() {
//                    Log.d("CACHEEE","IMAGE FROM CACHE");
//                }
//
//                @Override
//                public void onError(Exception e) {
//                    Picasso.get()
//                            .load(BuildConfig.BASE_URL + /*ApiConstant.Endpoint prefix to here */+imageUrl)
//                            .placeholder(R.drawable.placeholder)
//                            .into(imageView);
//                    Log.d("CACHEEE","IMAGE IS NOT FROM CACHE");
//
//
//                }
//            });
//
//            Log.d("CCC",BuildConfig.BASE_URL + ApiConstants.BANNER_IMAGE_ENDPOINT_PREFIX+imageUrl);
//        }
//    }


    @BindingAdapter({ "imageUrl" })
    public static void loadImage(ImageView imageView, String imageUrl) {

        if (imageUrl != null && !imageUrl.equals("")) {
            Picasso.get()
                    .load(BuildConfig.BASE_URL + ApiConstants.MAP_IMAGES_ENDPOINT + imageUrl)
                    .networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("CACHEEE","CACHE");
                }

                @Override
                public void onError(Exception e) {
                    Picasso.get()
                            .load(BuildConfig.BASE_URL + ApiConstants.MAP_IMAGES_ENDPOINT + imageUrl)
                            .placeholder(R.drawable.placeholder)
                            .into(imageView);
                    Log.d("CACHEEE","NETWORK");


                }
            });

            Log.d("CCC",BuildConfig.BASE_URL + ApiConstants.MAP_IMAGES_ENDPOINT + imageUrl);
        }
    }

}
