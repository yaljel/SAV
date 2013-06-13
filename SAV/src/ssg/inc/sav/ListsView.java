package ssg.inc.sav;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class ListsView extends Activity {
	// All static variables
	int URL = 0;
	// XML node keys
	static final String KEY_AREA = "Area"; // parent node
	static final String KEY_TEAM = "Team";
	static final String KEY_EMP = "Emp";
	static final String KEY_POSITION = "Position";
	static final String KEY_EMPPHONE = "EmpPhone";
	static ArrayList<HashMap<String, String>> officerList = new ArrayList<HashMap<String, String>>();
	ListView list;
	MakeList adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		officerList.clear();
		
		TextView subTitle = (TextView) findViewById(R.id.sub_title_location);
		// based on selected location. 1:지원(남), 2:지원(이), 3:신당, 4:ITO/ITS, 5:유동
		if (((Shinsegae)(this.getApplication())).getSelection() == 1)
		{
			subTitle.setText("지원(남) 봉사자");
		}
		else if (((Shinsegae)(this.getApplication())).getSelection() == 2)
		{
			subTitle.setText("지원(이) 봉사자");
		}
		else if (((Shinsegae)(this.getApplication())).getSelection() == 3)
		{
			subTitle.setText("신당 봉사자");
		}
		else if (((Shinsegae)(this.getApplication())).getSelection() == 4)
		{
			subTitle.setText("ITO/ITS 봉사자");
		}
		else if (((Shinsegae)(this.getApplication())).getSelection() == 5)
		{
			subTitle.setText("유통 봉사자");
		}
		

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// select xml
		if (((Shinsegae)(this.getApplication())).getSelection() == 1)
		{
			URL = R.xml.officer;
		}
		else if (((Shinsegae)(this.getApplication())).getSelection() == 2)
		{
			URL = R.xml.officer;
		}
		else
		{
			URL = R.xml.officer;
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
						if(tag.compareTo(KEY_AREA) == 0)
						{
							field = 1;
						}
						else if(tag.compareTo(KEY_TEAM) == 0)
						{
							field = 2;
						}
						else if(tag.compareTo(KEY_EMP) == 0)
						{
							field = 3;
						}
						else if(tag.compareTo(KEY_POSITION) == 0)
						{
							field = 4;
						}
						else if(tag.compareTo(KEY_EMPPHONE) == 0)
						{
							field = 5;
						}
						break;
					case XmlPullParser.TEXT:
						// 아이템 안에 subject 라는 태그의 데이터를 가지고 오기.
						tag = parser.getName();
						switch (field)
						{
							case 1:
								map = new HashMap<String, String>();
								map.put(KEY_AREA, parser.getText());
								break;
							case 2:
								map.put(KEY_TEAM, parser.getText());
								break;
							case 3:
								map.put(KEY_EMP, parser.getText());
								break;
							case 4:
								map.put(KEY_POSITION, parser.getText());
								break;
							case 5:
								map.put(KEY_EMPPHONE, parser.getText());
								break;
						}
						break;
					case XmlPullParser.END_TAG:
						//태그가 끝나면 텍스트 파일 가져오지 못하게 하기. 1:지원(남), 2:지원(이), 3:신당, 4:ITO/ITS, 5:유동
						tag = parser.getName();
						if(tag.compareTo("Sav") == 0)
						{
							String tmp = map.get(KEY_AREA);
							if (tmp.contains("지원(남)") && location == 1)
							{
								map.put("Id", count.toString());
								officerList.add(map);
								count++;
							}
							else if (map.get("Area").contains("지원(이)") && location == 2)
							{
								map.put("Id", count.toString());
								officerList.add(map);
								count++;
							}
							else if (map.get("Area").contains("신당") && location == 3)
							{
								map.put("Id", count.toString());
								officerList.add(map);
								count++;
							}
							else if (map.get("Area").contains("ITO/ITS") && location == 4)
							{
								map.put("Id", count.toString());
								officerList.add(map);
								count++;
							}
							else if (map.get("Area").contains("유통") && location == 5)
							{
								map.put("Id", count.toString());
								officerList.add(map);
								count++;
							}
						}
						field = 0;
						break;
				}
				parserEvent = parser.next(); // 다음 태그로 이동하기
			}while(parserEvent != XmlPullParser.END_DOCUMENT);
		}catch(Exception ex){}		
		
		((Shinsegae)(this.getApplication())).setOfficer(officerList);

		list = (ListView) findViewById(R.id.list2);

		// Getting adapter by passing xml data ArrayList
		adapter = new MakeList(this, officerList);
		list.setAdapter(adapter);

		// Click event for single list row
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 담당자를 선택하면 전화걸기
				String phoneNumber = officerList.get(position).get(KEY_EMPPHONE);
				if (!phoneNumber.contains("-"))
				{
					phoneNumber = PhoneNumberUtils.formatNumber(phoneNumber);
				}
				String contact = "tel:" + phoneNumber;
				Intent intent = new Intent (Intent.ACTION_CALL, Uri.parse(contact));
				intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
	}
}