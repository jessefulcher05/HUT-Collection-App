package com.example.jesse.hutcollectionapp_final;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import android.content.Context;
        import android.graphics.Typeface;
        import android.graphics.drawable.Drawable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseExpandableListAdapter;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
        import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles

    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);

        //progress bar stuff
        ProgressBar pBar = (ProgressBar)convertView.findViewById(R.id.progressBar);
        TextView tv = (TextView)convertView.findViewById(R.id.textCounter);

        //get string of Textview to pass in team for db query
        String team = ((TextView)(convertView.findViewById(R.id.lblListItem))).getText().toString();

        ArrayList<Player> playerItems;
        PlayerDB db = new PlayerDB(_context);
        playerItems = db.getPlayerByTeam(team);

        int valueCounter = 0;
        int playerCounter = 0;
        for (int i = 0; i < playerItems.size(); i++) {
            valueCounter += playerItems.get(i).getValue();
            playerCounter += 1;
        }

        int progress = (int) (((double) valueCounter / (double) playerCounter) * 100);
        pBar.setSecondaryProgress(progress);
        tv.setText("" + valueCounter + "/" + playerCounter);

        //get correct image for NHL list view item
       ImageView teamLogoIV = (ImageView)(convertView.findViewById(R.id.imageViewLogo));

        //gets instance of myLoc images
        ImgLocation myLoc = new ImgLocation();

       if(groupPosition == 0)
       {
           teamLogoIV.setImageResource(myLoc.getTeamImagesAtIndex(childPosition));
       }
        else
       {
            //if not in the first group position, returns the app's logo as default.
            teamLogoIV.setImageResource(myLoc.getDrawLogo());
       }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        //sets logo to correct league logo
        ImageView leagueLogoiv = (ImageView)(convertView.findViewById(R.id.imageViewLeagueLogo));

        //int nhlLogo = R.drawable.nhl;

        //gets instance of myLoc images
        ImgLocation myLoc = new ImgLocation();

        //searches array in other class, returns the image at this group position
        leagueLogoiv.setImageResource(myLoc.getLeagueImagesAtIndex(groupPosition));

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
