package com.example.android.effectivenavigation;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lokeshmutyala on 17-06-2017.
 */

public class ItemListAdapter extends ArrayAdapter<item> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    public ItemListAdapter( Context context, int resource, ArrayList<item> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;

    }

    private static class ViewHolder {
        TextView name;
        EditText price;
        TextView quantity;
        TextView no;
        EditText item_discount;
        ImageButton plus;
        ImageButton minus;
        ImageButton button;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        String Name=getItem(position).getName();
        Double Price=getItem(position).getPrice();
        final int Quantity=getItem(position).getQuantity();
        int No=getItem(position).getNo();
        final item Item=getItem(position);//new item(Name,Price,Quantity,No);
        final View result;

        final ViewHolder holder;

        if(convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(mContext);
            convertView=layoutInflater.inflate(mResource,parent,false);
            holder=new ViewHolder();
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.price=(EditText) convertView.findViewById(R.id.price);
            holder.button=(ImageButton)convertView.findViewById(R.id.delete);
            holder.quantity=(TextView)convertView.findViewById(R.id.quantity);
            holder.minus=(ImageButton)convertView.findViewById(R.id.minus);
            holder.plus=(ImageButton)convertView.findViewById(R.id.plus);
            holder.no=(TextView)convertView.findViewById(R.id.no);
            holder.item_discount=(EditText)convertView.findViewById(R.id.item_discount);
            result=convertView;
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
            result=convertView;
        }
        lastPosition = position;
        holder.name.setText(Item.getName());
        holder.price.setText(String.valueOf(Item.getPrice()));
        holder.no.setText(String.valueOf(position+1));
        holder.quantity.setText(String.valueOf(Item.getQuantity()));
//change Item.get to simply getItem if needed

        holder.item_discount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    Item.setaDouble(Double.parseDouble(s.toString()));

                    if(mContext instanceof Billing){
                        ((Billing)mContext).setTotal();
                    }

                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Item.getQuantity()==1){
                    //getItem(position).setQuantity(0);
                    Item.setQuantity(0);
                    holder.quantity.setText("0");
                    holder.minus.setClickable(false);
                }
                else if(Item.getQuantity()>1)//Integer.parseInt(holder.quantity.getText().toString())>1)
                {
                    //getItem(position).setQuantity(getItem(position).getQuantity()-1);
                    Item.setQuantity(Item.getQuantity()-1);
                    holder.quantity.setText(String.valueOf(Item.getQuantity()));//getItem(position).getQuantity()));

                }

                if(mContext instanceof Billing){
                    ((Billing)mContext).setTotal();
                }

                //holder.quantity.setText(String.valueOf(getItem(position).getQuantity()));

            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Item.getQuantity()==0){
                    //getItem(position).setQuantity(1);
                    Item.setQuantity(1);
                    holder.quantity.setText("1");
                    holder.minus.setClickable(true);
                }
                else{

                    //getItem(position).setQuantity(getItem(position).getQuantity()+1);
                    Item.setQuantity(Item.getQuantity()+1);
                    holder.quantity.setText(String.valueOf(Item.getQuantity()));//getItem(position).getQuantity()));
                }


                if(mContext instanceof Billing){
                    ((Billing)mContext).setTotal();
                }
                //holder.quantity.setText(String.valueOf(getItem(position).getQuantity()));
            }
        });


        holder.button.setTag(position);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index=(int) v.getTag();
                remove(getItem(index));
                notifyDataSetChanged();

                if(mContext instanceof Billing){
                    ((Billing)mContext).setTotal();
                }
            }
        });
        //total+=Integer.parseInt(holder.price.getText().toString().substring(4))*Integer.parseInt(holder.quantity.getText().toString());
        //total+=Item.getPrice()*Item.getQuantity();
        return convertView;
    }

}
