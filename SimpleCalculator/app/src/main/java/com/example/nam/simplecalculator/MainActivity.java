package com.example.nam.simplecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String entered_string;
    private double value1, value2;

    boolean deleteBool = true;

    private TextView enteredTV, val1TV, val2TV, resultTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        // recieve the intent
    }

    public void init() {
        enteredTV = (TextView) findViewById(R.id.entered_textView);
        val1TV = (TextView) findViewById(R.id.val1_textView);
        val2TV = (TextView) findViewById(R.id.val2_textView);
        resultTV = (TextView) findViewById(R.id.result_2_textView);

        entered_string = "";
        value1 = 0;
        value2 = 0;

    }

    public String add(double a_int, double b_int) {
        String s = Double.toString(a_int + b_int);
        try {
            return s.substring(0,18);
        } catch (IndexOutOfBoundsException e) {
            return s;
        }
    }

    public String subtract(double a_int, double b_int) {
        String s = Double.toString(a_int - b_int);
        try {
            return s.substring(0,18);
        } catch (IndexOutOfBoundsException e) {
            return s;
        }
    }

    public String multiply(double a_int, double b_int) {
        String s = Double.toString(a_int * b_int);
        try {
            return s.substring(0,18);
        } catch (IndexOutOfBoundsException e) {
            return s;
        }
    }

    public String divide(double a_int, double b_int) {
        if (b_int == 0) {
            return "";
        }
        String s =  Double.toString(a_int / b_int);
        try {
            return s.substring(0,18);
        } catch (IndexOutOfBoundsException e) {
            return s;
        }
    }

    public void one_button_clicked(View view) {
        if (entered_string.length() > 18) {
            return;
        }
        entered_string = entered_string + "1";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void two_button_clicked(View view) {
        if (entered_string.length() > 18) {
            return;
        }
        entered_string = entered_string + "2";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void three_button_clicked(View view) {
        if (entered_string.length() > 18) {
            return;
        }
        entered_string = entered_string + "3";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void four_button_clicked(View view) {
        if (entered_string.length() > 18) {
            return;
        }
        entered_string = entered_string + "4";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void five_button_clicked(View view) {
        if (entered_string.length() > 18) {
            return;
        }
        entered_string = entered_string + "5";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void six_button_clicked(View view) {
        if (entered_string.length() > 18) {
            return;
        }
        entered_string = entered_string + "6";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void seven_button_clicked(View view) {
        if (entered_string.length() > 18) {
            return;
        }
        entered_string = entered_string + "7";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void eight_button_clicked(View view) {
        if (entered_string.length() > 18) {
            return;
        }
        entered_string = entered_string + "8";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void nine_button_clicked(View view) {
        if (entered_string.length() > 18) {
            return;
        }
        entered_string = entered_string + "9";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void zero_button_clicked(View view) {
        if (entered_string.length() > 18) {
            return;
        }
        entered_string = entered_string + "0";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void delete_button_clicked(View view) {
        if (entered_string.length() == 0)
            return;
        entered_string = entered_string.substring(0,entered_string.length() - 1);
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
    }

    public void set1_button_clicked(View view) {
        double entered;
        try {
            entered = Double.parseDouble(entered_string);
        } catch (NumberFormatException e) {
            entered = 0;
        }
        entered_string = "";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
        value1 = entered;
        String val1String = Double.toString(value1);
        val1TV.setText(val1String);
    }

    public void set2_button_clicked(View view) {
        double entered;
        try {
            entered = Double.parseDouble(entered_string);
        } catch (NumberFormatException e) {
            entered = 0;
        }
        entered_string = "";
        String entered_string_final = "Entered: " + entered_string;
        enteredTV.setText(entered_string_final);
        value2 = entered;
        String val2String = Double.toString(value2);
        val2TV.setText(val2String);
    }

    public void add_button_clicked(View view) {
        String res_str = "Result: " + add(value1, value2) ;
        resultTV.setText(res_str);

        //set val1,val2, entered to zero.

        entered_string = "";
        value1 = 0;
        value2 = 0;
        enteredTV.setText("Entered: ");
        val1TV.setText("");
        val2TV.setText("");

    }

    public void subtract_button_clicked(View view) {
        String res_str = "Result: " + subtract(value1, value2) ;
        resultTV.setText(res_str);

        //set val1,val2, entered to zero.
        entered_string = "";
        value1 = 0;
        value2 = 0;
        enteredTV.setText("Entered: ");
        val1TV.setText("");
        val2TV.setText("");
    }

    public void multiply_button_clicked(View view) {
        String res_str = "Result: " + multiply(value1, value2) ;
        resultTV.setText(res_str);

        //set val1,val2, entered to zero.
        entered_string = "";
        value1 = 0;
        value2 = 0;
        enteredTV.setText("Entered: ");
        val1TV.setText("");
        val2TV.setText("");
    }

    public void divide_button_clicked(View view) {
        String res_str = "Result: " + divide(value1, value2) ;
        resultTV.setText(res_str);

        //set val1,val2, entered to zero.
        entered_string = "";
        value1 = 0;
        value2 = 0;
        enteredTV.setText("Entered: ");
        val1TV.setText("");
        val2TV.setText("");
    }

    public void help_button_clicked(View view) {
        // change view to help view
        Intent i = new Intent(this, ConverterActivity.class);
        startActivity(i);
    }
}
