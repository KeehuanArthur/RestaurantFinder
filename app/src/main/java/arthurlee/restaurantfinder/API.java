package arthurlee.restaurantfinder;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * Created by arthur on 9/20/2017.
 */

public class API {

    static String cdn = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    static String image_url = "https://maps.googleapis.com/maps/api/place/photo";
//    static String places_key = "AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk";
    // Note: the key above is the key that was given in the email. It ran out of uses. Maybe when the
    //       key was generated, the account wasn't verified so only 1000 uses were allowed instead of 150,000
    static String places_key = "AIzaSyDS7TIRB3odqBM9ny6mB9ULISHzP9_IFyU";


    static Double curLatitude;
    static Double curLongitude;

    static RequestQueue requestQueue;
    static ImageLoader imageLoader;
    private static ImageLoader.ImageCache imageCache;

    static String jNextPageToken = "next_page_token";
    static String jResults = "results";
    static String jTitle = "name";
    static String jGeometry = "geometry";
    static String jLocation = "location";
    static String jLatitude = "lat";
    static String jLongitude = "lng";
    static String jRating = "rating";
    public static String jId = "id";
    public static String jPlaceId = "place_id";
    static String jPhoto = "photos";
    static String jPhotoReference = "photo_reference";
    static String jAddress = "vicinity";

    static void setRequestQueue(RequestQueue r) {
        requestQueue = r;
    }
    public static void initImageLoader() {
        imageCache = new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCashe = new LruCache<String, Bitmap>(10);
            @Override
            public Bitmap getBitmap(String url) {
                return mCashe.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCashe.put(url, bitmap);
            }
        };
        imageLoader = new ImageLoader(requestQueue, imageCache);
    }

    public static String getGetImageUrl(String photoReference, int maxWidth) {


        String url = String.format(Locale.US, "%s?maxwidth=%d&photoreference=%s&key=%s", image_url, maxWidth, photoReference, places_key );
        return url;
    }

    public static void call (final Map<String, String> headers, JSONObject body, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, int method) {
        JsonObjectRequest request;

        String url = cdn;
        if (headers != null) {
            String query = "?";
            Iterator it = headers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                query += pair.getKey();
                query += "=";
                query += pair.getValue();
                query += "&";
                it.remove(); // avoids a ConcurrentModificationException
            }
            url += query;
            Log.d("API", url);
        }

        request = new JsonObjectRequest(method, url, body, listener, errorListener);

        requestQueue.add(request);
    }

}
