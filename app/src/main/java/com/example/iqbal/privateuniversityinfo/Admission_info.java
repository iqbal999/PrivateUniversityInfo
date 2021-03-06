package com.example.iqbal.privateuniversityinfo;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;

public class Admission_info extends Fragment {

    private RequestQueue mqueue;
    String ShowOrHideWebViewInitialUse = "show";
    private WebView webview;
    private ProgressBar spinner;
    String admission;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            admission = getArguments().getString("admission");
        }
        View v = inflater.inflate(R.layout.admission_info, container, false);
        spinner = (ProgressBar) v.findViewById(R.id.progressBar);

        webview = v.findViewById(R.id.infoWV);
        if (admission.equals("")) {
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();

        } else {
            webview.setWebViewClient(new CustomWebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            // load faster
            webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webview.getSettings().setAppCacheEnabled(true);
            webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            webview.getSettings().setUseWideViewPort(true);
            if (Build.VERSION.SDK_INT >= 19) {
                webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            } else {
                webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }

            webview.getSettings().setDomStorageEnabled(true);
            webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
            webview.loadUrl(admission);

        }

        return v;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(
                isVisibleToUser);


        if (getFragmentManager() != null) {

            getFragmentManager()
                    .beginTransaction()
                    .detach(this)
                    .attach(this)
                    .commit();
        }
    }

    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {

            // only make it invisible the FIRST time the app is run
            if (ShowOrHideWebViewInitialUse.equals("show")) {
                webview.setVisibility(webview.INVISIBLE);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            ShowOrHideWebViewInitialUse = "hide";
            spinner.setVisibility(View.GONE);

            view.setVisibility(webview.VISIBLE);
            super.onPageFinished(view, url);

        }
    }
}
