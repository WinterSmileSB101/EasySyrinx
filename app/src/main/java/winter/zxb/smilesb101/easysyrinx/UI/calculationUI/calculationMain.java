package winter.zxb.smilesb101.easysyrinx.UI.calculationUI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import winter.zxb.smilesb101.easysyrinx.R;

public class calculationMain extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener{

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which will keep every
	 * loaded fragment in memory. If this becomes too memory intensive, it
	 * may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private MagicIndicator magicIndicator;
	public static Activity activity;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		activity = this;
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

		setContentView(R.layout.activity_calculation_main);

		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager)findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		magicIndicator = (MagicIndicator)findViewById(R.id.magic_indicator);
		final CommonNavigator commonNavigator = new CommonNavigator(this);
		//commonNavigator.setAdjustMode(true);
		commonNavigator.setAdapter(new CommonNavigatorAdapter(){
			@Override
			public int getCount(){
				return mSectionsPagerAdapter.getCount();
			}

			@Override
			public IPagerTitleView getTitleView(Context context,final int index){
				ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
				colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
				colorTransitionPagerTitleView.setSelectedColor(Color.WHITE);
				colorTransitionPagerTitleView.setText(mSectionsPagerAdapter.getPageTitle(index));
				colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						mViewPager.setCurrentItem(index);
					}
				});
				return colorTransitionPagerTitleView;
			}

			@Override
			public IPagerIndicator getIndicator(Context context){
				BezierPagerIndicator bezierPagerIndicator = new BezierPagerIndicator(context);
				bezierPagerIndicator.setColors(Color.YELLOW,Color.RED);
				return bezierPagerIndicator;
			}
		});
		magicIndicator.setNavigator(commonNavigator);

		ViewPagerHelper.bind(magicIndicator,mViewPager);


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_calculation_main,menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if(id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item){
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if(id == R.id.calculation) {
			//打开记账活动
			//startActivity(new Intent(this,calculationMain.class),ActivityOptionsCompat.makeSceneTransitionAnimation(this,));
		} else if(id == R.id.notepad) {

		} else if(id == R.id.transport) {

		} else if(id == R.id.cart) {

		} else if(id == R.id.AR) {

		} else if(id == R.id.nav_send) {

		}

		DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements View.OnClickListener{
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		private Button write_oneNote;

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

		@Override
		public View onCreateView(LayoutInflater inflater,ViewGroup container,
								 Bundle savedInstanceState){
			View rootView = null;
			switch(getArguments().getInt(ARG_SECTION_NUMBER))
			{
				case 1:
					rootView = newLeftInstance(inflater,container);
					break;
				case 2:
					rootView = newCenterInstance(inflater,container);
					break;
				case 3:
					rootView = newRightInstance(inflater,container);
					break;
				default:
					rootView = newCenterInstance(inflater,container);
					break;
			}
//			TextView textView = (TextView)rootView.findViewById(R.id.section_label);
//			textView.setText(getString(R.string.section_format,getArguments().getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}

		private View newCenterInstance(LayoutInflater inflater,ViewGroup container)
		{
			View rootView = inflater.inflate(R.layout.fragment_calculation_main_center,container,false);
			ImageView bg = (ImageView)rootView.findViewById(R.id.fragment_calculation_main_bg);
			Glide.with(this)
					.load(R.mipmap.calculation_bg)
					.centerCrop()
					.into(bg);
			write_oneNote = (Button)rootView.findViewById(R.id.fragment_calculation_main_addButton);
			write_oneNote.setOnClickListener(this);
			return rootView;
		}

		private View newLeftInstance(LayoutInflater inflater,ViewGroup container)
		{
			View rootView = inflater.inflate(R.layout.fragment_calculation_main_left,container,false);
			return rootView;
		}
		private View newRightInstance(LayoutInflater inflater,ViewGroup container)
		{
			View rootView = inflater.inflate(R.layout.fragment_calculation_main_right,container,false);
			return rootView;
		}

		@Override
		public void onClick(View v){
			switch(v.getId())
			{
				case R.id.fragment_calculation_main_addButton:
					startActivity(new Intent(getActivity(),store_account_book_activity.class),ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),write_oneNote,"store").toBundle());
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
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount(){
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position){
			switch(position) {
				case 0:
					return "汇总";
				case 1:
					return "日常账本";
				case 2:
					return "添加账本";
			}
			return null;
		}
	}
}
