package com.example.dudas.sushi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<Profile> profi;

    public CartAdapter(Context context, List<Profile> profi) {
        this.context = context;
        this.profi = profi;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.cart_item_layout,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {
        holder.txt_amount.setNumber(String.valueOf(profi.get(position).amount));
        //holder.txt_price.setText(new );
        holder.txt_product_name.setText(profi.get(position).getNazwa());
        //holder.txt_sushi_size.
        holder.txt_amount.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Profile profiles = profi.get(position);
                profiles.amount = newValue;
                //Profile.cart
            }
        });
    }

    @Override
    public int getItemCount() {
        return profi.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        TextView txt_product_name, txt_sushi_size, txt_price;
        ElegantNumberButton txt_amount;


        public CartViewHolder(View itemView) {
            super(itemView);

            txt_amount = itemView.findViewById(R.id.txt_amount);
            txt_product_name = itemView.findViewById(R.id.txt_product_name);
            txt_sushi_size = itemView.findViewById(R.id.txt_sushi_size);
            txt_price  = itemView.findViewById(R.id.txt_price);
        }
    }
}
