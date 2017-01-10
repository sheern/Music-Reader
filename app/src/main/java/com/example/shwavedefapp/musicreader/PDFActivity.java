package com.example.shwavedefapp.musicreader;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class PDFActivity extends AppCompatActivity {

    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        container = (LinearLayout) findViewById(R.id.bitmap_container);
        PDFUtils renderer = new PDFUtils();
        renderer.renderAllPages((Uri) getIntent().getParcelableExtra(MusicDialog.EXTRA_URI));
        for(int i = 0; i < renderer.getNumPages(); i++) {

        }
    }
}