package winter.zxb.smilesb101.easysyrinx.UI.calculationUI;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.R;

public class baseChoseActivityMain extends AppCompatActivity{

	protected ImageView addBtn,sureBtn;
	protected MagicIndicator magicIndicator;
	protected ViewPager viewPager;
	protected TextView activityName;
	protected LinearLayout bootmlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_chose_main);
	}

	void InitView()
	{
		addBtn = (ImageView)findViewById(R.id.activity_base_addBtn);
		sureBtn = (ImageView)findViewById(R.id.activity_base_sureBtn);
		sureBtn.setVisibility(View.INVISIBLE);
		activityName = (TextView)findViewById(R.id.activity_base_activityName);
		viewPager = (ViewPager)findViewById(R.id.activity_base_ViewPager);
		magicIndicator = (MagicIndicator)findViewById(R.id.activity_base_tablayout);
		bootmlayout = (LinearLayout)findViewById(R.id.member_bottomlayout);
	}

	void InitValue()
	{
		CommonNavigator commonNavigator = new CommonNavigator(this);
		commonNavigator.setAdjustMode(true);
		commonNavigator.setAdapter(new CommonNavigatorAdapter(){
			@Override
			public int getCount(){
				return 0;
			}

			@Override
			public IPagerTitleView getTitleView(Context context,int index){
				return null;
			}

			@Override
			public IPagerIndicator getIndicator(Context context){
				return null;
			}
		});
	}
}
