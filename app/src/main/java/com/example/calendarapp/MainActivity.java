package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    private CalendarView calView;
    private Button datePick;
    private DatePickerDialog.OnDateSetListener ds;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calView = findViewById(R.id.calendarView);
        datePick = findViewById(R.id.button);

        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                Toast.makeText(view.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();

            }
        });

        datePick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,ds,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        ds = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                long date = calendar.getTimeInMillis();
                calView.setDate(date, true, true);

            }
        };
    }
}
