package com.spiderman.calnories.category;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spiderman.calnories.R;
import com.spiderman.calnories.data.CategoryModel;
import com.spiderman.calnories.food.FoodActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements  CategoryContract.View,CategoryAdapter.CategoryListener {
    private CategoryAdapter adapter;
    @BindView(R.id.rv_category)
    RecyclerView recyclerView;

    ArrayList<CategoryModel> list;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        setRecyclerview();
        addCategory();
        return view;
    }

    @Override
    public void onClickCategory(View view, int position) {
        Intent intent;
        if(list.get(position).getKategori() == "Breakfast"){
            intent = new Intent(getActivity(), FoodActivity.class);
            intent.putExtra("category", "breakfast");
            startActivity(intent);
        }else if(list.get(position).getKategori() == "Lunch"){
            intent = new Intent(getActivity(), FoodActivity.class);
            intent.putExtra("category", "lunch");
            startActivity(intent);
        }else if(list.get(position).getKategori() == "Dinner"){
            intent = new Intent(getActivity(), FoodActivity.class);
            intent.putExtra("category", "dinner");
            startActivity(intent);
        }else if(list.get(position).getKategori() == "Teatime"){
            intent = new Intent(getActivity(), FoodActivity.class);
            intent.putExtra("category", "teatime");
            startActivity(intent);
        }
    }

    private void setRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        adapter = new CategoryAdapter(getContext(), new ArrayList<CategoryModel>(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    public void addCategory(){
        list = new ArrayList<>();
        list.add(new CategoryModel("Breakfast", R.drawable.ic_sunrise_24));
        list.add(new CategoryModel("Lunch", R.drawable.ic_sun_24));
        list.add(new CategoryModel("Dinner", R.drawable.ic_night_24));
        list.add(new CategoryModel("Teatime", R.drawable.ic_cheese_24));
        list.add(new CategoryModel("Sports", R.drawable.ic_sports_24));
        adapter.replaceData(list);

    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }
}
