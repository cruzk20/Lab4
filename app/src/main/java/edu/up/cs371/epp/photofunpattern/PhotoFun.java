package edu.up.cs371.epp.photofunpattern;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.Paint;
        import android.graphics.Color;
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

public class PhotoFun extends AppCompatActivity {

    // Image resources
    private Bitmap myOriginalBmp;
    private SurfaceView myNewSurfaceView;
    private Bitmap myNewBmp;

    /*
    * onCreate This constructor lays out the user interface, initializes the
    * original image and links buttons to their actions.
    *
    * @param savedInstanceState Required by parent object
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_fun);

        ImageView originalImageView =
                (ImageView) findViewById(R.id.originalImage);
        BitmapDrawable originalDrawableBmp =
                (BitmapDrawable) originalImageView.getDrawable();
        myOriginalBmp = originalDrawableBmp.getBitmap();

        myNewSurfaceView = (SurfaceView) findViewById(R.id.newSurface);
        SurfaceHolder holder = myNewSurfaceView.getHolder();
        holder.addCallback(new HolderCallback());
        myNewSurfaceView.setOnTouchListener(new imageTouchListener());
        //myNewBmp = myOriginalBmp;   // TBD something different
        myNewBmp = Bitmap.createBitmap(myOriginalBmp.getWidth(), myOriginalBmp.getHeight(),
                myOriginalBmp.getConfig());
        myNewBmp.eraseColor(Color.WHITE);

        Button grayFilterButton =
                (Button) findViewById(R.id.grayFilterButton);
        grayFilterButton.setOnClickListener(new grayFilterButtonListener());
        Button brightnessFilterButton =
                (Button) findViewById(R.id.brightnessFilterButton);
        brightnessFilterButton.setOnClickListener
                (new brightnessFilterButtonListener());
    }

    private void setSurfaceViewBitmap (Bitmap inBmp){
        myNewBmp = inBmp;
        SurfaceHolder holder = myNewSurfaceView.getHolder();
        Surface surface = holder.getSurface();
        Canvas canvas = surface.lockCanvas(null);
        canvas.drawBitmap(inBmp, 0, 0, null);
        surface.unlockCanvasAndPost(canvas);

    }

    /*
    * class grayFilterButtonListener this inner class defines the action for
    * the gray filter button.
    */
    private class grayFilterButtonListener implements View.OnClickListener {
        public void onClick(View button) {
            GrayFilter filter = new GrayFilter();
            setSurfaceViewBitmap (filter.apply(myOriginalBmp));
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
            setSurfaceViewBitmap(filter.apply(myOriginalBmp));
        }
    }

    private class HolderCallback implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Surface surface = holder.getSurface();
            Canvas canvas = surface.lockCanvas(null);
            canvas.drawBitmap(myNewBmp, 0, 0, null);
            surface.unlockCanvasAndPost(canvas);

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
    private class imageTouchListener implements View.OnTouchListener {
        private Paint myPaint;

        public imageTouchListener () {
            super();
            myPaint = new Paint();
            myPaint.setColor(Color.RED);
            myPaint.setStrokeWidth(20);
            myPaint.setStrokeCap(Paint.Cap.ROUND);
        }

        private void startLine (SurfaceView surfaceView, int x, int y) {
            //surfaceView.setWillNotDraw(false);
            SurfaceHolder holder = surfaceView.getHolder();
            Surface surface = holder.getSurface();
            Canvas canvas = surface.lockCanvas(null);
            canvas.drawBitmap(myNewBmp, 0, 0, null);
            canvas.drawLine(x-30, y-30, x+30, y+30, myPaint);
            surface.unlockCanvasAndPost(canvas);
        }

        public boolean onTouch(View view, MotionEvent event){
            SurfaceView surfaceView = (SurfaceView)view;
            int x = (int)event.getX();
            int y = (int)event.getY();
            int eventAction = event.getAction();

            switch (eventAction) {
                case MotionEvent.ACTION_DOWN: {
                    startLine (surfaceView, x, y);
                    Log.i("OnTouch", "down at x:" + x + ", y:" + y);
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    Log.i("OnTouch", "move to x:" + x + ", y:" + y);
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    Log.i("OnTouch", "  up at x:" + x + ", y:" + y);
                    break;
                }
            }
           return true;
        }
    }
}

