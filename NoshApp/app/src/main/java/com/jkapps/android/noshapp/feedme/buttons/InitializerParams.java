package com.jkapps.android.noshapp.feedme.buttons;

import android.content.Context;
import android.widget.Button;

import com.jkapps.android.noshapp.feedme.display.DisplayParams;
import com.jkapps.android.noshapp.feedme.display.DisplayTask;
import com.uber.sdk.android.rides.RideRequestButton;

public class InitializerParams {

    private final DisplayTask displayTask;
    private final DisplayParams displayParams;
    private final RideRequestButton uberButton;
    private final Button overlapUberButton;
    private final Button dislikeButton;
    private final Button goBackButton;
    private final Context context;

    private InitializerParams(Builder builder) {
        this.displayTask       = builder.displayTask;
        this.displayParams     = builder.displayParams;
        this.uberButton        = builder.uberButton;
        this.overlapUberButton = builder.overlapUberButton;
        this.dislikeButton     = builder.dislikeButton;
        this.goBackButton      = builder.goBackButton;
        this.context           = builder.context;
    }

    public DisplayParams getDisplayParams() { return displayParams; }
    public DisplayTask getDisplayTask() { return displayTask; }
    public RideRequestButton getUberButton() { return uberButton; }
    public Button getOverlapUberButton() { return overlapUberButton; }
    public Button getDislikeButton() { return dislikeButton; }
    public Button getGoBackButton() { return goBackButton; }
    public Context getContext() { return context; }

    public static class Builder {

        private DisplayTask displayTask;
        private DisplayParams displayParams;
        private RideRequestButton uberButton;
        private Button goBackButton;
        private Button overlapUberButton;
        private Button dislikeButton;
        private Context context;

        public Builder withDisplayTask(final DisplayTask displayTask) {
            this.displayTask = displayTask;
            return this;
        }

        public Builder withDisplayParams(final DisplayParams displayParams) {
            this.displayParams = displayParams;
            return this;
        }

        public Builder withUberButton(final RideRequestButton uberButton) {
            this.uberButton = uberButton;
            return this;
        }

        public Builder withOverlapUberButton(final Button overlapUberButton) {
            this.overlapUberButton = overlapUberButton;
            return this;
        }

        public Builder withDislikeButton(final Button dislikeButton) {
            this.dislikeButton = dislikeButton;
            return this;
        }

        public Builder withGoBackButton(final Button goBackButton) {
            this.goBackButton = goBackButton;
            return this;
        }

        public Builder withContext(final Context context) {
            this.context = context;
            return this;
        }

        public InitializerParams build() {
            return new InitializerParams(this);
        }
    }
}
