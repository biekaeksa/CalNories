package com.spiderman.calnories.calories;

import android.util.Log;

import com.spiderman.calnories.data.UserModel;
import com.spiderman.calnories.data.source.remote.APIService;
import com.spiderman.calnories.util.SessionUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Biekaeksa on 4/13/2017.
 */

public class CaloriesPresenter implements CaloriesContract.Presenter {
    CaloriesContract.View caloriesView;

    @Override
    public void onAttach(CaloriesContract.View view) {
        caloriesView = view;
    }

    @Override
    public void onDetach() {
        caloriesView = null;
    }


    @Override
    public void addCalori(String id, float bb, float tb, int umur, int kal) {
        caloriesView.showProgress();
        Observable<UserModel.UserDataModel> call = APIService.factory.create().putCalories(id, bb, tb, umur, kal);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserModel.UserDataModel>() {
                    @Override
                    public void onCompleted() {
                        caloriesView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error ", e.getMessage());
                        caloriesView.hideProgress();
                    }

                    @Override
                    public void onNext(UserModel.UserDataModel userDataModel) {
                            caloriesView.showMainView(userDataModel);

                    }
                });
    }
}
