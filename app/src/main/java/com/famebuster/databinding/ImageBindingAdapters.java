package com.famebuster.databinding;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.famebuster.BuildConfig;
import com.famebuster.R;
import com.famebuster.data.remote.ApiConstants;
import com.famebuster.util.AppConstants;
import com.famebuster.util.CircleTransform;
import com.squareup.picasso.BuildConfig;
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

    @BindingAdapter({ "bind:newsImage" })
    public static void loadNewsImage(ImageView imageView, String imageUrl) {
       if (imageUrl != null && !imageUrl.equals("")) {
           Picasso.get()
                   .load(ApiConstants.IMAGE_BASE_URL + ApiConstants.NEWS_IMAGE_PATH  + imageUrl)
                   .networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
               @Override
               public void onSuccess() {
                   Log.d("CACHEEE","IMAGE FROM CACHE");
               }
               @Override
               public void onError(Exception e) {
                   Picasso.get()
                           .load(ApiConstants.IMAGE_BASE_URL + ApiConstants.NEWS_IMAGE_PATH  + imageUrl)
                           .placeholder(R.drawable.ic_placeholder_img)
                           .into(imageView);
                   Log.d("CACHEEE","IMAGE IS NOT FROM CACHE");
               }
           });
         //  Log.d("CCC",BuildConfig.BASE_URL + ApiConstants.BANNER_IMAGE_ENDPOINT_PREFIX+imageUrl);
       }
   }

    @BindingAdapter({ "bind:userImage" })
    public static void loadUserImage(ImageView imageView, String imageUrl) {
        if (imageUrl != null && !imageUrl.equals("")) {
            Picasso.get()
                    .load(ApiConstants.IMAGE_BASE_URL + ApiConstants.USER_IMAGE_PATH  + imageUrl)
                    .transform(new CircleTransform())
                    .networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("CACHEEE","IMAGE FROM CACHE");
                }
                @Override
                public void onError(Exception e) {
                    Picasso.get()
                            .load(ApiConstants.IMAGE_BASE_URL + ApiConstants.USER_IMAGE_PATH  + imageUrl)
                            .placeholder(R.drawable.ic_placeholder_img)
                            .into(imageView);
                    Log.d("CACHEEE","IMAGE IS NOT FROM CACHE");
                }
            });
            //  Log.d("CCC",BuildConfig.BASE_URL + ApiConstants.BANNER_IMAGE_ENDPOINT_PREFIX+imageUrl);
        }
    }

    @BindingAdapter({ "bind:celebImage" })
    public static void loadCelebImage(ImageView imageView, String imageUrl) {
        if (imageUrl != null && !imageUrl.equals("")) {
            Picasso.get()
                    .load(ApiConstants.IMAGE_BASE_URL + ApiConstants.CELEB_IMAGE_PATH  + imageUrl)
                    .networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("CACHEEE","IMAGE FROM CACHE");
                }
                @Override
                public void onError(Exception e) {
                    Picasso.get()
                            .load(ApiConstants.IMAGE_BASE_URL + ApiConstants.USER_IMAGE_PATH  + imageUrl)
                            .placeholder(R.drawable.ic_placeholder_img)
                            .into(imageView);
                    Log.d("CACHEEE","IMAGE IS NOT FROM CACHE");
                }
            });
            //  Log.d("CCC",BuildConfig.BASE_URL + ApiConstants.BANNER_IMAGE_ENDPOINT_PREFIX+imageUrl);
        }
    }


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
