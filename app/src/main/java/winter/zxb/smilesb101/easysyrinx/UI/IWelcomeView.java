package winter.zxb.smilesb101.easysyrinx.UI;


import winter.zxb.smilesb101.easysyrinx.Data.BingToday;

/**
 * 项目名称：CoderHome
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/6/1 0001 08:15
 * 修改人：Administrator
 * 修改时间：2017/6/1 0001 08:15
 * 修改备注：
 */

public interface IWelcomeView{
	void ShowTodayBing(BingToday bingToday);
	void onError(String error);
}
