
import { NativeModules,NativeEventEmitter } from 'react-native';

const { RNLocationSatellites } = NativeModules;
const loc = {RNLocationSatellites,NativeEventEmitter}

export {RNLocationSatellites,NativeEventEmitter};
