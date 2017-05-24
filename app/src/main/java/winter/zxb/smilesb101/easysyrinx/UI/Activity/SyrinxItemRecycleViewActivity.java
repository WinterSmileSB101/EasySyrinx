package winter.zxb.smilesb101.easysyrinx.UI.Activity;

		import android.animation.Animator;
		import android.animation.AnimatorSet;
		import android.animation.ObjectAnimator;
		import android.animation.TypeEvaluator;
		import android.animation.ValueAnimator;
		import android.content.Context;
		import android.content.Intent;
		import android.graphics.Color;
		import android.graphics.Point;
		import android.graphics.PointF;
		import android.os.Build;
		import android.support.design.widget.AppBarLayout;
		import android.support.design.widget.CollapsingToolbarLayout;
		import android.support.design.widget.CoordinatorLayout;
		import android.support.v7.app.ActionBar;
		import android.support.v7.app.AppCompatActivity;
		import android.os.Bundle;
		import android.support.v7.widget.LinearLayoutManager;
		import android.support.v7.widget.RecyclerView;
		import android.support.v7.widget.Toolbar;
		import android.util.Log;
		import android.view.Gravity;
		import android.view.MenuItem;
		import android.view.View;
		import android.view.ViewParent;
		import android.view.Window;
		import android.view.WindowManager;
		import android.view.animation.AccelerateInterpolator;
		import android.view.animation.AnimationSet;
		import android.view.animation.LinearInterpolator;
		import android.widget.ImageView;
		import android.widget.LinearLayout;
		import android.widget.PopupWindow;
		import android.widget.RelativeLayout;
		import android.widget.TextView;
		import android.widget.Toast;

		import com.bumptech.glide.Glide;

		import org.w3c.dom.Text;

		import java.security.PublicKey;
		import java.util.ArrayList;

		import jp.wasabeef.glide.transformations.BlurTransformation;
		import winter.zxb.smilesb101.easysyrinx.CustomAnimation.BezierEvaluator;
		import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.ShoppingCart;
		import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.ShoppingCartList;
		import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItem;
		import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItemRecycleViewAdapter;
		import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxSeries;
		import winter.zxb.smilesb101.easysyrinx.Logic.Help.help;
		import winter.zxb.smilesb101.easysyrinx.R;
		import winter.zxb.smilesb101.easysyrinx.UI.SelfView.CartPopWindow;

/**
 * 希芸产品信息的展示活动
 */
public class SyrinxItemRecycleViewActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener,SyrinxItemRecycleViewAdapter.clickListener,View.OnClickListener{

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

	//下面购物栏
	private ImageView chart;
	private LinearLayout chart_layout;
	private TextView puchaseNum;

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

		chart = (ImageView)findViewById(R.id.chart);
		chart_layout = (LinearLayout)findViewById(R.id.chart_layout);
		puchaseNum = (TextView)findViewById(R.id.puchase_num);

		chart.setOnClickListener(this);
		chart_layout.setOnClickListener(this);

		checkCartLayout();

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
		itemRecycleViewAdapter.setListener(this);//设置监听
		recyclerView.setAdapter(itemRecycleViewAdapter);
		//Log.i(TAG,"onCreate: huodongchuangjian");
	}

	/**
	 * 检查是否有已经存在的购物车，并更新View
	 */
	void checkCartLayout()
	{
		TextView isNull = (TextView)findViewById(R.id.rightBtnText);
		RelativeLayout rightBtn = (RelativeLayout)findViewById(R.id.rightBtn);
		TextView price = (TextView)findViewById(R.id.price);

		ShoppingCartList.SHOPPING_CART_LIST.checkSelfList();

		if(ShoppingCartList.SHOPPING_CART_LIST.getPrices()!=0)
		{
			//说明存在，更新view
			rightBtn.setBackgroundColor(Color.YELLOW);
			chart.setImageResource(R.mipmap.chart_yellow);
			isNull.setTextColor(Color.BLACK);

			isNull.setText("选好了");
			isNull.setOnClickListener(this);//增加按键监听
			puchaseNum.setBackgroundResource(R.drawable.round_background_aqua);

			String num = "";
			if(ShoppingCartList.SHOPPING_CART_LIST.getAllNum() > 99)
			{
				num = "99+";
			}
			else
			{
				num+=ShoppingCartList.SHOPPING_CART_LIST.getAllNum();
			}
			puchaseNum.setText(num);
			price.setText("￥ "+ShoppingCartList.SHOPPING_CART_LIST.getPrices());
		}
		else
		{
			rightBtn.setBackgroundColor(Color.parseColor("#00000000"));
			isNull.setTextColor(Color.WHITE);
			isNull.setText("未选中");
			isNull.setOnClickListener(null);//去掉按键监听
			chart.setImageResource(R.mipmap.chart_gray);
			price.setText("");
			//同步到静态类
			ShoppingCartList.SHOPPING_CART_LIST.setShoppingCarts(new ArrayList<ShoppingCart>());
			//更新ui
			puchaseNum.setBackgroundResource(R.drawable.round_background_gray);
			puchaseNum.setText("0");
		}
		if(itemRecycleViewAdapter!=null) {
			itemRecycleViewAdapter.notifyDataSetChanged();//更新rv数据
		}
	}

	void horzontailAnimation(final View view,final ImageView imageImage,final ImageView reduceBtn,final SyrinxItem syrinxItem)
	{
		final float trX = view.getTranslationX();
		final float rotation = view.getRotation();
		ValueAnimator animator = ValueAnimator.ofFloat(view.getTranslationX(),view.getTranslationX()-150);
		animator.setTarget(view);
		animator.setDuration(50);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
			@Override
			public void onAnimationUpdate(ValueAnimator animation){
				float value = (float)animation.getAnimatedValue();
				view.setTranslationX(value);
				view.setRotation(view.getRotation()-15);
			}
		});
		animator.addListener(new Animator.AnimatorListener(){
			@Override
			public void onAnimationStart(Animator animation){

			}

			@Override
			public void onAnimationEnd(Animator animation){
				//执行下一个动画
				view.setTranslationX(trX);
				view.setRotation(rotation);
				//reduceBtn.getRootView().findViewById(R.id.reduceView).setVisibility(View.VISIBLE);//提供视觉效果的显示
				reduceBtn.setVisibility(View.VISIBLE);//显示减少按钮
				paowuxian(imageImage,syrinxItem);
			}

			@Override
			public void onAnimationCancel(Animator animation){

			}

			@Override
			public void onAnimationRepeat(Animator animation){

			}
		});
		animator.start();
	}

	void paowuxian(final ImageView view,final SyrinxItem syrinxItem)
	{
		final ImageView imageView = new ImageView(this);
		imageView.setImageDrawable(view.getDrawable());
		imageView.setTranslationX(view.getTranslationX());
		imageView.setTranslationY(view.getTranslationY());
		final RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
		layout.addView(imageView);
		int[] pos = new int[2];
		int[] toPos = new int[2];
		view.getLocationInWindow(pos);//获取位置
		chart.getLocationInWindow(toPos);//获取位置
		Log.i(TAG,"paowuxian: "+ toPos[0]);

		PointF startPoint = new PointF(pos[0],pos[1]-view.getHeight()*2);
		PointF endPoint = new PointF(toPos[0]+chart.getWidth()/3,toPos[1]);

		PointF collerPoint = new PointF(((startPoint.x + endPoint.x) / 2 - 100),startPoint.y - 200);

		ValueAnimator animator = new ValueAnimator();
		animator.setDuration(100);
		animator.setObjectValues(startPoint,endPoint);
		animator.setEvaluator(new BezierEvaluator(collerPoint));
		animator.setInterpolator(new AccelerateInterpolator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
			@Override
			public void onAnimationUpdate(ValueAnimator animation){
				PointF pointF = (PointF)animation.getAnimatedValue();
				imageView.setTranslationX(pointF.x);
				imageView.setTranslationY(pointF.y);
			}
		});
		animator.addListener(new Animator.AnimatorListener(){
			@Override
			public void onAnimationStart(Animator animation){

			}

			@Override
			public void onAnimationEnd(Animator animation){
				//执行结束，状态初始化
				layout.removeView(imageView);//去掉这个view，

				//购物车图标变大
				ObjectAnimator objectAnimatorX = ObjectAnimator
				 .ofFloat(chart,"scaleX",1f,1.2f,1f);

				ObjectAnimator objectAnimatorY = ObjectAnimator
				.ofFloat(chart,"scaleY",1f,1.2f,1f);

				ObjectAnimator objectAnimator1X = ObjectAnimator
				.ofFloat(puchaseNum,"scaleX",1f,1.2f,1f);

				ObjectAnimator objectAnimator1Y = ObjectAnimator
				.ofFloat(puchaseNum,"scaleY",1f,1.2f,1f);

				AnimatorSet animatorSet =  new AnimatorSet();
				animatorSet.setDuration(50);
				animatorSet.playTogether(objectAnimatorX,objectAnimatorY,objectAnimator1X,objectAnimator1Y);
				animatorSet.start();

				addGoodsToChart(syrinxItem);//添加信息
			}

			@Override
			public void onAnimationCancel(Animator animation){

			}

			@Override
			public void onAnimationRepeat(Animator animation){

			}
		});
		animator.start();
	}

	/**
	 * 减少商品的动画
	 * @param view
	 * @param reduceView
	 * @param reduceBtn
	 * @param syrinxItem
	 */
	void reduceAnimation(final View view,final ImageView reduceView,final ImageView reduceBtn,final SyrinxItem syrinxItem)
	{
		final float trX = view.getTranslationX();
		final float rotation = view.getRotation();
		ValueAnimator animator = ValueAnimator.ofFloat(view.getTranslationX(),view.getTranslationX()+150);
		animator.setTarget(view);
//		animator.setDuration(100);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
			@Override
			public void onAnimationUpdate(ValueAnimator animation){
				float value = (float)animation.getAnimatedValue();
				view.setTranslationX(value);
				view.setRotation(view.getRotation()+15);
			}
		});
		animator.addListener(new Animator.AnimatorListener(){
			@Override
			public void onAnimationStart(Animator animation){

			}

			@Override
			public void onAnimationEnd(Animator animation){
				//执行下一个动画
				//重置位置,以便以后再次增加
				view.setTranslationX(trX);
				view.setRotation(rotation);

				if(reduceView.getVisibility()==View.INVISIBLE)//说明这是最后一个
				{
					reduceBtn.setVisibility(View.INVISIBLE);//不显示减少按钮,必须用gone，否则再次点击增加会出现错误
				}
				reduceGoods(syrinxItem);
			}

			@Override
			public void onAnimationCancel(Animator animation){

			}

			@Override
			public void onAnimationRepeat(Animator animation){

			}
		});

		ObjectAnimator objectAnimatorX = ObjectAnimator
				.ofFloat(chart,"scaleX",1f,0.8f,1f);

		ObjectAnimator objectAnimatorY = ObjectAnimator
				.ofFloat(chart,"scaleY",1f,0.8f,1f);

		ObjectAnimator objectAnimator1X = ObjectAnimator
				.ofFloat(puchaseNum,"scaleX",1f,0.8f,1f);

		ObjectAnimator objectAnimator1Y = ObjectAnimator
				.ofFloat(puchaseNum,"scaleY",1f,0.8f,1f);

		AnimatorSet animatorSet =  new AnimatorSet();
		animatorSet.setDuration(50);
		animatorSet.playTogether(animator,objectAnimatorX,objectAnimatorY,objectAnimator1X,objectAnimator1Y);
		animatorSet.start();
	}

	/**
	 * 减少商品
	 * @param syrinx
	 */
	void reduceGoods(SyrinxItem syrinx)
	{
		if(ShoppingCartList.SHOPPING_CART_LIST.getListNum() != 0) {
			int index = ShoppingCartList.SHOPPING_CART_LIST.getIndexByCartName(syrinx.getSeries());

			if(index!=-1) {//不为-1说明存在这个cart的记录
				//同步到静态类
				ShoppingCart cart = ShoppingCartList.SHOPPING_CART_LIST.getShoppingCart(index);
				cart.removeSyrinxItem(syrinx);
				cart.reducePrices(syrinx.getOut_price());
				//更新ui
				checkCartLayout();
			}
		}
		//这个只是以防外部违规调用
		else{
			checkCartLayout();
		}
	}

	/**
	 * 增加物品到购物车
	 * @param syrinx
	 */
	void addGoodsToChart(SyrinxItem syrinx)
	{
		//同步到静态类
		int index = ShoppingCartList.SHOPPING_CART_LIST.getIndexByCartName(syrinx.getSeries());
		if(index!=-1) {
			//存在这个记录
			ShoppingCart cart = ShoppingCartList.SHOPPING_CART_LIST.getShoppingCart(index);
			cart.addSyrinxItem(syrinx);
			cart.addPrices(syrinx.getOut_price());
		}
		else
		{
			//不存在这个记录
			ShoppingCart cart = new ShoppingCart();
			cart.setCartName(syrinx.getSeries());
			cart.addSyrinxItem(syrinx);
			cart.addPrices(syrinx.getOut_price());
			ShoppingCartList.SHOPPING_CART_LIST.addShoppingCart(cart);
		}

		//更新ui
		checkCartLayout();
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
		//Log.i(TAG,"onOffsetChanged: "+verticalOffset);
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

	@Override
	public void clickListener(View view,ImageView imageView,ImageView reduceBtn,SyrinxItem syrinxItem){
		switch(view.getId())
		{
			case R.id.reduceBtn:
				//减少物品
				reduceAnimation(view,imageView,reduceBtn,syrinxItem);
				break;
			case R.id.addBtn:
				//增加物品
				//执行动画
				horzontailAnimation(view,imageView,reduceBtn,syrinxItem);
				break;
		}
	}



	void showCartPopWindow()
	{
		CartPopWindow popWindow = new CartPopWindow(this,R.layout.cart_pop_layout);
		popWindow.showAtLocation(findViewById(R.id.layout),Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		//当弹出Popupwindow时，背景变半透明
		params.alpha=0.7f;
		getWindow().setAttributes(params);
		//设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
		popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				WindowManager.LayoutParams params = getWindow().getAttributes();
				params.alpha=1f;
				getWindow().setAttributes(params);
				//重新布局
				checkCartLayout();
			}
		});
	}



	@Override
	public void onClick(View v){
		switch(v.getId())
		{
			case R.id.chart_layout://购物列表按钮
			case R.id.cart://购物列表按钮
				showCartPopWindow();
				break;
			case R.id.rightBtnText://选好了按钮
				//显示结算界面
				Intent intent = new Intent(this,SureCartActivity.class);
				startActivity(intent);



				break;
		}
	}
}
