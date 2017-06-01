package winter.zxb.smilesb101.easysyrinx.UI;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * 项目名称：EasySyrinx
 * 类描述：不滑动的lm
 * 创建人：SmileSB101
 * 创建时间：2017/5/25 0025 09:51
 * 修改人：Administrator
 * 修改时间：2017/5/25 0025 09:51
 * 修改备注：
 */

public class NotScrollLinearLayoutManager extends LinearLayoutManager{
	public NotScrollLinearLayoutManager(Context context){
		super(context);
	}

	public NotScrollLinearLayoutManager(Context context,AttributeSet attrs,int defStyleAttr,int defStyleRes){
		super(context,attrs,defStyleAttr,defStyleRes);
	}

	public NotScrollLinearLayoutManager(Context context,int orientation,boolean reverseLayout){
		super(context,orientation,reverseLayout);
	}

	@Override
	public boolean canScrollVertically(){
		return false;
	}

	@Override
	public boolean canScrollHorizontally(){
		return false;
	}
}
