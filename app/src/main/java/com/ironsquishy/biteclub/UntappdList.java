package com.ironsquishy.biteclub;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import Callbacks.GeneralCallback;
import ApiManagers.NetworkRequestManager;
import apihelpers.SelectedBusiness;
import ApiManagers.UntappdManager;


public class UntappdList extends ActionBarActivity {

    private static ListView untappdListView;
    private static UntappdManager untappdData;
    private static SelectedBusiness mSelectedBusiness;

    private static SwipeRefreshLayout swipeRefreshLayout;

    private static String[] items;
    private static int resource = android.R.layout.simple_list_item_1;
    private static int textViewResourceID = android.R.id.text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_untappd_list);


        refreshFeed();
    }

   @Override
    protected void onStart() {
       super.onStart();


    }

    private void refreshFeed()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Getting Untappd feed.");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();
        final Context context = this;
        progressDialog.dismiss();



    }
}

