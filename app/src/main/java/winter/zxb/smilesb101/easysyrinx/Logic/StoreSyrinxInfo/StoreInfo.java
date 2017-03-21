package winter.zxb.smilesb101.easysyrinx.Logic.StoreSyrinxInfo;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItem;

/**
 * 项目名称：EasySyrinx
 * 类描述：存储希芸信息类
 * 创建人：SmileSB101
 * 创建时间：2017/2/2 0002 21:11
 * 修改人：Administrator
 * 修改时间：2017/2/2 0002 21:11
 * 修改备注：
 */

public class StoreInfo{
	/**
	 * 存储希芸信息到SharedPreferences文件中
	 * @param context 同步上下文
	 * @param syrinxItems 希芸商品（arraylist）
	 * @return
	 */
	public static boolean storeSyrinxInfoToSharedPreferencesFile(Context context,ArrayList<SyrinxItem> syrinxItems)
	{
		boolean flag = false;
		for(SyrinxItem item:syrinxItems)
		{
			SharedPreferences sharedPreferences = context.getSharedPreferences(item.getName(),Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString(SyrinxItem.NAME_STRING,item.getName());
			editor.putString(SyrinxItem.CODE_STRING,item.getCode());
			editor.putString(SyrinxItem.KIND_STRING,item.getKind());
			editor.putString(SyrinxItem.SERIES_STRING,item.getSeries());
			editor.putFloat(SyrinxItem.IN_PRICE_STRING,item.getIn_price());
			editor.putFloat(SyrinxItem.OUT_PRICE_STRING,item.getOut_price());
			editor.putString(SyrinxItem.INTRODUCE_STRING,item.getIntroduce());
			ArrayList<String> tags = item.getTagList();
			String s = "";
			for(int i = 0;i<tags.size();i++)
			{
				s += tags.get(i)+"||";
			}
			s = s.substring(s.lastIndexOf("||"),s.length());
			editor.putString(SyrinxItem.TAGLIST_STRING,s);
			editor.putString(SyrinxItem.FREEPOST_STRING,item.isFreePost());
			editor.apply();
			flag = true;
		}
		return flag;
	}
}
