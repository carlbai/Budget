package com.example.budget.app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class Category extends ActionBarActivity {


    private TextView temp;
    private ListView listView;
    private Spinner spinner1;
    private BigDecimal amount;
    private static final String TAG = "Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        temp = (TextView) findViewById(R.id.text);
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        List<String> values = new ArrayList<String>();

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            String value = extras.getString("amount");
            amount = new BigDecimal(value);


            NumberFormat usdCostFormat = NumberFormat.getCurrencyInstance(Locale.US);
            usdCostFormat.setMaximumFractionDigits( 2 );
            temp.setText( usdCostFormat.format(amount.doubleValue()) );

        }


        String[] valuesArr = new String[values.size()];
        valuesArr = values.toArray(valuesArr);

        findViewById(R.id.button).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = String.valueOf(spinner1.getSelectedItem());

                Transaction temp = new Transaction(category, amount);

                Log.v(TAG, String.valueOf(temp.getAmount()));
                Log.v(TAG, temp.getCategory());



                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("Transaction", temp);
                startActivity(i);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.category, menu);
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

}
