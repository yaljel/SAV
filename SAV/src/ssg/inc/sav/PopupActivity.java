package ssg.inc.sav;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PopupActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.popup_activity);
		
		Intent intent = getIntent();
		
		String name = intent.getExtras().getString("name");
		String address = intent.getExtras().getString("address");
		String phone = intent.getExtras().getString("phone");
		String memo = intent.getExtras().getString("memo");
		String home_image = intent.getExtras().getString("home_image");
		int identifier = getResources().getIdentifier(home_image, "drawable", getPackageName());
		//Bitmap bit = (new BitmapDrawable(identifier)).getBitmap();

        
		
		TextView textview1 = (TextView)findViewById(R.id.name);
		textview1.setText(name);
		
		TextView textview2 = (TextView)findViewById(R.id.address);
		textview2.setText(address);
		
		TextView textview3 = (TextView)findViewById(R.id.phone);
		textview3.setText(phone);
		
		TextView textview4 = (TextView)findViewById(R.id.memo);
		if(!memo.equals("null"))
			textview4.setText(memo);
		
		//Resources res = getResources();
		//BitmapDrawable bitmap = (BitmapDrawable)res.getDrawable(R.drawable.concept_car);
		
		ImageView imageview = (ImageView)findViewById(R.id.home_image);
		//imageview.setImageBitmap(bit);
		imageview.setImageResource(identifier);
	}
	
}
