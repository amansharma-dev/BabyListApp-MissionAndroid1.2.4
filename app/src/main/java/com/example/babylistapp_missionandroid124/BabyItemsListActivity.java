package com.example.babylistapp_missionandroid124;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.babylistapp_missionandroid124.Adapter.RecyclerViewAdapter;
import com.example.babylistapp_missionandroid124.Model.BabyItems;

import java.util.ArrayList;

public class BabyItemsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private ArrayList<BabyItems> babyItemsArrayList;
    private ArrayAdapter<String> babyItemsArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_items_list);

        recyclerView = findViewById(R.id.babyItemsList_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        babyItemsArrayList = new ArrayList<>();

        recyclerViewAdapter = new RecyclerViewAdapter(this,babyItemsArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
