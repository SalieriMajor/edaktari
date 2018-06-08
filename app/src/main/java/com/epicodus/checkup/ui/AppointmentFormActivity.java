//package com.epicodus.checkup.ui;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//import com.epicodus.checkup.R;
//
//public class AppointmentFormActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_appointment_form);
//    }
//}
package com.epicodus.checkup.ui;

import android.support.v7.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import java.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.checkup.R;

public class AppointmentFormActivity extends AppCompatActivity {


    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;
    private Button ButtonSendFeedback;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCurrentDateOnView();
        addListenerOnButton();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCurrentDateOnView() {
        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);
    }

    private void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };


    public void sendBack(View button) {
        final EditText nameField = (EditText) findViewById(R.id.firstname);
        String fname = nameField.getText().toString();

        final EditText emailField = (EditText) findViewById(R.id.sname);
        String sname = emailField.getText().toString();

        final EditText feedbackField = (EditText) findViewById(R.id.email);
        String email = feedbackField.getText().toString();


    }
}
