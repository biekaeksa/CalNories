package com.spiderman.calnories.detailfood;

import com.spiderman.calnories.BasePresenter;
import com.spiderman.calnories.BaseView;
import com.spiderman.calnories.data.FoodModel;
import com.spiderman.calnories.food.FoodContract;

import java.util.List;

/**
 * Created by Biekaeksa on 5/26/2017.
 */

public class DetailFoodContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showSucces();

    }
    interface Presenter extends BasePresenter<DetailFoodContract.View> {
        void postFoodDaily(String id, Float food_calorie, String food_id);
    }
}
