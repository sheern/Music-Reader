package com.example.shwavedefapp.musicreader;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.File;
import java.io.IOException;

public class PDFUtils {

    protected Bitmap[] result;
    private int numPages;

    int getNumPages() {return this.numPages;}

    void renderAllPages(Uri uri) {
        try {
            ParcelFileDescriptor fileLocation = ParcelFileDescriptor
                    .open(new File(uri.getPath()), ParcelFileDescriptor.MODE_READ_ONLY);
            PdfRenderer renderer = new PdfRenderer(fileLocation);
            numPages = renderer.getPageCount();
            result = new Bitmap[numPages];
            for(int i = 0; i < numPages; i++) {
                renderer.openPage(i)
                        .render(result[i], null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            }
            renderer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
