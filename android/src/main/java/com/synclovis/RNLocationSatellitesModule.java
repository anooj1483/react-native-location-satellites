
package com.synclovis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
  protected LocationListener locationListener;

  public RNLocationSatellitesModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNLocationSatellites";
  }

  @SuppressLint("MissingPermission")
  @ReactMethod
  public void startLocationUpdate(){

    try{
      locationManager = (LocationManager) reactContext.getSystemService(Context.LOCATION_SERVICE);
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 5, this);

    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void onLocationChanged(Location location) {
    WritableMap params = Arguments.createMap();
    params.putString("satellite", ""+location.getLatitude());
    sendEvent(reactContext, "RNSatellite", params);
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
                         @Nullable WritableMap params) {
    reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
  }
}