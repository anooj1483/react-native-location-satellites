using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Location.Satellites.RNLocationSatellites
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNLocationSatellitesModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNLocationSatellitesModule"/>.
        /// </summary>
        internal RNLocationSatellitesModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNLocationSatellites";
            }
        }
    }
}
