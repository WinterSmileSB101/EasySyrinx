package winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.R;
import winter.zxb.smilesb101.easysyrinx.UI.Activity.SyrinxItemDetailsActivity;
import winter.zxb.smilesb101.easysyrinx.UI.Activity.SyrinxItemRecycleViewActivity;

/**
 * 项目名称：EasySyrinx
 * 类描述：希芸recycleviewadapter
 * 创建人：SmileSB101
 * 创建时间：2017/2/7 0007 16:14
 * 修改人：Administrator
 * 修改时间：2017/2/7 0007 16:14
 * 修改备注：
 */

public class SyrinxItemRecycleViewAdapter extends RecyclerView.Adapter<SyrinxItemRecycleViewAdapter.ViewHolder>{
	private static final String TAG = "SyrinxRecycleView";
	private ArrayList<SyrinxItem> syrinxItemArrayList;
	private static Context context;
	private SyrinxItemRecycleViewActivity activity;

	private clickListener listener;

	public interface clickListener{
		void clickListener(View view,ImageView imageView,ImageView reduceBtn,SyrinxItem syrinxItem);
	}

	public SyrinxItemRecycleViewAdapter(ArrayList<SyrinxItem> syrinxItemArrayList,SyrinxItemRecycleViewActivity activity){
		this.syrinxItemArrayList = syrinxItemArrayList;
		this.activity = activity;
	}

	public clickListener getListener(){
		return listener;
	}

	public void setListener(clickListener listener){
		this.listener = listener;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		context = parent.getContext();
		View view = LayoutInflater.from(context)
				.inflate(R.layout.syrinxitem_recycleview_item,parent,false);
		final ViewHolder holder = new ViewHolder(view);
		holder.setListener(activity);//设置监听
		holder.reduceBtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				SyrinxItem syrinxItem = syrinxItemArrayList.get(holder.getAdapterPosition());
				//设置数量
				if(!holder.pubchaseNum.getText().equals("")&&!holder.pubchaseNum.getText().equals("0"))
				{
					int temp = Integer.parseInt(holder.pubchaseNum.getText().toString())-1;
					Log.e(TAG,"onClick: 数量："+temp);
					if(temp!=0) {
						holder.pubchaseNum.setText(temp + "");
						holder.reduceView.setVisibility(View.VISIBLE);//在这里控制提示图标的显示
					}
					else
					{
						holder.pubchaseNum.setText("");
						holder.reduceView.setVisibility(View.INVISIBLE);//在这里控制提示图标的隐藏
					}
					listener.clickListener(v,holder.reduceView,holder.reduceBtn,syrinxItem);//提交外部
				}
				else
				{
					holder.pubchaseNum.setText("");
				}
			}
		});
		holder.addBtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				SyrinxItem syrinxItem = syrinxItemArrayList.get(holder.getAdapterPosition());
				//设置数量
				if(!holder.pubchaseNum.getText().equals(""))
				{
					holder.pubchaseNum.setText(Integer.parseInt(holder.pubchaseNum.getText().toString())+1+"");
				}
				else
				{
					holder.pubchaseNum.setText("1");
				}
				listener.clickListener(v,holder.addImage,holder.reduceBtn,syrinxItem);//提交外部
			}
		});
		holder.rootView.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				//打开此产品的详情页面
				//Toast.makeText(context,"进入产品详情页面（有bug待处理）",Toast.LENGTH_SHORT).show();
				SyrinxItem syrinxItem = syrinxItemArrayList.get(holder.getAdapterPosition());
				Intent intent = new Intent(context,SyrinxItemDetailsActivity.class);
				intent.putExtra(SyrinxItemDetailsActivity.SYRINXITEM_KEY,syrinxItem);
				if(android.os.Build.VERSION.SDK_INT > 20) {
					context.startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(activity,holder.item_Image,"syrinxImage").toBundle());
				} else {
					context.startActivity(intent);
				}
			}
		});
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder,int position){
		SyrinxItem syrinxItem = syrinxItemArrayList.get(position);
		holder.item_Name.setText(syrinxItem.getName());
		holder.item_Code.setText(syrinxItem.getCode());
		holder.item_Format.setText(syrinxItem.getFormat());
		holder.item_outPrice.setText(syrinxItem.getOut_price() + "");
		holder.item_inPrice.setText(syrinxItem.getIn_price() + "");
		holder.item_Interest.setText((syrinxItem.getOut_price() - syrinxItem.getIn_price()) + "");
		holder.item_freePost.setText(syrinxItem.isFreePost());

		//设置显示此商品的购买数量等
		int num = ShoppingCartList.SHOPPING_CART_LIST.getSyrinxItemNumByName(syrinxItem.getName());
		Log.e(TAG,"onBindViewHolder: 数量："+num);
		if(num>0) {
			holder.pubchaseNum.setText(num+"");
			holder.reduceBtn.setVisibility(View.VISIBLE);
		}
		else
		{
			holder.pubchaseNum.setText("");
			holder.reduceBtn.setVisibility(View.INVISIBLE);
		}

		Glide.with(context)
				.load(syrinxItem.getImageId())
				.into(holder.item_Image);
		Glide.with(context)
				.load(syrinxItem.getImageId())
				.into(holder.addImage);
	}

	@Override
	public int getItemCount(){
		return syrinxItemArrayList.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder{

		private ImageView item_Image;
		private TextView item_Name;
		private TextView item_Code;
		private TextView item_Format;
		private TextView item_outPrice;
		private TextView item_inPrice;
		private TextView item_Interest;
		private TextView item_freePost;
		private View rootView;

		private ImageView addImage, addBtn,reduceBtn,reduceView;
		private TextView pubchaseNum;
		private SyrinxItemRecycleViewAdapter.clickListener listener;

		public ViewHolder(View itemView){
			super(itemView);
			rootView = itemView;
			item_Image = (ImageView)rootView.findViewById(R.id.syrinx_image);
			item_Name = (TextView)rootView.findViewById(R.id.syrinx_name);
			item_Code = (TextView)rootView.findViewById(R.id.syrinx_code);
			item_Format = (TextView)rootView.findViewById(R.id.syrinx_format);
			item_outPrice = (TextView)rootView.findViewById(R.id.syrinx_outPrice);
			item_inPrice = (TextView)rootView.findViewById(R.id.syrinx_inPrice);
			item_Interest = (TextView)rootView.findViewById(R.id.syrinx_interest);
			item_freePost = (TextView)rootView.findViewById(R.id.syrinx_freePost);

			addImage = (ImageView)rootView.findViewById(R.id.addImg);
			addBtn = (ImageView)rootView.findViewById(R.id.addBtn);
			pubchaseNum = (TextView)rootView.findViewById(R.id.puchase_num);
			reduceBtn = (ImageView)rootView.findViewById(R.id.reduceBtn);
			reduceView = (ImageView)rootView.findViewById(R.id.reduceView);
		}

		public clickListener getListener(){
			return listener;
		}

		public void setListener(clickListener listener){
			this.listener = listener;
		}
	}
}
