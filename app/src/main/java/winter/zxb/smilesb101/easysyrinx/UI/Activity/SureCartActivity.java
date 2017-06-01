package winter.zxb.smilesb101.easysyrinx.UI.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.ShoppingCart;
import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.ShoppingCartList;
import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItem;
import winter.zxb.smilesb101.easysyrinx.Data.UserAddressInfo;
import winter.zxb.smilesb101.easysyrinx.MainActivity;
import winter.zxb.smilesb101.easysyrinx.R;

/**
 * 项目名称：EasySyrinx
 * 类描述：购物确定界面
 * 创建人：SmileSB101
 * 创建时间：2017/5/23 0023 16:25
 * 修改人：Administrator
 * 修改时间：2017/5/23 0023 16:25
 * 修改备注：
 */

public class SureCartActivity extends AppCompatActivity implements View.OnClickListener{

	String TAG = "SureCartActivity";

	ActionBar actionBar;
	RecyclerView recyclerView;
	RVAdapter adapter;

	TextView price;
	RelativeLayout sureBtn;
	LinearLayout buyerInfo;
	CheckBox buyerFuKuan;
	Context context;

	TextView whoGetGoods,whoGetPhone,whoGetAdderess;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sure_cart_layout);
		context = this;
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		actionBar = getSupportActionBar();
		actionBar.setTitle("确认订单");
		actionBar.setDisplayHomeAsUpEnabled(true);

		/**
		 * 设置默认的地址，从数据库获取
		 */
		whoGetGoods = (TextView)findViewById(R.id.whoGetGoods);
		whoGetPhone = (TextView)findViewById(R.id.whoGetPhone);
		whoGetAdderess = (TextView)findViewById(R.id.whoGetAdderess);
		UserAddressInfo info = new UserAddressInfo("张孝波","13194889618",true,"成都");
		whoGetGoods.setText(info.getUserName());
		whoGetPhone.setText(info.getUserPhone());
		whoGetAdderess.setText(info.getUserAddress());

		price = (TextView)findViewById(R.id.price);
		sureBtn = (RelativeLayout)findViewById(R.id.sureBtn);
		buyerInfo = (LinearLayout)findViewById(R.id.buyerInfo);
		sureBtn.setOnClickListener(this);
		buyerInfo.setOnClickListener(this);
//		buyerInfo.setOnTouchListener(new View.OnTouchListener(){
//			@Override
//			public boolean onTouch(View v,MotionEvent event){
//				buyerInfo.setBackgroundResource(R.drawable.round_rect_gray);
//				if(event.getAction()==MotionEvent.ACTION_CANCEL)
//				{
//					//时间取消，恢复原图
//					buyerInfo.setBackgroundResource(R.drawable.round_rect_write);
//				}
//				return false;
//			}
//		});

		buyerFuKuan = (CheckBox)findViewById(R.id.buyerFuKuan);
		price.setText("￥ "+ShoppingCartList.SHOPPING_CART_LIST.getPrices());

		recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
		adapter = new RVAdapter();
		recyclerView.setAdapter(adapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v){
		switch(v.getId())
		{
			case R.id.buyerInfo://用户地址信息
				Log.e(TAG,"onClick: 用户信息");
				Intent intent = new Intent(context,AddressActivity.class);
				startActivity(intent);
				break;
			case R.id.sureBtn://提交订单

				//保存数据，待完成，先保存到一个xm文件中

				//清空临时数据
				ShoppingCartList.SHOPPING_CART_LIST.cleraShoppingCart();

				AlertDialog dialog = new AlertDialog.Builder(this).create();
				dialog.setTitle("提交订单成功！");
				dialog.setButton(AlertDialog.BUTTON_NEGATIVE,"返回",new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog,int which){
						//返回到主界面,这里主界面设置成singleTask，是为了保证返回逻辑的正确
						Intent intent = new Intent(context,MainActivity.class);
						startActivity(intent);
					}
				});
				dialog.setButton(AlertDialog.BUTTON_POSITIVE,"查看订单",new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog,int which){
						//跳转到订单管理界面
				}
				});
				dialog.show();
				break;
		}
	}

	/**
	 * 每个cart的recycler
	 */
	class RVAdapter extends RecyclerView.Adapter{

		Context context;

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
			context = parent.getContext();
			View view = LayoutInflater.from(context)
					.inflate(R.layout.sure_rv_item_layout,parent,false);
			return new MyViewHolder(view);
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
			MyViewHolder myViewHolder = (MyViewHolder)holder;
			ShoppingCart cart = ShoppingCartList.SHOPPING_CART_LIST.getShoppingCart(position);
			SyrinxItem item = cart.getItem(0);
			Glide.with(context)
			.load(item.getImageId())
			.into(myViewHolder.seriesImage);
			myViewHolder.position.setText((position+1)+".");
			myViewHolder.seriesName.setText(item.getSeries());
			myViewHolder.itemNum.setText("共 "+cart.getNum()+" 件商品");
			myViewHolder.SeriesPrices.setText("￥ "+cart.getPrice());

			myViewHolder.itemRv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
			myViewHolder.itemRv.setAdapter(new ItemRecyclerAdapter(cart));
		}

		@Override
		public int getItemCount(){
			return ShoppingCartList.SHOPPING_CART_LIST.getSeriesArray().size();
		}

		class MyViewHolder extends RecyclerView.ViewHolder{
			ImageView seriesImage;
			TextView seriesName,itemNum,SeriesPrices,position;
			RecyclerView itemRv;

			LinearLayout peisong,beizhu;

			public MyViewHolder(View itemView){
				super(itemView);
				seriesImage = (ImageView)itemView.findViewById(R.id.syrinxSeries_image);
				seriesName = (TextView)itemView.findViewById(R.id.syrinxSeries_name);
				itemRv = (RecyclerView)itemView.findViewById(R.id.itemRv);
				peisong = (LinearLayout)itemView.findViewById(R.id.peisong);
				beizhu = (LinearLayout)itemView.findViewById(R.id.beizhu);
				itemNum = (TextView)itemView.findViewById(R.id.itemNum);
				SeriesPrices = (TextView)itemView.findViewById(R.id.SeriesPrices);
				position = (TextView)itemView.findViewById(R.id.position);
			}
		}
	}

	/**
	 * cart 中 的syrinxitem的adapter
	 */
	class ItemRecyclerAdapter extends RecyclerView.Adapter{

		Context context;

		ShoppingCart shoppingCart;

		public ItemRecyclerAdapter(ShoppingCart cart)
		{
			shoppingCart = cart;
		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
			context = parent.getContext();
			View view = LayoutInflater.from(context)
					.inflate(R.layout.sure_itemrv_item_layout,parent,false);
			return new MyViewHolder(view);
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){

			SyrinxItem item = shoppingCart.getItem(position);
			MyViewHolder myViewHolder = (MyViewHolder)holder;

			Glide.with(context)
					.load(item.getImageId())
					.into(myViewHolder.itemImage);

			myViewHolder.position.setText((position+1)+".");

			myViewHolder.itemName.setText(item.getName());
			myViewHolder.itemNum.setText("x"+shoppingCart.getItemNum(position));
			myViewHolder.itemPrices.setText("￥ "+shoppingCart.getItemPrice(item));
			myViewHolder.itemClass.setText("");
		}

		@Override
		public int getItemCount(){
			return shoppingCart.getSyrinxItemArrayList().size();
		}

		class MyViewHolder extends RecyclerView.ViewHolder{
			TextView itemPrices,itemNum,itemClass,itemName,position;
			ImageView itemImage;

			public MyViewHolder(View itemView){
				super(itemView);
				itemPrices = (TextView)itemView.findViewById(R.id.itemPrices);
				itemName = (TextView)itemView.findViewById(R.id.itemName);
				itemNum = (TextView)itemView.findViewById(R.id.itemNum);
				itemClass = (TextView)itemView.findViewById(R.id.itemClass);
				itemImage = (ImageView)itemView.findViewById(R.id.itemImage);
				position = (TextView)itemView.findViewById(R.id.position);
			}
		}


	}

}
