package com.ironsquishy.biteclub;

<<<<<<< HEAD
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
/**
 * Created by Allen Space on 6/22/2015.
 */
public class Randomizer  {

    /*** Data Fields*/
    private Thread mThread;
    //private SQLiteHandlers
    private static String mReturnString;
    private static String mFindString;
    //private static GoogleApiClient mGoogleApiClient;

=======
import java.util.Random;

/**
 * Created by Allen Space on 6/22/2015.
 */
public class Randomizer {

    /**
     * Data Fields
     */
    private static Random mRandom;
    private static String mReturnString;
    private static String mFindString;
    private static String[] stringArray = {"Ice Cream", "Donut", "Burgers", "Pizza",
                                            "Sushi", "Lollipop", "Burrito", "Tacos"};
>>>>>>> 1d101b007a310c3c70f15bb45091ca780a9aa196
    //================================
    //***Constructors*****
    //================================
    //***Setters**********
    //================================
    //***Getters***********
    //================================
    //***Helpers***********
    //================================
<<<<<<< HEAD
    //***Thread run********
    //================================

    /***
     * @author Allen Space
     * */
    public Randomizer(Context context)
    {
        /*mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) context)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) context)
                .addApi(LocationServices.API)
                .build();*/
    }


    /***
     * @author Allen Space
     * */
=======

    /**
     * @author Allen Space
     */
    public Randomizer() {
        mRandom = new Random();
    }


    /**
     * @author Allen Space
     */
>>>>>>> 1d101b007a310c3c70f15bb45091ca780a9aa196
    public static void setmReturnString(String mReturnString) {
        Randomizer.mReturnString = mReturnString;
    }

    /**
     * @author Allen Space
<<<<<<< HEAD
     * */
=======
     */
>>>>>>> 1d101b007a310c3c70f15bb45091ca780a9aa196
    public static void setmFindString(String mFindString) {
        Randomizer.mFindString = mFindString;
    }

<<<<<<< HEAD
    protected synchronized void buildGoogleApiClient()
    {

    }
    /**
     * @author Allen Space
     * *
    @Override
    public void run()
    {

    }*/
=======
    /**
     * @return A random string.
     * @author Allen Space
     */
    public String getRandomString() {
        int i = mRandom.nextInt(stringArray.length);

        return stringArray[i];
    }

>>>>>>> 1d101b007a310c3c70f15bb45091ca780a9aa196
}
