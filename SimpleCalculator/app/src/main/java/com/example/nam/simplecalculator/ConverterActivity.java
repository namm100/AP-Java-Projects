package com.example.nam.simplecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConverterActivity extends Activity{

    private TextView enteredTextView;
    private TextView base1TextView, base2TextView;
    private TextView num1TextView, num2TextView;
    private TextView convertedTextView;
    private TextView resultTextView;

    private String enteredString;
    private int base1, base2;
    private String num1, num2;

    private final int DIGIT_LIMIT = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_converter);
        init();
    }

    void init() {
        enteredTextView = (TextView) findViewById(R.id.entered_textViewConv);
        base1TextView = (TextView) findViewById(R.id.base_1_textView);
        base2TextView = (TextView) findViewById(R.id.base_2_textView);
        num1TextView = (TextView) findViewById(R.id.num1_textViewConv);
        num2TextView = (TextView) findViewById(R.id.num2_textViewConv);
        convertedTextView = (TextView) findViewById(R.id.converted_num1_textView);
        resultTextView = (TextView) findViewById(R.id.result_textViewConv);

        enteredString = "";
        base1 = 10; base2 = 10;
        num1 = ""; num2 = "";
    }

    public static String nToN(int fromRadix, int toRadix, String number) {
        // converts bases from two to 16
        if ((fromRadix > 16 || toRadix > 16) || (fromRadix < 2 || toRadix < 2)) {
            return "";
        }
        int a = 0;
        try {
            a = Integer.parseInt(number, fromRadix);
        } catch (NumberFormatException e) {
            return "Error";
        }
        return Integer.toString(a, toRadix);
    }

    private boolean isDecNum(String num) {
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6'
                    || c == '7' || c == '8' || c == '9') {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public void zero_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "0";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void one_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "1";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void two_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "2";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void three_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "3";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void four_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "4";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void five_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "5";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void six_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "6";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void seven_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "7";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void eight_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "8";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void nine_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "9";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void A_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "A";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void B_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "B";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void C_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "C";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void D_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "D";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void E_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "E";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }
    public void F_button_clickedConv(View view) {
        if (enteredString.length() > DIGIT_LIMIT) {
            return;
        }
        enteredString = enteredString + "F";
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }

    public void delete_button_clickedConv(View view) {
        if (enteredString.length() == 0) {
            return;
        }
        enteredString = enteredString.substring(0,enteredString.length() - 1);
        String enteredStringFinal = "Entered: " + enteredString;
        enteredTextView.setText(enteredStringFinal);
    }

    public static int nToDec(int fromRadix, String num) {
        String base10S = nToN(fromRadix, 10, num);
        try {
            return Integer.parseInt(base10S);
        } catch (NumberFormatException e){
            return 0;
        }
    }
    public void add_button_clickedConv(View view) {
        int num1Base10 = nToDec(base1, num1);
        int num2Base10 = nToDec(base2, num2);
        int res = num1Base10 + num2Base10;
        String resStr = Integer.toString(res, 10);
        if (resStr.length() > DIGIT_LIMIT) {
            String resStrFinal = "Res: " + "Overflow";
            resultTextView.setText(resStrFinal);
        } else {
            String resStrFinal = "Res: " + resStr;
            resultTextView.setText(resStrFinal);
        }
    }

    public void minus_button_clickedConv(View view) {
        int num1Base10 = nToDec(base1, num1);
        int num2Base10 = nToDec(base2, num2);
        int res = num1Base10 - num2Base10;
        String resStr = Integer.toString(res, 10);
        if (resStr.length() > DIGIT_LIMIT) {
            String resStrFinal = "Res: " + "Overflow";
            resultTextView.setText(resStrFinal);
        } else {
            String resStrFinal = "Res: " + resStr;
            resultTextView.setText(resStrFinal);
        }
    }

    public void multiply_button_clickedConv(View view) {
        int num1Base10 = nToDec(base1, num1);
        int num2Base10 = nToDec(base2, num2);
        int res = num1Base10 * num2Base10;
        String resStr = Integer.toString(res, 10);
        if (resStr.length() > DIGIT_LIMIT) {
            String resStrFinal = "Res: " + "Overflow";
            resultTextView.setText(resStrFinal);
        } else {
            String resStrFinal = "Res: " + resStr;
            resultTextView.setText(resStrFinal);
        }
    }

    public void other_button_clickedConv(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void set_base1_button_clicked(View view) {
        if (isDecNum(enteredString)) {
            int base;
            try {
                base = Integer.parseInt(enteredString, 10);
            } catch (NumberFormatException e) {
                base = 0;
            }
            if (base > 1 && base < 17) {
                // set base:1 string to the base
                base1 = base;
            }
        }
        String base1FinalString = "Base 1: " + base1;
        base1TextView.setText(base1FinalString);
        // delete the entered text
        enteredTextView.setText("Entered: ");
        enteredString = "";
    }
    public void set_base2_button_clicked(View view) {
        if (isDecNum(enteredString)) {
            int base;
            try {
                base = Integer.parseInt(enteredString, 10);
            } catch (NumberFormatException e) {
                base = 0;
            }
            if (base > 1 && base < 17) {
                base2 = base;
            }
        }
        String base2FinalString = "Base 2: " + base2;
        base2TextView.setText(base2FinalString);
        enteredTextView.setText("Entered: ");
        enteredString = "";
    }

    public void set_num1_button_clicked(View view) {
        if (enteredString == "") {
            num1 = "0";
        } else {
            num1 = enteredString;
        }
        String num1FinalString = "Num 1: " + num1;
        num1TextView.setText(num1FinalString);
        enteredTextView.setText("Entered: ");
        enteredString = "";
    }

    public void set_num2_button_clicked(View view) {
        if (enteredString == "") {
            num2 = "0";
        } else {
            num2 = enteredString;
        }
        String num2FinalString = "Num 2: " + num2;
        num2TextView.setText(num2FinalString);
        enteredTextView.setText("Entered: ");
        enteredString = "";
    }

    public void convert_button_clicked(View view) {
        String converted = nToN(base1, base2,num1);
        if (converted.length() >= DIGIT_LIMIT) {
            convertedTextView.setText("Overflow");
        } else {
            convertedTextView.setText(converted);
        }

    }

    public void clear_button_clickedConv(View view) {
        enteredString = "";
        base1 = 10; base2 = 10;
        num1 = ""; num2 = "";
        enteredTextView.setText("Entered: ");
        base1TextView.setText("Base 1: ");
        base2TextView.setText("Base 2: ");
        num1TextView.setText("Num 1: ");
        num2TextView.setText("Num 2: ");
        convertedTextView.setText("");
        resultTextView.setText("");
    }
}
