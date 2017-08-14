package com.example.shwavedefapp.musicreader;

import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class PDFActivity extends AppCompatActivity {

    LinearLayout container;
    Uri uri;
    ScrollView scrollView;
    Button forward;
    Button auto;
    Button backward;

    // TODO 1 -- Once currentY hits ~8192, takes a dump
    private int currentY;
    private boolean scrollMode;
    private int speed = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        uri = getIntent().getParcelableExtra(DialogAdapter.EXTRA_URI);

        container = (LinearLayout) findViewById(R.id.bitmap_container);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        forward = (Button) findViewById(R.id.forward_scroll);
        auto = (Button) findViewById(R.id.auto_scroll);
        backward = (Button) findViewById(R.id.backward_scroll);
        PDFUtils renderer = new PDFUtils();
        renderer.renderAllPages(this, uri);

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scrollMode) {
                    speed -= 15;
                    if(speed < 5)
                        speed = 5;
                    reset(speed);
                } else {
                    currentY += scrollView.getHeight() - 30;
                    if(currentY > container.getHeight() - scrollView.getHeight())
                        currentY = container.getHeight() - scrollView.getHeight();
                    scrollView.smoothScrollTo(0, currentY);
                }
            }
        });
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollMode = !scrollMode;
                if(scrollMode) {
                    recursion(speed);
                }
            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scrollMode) {
                    if(speed <= 95) {
                        speed += 10;
                        reset(speed);
                    }
                    else
                        speed = 95;
                } else {
                    currentY -= scrollView.getHeight() - 30;
                    if (currentY < 0)
                        currentY = 0;
                    scrollView.smoothScrollTo(0, currentY);
                }
            }
        });
    }

    void recursion(final int setSpeed) {
        if(currentY < container.getHeight() && scrollMode) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentY++;
                    scrollView.scrollTo(0, currentY);
                    recursion(setSpeed);
                }
            }, setSpeed);
        }
    }

    void reset(final int setSpeed) {
        scrollMode = false;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollMode = true;
                recursion(setSpeed);
            }
        }, setSpeed + 5);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}