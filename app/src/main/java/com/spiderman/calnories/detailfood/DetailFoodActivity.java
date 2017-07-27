package com.spiderman.calnories.detailfood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.spiderman.calnories.R;
import com.spiderman.calnories.food.FoodActivity;
import com.spiderman.calnories.util.SessionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFoodActivity extends AppCompatActivity implements DetailFoodContract.View, View.OnClickListener {
    @BindView(R.id.detailfood_imageview_foto)
    ImageView imageViewFoto;
    @BindView(R.id.detailfood_textview_calories)
    TextView textViewCalories;
    @BindView(R.id.detailfood_textview_resep)
    TextView textViewResep;
    @BindView(R.id.detailfood_textview_pembuatan)
    TextView textViewPembuatan;
    @BindView(R.id.detailfood_button_save)
    Button buttonSave;

    DetailFoodPresenter presenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.detailfood_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("nama_makanan"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setData();

        initPresenter();
        onAttachView();
        initDialog();
        buttonSave.setOnClickListener(this);

    }

    private void initDialog() {
        progressDialog = new ProgressDialog(DetailFoodActivity.this);
    }

    private void setData() {
        textViewCalories.setText(getIntent().getStringExtra("kalori") + " kkal");
        textViewPembuatan.setText(getIntent().getStringExtra("pembuatan"));
        textViewResep.setText(getIntent().getStringExtra("resep"));
        Glide.with(this)
                .load(getIntent().getStringExtra("photo"))
                .into(imageViewFoto);
    }

    private void initPresenter() {
        presenter = new DetailFoodPresenter();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
    public void showSucces() {
        Toast.makeText(DetailFoodActivity.this,"Success", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onClick(View view) {
        presenter.postFoodDaily(SessionUtils.getLoggedUser(getContext()).getIdUser(), Float.parseFloat(getIntent().getStringExtra("kalori")),
                getIntent().getStringExtra("id"));
    }
}
