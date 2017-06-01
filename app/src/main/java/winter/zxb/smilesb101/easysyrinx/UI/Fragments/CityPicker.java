package winter.zxb.smilesb101.easysyrinx.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import winter.zxb.smilesb101.easysyrinx.Data.CityInfo;
import winter.zxb.smilesb101.easysyrinx.Data.ProvinceInfo;
import winter.zxb.smilesb101.easysyrinx.Data.TownInfo;
import winter.zxb.smilesb101.easysyrinx.Net.HttpWork;
import winter.zxb.smilesb101.easysyrinx.R;

/**
 * 项目名称：EasySyrinx
 * 类描述：城市选择器
 * 创建人：SmileSB101
 * 创建时间：2017/5/26 0026 10:31
 * 修改人：Administrator
 * 修改时间：2017/5/26 0026 10:31
 * 修改备注：
 */

public class CityPicker extends Fragment implements View.OnClickListener{
	View view;
	Context context;
	ImageView backImage;
	TextView backBtn;
	RecyclerView recyclerView;
	RVAdapter adapter;

	String TAG = "CityPicker";

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		view = inflater.inflate(R.layout.city_picker_fragment_layout,container,false);
		context = container.getContext();
		recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		backBtn = (TextView)view.findViewById(R.id.backBtn);
		backImage = (ImageView)view.findViewById(R.id.backImage);
		backBtn.setOnClickListener(this);
		backImage.setOnClickListener(this);
		recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false));


		HttpWork.getChinaProvince(new HttpWork.ProvinceListener(){
			@Override
			public void onSuccess(List<ProvinceInfo> infoList){
				adapter = new RVAdapter(infoList,getChildFragmentManager());
				recyclerView.setAdapter(adapter);
			}

			@Override
			public void onError(String error){
				Log.e(TAG,"onError: ",new Throwable(error));
			}
		});
		return view;
	}

	@Override
	public void onClick(View v){
		switch(v.getId())
		{
			case R.id.backBtn:
			case R.id.backImage:

				break;
		}
	}

	class RVAdapter extends RecyclerView.Adapter{

		List<ProvinceInfo> provinceInfos;
		FragmentManager fragmentManager;
		Context context;

		List<CityInfo> cityInfos;
		List<TownInfo> townInfos;

		RVAdapter adapter;
		int ProvinceId;

		public void setCityInfos(List<CityInfo> cityInfos){
			this.cityInfos = cityInfos;
		}

		public void setTownInfos(List<TownInfo> townInfos){
			this.townInfos = townInfos;
		}

		public RVAdapter(List<ProvinceInfo> provinceInfos,FragmentManager fm)
		{
			this.provinceInfos = provinceInfos;
			fragmentManager = fm;
			adapter = this;
		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
			context = parent.getContext();
			View view = LayoutInflater.from(context)
					.inflate(R.layout.city_pick_item,parent,false);
			return new MyViewHolder(view);
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position){
			MyViewHolder myViewHolder = (MyViewHolder)holder;
			if(townInfos!=null)
			{
				//乡镇信息
				final TownInfo townInfo = townInfos.get(position);
				myViewHolder.itemName.setText(townInfo.getName());
				myViewHolder.rightArrow.setVisibility(View.INVISIBLE);
				myViewHolder.item.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v){

					}
				});
			}
			else if(cityInfos!=null)
			{
				//城市信息
				final CityInfo info = cityInfos.get(position);
				myViewHolder.item.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v){
						//根据id获取相应内容
						Log.e(TAG,"onBindViewHolder: "+ProvinceId+" "+info.getOwn_id());
						HttpWork.getChinaCity(ProvinceId,info.getOwn_id(),new HttpWork.TownListener(){
							@Override
							public void onSuccess(List<TownInfo> infoList){
								townInfos = infoList;
								adapter.setTownInfos(townInfos);
								adapter.notifyDataSetChanged();
							}

							@Override
							public void onError(String error){
								Log.e(TAG,"onError: ",new Throwable(error));
							}
						});

					}
				});
				myViewHolder.itemName.setText(info.getName());
			}
			else if(provinceInfos!=null)
			{
				//省份信息
				final ProvinceInfo info = provinceInfos.get(position);
				myViewHolder.item.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v){
						//根据id获取相应内容
						HttpWork.getChinaCity(info.getOwn_id(),new HttpWork.CityListener(){
							@Override
							public void onSuccess(List<CityInfo> infoList){
								ProvinceId = position+1;//赋值省份id
								cityInfos = infoList;
								adapter.setCityInfos(infoList);
								adapter.notifyDataSetChanged();
							}

							@Override
							public void onError(String error){
								Log.e(TAG,"onError: ",new Throwable(error));
							}
						});

					}
				});
				myViewHolder.itemName.setText(info.getName());
			}
		}

		@Override
		public int getItemCount(){
			if(townInfos != null)
			{
				return townInfos.size();
			}
			else if(cityInfos!=null)
			{
				return cityInfos.size();
			}
			else if(provinceInfos!=null) {
				return provinceInfos.size();
			}
			return 0;
		}

		class MyViewHolder extends RecyclerView.ViewHolder{
			LinearLayout item;
			TextView itemName;
			ImageView rightArrow;
			public MyViewHolder(View itemView){
				super(itemView);
				item = (LinearLayout)itemView.findViewById(R.id.item);
				itemName = (TextView)itemView.findViewById(R.id.itemName);
				rightArrow = (ImageView)itemView.findViewById(R.id.rightArrow);
			}
		}
	}
}
