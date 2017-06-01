package winter.zxb.smilesb101.easysyrinx;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import winter.zxb.smilesb101.easysyrinx.Data.BingToday;
import winter.zxb.smilesb101.easysyrinx.Data.IBingTodayPresenter;
import winter.zxb.smilesb101.easysyrinx.UI.IWelcomeView;


public class WelcomeActivity extends AppCompatActivity implements IWelcomeView{

	ImageView bingToday;
	TextView title;
	TextView subtitle;
	TextView desc;
	TextView waitText;

	IBingTodayPresenter iBingTodayPresenter;

	String TAG = "WelcomeActivity";

	Handler handler;
	int watiTime;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initValue();
	}

	void initValue()
	{
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		bingToday = (ImageView)findViewById(R.id.imageToday);

		Glide.with(this)
				.load(R.mipmap.welcome)
				.into(bingToday);

		title = (TextView)findViewById(R.id.title);
		subtitle = (TextView)findViewById(R.id.subTitle);
		desc = (TextView)findViewById(R.id.desc);
		waitText = (TextView)findViewById(R.id.waitText);

		iBingTodayPresenter = new IBingTodayPresenter(this);
		iBingTodayPresenter.getBingToday();

		handler = new Handler();
		watiTime = 5;
		waitText.setText(watiTime+"");
		handler.postDelayed(runanble,1000);
	}

	Runnable runanble = new Runnable(){
		@Override
		public void run(){
			watiTime--;
			waitText.setText(watiTime + "");
			if(watiTime<=0)
			{
				//打开主界面
				Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
				return;
			}
			handler.postDelayed(this,1000);
		}
	};

	@Override
	public void ShowTodayBing(BingToday bingToday){
		//展示信息

		Log.i(TAG,"ShowTodayBing: ");
		Glide.with(this)
				.load(bingToday.getImg_1366())
				.into(this.bingToday);

		title.setText(bingToday.getTitle());
		subtitle.setText(bingToday.getSubtitle());
		desc.setText(bingToday.getDescription());



	}

	@Override
	public void onError(String error){
		Log.i(TAG,"ShowTodayBing: ");
		Toast.makeText(this,error,Toast.LENGTH_LONG).show();
	}
}
