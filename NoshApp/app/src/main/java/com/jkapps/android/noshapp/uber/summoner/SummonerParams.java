package com.jkapps.android.noshapp.uber.summoner;

import android.util.Pair;

import com.jkapps.android.noshapp.feedme.display.DisplayTask;
import com.uber.sdk.android.rides.RideRequestButton;

import com.jkapps.android.noshapp.apigateway.Business.Location;

public class SummonerParams {

    private final RideRequestButton rideRequestButton;
    private final Pair<Double, Double> coordinates;
    private final Location location;
    private final String name;
    private final DisplayTask displayTask;

    private SummonerParams(final Builder builder) {
        this.rideRequestButton = builder.rideRequestButton;
        this.coordinates       = builder.coordinates;
        this.location          = builder.location;
        this.name              = builder.name;
        this.displayTask       = builder.displayTask;
    }

    public RideRequestButton getRideRequestButton()
        { return rideRequestButton; }
    public Pair<Double, Double> getCoordinates() { return coordinates; }
    public Location getLocation() { return location; }
    public String getName() { return name; }
    public DisplayTask getDisplayTask() { return displayTask; }

    public static class Builder {

        private RideRequestButton rideRequestButton;
        private Pair<Double, Double> coordinates;
        private Location location;
        private String name;
        private DisplayTask displayTask;

        public Builder withRideRequestButton(final RideRequestButton rideRequestButton) {
            this.rideRequestButton = rideRequestButton;
            return this;
        }

        public Builder withCoordinates(final Pair<Double, Double> coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder withLocation(final Location location) {
            this.location = location;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withDisplayTask(final DisplayTask displayTask) {
            this.displayTask = displayTask;
            return this;
        }

        public SummonerParams build() { return new SummonerParams(this); }
    }
}
