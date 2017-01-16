package com.example.shwavedefapp.musicreader;

import android.app.IntentService;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.graphics.pdf.PdfRenderer.Page;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;

import java.io.IOException;

public class PDFUtils {

    private double ratio;
    static final int landscapeWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    void renderAllPages(PDFActivity display, Uri uri) {
        try {
            ParcelFileDescriptor fileLocation = display.getContentResolver()
                    .openFileDescriptor(uri, "r");
            PdfRenderer renderer = new PdfRenderer(fileLocation);
            int numPages = renderer.getPageCount();
            for(int i = 0; i < numPages; i++) {
                Page page = renderer.openPage(i);
                ratio = ((double)page.getHeight()) / page.getWidth();
                Bitmap result = Bitmap.createBitmap(landscapeWidth, (int)(landscapeWidth*ratio),
                        Bitmap.Config.ARGB_4444);
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

    public class RenderService extends IntentService {

        public RenderService() {super("RenderService");}

        @Override
        protected void onHandleIntent(Intent intent) {

        }
    }
}
