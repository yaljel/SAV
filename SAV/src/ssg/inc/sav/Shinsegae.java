package ssg.inc.sav;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;

public class Shinsegae extends Application{
	private int selection;
	private ArrayList<HashMap<String, String>> songsList;
	private ArrayList<HashMap<String, String>> officerList;
	private Long[] checkedItems;
	
	  public int getSelection()
	  {
	    return selection;
	  }
	  public void setSelection(int data)
	  {
	    this.selection = data;
	  }
	  
	  public ArrayList<HashMap<String, String>> getList()
	  {
		  return songsList;
	  }
	  public void setList (ArrayList<HashMap<String, String>> data)
	  {
		  this.songsList = data;
	  }
	  
	  public ArrayList<HashMap<String, String>> getOfficer()
	  {
		  return officerList;
	  }
	  public void setOfficer (ArrayList<HashMap<String, String>> data)
	  {
		  this.officerList = data;
	  }
	  
	  public Long[] getCheckedItems ()
	  {
		  return checkedItems;
	  }
	  public void setCheckedItems (Long[] data)
	  {
		  this.checkedItems = data;
	  }

}