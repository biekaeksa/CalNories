package com.spiderman.calnories;

/**
 * Created by Biekaeksa on 3/14/2017.
 */

public interface BasePresenter<T extends BaseView> {
    void onAttach(T view);

    void onDetach();
}
