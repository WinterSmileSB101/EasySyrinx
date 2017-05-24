package winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.MainActivity;
import winter.zxb.smilesb101.easysyrinx.R;
import winter.zxb.smilesb101.easysyrinx.UI.Activity.SyrinxItemRecycleViewActivity;
import winter.zxb.smilesb101.easysyrinx.UI.Fragments.SyrinxItemRecycleViewFragment;

/**
 * 项目名称：EasySyrinx
 * 类描述：希芸recycleview的Adapter
 * 创建人：SmileSB101
 * 创建时间：2017/2/7 0007 11:34
 * 修改人：Administrator
 * 修改时间：2017/2/7 0007 11:34
 * 修改备注：
 */

public class SyrinxSeriesRecycleViewAdapter extends RecyclerView.Adapter<SyrinxSeriesRecycleViewAdapter.ViewHolder>{
	private static final String TAG = "SeriesAdapter";

    private static ArrayList<SyrinxSeries> syrinxSeriesArrayList;
	private Context context;
	private AppCompatActivity activity;

	public SyrinxSeriesRecycleViewAdapter(ArrayList<SyrinxSeries> syrinxSeriesArrayList,AppCompatActivity appCompatActivity){
		this.syrinxSeriesArrayList = syrinxSeriesArrayList;
		this.activity = appCompatActivity;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		context = parent.getContext();
		final View view = LayoutInflater.from(context)
				.inflate(R.layout.syrinxseries_recycleview_item,parent,false);
		final ViewHolder holder = new ViewHolder(view);
		holder.rootView.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				int position = holder.getAdapterPosition();//获取到当前按下位置
				SyrinxSeries series = syrinxSeriesArrayList.get(position);//获取到当前的条目
				//跳转到希芸每个物品展示碎片
				ArrayList<SyrinxItem> list = new ArrayList<SyrinxItem>();
				switch(position)
				{
					case 0:
						list = MainActivity.syrinxItemArrayList001;
						break;
					case 1:
						list = MainActivity.syrinxItemArrayList002;
						break;
					case 2:
						list = MainActivity.syrinxItemArrayList003;
						break;
					case 3:
						list = MainActivity.syrinxItemArrayList004;
						break;
					case 4:
						list = MainActivity.syrinxItemArrayList005;
						break;
					case 5:
						list = MainActivity.syrinxItemArrayList006;
						break;
					case 6:
						list = MainActivity.syrinxItemArrayList007;
						break;
					case 7:
						list = MainActivity.syrinxItemArrayList008;
						break;
					case 8:
						list = MainActivity.syrinxItemArrayList009;
						break;
					case 9:
						list = MainActivity.syrinxItemArrayList010;
						break;
					case 10:
						list = MainActivity.syrinxItemArrayList011;
						break;
					case 11:
						list = MainActivity.syrinxItemArrayList012;
						break;
					case 12:
						list = MainActivity.syrinxItemArrayList013;
						break;
				}
				//SyrinxItemRecycleViewFragment fragment = SyrinxItemRecycleViewFragment.getInstance(series,list,activity);
				//activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();//开始跳转
				Intent intent = new Intent(activity,SyrinxItemRecycleViewActivity.class);
				intent.putExtra(SyrinxItemRecycleViewActivity.SYRINXLIST_VALUE,list);
				intent.putExtra(SyrinxItemRecycleViewActivity.SYRINXSERIES_VALUE,series);
				if(android.os.Build.VERSION.SDK_INT > 20)
				{
					//5.0以上
					activity.startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(activity,holder.seriesImage,"seriesImage").toBundle());
					Log.i(TAG,"onClick: 共享元素");
				}
				else {
					activity.startActivity(intent);
				}
			}
		});
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder,int position){
		SyrinxSeries series = syrinxSeriesArrayList.get(position);
		holder.freePost.setText(series.isFreePost());
		holder.seriesName.setText(series.getName());
		holder.seriesNum.setText(series.getNum()+"");
		Glide.with(context)
				.load(series.getImageUrl())
				.into(holder.seriesImage);

	}

	@Override
	public int getItemCount(){
		return syrinxSeriesArrayList.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder{
		ImageView seriesImage;
		TextView seriesName;
		TextView seriesNum;
		TextView freePost;
		View rootView;
		public ViewHolder(View itemView){
			super(itemView);
			rootView = itemView;
			seriesImage = (ImageView)rootView.findViewById(R.id.syrinxSeries_image);
			seriesName = (TextView)rootView.findViewById(R.id.syrinxSeries_name);
			seriesNum = (TextView)rootView.findViewById(R.id.syrinxSeries_num);
			freePost = (TextView)rootView.findViewById(R.id.syrinxSeries_freePost);
		}
	}


}
