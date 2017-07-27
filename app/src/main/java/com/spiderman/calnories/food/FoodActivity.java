package com.spiderman.calnories.food;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.spiderman.calnories.R;
import com.spiderman.calnories.data.FoodModel;
import com.spiderman.calnories.detailfood.DetailFoodActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodActivity extends AppCompatActivity implements FoodContract.View, SwipeRefreshLayout.OnRefreshListener, FoodAdapter.FoodListener {
    private FoodAdapter adapter;
    private FoodPresenter presenter;

    @BindView(R.id.rv_food)
    RecyclerView recyclerView;
    @BindView(R.id.food_swipe_refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.toolbarFood)
    Toolbar toolbar;

    String cok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Food");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initPresenter();
        onAttachView();
        setRecyclerview();
        loadData();
        setupSwipeRefresh();
    }

    @Override
    public void onDestroy() {
        onAttachView();
        super.onDestroy();
    }

    private void setupSwipeRefresh() {
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchh = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchh);
        search(searchView);
        return true;
    }

    public void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    private void setRecyclerview() {
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FoodAdapter(getContext(), new ArrayList<FoodModel>(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initPresenter() {
        presenter = new FoodPresenter();
    }

    @Override
    public void onRefresh() {
        //presenter.loadFood();
        presenter.loadFood(getIntent().getStringExtra("category"));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    public void showProgress() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showFoodData(List<FoodModel> foodModels) {
        adapter.replaceData(foodModels);
    }


    public void loadData() {
        presenter.loadFood(getIntent().getStringExtra("category"));
        //presenter.loadFood();
    }

    @Override
    public void onItemClick(String id, String makanan, String photo, Float kalori, String resep, String pembuatan) {
        Intent i = new Intent(FoodActivity.this, DetailFoodActivity.class);
        i.putExtra("id", id);
        i.putExtra("nama_makanan", makanan);
        i.putExtra("photo", photo);
        i.putExtra("kalori", String.valueOf(kalori));
        i.putExtra("resep", resep);
        i.putExtra("pembuatan", pembuatan);
        i.putExtra("category", getIntent().getStringExtra("category"));
        startActivity(i);
    }
}
