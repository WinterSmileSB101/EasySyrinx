package winter.zxb.smilesb101.easysyrinx;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import winter.zxb.smilesb101.easysyrinx.UI.calculationUI.date_time_pick;

public class testActivity extends AppCompatActivity{

	private static final String TAG = "testActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		Intent intent = new Intent(this,date_time_pick.class);
		intent.putExtra(date_time_pick.DAY,25);
		intent.putExtra(date_time_pick.MONTH,2);
		intent.putExtra(date_time_pick.YEAR,2017);
		startActivityForResult(intent,date_time_pick.RESULTCODE);
	}

	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		Log.i(TAG,"onActivityResult: 收到回复");
		switch(requestCode)
		{
			case date_time_pick.RESULTCODE:
				if(resultCode==RESULT_OK)
				{
					Log.i(TAG,"onActivityResult: "+data.getStringExtra(date_time_pick.DAY));
				}
				break;
		}
	}
}
