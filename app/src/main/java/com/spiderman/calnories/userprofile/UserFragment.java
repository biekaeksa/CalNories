package com.spiderman.calnories.userprofile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spiderman.calnories.R;
import com.spiderman.calnories.calories.CaloriesActivity;
import com.spiderman.calnories.util.SessionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    @BindView(R.id.user_circularimageview_foto)
    CircleImageView circleImageViewFoto;
    @BindView(R.id.user_textview_beratBadan)
    TextView textViewBeratBadan;
    @BindView(R.id.user_textview_email)
    TextView textViewEmail;
    @BindView(R.id.user_textview_nama)
    TextView textViewNama;
    @BindView(R.id.user_textview_umur)
    TextView textViewUmur;
    @BindView(R.id.user_textview_tinggiBadan)
    TextView textViewTinggiBadan;
    @BindView(R.id.user_textview_kalori)
    TextView textViewKalori;



    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this,view);
        setData();
        return view;
    }

    private void setData() {
        Log.e("Nama ", SessionUtils.getLoggedUser(getContext()).getDisplayName());
        Log.e("Nama ", SessionUtils.getLoggedUser(getContext()).getEmail());

        textViewNama.setText(SessionUtils.getLoggedUser(getContext()).getDisplayName());
        textViewEmail.setText(SessionUtils.getLoggedUser(getContext()).getEmail());
        textViewBeratBadan.setText(String.valueOf(SessionUtils.getLoggedUser(getContext()).getWeight())+ " kg");
        textViewTinggiBadan.setText(String.valueOf(SessionUtils.getLoggedUser(getContext()).getHeight())+" cm");
        textViewUmur.setText(String.valueOf(SessionUtils.getLoggedUser(getContext()).getAge())+ " tahun");
        textViewKalori.setText(String.valueOf(SessionUtils.getLoggedUser(getContext()).getCalories_target())+ " kkal");
        Glide.with(getContext())
                .load(SessionUtils.getLoggedUser(getContext()).getPhotoUrl())
                .into(circleImageViewFoto);
    }

    @OnClick(R.id.user_imageview_edit)
    public void clickEditUser(){
        Intent i = new Intent(getActivity(), CaloriesActivity.class);
        startActivity(i);
    }

}
