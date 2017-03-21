package winter.zxb.smilesb101.easysyrinx.UI.calculationUI;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.UI.Fragments.SectionPageAdapter.baseSectionpageAdapter;
import winter.zxb.smilesb101.easysyrinx.UI.Fragments.memberChoseFragment;

public class memberChoseActivity extends baseChoseActivityMain{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	void InitValue(){
		ArrayList<String> titles = new ArrayList<>();
		titles.add("单人");
		titles.add("多人");

		ArrayList<memberChoseFragment> fragments = new ArrayList<>();
		fragments.add(memberChoseFragment.newInstance(0,addBtn,bootmlayout));
		fragments.add(memberChoseFragment.newInstance(1,addBtn,bootmlayout));

		baseSectionpageAdapter adapter = new baseSectionpageAdapter(getSupportFragmentManager(),fragments,titles,sureBtn);
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
