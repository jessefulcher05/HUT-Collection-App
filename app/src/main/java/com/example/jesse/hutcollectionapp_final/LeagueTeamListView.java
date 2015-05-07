package com.example.jesse.hutcollectionapp_final;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LeagueTeamListView extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private Vibrator myVib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_team_list_view);

        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //sets title
        setTitle("Collection Checklists");
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
        nhl.add("Boston Bruins");
        nhl.add("Buffalo Sabres");
        nhl.add("Calgary Flames");
        nhl.add("Carolina Hurricanes");
        nhl.add("Chicago Blackhawks");
        nhl.add("Colorado Avalanche");
        nhl.add("Columbus Blue Jackets");
        nhl.add("Dallas Stars");
        nhl.add("Detroit Red Wings");
        nhl.add("Edmonton Oilers");
        nhl.add("Florida Panthers");
        nhl.add("Los Angeles Kings");
        nhl.add("Minnesota Wild");
        nhl.add("Montreal Canadiens");
        nhl.add("Nashville Predators");
        nhl.add("New Jersey Devils");
        nhl.add("New York Islanders");
        nhl.add("New York Rangers");
        nhl.add("Ottawa Senators");
        nhl.add("Philadelphia Flyers");
        nhl.add("Pittsburgh Penguins");
        nhl.add("San Jose Sharks");
        nhl.add("St. Louis Blues");
        nhl.add("Tampa Bay Lightning");
        nhl.add("Toronto Maple Leafs");
        nhl.add("Vancouver Canucks");
        nhl.add("Washington Capitals");
        nhl.add("Winnipeg Jets");

        List<String> ahl = new ArrayList<String>();
        ahl.add("Adirondack Flames");
        ahl.add("Albany Devils");
        ahl.add("Binghamton Senators");
        ahl.add("Bridgeport Sound Tigers");
        ahl.add("Charlotte Checkers");
        ahl.add("Chicago Wolves");

        List<String> shl = new ArrayList<String>();
        shl.add("Brynäs IF");
        shl.add("Djurgårdens Hockey");
        shl.add("Färjestad BK");
        shl.add("Frölunda Indians");
        shl.add("HV71");

        listDataChild.put(listDataHeader.get(0), nhl); // Header, Child data
        listDataChild.put(listDataHeader.get(1), ahl);
        listDataChild.put(listDataHeader.get(2), shl);

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                //vibrates leagueheaderbackground on click
                myVib.vibrate(50);

                //do stuff when it takes you to ducks leagueheaderbackground press
                Intent myIntent = new Intent(LeagueTeamListView.this, TeamCheckList.class); //creats intent to go to next page

                //gets string value of the text view so it knows which items to pull
                String text = ((TextView)(v.findViewById(R.id.lblListItem))).getText().toString();
                int teamId = childPosition;

                myIntent.putExtra("TEAMNAME",text); //passes in text on the leagueheaderbackground
                myIntent.putExtra("ID", teamId); //passes in team id in list
                LeagueTeamListView.this.startActivity(myIntent); //starts new page

                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

                //vibrates leagueheaderbackground on click
                myVib.vibrate(50);

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
        // automatically handle clicks on the Home/Up leagueheaderbackground, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


