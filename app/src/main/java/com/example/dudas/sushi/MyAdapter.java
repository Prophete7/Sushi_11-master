package com.example.dudas.sushi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Profile> profiles;

    Button btn_add_to_cart;
    


    public MyAdapter(Context c,ArrayList<Profile> p)
    {
        context=c;
        profiles=p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }
   // String cenaString = Integer.toString(profiles.get(int position).getCena);
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.nazwa.setText(profiles.get(position).getNazwa());
        holder.opis.setText(profiles.get(position).getOpis());
        holder.cena.setText(String.valueOf(profiles.get(position).getCena()));

        holder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddToCartDialog(position);
            }
        });
    }

    private void showAddToCartDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.add_to_cart_layout, null);

        final ElegantNumberButton txt_count = (ElegantNumberButton)itemView.findViewById(R.id.txt_count);
        TextView txt_product_dialog = (TextView)itemView.findViewById(R.id.txt_cart_product_name);
        EditText edt_comment = (EditText)itemView.findViewById(R.id.edt_comment);
        RadioButton rdt_sizeS = (RadioButton)itemView.findViewById(R.id.rdi_sizeS);
        RadioButton rdt_sizeM = (RadioButton)itemView.findViewById(R.id.rdi_sizeM);
        RadioButton rdt_sizeL = (RadioButton)itemView.findViewById(R.id.rdi_sizeL);
        
        rdt_sizeS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                
                if (b)
                    Profile.sizeOfCup=0;
            }
        });

        rdt_sizeM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b)
                    Profile.sizeOfCup=1;
            }
        });

        rdt_sizeL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b)
                    Profile.sizeOfCup=2;
            }
        });

        txt_product_dialog.setText(profiles.get(position).getNazwa());
        builder.setView(itemView);
        /*builder.setNegativeButton("Dodaj do koszyka", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (Profile.sizeOfCup == -1){
                    Toast.makeText(context, "Wybierz ilosc sztuk", Toast.LENGTH_SHORT).show();
                    return;
                }
                showConfirmDialog(position,txt_count.getNumber(),Profile.sizeOfCup);
                dialogInterface.dismiss();
            }
        });*/
        builder.show();

    }

    private void showConfirmDialog(int position, String number, int sizeOfCup) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View itemView = LayoutInflater.from(context).inflate(R.layout.confirm_add_to_cart_layout, null);
        TextView txt_product_dialog = (TextView)itemView.findViewById(R.id.txt_cart_product_name);
        TextView txt_product_price = (TextView)itemView.findViewById(R.id.txt_cart_product_price);
        TextView txt_size = (TextView)itemView.findViewById(R.id.txt_selected_size);

        /*txt_product_dialog.setText(new StringBuilder(profiles.get(position).getNazwa()).append(" x")
        .append(number).append(Profile.sizeOfCup == 0 ? "10" : "15").toString());

        double price = (Double.parseDouble(profiles.get(position).getCena())*Double)*/
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView nazwa,opis,cena;
        Button btn_add_to_cart;
        public MyViewHolder(View itemView) {
            super(itemView);
            nazwa = (TextView) itemView.findViewById(R.id.nazwa);
            opis = (TextView) itemView.findViewById(R.id.opis);
            cena = (TextView) itemView.findViewById(R.id.cena);

            btn_add_to_cart = (Button)itemView.findViewById(R.id.btn_add_to_cart);
        }
    }

}
