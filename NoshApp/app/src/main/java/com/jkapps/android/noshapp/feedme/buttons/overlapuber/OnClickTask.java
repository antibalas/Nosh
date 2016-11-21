package com.jkapps.android.noshapp.feedme.buttons.overlapuber;
import android.os.AsyncTask;

import com.jkapps.android.noshapp.feedme.buttons.InitializerParams;

import java.util.concurrent.Semaphore;

class OnClickTask extends
        AsyncTask<InitializerParams, Void, InitializerParams> {

    //so if you hit it a bunch of times before it loads, it will still
    //only load once
    private static final Semaphore PREVENT_MULTIPLE_UBER_ON_CLICKS =
            new Semaphore(1);

    //every on click will start this on a new thread (so we need the sem)
    //normally doInBackground ops are all on single background thread
    @Override
    public InitializerParams doInBackground
            (InitializerParams ... initializerParams) {

        if (!PREVENT_MULTIPLE_UBER_ON_CLICKS.tryAcquire())
            return null;

        try { //handles race 2 for uber
            synchronized (initializerParams[0].getDisplayTask()
                    .rideParametersSetLock) {

                while (!initializerParams[0].getDisplayTask()
                        .rideParametersSet.get()) {
                    initializerParams[0].getDisplayTask()
                            .rideParametersSetLock.wait();
                }
            }
        } catch (InterruptedException e)
            { throw new RuntimeException(e); }

        return initializerParams[0];
    }

    @Override
    public void onPostExecute(InitializerParams initializerParams) {
        if (initializerParams == null) return; //didn't get the semaphore

        /*
         * technically a race could still be possible here depending on how
         * uber made their onClick action:
         *
         * if it operates totally asynchronously (like our's does) then this
         * method could callOnClick without changing the UI at which point the
         * semaphore would be released
         *
         * then, if the user hits the button again, we would end up with 2 uber
         * on click methods loading since the semaphore could be acquired again
         *
         * so, after the first one ended, the second one would start up
         *
         * uber almost definitely implements onClick asynchronously, but since
         * this is a very difficult race to trigger (that I have no idea how to
         * prevent) and just causes the uber app to load twice, it's not a big
         * deal
         */

        initializerParams.getUberButton().callOnClick();
        PREVENT_MULTIPLE_UBER_ON_CLICKS.release();
    }
}
