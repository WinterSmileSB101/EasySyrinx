package winter.zxb.smilesb101.easysyrinx.UI.SelfView;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.ShoppingCart;
import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.ShoppingCartList;
import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItem;
import winter.zxb.smilesb101.easysyrinx.R;

/**
 * 项目名称：EasySyrinx
 * 类描述：弹出框
 * 创建人：SmileSB101
 * 创建时间：2017/5/18 0018 12:57
 * 修改人：Administrator
 * 修改时间：2017/5/18 0018 12:57
 * 修改备注：
 */

public class CartPopWindow extends PopupWindow implements View.OnClickListener{
	String TAG = "CartPopWindow";

	private Context context;

	private TextView isFreePost,price,deleteAll;
	private RecyclerView recyclerView;

	private View view;

	RVAdapter adapter;

	public CartPopWindow(Activity activity,int layoutID)
	{
		this.context = activity;
		view = LayoutInflater.from(context)
				.inflate(layoutID,null);
		isFreePost = (TextView)view.findViewById(R.id.IsFreePost);
		price = (TextView)view.findViewById(R.id.price);
		deleteAll = (TextView)view.findViewById(R.id.deleteAll);

		recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
		recyclerView.addItemDecoration(new TitleItemDecortion(context,ShoppingCartList.SHOPPING_CART_LIST.getAllSeriesArray()));

		adapter = new RVAdapter(this);
		recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
		recyclerView.setAdapter(adapter);

		this.setOutsideTouchable(true);
		this.setContentView(view);

		this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
		this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

		this.setFocusable(true);

		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置弹出窗体的背景
		this.setBackgroundDrawable(dw);

		// 设置弹出窗体显示时的动画，从底部向上弹出
		this.setAnimationStyle(R.style.cart_pop_ani);

		price.setText("￥ "+ShoppingCartList.SHOPPING_CART_LIST.getPrices());

	}

	@Override
	public void onClick(View v){

	}
	class RVAdapter extends RecyclerView.Adapter{

		CartPopWindow window;

		public RVAdapter(CartPopWindow window){
			this.window = window;
		}

		@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		View view1 = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.cart_pop_rv_item,parent,false);
		return new MyViewHolder(view1);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position){
		final MyViewHolder holder1 = (MyViewHolder)holder;
		final SyrinxItem item = ShoppingCartList.SHOPPING_CART_LIST.getSyrinxItem(position);
		final int itemNum = ShoppingCartList.SHOPPING_CART_LIST.getSyrinxItemNum(position);
		final float itemPrice = ShoppingCartList.SHOPPING_CART_LIST.getSyrinxItemPrice(position);
		if(item!=null) {
			holder1.syrinx_name.setText(item.getName());
			Glide.with(context)
					.load(item.getImageId())
					.into(holder1.syrinx_image);
			if(itemNum > -1) {
				holder1.item_num.setText(itemNum + "");
				holder1.price.setText("￥ " + itemPrice);//设置此条目的钱
				if(itemNum == 0) {
					holder1.reduceBtn.setVisibility(View.INVISIBLE);
				} else {
					holder1.reduceBtn.setVisibility(View.VISIBLE);
				}

				window.price.setText("￥ " + itemPrice);

			}

			final View.OnClickListener listener = new View.OnClickListener(){
				@Override
				public void onClick(View v){
					switch(v.getId())
					{
						case R.id.reduceBtn:
							//减少
							//ShoppingCartList.SHOPPING_CART_LIST.reduceItemNum(item);
							ShoppingCartList.SHOPPING_CART_LIST.reduceItemNum(item);
							notifyDataSetChanged();//必须要有，更新界面
							break;
						case R.id.addBtn:
							Log.e(TAG,"onClick: 增加");
							//增加
							int index = ShoppingCartList.SHOPPING_CART_LIST.getIndexByCartName(item.getSeries());
							if(index!=-1) {
								//存在这个记录
								ShoppingCart cart = ShoppingCartList.SHOPPING_CART_LIST.getShoppingCart(index);
								cart.addSyrinxItem(item);
								cart.addPrices(item.getOut_price());
							}
							else {
								//不存在这个记录
								ShoppingCart cart = new ShoppingCart();
								cart.setCartName(item.getSeries());
								cart.addSyrinxItem(item);
								cart.addPrices(item.getOut_price());
								ShoppingCartList.SHOPPING_CART_LIST.addShoppingCart(cart);
							}
							//更新界面
							notifyDataSetChanged();
							break;
					}
				}
			};

			holder1.reduceBtn.setOnClickListener(listener);
			holder1.addBtn.setOnClickListener(listener);

		}

	}

	@Override
	public int getItemCount(){
		return ShoppingCartList.SHOPPING_CART_LIST.getSyrinxItemNum();
	}

	class MyViewHolder extends RecyclerView.ViewHolder{

		TextView syrinx_name,price,item_num;
		ImageView syrinx_image,reduceBtn,addBtn;

		public MyViewHolder(View itemView){
			super(itemView);
			syrinx_name = (TextView)itemView.findViewById(R.id.syrinx_name);
			price = (TextView)itemView.findViewById(R.id.price);
			syrinx_image = (ImageView)itemView.findViewById(R.id.syrinx_image);
			reduceBtn = (ImageView)itemView.findViewById(R.id.reduceBtn);
			addBtn = (ImageView)itemView.findViewById(R.id.addBtn);
			item_num = (TextView)itemView.findViewById(R.id.item_num);
		}
	}
}
}
