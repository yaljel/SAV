package ssg.inc.sav;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


@SuppressWarnings("deprecation")
public class TabView extends TabActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ActionBar actionbar = null;
        actionbar = getActionBar();
      //액션바에 별도의 배경 이미지 지정
        actionbar.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.sinc2_ui_02_abar1));
        actionbar.setTitle("신세계 도시락배달 도우미");
        actionbar.setIcon(R.drawable.sinc_ui_abar_logo);


 
        final TabHost tabHost = getTabHost();
 
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("목적지", getResources().getDrawable(R.drawable.tab_1))
                .setContent(new Intent(this, LVSample3.class)));
 
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("담당자", getResources().getDrawable(R.drawable.tab_2))
                .setContent(new Intent(this, ListsView.class)));
                
    }
}
