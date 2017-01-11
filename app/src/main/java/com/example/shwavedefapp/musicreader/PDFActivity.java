package com.example.shwavedefapp.musicreader;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PDFActivity extends AppCompatActivity {

    LinearLayout container;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        uri = getIntent().getParcelableExtra(MusicDialog.EXTRA_URI);
        container = (LinearLayout) findViewById(R.id.bitmap_container);
        PDFUtils renderer = new PDFUtils();
        TextView test = new TextView(this);
        test.setText(uri.getPath());
        container.addView(test);
        renderer.renderAllPages(this, uri);
//        for(int i = 0; i < renderer.getNumPages(); i++) {
//            ImageView page = new ImageView(this);
//            page.setImageBitmap(renderer.result[i]);
//            container.addView(page);
//        }
    }
}