package com.example.bogdan.proftime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ShowTask extends AppCompatActivity {

    static TaskFragment taskFragment;

    TextView title;

    EditText editTextData;
    EditText editTextTime;

    Button status;
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
    TextView textInfo;

    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        linearLayout = (LinearLayout) findViewById(R.id.cont);
        title = (TextView) findViewById(R.id.textTitle);
        title.setText(taskFragment.title.toCharArray(), 0, taskFragment.title.length());

        editTextData = (EditText) findViewById(R.id.edit_Data);
        editTextTime = (EditText) findViewById(R.id.edit_Time);
        if (taskFragment.status)
            status.setBackgroundColor(Color.BLUE);
        textInfo = (TextView) findViewById(R.id.textWithInfo);
        textInfo.setText(taskFragment.info);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutask, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save:
                int count = StaticValues.listTitle.indexOf(taskFragment.title);
                if (timeFinal > 0)
                    StaticValues.listTime.set(count, String.valueOf(timeFinal));
                startActivity(new Intent(getApplicationContext(), MainActivity.class));//.putExtra("newTask", taskFragment));
                Snackbar snackbar1 = Snackbar
                        .make(linearLayout, "Сохранено", Snackbar.LENGTH_LONG);
                snackbar1.show();
                return true;
            case R.id.star:
                count = StaticValues.listTitle.indexOf(taskFragment.title);
                if (!StaticValues.listTaskStatus.get(count)) {
                    StaticValues.listTaskStatus.set(count, true);
                } else {
                    StaticValues.listTaskStatus.set(count, false);
                }

                Snackbar snackbar = Snackbar
                        .make(linearLayout, "добавлено в избранное", Snackbar.LENGTH_LONG);
                snackbar.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int count = StaticValues.listTitle.indexOf(taskFragment.title);
        if (timeFinal > 0)
            StaticValues.listTime.set(count, String.valueOf(timeFinal));
        startActivity(new Intent(getApplicationContext(), MainActivity.class));//.putExtra("newTask", taskFragment));
        finish();
    }
}
