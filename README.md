
# react-native-location-satellites

<img src="https://github.com/anooj1483/react-native-location-satellites/blob/master/example/logo.png?raw=true" data-canonical-src="https://github.com/anooj1483/react-native-location-satellites/blob/master/example/logo.png?raw=true" width="130" height="130" />

## Getting started

`$ npm install react-native-location-satellites --save`

### NOTE: Due to security issues iOS will not disclose satellite counts.
### iOS library will give you location details except satellite count

### Mostly automatic installation

`$ react-native link react-native-location-satellites`

### Manual installation


#### Android

1. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-location-satellites'
  	project(':react-native-location-satellites').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-location-satellites/android')
  	```
2. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-location-satellites')
  	```
3. Append the following lines in `MainApplication.java`
	```
	import com.synclovis.RNLocationSatellitesPackage;
	protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(                 
                    new RNLocationSatellitesPackage()
            );
    }
	```

### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-location-satellites` and add `RNLocationSatellites.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNLocationSatellites.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

### Using Cocoapods

1. In Terminal, from your root folder, `cd iOS`
2. Open PodFile, add the following,
	```
	 pod 'react-native-location-satellites', :path => '../node_modules/react-native-location-satellites'
	 ```
3. Run `pod install`


## Usage

Make sure that you have accessed right permissions for getting location.

This library will give you the following location details,
1. latitude & longitude
2. accuracy
3. speed
4. altitude
5. bearing
6. satellites


```javascript
import {NativeEventEmitter} from 'react-native';
import {RNLocationSatellites} from 'react-native-location-satellites';
const GPSEventEmitter = new NativeEventEmitter(RNLocationSatellites)
componentDidMount(){

	/**
	 * iOS Only - Request location permission (iOS 13 Support)
	 */
	RNLocationSatellites.requestAlwaysAuthorization()
		
	/**
	 * Event for getting location. 
	 */
    GPSEventEmitter.addListener('RNSatellite', (event) => {
      console.log(event);      
   	})
	
	/**
	 * Starts location update 
	 */
   	RNLocationSatellites.startLocationUpdate()
    

	
	/**
	 * Android Only - Returns the last known location (GPS)
	 */
	RNLocationSatellites.getLastKnownLocation().then((location)=>{
      console.log("Last known location: ",location)
    })
}

componentWillUnmount(){
	GPSEventEmitter.removeListener('RNSatellite')	
}

```