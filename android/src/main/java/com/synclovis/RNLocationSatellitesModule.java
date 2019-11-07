
package com.synclovis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;


import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RNLocationSatellitesModule extends ReactContextBaseJavaModule implements LocationListener{


  private final ReactApplicationContext reactContext;
  protected LocationManager locationManager;
  boolean isNetworkEnabled = false;
  boolean isGPSEnabled = false;

  public RNLocationSatellitesModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    locationManager = (LocationManager) reactContext.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
  }

  @Override
  public String getName() {
    return "RNLocationSatellites";
  }

  @SuppressLint("MissingPermission")
  @ReactMethod
  public void getLastKnownLocation(String eventName){
    try{
      Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      WritableMap params = Arguments.createMap();
      params.putString("latitude", "" + location.getLatitude());
      params.putString("longitude", "" + location.getLatitude());

      if(location.hasAccuracy()){
        params.putDouble("accuracy", location.getAccuracy());
      }
      if(location.hasAltitude()){
        params.putDouble("altitude", location.getAltitude());
      }
      if(location.hasSpeed()){
        params.putDouble("speed", location.getSpeed());
      }
      if(location.hasBearing()){
        params.putDouble("bearing", location.getBearing());
      }
      params.putString("elapsed_time",""+location.getElapsedRealtimeNanos());
      params.putInt("satellites", location.getExtras().getInt("satellites",0));

      sendEvent(reactContext, eventName, params);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @SuppressLint("MissingPermission")
  @ReactMethod
  public void startLocationUpdate(){

    try{

      isGPSEnabled = locationManager
              .isProviderEnabled(LocationManager.GPS_PROVIDER);
      isNetworkEnabled = locationManager
              .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

      if(isNetworkEnabled){
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
      }

      if(isGPSEnabled){
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
      }


    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void onLocationChanged(Location location) {
    try {
      //Toast.makeText(reactContext, "Location updated", Toast.LENGTH_LONG).show();
      WritableMap params = Arguments.createMap();
      params.putString("latitude", "" + location.getLatitude());
      params.putString("longitude", "" + location.getLatitude());

      if(location.hasAccuracy()){
        params.putDouble("accuracy", location.getAccuracy());
      }
      if(location.hasAltitude()){
        params.putDouble("altitude", location.getAltitude());
      }
      if(location.hasSpeed()){
        params.putDouble("speed", location.getSpeed());
      }
      if(location.hasBearing()){
        params.putDouble("bearing", location.getBearing());
      }
      params.putString("elapsed_time",""+location.getElapsedRealtimeNanos());
      params.putInt("satellites", location.getExtras().getInt("satellites",-1));

      sendEvent(reactContext, "RNSatellite", params);
    }catch (Exception e){
      Toast.makeText(reactContext,"ONError"+e,Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onStatusChanged(String s, int i, Bundle bundle) {

  }

  @Override
  public void onProviderEnabled(String s) {

  }

  @Override
  public void onProviderDisabled(String s) {

  }

  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         WritableMap params) {
    reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
  }
}