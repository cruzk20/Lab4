package edu.up.cs371.epp.photofunpattern;

import android.graphics.Bitmap;
import android.graphics.Color;

public class WestEdgeFilter extends PhotoFilter {
    private final int ADJUSTMENT = 100;

    /*
     * tranformPixel This method overrides the transformPixel in the parent
     * class. It adds 100 to each RGB color component. The maxium value of each
     * component is limited to 255
     *
     * @param inPixel is a 32 bit pixel that contains RGB color values
     * @return a new Pixel in which each of the RGB components has been increased
     */
    public int transformPixel(int p0, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {



        /*
        int red = constrain(Color.red(inPixel) + ADJUSTMENT);
        int green = constrain(Color.green(inPixel) + ADJUSTMENT);
        int blue = constrain(Color.blue(inPixel) + ADJUSTMENT);
        int outPixel = Color.argb(Color.alpha(inPixel), red, green, blue);
        */
       int  outPixel = (p0+p1+(p2*-1)+p3+(p4*-2)+(p5*-1)+p6+p7+(p8*-1))/9;

        return outPixel;
    }


}
