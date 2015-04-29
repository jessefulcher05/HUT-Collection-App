package com.example.jesse.hutcollectionapp_final;


public class Player{
    int id;
    String name;
    String team;
    int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */
    String position;
    String type;
    int pricePaid;
    int priceSold;

    Player(int id, String name, String team, int value, String position, String type, int pricePaid, int priceSold){
        this.name = name;
        this.value = value;
        this.id = id;
        this.team = team;
        this.position = position;
        this.type = type;
        this.pricePaid = pricePaid;
        this.priceSold = priceSold;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getValue(){
        return this.value;
    }
    public void setValue(int value){
        this.value = value;
    }

    public int getId(){
        return this.id;
    }

    public String getTeam(){
        return this.team;
    }
    public String getPosition(){
        return this.position;
    }
    public String getType(){
        return this.type;
    }

    public int getPricePaid(){
        return this.pricePaid;
    }
    public void setPricePaid(int pricePaid){
        this.pricePaid = pricePaid;
    }

    public int getPriceSold(){
        return this.priceSold;
    }
    public void setPriceSold(int priceSold){
        this.priceSold = priceSold;
    }

}


