package com.example.jesse.hutcollectionapp_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class PlayerDB extends SQLiteOpenHelper {

    /** Database name */
    private static String DBNAME = "PlayerDB";

    /** Version number of the database */
    private static int VERSION = 1;

    /** Field 1 of the table cust_master, which is the primary key */
    public static final String KEY_ROW_ID = "_id";

    /** Field 2 of the table cust_master, stores the customer code */
    public static final String KEY_NAME = "name";

    /** Field 3 of the table cust_master, stores the customer code */
    public static final String KEY_TEAM = "team";

    /** Field 4 of the table cust_master, stores the customer name */
    public static final String KEY_STATUS = "status";

    /** Field 5 of the table cust_master, stores the customer name */
    public static final String KEY_POSITION = "position";

    /** Field 6 of the table cust_master, stores the customer name */
    public static final String KEY_TYPE = "type";

    /** Field 7 of the table cust_master, stores the customer name */
    public static final String KEY_PRICEPAID = "pricePaid";

    /** Field 8 of the table cust_master, stores the customer name */
    public static final String KEY_PRICESOLD = "priceSold";

    /** A constant, stores the the table name */
    private static final String DATABASE_TABLE = "cust_master";

    /** An instance variable for SQLiteDatabase */
    private SQLiteDatabase mDB;

    public PlayerDB(Context context) {
        super(context, DATABASE_TABLE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql =     "create table "+ DATABASE_TABLE + " ( "
                + KEY_ROW_ID + " integer primary key autoincrement , "
                + KEY_NAME + " text  , "
                + KEY_TEAM + " text  , "
                + KEY_STATUS + "  text  , "
                + KEY_POSITION + "  text  , "
                + KEY_TYPE + "  text  , "
                + KEY_PRICEPAID + "  text  , "
                + KEY_PRICESOLD + "  text  ) ";

        //create table sql
        db.execSQL(sql);

        //creates arraylist of raw player list
        ArrayList<String> rawPlayerList = new ArrayList<>();
        //anaheim ducks
        rawPlayerList.add("values ('Anaheim Ducks','R. Getzlaf','0','C','PWF','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','C. Perry','0','RW','SNP','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','R. Kesler','0','C','TWF','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','F. Andersen','0','G','HYB','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','A. Cogliano','0','LW','TWF','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','J. Gibson','0','G','HYB','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','C. Fowler','0','LD','OFD','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','F. Beauchemin','0','LD','OFD','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','H. Lindhold','0','LD','TWD','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','D. Smith-Pelly','0','RW','PWF','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','K. Palmieri','0','RW','TWF','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','J. Silfverbert','0','LW','SNP','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','E. Etem','0','RW','SNP','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','M. Beleskey','0','LW','GRN','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','N. Thompson','0','C','TWF','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','B. Lovejoy','0','RD','TWD','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','B. Alleny','0','LD','DFD','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','S. Vatanen','0','RD','OFD','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','P. Maroon','0','LW','PWF','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','M. Fistric','0','LD','DFD','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','C. Stoner','0','LD','DFD','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','S. Souray','0','LD','TWD','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','T. Jackman','0','RW','GRN','0','0' )");
        rawPlayerList.add("values ('Anaheim Ducks','R. Rakell','0','RW','SNP','0','0' )");

        //Arizona coyotes
        rawPlayerList.add("values ('Arizona Coyotes','M. Smith','0','C','2WF','10','10' )");
        rawPlayerList.add("values ('Arizona Coyotes','L. Jones','0','C','2WF','10','10' )");

        //loops through all palyers in array and adds them to the db
        for(int i = 0; i <rawPlayerList.size();i++)
        {
            sql = "insert into " + DATABASE_TABLE + " ( " + KEY_TEAM + "," + KEY_NAME + "," + KEY_STATUS + "," + KEY_POSITION + "," + KEY_TYPE + "," + KEY_PRICEPAID + "," + KEY_PRICESOLD +" ) "
                    + rawPlayerList.get(i);

            //execute each line to add the players
            db.execSQL(sql);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        // Create tables again
        onCreate(db);

    }

    //returns all customers as a cursor
    public Cursor getAllCustomers() {
        return mDB.query(DATABASE_TABLE, new String[]{KEY_ROW_ID, KEY_NAME, KEY_TEAM, KEY_STATUS ,
                        KEY_POSITION, KEY_TYPE, KEY_PRICEPAID, KEY_PRICESOLD},
                null, null, null, null,
                KEY_NAME + " asc ");
    }

    //returns item from db at whatever id you enter
    public Player getPlayerById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ROW_ID,
                        KEY_NAME, KEY_TEAM ,KEY_STATUS ,KEY_POSITION, KEY_TYPE, KEY_PRICEPAID,
                        KEY_PRICESOLD}, KEY_ROW_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Player player = new Player(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getString(4),cursor.getString(5),
                Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)));
        // return contact
        return player;
    }

    //returns item from db at whatever team you enter
    public ArrayList getPlayerByTeam(String team) {

        ArrayList<Player> teamList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ROW_ID,
                        KEY_NAME, KEY_TEAM ,KEY_STATUS,KEY_POSITION, KEY_TYPE, KEY_PRICEPAID, KEY_PRICESOLD}, KEY_TEAM + "=?",
                new String[]{String.valueOf(team)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        //loops through the cursor and creates player out of each line
        for (int i = 0; i < cursor.getCount(); i++)
        {
            Player player = new Player(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getString(4),cursor.getString(5),
                    cursor.getInt(6), cursor.getInt(7));

            //adds the player that is created from the cursor to the teamList
            teamList.add(player);

            //!important! moves to the next cursor after adding the other one to the teamList
            cursor.move(1);
        }

        return teamList;
    }

    public boolean updatePlayer(int rowId, int status, int pricePaid, int priceSold) {

        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues args = new ContentValues();
        args.put(KEY_STATUS, status);
        args.put(KEY_PRICEPAID, pricePaid);
        args.put(KEY_PRICESOLD, priceSold);

        return db.update(DATABASE_TABLE, args, KEY_ROW_ID + "=" + rowId, null) > 0;
    }


}

