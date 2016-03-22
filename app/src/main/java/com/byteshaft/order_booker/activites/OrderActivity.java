package com.byteshaft.order_booker.activites;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.byteshaft.order_booker.R;
import com.byteshaft.order_booker.utils.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText orderThingName;
    private EditText fromWhere;
    private Button orderTimeDate;
    private Helpers mHelpers;
    private int year;
    private int month;
    private int day;
    private Calendar calendar;
    private boolean dateSelected = false;
    private String mDateTime;
    private Button mNowbutton;
    private String deliveryTime = null;
    private boolean dateFromDatePicker = false;
    private String mTime;
    private int mHours;
    private int mMinutes;
    private boolean mTimeSet = false;
    private boolean selectedNow = false;
    private TextView orderTextView;
    private TextView fromWhereTextView;
    private TextView timeDateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        Typeface typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/BradBunR.ttf");
        mHelpers = new Helpers();
        orderThingName = (EditText) findViewById(R.id.order_et);
        fromWhere = (EditText) findViewById(R.id.from_where_et);
        orderTimeDate = (Button) findViewById(R.id.time_date_button);
        orderTimeDate.setTypeface(typeFace);
        mNowbutton = (Button) findViewById(R.id.nowButton);
        orderTextView = (TextView) findViewById(R.id.order_tv);
        fromWhereTextView = (TextView) findViewById(R.id.from_where_tv);
        timeDateTextView = (TextView) findViewById(R.id.time_date_tv);
        timeDateTextView.setTypeface(typeFace);
        fromWhereTextView.setTypeface(typeFace);
        orderTextView.setTypeface(typeFace);
        mNowbutton.setTypeface(typeFace);
        mNowbutton.setOnClickListener(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Fill in the details");
        String message = "Select Time and Date";
        orderTimeDate.setText(message);
        orderTimeDate.setOnClickListener(this);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mHours = calendar.get(Calendar.HOUR_OF_DAY);
        mMinutes = calendar.get(Calendar.MINUTE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent upIntent = new Intent(this, MainActivity.class);
        switch (item.getItemId()) {
            case R.id.action_done:
                String orderProduct = orderThingName.getText().toString();
                String from = fromWhere.getText().toString();
                if (dateSelected && dateFromDatePicker && mTimeSet) {
                    deliveryTime = orderTimeDate.getText().toString();
                }
                if (orderProduct.isEmpty() || from.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must fill all the fields",
                            Toast.LENGTH_SHORT).show();
                    return false;
                } else if (!dateSelected) {
                    Toast.makeText(getApplicationContext(), "Please select time and date",
                            Toast.LENGTH_SHORT).show();
                    return false;
                } else if (dateSelected || selectedNow) {
                    System.out.println(deliveryTime);
                    String[] array = new String[] {orderProduct, from, deliveryTime};
                    new SendDataTask(OrderActivity.this).execute(array);
                    return true;
                }

            case android.R.id.home:
                NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        if (id == 21) {
            dialog =  new DatePickerDialog(this, myDateListener,year, month, day);
        } else if (id == 12) {
            dialog =  new TimePickerDialog(this, timeListener, mHours, mMinutes, false);
        }
        return dialog;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            String time = mTime;
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("h:mm aa");
            String out = null;
            try {
                Date date = dateFormat.parse(time);

                out = dateFormat2.format(date);
                Log.e("Time", out);
            } catch (ParseException e) {
            }
            mDateTime = out+" " +arg3 + "-" + (arg2+1) +"-"+ arg1;
            System.out.println(mDateTime);
            orderTimeDate.setText(mDateTime);
            dateSelected = true;
            dateFromDatePicker = true;
        }
    };

    private TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mTime = hourOfDay + ":" + minute;
            showDialog(21);
            mTimeSet = true;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_date_button:
                deliveryTime = null;
                selectedNow = false;
                mTime = null;
                if (mTimeSet) {
                    mTimeSet = false;
                    dateSelected = false;
                }
                if (mTimeSet) {
                    showDialog(21);
                } else {
                    showDialog(12);
                }
                break;
            case R.id.nowButton:
                orderTimeDate.setText("select date");
                mTime = null;
                deliveryTime = null;
                deliveryTime = Helpers.getTimeStamp();
                selectedNow = true;
                dateSelected = true;
                mTimeSet = false;
                dateFromDatePicker = false;
                Toast.makeText(OrderActivity.this, "Time & Date Selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
