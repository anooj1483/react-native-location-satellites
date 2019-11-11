#import <CoreLocation/CoreLocation.h>
#import "RNLocationSatellites.h"

@interface RNLocationSatellites() <CLLocationManagerDelegate>

@property (strong, nonatomic) CLLocationManager *locationManager;
@property (strong, nonatomic) RCTPromiseResolveBlock alwaysPermissionResolver;
@property (strong, nonatomic) RCTPromiseResolveBlock whenInUsePermissionResolver;
@property (nonatomic) BOOL hasListeners;

@end

@implementation RNLocationSatellites


RCT_EXPORT_MODULE()


RCT_REMAP_METHOD(getKnownLocation,
                 findEventsWithResolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
  NSArray *location = @[@"LAT", @"LONG"];
  //if (events) {
    resolve(location);
//  } else {
//    NSError *error = ...
//    reject(@"no_events", @"There were no events", error);
//  }
}


+ (BOOL)requiresMainQueueSetup
{
    return YES;
}



- (instancetype)init
{
    if (self = [super init]) {
        self.locationManager = [[CLLocationManager alloc] init];
        self.locationManager.delegate = self;
    }

    return self;
}

RCT_EXPORT_METHOD(startMonitoringSignificantLocationChanges)
{
    [self.locationManager startMonitoringSignificantLocationChanges];
}

RCT_EXPORT_METHOD(startLocationUpdate)
{
    [self.locationManager startUpdatingLocation];
}

- (void)locationManager:(CLLocationManager *)manager didUpdateLocations:(NSArray *)locations
{
    if (!self.hasListeners) {
        return;
    }
    
    NSMutableArray *results = [NSMutableArray arrayWithCapacity:[locations count]];
    [locations enumerateObjectsUsingBlock:^(CLLocation *location, NSUInteger idx, BOOL *stop) {
        [results addObject:@{
                             @"latitude": @(location.coordinate.latitude),
                             @"longitude": @(location.coordinate.longitude),
                             @"altitude": @(location.altitude),
                             @"accuracy": @(location.horizontalAccuracy),
                             @"altitudeAccuracy": @(location.verticalAccuracy),
                             @"course": @(location.course),
                             @"speed": @(location.speed),
                             @"floor": @(location.floor.level),
                             @"timestamp": @([location.timestamp timeIntervalSince1970] * 1000) // in ms
                             }];
    }];
    

    [self sendEventWithName:@"RNSatellite" body:results];
}


- (NSArray<NSString *> *)supportedEvents
{
  return @[@"RNSatellite"];
}

@end
