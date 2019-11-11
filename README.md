
# react-native-location-satellites

<img src="https://github.com/anooj1483/react-native-location-satellites/blob/master/example/logo.png?raw=true" data-canonical-src="https://github.com/anooj1483/react-native-location-satellites/blob/master/example/logo.png?raw=true" width="130" height="130" />

## Getting started

`$ npm install react-native-location-satellites --save`

### NOTE: Supports only Android. Due to security issues iOS will not disclose satellite counts.
### Upcoming release: Supports iOS for getting normal location in background.

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
	 * Starts location update 
	 */
	RNLocationSatellites.startLocationUpdate();
	
	/**
	 * Event for getting location. 
	 */
    GPSEventEmitter.addListener('RNSatellite', (event) => {
      alert(JSON.stringify(event))
   	})
	
	/**
	 * Returns the last known location (GPS)
	 */
	RNLocationSatellites.getLastKnownLocation().then((location)=>{
      console.log("Last known location: ",location)
    })
}

componentWillUnmount(){
	GPSEventEmitter.removeListener('RNSatellite')	
}

```