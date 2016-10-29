package com.example.nammy.complexanalysis;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;


public class PaintedActivity extends Activity {

    PaintedView paintedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        paintedView = new PaintedView(this,size.x,size.y);
        setContentView(paintedView);

    }
}
