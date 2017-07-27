package com.spiderman.calnories.sports;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.spiderman.calnories.R;
import com.spiderman.calnories.data.SportsModel;
import com.spiderman.calnories.food.FoodContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportsActivity extends AppCompatActivity implements SportsConstract.View ,SwipeRefreshLayout.OnRefreshListener, SportsAdapter.SportsListener {

    private SportsAdapter adapter;
    private SportsPresenter presenter;

    @BindView(R.id.rv_sports)
    RecyclerView recyclerViewSports;
    @BindView(R.id.sports_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.toolbarSports)
    Toolbar toolbar;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sports");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initPresenter();
        onAttachView();
        setRecyclerview();
        loadData();
        initDialog();
        setupSwipeRefresh();
    }

    private void initDialog() {
        progressDialog = new ProgressDialog(this);
    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void loadData() {
        presenter.loadSports();
    }

    private void setRecyclerview() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSports.setLayoutManager(layoutManager);
        adapter = new SportsAdapter(this, new ArrayList<SportsModel>(),this);
        recyclerViewSports.setAdapter(adapter);
    }

    private void initPresenter() {
        presenter = new SportsPresenter();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void showProgressDialog() {
        progressDialog = ProgressDialog.show(this, "", "Saving...", true, false);
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showListSports(List<SportsModel> sportsModels) {
        adapter.replaceData(sportsModels);
    }

    @Override
    public void showMainView(SportsModel sportsModels) {
        Toast.makeText(SportsActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        presenter.loadSports();
    }

    @Override
    public void onClickAdd(String id, float kalori , String id_activity) {
        presenter.postSports(id, kalori, id_activity);
    }
}
