package ssg.inc.sav;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SelectView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_view);
               
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_view, menu);
        return true;
    }
 
    public void myClickHandler (View v){
    	Intent intent = new Intent(this, ListsView.class);
    	switch(v.getId()){
    		case R.id.button_gudi:
    			//구디지역에 해당하는 xml load    			
    			intent.putExtra("section",  1);
    		    startActivity(intent);
    			break;
    		case R.id.button_guro:
    			//남구로지역에 해당하는 xml load
    			intent.putExtra("section",  2);
    			startActivity(intent);
    			break;
    		case R.id.button_shin:
    			//신당지역에 해당하는 xml load
    			intent.putExtra("section",  3);
    			startActivity(intent);
    			break;
    	}
    	
    }

}


