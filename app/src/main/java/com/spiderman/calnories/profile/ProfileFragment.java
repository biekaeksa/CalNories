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
    private ProfileAdapter adapter;

    ArrayList<DummyModel> list;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.progress_circular_txt)
    TextView txtPercen;
    Handler progressHandler = new Handler();
    int i = 0;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initCircularProgressBar();
        setRecyclerview();
        addDummy();

        return view;
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

    private void initCircularProgressBar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i < 100){
                    i+=2;
                    progressHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(i);
                            txtPercen.setText("" + i + " %");
                        }
                    });
                    try {
                        Thread.sleep(300);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

}
