#import "RNLocationSatellites.h"


@implementation RNLocationSatellites

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(sampleMethod:(NSString *)stringArgument numberParameter:(nonnull NSNumber *)numberArgument callback:(RCTResponseSenderBlock)callback)
{
    // TODO: Implement some actually useful functionality
	callback(@[[NSString stringWithFormat: @"numberArgument: %@ stringArgument: %@", numberArgument, stringArgument]]);
}

RCT_REMAP_METHOD(getLastKnownLocation,
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

@end
