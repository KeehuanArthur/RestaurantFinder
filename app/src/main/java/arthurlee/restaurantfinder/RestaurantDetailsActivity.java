package arthurlee.restaurantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private String mTitle;
    private String mAddress;
    private String mPlaceId;
    private float mRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mAddress = intent.getStringExtra("address");
        mPlaceId = intent.getStringExtra("placeId");
        mRating = intent.getFloatExtra("rating", 0);

        ((TextView)findViewById(R.id.detail_title)).setText(mTitle);
        ((TextView)findViewById(R.id.detail_address)).setText(mAddress);
        ((RatingBar)findViewById(R.id.detail_rating)).setRating(mRating);
    }
}
