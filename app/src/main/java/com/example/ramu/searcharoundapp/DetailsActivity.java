package com.example.ramu.searcharoundapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback {


    GoogleMap mgoogleMap;
    WebView webView;
    Results result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        webView = (WebView) findViewById(R.id.webview);


        result = getIntent().getParcelableExtra("singleResult");
        webView.loadUrl(result.businessURL);

        if(isGoogleMapAvailable()){
            initMap();
        }


    }

    private void initMap() {
        MapFragment mapfragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapfragment.getMapAsync(this);
    }

    public boolean isGoogleMapAvailable(){

        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS)
            return true;
        else if(api.isUserResolvableError(isAvailable)){
            Dialog dialog = api.getErrorDialog(this,isAvailable,0);
            dialog.show();
        }
        else
            Toast.makeText(this,"Google Play services is not there", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;

        /**
         * ZOoming into the given locaiton in google maps
         */
        LatLng ll = new LatLng(result.latitude,result.longitude);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,15);
        mgoogleMap.animateCamera(update);

        /***
         * marking the google maps locations
         */
        MarkerOptions options = new MarkerOptions()
                .title(result.title)
                .snippet(result.phone)
                .position(ll);
        mgoogleMap.addMarker(options);

    }
}
