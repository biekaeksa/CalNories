package com.spiderman.calnories.detailfood;

import android.util.Log;
import android.widget.Toast;

import com.spiderman.calnories.data.FoodModel;
import com.spiderman.calnories.data.source.remote.APIService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Biekaeksa on 5/26/2017.
 */

public class DetailFoodPresenter implements DetailFoodContract.Presenter {
    private DetailFoodContract.View detailFoodView;

    @Override
    public void onAttach(DetailFoodContract.View view) {
        detailFoodView = view;
    }

    @Override
    public void onDetach() {
        detailFoodView = null;
    }

    @Override
    public void postFoodDaily(String id, Float food_calorie, String food_id) {
        detailFoodView.showProgress();
        Observable<FoodModel> call = APIService.factory.create().postDailyCalorie(id,
                food_calorie, food_id);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FoodModel>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Sukses", "JANCOK");
                        detailFoodView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error" , e.getMessage());
                        detailFoodView.hideProgress();
                        detailFoodView.showErrorMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(FoodModel foodDataModel) {
                        detailFoodView.showSucces();
                    }
                });
    }
}
