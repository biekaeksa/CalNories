package com.spiderman.calnories.profile;

import android.util.Log;

import com.spiderman.calnories.data.ProfileModel;
import com.spiderman.calnories.data.source.remote.APIService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Biekaeksa on 5/29/2017.
 */

public class ProfilPresenter implements ProfileContract.Presenter {
    private ProfileContract.View  profilView;

    @Override
    public void onAttach(ProfileContract.View view) {
        profilView = view;
    }

    @Override
    public void onDetach() {
        profilView = null;
    }

    @Override
    public void loadData(String id) {
        Observable<ProfileModel.ProfileDataModel> call = APIService.factory.create().getDailyCalorie(id);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProfileModel.ProfileDataModel>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Berhasil", " cok");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("GAGAL", e.getMessage());
                    }

                    @Override
                    public void onNext(ProfileModel.ProfileDataModel profileDataModel) {
                        profilView.showDataProfile(profileDataModel.getResults());
                        profilView.getTargetCalorie(profileDataModel.getCalorieTarget(), profileDataModel.getCalorieDaily());
                    }
                });
    }
}
