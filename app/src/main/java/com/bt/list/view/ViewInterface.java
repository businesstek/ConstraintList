package com.bt.list.view;

import android.view.View;

import com.bt.list.data.ListItem;

import java.util.List;

/**
 * Created by owner on 10/10/17.
 */



public interface ViewInterface {
    void startDetailActivity(String dateAndTime, String message, int colorResource, View view);
    void setupAdapterAndView(List<ListItem> listOfData);


}
