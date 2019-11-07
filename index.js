
import { NativeModules,NativeEventEmitter } from 'react-native';

const { RNLocationSatellites } = NativeModules;
const GPSEventEmitter = new NativeEventEmitter(RNLocationSatellites);
export {RNLocationSatellites,GPSEventEmitter};
