package com.example.jesse.hutcollectionapp_final;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TeamCheckList extends ActionBarActivity {

    //title of page displays team name
    TextView title;
    //listview variable
    ListView lv;
    //team Name variable
    String teamName;
    //array list of my database items
    ArrayList<Player> playerItems;
    //update button
    Button updateButton;
    //playerdb
    PlayerDB db = new PlayerDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_check_list);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            //passes in text on button to equal teamName
            teamName = extras.getString("KEY");

            //sets title to team name
            setTitle(teamName);
        }

        //turns on back navigation in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ////////////////database stuff////////////
        lv = (ListView) findViewById(R.id.listView1);

        //find all players on a team by teamName
        playerItems = db.getPlayerByTeam(teamName);

        //custom adapter which puts my array list into list view
        final PlayerListViewAdapter adapter = new PlayerListViewAdapter(this, playerItems);
        lv.setAdapter(adapter);

        //updateButton stuff
        updateButton = (Button)findViewById(R.id.buttonSave);
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                for(int i = 0;i<adapter.playerItems.size();i++)
                {
                    int newID = adapter.playerItems.get(i).getId();
                    //int newId = playerItems.get(i).id;
                    int newStatus = adapter.playerItems.get(i).value;
                    int newPricePaid = adapter.playerItems.get(i).pricePaid;
                    int newPriceSold = adapter.playerItems.get(i).priceSold;

                    //long rowId, int status, int pricePaid, int priceSold
                    db.updatePlayer(newID,newStatus,newPricePaid,newPriceSold);

                }

                //hides keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                //displays message telling users its been updated
                Context context = getApplicationContext();
                Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show();
            }
        });

        if(teamName.equalsIgnoreCase("Anaheim Ducks"))
        {
            //if team name is ducks set icon to ducks
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ducks);
        }
        else if(teamName.equalsIgnoreCase("Arizona Coyotes"))
        {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.coyotes);
        }
        else if(teamName.equalsIgnoreCase("Boston Bruins"))
        {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.bruins);
        }
        else if(teamName.equalsIgnoreCase("Buffalo Sabres"))
        {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.sabres);
        }
        else if(teamName.equalsIgnoreCase("Calgary Flames"))
        {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.flames);
        }


    }



    //method that checks if data has been saved and returns status
    public Boolean checkForSave(){

        Boolean isSaved = true;

        //custom adapter which puts my array list into list view
        final PlayerListViewAdapter adapter = new PlayerListViewAdapter(this, playerItems);

        //gets new player list according to what is in the database.
        ArrayList<Player> newPlayerItems = db.getPlayerByTeam(teamName);

        for(int i = 0;i<adapter.playerItems.size();i++)
        {
            int newStatus = adapter.playerItems.get(i).value;
            int newPricePaid = adapter.playerItems.get(i).pricePaid;
            int newPriceSold = adapter.playerItems.get(i).priceSold;

            //if any of the items are different then what data has set save status to 1
            if(newStatus != newPlayerItems.get(i).value || newPricePaid != newPlayerItems.get(i).pricePaid || newPriceSold != newPlayerItems.get(i).priceSold)
            {
                isSaved = false;
                break;
            }
        }
        return isSaved;
    }

    //method that displays message if on exit data is not saved.
    public void displayMessageIfNotSaved(){

        if(checkForSave() == false)
        {
            (new AlertDialog.Builder(this))
                    .setTitle("UNSAVED CHANGES")
                    .setMessage("You have not saved all changes. Do you want to continue without saving?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish(); //closes action and goes back to main
                        }
                    })
                    .setNegativeButton("No", null)
                    .show(); //stays on current action allowing user to "save"
        }
        else{
            finish();
        }

    }

    @Override
    public void onBackPressed() {

        //displays message on back press if items haven't been saved
        displayMessageIfNotSaved();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_check_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //calls method that displays message on back only if items have not been saved
        displayMessageIfNotSaved();

        //not sure why this works. Research.
        return true;
    }

}

