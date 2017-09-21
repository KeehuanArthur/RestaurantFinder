package arthurlee.restaurantfinder;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFinderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFinderFragment extends Fragment implements OnMapReadyCallback {

    private static final String ARG_TITLE = "title";

    FragmentInteractionListener mListener;

    private String mTitle;
    public GoogleMap mGoogleMap;
    private MapView mMapView;

    public MapFinderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title title text
     * @return A new instance of fragment MapFinderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFinderFragment newInstance(String title) {
        MapFinderFragment fragment = new MapFinderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map_finder, container, false);

        final GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        switch( googleAPI.isGooglePlayServicesAvailable(getActivity()) ) {
            case ConnectionResult.SUCCESS:
                mMapView = (MapView) v.findViewById(R.id.map_view);
                mMapView.onCreate(savedInstanceState);
                mMapView.onResume();
                if( mMapView != null ) {
                    mMapView.getMapAsync(this);
                }
                break;
            case ConnectionResult.SERVICE_MISSING:
                Toast.makeText(getActivity(), "Google Play Service Missing", Toast.LENGTH_LONG).show();
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(getActivity(), "Google Play Update Required", Toast.LENGTH_LONG).show();
                break;
        }


        return v;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mGoogleMap = map;
        Location cur_location = ((MainActivity)mListener).mCurrentLocation;

        if (cur_location == null) {
            // TODO: create a new runnable that waits for location permissions to be filled.
        } else {
            mGoogleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(cur_location.getLatitude(), cur_location.getLongitude()) , 17) );
        }

        mGoogleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                Log.d("MapFinder", mGoogleMap.getCameraPosition().target.toString() );
                VisibleRegion visibleRegion = mGoogleMap.getProjection().getVisibleRegion();
                Double radius = distanceBetween(visibleRegion.farLeft, visibleRegion.farRight);
                LatLng center = mGoogleMap.getCameraPosition().target;
                Log.d("MAPFINDER", "-------- radius: " + String.format("%.4f", radius) );
                mListener.try_api(center.latitude, center.longitude, radius);
            }
        });

    }

    public double distanceBetween(LatLng pt1, LatLng pt2)
    {
        Location location1 = new Location("");
        Location location2 = new Location("");

        location1.setLatitude(pt1.latitude);
        location1.setLongitude(pt1.longitude);

        location2.setLatitude(pt2.latitude);
        location2.setLongitude(pt2.longitude);

        return location1.distanceTo(location2);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
