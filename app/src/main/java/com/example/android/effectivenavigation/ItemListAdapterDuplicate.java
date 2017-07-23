package com.example.android.effectivenavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.effectivenavigation.database.ProductsEntity;

import java.util.ArrayList;

/**
 * Created by lokeshmutyala on 17-06-2017.
 */

public class ItemListAdapterDuplicate extends ArrayAdapter<ProductsEntity> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    public ItemListAdapterDuplicate(Context context, int resource, ArrayList<ProductsEntity> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;

    }

    private static class ViewHolder {
        TextView name;
        TextView price;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        String Name=getItem(position).getProduct_Name();
        Double Price=getItem(position).getMRP();
//        final int Quantity=getItem(position).getQuantity();
//        int No=getItem(position).getNo();
//        final item Item=getItem(position);//new item(Name,Price,Quantity,No);
          final View result;

        final ViewHolder holder;

        if(convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(mContext);
            convertView=layoutInflater.inflate(mResource,parent,false);
            holder=new ViewHolder();
            holder.name=(TextView)convertView.findViewById(R.id.itemname);
            holder.price=(TextView)convertView.findViewById(R.id.itemprice);
//            holder.quantity=(TextView)convertView.findViewById(R.id.quantity);
//            holder.no=(TextView)convertView.findViewById(R.id.no);
            result=convertView;
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
            result=convertView;
        }
        lastPosition = position;
        holder.name.setText(Name);
        holder.price.setText("MRP "+String.valueOf(Price));
//        holder.no.setText(String.valueOf(position+1));
//        holder.quantity.setText(String.valueOf(Item.getQuantity()));

        return convertView;
    }

}
