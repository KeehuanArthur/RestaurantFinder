package arthurlee.restaurantfinder;

import android.content.Intent;
import android.media.Rating;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private String TAG = "RestaurantDetailsAct";

    private String mTitle;
    private String mAddress;
    private String mPlaceId;
    private String mContact;
    private float mRating;
    private ArrayList<String> imageReferences;

    private TextView mTitleView, mAddressView, mContactView;
    private RatingBar mRatingBar;
    private ViewPager mImagePager;
    private SlidingImageAdapter mSlidingImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        API.setRequestQueue(Volley.newRequestQueue(this));
        API.initImageLoader();
        imageReferences = new ArrayList<>();

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mAddress = intent.getStringExtra("address");
        mPlaceId = intent.getStringExtra("placeId");
        mRating = intent.getFloatExtra("rating", 0);

        mTitleView = (TextView) findViewById(R.id.detail_title);
        mAddressView = (TextView) findViewById(R.id.detail_address);
        mContactView = (TextView) findViewById(R.id.detail_contact);
        mRatingBar = (RatingBar) findViewById(R.id.detail_rating);
        mImagePager = (ViewPager) findViewById(R.id.detail_image_pager);

        mSlidingImageAdapter = new SlidingImageAdapter(mImagePager.getContext(), null);
        mImagePager.setAdapter(mSlidingImageAdapter);

        getDetails();
    }


    private void getDetails() {
        final Map<String, String> headers = new HashMap<>();
        headers.put("key", API.places_key);
        headers.put("placeid", mPlaceId);

        Response.Listener listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    response = response.getJSONObject(API.jResult);
                    mAddress = response.getString(API.jFormattedAddress);
                    mContact = response.getString(API.jFormattedPhoneNumber);
                    mTitle = response.getString(API.jTitle);
                    mRating = Float.parseFloat(response.getString(API.jRating));

                    JSONArray jsonPhotoList = response.getJSONArray(API.jPhotos);
                    for (int i = 0; i < jsonPhotoList.length(); i++){
                        imageReferences.add(jsonPhotoList.getJSONObject(i).getString(API.jPhotoReference));
                        mSlidingImageAdapter.addItem( jsonPhotoList.getJSONObject(i).getString(API.jPhotoReference) );
                        mSlidingImageAdapter.notifyDataSetChanged();
                    }

                    mTitleView.setText(mTitle);
                    mAddressView.setText(mAddress);
                    mContactView.setText(mContact);
                    mRatingBar.setRating(mRating);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        API.call(API.DETAILS, headers, null, listener, errorListener, Request.Method.GET);
    }

}
