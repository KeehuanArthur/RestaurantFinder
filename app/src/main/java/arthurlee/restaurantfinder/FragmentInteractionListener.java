package arthurlee.restaurantfinder;

/**
 * Created by arthur on 9/20/2017.
 */

public interface FragmentInteractionListener {
    void try_api(Double latitude, Double longitude, Double radius);
    void openDetailsActivity(String title, String location, float rating, String placeId);
}
