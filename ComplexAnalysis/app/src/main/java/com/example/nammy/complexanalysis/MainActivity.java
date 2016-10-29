package com.example.nammy.complexanalysis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void graph_button_clicked(View view) {
        Intent i = new Intent(this,PaintedActivity.class);
        startActivity(i);
    }
}
