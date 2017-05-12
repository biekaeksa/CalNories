package com.spiderman.calnories.calories;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.spiderman.calnories.R;
import com.spiderman.calnories.data.UserModel;
import com.spiderman.calnories.main.MainActivity;
import com.spiderman.calnories.util.SessionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaloriesActivity extends AppCompatActivity implements CaloriesContract.View, RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    @BindView(R.id.tb)
    EditText tinggiBadan;
    @BindView(R.id.bb)
    EditText beratBadan;
    @BindView(R.id.umur)
    EditText umur;
    @BindView(R.id.rdGender)
    RadioGroup gender;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female)
    RadioButton female;
    @BindView(R.id.berat)
    RadioButton berat;
    @BindView(R.id.rdActi)
    RadioGroup activity;
    @BindView(R.id.sedang)
    RadioButton sedang;
    @BindView(R.id.rendah)
    RadioButton rendah;
    private int kelamin;
    private int acti;
    private double kalori;
    private int kal;
    private CaloriesPresenter presenter;
    private ProgressDialog progressDialog;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Calorie");
        initPresenter();
        onAttachView();
        gender.setOnCheckedChangeListener(this);
        activity.setOnCheckedChangeListener(this);

    }


    private void initPresenter() {
        presenter = new CaloriesPresenter();
    }

    @Override
    public Context getContext() {
        return this;
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
        progressDialog = ProgressDialog.show(this, "", "Saving...", true, false);
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showMainView(UserModel.UserDataModel userDataModel) {
        Intent intent = new Intent(CaloriesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        if (male.isChecked()) {
            kelamin = 1;
        } else if (female.isChecked()) {
            kelamin = 2;
        }
        if (berat.isChecked()) {
            acti = 1;
        } else if (sedang.isChecked()) {
            acti = 2;
        } else if (rendah.isChecked()) {
            acti = 3;
        }
    }

    @OnClick(R.id.btnSave)
    public void clickSaveCalories() {

        if (!tinggiBadan.getText().toString().equals("") || !beratBadan.getText().toString().equals("") ||
                !umur.getText().toString().equals("")) {
            if ((acti == 1) && (kelamin == 1)) {
                kalori = (66.5 + 13.8 * Integer.parseInt(beratBadan.getText().toString()) +
                        5 * Integer.parseInt(tinggiBadan.getText().toString()) - 6.8 * Integer.parseInt(umur.getText().toString())) * 2.1;
                presenter.addCalori(getIntent().getStringExtra("id"), Float.parseFloat(beratBadan.getText().toString()),
                        Float.parseFloat(tinggiBadan.getText().toString()),
                        Integer.parseInt(umur.getText().toString()), (int) Math.round(kalori));
            } else if ((acti == 1) && (kelamin == 2)) {
                kalori = (66.5 + 13.8 * Integer.parseInt(beratBadan.getText().toString()) +
                        5 * Integer.parseInt(tinggiBadan.getText().toString()) - 6.8 * Integer.parseInt(umur.getText().toString())) * 2;
                presenter.addCalori(getIntent().getStringExtra("id"), Float.parseFloat(beratBadan.getText().toString()),
                        Float.parseFloat(tinggiBadan.getText().toString()),
                        Integer.parseInt(umur.getText().toString()), (int) Math.round(kalori));
            } else if ((acti == 2) && (kelamin == 1)) {
                kalori = (66.5 + 13.8 * Integer.parseInt(beratBadan.getText().toString()) +
                        5 * Integer.parseInt(tinggiBadan.getText().toString()) - 6.8 * Integer.parseInt(umur.getText().toString())) * 1.76;
                presenter.addCalori(getIntent().getStringExtra("id"), Float.parseFloat(beratBadan.getText().toString()),
                        Float.parseFloat(tinggiBadan.getText().toString()),
                        Integer.parseInt(umur.getText().toString()), (int) Math.round(kalori));
            } else if ((acti == 2) && (kelamin == 2)) {
                kalori = (66.5 + 13.8 * Integer.parseInt(beratBadan.getText().toString()) +
                        5 * Integer.parseInt(tinggiBadan.getText().toString()) - 6.8 * Integer.parseInt(umur.getText().toString())) * 1.7;
                presenter.addCalori(getIntent().getStringExtra("id"), Float.parseFloat(beratBadan.getText().toString()),
                        Float.parseFloat(tinggiBadan.getText().toString()),
                        Integer.parseInt(umur.getText().toString()), (int) Math.round(kalori));
            } else if ((acti == 3) && (kelamin == 1)) {
                kalori = (66.5 + 13.8 * Integer.parseInt(beratBadan.getText().toString()) +
                        5 * Integer.parseInt(tinggiBadan.getText().toString()) - 6.8 * Integer.parseInt(umur.getText().toString())) * 1.65;
                presenter.addCalori(getIntent().getStringExtra("id"), Float.parseFloat(beratBadan.getText().toString()),
                        Float.parseFloat(tinggiBadan.getText().toString()),
                        Integer.parseInt(umur.getText().toString()), (int) Math.round(kalori));
            } else if ((acti == 3) && (kelamin == 2)) {
                kalori = (66.5 + 13.8 * Integer.parseInt(beratBadan.getText().toString()) +
                        5 * Integer.parseInt(tinggiBadan.getText().toString()) - 6.8 * Integer.parseInt(umur.getText().toString())) * 1.55;
                presenter.addCalori(getIntent().getStringExtra("id"), Float.parseFloat(beratBadan.getText().toString()),
                        Float.parseFloat(tinggiBadan.getText().toString()),
                        Integer.parseInt(umur.getText().toString()), (int) Math.round(kalori));
            }else {
                Toast.makeText(CaloriesActivity.this, "Aktifitas atau jenis kelamin belum dipilih", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(CaloriesActivity.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View view) {

    }
}
