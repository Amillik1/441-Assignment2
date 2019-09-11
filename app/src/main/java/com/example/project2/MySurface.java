package com.example.project2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder = null;
    private Paint paint = null;

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        paint = null;

    }

    public MySurface(Context context){
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    public void drawTile(){
        surfaceHolder = getHolder();

        Canvas canvas = surfaceHolder.lockCanvas();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        float[] verts = {1, 1, 5, 5, 7, 7};
        //canvas mode is triangle_fan, hex needs 7 points

    }
}