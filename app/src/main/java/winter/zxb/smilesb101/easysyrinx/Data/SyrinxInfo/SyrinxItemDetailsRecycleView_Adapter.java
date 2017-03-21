package winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.R;

/**
 * 项目名称：EasySyrinx
 * 类描述：商品详情页面的recycleviewadapter
 * 创建人：SmileSB101
 * 创建时间：2017/2/13 0013 14:02
 * 修改人：Administrator
 * 修改时间：2017/2/13 0013 14:02
 * 修改备注：
 */

public class SyrinxItemDetailsRecycleView_Adapter extends RecyclerView.Adapter<SyrinxItemDetailsRecycleView_Adapter.ViewHolder>{
	private ArrayList<String> title;
	private ArrayList<String> arrayList;
	private static Context context;
	private View rootView;

	public SyrinxItemDetailsRecycleView_Adapter(ArrayList<String> title,ArrayList<String> arrayList){
		this.title = title;
		this.arrayList = arrayList;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		context = parent.getContext();
		rootView = LayoutInflater.from(context)
				.inflate(R.layout.itemdetails_recycleview_item,parent,false);
		return new ViewHolder(rootView);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder,int position){
		holder.title.setText(title.get(position));
		holder.content.setText(arrayList.get(position));
	}

	@Override
	public int getItemCount(){
		return arrayList.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder{
		private TextView title;
		private TextView content;
		private View rootView;
		public ViewHolder(View itemView){
			super(itemView);
			rootView = itemView;
			title = (TextView)rootView.findViewById(R.id.itemdetails_item_title);
			content = (TextView)rootView.findViewById(R.id.itemdetails_item_content);
		}
	}
}
