package com.example.bogdan.proftime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class ShowTask extends AppCompatActivity {

    EditText editTextData;
    EditText editTextTime;

    Button delete;
    Button buttonStart;

    int DIALOG_TIME_DATA = 1;
    int DIALOG_TIME_CLOCK = 2;
    int myHour = 14;
    int myMinute = 35;
    int myYear = 2015;
    int myMonth = 11;
    int myDay = 13;
    Calendar c;
    long timeFinal = 0;
    long bufTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        editTextData = (EditText) findViewById(R.id.edit_Data);
        editTextTime = (EditText) findViewById(R.id.edit_Time);
        delete = (Button) findViewById(R.id.delete);
        buttonStart = (Button) findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("time", timeFinal));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void onclickTime(View view) {
        showDialog(DIALOG_TIME_CLOCK);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME_CLOCK) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }

        if (id == DIALOG_TIME_DATA) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBackData, myYear, myMonth, myDay);
            return tpd;
        }

        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {



            myHour = hourOfDay;
            myMinute = minute;
            Log.d("Teg", "Time is " + myHour + " hours " + myMinute + " minutes "
                    + (((myHour * 60 * 60 * 1000) + (myMinute * 60 * 1000)) + System.currentTimeMillis()));

            editTextTime.setText(myHour + "." + myMinute);

            c = Calendar.getInstance();
            c.set(myYear, myMonth, myDay, myHour, myMinute);
            bufTime = c.getTimeInMillis();
            timeFinal = bufTime - System.currentTimeMillis();
        }
    };


    public void onclickData(View view) {
        showDialog(DIALOG_TIME_DATA);
    }

    DatePickerDialog.OnDateSetListener myCallBackData = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String tempMoth = null;

            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;

            if(myMonth < 9){
                int tempMothInteger = myMonth + 1;
                tempMoth = "0" + tempMothInteger;
                editTextData.setText(myDay + "." + tempMoth + "." + myYear);
            } else {
                int tempMothInteger = myMonth + 1;
                editTextData.setText(myDay + "." + tempMothInteger + "." + myYear);
            }




            c = Calendar.getInstance();
            c.set(myYear, myMonth, myDay, myHour, myMinute);
            long d = c.getTimeInMillis();
            timeFinal = d - System.currentTimeMillis();

        }
    };
}