package com.example.shwavedefapp.musicreader;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;

import java.io.IOException;

/**
 * Created by Shwave DeFapp on 1/8/2017.
 */

public class PDFUtils {

    protected PdfRenderer renderer;
    protected Bitmap[] result;
    private int numPages;

    public int getNumPages() {return this.numPages;}

    public void renderAllPages(ParcelFileDescriptor fileLocation) {
        try {
            renderer = new PdfRenderer(fileLocation);
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
