package winter.zxb.smilesb101.easysyrinx.UI.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import winter.zxb.smilesb101.easysyrinx.R;

/**
 * 项目名称：EasySyrinx
 * 类描述：找出希芸对应产品的价格目前是金泰的还不能添加
 * 创建人：SmileSB101
 * 创建时间：2017/2/1 0001 20:55
 * 修改人：Administrator
 * 修改时间：2017/2/1 0001 20:55
 * 修改备注：
 */

public class FindPricesOfSyrinx extends Fragment implements View.OnClickListener{

	TextView showResultText;
	EditText findText;
	Button findButton;
	View rootView;

	public FindPricesOfSyrinx(){
		this.rootView = null;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.findsyrinx_main,container,false);
		showResultText = (TextView)v.findViewById(R.id.findsyrinx_main_price);
		findButton = (Button)v.findViewById(R.id.findsyrinx_main_findbtn);
		findText = (EditText)v.findViewById(R.id.findsyrinx_main_findstr);
		return v;
	}

	/**
	 * 点击事件处理
	 * @param v
	 */
	@Override
	public void onClick(View v){
		switch(v.getId())
		{
			case R.id.findsyrinx_main_findbtn:
				//按下查询开始查询
				break;
			default:break;
		}
	}
}
