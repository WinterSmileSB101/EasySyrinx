package winter.zxb.smilesb101.easysyrinx.UI.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;
import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItem;
import winter.zxb.smilesb101.easysyrinx.R;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;

public class SyrinxItemDetailsActivity extends AppCompatActivity{

	public static final String SYRINXITEM_KEY = "SYRINXITEM";
	private static final String TAG = "SyrinxItemDetailsActivity";

	private ImageView item_Image_bg;
	private ImageView item_Image;
	private TextView syrinx_itemdetails_itemEffects;
	private TextView syrinx_itemdetails_tips;
	private CollapsingToolbarLayout collapsingToolbarLayout;
	private TextView item_format;
	private TextView item_name;
	private TextView item_wordPrice;
	private ImageView item_useImage;
	private TextView item_code;
	private TextView item_outPrice;
	private TextView item_inPrice;
	private TextView item_freePost;
	private Toolbar toolbar;

	private LinearLayout dynamicLayout;

	private SyrinxItem syrinxItem;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		//设置状态栏透明
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(Color.TRANSPARENT);
			window.setNavigationBarColor(Color.TRANSPARENT);
		}

		setContentView(R.layout.syrinx_itemdetails_layout);
		getWidget();
		initValue();
	}

	void getWidget()
	{
		item_Image = (ImageView)findViewById(R.id.syrinx_itemdetails_Image);
		item_Image_bg = (ImageView)findViewById(R.id.syrinx_itemdetails_Image_bg);
		syrinx_itemdetails_itemEffects = (TextView)findViewById(R.id.syrinx_itemdetails_itemEffects);
		syrinx_itemdetails_tips = (TextView)findViewById(R.id.syrinx_itemdetails_tips);
		collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.syrinx_itemdetails_collapsingToolBar);
		item_name = (TextView)findViewById(R.id.syrinx_itemdetails_Name);
		item_format = (TextView)findViewById(R.id.syrinx_itemdetails_format);
		item_wordPrice = (TextView)findViewById(R.id.syrinx_itemdetails_wordPrice);
		item_useImage = (ImageView)findViewById(R.id.syrinx_itemdetails_useImage);
		item_outPrice = (TextView)findViewById(R.id.syrinx_itemdetails_outprice);
		item_inPrice = (TextView)findViewById(R.id.syrinx_itemdetails_inprice);
		item_freePost = (TextView)findViewById(R.id.syrinx_itemdetails_freePost);
		item_code = (TextView)findViewById(R.id.syrinx_itemdetails_code);
		toolbar = (Toolbar)findViewById(R.id.syrinxitem_layout_toolbar);
	}

	void initValue()
	{
		Intent intent = getIntent();
		syrinxItem = (SyrinxItem)intent.getSerializableExtra(SYRINXITEM_KEY);

		Glide.with(this)
				.load(syrinxItem.getImageId())
				.bitmapTransform(new BlurTransformation(this,100,1))
				.into(item_Image_bg);

		Glide.with(this)
				.load(syrinxItem.getImageId())
				.into(item_Image);

		if(getSupportActionBar()==null)
		{
			setSupportActionBar(toolbar);
		}
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		collapsingToolbarLayout.setTitle(syrinxItem.getName()+"详情");
		collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
		collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

		item_outPrice.setText(syrinxItem.getOut_price()+"");
		item_inPrice.setText(syrinxItem.getIn_price()+"");
		item_freePost.setText(syrinxItem.isFreePost());
		item_code.setText(syrinxItem.getCode());

		syrinx_itemdetails_itemEffects.setText(syrinxItem.getItemEffects());
		syrinx_itemdetails_tips.setText(syrinxItem.getTips());
		item_name.setText(syrinxItem.getName());
		item_format.setText(syrinxItem.getFormat());
		item_wordPrice.setText(syrinxItem.getWordPrice()+"");
		if(syrinxItem.getUseImageID()!=0) {
			item_useImage.setImageResource(syrinxItem.getUseImageID());
		}
		//设置多项的内容
		setMulitContent(0);
		setMulitContent(1);
	}
private void setMulitContent(int i)
{
	ArrayList<String> titles = new ArrayList<>();
	ArrayList<String> contents = new ArrayList<>();
	int id = 0;
	switch(i) {
		case 0://第一种
			titles = syrinxItem.getHowToUse_title();
			contents = syrinxItem.getHowToUse();
			id = R.id.syrinx_itemdetails_dynamicLayout;
			break;
		case 1://第二种
			titles = syrinxItem.getMajorContent_title();
			contents = syrinxItem.getMajorContent();
			id = R.id.syrinx_itemdetails_dynamicLayoutMajor;
			break;
	}
	for(int s=0;s<titles.size();s++)
	{
		dynamicLayoutAdd(titles.get(s),contents.get(s),id);
	}
}
	/**
	 * 动态添加布局
	 * @param title 标题
	 * @param content 内容
	 */
	private void dynamicLayoutAdd(String title,String content,int id)
	{
		dynamicLayout = (LinearLayout)findViewById(id);
		View view = LayoutInflater.from(this)
				.inflate(R.layout.itemdetails_recycleview_item,dynamicLayout,false);
		TextView Title,Content;
		Title = (TextView)view.findViewById(R.id.itemdetails_item_title);
		Content = (TextView)view.findViewById(R.id.itemdetails_item_content);
		Title.setText(title);
		Content.setText(content);
		dynamicLayout.addView(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
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
}
