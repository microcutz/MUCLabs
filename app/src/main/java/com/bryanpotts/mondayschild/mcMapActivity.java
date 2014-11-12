package com.bryanpotts.mondayschild;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryan on 12/11/14.
 */
public class mcMapActivity extends FragmentActivity {

    List<mcMapData> mapDataLst;
    private Marker[] mapDataMarkerList = new Marker[5];
    private GoogleMap mapStarSigns; // Google map variable
    private float markerColours[] = {210.0f, 120.0f, 300.0f, 330.0f, 270.0f };

    private LatLng latlangEKCentre = new LatLng(55.7591402, -4.1883331); // The lat & long for teh centre of East Kilbride

    // ****************************************************************
    // onCreate
    // An event handler called when the app is first created
    // Usually contains all initializations and setting up for the app.
    // Generally the place to show the app main UI screen
    // ****************************************************************

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.mc_map_view); // App main UI screen

        mapDataLst = new ArrayList<mcMapData>();
        mcMapDataDBMgr mapDB = new mcMapDataDBMgr(this, "mapEKFamous5.s3db", null, 1);
        try
        {
            mapDB.dbCreate();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        mapDataLst = mapDB.allMapData();
        SetUpMap();
        AddMarkers();
    }

    public void SetUpMap() {
        mapStarSigns = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();    // Create the map and apply to the variable
        if (mapStarSigns != null) {
            mapStarSigns.moveCamera(CameraUpdateFactory.newLatLngZoom(latlangEKCentre, 12));        // Set the default position to EK
            mapStarSigns.setMyLocationEnabled(true);                                                // Turn on GPS
            mapStarSigns.getUiSettings().setCompassEnabled(true);                                   // Turn on the Compass
            mapStarSigns.getUiSettings().setMyLocationButtonEnabled(true);                          // Turn on the Location Button Facility
            mapStarSigns.getUiSettings().setRotateGesturesEnabled(true);
        }
    }

    public void AddMarkers() {

        MarkerOptions marker;
        mcMapData mapData;
        String mrkTitle;
        String mrkText;

        /* For all the marker options in dbList list */
        for (int i = 0; i < mapDataLst.size(); i++) {
            mapData = mapDataLst.get(i);
            mrkTitle = mapData.getFirstname() + " " + mapData.getSurname() + " Occupation: " + mapData.getOccupation();
            mrkText = "Star Sign: " + mapData.getStarSign();
            marker = SetMarker(mrkTitle, mrkText, new LatLng(mapData.getLatitude(), mapData.getLongitude()), markerColours[i], true);
            mapDataMarkerList[i] = mapStarSigns.addMarker(marker); // Create a marker and add it to the venue markers list
        }
    }

    public MarkerOptions SetMarker(String title, String snippet, LatLng position, float markerColour, Boolean centreAnchor) {

        float anchorX; // Create anchorX
        float anchorY; // Create anchorY

        /* on the condition the anchor is to be centred */
        if (centreAnchor) {
            anchorX = 0.5f; // Centre X
            anchorY = 0.5f; // Centre Y
        } else {
            anchorX = 0.5f; // Centre X
            anchorY = 1.0f; // Bottom Y
        }

        // Create Marker options from the input varaiables
        MarkerOptions marker = new MarkerOptions().title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(markerColour)).anchor(anchorX, anchorY).position(position);

        return marker; // Return marker


    }

}
