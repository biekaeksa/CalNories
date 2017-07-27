package com.spiderman.calnories.sports;

import android.util.Log;

import com.spiderman.calnories.data.SportsModel;
import com.spiderman.calnories.data.source.remote.APIService;
import com.spiderman.calnories.food.FoodContract;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Biekaeksa on 7/2/2017.
 */

public class SportsPresenter implements SportsConstract.Presenter {
    private SportsConstract.View sportsView;

    @Override
    public void onAttach(SportsConstract.View view) {
        sportsView = view;
    }

    @Override
    public void onDetach() {
        sportsView = null;
    }

    @Override
    public void loadSports() {
        sportsView.showProgress();
        Observable<SportsModel.SportsDataModel> call = APIService.factory.create().getListSports();
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SportsModel.SportsDataModel>() {
                    @Override
                    public void onCompleted() {
                        sportsView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        sportsView.hideProgress();
                        Log.e("Error ", e.getMessage());
                    }

                    @Override
                    public void onNext(SportsModel.SportsDataModel sportsDataModel) {
                        sportsView.showListSports(sportsDataModel.getResult());
                    }
                });
    }

    @Override
    public void postSports(String id, float kalori, String id_activity) {
        sportsView.showProgressDialog();
        Observable<SportsModel.SportsDataaModel> callSports = APIService.factory.create().postCalorieSports(id, kalori, id_activity);
        callSports.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SportsModel.SportsDataaModel>() {
                    @Override
                    public void onCompleted() {
                        sportsView.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        sportsView.hideProgressDialog();
                        Log.e("Error ", e.getMessage());
                    }

                    @Override
                    public void onNext(SportsModel.SportsDataaModel sportsDataModel) {
                        sportsView.showMainView(sportsDataModel.getResult());
                    }
                });
    }

}
