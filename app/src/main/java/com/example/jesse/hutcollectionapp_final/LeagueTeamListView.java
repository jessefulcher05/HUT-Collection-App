package com.example.jesse.hutcollectionapp_final;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LeagueTeamListView extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_team_list_view);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


    }

    @Override
    protected void onStart() {

        super.onStart();

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

     // Preparing the list data
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("NHL");
        listDataHeader.add("AHL");
        listDataHeader.add("SHL");

        // Adding child data
        List<String> nhl = new ArrayList<String>();
        nhl.add("Anaheim Ducks");
        nhl.add("Arizona Coyotes");
        //nhl.add("Boston Bruins");

        List<String> ahl = new ArrayList<String>();
        ahl.add("The Conjuring");
        ahl.add("Despicable Me 2");
        ahl.add("Turbo");
        ahl.add("Grown Ups 2");
        ahl.add("Red 2");
        ahl.add("The Wolverine");

        List<String> shl = new ArrayList<String>();
        shl.add("2 Guns");
        shl.add("The Smurfs 2");
        shl.add("The Spectacular Now");
        shl.add("The Canyons");
        shl.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), nhl); // Header, Child data
        listDataChild.put(listDataHeader.get(1), ahl);
        listDataChild.put(listDataHeader.get(2), shl);

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                //do stuff when it takes you to ducks button press
                Intent myIntent = new Intent(LeagueTeamListView.this, TeamCheckList.class); //creats intent to go to next page

                //gets string value of the text view so it knows which items to pull
                String text = ((TextView)(v.findViewById(R.id.lblListItem))).getText().toString();

                myIntent.putExtra("KEY",text); //passes in text on the button
                LeagueTeamListView.this.startActivity(myIntent); //starts new page

                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {


//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_league_team_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


