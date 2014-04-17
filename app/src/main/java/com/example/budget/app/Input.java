package com.example.budget.app;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Input extends ActionBarActivity implements View.OnClickListener {


    private TextView display;
    private static final String DIGITS = "0123456789.";
    private Calculate calculate;
    private boolean operation = true;
    DecimalFormat df = new DecimalFormat("@###########");
    int maxNumbers = 8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_input);

        display = (TextView) findViewById(R.id.display);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        //numbers
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);

        //Operations
        findViewById(R.id.buttonC).setOnClickListener(this);
        findViewById(R.id.buttonTIP).setOnClickListener(this);
        findViewById(R.id.buttonPercent).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonTimes).setOnClickListener(this);
        findViewById(R.id.buttonMinus).setOnClickListener(this);
        findViewById(R.id.buttonPlus).setOnClickListener(this);
        findViewById(R.id.buttonDecimal).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        String buttonPressed = ((Button) v).getText().toString();

        //number was pressed
        if (DIGITS.contains(buttonPressed))
        {
            if (buttonPressed.equals(".") && display.getText().toString().contains("."))
            {
                //NO DOUBLE DECIMAL POINTS!
            }
            else
            {
                if(maxNumbers > 0)
                {
                    if(display.getText().equals("0"))
                    {
                        display.setText(buttonPressed);
                    }
                    else
                    {
                        operation = false;
                        display.append(buttonPressed);
                        maxNumbers --;
                    }
                }
            }
        }
        else
        {
            if(buttonPressed.equals("="))
            {
                //calculated
            }
            else if(buttonPressed.equals("C"))
            {
                display.setText("0");
            }
            else
            {
                if(!operation)
                {
                    appendColoredText(display, buttonPressed, getResources().getColor(R.color.blue_light));
                    operation = true;
                }
                else
                {
                    //change
                    String temp = String.valueOf(display.getText());
                    if (temp.length() > 0) {
                        temp = temp.substring(0, temp.length()-1);

                    }
                    display.setText(temp);
                    appendColoredText(display, buttonPressed, getResources().getColor(R.color.blue_light));
                }
            }
            maxNumbers = 8;
        }

    }

    public static void appendColoredText(TextView display, String text, int color) {
        int start = display.getText().length();
        display.append(text);
        int end = display.getText().length();

        Spannable spannableText = (Spannable) display.getText();
        spannableText.setSpan(new ForegroundColorSpan(color), start, end, 0);
    }
}
