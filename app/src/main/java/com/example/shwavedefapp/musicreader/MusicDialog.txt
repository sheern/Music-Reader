package com.example.shwavedefapp.musicreader;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

class MusicDialog extends AlertDialog {

    private MainActivity m;
    private static final LayoutParams margins = new LayoutParams
            (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    MusicDialog(MainActivity call) {
        super(call);
        m = call;
    }

    public static final int READ_REQUEST_CODE = 1;
    public static final String EXTRA_URI = "com.example.shwavedefapp.musicreader.FILEURI";

    void dialogSetup(final LinearLayout l, final TextView t) {
        final AlertDialog dialog = new AlertDialog.Builder(m)
                .setTitle(m.getString(R.string.dialog_title))
                .setView(R.layout.addsheet_dialog)
                .setPositiveButton(m.getString(R.string.dialog_create),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onDialogCreateNewSheet(l, t);
                            }
                        })
                .setNegativeButton(m.getString(R.string.dialog_cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        }).create();
        dialog.show();

        Button browseFile = (Button) dialog.findViewById(R.id.dialog_browse);
        browseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDialogBrowse();
            }
        });
    }

    // TODO (1) -- CREATE SEPARATE CLASS FOR SPECIAL BUTTON
    private void onDialogCreateNewSheet(LinearLayout l, TextView t) {
        if(m.fileUri == null) {
            Toast.makeText(m, "No file selected", Toast.LENGTH_SHORT).show();
        } else {
            Intent newPdf = new Intent(m, PDFActivity.class);
            newPdf.putExtra(EXTRA_URI, m.fileUri);
            m.startActivity(newPdf);
            Button newSheet = new Button(m);
            newSheet.setBackgroundColor(m.getResources().getColor(R.color.colorPrimary));
            margins.setMargins(0, 0, 0, 16);
            newSheet.setLayoutParams(margins);
            l.addView(newSheet);
            if (l.getChildCount() == 2) t.setVisibility(View.GONE);
        }
    }

    private void onDialogBrowse() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        m.startActivityForResult(intent, READ_REQUEST_CODE);
    }
}
