package winter.zxb.smilesb101.easysyrinx.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

import winter.zxb.smilesb101.easysyrinx.Data.UserAddressInfo;
import winter.zxb.smilesb101.easysyrinx.R;
import winter.zxb.smilesb101.easysyrinx.UI.NotScrollLinearLayoutManager;

/**
 * 项目名称：EasySyrinx
 * 类描述：地址管理活动
 * 创建人：SmileSB101
 * 创建时间：2017/5/24 0024 14:33
 * 修改人：Administrator
 * 修改时间：2017/5/24 0024 14:33
 * 修改备注：
 */

public class AddressActivity extends AppCompatActivity implements View.OnClickListener{

	String TAG = "AddressActivity";

	RecyclerView recyclerView;
	TextView userName,userPhone,userAddress,manageBtn;
	RVAdapter adapter;

	ActionBar actionBar;
	Button addAddress;
	boolean manage_state;

	ArrayList<UserAddressInfo> infoArray;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_activity_layout);

		addAddress = (Button)findViewById(R.id.addAddress);
		addAddress.setOnClickListener(this);

		manageBtn = (TextView)findViewById(R.id.manageBtn);
		manageBtn.setOnClickListener(this);

		manage_state = false;

		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		actionBar = getSupportActionBar();
		actionBar.setTitle("地址管理");
		actionBar.setDisplayHomeAsUpEnabled(true);

		userName = (TextView)findViewById(R.id.userName);
		userPhone = (TextView)findViewById(R.id.userPhone);
		userAddress = (TextView)findViewById(R.id.userAddress);

		recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
		/**
		 * 从数据库获取地址列表
		 */
		infoArray = new ArrayList<>();
		addToDB(new UserAddressInfo("张孝波","13194889618",true,"成都"));

		/**
		 * 设置当前的地址，从数据库获取
		 */
		getAddressFromDB();
		userName.setText(infoArray.get(0).getUserName());
		userPhone.setText(infoArray.get(0).getUserPhone());
		userAddress.setText(infoArray.get(0).getUserAddress());

		adapter = new RVAdapter(infoArray);

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
			case R.id.addAddress:
//				UserAddressInfo userAddressInfo = new UserAddressInfo("张孝波","15680487872",true,"成都");
//				addToDB(userAddressInfo);
				//打开添加活动
				Intent intent = new Intent(this,AddEditorAddressActivity.class);
				startActivityForResult(intent,0);
				break;
			case R.id.manageBtn:
				manage_state = !manage_state;
				adapter.setState_Manage(manage_state);
				adapter.notifyDataSetChanged();
				break;
		}
	}

	void addToDB(UserAddressInfo obj)
	{
		//添加地址，到数据库
		SQLiteDatabase db = Connector.getDatabase();
		if(db!=null&&obj!=null) {
//			DataSupport.deleteAll(UserAddressInfo.class);
			List<UserAddressInfo> info = DataSupport.where("userAddress = ? ",obj.getUserAddress()).find(UserAddressInfo.class);
			for(UserAddressInfo t : info) {
				if(obj.equals(t)) {
					//存在记录
					Toast.makeText(this,"已经存在相同地址噢",Toast.LENGTH_SHORT).show();
					return;
				}
			}
			//保存数据
			obj.save();
			//向表中也添加此数据，减少重复访问数据库开支以及更新界面
			if(infoArray == null) {
				infoArray = new ArrayList<>();
			}
			infoArray.add(obj);
			if(adapter != null) {
				adapter.notifyDataSetChanged();
			}

		}
	}

	void getAddressFromDB()
	{
		SQLiteDatabase db = Connector.getDatabase();
		if(db!=null)
		{
			infoArray = (ArrayList<UserAddressInfo>)DataSupport.findAll(UserAddressInfo.class);
			if(adapter!=null) {
				adapter.notifyDataSetChanged();
			}
		}
	}


	class RVAdapter extends RecyclerView.Adapter{

		ArrayList<UserAddressInfo> addressInfoArrayList;
		Context context;

		boolean state_Manage;//是否是管理

		public RVAdapter(ArrayList<UserAddressInfo> addressInfoArrayList){
			if(addressInfoArrayList!=null) {
				this.addressInfoArrayList = addressInfoArrayList;
			}
			else
			{
				addressInfoArrayList = new ArrayList<>();
			}
			state_Manage = false;
		}

		public RVAdapter(){
			addressInfoArrayList = new ArrayList<>();
			state_Manage = false;
		}

		public void setState_Manage(boolean state_Manage){
			this.state_Manage = state_Manage;
		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
			context = parent.getContext();
			View view = LayoutInflater.from(context)
					.inflate(R.layout.adderres_item_layout,parent,false);
			return new MyViewHolder(view);
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
			MyViewHolder myViewHolder = (MyViewHolder)holder;
			final UserAddressInfo info = addressInfoArrayList.get(position);
			myViewHolder.userName.setText(info.getUserName());
			myViewHolder.userPhone.setText(info.getUserPhone());
			myViewHolder.userAddress.setText(info.getUserAddress());
			if(state_Manage)
			{
				myViewHolder.default_Add.setVisibility(View.GONE);
				myViewHolder.manageLayout.setVisibility(View.VISIBLE);

				//编辑按钮
				myViewHolder.editor.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v){
						//打开活动
						Intent intent = new Intent(context,AddEditorAddressActivity.class);
						intent.putExtra(AddEditorAddressActivity.USERINFO_KEY,info);
						startActivity(intent);
					}
				});

				myViewHolder.default_Add.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v){
						//删除数据库中这条记录
					}
				});

				if(info.isDefault())
				{
					myViewHolder.isDefault.setChecked(true);
					myViewHolder.isDefaultText.setTextColor(Color.RED);
					myViewHolder.isDefaultText.setText("默认地址");
				}
				else
				{
					myViewHolder.isDefault.setChecked(false);
					myViewHolder.isDefaultText.setTextColor(Color.BLACK);
					myViewHolder.isDefaultText.setText("设为默认");
				}
			}
			else {

				if(info.isDefault()) {
					myViewHolder.manageLayout.setVisibility(View.GONE);
					myViewHolder.default_Add.setVisibility(View.VISIBLE);
				}
			}
		}

		@Override
		public int getItemCount(){
			return addressInfoArrayList.size();
		}

		class MyViewHolder extends RecyclerView.ViewHolder{

			TextView userName,userPhone,userAddress,default_Add;
			LinearLayout manageLayout,editor,delete;
			CheckBox isDefault;
			TextView isDefaultText;

			public MyViewHolder(View itemView){
				super(itemView);
				userName = (TextView)itemView.findViewById(R.id.userName);
				userPhone = (TextView)itemView.findViewById(R.id.userPhone);
				userAddress = (TextView)itemView.findViewById(R.id.userAddress);
				default_Add = (TextView)itemView.findViewById(R.id.default_Add);
				manageLayout = (LinearLayout)itemView.findViewById(R.id.manageLayout);
				editor = (LinearLayout)itemView.findViewById(R.id.editor);
				delete = (LinearLayout)itemView.findViewById(R.id.delete);
				isDefault = (CheckBox)itemView.findViewById(R.id.isDefault);
				isDefaultText = (TextView)itemView.findViewById(R.id.isDefaultText);
			}
		}
	}
}
