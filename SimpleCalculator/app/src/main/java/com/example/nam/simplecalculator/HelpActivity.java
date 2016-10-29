package com.example.nam.simplecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HelpActivity extends Activity {

    boolean deleteChecBoxBool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        deleteChecBoxBool = true;
    }

    public void back_button_pressed(View view) {
        // go back to calculator with boolean value there.
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("delete_bool", deleteChecBoxBool);
        startActivity(i);
    }

    public void toConverterButtonClicked(View view) {
        // change to converter view
        Intent i = new Intent(this, ConverterActivity.class);
        startActivity(i);

    }

    public void delete_checkBox_clicked(View view) {
        Toast t = Toast.makeText(this,"changed settings",Toast.LENGTH_SHORT);
        t.show();
        deleteChecBoxBool = !deleteChecBoxBool;
    }
}
