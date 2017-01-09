package com.example.shwavedefapp.musicreader;

import android.content.Context;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Shwave DeFapp on 1/9/2017.
 */

public class Metronome {

    public void create(final Context c, SeekBar bar, final TextView bpm) {
        bpm.setText(c.getString(R.string.metronome_off));

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                if(i > 0) {
                    bpm.setTextColor(c.getResources().getColor(R.color.colorAccent));
                    bpm.setText("" + i);
                } else {
                    bpm.setText(c.getResources().getString(R.string.metronome_off));
                    bpm.setTextColor(c.getResources().getColor(R.color.offBPM));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(progress > 0) {
                    bpm.setTextColor(c.getResources().getColor(R.color.colorAccent));
                    bpm.setText("" + progress);
                } else {
                    bpm.setText(c.getResources().getString(R.string.metronome_off));
                    bpm.setTextColor(c.getResources().getColor(R.color.offBPM));
                }
            }
        });
    }

}
