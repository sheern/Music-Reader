package com.example.shwavedefapp.musicreader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.graphics.pdf.PdfRenderer.Page;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;

import java.io.IOException;

public class PDFUtils {

    void renderAllPages(PDFActivity display, Uri uri) {
        try {
            ParcelFileDescriptor fileLocation = display.getContentResolver()
                    .openFileDescriptor(uri, "r");
            PdfRenderer renderer = new PdfRenderer(fileLocation);
            int numPages = renderer.getPageCount();
            for(int i = 0; i < numPages; i++) {
                Bitmap result = Bitmap.createBitmap(Resources.getSystem().getDisplayMetrics().widthPixels,
                        Resources.getSystem().getDisplayMetrics().heightPixels,
                        Bitmap.Config.ARGB_4444);
                Page page = renderer.openPage(i);
                page.render(result, null, null, Page.RENDER_MODE_FOR_DISPLAY);
                ImageView newPage = new ImageView(display);
                newPage.setImageBitmap(result);
                display.container.addView(newPage);
                page.close();
            }
            renderer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
