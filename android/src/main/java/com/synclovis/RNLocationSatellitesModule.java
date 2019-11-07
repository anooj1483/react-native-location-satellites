
package com.synclovis;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

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
  public int getSatellites(Double latitude, Double longitude){
      return 8;
  }
}