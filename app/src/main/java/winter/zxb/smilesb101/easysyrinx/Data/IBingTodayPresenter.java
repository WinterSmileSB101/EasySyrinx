package winter.zxb.smilesb101.easysyrinx.Data;

import android.util.Log;

import winter.zxb.smilesb101.easysyrinx.UI.IWelcomeView;


/**
 * 项目名称：CoderHome
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/6/1 0001 08:13
 * 修改人：Administrator
 * 修改时间：2017/6/1 0001 08:13
 * 修改备注：
 */

public class IBingTodayPresenter{
	IWelcomeView iWelcomeView;

	String TAG = "IBingTodayP";

	public IBingTodayPresenter(IWelcomeView iWelcomeView){
		this.iWelcomeView = iWelcomeView;
	}

	public void getBingToday()
	{
		Log.i(TAG,"getBingToday: 获取每日图片");
		IBingTodayModel.I_BING_TODAY_MODEL.getBingToday(new IBingTodayModel.BingTodayListener(){
			@Override
			public void onSuccess(BingToday bingToday){
				iWelcomeView.ShowTodayBing(bingToday);
			}

			@Override
			public void onError(String error){
				iWelcomeView.onError(error);
			}
		});
	}
}
