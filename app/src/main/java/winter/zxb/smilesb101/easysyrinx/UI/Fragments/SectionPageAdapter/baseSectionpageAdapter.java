package winter.zxb.smilesb101.easysyrinx.UI.Fragments.SectionPageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * 项目名称：EasySyrinx
 * 类描述：基类
 * 创建人：SmileSB101
 * 创建时间：2017/2/25 0025 21:18
 * 修改人：Administrator
 * 修改时间：2017/2/25 0025 21:18
 * 修改备注：
 */

public class baseSectionpageAdapter extends FragmentPagerAdapter{

	private ArrayList<Fragment> fragmentArrayList;
	private ArrayList<String> titles;

	public baseSectionpageAdapter(FragmentManager fm,ArrayList<Fragment> fragmentArrayList,ArrayList<String> titles,ImageView toolbar_sureBtn){
		super(fm);
		this.fragmentArrayList = fragmentArrayList;
		this.titles = titles;
	}

	@Override
	public Fragment getItem(int position){
		return fragmentArrayList.get(position);
	}

	@Override
	public int getCount(){
		return fragmentArrayList.size();
	}

	@Override
	public CharSequence getPageTitle(int position){
		return titles.get(position);
	}
}
