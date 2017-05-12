package com.spiderman.calnories.category;

import com.spiderman.calnories.BaseView;


/**
 * Created by Biekaeksa on 4/16/2017.
 */

public class CategoryContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void addCategory();
    }
}
