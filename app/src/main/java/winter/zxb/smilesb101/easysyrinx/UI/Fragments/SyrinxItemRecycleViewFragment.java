package winter.zxb.smilesb101.easysyrinx.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItem;
import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItemRecycleViewAdapter;
import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxSeries;
import winter.zxb.smilesb101.easysyrinx.MainActivity;
import winter.zxb.smilesb101.easysyrinx.R;
import winter.zxb.smilesb101.easysyrinx.UI.Activity.SyrinxItemRecycleViewActivity;

/**
 * 项目名称：EasySyrinx
 * 类描述：希芸的详细条目展示碎片
 * 创建人：SmileSB101
 * 创建时间：2017/2/7 0007 16:37
 * 修改人：Administrator
 * 修改时间：2017/2/7 0007 16:37
 * 修改备注：
 */

public class SyrinxItemRecycleViewFragment extends Fragment{

	private final static String TAG = "SyrinxItemRecycleViewFragment";

	private View rootView;
	private RecyclerView recyclerView;
	private Context context;
	private SyrinxItemRecycleViewAdapter itemRecycleViewAdapter;
	private SyrinxSeries syrinxSeries;
	private ArrayList<SyrinxItem> syrinxItems;
	private SyrinxItemRecycleViewActivity activity;

	public SyrinxSeries getSyrinxSeries(){
		return syrinxSeries;
	}

	public void setSyrinxSeries(SyrinxSeries syrinxSeries){
		this.syrinxSeries = syrinxSeries;
	}

	public ArrayList<SyrinxItem> getSyrinxItems(){
		return syrinxItems;
	}

	public void setSyrinxItems(ArrayList<SyrinxItem> syrinxItems){
		this.syrinxItems = syrinxItems;
	}

	public AppCompatActivity GetActivity()
	{
		return activity;
	}

	public void setActivity(SyrinxItemRecycleViewActivity activity){
		this.activity = activity;
	}

	public SyrinxItemRecycleViewFragment(){
		rootView = null;
		syrinxItems = new ArrayList<>();
	}

	public static SyrinxItemRecycleViewFragment getInstance(SyrinxSeries series,ArrayList<SyrinxItem> list,SyrinxItemRecycleViewActivity appCompatActivity)
	{
		SyrinxItemRecycleViewFragment fragment = new SyrinxItemRecycleViewFragment();
		fragment.setSyrinxSeries(series);
		fragment.setSyrinxItems(list);
		fragment.setActivity(appCompatActivity);
		Log.i(TAG,"getInstance: 获取到SyrinxItem实例");
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		Log.i(TAG,"onCreateView: 创建View");
		rootView = inflater.inflate(R.layout.syrinxitem_recycleview_layout,container,false);
		recyclerView = (RecyclerView)rootView.findViewById(R.id.syrinxitem_layout_recycleView);
		context = container.getContext();
		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		recyclerView.setLayoutManager(layoutManager);
		if(syrinxItems==null)
		{
			syrinxItems = new ArrayList<>();
		}
		itemRecycleViewAdapter = new SyrinxItemRecycleViewAdapter(syrinxItems,activity);
		recyclerView.setAdapter(itemRecycleViewAdapter);
		return rootView;
	}
}
