package com.spiderman.calnories.food;

import com.spiderman.calnories.BasePresenter;
import com.spiderman.calnories.BaseView;
import com.spiderman.calnories.data.FoodModel;

import java.util.List;

/**
 * Created by Biekaeksa on 4/15/2017.
 */

public class FoodContract {
    interface View extends BaseView{
        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showFoodData(List<FoodModel> foodModels);
    }
    interface Presenter extends BasePresenter<View> {
        void loadFood(String category);
    }

}
