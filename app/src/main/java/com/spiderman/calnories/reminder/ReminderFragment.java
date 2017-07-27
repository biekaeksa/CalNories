package com.spiderman.calnories.reminder;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.spiderman.calnories.R;
import com.spiderman.calnories.util.StringHelper;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.time_breakfast)
    TextView edtBreakfast;
    @BindView(R.id.time_lunch)
    TextView edtLunch;
    @BindView(R.id.time_dinner)
    TextView edtDinner;
    @BindView(R.id.switch_pagi)
    SwitchCompat switchPagi;
    @BindView(R.id.switch_siang)
    SwitchCompat switchSiang;
    @BindView(R.id.switch_malam)
    SwitchCompat switchMalam;
    private java.util.Calendar calendarPagi, calendarSiang, calendarMalam;
    private String tanggalBreakfast;
    private String tanggalLunch;
    private String tanggalDinner;

    private PendingIntent pendingIntent;
    AlarmManager alarmManager;

    private static ReminderFragment inst;

    public static ReminderFragment instance(){
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    public ReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_reminder, container, false);
        ButterKnife.bind(this, view);
        alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
        switchPagi.setOnCheckedChangeListener(this);
        return view;
    }


    @OnClick(R.id.time_breakfast)
    public void clickTimeBreakfast() {
        calendarPagi = java.util.Calendar.getInstance();

        final int mHour = calendarPagi.get(java.util.Calendar.HOUR_OF_DAY);
        final int mMin = calendarPagi.get(java.util.Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                calendarPagi = java.util.Calendar.getInstance();
                calendarPagi.set(Calendar.HOUR_OF_DAY, hour);
                calendarPagi.set(Calendar.MINUTE, minute);
                calendarPagi.add(Calendar.DATE,1);
                tanggalBreakfast = StringHelper.convertTimeToString(hour, minute);
                edtBreakfast.setText(tanggalBreakfast);

            }
        }, mHour, mMin, false);
        timePickerDialog.show();

    }

    @OnClick(R.id.time_lunch)
    public void clickTimeLunch() {
        calendarSiang = java.util.Calendar.getInstance();
        final int mHour = calendarSiang.get(java.util.Calendar.HOUR_OF_DAY);
        final int mMin = calendarSiang.get(java.util.Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                tanggalLunch = StringHelper.convertTimeToString(hour, minute);
                edtLunch.setText(tanggalLunch);
            }
        }, mHour, mMin, false);
        timePickerDialog.show();
    }

    @OnClick(R.id.time_dinner)
    public void clickTimeDinner() {
        calendarMalam = java.util.Calendar.getInstance();
        final int mHour = calendarMalam.get(java.util.Calendar.HOUR_OF_DAY);
        final int mMin = calendarMalam.get(java.util.Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                tanggalDinner = StringHelper.convertTimeToString(hour, minute);
                edtDinner.setText(tanggalDinner);
            }
        }, mHour, mMin, false);
        timePickerDialog.show();
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_pagi:
                //setSwitchState(b);
                break;
            case R.id.switch_siang:
                //setSwitchState(b);
                break;
            case R.id.switch_malam:
                //setSwitchState(b);
                break;
        }
    }

    private void setSwitchState(boolean state){
        if(state){
            Log.e("Alarm ", "On");
            Intent i = new Intent(getContext(), AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(getContext(), 1, i, 1 );
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendarPagi.getTimeInMillis(), pendingIntent);
        }else {
            alarmManager.cancel(pendingIntent);
            Log.e("Alarm ", "Off");
        }
    }
}
