package winter.zxb.smilesb101.easysyrinx.Logic.Help;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItem;

/**
 * 项目名称：EasySyrinx
 * 类描述：静态的帮助类
 * 创建人：SmileSB101
 * 创建时间：2017/2/1 0001 22:25
 * 修改人：Administrator
 * 修改时间：2017/2/1 0001 22:25
 * 修改备注：
 */

public class help{

	public static float addAllSyrinxItem(ArrayList<SyrinxItem> items)
	{
		float sum = 0f;
		for(SyrinxItem item:items)
		{
			sum += item.getOut_price();
		}
		return sum;
	}

}
