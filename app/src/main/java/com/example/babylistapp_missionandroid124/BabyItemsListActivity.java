package com.example.babylistapp_missionandroid124;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.babylistapp_missionandroid124.Adapter.RecyclerViewAdapter;
import com.example.babylistapp_missionandroid124.Data.DatabaseHandler;
import com.example.babylistapp_missionandroid124.Model.BabyItems;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class BabyItemsListActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG ="BabyItemsListActivity";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private List<BabyItems> babyItemsList;
    private DatabaseHandler databaseHandler;

    private FloatingActionButton floatingActionButton;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private EditText babyItemName;
    private EditText quantity;
    private EditText color;
    private EditText size;
    private Button saveBtn;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_items_list);

        recyclerView = findViewById(R.id.babyItemsList_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton = findViewById(R.id.fab_activityBabyItems);
        floatingActionButton.setOnClickListener(this);


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

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.fab_activityBabyItems:

                createAlertDialog();

                break;

            case R.id.saveButton_Button:
                if (!babyItemName.getText().toString().isEmpty()
                        && !quantity.getText().toString().isEmpty()
                        && !color.getText().toString().isEmpty()
                        && !size.getText().toString().isEmpty()){

                    saveBabyDetails(view);

                    //Todo: post delay in dialog dismiss

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertDialog.dismiss();
                            //Todo: move to next screen - details screen
                            Intent intent = new Intent(BabyItemsListActivity.this,BabyItemsListActivity.class);
                            startActivity(intent);
                        }
                    }, 1200);


                }
                else{

                    Snackbar.make(view,"NOT SAVED, ENTER DETAILS",Snackbar.LENGTH_LONG)
                            .setTextColor(getResources().getColor(R.color.colorButtonDelete))
                            .setAction("Action",null)
                            .show();
                }

                break;
        }
        createAlertDialog();
    }

    private void createAlertDialog(){

        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup,null);

        babyItemName = view.findViewById(R.id.babyItemName_editText);
        quantity = view.findViewById(R.id.quantity_EditText);
        color = view.findViewById(R.id.color_EditText);
        size = view.findViewById(R.id.size_EditText);
        saveBtn = view.findViewById(R.id.saveButton_Button);
        cardView = view.findViewById(R.id.popup_cardView);
        cardView.setCardBackgroundColor(this.getResources().getColor(R.color.colorCard));

        saveBtn = view.findViewById(R.id.saveButton_Button);

        saveBtn.setOnClickListener(this);

        builder.setView(view);
        alertDialog = builder.create();//creating dialog object
        alertDialog.show();//show
    }

    public void saveBabyDetails(View view){

        //Todo: save each baby item in a Database
        //Todo: move to next Screen - baby Items/ details screen.

        String baby_ItemName = babyItemName.getText().toString().trim();
        int baby_quantity = Integer.parseInt(quantity.getText().toString().trim());
        String baby_color = color.getText().toString().trim();
        int baby_size = Integer.parseInt(size.getText().toString().trim());


        BabyItems babyItems = new BabyItems();
        babyItems.setItemName(baby_ItemName);
        babyItems.setQuantity(baby_quantity);
        babyItems.setColor(baby_color);
        babyItems.setSize(baby_size);

        databaseHandler.addBabyItem(babyItems);

        //snackbar is attached to a view thats why passing View view in save()
        Snackbar.make(view,"Baby item saved",Snackbar.LENGTH_SHORT)
                .setAction("Action",null).show();

    }
}
