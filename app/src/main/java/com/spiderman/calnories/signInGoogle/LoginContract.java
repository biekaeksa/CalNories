package com.spiderman.calnories.signInGoogle;

import com.spiderman.calnories.BasePresenter;
import com.spiderman.calnories.BaseView;
import com.spiderman.calnories.data.UserModel;

import java.util.List;

/**
 * Created by Biekaeksa on 3/17/2017.
 */

public class LoginContract {
    interface View extends BaseView {


        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showMainView(List<UserModel> userDataModel);

    }

    interface Presenter extends BasePresenter<View>{
        void signInUp(String id, String displayName, String email,
                      String photoUrl);
    }
}
