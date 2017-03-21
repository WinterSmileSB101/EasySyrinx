package winter.zxb.smilesb101.easysyrinx.UI.calculationUI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.TriangularPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.Data.utils;
import winter.zxb.smilesb101.easysyrinx.R;

public class store_account_book_activity extends AppCompatActivity{

	private static String TAG = "store_Activity";

	private View rootView;
	private MagicIndicator magicIndicator;
	private ViewPager viewPager;
	private static SectionsPagerAdapter sectionsPagerAdapter;
	private ActionBar actionBar;
	private ImageButton backBtn,storeBtn;
	private AppCompatSpinner spinner;
	private ArrayAdapter<String> spinnerSelects;
	private ArrayList<String> accountBooks;

	private ArrayList<PlaceholderFragment> placeHoderFragments;

	private static int fragmentIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState){
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
		setContentView(R.layout.store_account_book);
		getWidget();
		initValue();
	}
	void initValue()
	{
		placeHoderFragments = new ArrayList<>();
		sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(sectionsPagerAdapter);
		final CommonNavigator commonNavigator = new CommonNavigator(this);
		commonNavigator.setAdjustMode(true);
		commonNavigator.setAdapter(new CommonNavigatorAdapter(){
			@Override
			public int getCount(){
				return sectionsPagerAdapter.getCount();
			}

			@Override
			public IPagerTitleView getTitleView(Context context,final int index){
				ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
				colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
				colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#CC3300"));
				colorTransitionPagerTitleView.setText(sectionsPagerAdapter.getPageTitle(index));
				colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						viewPager.setCurrentItem(index);
					}
				});
				return colorTransitionPagerTitleView;
			}

			@Override
			public IPagerIndicator getIndicator(Context context){
				LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
				linePagerIndicator.setColors(Color.parseColor("#CC3300"));
				return linePagerIndicator;
			}
		});
		magicIndicator.setNavigator(commonNavigator);
		ViewPagerHelper.bind(magicIndicator,viewPager);

		accountBooks = new ArrayList<>();
		accountBooks.add("日常账本1");
		accountBooks.add("日常账本2");
		accountBooks.add("新增账本");
		spinnerSelects = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,accountBooks);
		spinnerSelects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(spinnerSelects);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
				Log.i(TAG,"onItemSelected: 选择了第："+position+" 项");
				if(position==accountBooks.size()-1)
				{
					Log.i(TAG,"onItemSelected: 最后一项选中");
					//打开新建账本活动
				}
				else
				{

				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent){

			}
		});
	}
	void getWidget(){
		magicIndicator = (MagicIndicator)findViewById(R.id.store_tablayout);
		viewPager = (ViewPager)findViewById(R.id.store_account_book_viewPager);
		Toolbar toolbar = (Toolbar)findViewById(R.id.store_toolbar);
		if(getSupportActionBar()==null)
		{
			setSupportActionBar(toolbar);
		}
		actionBar = getSupportActionBar();

		backBtn = (ImageButton)findViewById(R.id.toolbar_backBtn);
		storeBtn = (ImageButton)findViewById(R.id.toolbar_store);

		spinner = (AppCompatSpinner)findViewById(R.id.toolbar_spinner);
	}


	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		Log.i(TAG,"onActivityResult: 收到回复"+fragmentIndex);
				if(resultCode==RESULT_OK) {
					Log.i(TAG,"onActivityResult: 收到回复");
					placeHoderFragments.get(fragmentIndex-1).setTimer_textView(data.getStringExtra(date_time_pick.YEAR)+" 年 "+data.getStringExtra(date_time_pick.MONTH)+" 月 "+data.getStringExtra(date_time_pick.DAY)+" 日   "+data.getStringExtra(date_time_pick.HOUR)+" : "+data.getStringExtra(date_time_pick.MINUTE));
				}
	}

	public  static class PlaceholderFragment extends Fragment implements View.OnClickListener{
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_DATE = "DATE";
		private static final String ARG_SECTION_NUMBER = "section_number";

		private TextInputEditText inputEditText;
		private View rootView;
		private TextView test_text;
		private LinearLayout timer_selecter;
		private TextView timer_loop;

		public void setTimer_textView(String timer_string){
			Log.i(TAG,"setTimer_textView: 设置文字"+timer_string);
			this.timer_textView.setText(timer_string);
		}

		private TextView timer_textView;

		public PlaceholderFragment(){
		}

		/**
		 * Returns a new instance of this fragment for the given section
		 * number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber){
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER,sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		@Nullable
		@Override
		public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
			rootView = inflater.inflate(R.layout.fragment_account_book,container,false);
			InitView();
			return rootView;
		}

		void InitView()
		{
			if(rootView!=null) {
				inputEditText = (TextInputEditText)rootView.findViewById(R.id.fragment_account_book_num);
				test_text = (TextView)rootView.findViewById(R.id.test_text);
				//添加文字改变监听
				inputEditText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s,int start,int count,int after){
					}

					@Override
					public void onTextChanged(CharSequence s,int start,int before,int count){
						String str = s.toString();
						Log.i(TAG,"onTextChanged: text变化: " + str);
						if(str.contains(".")) {
							Log.i(TAG,"onTextChanged: 到了位置。");
							String lastS = str.substring(str.indexOf(".") + 1,str.length());
							str = str.substring(0,str.indexOf("."));
							switch(lastS.length()) {
								case 0:
									test_text.setText(str + ".00");
									break;
								case 1:
									test_text.setText(str + "." + lastS + "0");
									break;
								case 2:
									test_text.setText(str + "." + lastS);
									break;
								default://超过两位，不变化，而且值保留两位，不再多保存，防止一直删除确看起来删除不了的bug
									str = str + "." + lastS.substring(0,2);
									inputEditText.setText(str);
									inputEditText.setSelection(str.length());//设置光标位置到最后，避免输入从头开始的bug
									break;
							}
						} else if(! str.trim().equals("")) {
							test_text.setText(str + ".00");
						} else {
							test_text.setText("0.00");
						}
					}

					@Override
					public void afterTextChanged(Editable s){

					}
				});
				Log.i(TAG,"onCreateView: 创建View");
				switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
					case 1://支出，
						test_text.setTextColor(getResources().getColor(R.color.colorAccent));
						break;
					case 2://收入
						test_text.setTextColor(getResources().getColor(R.color.colorTurquoise));
						break;
					default://其他
						test_text.setTextColor(getResources().getColor(R.color.colorBlack));
						break;
				}

				timer_selecter = (LinearLayout)rootView.findViewById(R.id.fragment_account_book_timer_selecter);
				timer_selecter.setOnClickListener(this);
				timer_textView = (TextView)rootView.findViewById(R.id.fragment_account_book_timer);
				timer_textView.setText(utils.getCurrentTime("yyyy 年 MM 月 dd 日   HH : mm"));
				timer_loop = (TextView)rootView.findViewById(R.id.fragment_account_book_Loop);
				timer_loop.setOnClickListener(this);
			}
		}

		@Override
		public void onClick(View v){
			switch(v.getId())
			{
				case R.id.fragment_account_book_timer_selecter:
					//时间选择,打开时间选择器
					fragmentIndex = getArguments().getInt(ARG_SECTION_NUMBER);
					startActivityForResult(new Intent(getActivity(),date_time_pick.class),date_time_pick.RESULTCODE);
					break;
				case R.id.fragment_account_book_Loop:
					String s = timer_loop.getText().toString().trim();
					String b = "重复";
					String c = "不重复";
					if(s.equals(b))
					{
						s = c;
					}
					else
					{
						s = b;
					}
					timer_loop.setText(s);
					break;
				default:
					break;
			}
		}
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter{

		public SectionsPagerAdapter(FragmentManager fm){
			super(fm);
		}

		@Override
		public Fragment getItem(int position){
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class below).
			PlaceholderFragment f = PlaceholderFragment.newInstance(position + 1);
			placeHoderFragments.add(f);
			return f;
		}

		@Override
		public int getCount(){
			// Show 3 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position){
			switch(position) {
				case 0:
					return "支出";
				case 1:
					return "收入";
				case 2:
					return "转账";
				case 3:
					return "借贷";
			}
			return null;
		}
	}

}
