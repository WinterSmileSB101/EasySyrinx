package winter.zxb.smilesb101.easysyrinx.UI.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import winter.zxb.smilesb101.easysyrinx.R;

/**
 * 项目名称：EasySyrinx
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/2/25 0025 21:39
 * 修改人：Administrator
 * 修改时间：2017/2/25 0025 21:39
 * 修改备注：
 */

public class memberChoseFragment extends Fragment{

	private static final String ARG_SECTION_NUM = "num";
	private RecyclerView recyclerView;
	private View rootView;
	private CheckBox checkAllBtn;
	private Button shareBtn;
	private int pageNum;
	/**
	 * 顶部的确定按钮，来自于活动
	 */
	private ImageView toolbar_sureBtn;
	private LinearLayout member_bottomlayout;

	public static memberChoseFragment newInstance(int section_num,ImageView toolbar_sureBtn,LinearLayout member_bottomlayout)
	{
		memberChoseFragment fragment = new memberChoseFragment();
		fragment.setToolbar_sureBtn(toolbar_sureBtn);
		fragment.setMember_bottomlayout(member_bottomlayout);
		Bundle bundle = new Bundle();
		bundle.putInt(ARG_SECTION_NUM,section_num);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		rootView = inflater.inflate(R.layout.member_chose_fragment_layout,container,false);
		pageNum = getArguments().getInt(ARG_SECTION_NUM);
		return rootView;
	}

	public void setToolbar_sureBtn(ImageView toolbar_sureBtn){
		this.toolbar_sureBtn = toolbar_sureBtn;
	}


	public void setMember_bottomlayout(LinearLayout member_bottomlayout){
		this.member_bottomlayout = member_bottomlayout;
	}

	void InitView()
	{
		if(rootView!=null) {
			recyclerView = (RecyclerView)rootView.findViewById(R.id.member_RecyclerView);
			checkAllBtn = (CheckBox)rootView.findViewById(R.id.member_choseAll);
			shareBtn = (Button)rootView.findViewById(R.id.member_shareBtn);
			member_bottomlayout = (LinearLayout)rootView.findViewById(R.id.member_bottomlayout);
			if(pageNum==0)
			{
				checkAllBtn.setVisibility(View.INVISIBLE);
				shareBtn.setVisibility(View.INVISIBLE);
				toolbar_sureBtn.setVisibility(View.INVISIBLE);
				member_bottomlayout.setVisibility(View.GONE);
			}
			else
			{
				checkAllBtn.setVisibility(View.VISIBLE);
				shareBtn.setVisibility(View.VISIBLE);
				toolbar_sureBtn.setVisibility(View.VISIBLE);
				member_bottomlayout.setVisibility(View.VISIBLE);
			}
		}
	}
}
