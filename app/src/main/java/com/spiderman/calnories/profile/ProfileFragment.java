package com.spiderman.calnories.profile;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.spiderman.calnories.R;
import com.spiderman.calnories.category.CategoryAdapter;
import com.spiderman.calnories.data.DummyModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    @BindView(R.id.rv_grid_profile)
    RecyclerView recyclerView;
    @BindView(R.id.pie_chart)
    PieChart pieChart;
    private ProfileAdapter adapter;

    ArrayList<DummyModel> list;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        setPieChart();
        setRecyclerview();
        addDummy();

        return view;
    }

    private void setPieChart() {
        pieChart.setUsePercentValues(true);
        ArrayList<Entry> values = new ArrayList<Entry>();
        values.add(new Entry(80f, 0));
        values.add(new Entry(20f, 1));
        PieDataSet dataSet = new PieDataSet(values, "Calories Result");

        ArrayList<String> val = new ArrayList<String>();
        val.add("Tercapai");
        val.add("Belum");
        PieData data = new PieData(val, dataSet);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);

        data.setValueTextSize(8f);

        pieChart.animateXY(1400, 1400);
    }

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
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProfileAdapter(getContext(), new ArrayList<DummyModel>());
        recyclerView.setAdapter(adapter);
    }



}
