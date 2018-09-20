package edu.up.cs371.epp.photofunpattern;

import android.graphics.Color;
/**

 *  class GrayFilter changes the image manipulation behavior of its parent
 *  PhotoFilter to convert the image to gray scale.
 *
 *  @author Edward C. Epp
 *  @version November 2017
 *  https://github.com/edcepp/PhotoFunPattern
 */

public class GrayFilter extends PhotoFilter {

    /*
    * tranformPixel This method overrides the transformPixel in the parent
    * class. It transforms a color pixel to gray by averaging its three RGB
    * components.
    *
    * @param inPixel is a 32 bit pixel that contains RGB color values
    * @return a new Pixel in which each of the RGB components is their averaged
    * value
    */
    public int transformPixel(int p0, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {

        int red0 = constrain(Color.red(p0));
        int green0 = constrain(Color.green(p0));
        int blue0 = constrain(Color.blue(p0));

        int red1 = constrain(Color.red(p1));
        int green1 = constrain(Color.green(p1));
        int blue1 = constrain(Color.blue(p1));

        int red2 = constrain(Color.red(p2));
        int green2 = constrain(Color.green(p2));
        int blue2 = constrain(Color.blue(p2));

        int red3 = constrain(Color.red(p3));
        int green3 = constrain(Color.green(p3));
        int blue3 = constrain(Color.blue(p3));

        int red4 = constrain(Color.red(p4));
        int green4 = constrain(Color.green(p4));
        int blue4 = constrain(Color.blue(p4));

        int red5 = constrain(Color.red(p5));
        int green5 = constrain(Color.green(p5));
        int blue5 = constrain(Color.blue(p5));

        int red6 = constrain(Color.red(p6));
        int green6 = constrain(Color.green(p6));
        int blue6 = constrain(Color.blue(p6));

        int red7 = constrain(Color.red(p7));
        int green7 = constrain(Color.green(p7));
        int blue7 = constrain(Color.blue(p7));

        int red8 = constrain(Color.red(p8));
        int green8 = constrain(Color.green(p8));
        int blue8 = constrain(Color.blue(p8));

        int red = (red0 + red1 + red2 + red3 +red4 + red5 + red6 +red7 +red8) /9;
        int green = (green0 + green1 + green2 + green3 + green4 +green5 + green6 +green7 + green8)/9;
        int blue = (blue0 + blue1 + blue2 + blue3 +blue4 +blue5 +blue6 +blue7 +blue8)/9;

        p4 = Color.argb(Color.alpha(p4), red, green, blue);
        return p4;
    }

}
