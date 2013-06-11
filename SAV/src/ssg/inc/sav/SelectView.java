package ssg.inc.sav;

import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SelectView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_view);
        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_view, menu);
        return true;
    }
 
    public void myClickHandler (View v){
    	Intent intent = new Intent(this, TabView.class);
		// 지도에서 지역 선택 . 1:지원(남), 2:지원(이), 3:신당, 4:ITO/ITS, 5:유동
    	switch(v.getId()){
    		// 지원(남) 선택
    		case R.id.button_1:
    			//구디지역에 해당하는 xml load    		
    			((Shinsegae)(this.getApplication())).setSelection(1);
    		    startActivity(intent);
    			break;
    		// 지원(이) 선택
    		case R.id.button_2:
    			//남구로지역에 해당하는 xml load
    			((Shinsegae)(this.getApplication())).setSelection(2);
    			startActivity(intent);
    			break;
    		// 신당 선택
    		case R.id.button_3:
    			//신당지역에 해당하는 xml load
    			((Shinsegae)(this.getApplication())).setSelection(3);
    			startActivity(intent);
    			break;
    		// ITO/ITS
    		case R.id.button_4:
    			//신당지역에 해당하는 xml load
    			((Shinsegae)(this.getApplication())).setSelection(4);
    			startActivity(intent);
    			break;
    		// 유통
    		case R.id.button_5:
    			//신당지역에 해당하는 xml load
    			((Shinsegae)(this.getApplication())).setSelection(5);
    			startActivity(intent);
    			break;
    	}
    	
    	int URL;
    	ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    	String KEY_SONG = "song"; // parent node
    	String KEY_ID = "id";
    	String KEY_TITLE = "title";
    	String KEY_ARTIST = "artist";

		// select xml
		if (((Shinsegae)(this.getApplication())).getSelection() == 1)
		{
			URL = R.xml.list;
		}
		else if (((Shinsegae)(this.getApplication())).getSelection() == 2)
		{
			URL = R.xml.list;
		}
		else
		{
			URL = R.xml.list;
		}
		
				
		try{
			HashMap<String, String> map = null;// = new HashMap<String, String>();

			XmlResourceParser parser = this.getResources().getXml(URL);
			int parserEvent = parser.getEventType();
			String tag = "";
			String subject = "";
			int field = 0;
			Integer count = 0;
			int location = ((Shinsegae)(this.getApplication())).getSelection();
			do{
				switch(parserEvent)
				{
					case XmlPullParser.START_TAG:
						//태그가 시작점인지 판단
						tag = parser.getName();
						field = 0;
						if(tag.compareTo("Area") == 0)
						{
							field = 1;
						}
						else if(tag.compareTo("Name") == 0)
						{
							field = 2;
						}
						else if(tag.compareTo("Address") == 0)
						{
							field = 3;
						}
						else if(tag.compareTo("Lng") == 0)
						{
							field = 4;
						}
						else if(tag.compareTo("Lat") == 0)
						{
							field = 5;
						}
						else if(tag.compareTo("Phone") == 0)
						{
							field = 6;
						}
						else if(tag.compareTo("Memo") == 0)
						{
							field = 7;
						}
						else if(tag.compareTo("Img") == 0)
						{
							field = 8;
						}
						break;
					case XmlPullParser.TEXT:
						// 아이템 안에 subject 라는 태그의 데이터를 가지고 오기.
						tag = parser.getName();
						switch (field)
						{
							case 1:
								map = new HashMap<String, String>();
								map.put("Area", parser.getText());
								break;
							case 2:
								map.put("Name", parser.getText());
								break;
							case 3:
								map.put("Address", parser.getText());
								break;
							case 4:
								map.put("Lng", parser.getText());
								break;
							case 5:
								map.put("Lat", parser.getText());
								break;
							case 6:
								map.put("Phone", parser.getText());
								break;
							case 7:
								map.put("Memo", parser.getText());
								break;
							case 8:
								map.put("Img", parser.getText());
								break;	
							
						}
						break;
					case XmlPullParser.END_TAG:
						//태그가 끝나면 텍스트 파일 가져오지 못하게 하기.
						tag = parser.getName();
						if(tag.compareTo("Sav") == 0)
						{
							// 1:지원(남), 2:지원(이), 3:신당, 4:ITO/ITS, 5:유동
							String tmp = map.get("Area");
							if (tmp.contains("지원(남)") && location == 1)
							{
								map.put("Id", count.toString());
								songsList.add(map);
								count++;
							}
							else if (map.get("Area").contains("지원(이)") && location == 2)
							{
								map.put("Id", count.toString());
								songsList.add(map);
								count++;
							}
							else if (map.get("Area").contains("신당") && location == 3)
							{
								map.put("Id", count.toString());
								songsList.add(map);
								count++;
							}
							else if (map.get("Area").contains("ITO/ITS") && location == 4)
							{
								map.put("Id", count.toString());
								songsList.add(map);
								count++;
							}
							else if (map.get("Area").contains("유통") && location == 5)
							{
								map.put("Id", count.toString());
								songsList.add(map);
								count++;
							}
							/*
							// for test
							map.put("Id", count.toString());
							songsList.add(map);
							count++;
							*/
						}
						field = 0;
						break;
				}
				parserEvent = parser.next(); // 다음 태그로 이동하기
			}while(parserEvent != XmlPullParser.END_DOCUMENT);
		}catch(Exception ex){}
		
		((Shinsegae)(this.getApplication())).setList(songsList);
    	
    	
    }

}

/* Naver Map api key : 7585818735499500f34cf80643c00eda */


