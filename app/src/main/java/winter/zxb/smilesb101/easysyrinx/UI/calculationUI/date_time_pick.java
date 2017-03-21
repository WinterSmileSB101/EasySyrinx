package winter.zxb.smilesb101.easysyrinx.UI.calculationUI;

import android.animation.TypeConverter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.wasabeef.glide.transformations.internal.Utils;
import winter.zxb.smilesb101.easysyrinx.Data.utils;
import winter.zxb.smilesb101.easysyrinx.Logic.FindSyrinxInfo.FindInfo;
import winter.zxb.smilesb101.easysyrinx.R;

public class date_time_pick extends AppCompatActivity{
	public static final String MONTH = "MONTH";
	public static final String DAY = "DAY";
	public static final String YEAR = "YEAR";
	public static final String HOUR = "HOUR";
	public static final String MINUTE = "MINUTE";
	public static final String WEEK = "WEEK";
	public static final int RESULTCODE = 1;

	private static final String TAG = "date_time_pick";

	private View rootView;
	private CalendarView calendarView;

	private TextView month_show,day_show,year_show,week_show;

	private String Month,Day,Year,Hour,Minute;
	private String Week;

	private Button sureBtn,timeSureBtn;

	private TimePicker timerPicker;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		//设置状态栏透明
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(Color.TRANSPARENT);
			window.setNavigationBarColor(Color.TRANSPARENT);
		}
		setContentView(R.layout.fragment_date_time_pick);
		Log.i(TAG,"onCreate: "+utils.getCurrentTime("yyyy-MM-dd"));
		Year = utils.getCurrentTime("yyyy");
		Month = utils.getCurrentTime("MM");
		Day = utils.getCurrentTime("dd");
		Week = utils.getWeek(utils.getCurrentTime("yyyy-MM-dd"));
//		Intent intent = getIntent();
//		Month = intent.getIntExtra(MONTH,2);
//		Year = intent.getIntExtra(YEAR,2017);
//		Day = intent.getIntExtra(DAY,25);
		InitDateView();
	}

	void InitDateView(){
		month_show = (TextView)findViewById(R.id.datepick_month);
		year_show = (TextView)findViewById(R.id.datepick_year);
		day_show = (TextView)findViewById(R.id.datepick_day);
		week_show = (TextView)findViewById(R.id.datepick_week);

		sureBtn = (Button)findViewById(R.id.datepick_sureBtn);
		sureBtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				//按下取到新页面
				setContentView(R.layout.timepick_layout);
				InitTimeView();
			}
		});

		calendarView = (CalendarView)findViewById(R.id.datepick_calendar);

		calendarView.setDate(System.currentTimeMillis());

		month_show.setText(Month + "月");
		day_show.setText(Day);
		year_show.setText(Year);
		week_show.setText(Week);

		calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
			@Override
			public void onSelectedDayChange(CalendarView view,int year,int month,int dayOfMonth){
				Month = (month + 1)+"";
				Day = dayOfMonth+"";
				Year = year+"";
				int firstDayOfWeek = view.getFirstDayOfWeek();

				month_show.setText(Month + "月");
				day_show.setText(Day);
				year_show.setText(Year);
				view.getDate();
				Week = utils.getWeek(Year + "-" + Month + "-" + Day);
				week_show.setText(Week);

			}
		});
	}

	void InitTimeView()
	{
		Hour = utils.getCurrentTime("HH");
		Minute = utils.getCurrentTime("mm");
		timerPicker = (TimePicker)findViewById(R.id.time_pick_timePicker);
		timerPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
			@Override
			public void onTimeChanged(TimePicker view,int hourOfDay,int minute){
				Hour = hourOfDay +"";
				Minute = minute + "";
			}
		});
		timeSureBtn = (Button)findViewById(R.id.time_pick_sureBtn);
		timeSureBtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				onBackPressed();
			}
		});
	}

	@Override
	public void onBackPressed(){
		Log.i(TAG,"onBackPressed: 返回");
		//返回主活动
		Intent intent = new Intent();
		intent.putExtra(YEAR,Year);
		intent.putExtra(MONTH,Month);
		intent.putExtra(DAY,Day);
		intent.putExtra(HOUR,Hour);
		intent.putExtra(MINUTE,Minute);
		intent.putExtra(WEEK,Week);
		setResult(RESULT_OK,intent);
		finish();
	}
}
