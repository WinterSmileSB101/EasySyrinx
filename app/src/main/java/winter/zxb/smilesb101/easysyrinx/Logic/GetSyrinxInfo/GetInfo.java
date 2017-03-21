package winter.zxb.smilesb101.easysyrinx.Logic.GetSyrinxInfo;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItem;

/**
 * 项目名称：EasySyrinx
 * 类描述：获取希芸信息
 * 创建人：SmileSB101
 * 创建时间：2017/2/2 0002 21:39
 * 修改人：Administrator
 * 修改时间：2017/2/2 0002 21:39
 * 修改备注：
 */

public class GetInfo{
	/**
	 * 通过名称在SharedPreferences文件中查询商品
	 * @param context
	 * @param name 希芸商品名称
	 * @return
	 */
	public static SyrinxItem getSyrinxInfoThrowSharedPreferencesByName(Context context,String name)
	{
		SharedPreferences preferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
		ArrayList<String> taglist = new ArrayList<>();
		String[] strings = preferences.getString(SyrinxItem.TAGLIST_STRING,"").split("||");//有可能有问题
		for(String s:strings)
		{
			taglist.add(s);
		}
		/*return new SyrinxItem(preferences.getString(SyrinxItem.CODE_STRING,""),preferences.getString(SyrinxItem.NAME_STRING,""),preferences.getString(SyrinxItem.KIND_STRING,""),preferences.getString(SyrinxItem.SERIES_STRING,""),preferences.getFloat(SyrinxItem.IN_PRICE_STRING,0),preferences.getFloat(SyrinxItem.OUT_PRICE_STRING,0),preferences.getString(SyrinxItem.INTRODUCE_STRING,""),taglist,preferences.getBoolean(SyrinxItem.FREEPOST_STRING,false));*/
		return null;
	}
}
