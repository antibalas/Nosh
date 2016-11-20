package com.jkapps.android.noshapp.feedme.yelp;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class YelpWebViewClient extends WebViewClient {

    private boolean doneLoadingPage = false;

    public void setDone() { doneLoadingPage = true; }

    @Override
    public void onPageStarted(final WebView webView,
                              final String url,
                              final Bitmap bitmap) {
        super.onPageStarted(webView, url, bitmap);
        webView.setVisibility(View.GONE);
    }

    @Override //inject our javascript here
    public void onPageFinished(final WebView webView, final String url) {
        if (doneLoadingPage) {
            super.onPageFinished(webView, url);
            webView.loadUrl("javascript:(function() {" +
                    "document.getElementById('fullscreen-pitch').style.display=\"none\";" +
                    "})()");
            ((YelpWebView) webView).haveInjectedJavascript();
        }
    }
}
