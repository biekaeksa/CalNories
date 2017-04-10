package com.spiderman.calnories;

import android.content.Context;

/**
 * Created by Biekaeksa on 3/14/2017.
 */

public interface BaseView {
    Context getContext();

    void onAttachView();

    void onDetachView();

}
