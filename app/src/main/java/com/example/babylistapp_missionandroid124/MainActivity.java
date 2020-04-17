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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);




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
        builder.setView(view);
        alertDialog = builder.create();//creating dialog object
        alertDialog.show();//show
    }
}
