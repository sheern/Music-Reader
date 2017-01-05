package com.example.shwavedefapp.musicreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar mMetronomeBar;
    TextView mDynamicBPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        metronome();
    }

    public void metronome() {
        mMetronomeBar = (SeekBar) findViewById(R.id.metronome_bar);
        mDynamicBPM = (TextView) findViewById(R.id.dynamic_bpm);
        mDynamicBPM.setText(getResources().getString(R.string.metronome_off));

        mMetronomeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                if(i > 0) {
                    mDynamicBPM.setTextColor(getResources().getColor(R.color.colorAccent));
                    mDynamicBPM.setText("" + i);
                } else {
                    mDynamicBPM.setText(getResources().getString(R.string.metronome_off));
                    mDynamicBPM.setTextColor(getResources().getColor(R.color.offBPM));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(progress > 0) {
                    mDynamicBPM.setTextColor(getResources().getColor(R.color.colorAccent));
                    mDynamicBPM.setText("" + progress);
                } else {
                    mDynamicBPM.setText(getResources().getString(R.string.metronome_off));
                    mDynamicBPM.setTextColor(getResources().getColor(R.color.offBPM));
                }
            }
        });
    }
}
