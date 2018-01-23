package edu.up.cs371.epp.photofunpattern;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
        import android.widget.ImageView;
        import android.view.SurfaceView;
        import android.graphics.Canvas;
        import android.view.SurfaceHolder;
        import android.view.Surface;
        import android.widget.Button;
        import android.view.MotionEvent;
        import android.view.View;
        import android.util.Log;

/**
 *  class PhotoFun controls this photo manipulation app.
 *
 *  @author Edward C. Epp
 *  @version November 2017
 *   https://github.com/edcepp/PhotoFunPattern
 */

public class PhotoFun extends AppCompatActivity implements SurfaceHolder.Callback {

    // Image resources
    private Bitmap myOriginalBmp;
    private ImageView myNewImageView;
    private SurfaceView myNewSurfaceView;
    private Surface mySurface;
    //private Canvas myCanvas;

    /*
    * onCreate This constructor lays out the user interface, initializes the
    * original image and links buttons to their actions.
    *
    * @param savedInstanceState Required by parent object
    */

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        mySurface = holder.getSurface();
        Canvas canvas = mySurface.lockCanvas(null);
        canvas.drawBitmap(myOriginalBmp,0, 0, null);
        mySurface.unlockCanvasAndPost(canvas);

    }

    @Override
    public void surfaceChanged (SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed (SurfaceHolder holder){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_fun);

        ImageView originalImageView =
                (ImageView) findViewById(R.id.originalImage);
        BitmapDrawable originalDrawableBmp =
                (BitmapDrawable) originalImageView.getDrawable();
        myOriginalBmp = originalDrawableBmp.getBitmap();

        myNewImageView = (ImageView) findViewById(R.id.newImage);

        myNewSurfaceView = (SurfaceView) findViewById(R.id.newSurface);
        SurfaceHolder holder = myNewSurfaceView.getHolder();
        holder.addCallback(this);
        myNewSurfaceView.setOnTouchListener(new imageTouchListener());

        Button grayFilterButton =
                (Button) findViewById(R.id.grayFilterButton);
        grayFilterButton.setOnClickListener(new grayFilterButtonListener());
        Button brightnessFilterButton =
                (Button) findViewById(R.id.brightnessFilterButton);
        brightnessFilterButton.setOnClickListener
                (new brightnessFilterButtonListener());
        Log.i("OnCreate", "done");
    }

    /*
    * class grayFilterButtonListener this inner class defines the action for
    * the gray filter button.
    */
    private class grayFilterButtonListener implements View.OnClickListener {
        public void onClick(View button) {
            GrayFilter filter = new GrayFilter();
            myNewImageView.setImageBitmap(filter.apply(myOriginalBmp));
        }
    }

    /*
    * class grayFilterButtonListener this inner class defines the action for the
    * brightness filter
    * button.
    */
    private class brightnessFilterButtonListener
            implements View.OnClickListener {
        public void onClick(View button) {
            BrightnessFilter filter = new BrightnessFilter();
            myNewImageView.setImageBitmap(filter.apply(myOriginalBmp));
        }
    }

    private class imageTouchListener implements View.OnTouchListener {
        public boolean onTouch(View canvas, MotionEvent event){
            int x = (int)event.getRawX();
            int y = (int)event.getRawY();
            Log.i("OnTouch", "x:"+x+", y:"+y);
           return true;
        }
    }
}

