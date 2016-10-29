package com.example.nammy.complexanalysis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class PaintedView extends View {

    int X_MIN = 0, X_MAX;
    int Y_MIN = 0, Y_MAX;
    final int PIXEL_SIZE = 5;
    Complex res_z;

    float function_max, function_min;
    Paint paint;
    public PaintedView(Context context, int screen_width, int screen_height) {
        super(context);

        this.paint = new Paint();
        Y_MAX = screen_height;
        X_MAX = screen_width;
        res_z = new Complex(0,0);
        // FIND MAX VALUE OF FUNCTION
        Complex zero = new Complex(0,0);
        Complex basis = f(zero);
        function_min = Complex.Mod(basis);
        function_max = function_min;
        for (int x = X_MIN; x < X_MAX; x+=PIXEL_SIZE) {
            for (int y = Y_MIN; y < Y_MAX; y+=PIXEL_SIZE) {
                float x_norm = (2.0f * (float) Math.PI) * ((float) x / (float) X_MAX);
                float y_norm = (2.0f * (float) Math.PI) * ((float) y / (float) Y_MAX);
                try {
                    float curr = Complex.Mod(f(new Complex(x_norm, y_norm)));
                    if (curr < function_min)
                        function_min = curr;
                    if (curr > function_max)
                        function_max = curr;
                } catch (Exception e) { continue; }
            }
        }
        /*
        String msg = "min: " + function_min + " max: " + function_max;
        Toast t = Toast.makeText(context,msg,Toast.LENGTH_LONG);
        t.show();
        */

    }
    Complex f(Complex z) {
        try {
            return z.conjugate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Function not defined at: " + z);
            return new Complex(0,0);
        }
    }

    Complex exp(Complex z) {
        Complex unit = new Complex(Math.cos(z.real),Math.sin(z.imaginary));
        return unit.multiply((float)Math.exp(z.real));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int x = X_MIN; x < X_MAX; x+=PIXEL_SIZE) {
            for (int y = Y_MIN; y < Y_MAX; y++) {
                // NORMALIZES VALUES FROM 0 TO 2PI
                float x_norm = (2.0f * (float) Math.PI) * ((float) x / (float) X_MAX);
                float y_norm = (2.0f * (float) Math.PI) * ((float) y / (float) Y_MAX);

                res_z.real = x_norm; res_z.imaginary = y_norm;
                try {
                    res_z = f(res_z);
                    float arg = Complex.Arg(res_z);
                    arg = arg * 180.0f / (float) Math.PI;
                    float mod = Complex.Mod(res_z);

                    // TODO: normalize the results: z = (x - x_min)/(x_max - x_min)
                    float norm = 0f;
                    try {
                        norm = (mod - function_min) / (function_max - function_min);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    //System.out.println("Min: " + function_min + " Max: " + function_max);
                    System.out.println("Arg: " + arg + " Mod: " + mod + " Norm: " + norm);
                    float[] hsv = {arg, 1.0f - norm, 1.0f}; // TODO: TEMP

                    paint.setColor(Color.HSVToColor(hsv));
                } catch (Exception e) {
                    e.printStackTrace();
                    float[] hsv = {0,1.0f,0.0f};
                    paint.setColor(Color.HSVToColor(hsv));
                }
                canvas.drawRect(x,y,x+PIXEL_SIZE,y+PIXEL_SIZE,paint);
            }
        }
        Toast t = Toast.makeText(getContext(),"Done",Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float x_norm = (2.0f * (float) Math.PI) * ((float) x / (float) X_MAX);
        float y_norm = (2.0f * (float) Math.PI) * ((float) y / (float) Y_MAX);

        return super.onTouchEvent(event);
    }
}
