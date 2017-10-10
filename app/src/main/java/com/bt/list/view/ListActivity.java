package com.bt.list.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bt.list.R;
import com.bt.list.data.FakeDataSource;
import com.bt.list.data.ListItem;
import com.bt.list.logic.Controller;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ViewInterface {

    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_DRAWABLE = "EXTRA_DRAWABLE";

    private List<ListItem> listOfData;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView =(RecyclerView) findViewById(R.id.rec_list_activity);
        layoutInflater = getLayoutInflater();

        //This is Dependency Injection here
        controller = new Controller(this, new FakeDataSource());
    }


    //**********************  View Interface *******************
    // implement methods
    //

    @Override
    public void startDetailActivity(String dateAndTime, String message, int colorResource, View view) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra(EXTRA_DATE_AND_TIME, dateAndTime);
        i.putExtra(EXTRA_MESSAGE, message);
        i.putExtra(EXTRA_DRAWABLE, colorResource);

        startActivity(i);
    }

    @Override
    public void setupAdapterAndView(List<ListItem> listOfData) {
        this.listOfData = listOfData;
        //Could be grid or staggered grid
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);
    }

    //**********************  ADAPTER *******************
    // put with view class activity nested to access to data
    //
    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

        //
        //Must implement next three methods
        //
        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //false don't attach to parent view root
            View v = layoutInflater.inflate(R.layout.item_data,parent,false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            ListItem currentItem = listOfData.get(position);
            holder.coloredCircle.setBackgroundResource(currentItem.getColorResource());
            holder.message.setText(currentItem.getMessage());
            holder.dateAndTime.setText(currentItem.getDateAndTime());
        }

        //size of data set
        //helps adapter decide how many items it will need to create to manage
        @Override
        public int getItemCount() {
            return listOfData.size();
        }

        //View holder binds data to view
        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private View coloredCircle;
            private TextView dateAndTime;
            private TextView message;
            private ViewGroup container;


            public CustomViewHolder(View itemView) {
                super(itemView);
                this.coloredCircle = itemView.findViewById(R.id.imv_list_item_circle);
                this.dateAndTime = (TextView)itemView.findViewById(R.id.lbl_date_and_time);
                this.message = (TextView)itemView.findViewById(R.id.lbl_message);
                this.container = (ViewGroup)itemView.findViewById(R.id.root_list_item);

                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                ListItem listItem=listOfData.get(this.getAdapterPosition());
                controller.onListItemClick(listItem, view);
            }
        }


    }



}
