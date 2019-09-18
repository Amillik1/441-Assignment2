package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private LinearLayout canvasLayout = null;
    MySurface customSurfaceView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("SurfaceView");
        getSupportActionBar().hide();
        initControls();

        //fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        customSurfaceView = new MySurface(getApplicationContext());
        customSurfaceView.setOnTouchListener(this);
        canvasLayout.addView(customSurfaceView);
    }

    private void initControls(){
        if (canvasLayout == null){
            canvasLayout = (LinearLayout)findViewById(R.id.boardSpace);
        }
    }

    //implement scroll and other touch operations

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if(view instanceof SurfaceView){
            //customSurfaceView.drawTile(x,y);
            customSurfaceView.drawBoard(x,y);
            return true;
        }else{
            return false;
        }
    }
}
