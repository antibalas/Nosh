package com.jkapps.android.noshapp.feedme.buttons.goback;

import android.content.Intent;
import android.view.View;

import com.jkapps.android.noshapp.MainActivity;
import com.jkapps.android.noshapp.feedme.buttons.Initializer;
import com.jkapps.android.noshapp.feedme.buttons.InitializerParams;

public class GoBackInitializer implements Initializer {

    @Override
    public void initialize(final InitializerParams initializerParams) {
        initializerParams.getGoBackButton()
                         .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializerParams.getContext()
                         .startActivity
                                 (new Intent(initializerParams.getContext(),
                                             MainActivity.class));
            }
        });
    }

}
