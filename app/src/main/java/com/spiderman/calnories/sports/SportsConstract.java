package com.spiderman.calnories.sports;

import com.spiderman.calnories.BasePresenter;
import com.spiderman.calnories.BaseView;
import com.spiderman.calnories.data.SportsModel;

import java.util.List;

/**
 * Created by Biekaeksa on 7/2/2017.
 */

public class SportsConstract {
    interface View extends BaseView {
        void showProgress();

        void showProgressDialog();

        void hideProgressDialog();

        void hideProgress();

        void showErrorMessage(String message);

        void showListSports(List<SportsModel> sportsModels);

        void showMainView(SportsModel sportsModels);
    }
    interface Presenter extends BasePresenter<View> {
        void loadSports();
        void postSports(String id, float kalori, String id_activity);
    }
}
