package apihelpers.googleapis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ironsquishy.biteclub.R;

import java.util.List;
import java.util.Objects;

import ApiManagers.DatabaseManager;
import ApiManagers.LocationHandler;
import ApiManagers.NetworkRequestManager;
import ApiManagers.UntappdManager;
import apihelpers.SQLiteHandler.VisitedPlace;
import apihelpers.Untappd.UntappdData;
import apihelpers.YelpApiHandler.Restaurant;


/**
 * Created by Allen Space on 7/14/2015.
 * Edited by Guan .
 * Edited by Renz icons 7/29/15
 */
public class MarkerMapFactory {

    private static MarkerOptions mMarkers;
    private static GoogleMap mGoogleMap;
    private static DatabaseManager mDatabaseManager;
    private static Restaurant mRestaurant;
    private static Context context;

    /**
     * @author Allen Space
     *@param pGoogleMap GoogleMap java object.
     *Description: Main class constructor
     * */
    public MarkerMapFactory(GoogleMap pGoogleMap, Context pContext)
    {
        mGoogleMap =  pGoogleMap;
        mDatabaseManager =  DatabaseManager.getInstance(pContext);
        context = pContext;
    }

    public MarkerMapFactory(Restaurant pRestaurant)
    {
        mRestaurant = pRestaurant;
    }

    /**
     * @author Allen Space
     * Description: Call to place marker on clients location in google maps.
     * @return Marker object.
     * */
    public Marker createClientMarker()
    {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(LocationHandler.getmLatitude(), LocationHandler.getmLongitude()))
                .title("You're Here!");
                //.snippet(LocationHandler.streetAddress + ": " + LocationHandler.cityAddress);

        Bitmap userIcon = convertToBitmapMarkers(R.drawable.gmarker_user);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(userIcon));
        Marker  clientMarker = mGoogleMap.addMarker(markerOptions);

        return clientMarker;
    }


    /**
     * @author Allen Space Edited by Guan
     * @return Marker java object
     * Description: Factory method that will create the resulting marker from yelp response.
     * */
    public Marker createResultMarker()
    {

        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(mRestaurant.getmLatitude(), mRestaurant.getmLongitude()))
                .title(mRestaurant.getmRestName());

        Bitmap burgerIcon = convertToBitmapMarkers(R.drawable.gmarker_burger);

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(burgerIcon));

        Marker marker = mGoogleMap.addMarker(markerOptions);
        marker.showInfoWindow();

        return marker;
    }

    /**
     * @author: Guan
     * Description: turn a resource image into bitmap and resized to google marker's size
     * @param id the resource ID of the image data
     * @return Bitmap object of resized image
     */
    private Bitmap convertToBitmapMarkers(int id) {
        Bitmap marker = BitmapFactory.decodeResource(context.getResources(), id);
        marker = Bitmap.createScaledBitmap(marker, 100, 132, true);

        return marker;
    }

    /**
     * @author: Guan
     * Description: populate google map with custom markers of user's favorite location
     */
    public void createHistoryMarkers()
    {
        List<VisitedPlace> restaurants;

        if (!mDatabaseManager.isDatabaseEmpty()) {
            restaurants = mDatabaseManager.getAllVisitedPlaces();

            Bitmap starIcon = convertToBitmapMarkers(R.drawable.gmarker_star);

            for (int i = 0; i < restaurants.size(); i++) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(restaurants.get(i).get_latitude(), restaurants.get(i).get_longitude()));
                markerOptions.title(restaurants.get(i).get_name());
                markerOptions.snippet("Visited " + restaurants.get(i).get_date());
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(starIcon));

                mGoogleMap.addMarker(markerOptions);
            }
        }
    }

    public void createUntappdMarkers(Context context)
    {
        UntappdManager untappdManager = new UntappdManager();

        List<UntappdData.Item> listItems;

        listItems = untappdManager.getListItems();

        for(int i = 0; i < listItems.size(); i++)
        {
            final double beerLat = listItems.get(i).venue.location.lat;
            final double beerLng = listItems.get(i).venue.location.lng;
            final String beerTitle = listItems.get(i).beer.beer_name;

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(beerLat, beerLng));
            markerOptions.title("Drink: " + beerTitle);

            // use this line for custom marker
            Bitmap bitmap = convertToBitmapMarkers(R.drawable.gmarker_beer);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));

            Marker marker = mGoogleMap.addMarker(markerOptions);
        }

    }

}
