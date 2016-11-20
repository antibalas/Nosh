package com.jkapps.android.noshapp.feedme.buttons;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jkapps.android.noshapp.R;
import com.jkapps.android.noshapp.feedme.buttons.dislike.DislikeInitializer;
import com.jkapps.android.noshapp.feedme.buttons.goback.GoBackInitializer;
import com.jkapps.android.noshapp.feedme.buttons.overlapuber.OverlapUberInitializer;
import com.jkapps.android.noshapp.feedme.display.DisplayParams;
import com.jkapps.android.noshapp.feedme.display.DisplayTask;
import com.uber.sdk.android.rides.RideRequestButton;

public class InitializeAll extends AppCompatActivity {

    private static final Initializer[] BUTTON_INITIALIZERS = {
            new DislikeInitializer(),
            new GoBackInitializer(),
            new OverlapUberInitializer()
    };

    public void initializeButtons(final DisplayTask displayTask,
                                  final RideRequestButton uberButton,
                                  final DisplayParams displayParams,
                                  final Context context) {
        final InitializerParams initializerParams =
                getInitializerParams(displayTask, uberButton,
                        displayParams, context);
        for (Initializer initializer : BUTTON_INITIALIZERS)
            initializer.initialize(initializerParams);
    }

    private InitializerParams getInitializerParams
            (final DisplayTask displayTask,
             final RideRequestButton uberButton,
             final DisplayParams displayParams,
             final Context context) {
        return new InitializerParams.Builder()
                .withDisplayTask(displayTask)
                .withDisplayParams(displayParams)
                .withUberButton(uberButton)
                .withDislikeButton(getDislikeButton(context))
                .withOverlapUberButton(getOverlapUberButton(context))
                .withGoBackButton(getGoBackButton(context))
                .withContext(context)
                .build();
    }

    private Button getDislikeButton(final Context context) {
        return (Button) ((Activity) context).findViewById(R.id.Dislike);
    }

    private Button getOverlapUberButton(final Context context) {
        return (Button) ((Activity) context).findViewById(R.id.OverlapUberButton);
    }

    private Button getGoBackButton(final Context context) {
        return (Button) ((Activity) context).findViewById(R.id.GoBack);
    }
}

