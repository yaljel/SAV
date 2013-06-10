package ssg.inc.sav;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.content.Intent;


@SuppressWarnings("deprecation")
public class TabView extends TabActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        final TabHost tabHost = getTabHost();
 
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("목적지")
                .setContent(new Intent(this, LVSample3.class)));
 
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("담당자")
                .setContent(new Intent(this, ListsView.class)));
                
    }
}
