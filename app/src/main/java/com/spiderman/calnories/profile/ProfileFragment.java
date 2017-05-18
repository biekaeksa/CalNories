package com.spiderman.calnories.profile;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.spiderman.calnories.R;
import com.spiderman.calnories.data.DummyModel;
import com.spiderman.calnories.util.StringHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    @BindView(R.id.rv_grid_profile)
    RecyclerView recyclerView;
    @BindView(R.id.profile_textview_tanggal)
    TextView txtTanggal;
    private ProfileAdapter adapter;
    Calendar calendar;
    private String tanggal;
    private Handler handler = new Handler();

    ArrayList<DummyModel> list;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        handler.postDelayed(runnable, 1000);
        setRecyclerview();
        addDummy();

        return view;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy ");
            String strDate = dateFormat.format(calendar.getTime());
            txtTanggal.setText(strDate);
        }
    };



    private void addDummy() {
        list = new ArrayList<>();
        list.add(new DummyModel("Target Calorie", 2100));
        list.add(new DummyModel("Breakfast", 800));
        list.add(new DummyModel("Lunch", 800));
        list.add(new DummyModel("Dinner", 800));
        list.add(new DummyModel("Teatime", 400));
        list.add(new DummyModel("Sports", 700));
        adapter.replaceData(list);

    }

    private void setRecyclerview() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProfileAdapter(getContext(), new ArrayList<DummyModel>());
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.profile_textview_tanggal)
    public void dateProfileClicked(){
        calendar = java.util.Calendar.getInstance();
        final int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        final int mMonth = calendar.get(Calendar.MONTH);
        final int mYears = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                tanggal = StringHelper.convertCalendarToString(i, i1, i2);
                txtTanggal.setText(tanggal);
            }
        },mYears,mMonth,mDay);
        datePickerDialog.show();
    }


}
