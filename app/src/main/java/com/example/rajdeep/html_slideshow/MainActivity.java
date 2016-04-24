package com.example.rajdeep.html_slideshow;

import android.content.Context;

import java.io.File;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.os.*;


public class MainActivity extends AppCompatActivity {

    //private static final String[] LOCAL_RESOURCE = {"file:///android_asset/webpage.html","file:///android_asset/webpage2.html"};
    //private static String LOCAL_RESOURCE="file:///android_asset/";
    static int file_number=0;
    //static boolean isrunning=false;
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       final WebView wv=  (WebView) findViewById(R.id.webview);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        wv.addJavascriptInterface(new WebAppInterface(this), "Android");


        Bundle bundle=getIntent().getExtras();
       final String folder_name=bundle.getString("value");
        AssetManager assetManager = getAssets();

        try {
           final String[] webpagepath = assetManager.list(folder_name);
            //Toast.makeText(MainActivity.this, webpagepath[0], Toast.LENGTH_SHORT).show();
            showSlides(webpagepath,wv,folder_name);


        }
        catch(Exception e)
        {

            e.printStackTrace();
        }

        /*new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                Toast.makeText(MainActivity.this, "seconds remaining: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                //mTextField.setText("done!");
                wv.loadUrl(LOCAL_RESOURCE);
            }
        }.start();*/

    }
private void showSlides(final String[] webpagepath,final WebView wv,final String folder_name)
{
    wv.loadUrl("file:///android_asset/" + folder_name + "/" + webpagepath[file_number]);
    final Runnable runnable=new Runnable(){


        @Override
        public void run() {

            if (file_number < webpagepath.length-1) {
                file_number++;
                showSlides(webpagepath, wv, folder_name);
            }

        }
    };
    if(file_number<webpagepath.length-1)
        handler.postDelayed(runnable, 5000);
    else
        handler.removeCallbacks(runnable);

}


protected void onStop()
{
    super.onStop();
    file_number=0;

}

   /* public class MyCountDownTimer extends CountDownTimer
    {

        public MyCountDownTimer(long startTime, long interval)
        {
            super(startTime, interval);

        }

        @Override
        public void onFinish()
        {
            isrunning=false;
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            isrunning=true;

            Toast.makeText(MainActivity.this, "isrunning= "+isrunning, Toast.LENGTH_SHORT).show();

        }
    }*/




    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }
}
