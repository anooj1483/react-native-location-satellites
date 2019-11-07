
package com.synclovis;

import android.location.Location;
import android.location.LocationManager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

public class RNLocationSatellitesModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNLocationSatellitesModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNLocationSatellites";
  }

  @ReactMethod
  public void getSatellites(float latitude, float longitude, Promise promise){

      try{
        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        WritableMap map = Arguments.createMap();
        map.putInt("satellites",location.getExtras().getInt("satellites",-1));
        promise.resolve(map);

      }catch (Exception e){
        e.printStackTrace();
      }
  }
}