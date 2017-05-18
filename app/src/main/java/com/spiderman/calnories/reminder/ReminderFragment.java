package com.spiderman.calnories.reminder;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.spiderman.calnories.R;
import com.spiderman.calnories.util.StringHelper;

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
    private java.util.Calendar calendar;
    private String tanggalBreakfast;
    private String tanggalLunch;
    private String tanggalDinner;


    public ReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_reminder, container, false);
        ButterKnife.bind(this, view);
        switchPagi.setOnCheckedChangeListener(this);
        return view;
    }


    @OnClick(R.id.time_breakfast)
    public void clickTimeBreakfast() {
        calendar = java.util.Calendar.getInstance();
        final int mHour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
        final int mMin = calendar.get(java.util.Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                tanggalBreakfast = StringHelper.convertTimeToString(hour, minute);
                edtBreakfast.setText(tanggalBreakfast);
            }
        }, mHour, mMin, false);
        timePickerDialog.show();

    }

    @OnClick(R.id.time_lunch)
    public void clickTimeLunch() {
        calendar = java.util.Calendar.getInstance();
        final int mHour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
        final int mMin = calendar.get(java.util.Calendar.MINUTE);

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
        calendar = java.util.Calendar.getInstance();
        final int mHour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
        final int mMin = calendar.get(java.util.Calendar.MINUTE);

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
                setSwitchState(b);
                break;
            case R.id.switch_siang:
                setSwitchState(b);
                break;
            case R.id.switch_malam:
                setSwitchState(b);
                break;
        }
    }

    private void setSwitchState(boolean state){
        if(state){
            Toast.makeText(getActivity(), "Berjalan", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Berhenti", Toast.LENGTH_SHORT).show();
        }
    }
}
