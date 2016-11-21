package com.jkapps.android.noshapp.feedme.buttons.overlapuber;

import android.os.AsyncTask;
import android.view.View;

import com.jkapps.android.noshapp.feedme.buttons.Initializer;
import com.jkapps.android.noshapp.feedme.buttons.InitializerParams;

public class OverlapUberInitializer implements Initializer {

    @Override
    public void initialize(final InitializerParams initializerParams) {
        initializerParams.getOverlapUberButton()
                         .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                (new OnClickTask()).executeOnExecutor
                        (AsyncTask.THREAD_POOL_EXECUTOR, initializerParams);
            }
        });
    }
}

