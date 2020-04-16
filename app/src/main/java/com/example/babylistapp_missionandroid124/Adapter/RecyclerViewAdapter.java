package com.example.babylistapp_missionandroid124.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babylistapp_missionandroid124.Model.BabyItems;
import com.example.babylistapp_missionandroid124.R;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<BabyItems> babyItemsList;

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
        holder.quantity.setText(babyItems.getQuantity());
        holder.color.setText(babyItems.getColor());
        holder.size.setText(babyItems.getSize());
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

            item = itemView.findViewById(R.id.item_textView);
            quantity = itemView.findViewById(R.id.quantity_textView);
            color = itemView.findViewById(R.id.color_textView);
            size = itemView.findViewById(R.id.size_textView);
            dateAddedOn = itemView.findViewById(R.id.dateAddedOn_textView);
            updateImgBtn = itemView.findViewById(R.id.update_imageButton);
            deleteImgBtn = itemView.findViewById(R.id.delete_imageButton);
            cardView= itemView.findViewById(R.id.cardViewBabyItem_cardView);
            updateImgBtn.setOnClickListener(this);
            deleteImgBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.update_imageButton:

                    break;

                case R.id.delete_imageButton:

                    break;
            }
        }
    }
}
