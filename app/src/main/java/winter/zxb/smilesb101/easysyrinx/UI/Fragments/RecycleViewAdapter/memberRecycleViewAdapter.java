package winter.zxb.smilesb101.easysyrinx.UI.Fragments.RecycleViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.R;

/**
 * 项目名称：EasySyrinx
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/2/25 0025 22:27
 * 修改人：Administrator
 * 修改时间：2017/2/25 0025 22:27
 * 修改备注：
 */

public class memberRecycleViewAdapter extends RecyclerView.Adapter{

	private ArrayList<String> members;
	private Context context;
	private int pagePos;

	public memberRecycleViewAdapter(ArrayList<String> members,int pagePos,Context context){
		this.members = members;
		this.pagePos = pagePos;
		this.context = context;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
		ViewHolder holder1 = (ViewHolder)holder;
		if(pagePos==0)
		{
			holder1.checkBox.setVisibility(View.VISIBLE);
			holder1.shareMoney.setVisibility(View.VISIBLE);
		}
		else
		{
			holder1.shareMoney.setVisibility(View.INVISIBLE);
			holder1.checkBox.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public int getItemCount(){
		return members.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder{

		private CheckBox checkBox;
		private TextView shareMoney;
		private TextView member_Name;
		private View rootView;

		public ViewHolder(View itemView){
			super(itemView);
			rootView = itemView;
			checkBox = (CheckBox)rootView.findViewById(R.id.member_checkBox);
			shareMoney = (TextView)rootView.findViewById(R.id.member_shareMoney);
			member_Name = (TextView)rootView.findViewById(R.id.member_Name);
		}
	}
}
