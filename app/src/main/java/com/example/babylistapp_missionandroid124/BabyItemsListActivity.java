package com.example.babylistapp_missionandroid124;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.babylistapp_missionandroid124.Adapter.RecyclerViewAdapter;
import com.example.babylistapp_missionandroid124.Data.DatabaseHandler;
import com.example.babylistapp_missionandroid124.Model.BabyItems;

import java.util.ArrayList;
import java.util.List;

public class BabyItemsListActivity extends AppCompatActivity {

    public static final String TAG ="BabyItemsListActivity";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private List<BabyItems> babyItemsList;
    private DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_items_list);

        recyclerView = findViewById(R.id.babyItemsList_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        babyItemsList = new ArrayList<>();
        databaseHandler = new DatabaseHandler(this);

        babyItemsList = databaseHandler.getAllBabyItems();
        for (BabyItems babyItems : babyItemsList){

            Log.d(TAG, "onCreate: "+babyItems.getItemName());
            //babyItemsList.add(babyItems);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(this,babyItemsList);
        recyclerView.setAdapter(recyclerViewAdapter);
        //whenever data is changing
        recyclerViewAdapter.notifyDataSetChanged();


    }
}
