package com.example.mentalhealthcounselling1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class VideoTrial extends AppCompatActivity {

    WebView webView;
    String videoUrl = "<iframe width=\"100\" height=\"100%\" src=\"https://www.youtube.com/embed/6rqIEhuHIYY?si=9CviF8cs8xGjgcQp\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"; // Replace with the actual video URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_trial);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(videoUrl, "text/html", "utf-8");
        webView.setWebChromeClient(new WebChromeClient());

    }
}