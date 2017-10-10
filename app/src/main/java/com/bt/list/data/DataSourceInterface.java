package com.bt.list.data;

import java.util.List;

/**
 * Created by owner on 10/10/17.
 * This is aContract between Classes without implementation
 * can be network or our fake data
 *
 */

public interface DataSourceInterface {
    List<ListItem> getListOfData();

}
