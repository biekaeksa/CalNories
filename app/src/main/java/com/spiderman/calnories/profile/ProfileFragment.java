package com.spiderman.calnories.profile;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.pavlospt.CircleView;
import com.spiderman.calnories.R;
import com.spiderman.calnories.data.ProfileModel;
import com.spiderman.calnories.data.UserModel;
import com.spiderman.calnories.util.SessionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileContract.View {
    @BindView(R.id.rv_grid_profile)
    RecyclerView recyclerView;
    private ProfileAdapter adapter;
    Calendar calendar;
    private String tanggal;
    private Handler handler = new Handler();
    ProfilPresenter presenter;

    @BindView(R.id.profile_textview_target)
    TextView textViewTarget;
    @BindView(R.id.fragment_circular_view)
    CircleView circleViewCalorie;

    float persentase;

    ArrayList<ProfileModel.ProfileDataModel> list;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initPresenter();
        loadDataProfile(SessionUtils.getLoggedUser(getContext()));
        onAttachView();

        setRecyclerview();


        return view;
    }


    private void loadDataProfile(UserModel userModel) {
        presenter.loadData(userModel.getIdUser());
    }


    private void initPresenter() {
        presenter = new ProfilPresenter();
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
        adapter = new ProfileAdapter(getActivity(), new ArrayList<ProfileModel>());
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showDataProfile(List<ProfileModel> foodModels) {
        adapter.replaceData(foodModels);
    }

    @Override
    public void getTargetCalorie(float targetCalorie, float calorieDaily) {
        textViewTarget.setText(String.valueOf(targetCalorie)+" kkal");
        circleViewCalorie.setSubtitleText(String.valueOf(calorieDaily)+" kkal");

        persentase = (calorieDaily / SessionUtils.getLoggedUser(getContext()).getCalories_target()) * 100;
        if (persentase > 100){
            circleViewCalorie.setTitleText("100%");
        }else {
            circleViewCalorie.setTitleText(String.valueOf(Math.round(persentase))+"%");
        }

    }


}
