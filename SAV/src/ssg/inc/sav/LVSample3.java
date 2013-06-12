package ssg.inc.sav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ssg.inc.sav.R;
import ssg.inc.sav.LVSample3Adapter.ViewHolder;
import ssg.inc.sav.R.drawable;
import ssg.inc.sav.R.id;
import ssg.inc.sav.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LVSample3 extends Activity implements OnClickListener {

	private Button btnSearch;
	private Button btnAdd;
	private Button btnDelete;

	private ListView lv;
	private List<LVSample3Item> dataSources;
	private ListAdapter adapter;

	private DialogInterface.OnClickListener deleteYesListener;
	private DialogInterface.OnClickListener deleteNoListener;
	private ArrayList<HashMap<String, String>> parsedList;
	private Long[] savedItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		
		TextView subTitle = (TextView) findViewById(R.id.sub_title_List);
		// based on selected location. 1:지원(남), 2:지원(이), 3:신당, 4:ITO/ITS, 5:유동
		if (((Shinsegae)(this.getApplication())).getSelection() == 1)
		{
			subTitle.setText("지원(남) 목적지");
		}
		else if (((Shinsegae)(this.getApplication())).getSelection() == 2)
		{
			subTitle.setText("지원(이) 목적지");
		}
		else if (((Shinsegae)(this.getApplication())).getSelection() == 3)
		{
			subTitle.setText("신당 목적지");
		}
		else if (((Shinsegae)(this.getApplication())).getSelection() == 4)
		{
			subTitle.setText("ITO/ITS 목적지");
		}
		else if (((Shinsegae)(this.getApplication())).getSelection() == 5)
		{
			subTitle.setText("유통 목적지");
		}
		
		parsedList = ((Shinsegae)(this.getApplication())).getList();

		// For Buttons
		btnSearch = (Button) findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(this);

		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);

		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(this);

		// For Custom ListView
		dataSources = getDataSource();
		adapter = new LVSample3Adapter(dataSources, this);
		this.lv = (ListView) findViewById(R.id.list);
		this.lv.setAdapter(adapter);
		this.lv.setItemsCanFocus(false);
		this.lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
		final Long[] tmp = ((Shinsegae)(this.getApplication())).getCheckedItems();
		final Intent NMapIntent = new Intent(this, NMapViewer.class);
		
		this.lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
				// TODO Auto-generated method stub
				dataSources.get(position).getUniqueId();
				
				Long[] checkedItems = ((LVSample3Adapter) adapter).getCheckItemIds();
				for (int i = 0; i < checkedItems.length; i++)
				{
					String uniqueId = dataSources.get((int)(long)checkedItems[i]).getUniqueId();
					checkedItems[i] = (long)Integer.valueOf(uniqueId);
				}
				
				Long tmp = Long.parseLong(dataSources.get(position).getUniqueId());
				NMapIntent.putExtra("uniqueId", (int)(long)tmp);
				startActivity(NMapIntent);
			}	
		});
		
		this.lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				dataSources.get(arg2).getUniqueId();
				
				Long[] checkedItems = ((LVSample3Adapter) adapter).getCheckItemIds();
				for (int i = 0; i < checkedItems.length; i++)
				{
					String uniqueId = dataSources.get((int)(long)checkedItems[i]).getUniqueId();
					checkedItems[i] = (long)Integer.valueOf(uniqueId);
				}
				
				Long tmp = Long.parseLong(dataSources.get(arg2).getUniqueId());
				NMapIntent.putExtra("uniqueId", (int)(long)tmp);
				startActivity(NMapIntent);
				return false;
			}
		});
	}

	@Override
	public void onClick(View view) {
		// 전체보기
		if (view.getId() == R.id.btnSearch) {
			Long[] checkedItems = ((LVSample3Adapter) adapter).getCheckItemIds();
			for (int i = 0; i < checkedItems.length; i++)
			{
				String uniqueId = dataSources.get((int)(long)checkedItems[i]).getUniqueId();
				checkedItems[i] = (long)Integer.valueOf(uniqueId);
			}
			dataSources.clear();
			for (int i = 0; i < parsedList.size(); i++) 
			{
				LVSample3Item item = new LVSample3Item(parsedList.get(i).get("Address"), parsedList.get(i).get("Name"), parsedList.get(i).get("Id"));
				dataSources.add(item);
			}
			
			adapter = new LVSample3Adapter(dataSources, this);
			this.lv = (ListView) findViewById(R.id.list);
			this.lv.setAdapter(adapter);
			this.lv.setItemsCanFocus(false);
			this.lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

			this.lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
					// TODO Auto-generated method stub
				}
			});
			
			((LVSample3Adapter) adapter).clearChoices();
			ViewHolder holder;
			View convertView;
			if (checkedItems != null)
			{
				for (int i = 0; i < checkedItems.length; i++)
				{
					convertView = ((LVSample3Adapter) adapter).getView((int)(long)checkedItems[i], null, this.lv);
					holder = (ViewHolder) convertView.getTag();
					holder.chk.setChecked(true);
				}
			}
			((LVSample3Adapter) adapter).notifyDataSetChanged();
			

		} 
		// 선택보기
		else if (view.getId() == R.id.btnAdd) {
			// 선택된 것 없을 때 msg 출력
			Long[] checkedItems = ((LVSample3Adapter) adapter).getCheckItemIds();
			if (checkedItems == null || checkedItems.length == 0) {
				ShowMessageBox(this, "Selected items is Nothing.");
				return;
			}
			
			java.util.Arrays.sort(checkedItems);
			
			int checked = checkedItems.length - 1;
			for (int index = ((LVSample3Adapter) adapter).getCount() - 1; index >= 0 ; index--)
			{
				if (index != checkedItems[checked])
					dataSources.remove(index);
				else
				{
					checked --;
					if (checked < 0)
					{
						for (index--;index >=0; index--)
						{
							dataSources.remove(index);
						}
						break;
					}
						
				}
			}
			
			adapter = new LVSample3Adapter(dataSources, this);
			this.lv = (ListView) findViewById(R.id.list);
			this.lv.setAdapter(adapter);
			this.lv.setItemsCanFocus(false);
			this.lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

			this.lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
					// TODO Auto-generated method stub
				}
			});
			
			((LVSample3Adapter) adapter).clearChoices();
			
			ViewHolder holder;
			View convertView;
			if (checkedItems != null)
			{
				for (int i = 0; i < dataSources.size(); i++)
				{
					convertView = ((LVSample3Adapter) adapter).getView(i, null, this.lv);
					holder = (ViewHolder) convertView.getTag();
					holder.chk.setChecked(true);
				}
			}
			((LVSample3Adapter) adapter).notifyDataSetChanged();
		}
		// 지도 표시
		else if (view.getId() == R.id.btnDelete) {
			Long[] checkedItems = ((LVSample3Adapter) adapter).getCheckItemIds();
			// 선택된 것 없을 때 msg 출력
			if (checkedItems == null || checkedItems.length == 0) {
				ShowMessageBox(this, "Selected items is Nothing.");
				return;
			}
			
			for (int i = 0; i < checkedItems.length; i++)
			{
				String uniqueId = dataSources.get((int)(long)checkedItems[i]).getUniqueId();
				checkedItems[i] = (long)Integer.valueOf(uniqueId);
			}
			((Shinsegae)(this.getApplication())).setCheckedItems (checkedItems);
			
			Intent intent = new Intent(this, NMapViewer.class);
			intent.putExtra("uniqueId", -1);
			startActivity(intent);
		}
	}

	private List<LVSample3Item> getDataSource() {
		List<LVSample3Item> lstItems = new ArrayList<LVSample3Item>();
		for (int i = 0; i < parsedList.size(); i++) {
			LVSample3Item item = new LVSample3Item(parsedList.get(i).get("Address"), parsedList.get(i).get("Name"), parsedList.get(i).get("Id"));
			lstItems.add(item);
		}
		return lstItems;
	}

	// Not used
	private List<LVSample3Item> addDataSource(List<LVSample3Item> dataSources) {
		LVSample3Item item = new LVSample3Item("Added" + dataSources.size(), "This is a added mssage.", "Not used Function");
		dataSources.add(item);

		return dataSources;
	}
	
	// Not used
	private void ShowMessageBox(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
	
	// Not used
	public void ShowMessageYesNo(Context context, String title, String message,
			DialogInterface.OnClickListener YesListener, DialogInterface.OnClickListener NoListener) {
		AlertDialog.Builder alterBuilder = new AlertDialog.Builder(context);
		alterBuilder.setMessage(message).setCancelable(false).setPositiveButton(android.R.string.yes, YesListener)
				.setNegativeButton(android.R.string.no, NoListener);
		AlertDialog alert = alterBuilder.create();
		// Title for AlertDialog
		alert.setTitle(title);
		// Icon for AlertDialog
		alert.setIcon(R.drawable.icon);
		alert.show();
	}
	
	@Override
	protected void onPause() { // TODO Auto-generated method stub
		savedItems = ((LVSample3Adapter) adapter).getCheckItemIds();
	    super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		adapter = new LVSample3Adapter(dataSources, this);
		//adapter = new LVSample3Adapter(((Shinsegae)(this.getApplication())).getList(), this);
		this.lv = (ListView) findViewById(R.id.list);
		this.lv.setAdapter(adapter);
		this.lv.setItemsCanFocus(false);
		this.lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		this.lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
				// TODO Auto-generated method stub
			}
		});
		
		ViewHolder holder;
		View convertView;
		if (savedItems != null)
		{
			for (int i = 0; i < savedItems.length; i++)
			{
				convertView = ((LVSample3Adapter) adapter).getView((int)(long)savedItems[i], null, this.lv);
				holder = (ViewHolder) convertView.getTag();
				holder.chk.setChecked(true);
			}
		}
		
		((LVSample3Adapter) adapter).notifyDataSetChanged();
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		dataSources.clear();
	}
}