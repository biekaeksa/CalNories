package com.spiderman.calnories.food;

import android.util.Log;

import com.spiderman.calnories.data.FoodModel;
import com.spiderman.calnories.data.source.remote.APIService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Biekaeksa on 4/15/2017.
 */

public class FoodPresenter implements FoodContract.Presenter  {
    private FoodContract.View foodView;

    @Override
    public void onAttach(FoodContract.View view) {
        foodView = view;
    }

    @Override
    public void onDetach() {
        foodView = null;
    }


    @Override
    public void loadFood(String category) {
        foodView.showProgress();
        Observable<FoodModel.FoodDataModel> call = APIService.factory.create().loadFoodCategory(category);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FoodModel.FoodDataModel>() {
                    @Override
                    public void onCompleted() {
                        foodView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error ", e.getMessage());
                        foodView.hideProgress();
                        foodView.showErrorMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(FoodModel.FoodDataModel foodDataModel) {
                        foodView.showFoodData(foodDataModel.getResult());
                    }
                });
    }
}
