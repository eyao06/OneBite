package com.ironsquishy.biteclub;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import ApiManagers.DatabaseManager;
import apihelpers.YelpApiHandler.Restaurant;

/**
 * @author Allen Space
 * Description: This was created to have a standalone activity of maps.
 *
 * */
public class MapActivity extends ActionBarActivity {

    private static Restaurant mRestaurant;
    private static DatabaseManager mDatabaseManager;

    /**
     * @author Allen Space
     * Description: on create goes to map fragment in layouts.
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mDatabaseManager = DatabaseManager.getInstance(this);
    }

    /**
     * @author: Eric Edited by Guan
     * Description: delete all content in the database when user click on a button
     * @param view View object
     */
    public void clearHistory(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Deleting Favorites")
                .setMessage("Are you sure you want to delete all favorites?")
                .setPositiveButton("YOLO!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDatabaseManager.clearDatabase();
                        recreate();
                        Toast.makeText(getApplicationContext(), "All favorites Cleared.",
                                Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Just kidding!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * @author Allen Space
     * Descriptio: On start any changes.
     * */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * @author Allen Space
     * Desciption: Private method that adds a fragment to the this activity.
     *             The MapFragment fragment layout must be on the xml layout.
     *
     * Only needed when making call to create.
     * */
    private void addFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MapFragment fragment = new MapFragment();
        transaction.add(R.id.mapView, fragment);
        transaction.commit();
    }


}


