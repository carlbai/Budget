package com.example.budget.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class LockScreen extends Activity implements View.OnClickListener
{
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;

    TextView first;
    TextView second;
    TextView third;
    TextView fourth;

    protected String password = "7437";
    protected String guess = "";
    protected int last = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lock_screen);


        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_lock_screen);



        first = (TextView) findViewById(R.id.first);
        second = (TextView) findViewById(R.id.second);
        third = (TextView) findViewById(R.id.third);
        fourth = (TextView) findViewById(R.id.fourth);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        
        button0.setText(Html.fromHtml("<b>" + "<big>" + "0" + "</big>" + "</b>" + "<br/>" + "<small> </small>"));
        button1.setText(Html.fromHtml("<b>" + "<big>" + "1" + "</big>" + "</b>" + "<br/>" + "<small> </small>"));
        button2.setText(Html.fromHtml("<b>" + "<big>" + "2" + "</big>" + "</b>" + "<br/>" + "<small> ABC</small>"));
        button3.setText(Html.fromHtml("<b>" + "<big>" + "3" + "</big>" + "</b>" + "<br/>" + "<small> DEF</small>"));
        button4.setText(Html.fromHtml("<b>" + "<big>" + "4" + "</big>" + "</b>" + "<br/>" + "<small> GHI</small>"));
        button5.setText(Html.fromHtml("<b>" + "<big>" + "5" + "</big>" + "</b>" + "<br/>" + "<small> JKL</small>"));
        button6.setText(Html.fromHtml("<b>" + "<big>" + "6" + "</big>" + "</b>" + "<br/>" + "<small> MNO</small>"));
        button7.setText(Html.fromHtml("<b>" + "<big>" + "7" + "</big>" + "</b>" + "<br/>" + "<small> PQRS</small>"));
        button8.setText(Html.fromHtml("<b>" + "<big>" + "8" + "</big>" + "</b>" + "<br/>" + "<small> TUV</small>"));
        button9.setText(Html.fromHtml("<b>" + "<big>" + "9" + "</big>" + "</b>" + "<br/>" + "<small> WXYZ</small>"));


        button0.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check("0");
            }
        });

        button1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check("1");
            }
        });
        button2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check("2");
            }
        });

        button3.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check("3");
            }
        });

        button4.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check("4");
            }
        });

        button5.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check("5");
            }
        });

        button6.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check("6");
            }
        });

        button7.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check("7");
            }
        });
        button8.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check("8");
            }
        });

        button9.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check("9");
            }
        });

    }

    public void check(String s)
    {
        guess = guess + s;
        if(guess.equalsIgnoreCase(password))
        {
            Toast.makeText(getApplicationContext(), "correct", Toast.LENGTH_SHORT).show();
            Intent temp = new Intent(this, MainActivity.class);
            startActivity(temp);
            finish();
        }
        else if(guess.length() == 4)
        {
            guess = "";

            first.setTextColor(getResources().getColor(R.color.black));
            second.setTextColor(getResources().getColor(R.color.black));
            third.setTextColor(getResources().getColor(R.color.black));
            fourth.setTextColor(getResources().getColor(R.color.black));
            last --;

            if(last <= 0)
            {

                Toast.makeText(getApplicationContext(), "KABOOOOOOM", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "incorrect", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            int length = guess.length();
            if(length == 1)
            {
                first.setTextColor(getResources().getColor(R.color.blue_light));
            }
            else if(length == 2)
            {  second.setTextColor(getResources().getColor(R.color.blue_light));
            }
            else
            {
                third.setTextColor(getResources().getColor(R.color.blue_light));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lock_screen, menu);
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
    public void onClick(View view)
    {

    }
}
