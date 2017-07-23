package com.example.android.effectivenavigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lokeshmutyala on 19-07-2017.
 */

public class ItemListAdapterRecycler extends RecyclerView.Adapter<ItemListAdapterRecycler.MyViewHolder> {

    private List<item> itemList;
    private Context mContext;
    public View mFocusedView;

    private AlphaAnimation buttonClick=new AlphaAnimation(1F,0.2F);
    public ItemListAdapterRecycler(List<item> itemList, Context context) {
        this.itemList = itemList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        final item Item = itemList.get(position);
        holder.name.setText(Item.getName());
        holder.price.setText(String.valueOf(Item.getPrice()));
        holder.no.setText(String.valueOf(position + 1));
        holder.quantity.setText(String.valueOf(Item.getQuantity()));
        if (Item.getaDouble() == 0.0) {
            holder.item_discount.setText("0");
        } else {
            holder.item_discount.setText(String.valueOf(Item.getaDouble()));
        }

        holder.discountChangeListener.setActive(false);
        holder.item_discount.setTag(Item);

        holder.item_discount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if (hasFocus) {
                    mFocusedView = view;
                    holder.discountChangeListener.setActive(true);
                }
            }
        });

//        holder.item_discount.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
////                if (!s.toString().isEmpty()) {
//                try {
//                    Item.setaDouble(Double.parseDouble(s.toString()));
//                    //itemList.get(position).setaDouble(Double.parseDouble(s.toString()));
//                    //notifyDataSetChanged();
//                    if (mContext instanceof Billing) {
//                        ((Billing) mContext).setTotal();
//                    }
//
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
////                }
//            }
//
//        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                if (Item.getQuantity() == 1) {
                    //getItem(position).setQuantity(0);
                    Item.setQuantity(0);
                    holder.quantity.setText("0");
                    holder.minus.setClickable(false);
                } else if (Item.getQuantity() > 1)//Integer.parseInt(holder.quantity.getText().toString())>1)
                {
                    //getItem(position).setQuantity(getItem(position).getQuantity()-1);
                    Item.setQuantity(Item.getQuantity() - 1);
                    holder.quantity.setText(String.valueOf(Item.getQuantity()));//getItem(position).getQuantity()));

                }

                if (mContext instanceof Billing) {
                    ((Billing) mContext).setTotal();
                }

                //holder.quantity.setText(String.valueOf(getItem(position).getQuantity()));

            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                if (Item.getQuantity() == 0) {
                    //getItem(position).setQuantity(1);
                    Item.setQuantity(1);
                    holder.quantity.setText("1");
                    holder.minus.setClickable(true);
                } else {

                    //getItem(position).setQuantity(getItem(position).getQuantity()+1);
                    Item.setQuantity(Item.getQuantity() + 1);
                    holder.quantity.setText(String.valueOf(Item.getQuantity()));//getItem(position).getQuantity()));
                }


                if (mContext instanceof Billing) {
                    ((Billing) mContext).setTotal();
                }
                //holder.quantity.setText(String.valueOf(getItem(position).getQuantity()));
            }
        });


        holder.button.setTag(position);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                int index = (int) v.getTag();
                itemList.remove(index);
                notifyDataSetChanged();
                notifyItemRemoved(index);
                notifyItemRangeChanged(index, itemList.size());

                if (mContext instanceof Billing) {
                    ((Billing) mContext).setTotal();
                }
            }
        });


    }

    class DiscountChangeListener implements TextWatcher {

        private boolean isActive;

        private void setActive(boolean isActive) {
            this.isActive = isActive;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("check changed state","changed:"+s.toString());
            if (isActive && mFocusedView != null) {
                item itemDetails = (item) mFocusedView.getTag();
                try {
                    Double x=0.0;
                    if(!s.toString().isEmpty()){
                         x = Double.parseDouble(s.toString());
                    }

                    if (x <= itemDetails.getPrice()) {
                        itemDetails.setaDouble(x);
                        //itemList.get(position).setaDouble(Double.parseDouble(s.toString()));
                        //notifyDataSetChanged();
                        if (mContext instanceof Billing) {
                            ((Billing) mContext).setTotal();
                        }
                        Log.e(itemDetails.getName(), "value: " + itemDetails.getaDouble() + "");
                    }else{
                        Toast.makeText(mContext,"discount can't exceed MRP",Toast.LENGTH_LONG).show();
                        itemDetails.setaDouble(0.0);
                        notifyDataSetChanged();
                    }
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public EditText price;
        public TextView quantity;
        public TextView no;
        public EditText item_discount;
        public ImageButton plus;
        public ImageButton minus;
        public ImageButton button;
        public DiscountChangeListener discountChangeListener;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            price = (EditText) view.findViewById(R.id.price);
            button = (ImageButton) view.findViewById(R.id.delete);
            quantity = (TextView) view.findViewById(R.id.quantity);
            minus = (ImageButton) view.findViewById(R.id.minus);
            plus = (ImageButton) view.findViewById(R.id.plus);
            no = (TextView) view.findViewById(R.id.no);
            item_discount = (EditText) view.findViewById(R.id.item_discount);
            discountChangeListener = new DiscountChangeListener();
            item_discount.addTextChangedListener(discountChangeListener);
        }
    }

}
