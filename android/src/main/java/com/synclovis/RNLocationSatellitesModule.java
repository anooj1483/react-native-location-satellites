
package com.synclovis;

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
        promise.resolve(8);

      }catch (Exception e){
        e.printStackTrace();
      }
  }
}