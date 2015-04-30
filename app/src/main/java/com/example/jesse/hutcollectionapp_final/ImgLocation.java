package com.example.jesse.hutcollectionapp_final;

/**
 * Created by Jesse on 4/29/2015.
 *
 * This class provides all my images in an array. If you need to get the image you can get the entire
 * array and then get the image by placement in array
 */
public class ImgLocation {

    //app logo
    int drawLogo = R.drawable.logo;

    //nhl logos
    int ducks = R.drawable.ducks;
    int coyotes = R.drawable.coyotes;
    int bruins = R.drawable.bruins;
    int sabres = R.drawable.sabres;
    int flames = R.drawable.flames;
    int hurricanes = R.drawable.hurricane;
    int blackhawks = R.drawable.blackhawks;
    int avs = R.drawable.avalanche;
    int bluejackets = R.drawable.bluejackets;
    int stars = R.drawable.stars;
    int redwings = R.drawable.redwings;
    int oilers = R.drawable.oilers;
    int panthers = R.drawable.panthers;
    int kings = R.drawable.kings;
    int wild = R.drawable.wild;
    int canadiens = R.drawable.canadians;
    int predators = R.drawable.predators;
    int devils = R.drawable.devils;
    int islanders = R.drawable.islanders;
    int rangers = R.drawable.rangers;
    int senators = R.drawable.senators;
    int flyers = R.drawable.flyers;
    int pens = R.drawable.penguins;
    int sharks = R.drawable.sharks;
    int blues = R.drawable.blues;
    int lighting = R.drawable.lightning;
    int leafs = R.drawable.mapleleafs;
    int canucks = R.drawable.canucks;
    int caps = R.drawable.capitals;
    int jets = R.drawable.jets;

    int[] myTeamImageArray = {ducks,coyotes,bruins,sabres,flames,hurricanes,blackhawks,avs,bluejackets,stars
            ,redwings,oilers,panthers,kings,wild,canadiens,predators,devils,islanders,rangers,senators
            ,flyers,pens,sharks,blues,lighting,leafs,canucks,caps,jets};

    //league logos
    int nhl = R.drawable.nhl;
    int ahl = R.drawable.ahl;
    int shl = R.drawable.shl;

    int[] myLeagueImageArray = {nhl,ahl,shl};

    //constructor
    public ImgLocation(){

    }

    public int getTeamImagesAtIndex(int i){
        return myTeamImageArray[i];
    }
    public int getDrawLogo(){
        return drawLogo;
    }
    public int getLeagueImagesAtIndex(int i){
        return myLeagueImageArray[i];
    }
}




