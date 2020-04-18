package com.example.babylistapp_missionandroid124.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babylistapp_missionandroid124.Data.DatabaseHandler;
import com.example.babylistapp_missionandroid124.Model.BabyItems;
import com.example.babylistapp_missionandroid124.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<BabyItems> babyItemsList;


    //confirm dialog delete
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private int position;
    public RecyclerViewAdapter(Context context, List<BabyItems> babyItemsList) {
        this.context = context;
        this.babyItemsList = babyItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.baby_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BabyItems babyItems = babyItemsList.get(position);

        holder.item.setText(babyItems.getItemName());
        holder.quantity.setText(String.valueOf(babyItems.getQuantity()));
        holder.color.setText(babyItems.getColor());
        holder.size.setText(String.valueOf(babyItems.getSize()));
        holder.dateAddedOn.setText(babyItems.getDateAddedOn());
        holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorCard));
    }


    @Override
    public int getItemCount() {
        return babyItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView item;
        public TextView quantity;
        public TextView color;
        public TextView size;
        public TextView dateAddedOn;
        public ImageButton updateImgBtn;
        public ImageButton deleteImgBtn;
        public CardView cardView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.babyItemListRow_textView);
            quantity = itemView.findViewById(R.id.quantityListRow_textView);
            color = itemView.findViewById(R.id.colorListRow_textView);
            size = itemView.findViewById(R.id.sizeListRow_textView);
            dateAddedOn = itemView.findViewById(R.id.dateAddedOnListRow_textView);
            updateImgBtn = itemView.findViewById(R.id.update_imageButton);
            deleteImgBtn = itemView.findViewById(R.id.delete_imageButton);
            cardView= itemView.findViewById(R.id.cardViewBabyItem_cardView);
            updateImgBtn.setOnClickListener(this);
            deleteImgBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position;
            position = getAdapterPosition();
            BabyItems babyItems = babyItemsList.get(position);
            switch (view.getId()){

                case R.id.update_imageButton:

                    updateItem(babyItems);
                    break;

                case R.id.delete_imageButton:

                    deleteItem(babyItems.getId());
                    break;
            }
        }

        private void deleteItem(final int id){

            builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.delete_confirmation_alertdialog,null);
            Button noButton = view.findViewById(R.id.confirmNo_button);
            Button yesButton = view.findViewById(R.id.confirmYes_button);
            cardView = view.findViewById(R.id.confirm_cardView);
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorCard));

            builder.setView(view);
            alertDialog = builder.create();
            alertDialog.show();

            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHandler databaseHandler = new DatabaseHandler(context);
                    databaseHandler.deleteBabyItem(id);
                    babyItemsList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    alertDialog.dismiss();
                }
            });
        }

        private void updateItem(final BabyItems newBabyItems){
            //Todo: to populate the current details in a update dialog
            //final BabyItems babyItems = babyItemsList.get(getAdapterPosition());
            builder = new AlertDialog.Builder(context);
            View view  = LayoutInflater.from(context).inflate(R.layout.popup,null);

             final EditText babyItemName;
             final EditText quantity;
             final EditText color;
             final EditText size;
             Button saveBtn;
             CardView cardView;
             final TextView title_textView;

            babyItemName = view.findViewById(R.id.babyItemName_editText);
            quantity = view.findViewById(R.id.quantity_EditText);
            color = view.findViewById(R.id.color_EditText);
            size = view.findViewById(R.id.size_EditText);
            saveBtn = view.findViewById(R.id.saveButton_Button);
            saveBtn.setText(R.string.update);
            cardView = view.findViewById(R.id.popup_cardView);
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorCard));

            title_textView = view.findViewById(R.id.titleEnterDetails_textView);
            title_textView.setText(R.string.edit_details);

            babyItemName.setText(newBabyItems.getItemName());
            quantity.setText(String.valueOf(newBabyItems.getQuantity()));
            color.setText(newBabyItems.getColor());
            size.setText(String.valueOf(newBabyItems.getSize()));

            builder.setView(view);
            alertDialog = builder.create();
            alertDialog.show();

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHandler databaseHandler = new DatabaseHandler(context);

                    //update Items
                    newBabyItems.setItemName(babyItemName.getText().toString());
                    newBabyItems.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    newBabyItems.setColor(color.getText().toString());
                    newBabyItems.setSize(Integer.parseInt(size.getText().toString()));

                    if(!babyItemName.getText().toString().isEmpty()
                    && !quantity.getText().toString().isEmpty()
                    && !color.getText().toString().isEmpty()
                    && !size.getText().toString().isEmpty()){
                        databaseHandler.updateBabyItem(newBabyItems);
                        notifyItemChanged(getAdapterPosition(),newBabyItems);
                    }
                    else{
                        Snackbar.make(view,"Enter Details.",Snackbar.LENGTH_SHORT).setTextColor(context.getResources().getColor(R.color.colorButtonDelete)).setAction("Action",null).show();
                    }

                    alertDialog.dismiss();
                }
            });
        }
    }
}
