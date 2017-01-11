package com.example.shwavedefapp.musicreader;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar mMetronomeBar;
    TextView mDynamicBPM;
    Button mAddMusic;
    TextView mNoMusicHint;
    LinearLayout mListSheets;
    MusicDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMetronomeBar = (SeekBar) findViewById(R.id.metronome_bar);
        mDynamicBPM = (TextView) findViewById(R.id.dynamic_bpm);
        Metronome metronome = new Metronome();
        metronome.create(this, mMetronomeBar, mDynamicBPM);

        mAddMusic = (Button) findViewById(R.id.add_music_button);
        mListSheets = (LinearLayout) findViewById(R.id.list_sheets);
        mNoMusicHint = (TextView) findViewById(R.id.no_music_text);
        mAddMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new MusicDialog(MainActivity.this);
                dialog.dialogSetup();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MusicDialog.READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                dialog.fileUri = data.getData();
                dialog.browseFile.setText(dialog.fileUri.getPath());
            }
        }
    }
}