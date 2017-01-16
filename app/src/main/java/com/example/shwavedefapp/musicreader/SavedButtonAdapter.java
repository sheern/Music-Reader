package com.example.shwavedefapp.musicreader;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

public class SavedButtonAdapter {

    static Button getButton(Context c, String text) {
        Button button = new Button(c);
        button.setText(text);
        button.setTextColor(ContextCompat.getColor(c, R.color.buttonText));
        button.setTransformationMethod(null);
        button.setBackground(ContextCompat.getDrawable(c, R.drawable.minimal_btn));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return button;
    }

}
