package com.example.jesse.hutcollectionapp_final;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class PlayerListViewAdapter extends ArrayAdapter {

    ArrayList<Player> playerItems = new ArrayList<>();
    Context context;


    public PlayerListViewAdapter(Context context, ArrayList<Player> resource) {
        super(context, R.layout.player_row, resource);
// TODO Auto-generated constructor stub
        this.context = context;
        this.playerItems = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

// TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.player_row, parent, false);

        //inserts name into the team textview
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        //sets name
        name.setText(playerItems.get(position).getName());

        //inserts position into the position textview
        TextView playerPosition = (TextView) convertView.findViewById(R.id.textPosition);
        //sets position
        playerPosition.setText(playerItems.get(position).getPosition());

        //inserts player type into the type textview
        TextView playerType = (TextView) convertView.findViewById(R.id.textType);
        //sets type
        playerType.setText(playerItems.get(position).getType());

        //inserts price paid into
        EditText pricePaidValue = (EditText) convertView.findViewById(R.id.pricePaid);
        //converts to string and sets value
        pricePaidValue.setText("" + playerItems.get(position).getPricePaid());

        //sets text watcher to this Edit Text
        pricePaidValue.addTextChangedListener(new pricePaidTextWatcher(convertView, position));


        //inserts price sold into
        EditText priceSoldValue = (EditText) convertView.findViewById(R.id.priceSold);
        //converts to string and sets value
        priceSoldValue.setText("" + playerItems.get(position).getPriceSold());
        //sets text watcher to this Edit Text
        priceSoldValue.addTextChangedListener(new priceSoldTextWatcher(convertView, position));

        //inserts team into the team textview
        //not sure if we'll display team name. Just used for reference.

        //inserts cb status into checkbox
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);


        //checks status of check box to determin if checked or not
        if (playerItems.get(position).getValue() == 1) {
            cb.setChecked(true);
        } else {
            cb.setChecked(false);
        }
        //sets the onclick listener of the checkbox
        cb.setOnClickListener(new View.OnClickListener() {

            //on click if cb is checked give value set to 1 else set it to 0
            @Override
            public void onClick(View v) {

                CheckBox cb = (CheckBox) v;

                if (cb.isChecked())
                {
                    playerItems.get(position).setValue(1);
                }
                else
                {
                    playerItems.get(position).setValue(0);
                }
            }
        });


        return convertView;

    }

    //text watcher that sees when value is updated on pricePaid element
    private class pricePaidTextWatcher implements TextWatcher {

        int newPosition;
        private View view;

        //passes in position in this array
        private pricePaidTextWatcher(View view, int newPosition) {

            this.view = view;
            this.newPosition = newPosition;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //do nothing

        }

        @Override
        public void afterTextChanged(Editable s) {


            String pricePaidString = s.toString();
            int pricePaidInt = pricePaidString.equals("") ? 0:Integer.valueOf(pricePaidString);

            //EditText et = (EditText) view.findViewById(R.id.pricePaid);
            playerItems.get(newPosition).setPricePaid(pricePaidInt);

        }
    }

    //Text Watcher watches for when price changes on a priceSold item
    private class priceSoldTextWatcher implements TextWatcher {

        private View view;
        int newPosition;

        //passes in position in this array
        private priceSoldTextWatcher(View view, int newPosition) {
            this.view = view;
            this.newPosition = newPosition;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //do nothing
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //do nothing
        }
        @Override
        public void afterTextChanged(Editable s) {

            String priceSoldString = s.toString();
            int priceSoldInt = priceSoldString.equals("") ? 0:Integer.valueOf(priceSoldString);

            //EditText et = (EditText) view.findViewById(R.id.pricePaid);
            playerItems.get(newPosition).setPriceSold(priceSoldInt);

        }
    }


}

