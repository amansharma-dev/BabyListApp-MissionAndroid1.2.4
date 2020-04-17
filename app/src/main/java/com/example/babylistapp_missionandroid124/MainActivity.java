package com.example.babylistapp_missionandroid124;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babylistapp_missionandroid124.Data.DatabaseHandler;
import com.example.babylistapp_missionandroid124.Model.BabyItems;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private EditText babyItemName;
    private EditText quantity;
    private EditText color;
    private EditText size;
    private Button saveBtn;
    private CardView cardView;

    private DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);

        databaseHandler = new DatabaseHandler(MainActivity.this);


        floatingActionButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fab:

                createAlertDialog();
//                Snackbar.make(view,"Sample Text",Snackbar.LENGTH_LONG)
//                        .setAction("Action",null)
//                        .show();
                break;

            case R.id.saveButton_Button:

                if (!babyItemName.getText().toString().isEmpty()
                && !quantity.getText().toString().isEmpty()
                && !color.getText().toString().isEmpty()
                && !size.getText().toString().isEmpty()){

                    saveBabyDetails(view);


                }
                else{

                    Snackbar.make(view,"NOT SAVED, ENTER DETAILS",Snackbar.LENGTH_LONG)
                            .setTextColor(getResources().getColor(R.color.colorButtonDelete))
                            .setAction("Action",null)
                            .show();
                }

                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_main,menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings){
            Toast.makeText(getApplicationContext(),"Setting Menu Clicked",Toast.LENGTH_SHORT).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void createAlertDialog(){
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
