package com.jkapps.android.noshapp.feedme.yelp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;

public class YelpWebView extends WebView {

    private boolean injectedJavascript = false;

    public void haveInjectedJavascript() { injectedJavascript = true; }

    public YelpWebView(final Context context) {
        super(context);
    }

    public YelpWebView(final Context context,
                       final AttributeSet attrs) {
        super(context, attrs);
    }

    public YelpWebView(final Context context,
                       final AttributeSet attrs,
                       final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void invalidate() {
        if (injectedJavascript && getContentHeight() > 1000) {
            setVisibility(View.VISIBLE);
            injectedJavascript = false;
        }
        if (injectedJavascript) setVisibility(View.INVISIBLE);
    }
}

