package com.spiderman.calnories.profile;

import com.spiderman.calnories.BasePresenter;
import com.spiderman.calnories.BaseView;
import com.spiderman.calnories.data.ProfileModel;

import java.util.List;

/**
 * Created by Biekaeksa on 5/29/2017.
 */

public class ProfileContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showDataProfile(List<ProfileModel> foodModels);

        void getTargetCalorie(float targetCalorie, float calorieDaily);
    }
    interface Presenter extends BasePresenter<View> {
        void loadData(String id);
    }
}
