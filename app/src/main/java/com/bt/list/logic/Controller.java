package com.bt.list.logic;

import android.view.View;

import com.bt.list.data.DataSourceInterface;
import com.bt.list.data.ListItem;
import com.bt.list.view.ViewInterface;

/**
 * Created by owner on 10/10/17.
 */

public class Controller {

    //Code through interfaces
    //ListActivity and FakeDataSource
    private ViewInterface view;
    private DataSourceInterface dataSource;

    private ListItem temporaryListItem;
    private int temporaryListItemPosition;

    /**
     * As soon as this object is created, it does a few things:
     * 1. Assigns Interfaces Variables so that it can talk to the DataSource and that Activity
     * 2. Tells the dataSource to fetch a List of ListItems.
     * 3. Tells the View to draw the fetched List of Data.
     * @param view
     * @param dataSource
     */
    public Controller(ViewInterface view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;

        getListFromDataSource();
    }

    public void onListItemClick(ListItem selectedItem, View viewRoot) {
        view.startDetailActivity(
                selectedItem.getDateAndTime(),
                selectedItem.getMessage(),
                selectedItem.getColorResource(),
                viewRoot
        );
    }

    public void getListFromDataSource() {
        view.setupAdapterAndView(dataSource.getListOfData());
    }


}
