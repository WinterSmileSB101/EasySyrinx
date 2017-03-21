package winter.zxb.smilesb101.easysyrinx.UI.Activity;

		import android.content.Context;
		import android.content.Intent;
		import android.graphics.Color;
		import android.os.Build;
		import android.support.design.widget.AppBarLayout;
		import android.support.design.widget.CollapsingToolbarLayout;
		import android.support.v7.app.ActionBar;
		import android.support.v7.app.AppCompatActivity;
		import android.os.Bundle;
		import android.support.v7.widget.LinearLayoutManager;
		import android.support.v7.widget.RecyclerView;
		import android.support.v7.widget.Toolbar;
		import android.util.Log;
		import android.view.MenuItem;
		import android.view.View;
		import android.view.Window;
		import android.view.WindowManager;
		import android.widget.ImageView;
		import android.widget.TextView;

		import com.bumptech.glide.Glide;

		import java.util.ArrayList;

		import jp.wasabeef.glide.transformations.BlurTransformation;
		import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItem;
		import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItemRecycleViewAdapter;
		import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxSeries;
		import winter.zxb.smilesb101.easysyrinx.Logic.Help.help;
		import winter.zxb.smilesb101.easysyrinx.R;

/**
 * 希芸产品信息的展示活动
 */
public class SyrinxItemRecycleViewActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

	public final static String SYRINXLIST_VALUE = "SYRINXLIST";
	public final static String SYRINXSERIES_VALUE = "SYRINXSERIES";
	private final static String TAG = "SyrinxItemActivity";

	private View rootView;
	private AppBarLayout appbarLayout;
	private RecyclerView recyclerView;
	private Context context;
	private SyrinxItemRecycleViewAdapter itemRecycleViewAdapter;
	private ImageView top_bg;
	private SyrinxSeries syrinxSeries;
	private ArrayList<SyrinxItem> syrinxItems;
	private ImageView seriesImage;
	private Toolbar toolbar;
	private CollapsingToolbarLayout collapsingToolbar;
	private ActionBar actionBar;
	private TextView seriesName;
	private TextView seriesMore;
	private TextView seriesPrice;
	private TextView seriesNum;
	private TextView freePost;

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

		setContentView(R.layout.syrinxitem_recycleview_layout);

		//获取上个活动传递过来的List与对象实例
		Intent intent = getIntent();
		syrinxItems = (ArrayList<SyrinxItem>)intent.getSerializableExtra(SYRINXLIST_VALUE);
		syrinxSeries = (SyrinxSeries)intent.getSerializableExtra(SYRINXSERIES_VALUE);

		seriesImage = (ImageView)findViewById(R.id.syrinxitem_layout_top_seriesImage);
		appbarLayout = (AppBarLayout)findViewById(R.id.syrinxitem_layout_appbarLayout);
		appbarLayout.addOnOffsetChangedListener(this);

		toolbar = (Toolbar)findViewById(R.id.syrinxitem_layout_toolbar);
		if(getSupportActionBar()==null)
		{
			setSupportActionBar(toolbar);
		}
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("系列");
		collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.syrinxitem_layout_collapsingToolbar);
		collapsingToolbar.setTitleEnabled(false);

		Glide.with(this)
				.load(syrinxSeries.getImageUrl())
				.into(seriesImage);
		top_bg = (ImageView)findViewById(R.id.syrinxitem_layout_top_bg);
		Glide.with(this)
				.load(syrinxSeries.getImageUrl())
				.bitmapTransform(new BlurTransformation(this,100,1))  // “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。
				.into(top_bg);

		//设置系列名称等：
		seriesName = (TextView)findViewById(R.id.syrinxitem_layout_seriesName);
		seriesMore = (TextView)findViewById(R.id.syrinxitem_layout_seriesMore);
		seriesMore.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				//取到系列详情界面
				Log.i(TAG,"onClick: 显示详情界面");
			}
		});

		seriesNum = (TextView)findViewById(R.id.syrinxitem_layout_itemsNum);
		seriesPrice = (TextView)findViewById(R.id.syrinxitem_layout_allitem_price);
		freePost = (TextView)findViewById(R.id.syrinxitem_layout_freePost);
		recyclerView = (RecyclerView)findViewById(R.id.syrinxitem_layout_recycleView);

		seriesName.setText(syrinxSeries.getName()+"系列");
		seriesNum.setText(syrinxItems.size()+"");
		seriesPrice.setText(help.addAllSyrinxItem(syrinxItems)+"");
		freePost.setText(syrinxSeries.isFreePost());

		LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutmanager);
		itemRecycleViewAdapter = new SyrinxItemRecycleViewAdapter(syrinxItems,this);
		recyclerView.setAdapter(itemRecycleViewAdapter);
		Log.i(TAG,"onCreate: huodongchuangjian");
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
	public void onBackPressed(){
		super.onBackPressed();
	}

	@Override
	protected void onPause(){
		super.onPause();
		appbarLayout.removeOnOffsetChangedListener(this);
	}

	@Override
	protected void onResume(){
		super.onResume();
		appbarLayout.addOnOffsetChangedListener(this);
	}

	/**
	 * 监听appbar的滑动状态
	 * @param appBarLayout
	 * @param verticalOffset
	 */
	@Override
	public void onOffsetChanged(AppBarLayout appBarLayout,int verticalOffset){
		Log.i(TAG,"onOffsetChanged: "+verticalOffset);
		if(verticalOffset<=-75)
		{
			actionBar.setTitle(syrinxSeries.getName()+"系列");
			seriesName.setVisibility(View.INVISIBLE);
		}
		else
		{
			actionBar.setTitle("系列");
			seriesName.setVisibility(View.VISIBLE);
		}
	}
}
