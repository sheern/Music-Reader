package com.example.shwavedefapp.musicreader;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MusicDialog extends Activity {

    private static final int READ_REQUEST_CODE = 1;
    Uri fileUri;

    public void dialogSetup(final Activity m, final ViewGroup l, final TextView t) {
        AlertDialog.Builder builder = new AlertDialog.Builder(m)
                .setTitle(m.getResources().getString(R.string.dialog_title))
                .setView(R.layout.addsheet_dialog)
                .setPositiveButton(m.getString(R.string.dialog_create),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onDialogCreateNewSheet(m, l, t);
                            }
                        })
                .setNeutralButton(m.getString(R.string.dialog_browse),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onDialogBrowse(m);
                            }
                        })
                .setNegativeButton(m.getString(R.string.dialog_cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void onDialogCreateNewSheet(Activity m, ViewGroup l, TextView t) {
        Intent newPdf = new Intent(m, PDFActivity.class);
        m.startActivity(newPdf);

        Button newSheet = new Button(m);
        l.addView(newSheet);
        if(l.getChildCount() == 2) t.setVisibility(View.GONE);
    }

    private void onDialogBrowse(Activity m) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        m.startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                fileUri = data.getData();
            }
        }
    }
}
