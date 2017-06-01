package winter.zxb.smilesb101.easysyrinx.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import winter.zxb.smilesb101.easysyrinx.Data.UserAddressInfo;
import winter.zxb.smilesb101.easysyrinx.R;
import winter.zxb.smilesb101.easysyrinx.UI.Fragments.CityPicker;

/**
 * 项目名称：EasySyrinx
 * 类描述：添加和修改地址的活动
 * 创建人：SmileSB101
 * 创建时间：2017/5/25 0025 19:40
 * 修改人：Administrator
 * 修改时间：2017/5/25 0025 19:40
 * 修改备注：
 */

public class AddEditorAddressActivity extends AppCompatActivity implements View.OnClickListener{
	ActionBar actionBar;

	TextView manageBtn,delete,quyu,jiedao;
	EditText Name,Phone;
	EditText xiangxi;
	LinearLayout setDefault;
	CheckBox defaultBtn;

	public static final String USERINFO_KEY = "userInfo";

	UserAddressInfo EditorInfo;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_editor_address_layout);

		Intent intent = getIntent();
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		manageBtn = (TextView)findViewById(R.id.manageBtn);
		Name = (EditText)findViewById(R.id.Name);
		Phone = (EditText)findViewById(R.id.Phone);
		quyu = (TextView)findViewById(R.id.quyu);
		jiedao = (TextView)findViewById(R.id.jiedao);
		delete = (TextView)findViewById(R.id.delete);

		quyu.setOnClickListener(this);
		jiedao.setOnClickListener(this);

		manageBtn.setOnClickListener(this);
		delete.setOnClickListener(this);

		xiangxi = (EditText)findViewById(R.id.xiangxi);
		setDefault = (LinearLayout)findViewById(R.id.setDefault);
		defaultBtn = (CheckBox)findViewById(R.id.defaultBtn);

		if(intent.getSerializableExtra(USERINFO_KEY)!=null)
		{
			//修改地址活动
			EditorInfo = (UserAddressInfo)intent.getSerializableExtra(USERINFO_KEY);
			actionBar.setTitle("修改地址");
			delete.setVisibility(View.VISIBLE);

			Name.setText(EditorInfo.getUserName());
			Name.setSelection(EditorInfo.getUserName().length());//设置光标位置到最后
			Phone.setText(EditorInfo.getUserPhone());
			Phone.setSelection(EditorInfo.getUserPhone().length());//设置光标位置到最后

		}
		else {
			//新增地址活动
			actionBar.setTitle("添加地址");
			setDefault.setVisibility(View.VISIBLE);
		}


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
			case R.id.manageBtn:
				break;
			case R.id.delete:
				break;
			case R.id.quyu:
				//进入城市选择器
				FragmentManager fragmentManager = getSupportFragmentManager();
				fragmentManager.beginTransaction()
						.add(R.id.container,new CityPicker())
						.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
						.addToBackStack("city")
						.commit();
				break;
			case R.id.jiedao:
				break;
		}
	}
}
