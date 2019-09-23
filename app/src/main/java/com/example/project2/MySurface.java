package com.example.project2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import java.lang.Math;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder = null;
    private Paint paint = null;
    enum selected{yes, no}

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
        paint.setStrokeWidth(10000);
    }

    public void drawBoard(float x, float y){
        float centerX = 600;
        float centerY = 800;
        surfaceHolder = getHolder();

        selected[] tileState = {selected.no, selected.no,selected.no, selected.no, selected.no, selected.no};
        float[][] tileLoc = {{centerX, centerY+173},
                {centerX+150, centerY+87},
                {centerX+150, centerY-87},
                {centerX, centerY-173},
                {centerX-150, centerY-87},
                {centerX-150, centerY+87}};
        //select a tile to change color if touched
        int selectedTile = -1;
        double smallDist = 100;
        for (int i =0; i < tileLoc.length; i++){
            double distToCenter = Math.sqrt( (Math.abs(tileLoc[i][0]-x)*Math.abs(tileLoc[i][0]-x) )+ (Math.abs(tileLoc[i][1]-y)*Math.abs(tileLoc[i][1]-y)) );
            if (smallDist > distToCenter){
                smallDist = distToCenter;
                selectedTile = i;
            }
        }
        if (selectedTile != -1){
            tileState[selectedTile] = selected.yes;
        }

        Canvas canvas = surfaceHolder.lockCanvas();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        //reset the background
        canvas.drawColor(Color.WHITE);

        //Drawing the tiles
        drawTile(centerX, centerY+173, canvas, tileState[0]);
        drawTile(centerX+150, centerY+87, canvas, tileState[1]);
        drawTile(centerX+150, centerY-87, canvas, tileState[2]);
        drawTile(centerX, centerY-173, canvas, tileState[3]);
        drawTile(centerX-150, centerY-87, canvas, tileState[4]);
        drawTile(centerX-150, centerY+87, canvas, tileState[5]);


        surfaceHolder.unlockCanvasAndPost(canvas);

    }

    public void drawTile(float posX, float posY, Canvas canvas, selected state){
        float[] vert = {posX, posY, posX+50, posY-87, posX-50, posY-87, posX-100, posY, posX-50, posY+87, posX+50, posY+87,posX+100, posY, posX+50, posY-87};
        int[] colorsSelected = {Color.TRANSPARENT, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW};
        int[] colorsNormal = {Color.TRANSPARENT, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY};
        //canvas mode is triangle_fan, hex needs 7 points
        if (state == selected.no){
            canvas.drawVertices(Canvas.VertexMode.TRIANGLE_FAN, vert.length, vert, 0, null, 0, colorsNormal, 0, null, 0, 0, paint);
        }
        if (state == selected.yes) {
            canvas.drawVertices(Canvas.VertexMode.TRIANGLE_FAN, vert.length, vert, 0, null, 0, colorsSelected, 0, null, 0, 0, paint);
        }
    }
}
