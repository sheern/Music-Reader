package com.example.shwavedefapp.musicreader;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    SeekBar mMetronomeBar;
    TextView mDynamicBPM;
    Button mAddMusic;
    TextView mNoMusicHint;
    LinearLayout mListSheets;

    EditText mSheetName;
    Button mDialogCreate;
    Button mDialogCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        metronome();
        mNoMusicHint = (TextView) findViewById(R.id.no_music_text);
        mListSheets = (LinearLayout) findViewById(R.id.list_sheets);
        mAddMusic = (Button) findViewById(R.id.add_music_button);
        mAddMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSetup();
            }
        });
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

    private void dialogSetup() {
        final AlertDialog addMusicDialog = new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.dialog_title))
                .setView(R.layout.addsheet_dialog).create();
        addMusicDialog.show();

        mSheetName = (EditText) addMusicDialog.findViewById(R.id.custom_file_name);
        mDialogCreate = (Button) addMusicDialog.findViewById(R.id.dialog_create);
        mDialogCancel = (Button) addMusicDialog.findViewById(R.id.dialog_cancel);

        mDialogCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogCreateNewSheet();
                addMusicDialog.cancel();
            }
        });
        mDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMusicDialog.cancel();
            }
        });
    }

    private void onDialogCreateNewSheet() {
        Button newSheet = new Button(getApplicationContext());
        newSheet.setText(mSheetName.getText().toString());
        newSheet.setBackgroundResource(R.color.colorPrimary);
        mListSheets.addView(newSheet);
        if(mListSheets.getChildCount() == 2) mNoMusicHint.setVisibility(View.GONE);
    }
}
