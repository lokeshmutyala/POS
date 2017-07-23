package com.example.android.effectivenavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lokeshmutyala on 17-06-2017.
 */

public class ItemListAdapterInvoice extends ArrayAdapter<item> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    public ItemListAdapterInvoice(Context context, int resource, ArrayList<item> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;

    }

    private static class ViewHolder {
        TextView name;
        TextView price;
        TextView quantity;
        TextView no;
        TextView discount;

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
            holder.price=(TextView)convertView.findViewById(R.id.price);
            holder.quantity=(TextView)convertView.findViewById(R.id.quantity);
            holder.no=(TextView)convertView.findViewById(R.id.no);
            holder.discount=(TextView)convertView.findViewById(R.id.invoice_discount);
            result=convertView;
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
            result=convertView;
        }
        lastPosition = position;
        holder.name.setText(Item.getName());
        holder.price.setText("MRP      :₹"+String.valueOf(Item.getPrice()));
        holder.no.setText(String.valueOf(position+1));
        holder.quantity.setText(String.valueOf(Item.getQuantity()));
        holder.discount.setText(String.valueOf(Item.getaDouble()));
        return convertView;
    }

}
