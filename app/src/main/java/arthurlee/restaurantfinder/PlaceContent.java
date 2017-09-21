package arthurlee.restaurantfinder;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<PlaceItem> ITEMS = new ArrayList<PlaceItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, PlaceItem> ITEM_MAP = new HashMap<String, PlaceItem>();

    private static final int COUNT = 25;

    public static void addItem(PlaceItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static int clear(){
        int prev_len = ITEMS.size();
        ITEMS.clear();
        ITEM_MAP.clear();
        return prev_len;
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class PlaceItem {
        public String id;
        public String title;
        public String address;
        public Double latitude;
        public Double longitude;
        public Float rating;
        public String image_reference;
        public String image_url;
        public Marker marker;
        public Bitmap image;

        public PlaceItem() {}

        public PlaceItem(String id, String title, String location, Float rating, String image_url) {
            this.id = id;
            this.title = title;
            this.address = location;
            this.rating = rating;
            this.image_url = image_url;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }
}
