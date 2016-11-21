package com.jkapps.android.noshapp.uber.summoner;

import com.uber.sdk.android.rides.RideParameters;

public class Summoner {

    private static String getFormattedAddress
            (final SummonerParams summonerParams) {
        return summonerParams.getLocation().getAddress1() + ", " +
               summonerParams.getLocation().getCity();
    }

    private static RideParameters getRideParameters
            (final SummonerParams summonerParams) {
        return (new RideParameters.Builder())
                .setDropoffLocation(summonerParams.getCoordinates().first,
                                    summonerParams .getCoordinates().second,
                                    summonerParams.getName(),
                                    getFormattedAddress(summonerParams))
                .setPickupToMyLocation()
                .build();
    }

    public static void setRideParameters(final SummonerParams summonerParams) {
        summonerParams.getRideRequestButton()
                      .setRideParameters(getRideParameters(summonerParams));
    }

}
