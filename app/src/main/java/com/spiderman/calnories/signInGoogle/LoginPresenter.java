package com.spiderman.calnories.signInGoogle;

import android.util.Log;

import com.spiderman.calnories.data.UserModel;
import com.spiderman.calnories.data.source.remote.APIService;
import com.spiderman.calnories.util.SessionUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Biekaeksa on 3/17/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;

    @Override
    public void onAttach(LoginContract.View view) {
        loginView = view;
    }

    @Override
    public void onDetach() {
        loginView = null;
    }

    @Override
    public void signInUp(String id, String displayName, String email, String photoUrl) {
        loginView.showProgress();
        Observable<UserModel.UserDataModel> call = APIService.factory.create().postSignInUp(id,displayName, email, photoUrl);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserModel.UserDataModel>() {
                    @Override
                    public void onCompleted() {
                        loginView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                        loginView.hideProgress();
                    }

                    @Override
                    public void onNext(UserModel.UserDataModel userDataModel) {
                        if(SessionUtils.login(loginView.getContext(), userDataModel.getData())) {
                            loginView.showMainView(userDataModel);
                        }
                    }
                });
    }


}
