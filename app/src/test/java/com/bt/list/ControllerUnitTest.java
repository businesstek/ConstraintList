package com.bt.list;

import android.view.View;

import com.bt.list.data.DataSourceInterface;
import com.bt.list.data.ListItem;
import com.bt.list.logic.Controller;
import com.bt.list.view.ViewInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test Driven Development Bonus: Before writing the methods themselves (a.k.a. writing the
 * "Implementation"), I've written some Unit Tests. In a nutshell, these Tests help me to figure out
 * what methods and logic I'll need for the class which I'm testing. This process is slow at first,
 * but once you become fast at writing Tests, you'll start to see how they actually help you write
 * Better Code, often at a Faster Pace.
 * <p>
 * I have some Videos on this topic on my youtube channel. Check them out for more info.
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerUnitTest {

    //We technically could've just used the FakeDataSource here, but you don't always want to use
    //Mocks over Fakes and vice versa. Depends on your use case.
    @Mock
    DataSourceInterface dataSource;

    @Mock
    ViewInterface view;

    @Mock
    View testViewRoot;

    /**
     * Often times we'll want data to run our tests against. I like to define such Data as a series
     * of Static variables in the Test file itself for convenience.
     */
    private static final ListItem TEST_ITEM = new ListItem(
            "6:30AM 06/01/2017",
            "Look at Open Source Projects",
            R.color.RED);


    private static final int POSITION = 3;

    /**
     * Since we're testing our Controller Class, this one is the Real implementation!
     * How this works is we make a Real instance of the Controller talk to Fake Instances of
     * what it would normally talk to (i.e. our Mocks above). Since the Controller does most of the
     * thinking (Logic), it follows that it would benefit the most from a good suite of Tests.
     */
    Controller controller;


    /**
     * the @Before annotation makes this Code run before the Tests themselves. You can use this
     * to initialize the different components of your Test, should you need to. You can also
     * initialize things in the Test methods themselves. Whatever works best for your Use Case.
     */
    @Before
    public void setUpTest() {
        controller = new Controller(view, dataSource);
    }

    @Test
    public void onGetListDataSuccessful() {
        //Since we require a List<ListItem> to be returned by the dataSource, we can define it here:
        List<ListItem> listOfData = new ArrayList<>();
        listOfData.add(TEST_ITEM);


        //This is where we tell our "Mocks" what to do when our Controller talks to them. Since they
        //aren't real objects, we must tell them exactly what to do if we want responses from them.
        Mockito.when(dataSource.getListOfData())
                .thenReturn(listOfData);

        //This is the method we are testing
        controller.getListFromDataSource();

        //Check how the Tested Class responds to the data it receives
        //or test it's behaviour
        Mockito.verify(view).setupAdapterAndView(listOfData);
    }


    @Test
    public void onListItemClicked() {
        controller.onListItemClick(TEST_ITEM, testViewRoot);

        Mockito.verify(view).startDetailActivity(
                TEST_ITEM.getDateAndTime(),
                TEST_ITEM.getMessage(),
                TEST_ITEM.getColorResource(),
                testViewRoot);
    }











    @Test
    public void addition_isCorrect() throws Exception{
        assertEquals(4,2+2);
    }
}
