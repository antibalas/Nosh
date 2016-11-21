package com.jkapps.android.noshapp.feedme.buttons.dislike;

import android.view.View;

import com.jkapps.android.noshapp.feedme.buttons.Initializer;
import com.jkapps.android.noshapp.feedme.buttons.InitializerParams;

public class DislikeInitializer implements Initializer {

    @Override
    public void initialize(final InitializerParams initializerParams) {
        initializerParams.getDislikeButton()
                         .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializerParams.getDisplayTask().rideParametersSet.set(false);
                initializerParams.getDisplayTask()
                                 .displayNextYelpPage
                                         (initializerParams.getDisplayParams());
            }
        });
    }

}

