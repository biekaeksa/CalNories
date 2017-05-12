package com.spiderman.calnories.calories;

import com.spiderman.calnories.BasePresenter;
import com.spiderman.calnories.BaseView;
import com.spiderman.calnories.data.UserModel;

/**
 * Created by Biekaeksa on 4/13/2017.
 */

public class CaloriesContract {
    interface View extends BaseView {


        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showMainView(UserModel.UserDataModel userDataModel);

    }

    interface Presenter extends BasePresenter<View> {
        void addCalori(String id, float bb, float tb, int umur, int kal);
    }
}
