package com.bt.list.data;

import com.bt.list.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by owner on 10/10/17.
 * Test Double
 */

public class FakeDataSource implements DataSourceInterface{
    private static final int sizeOfCollection = 12;
    private Random random;

    private final String[] datesAndTimes = {
            "6:30AM 06/01/2017",
            "9:26PM 04/22/2013",
            "2:01PM 12/02/2015",
            "2:43AM 09/7/2018",
    };

    private final String[] messages = {
            "Check out content like Fragmented Podcast to expose yourself to the knowledge, ideas, " +
                    "and opinions of experts in your field",
            "Look at Open Source Projects like Android Architecture Blueprints to see how experts" +
                    " design and build Apps",
            "Write lots of Code and Example Apps. Writing good Quality Code in an efficient manner "
                    + "is a Skill to be practiced like any other.",
            "If at first something doesn't make any sense, find another explanation. We all " +
                    "learn/teach different from each other. Find an explanation that speaks to you."
    };

    private final int[] drawables = {
            R.color.RED,
            R.color.BLUE,
            R.color.GREEN,
            R.color.YELLOW
    };

    @Override
    public List<ListItem> getListOfData() {
        ArrayList<ListItem> listOfData = new ArrayList<>();

        Random random = new Random();

        for(int i=0;i<12;i++){
            int randOne = random.nextInt(4);
            int randTwo = random.nextInt(4);
            int randThree = random.nextInt(4);

            ListItem listItem = new ListItem(
                    datesAndTimes[randOne],
                    messages[randTwo],
                    drawables[randThree]
            );

            listOfData.add(listItem);
        }
        return listOfData;
    }
}
