package winter.zxb.smilesb101.easysyrinx;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.ArrayList;

import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxItem;
import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxSeries;
import winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo.SyrinxSeriesRecycleViewAdapter;
import winter.zxb.smilesb101.easysyrinx.UI.calculationUI.calculationMain;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener{

	public static ArrayList<SyrinxSeries> seriesArrayList = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList001 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList002 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList003 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList004 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList005 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList006 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList007 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList008 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList009 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList010 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList011 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList012 = new ArrayList<>();
	public static ArrayList<SyrinxItem> syrinxItemArrayList013 = new ArrayList<>();
	private RecyclerView recyclerView;
	private RollPagerView rollPagerView;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				Snackbar.make(view,"回到顶部（功能待完成）",Snackbar.LENGTH_LONG)
						.setAction("Action",null).show();
			}
		});

		DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		getWidget();
		initValue();

		StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(new SyrinxSeriesRecycleViewAdapter(seriesArrayList,this));
	}

	@Override
	public void onBackPressed(){
		DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		if(drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if(id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item){
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if(id == R.id.calculation) {
			//打开记账活动
			startActivity(new Intent(this,calculationMain.class));
		} else if(id == R.id.notepad) {

		} else if(id == R.id.transport) {

		} else if(id == R.id.cart) {

		} else if(id == R.id.AR) {

		} else if(id == R.id.nav_send) {

		}

		DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	/**
	 * 获取控件的引用
	 */
	void getWidget()
	{
		recyclerView = (RecyclerView)findViewById(R.id.content_main_recycleView);
		rollPagerView = (RollPagerView)findViewById(R.id.content_main_banner);
		rollPagerView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(int position){
				Toast.makeText(MainActivity.this,"Item "+position+" clicked",Toast.LENGTH_SHORT).show();
			}
		});
		rollPagerView.setAdapter(new MyLoopAdapter(rollPagerView));
	}
	private class MyLoopAdapter extends LoopPagerAdapter{
		private int[] imgs = {
				R.mipmap.apphuodong001,
				R.mipmap.apphuodong002,
				R.mipmap.apphuodong003,
				R.mipmap.apphuodong004,
				R.mipmap.apphuodong005
		};

		public MyLoopAdapter(RollPagerView viewPager) {
			super(viewPager);
		}

		@Override
		public View getView(ViewGroup container, int position) {
			ImageView view = new ImageView(container.getContext());
			view.setImageResource(imgs[position]);
			view.setScaleType(ImageView.ScaleType.FIT_XY);
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return view;
		}

		@Override
		public int getRealCount() {
			return imgs.length;
		}
	}
	/**
	 * 值的初始化
	 */
	void initValue()
	{
		//鲨烷系列
		if(seriesArrayList.equals(new ArrayList<>()))
		{
			seriesArrayList.add(new SyrinxSeries("鲨烷","001",R.mipmap.syrinx_series_sahwan,13,true));
			seriesArrayList.add(new SyrinxSeries("修护","002",R.mipmap.syrinx_suiningxiuhuye,3,true));
			seriesArrayList.add(new SyrinxSeries("幻时凝润","003",R.mipmap.syrinx_huanshiningrun,6,true));
			seriesArrayList.add(new SyrinxSeries("净颜修容","004",R.mipmap.syrinx_ganshiliangyongfenbin,6,true));
			seriesArrayList.add(new SyrinxSeries("臻白焕彩","005",R.mipmap.syrinx_series_zhengbaihuancai,5,true));
			seriesArrayList.add(new SyrinxSeries("纯净清颜","006",R.mipmap.syrinx_cunjingqingyan,6,true));
			seriesArrayList.add(new SyrinxSeries("水漾沁透","007",R.mipmap.syrinx_shuiyangqingtouyanzheli,4,true));
			seriesArrayList.add(new SyrinxSeries("新水漾","008",R.mipmap.syrinx_series_xinshuiyang,5,true));
			seriesArrayList.add(new SyrinxSeries("清透肌","009",R.mipmap.syrinx_qingtouji,4,true));
			seriesArrayList.add(new SyrinxSeries("口红","010",R.mipmap.syrinx_kouhong,7,false));
			seriesArrayList.add(new SyrinxSeries("礼盒","011",R.mipmap.syrinx_ganshiliangyongfenbin,2,true));
			seriesArrayList.add(new SyrinxSeries("固体饮料","012",R.mipmap.syrinx_guoshufen,2,true));
			seriesArrayList.add(new SyrinxSeries("中小样","013",R.mipmap.syrinx_roufushui,14,false));
		}

		syrinxItemArrayList001 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList001,"鲨烷系列");
		syrinxItemArrayList002 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList002,"修护系列");
		syrinxItemArrayList003 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList003,"幻时凝润系列");
		syrinxItemArrayList004 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList004,"净颜修容系列");
		syrinxItemArrayList005 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList005,"臻白焕彩系列");
		syrinxItemArrayList006 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList006,"纯净清颜系列");
		syrinxItemArrayList007 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList007,"水漾沁透系列");
		syrinxItemArrayList008 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList008,"新水漾系列");
		syrinxItemArrayList009 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList009,"清透肌系列");
		syrinxItemArrayList010 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList010,"口红系列");
		syrinxItemArrayList011 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList011,"礼盒系列");
		syrinxItemArrayList012 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList012,"固体饮料系列");
		syrinxItemArrayList013 = checkSyrinxItemIsEqualsNew(syrinxItemArrayList013,"中小样系列");

	}


	ArrayList<String> initItemdetails(int j,int i)
	{
		ArrayList<String> arrayList = new ArrayList<>();
		switch(j) {
			case 0:
			switch(i) {
				case 2://第一个标题
					arrayList.add("角鲨烷");
					arrayList.add("雪莲花提取物");
					arrayList.add("透明质酸钠");
					arrayList.add("生育酚（维生素E）");
					break;
				case 3://第一个标题的内容
					arrayList.add("是皮肤一种天然成分，具有较好抗氧化力和刺激免疫力，能快速地与肌肤内的水分和油脂相溶，形成天然的皮肤保护屏障。能有效保护皮肤不受外界物质的侵害，极佳的渗透力，高效的携氧性，能调整肌肤的水油平衡，避免肌肤粗糙、暗沉，恢复肌肤原本的柔嫩触感。尤其适合在寒冷季节中使用，是肌肤的润滑油，能补充皮肤天然皮脂。");
					arrayList.add("能够清除体内自由基，有效延缓肌肤衰老，同时可以帮助肌肤吸收和锁住水分，长效保湿，避免肌肤干燥粗糙。");
					arrayList.add("属于肌肤调理剂，是一种酸性粘多糖，天然存在于角膜皮中，可吸收其本身重量1000倍水分，以达到通过保留皮肤水分、阻止水分经表皮流失。\n" +
							"当皮肤受到损伤时的屏障修复，令皮肤使用后不会感到干燥，增加光泽。\n" +
							"可以改善皮肤营养代谢，使皮肤柔、光滑、去皱、增加弹性、防止衰老，在保湿的同时又是良好的透皮吸收促进剂。\n" +
							"与其他营养成分配合使用，可以起到促进营养吸收的更理想效果。");
					arrayList.add("有很好的抗氧化效果，它是一种油溶性的天然物质，对肌肤是很好的营养滋润剂。在保湿和维持结缔组织，以及保护皮肤不受紫外线伤害都有很好表现，该成分能使肌肤触感柔软并保持肌肤水分，促进伤口愈合、预防发炎防止肌肤粗糙皲裂、改善黑斑。");
					break;
				case 0://第二个标题
					arrayList.add("Step 1:");
					arrayList.add("Step 2:");
					arrayList.add("Step 3:");
					arrayList.add("Step 4:");
					arrayList.add("Step 5:");
					arrayList.add("Step 6:");
					arrayList.add("PS：");
					break;
				case 1://第二个标题的内容
					arrayList.add("取出面膜展开，先将额头、鼻梁、下巴，这三处贴好固定；");
					arrayList.add("然后撕下外层膜纸，双手从嘴角处向耳际提拉膜纸；");
					arrayList.add("仔细将面膜里的空气压出，使膜纸完全贴合肌肤；");
					arrayList.add("静享10-15分钟的美好时光；");
					arrayList.add("从下巴处向上慢慢揭开膜纸。");
					arrayList.add("在面部轻轻打圈按摩，帮助肌肤吸收剩余在脸上的精华液。");
					arrayList.add("精华吸收后仍有黏腻感，用清水洗净即可。");
					break;
			}
				break;
			case 1:
			switch(i) {
				case 2://第一个标题
					arrayList.add("角鲨烷");
					arrayList.add("库拉索芦荟叶提取物");
					arrayList.add("墨角藻提取物");
					arrayList.add("蜀葵提取物");
					arrayList.add("小球藻萃取物");
					arrayList.add("巨藻");
					arrayList.add("海藻糖");
					break;
				case 3://第一个标题的内容
					arrayList.add("是皮肤一种天然成分，具有较好抗氧化力和刺激免疫力，能快速地与肌肤内的水分和油脂相溶，形成天然的皮肤保护屏障。能有效保护皮肤不受外界物质的侵害，极佳的渗透力，高效的携氧性，能调整肌肤的水油平衡，避免肌肤粗糙、暗沉，恢复肌肤原本的柔嫩触感。尤其适合在寒冷季节中使用，是肌肤的润滑油，能补充皮肤天然皮脂。");
					arrayList.add("对纤维蛋白增长的促进作用、对弹性蛋白的抑制、对胶原蛋白的合成已经对自由基的清除作用，说明有很好的活肤、抗老化在作用；有助于改变皮脂的组成，减少油光和增加皮肤的柔润程度；同时具有抗菌消炎和保湿的作用。");
					arrayList.add("来源于墨角藻，其中含有的谷氨酸可增加肌肤的柔软和光滑性，有效滋养肌肤，为肌肤提供皮脂膜，同时保护肌肤，帮助脂肪细胞的代谢及结缔组织的新生。其中含有的矿物质、维生素和多酚，能够刺激身体的天然愈合过程。");
					arrayList.add("对紫外线有很好的防护作用，可增强皮肤新陈代谢，有抗衰作用；对κB受体活化有抑制作用，反映抗炎能力；同时具有优秀的保湿能力。");
					arrayList.add("能够促进胶原蛋白及弹力蛋白合成,增加皮肤弹性及紧致感,可用于祛除妊娠纹,消除肥胖纹及暗沉产品。具有良好的保湿和调理肌肤的效果。");
					arrayList.add("高含量的天然植物蛋白。具有补充细胞营养的功效，有效保湿的作用。");
					arrayList.add("属于小分子保湿剂，与膜蛋白有很好的亲和性，可用作皮肤渗透剂增加皮肤对营养成分的吸收。增加细胞的水化功能，有良好的保湿性，在治疗由于皮肤干燥引起的皮屑增多、燥热、角质硬化有特效。");
					break;
				case 0://第二个标题
					arrayList.add("Step 1:");
					arrayList.add("Step 2:");
					break;
				case 1://第二个标题的内容
					arrayList.add("顺着肌肤纹理轻轻按压，涂抹轻拍，顺序为：脸颊→额头→眼部→鼻子→下巴；");
					arrayList.add("在面部颜色较暗哑的部位可适当增加用量,达到提亮肤色的效果。");
					break;
			}
				break;


			case 2:
			switch(i) {
				case 2://第一个标题
					arrayList.add("鲟鱼子酱提取物");
					arrayList.add("墨角藻提取物");
					arrayList.add("蜀葵提取物");
					arrayList.add("海藻糖");
					break;
				case 3://第一个标题的内容
					arrayList.add("它能给皮肤提供保湿、滋养、抗衰老、补充维生素和防护功能，能够帮助皮肤逆转老化过程。高含量的不饱和脂肪可以滋润干燥老化的皮肤、维生素和微量元素可以恢复皮肤活力。");
					arrayList.add("来源于墨角藻，其中含有的谷氨酸可增加肌肤的柔软和光滑性，有效滋养肌肤，为肌肤提供皮脂膜，同时保护肌肤，帮助脂肪细胞的代谢及结缔组织的新生。其中含有的矿物质、维生素和多酚，能够刺激身体的天然愈合过程。");
					arrayList.add("对紫外线有很好的防护作用，可增强皮肤新陈代谢，有抗衰作用；对κB受体活化有抑制作用，反映抗炎能力；同时具有优秀的保湿能力。");
					arrayList.add("属于小分子保湿剂，与膜蛋白有很好的亲和性，可用作皮肤渗透剂增加皮肤对营养成分的吸收。增加细胞的水化功能，有良好的保湿性，在治疗由于皮肤干燥引起的皮屑增多、燥热、角质硬化有特效。");
					break;
				case 0://第二个标题
					arrayList.add("Step 1:");
					arrayList.add("Step 2:");
					arrayList.add("Step 3:");
					arrayList.add("Step 4:");
					arrayList.add("Step 5:");
					arrayList.add("Step 6:");
					arrayList.add("PS：");
					break;
				case 1://第二个标题的内容
					arrayList.add("取出面膜展开，先将额头、鼻梁、下巴，这三处贴好固定；");
					arrayList.add("然后撕下外层膜纸，双手从嘴角处向耳际提拉膜纸；");
					arrayList.add("仔细将面膜里的空气压出，使膜纸完全贴合肌肤；");
					arrayList.add("静享10-15分钟的美好时光；");
					arrayList.add("从下巴处向上慢慢揭开膜纸。");
					arrayList.add("在面部轻轻打圈按摩，帮助肌肤吸收剩余在脸上的精华液。");
					arrayList.add("精华吸收后仍有黏腻感，用清水洗净即可。");
					break;
			}
				break;
			case 3:
			switch(i) {
				case 2://第一个标题
					arrayList.add("酵母提取物");
					arrayList.add("壳聚糖");
					break;
				case 3://第一个标题的内容
					arrayList.add("做皮肤调理剂使用，该成分含有丰富的天然氨基酸中的各种营养成分细胞增殖作用，能够抑制酪氨酸酶活性，从而达到抑制黑色素，达到美白、长效保湿以及加速老化角质层细胞更新换代的作用，并且具有良好的安全性和稳定性。");
					arrayList.add("本物质的脱乙酰壳多糖基团具有良好的生物相容性、黏附性、降解性、抑菌性和可塑性,可促进伤口愈合和组织再生，可能对黏膜有刺激。");
					break;
				case 0://第二个标题
					arrayList.add("Step 1:");
					arrayList.add("Step 2:");
					break;
				case 1://第二个标题的内容
					arrayList.add("使用化妆棉蘸取爽肤水自下而上轻抚脸部，补水的同时还可以起到二次清洁作用。");
					arrayList.add("待产品吸收后，将化妆水倒在掌心，待掌心温度使之有温感之后再均匀轻拍于脸部肌肤，进行二次补水。");
					break;
			}
				break;

			case 4:
				switch(i) {
					case 2://第一个标题
						arrayList.add("角鲨烷");
						break;
					case 3://第一个标题的内容
						arrayList.add("皮肤一种天然成分，具有较好抗氧化力和刺激免疫力，能快速地与肌肤内的水分和油脂相溶，形成天然的皮肤保护屏障，稳定性极佳，不会与空气、紫外线等发生氧化作用，能有效保护皮肤不受外界物质的侵害，极佳的渗透力，高效的携氧性，能调整肌肤的水油平衡，避免肌肤粗糙、暗沉，恢复肌肤原本的柔嫩触感。尤其适合在寒冷季节中使用，是肌肤的润滑油，能补充皮肤天然皮脂。");
						break;
					case 0://第二个标题
						arrayList.add("Step 1:");
						arrayList.add("Step 2:");
						arrayList.add("额头:");
						arrayList.add("I区:");
						arrayList.add("两颊:");
						break;
					case 1://第二个标题的内容
						arrayList.add("取2-4滴美容油，轻柔、均匀的涂抹于面部。");
						arrayList.add("使用美容指按摩。将面部分成额头、I区、两颊三部分，分别按摩。\n" +
								"（美容指：中指和无名指）");
						arrayList.add("由眉心向发际螺旋状按摩。");
						arrayList.add("鼻梁至下巴，由上往下打圈按摩。");
						arrayList.add("以鼻翼处为出发点至耳际方向，由内到外轻轻打圈按摩再提拉。");
						break;
				}
				break;
			case 5:
				switch(i) {
					case 2://第一个标题
						arrayList.add("角鲨烷");
						arrayList.add("乳木果油");
						arrayList.add("樱桃提取物");
						arrayList.add("维生素E");
						break;
					case 3://第一个标题的内容
						arrayList.add("皮肤一种天然成分，具有较好抗氧化力和刺激免疫力，能快速地与肌肤内的水分和油脂相溶，形成天然的皮肤保护屏障，稳定性极佳，不会与空气、紫外线等发生氧化作用，能有效保护皮肤不受外界物质的侵害，极佳的渗透力，高效的携氧性，能调整肌肤的水油平衡，避免肌肤粗糙、暗沉，恢复肌肤原本的柔嫩触感。尤其适合在寒冷季节中使用，是肌肤的润滑油，能补充皮肤天然皮脂。");
						arrayList.add("皮肤一种天然成分，具有较好抗氧化力和刺激免疫力，能快速地与肌肤内的水分和油脂相溶，形成天然的皮肤保护屏障，稳定性极佳，不会与空气、紫外线等发生氧化作用，能有效保护皮肤不受外界物质的侵害，极佳的渗透力，高效的携氧性，能调整肌肤的水油平衡，避免肌肤粗糙、暗沉，恢复肌肤原本的柔嫩触感。尤其适合在寒冷季节中使用，是肌肤的润滑油，能补充皮肤天然皮脂。");
						arrayList.add("皮肤一种天然成分，具有较好抗氧化力和刺激免疫力，能快速地与肌肤内的水分和油脂相溶，形成天然的皮肤保护屏障，稳定性极佳，不会与空气、紫外线等发生氧化作用，能有效保护皮肤不受外界物质的侵害，极佳的渗透力，高效的携氧性，能调整肌肤的水油平衡，避免肌肤粗糙、暗沉，恢复肌肤原本的柔嫩触感。尤其适合在寒冷季节中使用，是肌肤的润滑油，能补充皮肤天然皮脂。");
						arrayList.add("有很好的抗氧化效果，它是一种油溶性的天然物质，对肌肤是很好的营养滋润剂。在保湿和维持结缔组织，以及保护皮肤不受紫外线伤害都有很好表现，该成分能使肌肤触感柔软并保持肌肤水分，促进伤口愈合、预防发炎防止肌肤粗糙皲裂、改善黑斑。");
						break;
					case 0://第二个标题
						break;
					case 1://第二个标题的内容
						break;
				}
				break;
			case 6:
				switch(i) {
					case 2://第一个标题
						arrayList.add("Blue lagoon water(波利尼西亚礁岛海水）");
						arrayList.add("Alpine-E(瑞士高山植物提取物）");
						arrayList.add("Phyto-HA(植物性透明质酸）");
						arrayList.add("亚洲白桦提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("净化和激励皮肤，含有多种矿物质成分，在肌肤表面建立锁水保护膜，智能保湿，改善肌肤粗糙无光泽，回复肌肤光彩和弹性。");
						arrayList.add("超强保湿锁水，安抚镇静肌肤，增强肌肤免疫力，消除炎症。");
						arrayList.add("高保湿效果，润滑肌肤，抗氧化。");
						arrayList.add("维持NMF和角质层的水分平衡，防止角质硬化，提高皮肤对环境的适应性，具有保湿和抗氧化效果。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("额头 :");
						arrayList.add("I区 :");
						arrayList.add("两颊 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取一颗黄豆大小的精华液，轻柔均匀的涂抹于面部。");
						arrayList.add("使用美容指按摩。将面部分成额头、I区、两颊三部分，分别按摩。\n" +
								"（美容指：中指和无名指）");
						arrayList.add("由眉心向发际螺旋状按摩；");
						arrayList.add("鼻梁至下巴，由上往下打圈按摩；");
						arrayList.add("以鼻翼处为出发点至耳际方向，由内到外轻轻打圈按摩再提拉。");
						break;
				}
				break;

			case 7:
				switch(i) {
					case 2://第一个标题
						arrayList.add("百合提取物");
						arrayList.add("Osmopure复合物");
						arrayList.add("熊果苷");
						arrayList.add("烟酰胺");
						break;
					case 3://第一个标题的内容
						arrayList.add("减少有害射线，缓解皮肤刺激，柔软肌肤，赋予亮彩新颜。");
						arrayList.add("阻碍大气污染对皮肤细胞的伤害；缓解压力，镇定肌肤，减少刺激。赋予肌肤锁水保湿能力。");
						arrayList.add("抑制酪氨酸酶的活性，阻断黑色素生成，减少皮肤色素沉积，淡化色斑和雀斑。");
						arrayList.add("阻碍角质蛋白与黑色素母细胞间的联系，约束黑色素的转移，减少沉淀的色素，提亮肤色。预防痘痘的生成和淡化痘印的作用。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("额头 :");
						arrayList.add("I区 :");
						arrayList.add("两颊 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取一颗黄豆大小的精华液，轻柔均匀的涂抹于面部。");
						arrayList.add("使用美容指按摩。将面部分成额头、I区、两颊三部分，分别按摩。\n" +
								"（美容指：中指和无名指）");
						arrayList.add("由眉心向发际螺旋状按摩；");
						arrayList.add("鼻梁至下巴，由上往下打圈按摩；");
						arrayList.add("以鼻翼处为出发点至耳际方向，由内到外轻轻打圈按摩再提拉。");
						break;
				}
				break;

			case 8:
				switch(i) {
					case 2://第一个标题
						arrayList.add("腺苷(Adenosine)");
						arrayList.add("水解伸展蛋白");
						arrayList.add("猴面包果肉提取物");
						arrayList.add("PADSE复合物");
						break;
					case 3://第一个标题的内容
						arrayList.add("可促进蛋白质的合成，增加皮肤弹性。");
						arrayList.add("富含羟脯氨酸（羟脯氨酸是维持皮肤弹性的重要组成成分），特有的保湿性和吸湿性。在皮肤表面形成保护膜，有助于胶原蛋白的重建及弹性蛋白的再生。");
						arrayList.add("可瞬间平滑肌肤纹路、减少皮肤表面细纹的数量、增加皮肤中水分的含量。");
						arrayList.add("可在皮肤表面形成三重网络结构 ，即时修复细纹。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("额头 :");
						arrayList.add("I区 :");
						arrayList.add("两颊 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取一颗黄豆大小的精华液，轻柔均匀的涂抹于面部。");
						arrayList.add("使用美容指按摩。将面部分成额头、I区、两颊三部分，分别按摩。\n" +
								"（美容指：中指和无名指）");
						arrayList.add("由眉心向发际螺旋状按摩；");
						arrayList.add("鼻梁至下巴，由上往下打圈按摩；");
						arrayList.add("以鼻翼处为出发点至耳际方向，由内到外轻轻打圈按摩再提拉。");
						break;
				}
				break;
//幻时凝润系列
			case 9:
				switch(i) {
					case 2://第一个标题
						arrayList.add("腺苷");
						arrayList.add("猴面包树（ADANSONIA DIGITATA）果肉提取物");
						arrayList.add("生育酚乙酸酯");
						arrayList.add("柏氏灰莉(FAGRAEA BERTERROANA)花提取物");
						arrayList.add("海巴戟（MORINDA CITRIFOLIA）叶提取物");
						arrayList.add("藿香蓟（AGERATUM CONYZOIDES）叶提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("腺苷在化妆品中作抗氧化剂、皮肤调理剂、保湿剂使用。\n" +
								"腺苷为抗衰老导入体，能补充肌肤能量，显著改善皮肤的弹性，促进皮肤细胞代谢，防止皮肤组织的松弛，能重新组织皮肤结构，使肌肤通过收紧、收细毛孔而恢复弹性。性的重要组成成分），特有的保湿性和吸湿性。在皮肤表面形成保护膜，有助于胶原蛋白的重建及弹性蛋白的再生。");
						arrayList.add("猴面包树果和叶提取物可以柔润皮肤，有良好的抗氧性，可用于抗衰化妆品。");
						arrayList.add("生育酚乙酸酯属于维生素E的衍生物，在化妆品中常做抗氧化剂使用，有很好的抗氧化效果，它是一种油溶性的天然物质，对肌肤是很好的营养滋润剂，添加于保养品中，能够避免油脂的酸败氧化，安定油脂。在保湿和维持结缔组织，以及保护皮肤不受紫外线伤害都有很好表现，该成分能使肌肤触感柔软并保持肌肤水分，促进伤口愈合、预防发炎防止肌肤粗糙皲裂、改善黑斑，安全风险指数不高故使用广泛。");
						arrayList.add("柏氏灰莉(FAGRAEA BERTERROANA)花提取物是一种皮肤调理剂。");
						arrayList.add("国外又名诺丽果，茜草科巴戟天属植物，含有20%的多糖。其含有极强抗氧功效（多种黄酮类物质）,有抗免疫调节、降血糖、抗病毒、降血脂等作用.用于头发皮肤护理，亦可用于防晒。");
						arrayList.add("为唇形科植物藿香的地上部份。藿香含挥发油，有甲基胡椒酚，茴香脑，茴香醛)，柠檬烯，有良好的清凉感，用于皮肤护理。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量产品放入掌心，加入少量水。");
						arrayList.add("使用另一支手的食指和中指，快速摩擦掌心的洁面膏体，使膏体与水充分混合，打出大量泡沫。");
						arrayList.add("将泡沫涂在脸上，让泡沫遍及整个面部，然后轻轻打圈按摩半分钟左右，注意手法要轻，避免手掌和面部摩擦产生皱纹。");
						arrayList.add("使用温水清洁面部泡沫，以脸部肌肤不涩，不滑腻，自然舒适即可。");
						break;
				}
				break;

			case 10:
				switch(i) {
					case 2://第一个标题
						arrayList.add("水解伸展蛋白");
						arrayList.add("腺苷");
						arrayList.add("甜扁桃（PRUNUS AMYGDALUS DULCIS）籽提取物");
						arrayList.add("生育酚乙酸酯");
						arrayList.add("熊果苷");
						arrayList.add("柏氏灰莉(FAGRAEA BERTERROANA)花提取物");
						arrayList.add("藿香蓟（AGERATUM CONYZOIDES）叶提取物");
						arrayList.add("海巴戟（MORINDA CITRIFOLIA）叶提取物");
						arrayList.add("猴面包树（ADANSONIA DIGITATA）果肉提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("高效保湿成分，活化肌肤，诱导组织再生。");
						arrayList.add("腺苷在化妆品中作抗氧化剂、皮肤调理剂、保湿剂使用。\n" +
								"腺苷为抗衰老导入体，能补充肌肤能量，显著改善皮肤的弹性，促进皮肤细胞代谢，防止皮肤组织的松弛，能重新组织皮肤结构，使肌肤通过收紧、收细毛孔而恢复弹性。");
						arrayList.add("一种保养皮肤及滋润效果极佳的植物油，富含矿物质、蛋白质及各种维他命，超强的补水保湿能力，同时能有效地减皮肤发痒、红肿、干燥和发炎现象，具有舒缓与抗过敏的作用。");
						arrayList.add("生育酚乙酸酯属于维生素E的衍生物，在化妆品中常做抗氧化剂使用，有很好的抗氧化效果，它是一种油溶性的天然物质，对肌肤是很好的营养滋润剂，添加于保养品中，能够避免油脂的酸败氧化，安定油脂。在保湿和维持结缔组织，以及保护皮肤不受紫外线伤害都有很好表现，该成分能使肌肤触感柔软并保持肌肤水分，促进伤口愈合、预防发炎防止肌肤粗糙皲裂、改善黑斑，安全风险指数不高故使用广泛。");
						arrayList.add("熊果苷为美白成分，通过抑制生产黑色素的酵素酪氨酸酶活性，减少黑色素生成。");
						arrayList.add("柏氏灰莉(FAGRAEA BERTERROANA)花提取物是一种皮肤调理剂。");
						arrayList.add("为唇形科植物藿香的地上部份。藿香含挥发油，有甲基胡椒酚，茴香脑，茴香醛)，柠檬烯，有良好的清凉感，用于皮肤护理。");
						arrayList.add("国外又名诺丽果，茜草科巴戟天属植物，含有20%的多糖。其含有极强抗氧功效（多种黄酮类物质）,有抗免疫调节、降血糖、抗病毒、降血脂等作用.用于头发皮肤护理，亦可用于防晒。");
						arrayList.add("猴面包树种子油可以柔润皮肤，易与乳化剂配伍，可以作为高级的油基材料；猴面包树果和叶提取物有良好的抗氧性，可用于抗衰化妆品。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("使用化妆棉蘸取亲肤水自下而上轻抚脸部，补水的同时还可以起到二次清洁作用。");
						arrayList.add("待产品吸收后，将亲肤水倒在掌心，待掌心温度使之有温感之后再均匀轻拍于脸部肌肤，进行二次补水。");
						break;
				}
				break;

			case 11:
				switch(i) {
					case 2://第一个标题
						arrayList.add("水解伸展蛋白");
						arrayList.add("全缘叶澳洲坚果（MACADAMIA INTEGRIFOLIA）籽油");
						arrayList.add("腺苷");
						arrayList.add("甜扁桃（PRUNUS AMYGDALUS DULCIS）籽提取物");
						arrayList.add("海藻糖");
						arrayList.add("生育酚乙酸酯");
						arrayList.add("藿香蓟（AGERATUM CONYZOIDES）叶提取物");
						arrayList.add("海巴戟（MORINDA CITRIFOLIA）叶提取物");
						arrayList.add("柏氏灰莉(FAGRAEA BERTERROANA)花提取物");
						arrayList.add("猴面包树（ADANSONIA DIGITATA）果肉提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("高效保湿成分，活化肌肤，诱导组织再生。");
						arrayList.add("推展性及吸收性良好，能让肌肤有柔嫩感及强化肌肤抵抗能力，除此之外坚果油具有部分天然防晒能力。");
						arrayList.add("腺苷在化妆品中作抗氧化剂、皮肤调理剂、保湿剂使用。\n" +
								"\n" +
								"腺苷为抗衰老导入体，能补充肌肤能量，显著改善皮肤的弹性，促进皮肤细胞代谢，防止皮肤组织的松弛，能重新组织皮肤结构，使肌肤通过收紧、收细毛孔而恢复弹性。");
						arrayList.add("一种保养皮肤及滋润效果极佳的植物油，富含矿物质、蛋白质及各种维他命，超强的补水保湿能力，同时能有效地减皮肤发痒、红肿、干燥和发炎现象，具有舒缓与抗过敏的作用。");
						arrayList.add("海藻糖是一种天然糖类，属于小分子保湿剂，与膜蛋白有很好的亲和性，可用作皮肤渗透剂增加皮肤对营养成分的吸收。增加细胞的水化功能，有良好的保湿性，在治疗由于皮肤干燥引起的皮屑增多、燥热、角质硬化有特效。");
						arrayList.add("生育酚乙酸酯属于维生素E的衍生物，在化妆品中常做抗氧化剂使用，有很好的抗氧化效果，它是一种油溶性的天然物质，对肌肤是很好的营养滋润剂，添加于保养品中，能够避免油脂的酸败氧化，安定油脂。在保湿和维持结缔组织，以及保护皮肤不受紫外线伤害都有很好表现，该成分能使肌肤触感柔软并保持肌肤水分，促进伤口愈合、预防发炎防止肌肤粗糙皲裂、改善黑斑，安全风险指数不高故使用广泛。");
						arrayList.add("为唇形科植物藿香的地上部份。藿香含挥发油，有甲基胡椒酚，茴香脑，茴香醛)，柠檬烯，有良好的清凉感，用于皮肤护理。");
						arrayList.add("国外又名诺丽果，茜草科巴戟天属植物，含有20%的多糖。其含有极强抗氧功效（多种黄酮类物质）,有抗免疫调节、降血糖、抗病毒、降血脂等作用.用于头发皮肤护理，亦可用于防晒。");
						arrayList.add("柏氏灰莉(FAGRAEA BERTERROANA)花提取物是一种皮肤调理剂。");
						arrayList.add("猴面包树种子油可以柔润皮肤，易与乳化剂配伍，可以作为高级的油基材料；猴面包树果和叶提取物有良好的抗氧性，可用于抗衰化妆品。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量乳液点涂以下部位：两颊、嘴角两侧、上下颚。");
						arrayList.add("再取适量点涂于T区、两颊，然后均匀涂抹于面部，可以使干燥的部位得到双重滋润。");
						arrayList.add("最后取适量乳液涂抹于颈部。抬起下巴，由下至上轻轻提拉按摩，直至面霜完全吸收。");
						break;
				}
				break;

			case 12:
				switch(i) {
					case 2://第一个标题
						arrayList.add("水解伸展蛋白");
						arrayList.add("透明质酸");
						arrayList.add("甜扁桃（PRUNUS AMYGDALUS DULCIS）籽提取物");
						arrayList.add("腺苷");
						arrayList.add("猴面包树（ADANSONIA DIGITATA）果肉提取物");
						arrayList.add("牛油果树（BUTYROSPERMUM PARKII）果脂");
						arrayList.add("全缘叶澳洲坚果（MACADAMIA INTEGRIFOLIA）籽油");
						arrayList.add("尿囊素");
						arrayList.add("海巴戟（MORINDA CITRIFOLIA）叶提取物");
						arrayList.add("藿香蓟（AGERATUM CONYZOIDES）叶提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("高效保湿成分，活化肌肤，诱导组织再生。");
						arrayList.add("透明质酸钠属于肌肤调理剂，天然存在于角膜皮中，可吸收其本身重量1000倍水分，以达到通过保留皮肤水分、阻止水分经表皮流失，以及当皮肤受到损伤时的屏障修复，令皮肤使用后不会感到干燥，高效抓水，使皮肤柔润有光泽，是目前最佳的水溶性保湿剂。");
						arrayList.add("一种保养皮肤及滋润效果极佳的植物油，富含矿物质、蛋白质及各种维他命，超强的补水保湿能力，同时能有效地减皮肤发痒、红肿、干燥和发炎现象，具有舒缓与抗过敏的作用。 ");
						arrayList.add("腺苷在化妆品中作抗氧化剂、皮肤调理剂、保湿剂使用。\n" +
								"\n" +
								"腺苷为抗衰老导入体，能补充肌肤能量，显著改善皮肤的弹性，促进皮肤细胞代谢，防止皮肤组织的松弛，能重新组织皮肤结构，使肌肤通过收紧、收细毛孔而恢复弹性。");
						arrayList.add("猴面包树果和叶提取物可以柔润皮肤，有良好的抗氧性，可用于抗衰化妆品。");
						arrayList.add("从牛油果树的果实中提取而成，含有丰富的不饱和脂肪酸，能够加强皮肤的保湿能力，对干性皮肤即角质受损的肌肤能加以滋润。同时可以调节产品的流动性，改善粘度，提高产品的感官品质和使用肤感。");
						arrayList.add("推展性及吸收性良好，适合作为按摩、护发护肤使用，添加于护发品中能改善头皮屑问题。能让肌肤有柔嫩感及强化肌肤抵抗能力，除此之外坚果油具有部分天然防晒能力。");
						arrayList.add("尿囊素是一种无毒、无味、无刺激性、无过敏性的白色粉体，主要做皮肤调理剂、防护剂使用，能够帮助皮肤抗炎、舒缓并促进细胞修复。尿囊素可降低角质层细胞的粘着力，加速表皮细胞更新。一些含有果酸、维生素A具有刺激性的化妆品或消炎镇静的晒后修护产品都会添加尿囊素增强其修复能力。但是尿囊素添加剂量在0.1%—0.2%左右，添加过量亦会引发刺激反应。但尿囊素总体来看是抗敏成分，安全度高。");
						arrayList.add("国外又名诺丽果，茜草科巴戟天属植物，含有20%的多糖。其含有极强抗氧功效（多种黄酮类物质）,有抗免疫调节、降血糖、抗病毒、降血脂等作用.用于头发皮肤护理，亦可用于防晒。");
						arrayList.add("为唇形科植物藿香的地上部份。藿香含挥发油，有甲基胡椒酚，茴香脑，茴香醛)，柠檬烯，有良好的清凉感，用于皮肤护理。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量乳液点涂以下部位：两颊、嘴角两侧、上下颚。");
						arrayList.add("再取适量点涂于T区、两颊，然后均匀涂抹于面部，可以使干燥的部位得到双重滋润。");
						arrayList.add("最后取适量乳液涂抹于颈部。抬起下巴，由下至上轻轻提拉按摩，直至面霜完全吸收。");
						break;
				}
				break;

			case 13:
				switch(i) {
					case 2://第一个标题
						arrayList.add("透明质酸（玻尿酸）");
						arrayList.add("甜扁桃（PRUNUS AMYGDALUS DULCIS）籽提取物");
						arrayList.add("腺苷");
						arrayList.add("猴面包树（ADANSONIA DIGITATA）果肉提取物");
						arrayList.add("糖基海藻糖");
						arrayList.add("库拉索芦荟（ALOE BARBADENSIS）叶汁");
						arrayList.add("水解伸展蛋白");
						arrayList.add("β-葡聚糖");
						arrayList.add("海巴戟（MORINDA CITRIFOLIA）叶提取物");
						arrayList.add("藿香蓟（AGERATUM CONYZOIDES）叶提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("属于肌肤调理剂，天然存在于角膜皮中，可吸收其本身重量1000倍水分，以达到通过保留皮肤水分、阻止水分经表皮流失，以及当皮肤受到损伤时的屏障修复，令皮肤使用后不会感到干燥，高效抓水，使皮肤柔润有光泽，是目前最佳的水溶性保湿剂。");
						arrayList.add("一种保养皮肤及滋润效果极佳的植物油，富含矿物质、蛋白质及各种维他命，超强的补水保湿能力，同时能有效地减皮肤发痒、红肿、干燥和发炎现象，具有舒缓与抗过敏的作用。");
						arrayList.add("腺苷在化妆品中作抗氧化剂、皮肤调理剂、保湿剂使用。\n" +
								"\n" +
								"腺苷为抗衰老导入体，能补充肌肤能量，显著改善皮肤的弹性，促进皮肤细胞代谢，防止皮肤组织的松弛，能重新组织皮肤结构，使肌肤通过收紧、收细毛孔而恢复弹性。");
						arrayList.add("猴面包树果和叶提取物可以柔润皮肤，有良好的抗氧性，可用于抗衰化妆品。");
						arrayList.add("用于化妆品具有超强的保持细胞活力和生物活性功能，在高温、干燥、强紫外线等环境下，可在细胞面层形成一层特殊的保护膜，不仅保持皮肤原有的营养和水分，还能滋养皮肤细胞。");
						arrayList.add("显示了多方面的生物活性，是化妆品重要的添加剂。从其对纤维蛋白增长的促进作用、对弹性蛋白的抑制、对胶原蛋白的合成以及对自由基的清除作用，说明有很好的活肤、抗老化的作用；对胆甾醇合成有促进作用，有助于改变皮脂的组成，减少油光和增加皮肤的柔润程度；同时具有抗菌消炎和保湿的作用。也用作皮肤晒黑剂。");
						arrayList.add("高效保湿成分，活化肌肤，诱导组织再生。");
						arrayList.add("β-葡聚糖为天然植物胶聚糖，在化妆品中皮肤调理剂和保湿剂使用，同时该成分还有一定抗敏抗炎等作用。该成分为水溶性，能与大多数原料有较好配伍性，无过敏反应，还能提高皮肤保湿能力。该成分还能起到抗氧化和保护皮肤免受紫外线伤害的作用，能够减少皮肤皱纹，提高皮肤抵御外界伤害的能力。");
						arrayList.add("国外又名诺丽果，茜草科巴戟天属植物，含有20%的多糖。其含有极强抗氧功效（多种黄酮类物质）,有抗免疫调节、降血糖、抗病毒、降血脂等作用.用于头发皮肤护理，亦可用于防晒。");
						arrayList.add("为唇形科植物藿香的地上部份。藿香含挥发油，有甲基胡椒酚，茴香脑，茴香醛)，柠檬烯，有良好的清凉感，用于皮肤护理。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						arrayList.add("Step5 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("在早晚洁肤后，用无名指取绿豆大小的眼霜，两个无名指指腹相互揉搓，给眼霜加温，使之更容易被肌肤吸收。");
						arrayList.add("以弹钢琴的方式，均匀地轻轻将眼霜拍打在眼周肌肤上。着重在下眼窝和眼尾至太阳穴的延伸部位多加涂抹。");
						arrayList.add("先从眼部下方，由睛明穴向眼尾轻轻按压。然后从眼部上方，由内向外轻轻按压。");
						arrayList.add("用中指指腹从眉头下方开始，轻轻按压。再沿着眼眶，由内向外轻轻按压。");
						arrayList.add("用中指指尖，轻轻按压鼻翼两旁的迎香穴，促进眼部肌肤的血液循环。");
						break;
				}
				break;

			case 14:
				switch(i) {
					case 2://第一个标题
						arrayList.add("甜扁桃（PRUNUS AMYGDALUS DULCIS）籽提取物");
						arrayList.add("腺苷");
						arrayList.add("猴面包树（ADANSONIA DIGITATA）果肉提取物");
						arrayList.add("水解伸展蛋白");
						break;
					case 3://第一个标题的内容
						arrayList.add("一种保养皮肤及滋润效果极佳的植物油，富含矿物质、蛋白质及各种维他命，超强的补水保湿能力，同时能有效地减皮肤发痒、红肿、干燥和发炎现象，具有舒缓与抗过敏的作用。 ");
						arrayList.add("腺苷在化妆品中作抗氧化剂、皮肤调理剂、保湿剂使用。\n" +
								"\n" +
								"腺苷为抗衰老导入体，能补充肌肤能量，显著改善皮肤的弹性，促进皮肤细胞代谢，防止皮肤组织的松弛，能重新组织皮肤结构，使肌肤通过收紧、收细毛孔而恢复弹性。");
						arrayList.add("猴面包树果和叶提取物可以柔润皮肤，有良好的抗氧性，可用于抗衰化妆品。");
						arrayList.add("高效保湿成分，活化肌肤，诱导组织再生。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						arrayList.add("Step5 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("在早晚洁肤后，用无名指取绿豆大小的眼霜，两个无名指指腹相互揉搓，给眼霜加温，使之更容易被肌肤吸收。");
						arrayList.add("以弹钢琴的方式，均匀地轻轻将眼霜拍打在眼周肌肤上。着重在下眼窝和眼尾至太阳穴的延伸部位多加涂抹。");
						arrayList.add("先从眼部下方，由睛明穴向眼尾轻轻按压。然后从眼部上方，由内向外轻轻按压。");
						arrayList.add("用中指指腹从眉头下方开始，轻轻按压。再沿着眼眶，由内向外轻轻按压。");
						arrayList.add("用中指指尖，轻轻按压鼻翼两旁的迎香穴，促进眼部肌肤的血液循环。");
						break;
				}
				break;


			//净颜修容系列
			case 15:
				switch(i) {
					case 2://第一个标题
						arrayList.add("雪莲花提取物");
						arrayList.add("生育酚（维生素E）");
						break;
					case 3://第一个标题的内容
						arrayList.add("可加速皮肤的新陈代谢，减少皱纹，使皮肤保持光泽、丰满。延缓衰老。并且对面部雀斑、肝斑等有良好的疗效。");
						arrayList.add("延缓衰老，有效减少皱纹的产生，保持青春的容貌。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						arrayList.add("Step5 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("首先是U字部，顺着毛孔生长的方向轻轻涂抹均匀；");
						arrayList.add("其次是T字部，用同样的手法沾取粉饼，顺着T字部位涂抹均匀；");
						arrayList.add("再次是眼部周围，沾取粉饼轻轻的按压，使其均匀；");
						arrayList.add("接着是鼻翼、嘴角、下巴、耳后等死角，少量多次的沾取粉饼，轻轻的涂抹均匀；");
						arrayList.add("最后是颈部，由脸颊的边界开始，从上到下轻轻涂抹均匀，使面部与颈部的肌肤颜色更自然。");
						break;
				}
				break;

			case 16:
				switch(i) {
					case 2://第一个标题
						arrayList.add("透明质酸钠");
						arrayList.add("莲花提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("可以改善皮肤新陈代谢，具有高效的保湿效果。");
						arrayList.add("抗自由基，预防皮肤老化，促使皮肤光滑透亮。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						arrayList.add("Step5 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("沾取适量隔离霜点涂于颧骨，利用美容指（中指+无名指）在颧骨打圈涂抹");
						arrayList.add("下巴位置, 用同样打圈的方式涂抹均匀；");
						arrayList.add("额头位置，由内往外打圈涂抹，慢慢延展至太阳穴；");
						arrayList.add("发髻线周围，利用海绵沾取适量隔离霜在发髻线周围，轻轻按压均匀；");
						arrayList.add("鼻翼、嘴角，利用海绵轻轻擦拭，涂抹均匀。");
						break;
				}
				break;

			case 17:
				switch(i) {
					case 2://第一个标题
						break;
					case 3://第一个标题的内容
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("首先将防晒分别点涂在额头、鼻梁、下巴、脸颊；");
						arrayList.add("然后用美容指（中指+无名指）用打圈的方式延展开；");
						arrayList.add("再以大范围画圆的方式，将防晒霜涂抹全脸；");
						arrayList.add("最后再加强涂抹鼻尖、两颊等容易晒伤的部位。");
						break;
				}
				break;

			case 18:
				switch(i) {
					case 2://第一个标题
						arrayList.add("巴西棕榈蜡");
						arrayList.add("小烛树蜡");
						break;
					case 3://第一个标题的内容
						arrayList.add("主要由酸和羟基酸的酯组成的复杂混合物.");
						arrayList.add("天然植物蜡，唇膏、家用蜡、手工皂、笔类产品原料中的佼佼者。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("从睫毛根部开始缓慢的向睫毛尖刷一遍，再由睫毛根部到睫毛尖呈Z型涂抹，将每一根睫毛都涂抹到位；");
						arrayList.add("然后涂抹下睫毛：从睫毛根部缓慢的涂抹到睫毛尖，轻刷两次即可，避免多次涂抹出现“苍蝇腿”现象。");
						break;
				}
				break;

			//臻白焕彩系列
			case 19:
				switch(i) {
					case 2://第一个标题
						arrayList.add("白花百合（LILIUM CANDIDUM）花提取物");
						arrayList.add("向日葵（HELIANTHUS ANNUUS）籽提取物");
						arrayList.add("莲（NELUMBO NUCIFERA）花提取物");
						arrayList.add("蔷薇（ROSA CANINA）果提取物");
						arrayList.add("小苍兰（FREESIA REFRACTA）提取物");
						arrayList.add("素方花（JASMINUM OFFICINALE）花/叶提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("对纤维芽细胞有增殖、组织单倍酶活性促进等作用显示，可增强皮肤细胞新陈代谢，有抗衰功能；对荧光素酶活性有促进作用，说明有抗炎作用。");
						arrayList.add("对酪氨酸酶有一定的抑制作用，有一定的美白功效；对过氧化酶激活受体有活化作用，有促进皮肤创伤愈合的抗炎作用；能促进脂肪细胞的分解，有减肥的功效。");
						arrayList.add("对组成蛋白纤维的胶原蛋白、弹性蛋白的生成均有刺激作用，显示其增强皮肤活性的能力，结合抗氧化性，可用于抗衰防皱化妆品；对脂肪分解有强烈的促进作用，可用于减肥化妆品。");
						arrayList.add("可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可抗衰老。");
						arrayList.add("有甜美香味，使人心情舒畅。可以温和去除皮肤污垢滋润皮肤，使肌肤光洁靓丽。");
						arrayList.add("能滋润清凉皮肤，抑菌，气味抑制，消除粉刺，能短时间使皮肤收敛毛孔。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量产品放入掌心，加入少量水。");
						arrayList.add("使用另一支手的食指和中指，快速摩擦掌心的洁面膏体，使膏体与水充分混合，打出大量泡沫。");
						arrayList.add("将泡沫涂在脸上，让泡沫遍及整个面部，然后轻轻打圈按摩半分钟左右，注意手法要轻，避免手掌和面部摩擦产生皱纹。");
						arrayList.add("使用温水清洁面部泡沫，以脸部肌肤不涩，不滑腻，自然舒适即可。");
						break;
				}
				break;

			case 20:
				switch(i) {
					case 2://第一个标题
						arrayList.add("腺苷");
						arrayList.add("海藻糖");
						arrayList.add("熊果苷");
						arrayList.add("烟酰胺");
						arrayList.add("向日葵（HELIANTHUS ANNUUS）籽提取物");
						arrayList.add("白花百合（LILIUM CANDIDUM）花提取物");
						arrayList.add("小苍兰（FREESIA REFRACTA）提取物");
						arrayList.add("全缘叶澳洲坚果（MACADAMIA INTEGRIFOLIA）籽油");
						arrayList.add("PCA 钠 ");
						arrayList.add("变色鸢尾（IRIS VERSICOLOR）提取物");
						arrayList.add("高山火绒草（LEONTOPODIUM ALPINUM）花/叶提取物");
						arrayList.add("莲（NELUMBO NUCIFERA）花提取物");
						arrayList.add("蔷薇（ROSA SPP.）提取物");
						arrayList.add("素方花（JASMINUM OFFICINALE）花/叶提取物");
						arrayList.add("小苍兰（FREESIA REFRACTA）提取物");
						arrayList.add("甘草酸二钾");
						arrayList.add("生育酚乙酸酯");
						arrayList.add("洋常春藤（HEDERA HELIX）叶/茎提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("腺苷在化妆品中作抗氧化剂、皮肤调理剂、保湿剂使用。腺苷为抗衰老导入体，能补充肌肤能量，显著改善皮肤的弹性，促进皮肤细胞代谢，防止皮肤组织的松弛，能重新组织皮肤结构，使肌肤通过收紧、收细毛孔而恢复弹性。");
						arrayList.add("海藻糖是一种天然糖类，属于小分子保湿剂，与膜蛋白有很好的亲和性，可用作皮肤渗透剂增加皮肤对营养成分的吸收。增加细胞的水化功能，有良好的保湿性，在治疗由于皮肤干燥引起的皮屑增多、燥热、角质硬化有特效。");
						arrayList.add("熊果苷为美白成分，通过抑制生产黑色素的酵素酪氨酸酶活性，减少黑色素生成。");
						arrayList.add("作用于已经产生的黑色素，减少其向表层细胞转移，并加速皮肤新陈代谢，促进黑色素胶质细胞脱落，可起到改善肤质的作用。同时烟酰胺还能极大减少或消除皮肤老化现象，比如皱纹、起皮、毛孔粗大等，减少自由基对皮肤的伤害，起到预防老化、修复皮肤和减少面部暗沉的作用。");
						arrayList.add("对酪氨酸酶有一定的抑制作用，有一定的美白功效；对过氧化酶激活受体有活化作用，有促进皮肤创伤愈合的抗炎作用；能促进脂肪细胞的分解，有减肥的功效。");
						arrayList.add("对纤维芽细胞有增殖、组织单倍酶活性促进等作用显示，可增强皮肤细胞新陈代谢，有抗衰功能；对荧光素酶活性有促进作用，说明有抗炎作用。");
						arrayList.add("有甜美香味，使人心情舒畅。可以温和去除皮肤污垢滋润皮肤，使肌肤光洁靓丽。");
						arrayList.add("推展性及吸收性良好，适合作为按摩、护发护肤使用，添加于护发品中能改善头皮屑问题。能让肌肤有柔嫩感及强化肌肤抵抗能力，除此之外坚果油具有部分天然防晒能力。");
						arrayList.add("PCA钠在化妆品中作保湿剂、肌肤调理剂和抗静电剂使用，是皮肤的天然成分，也是很好的保湿剂。有强化角质功能，并用以增强肌肤自身的保湿能力。");
						arrayList.add("在化妆品中不仅可做高档化妆品的香精使用，同时其他组分亦可使皮肤得到改善。对组织蛋白酶活性的促进和对胰蛋白酶的抑制作用显示，可增强皮肤细胞的新陈代谢，有抗衰作用；同时还有抗炎、抑制毛发生长和美白的作用。");
						arrayList.add("对弹性蛋白酶活性的抑制和对内皮蛋白生成的促进作用显示，可减缓弹性蛋白的降解，可维持皮肤弹性，可用于抗皱化妆品；对一些细菌有一定的抑制作用，具有抗菌消炎的作用。");
						arrayList.add("对组成蛋白纤维的胶原蛋白、弹性蛋白的生成均有刺激作用，显示其增强皮肤活性的能力，结合抗氧化性，可用于抗衰防皱化妆品；对脂肪分解有强烈的促进作用，可用于减肥化妆品。");
						arrayList.add("蔷薇花挥发油可用于抵挡香精的调配。对非雄性激素过高而引起的脱发有防治作用；对B-16细胞活性的促进作用，可增加黑色素的分泌，生发的同时，防治白发出现；对血管内皮细胞呈增殖活性，可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可用于抗衰老化妆品。");
						arrayList.add("木犀科、素馨属攀援灌木，中药材中，解毒杀虫。花含挥发油，油中含乙酸苄酯、芳樟醇、茉莉酮、吲哚及邻按基苯甲酸甲酯等。对皮肤有轻微的刺激，能滋润清凉皮肤，抑菌，气味抑制，消除粉刺，能短时间使皮肤收敛毛孔。");
						arrayList.add("有甜美香味，使人心情舒畅。可以温和去除皮肤污垢滋润皮肤，使肌肤光洁靓丽。");
						arrayList.add("甘草酸二钾是作为皮肤调理剂使用，主要为甘草根部及茎部的甘草甜素（甘草酸）成分适合过敏性肌肤，抗刺激及控油，退红肿消炎愈合作用。可有效预防皮肤受刺激时敏感发炎现象。对日照引起的炎症具有消炎镇静作用，常被用于抗过敏及修复化妆品中。");
						arrayList.add("生育酚乙酸酯属于维生素E的衍生物，在化妆品中常做抗氧化剂使用，有很好的抗氧化效果，它是一种油溶性的天然物质，对肌肤是很好的营养滋润剂，添加于保养品中，能够避免油脂的酸败氧化，安定油脂。在保湿和维持结缔组织，以及保护皮肤不受紫外线伤害都有很好表现，该成分能使肌肤触感柔软并保持肌肤水分，促进伤口愈合、预防发炎防止肌肤粗糙皲裂、改善黑斑，安全风险指数不高故使用广泛。");
						arrayList.add("从洋常春藤的叶和茎提取而得，对载脂蛋白的活性有抑制，可以减少腋臭；对干燥棒状杆菌有抑制，可以用于抑臭；有抑制皮脂分泌的作用，适用于油性皮肤的护理。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量乳液点涂以下部位：两颊、嘴角两侧、上下颚。");
						arrayList.add("再取适量点涂于T区、两颊，然后均匀涂抹于面部，可以使干燥的部位得到双重滋润。");
						arrayList.add("最后取适量乳液涂抹于颈部。抬起下巴，由下至上轻轻提拉按摩，直至面霜完全吸收。");
						break;
				}
				break;


			case 21:
				switch(i) {
					case 2://第一个标题
						arrayList.add("白花百合（LILIUM CANDIDUM）花提取物");
						arrayList.add("向日葵（HELIANTHUS ANNUUS）籽提取物");
						arrayList.add("莲（NELUMBO NUCIFERA）花提取物");
						arrayList.add("蔷薇（ROSA CANINA）果提取物");
						arrayList.add("小苍兰（FREESIA REFRACTA）提取物");
						arrayList.add("素方花（JASMINUM OFFICINALE）花/叶提取物");
						arrayList.add("海藻糖");
						break;
					case 3://第一个标题的内容
						arrayList.add("对纤维芽细胞有增殖、组织单倍酶活性促进等作用显示，可增强皮肤细胞新陈代谢，有抗衰功能；对荧光素酶活性有促进作用，说明有抗炎作用。");
						arrayList.add("对酪氨酸酶有一定的抑制作用，有一定的美白功效；对过氧化酶激活受体有活化作用，有促进皮肤创伤愈合的抗炎作用；能促进脂肪细胞的分解，有减肥的功效。");
						arrayList.add("对组成蛋白纤维的胶原蛋白、弹性蛋白的生成均有刺激作用，显示其增强皮肤活性的能力，结合抗氧化性，可用于抗衰防皱化妆品；对脂肪分解有强烈的促进作用，可用于减肥化妆品。");
						arrayList.add("可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可抗衰老。");
						arrayList.add("有甜美香味，使人心情舒畅。可以温和去除皮肤污垢滋润皮肤，使肌肤光洁靓丽。");
						arrayList.add("能滋润清凉皮肤，抑菌，气味抑制，消除粉刺，能短时间使皮肤收敛毛孔。");
						arrayList.add("海藻糖是一种天然糖类，属于小分子保湿剂，与膜蛋白有很好的亲和性，可用作皮肤渗透剂增加皮肤对营养成分的吸收。增加细胞的水化功能，有良好的保湿性，在治疗由于皮肤干燥引起的皮屑增多、燥热、角质硬化有特效。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量面霜点涂以下部位：两颊、嘴角两侧、上下颚。");
						arrayList.add("再取适量点涂于T区、两颊，然后均匀涂抹于面部，可以使干燥的部位得到双重滋润。");
						arrayList.add("最后取适量面霜涂抹于颈部。抬起下巴，由下至上轻轻提拉按摩，直至面霜完全吸收。");
						break;
				}
				break;

			case 22:
				switch(i) {
					case 2://第一个标题
						arrayList.add("百合提取物");
						arrayList.add("Osmopure复合物");
						arrayList.add("熊果苷");
						arrayList.add("烟酰胺");
						arrayList.add("透明质酸钠");
						arrayList.add("生物糖 胶-1");
						arrayList.add("马齿苋（PORTULACA OLERACEA）提取物");
						arrayList.add("向日葵（HELIANTHUS ANNUUS）籽提取物");
						arrayList.add("洋常春藤（HEDERA HELIX）叶/茎提取物");
						arrayList.add("变色鸢尾（IRIS VERSICOLOR）提取物");
						arrayList.add("高山火绒草（LEONTOPODIUM ALPINUM）花/叶提取物");
						arrayList.add("莲（NELUMBO NUCIFERA）花提取物");
						arrayList.add("蔷薇（ROSA SPP.）提取物");
						arrayList.add("素方花（JASMINUM OFFICINALE）花/叶提取物");
						arrayList.add("小苍兰（FREESIA REFRACTA）提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("减少有害射线，缓解皮肤刺激，柔软肌肤，赋予亮彩新颜。");
						arrayList.add("阻碍大气污染对皮肤细胞的伤害；缓解压力，镇定肌肤，减少刺激。赋予肌肤锁水保湿能力。");
						arrayList.add("抑制酪氨酸酶的活性，阻断黑色素生成，减少皮肤色素沉积，淡化色斑和雀斑。");
						arrayList.add("阻碍角质蛋白与黑色素母细胞间的联系，约束黑色素的转移，减少沉淀的色素，提亮肤色。预防痘痘的生成和淡化痘印的作用。");
						arrayList.add("透明质酸钠属于肌肤调理剂，是一种酸性粘多糖，天然存在于角膜皮中，可吸收其本身重量1000倍水分，以达到通过保留皮肤水分、阻止水分经表皮流失，以及当皮肤受到损伤时的屏障修复，令皮肤使用后不会感到干燥，增加光泽，它可以改善皮肤营养代谢，使皮肤柔、光滑、去皱、增加弹性、防止衰老，在保湿的同时又是良好的透皮吸收促进剂。与其他营养成分配合使用，可以起到促进营养吸收的更理想效果。");
						arrayList.add("一种由发酵植物制成的多糖，生物糖 胶-1是一种保湿和皮肤舒缓成分。它可以帮助表皮锁住水分，在皮肤上形成一层保湿膜，使皮肤柔软有光滑感。除了能够增强保湿外，它还具有抗刺激性。");
						arrayList.add("马齿苋提取物具有广谱的抗菌性，又有消炎作用，可防治皮肤湿疹、过敏性皮炎、接触性皮等皮肤病；有良好的氧自由基清除能力，说明其有明显的抗氧化的作用；有良好的皮肤保湿性。");
						arrayList.add("向日葵籽油是化妆品的重要基础油原料，有润滑皮肤的作用。对过氧化酶激活受体有活化作用，有促进皮肤创伤愈合的抗炎作用；能促进脂肪细胞的分解，有减肥的功效；对酪氨酸酶有一定的抑制作用，有一定的美白功效。");
						arrayList.add("从洋常春藤的叶和茎提取而得，对载脂蛋白的活性有抑制，可以减少腋臭；对干燥棒状杆菌有抑制，可以用于抑臭；有抑制皮脂分泌的作用，适用于油性皮肤的护理。");
						arrayList.add("在化妆品中不仅可做高档化妆品的香精使用，同时其他组分亦可使皮肤得到改善。对组织蛋白酶活性的促进和对胰蛋白酶的抑制作用显示，可增强皮肤细胞的新陈代谢，有抗衰作用；同时还有抗炎、抑制毛发生长和美白的作用。");
						arrayList.add("对弹性蛋白酶活性的抑制和对内皮蛋白生成的促进作用显示，可减缓弹性蛋白的降解，可维持皮肤弹性，可用于抗皱化妆品；对一些细菌有一定的抑制作用，具有抗菌消炎的作用。");
						arrayList.add("对组成蛋白纤维的胶原蛋白、弹性蛋白的生成均有刺激作用，显示其增强皮肤活性的能力，结合抗氧化性，可用于抗衰防皱化妆品；对脂肪分解有强烈的促进作用，可用于减肥化妆品。");
						arrayList.add("蔷薇花挥发油可用于抵挡香精的调配。对非雄性激素过高而引起的脱发有防治作用；对B-16细胞活性的促进作用，可增加黑色素的分泌，生发的同时，防治白发出现；对血管内皮细胞呈增殖活性，可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可用于抗衰老化妆品。");
						arrayList.add("木犀科、素馨属攀援灌木，中药材中，解毒杀虫。花含挥发油，油中含乙酸苄酯、芳樟醇、茉莉酮、吲哚及邻按基苯甲酸甲酯等。对皮肤有轻微的刺激，能滋润清凉皮肤，抑菌，气味抑制，消除粉刺，能短时间使皮肤收敛毛孔。");
						arrayList.add("有甜美香味，使人心情舒畅。可以温和去除皮肤污垢滋润皮肤，使肌肤光洁靓丽。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("额头 :");
						arrayList.add("I区 :");
						arrayList.add("两颊 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取黄豆粒大小的精华，轻柔、均匀的涂抹于面部。");
						arrayList.add("使用美容指按摩。将面部分成额头、I区、两颊三部分，分别按摩。\n" +
								"（美容指：中指和无名指）");
						arrayList.add("由眉心向发际螺旋状按摩。");
						arrayList.add("鼻梁至下巴，由上往下打圈按摩。");
						arrayList.add("以鼻翼处为出发点至耳际方向，由内到外轻轻打圈按摩再提拉。");
						break;
				}
				break;

			case 23:
				switch(i) {
					case 2://第一个标题
						arrayList.add("白花百合（LILIUM CANDIDUM）花提取物");
						arrayList.add("向日葵（HELIANTHUS ANNUUS）籽提取物");
						arrayList.add("莲（NELUMBO NUCIFERA）花提取物");
						arrayList.add("蔷薇（ROSA CANINA）果提取物");
						arrayList.add("小苍兰（FREESIA REFRACTA）提取物");
						arrayList.add("素方花（JASMINUM OFFICINALE）花/叶提取物");
						arrayList.add("生物糖 胶-1");
						arrayList.add("透明质酸钠");
						arrayList.add("洋常春藤（HEDERA HELIX）叶/茎提取物");
						arrayList.add("变色鸢尾（IRIS VERSICOLOR）提取物");
						arrayList.add("高山火绒草（LEONTOPODIUM ALPINUM）花/叶提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("对纤维芽细胞有增殖、组织单倍酶活性促进等作用显示，可增强皮肤细胞新陈代谢，有抗衰功能；对荧光素酶活性有促进作用，说明有抗炎作用。");
						arrayList.add("对酪氨酸酶有一定的抑制作用，有一定的美白功效；对过氧化酶激活受体有活化作用，有促进皮肤创伤愈合的抗炎作用；能促进脂肪细胞的分解，有减肥的功效。");
						arrayList.add("对组成蛋白纤维的胶原蛋白、弹性蛋白的生成均有刺激作用，显示其增强皮肤活性的能力，结合抗氧化性，可用于抗衰防皱化妆品；对脂肪分解有强烈的促进作用，可用于减肥化妆品。");
						arrayList.add("可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可抗衰老。");
						arrayList.add("有甜美香味，使人心情舒畅。可以温和去除皮肤污垢滋润皮肤，使肌肤光洁靓丽。");
						arrayList.add("能滋润清凉皮肤，抑菌，气味抑制，消除粉刺，能短时间使皮肤收敛毛孔。");
						arrayList.add("一种由发酵植物制成的多糖，生物糖 胶-1是一种保湿和皮肤舒缓成分。它可以帮助表皮锁住水分，在皮肤上形成一层保湿膜，使皮肤柔软有光滑感。除了能够增强保湿外，它还具有抗刺激性。");
						arrayList.add("透明质酸钠属于肌肤调理剂，是一种酸性粘多糖，天然存在于角膜皮中，可吸收其本身重量1000倍水分，以达到通过保留皮肤水分、阻止水分经表皮流失，以及当皮肤受到损伤时的屏障修复，令皮肤使用后不会感到干燥，增加光泽，它可以改善皮肤营养代谢，使皮肤柔、光滑、去皱、增加弹性、防止衰老，在保湿的同时又是良好的透皮吸收促进剂。");
						arrayList.add("从洋常春藤的叶和茎提取而得，对载脂蛋白的活性有抑制，可以减少腋臭；对干燥棒状杆菌有抑制，可以用于抑臭；有抑制皮脂分泌的作用，适用于油性皮肤的护理。");
						arrayList.add("在化妆品中不仅可做高档化妆品的香精使用，同时其他组分亦可使皮肤得到改善。对组织蛋白酶活性的促进和对胰蛋白酶的抑制作用显示，可增强皮肤细胞的新陈代谢，有抗衰作用；同时还有抗炎、抑制毛发生长和美白的作用。");
						arrayList.add("对弹性蛋白酶活性的抑制和对内皮蛋白生成的促进作用显示，可减缓弹性蛋白的降解，可维持皮肤弹性，可用于抗皱化妆品；对一些细菌有一定的抑制作用，具有抗菌消炎的作用。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("使用化妆棉蘸取亮肤水自下而上轻抚脸部，补水的同时还可以起到二次清洁作用。");
						arrayList.add("待产品吸收后，将亮肤水倒在掌心，待掌心温度使之有温感之后再均匀轻拍于脸部肌肤，进行二次补水。");
						break;
				}
				break;



			//纯净清颜系列
			case 24:
				switch(i) {
					case 2://第一个标题
						arrayList.add("生育酚（维生素E）");
						arrayList.add("茶（CAMELLIA SINENSIS）叶提取物");
						arrayList.add("芒果（MANGIFERA INDICA）果提取物");
						arrayList.add("番木瓜（CARICA PAPAYA）果提取物");
						arrayList.add("葡萄柚（CITRUS PARADISI）果提取物");
						arrayList.add("中华猕猴桃（ACTINIDIA CHINENSIS）果提取物");
						arrayList.add("番石榴（PSIDIUM GUAJAVA）果提取物");
						arrayList.add("线状阿司巴拉妥（ASPALATHUS LINEARIS）叶提取物");
						arrayList.add("素方花（JASMINUM OFFICINALE）花/叶提取物");
						arrayList.add("欧洲赤松（PINUS SYLVESTRIS）芽提取物");
						arrayList.add("海巴戟（MORINDA CITRIFOLIA）叶提取物");
						arrayList.add("藿香蓟（AGERATUM CONYZOIDES）叶提取物");
						arrayList.add("柏氏灰莉(FAGRAEA BERTERROANA)花提取物");
						arrayList.add("柿（DIOSPYROS KAKI）叶提取物");
						arrayList.add("乌龙茶（CAMELLIA SINENSIS）叶提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("即是维生素E，其实是对两种化学物质:生育醇与三烯生育醇的总称。生育醇与三烯生育醇都各有四种:alpha、 beta、gamma、以及delta，其间的差异是甲基在酚环上的数量与位置;三烯生育醇的差异则在其不饱和的侧链。三烯生育醇 (Tocotrienols)属于维他命E的一种。与维他命E相同，三烯生育醇也是相当强的抗氧化剂，能对抗脂质过氧化反应(有害脂肪的氧化)。");
						arrayList.add("茶叶的提取物为茶多酚，又名茶单宁、茶揉质,是一类存在于茶叶中的多经基酚性化合物。其主要成分为儿茶素(黄烷醇类)、黄酮及黄酮贰类、花青素及花白素类和酚酸及缩酚酸类等四类,是三十多种酚类物质组成的复合体。纯天然提取产物，具有健美肌肤、延缓皮肤衰老等功能，能够清除体内的各种自由基，达到抗氧化的效果，其中茶多酚具有收敛性，能够使蛋白质沉淀变性，故具有较强的抗菌作用。");
						arrayList.add("从芒果中提取而得，含有天然果酸成分，具有保湿效果，能够清除机体内的自由基，美化肌肤，延缓肌肤衰老。");
						arrayList.add("木瓜提取物中的木瓜蛋白酶通过对角质蛋白的水解作用，而起到对角化皮肤的逐渐软化和溶化，促进衰老皮肤细胞的去除，加速皮肤及肌体的新陈代谢，通过溶角质化作用，有清洁皮肤、嫩化肌肉、消除色斑、抗皱纹及抑制粉刺产生功效；木瓜蛋白酶的另一作用，是水解毛发角质蛋白，故可用作脱毛剂，对皮肤无副作用。");
						arrayList.add("椰子油是制造香皂用的基础油脂，在化妆品中也大量使用，对皮肤具有滋润、保湿的效果；椰子提取物对金属蛋白酶有很好的抑制作用，金属蛋白酶的活性可看作生物体老化的一个指标，因此该提取物可以用于抗衰老化妆品。椰子果提取物还具有清洁皮肤的作用，可用作起泡卸妆剂。能缓解红血丝，改善角质。");
						arrayList.add("对芳香化酶的活化作用很强，芳香化酶的活化与雌激素的水平呈正比关系，在局部涂敷有利于提高雌激素的含量，有丰乳的作用；对尿酸酶有良好的抑制作用，兼之具有较广谱的抗菌性，可应用于防狐臭；对自由基有强烈的消除作用，有抗衰抗氧化的作用；对荧光素酶有活化作用，说明具有抗炎性；对胆甾醇分泌有促进作用，可增加皮肤的柔润；对透明质酸分泌有促进作用，可用作保湿剂。");
						arrayList.add("猕猴桃提取物对胶原蛋白凝胶的收缩研究是对皮肤施用试验的模拟，数据显示猕猴桃提取物有良好的收缩作用，可用于紧肤化妆品；猕猴桃中含大量的果酸，果酸能抑制角质细胞内聚力以及黑色素，有效地祛除过度的角质层或淡化黑斑，在改善干性和油性肌肤组织上也有显著功效；同时能促进胆甾醇生物的合成，具有保湿功能；促进表皮细胞的增殖作用，也有消除自由基的作用，可用于抗衰化妆品。");
						arrayList.add("透明质酸钠属于肌肤调理剂，是一种酸性粘多糖，天然存在于角膜皮中，可吸收其本身重量1000倍水分，以达到通过保留皮肤水分、阻止水分经表皮流失，以及当皮肤受到损伤时的屏障修复，令皮肤使用后不会感到干燥，增加光泽，它可以改善皮肤营养代谢，使皮肤柔、光滑、去皱、增加弹性、防止衰老，在保湿的同时又是良好的透皮吸收促进剂。");
						arrayList.add("有良好的抗脂质过氧化作用，可以起到延缓衰老作用；对黄嘌呤氧化酶的抑制，说明具有抗炎和收敛的效果，对B-16黑色素细胞活性显示强烈的抑制，是很好的化妆品美白添加剂。");
						arrayList.add("抗酸茶是生长在炎热，干燥的严峻环境中的灌木。线状阿司巴拉妥提取物是从抗酸茶的全草中获取的保湿成分，同时能消除自由基，有抗氧化的功效。");
						arrayList.add("木犀科、素馨属攀援灌木，中药材中，解毒杀虫。花含挥发油，油中含乙酸苄酯、芳樟醇、茉莉酮、吲哚及邻按基苯甲酸甲酯等。对皮肤有轻微的刺激，能滋润清凉皮肤，抑菌，气味抑制，消除粉刺，能短时间使皮肤收敛毛孔。");
						arrayList.add("来源于欧洲赤松的芽部，在化妆品配方中具有调理肌肤的功效，让肌肤柔嫩细滑。");
						arrayList.add("国外又名诺丽果，茜草科巴戟天属植物，含有20%的多糖。其含有极强抗氧功效（多种黄酮类物质）,有抗免疫调节、降血糖、抗病毒、降血脂等作用.用于头发皮肤护理，亦可用于防晒。");
						arrayList.add("为唇形科植物藿香的地上部份。藿香含挥发油，有甲基胡椒酚，茴香脑，茴香醛)，柠檬烯，有良好的清凉感，用于皮肤护理。");
						arrayList.add("柏氏灰莉(FAGRAEA BERTERROANA)花提取物是一种皮肤调理剂。");
						arrayList.add("别名耳环果、羊奶果、甜棒槌、砂糖罐、桂香柳。以果、根、叶入药。叶含生物碱、黄酮甙、酚类、醣类、氨基酸、有机酸。有收敛止泻功效，利水通淋，散瘀消肿。可杀菌，滋润皮肤的效果。");
						arrayList.add("杀菌消炎、解毒防病，还有抗衰老等特殊功效。用于化妆品有保养滋润皮肤功效。");
						break;
					case 0://第二个标题
						arrayList.add("无文字介绍");
						break;
					case 1://第二个标题的内容
						arrayList.add("");
						break;
				}
				break;

			case 25:
				switch(i) {
					case 2://第一个标题
						arrayList.add("尿囊素");
						arrayList.add("洋蔷薇（ROSA CENTIFOLIA）花水");
						arrayList.add("山金车（ARNICA MONTANA）花提取物");
						arrayList.add("黄龙胆（GENTIANA LUTEA）根提取物");
						arrayList.add("欧蓍草（ACHILLEA MILLEFOLIUM）提取物");
						arrayList.add("中亚苦蒿（ARTEMISIA ABSINTHIUM）提取物");
						arrayList.add("柏氏灰莉(FAGRAEA BERTERROANA)花提取物");
						arrayList.add("海巴戟（MORINDA CITRIFOLIA）叶提取物");
						arrayList.add("藿香蓟（AGERATUM CONYZOIDES）叶提取物");
						arrayList.add("亚洲白桦（BETULA PLATYPHYLLA）树汁");
						arrayList.add("银耳（TREMELLA FUCIFORMIS）子实体提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("尿囊素是一种无毒、无味、无刺激性、无过敏性的白色粉体，主要做皮肤调理剂、防护剂使用，能够帮助皮肤抗炎、舒缓并促进细胞修复。尿囊素可降低角质层细胞的粘着力，加速表皮细胞更新。一些含有果酸、维生素A具有刺激性的化妆品或消炎镇静的晒后修护产品都会添加尿囊素增强其修复能力。但是尿囊素添加剂量在0.1%—0.2%左右，添加过量亦会引发刺激反应。但尿囊素总体来看是抗敏成分，安全度高。");
						arrayList.add("蔷薇花挥发油可用于抵挡香精的调配。对非雄性激素过高而引起的脱发有防治作用；对B-16细胞活性的促进作用，可增加黑色素的分泌，生发的同时，防治白发出现；对血管内皮细胞呈增殖活性，可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可用于抗衰老化妆品。 ");
						arrayList.add("对组织蛋白酶活性的促进作用和对纤维芽细胞的增殖说明，可增强细胞新陈代谢，结合它的抗氧化性，有活肤抗衰作用；对荧光素酶活性的抑制说明有抗炎作用；同时可用作抑臭剂、美白剂。含有金山花提取物的化妆品不可用于伤损皮肤，若出现皮肤过敏的现象，应立即停止使用。");
						arrayList.add("对荧光素酶的有很好的活化效果，说明对特异性皮炎和过敏有作用，可抑制刺激，起到抗炎的作用；对芳香化酶的活化，显示对皮肤局部的雌激素水平有作用，加之对弹性蛋白酶的抑制和抗氧性，具有抗衰老的作用；对表皮细胞胆甾醇的分泌有促进作用，胆甾醇是人体皮脂中一重要的柔润成分，可改善油性粗糙皮肤的状态；也用作生发剂、美白剂和保湿剂。");
						arrayList.add("来源于欧蓍草的纯天然萃取产物，具有抑菌功效，能用于护发产品中，为头皮解决头屑困扰。同时因其清香气味可作气味抑制剂改善产品的感官品质，促进发汗，将毒素排出体外，改善肌肤健康状态，让肌肤呈现柔润净透态。");
						arrayList.add("中亚苦蒿为菊科蒿属的植物。是酿造苦艾酒的原料之一。全草入药，有消炎、健胃、驱虫之效。能有效抑菌，消除粉刺。");
						arrayList.add("柏氏灰莉(FAGRAEA BERTERROANA)花提取物是一种皮肤调理剂。");
						arrayList.add("国外又名诺丽果，茜草科巴戟天属植物，含有20%的多糖。其含有极强抗氧功效（多种黄酮类物质）,有抗免疫调节、降血糖、抗病毒、降血脂等作用.用于头发皮肤护理，亦可用于防晒。");
						arrayList.add("为唇形科植物藿香的地上部份。藿香含挥发油，有甲基胡椒酚，茴香脑，茴香醛)，柠檬烯，有良好的清凉感，用于皮肤护理。");
						arrayList.add("可抑制组胺的释放，有抗敏的作用；对弹性蛋白酶和金属蛋白酶有抑制作用，表明其抗衰的作用较强；与其他成分配合用于调理皮肤、柔滑皮肤、预防皲裂、改善肤色等护肤品。");
						arrayList.add("来源于银耳子实体，是一种物源性的类透明质酸，从银耳中提取出来的水溶性高分子异多糖具有超越透明质酸的保湿性能，能保持490倍的水，极为优异的成膜性及无比滑爽的感觉，极佳的护肤效果。增强细胞免疫功能，清除自由基抗氧化、抗衰老、补充营养，增加皮肤弹性，减小皮肤纹理。");
						break;
					case 0://第二个标题
						arrayList.add("无文字介绍");
						break;
					case 1://第二个标题的内容
						arrayList.add("");
						break;
				}
				break;

			case 26:
				switch(i) {
					case 2://第一个标题
						arrayList.add("亚洲白桦");
						arrayList.add("GIGAWhite ");
						arrayList.add("透明质酸钠");
						arrayList.add("热带果实复合提取物");
						arrayList.add("茶叶提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("维持NMF和角质层的水分平衡，防止质层硬化，提高皮肤对环境的适应性，具有保湿和抗炎效果。");
						arrayList.add("7种阿尔卑斯高海拔的花卉提取物，舒缓镇静肌肤，增强皮肤的弹性和柔软性。");
						arrayList.add("赋予皮肤光滑感和柔软性，保存水分，抑制皮肤老化。");
						arrayList.add("有效预防老化，维持肌肤原有弹性，令肤质柔皙亮泽。");
						arrayList.add("抑制酪氨酸酶活性，减少黑色素生成，吸收紫外线保护皮肤，有一定的抗菌效果。");
						break;
					case 0://第二个标题
						arrayList.add("无文字介绍");
						break;
					case 1://第二个标题的内容
						arrayList.add("");
						break;
				}
				break;

			case 27:
				switch(i) {
					case 2://第一个标题
						arrayList.add("母菊（CHAMOMILLA RECUTITA）花提取物");
						arrayList.add("线状阿司巴拉妥（ASPALATHUS LINEARIS）提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("母菊花，即洋甘菊，其含挥发油、黄酮类化合物、氨基酸、绿原酸和微量元素等。基于菊花提取物具有抗菌、抗氧化、抗炎、舒缓抗敏等生物活性，广泛应用于化妆品中，可起到预防过敏、保湿、防治粉刺和抗衰老等功效。");
						arrayList.add("抗酸茶是生长在炎热，干燥的严峻环境中的灌木。线状阿司巴拉妥提取物是从抗酸茶的全草中获取的保湿成分，同时能消除自由基，有抗氧化的功效。");
						break;
					case 0://第二个标题
						arrayList.add("无文字介绍");
						break;
					case 1://第二个标题的内容
						arrayList.add("");
						break;
				}
				break;

			case 28:
				switch(i) {
					case 2://第一个标题
						arrayList.add("海藻糖");
						arrayList.add("白花百合（LILIUM CANDIDUM）花提取物");
						arrayList.add("变色鸢尾（IRIS VERSICOLOR）提取物");
						arrayList.add("高山火绒草（LEONTOPODIUM ALPINUM）花/叶提取物");
						arrayList.add("莲（NELUMBO NUCIFERA）花提取物");
						arrayList.add("蔷薇（ROSA SPP.）提取物");
						arrayList.add("素方花（JASMINUM OFFICINALE）花/叶提取物");
						arrayList.add("小苍兰（FREESIA REFRACTA）提取物");
						arrayList.add("茶（CAMELLIA SINENSIS）叶提取物");
						arrayList.add("透明质酸钠");
						arrayList.add("香蜂花（MELISSA OFFICINALIS）叶提取物");
						arrayList.add("欧锦葵（MALVA SYLVESTRIS）提取物");
						arrayList.add("欧蓍草（ACHILLEA MILLEFOLIUM）提取物");
						arrayList.add("羽衣草（ALCHEMILLA VULGARIS）提取物");
						arrayList.add("黄花九轮草（PRIMULA VERIS）提取物");
						arrayList.add("辣薄荷（MENTHA PIPERITA）叶提取物");
						arrayList.add("药用婆婆纳（VERONICA OFFICINALIS）提取物");
						arrayList.add("大果越桔（VACCINIUM MACROCARPON）果提取物");
						arrayList.add("草莓（FRAGARIA CHILOENSIS）果提取物");
						arrayList.add("覆盆子（RUBUS IDAEUS）果提取物");
						arrayList.add("狭叶越桔（VACCINIUM ANGUSTIFOLIUM）果提取物");
						arrayList.add("黑莓（RUBUS FRUTICOSUS）果提取物");
						arrayList.add("野蔷薇（ROSA MULTIFLORA）果提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("海藻糖是一种天然糖类，属于小分子保湿剂，与膜蛋白有很好的亲和性，可用作皮肤渗透剂增加皮肤对营养成分的吸收。增加细胞的水化功能，有良好的保湿性，在治疗由于皮肤干燥引起的皮屑增多、燥热、角质硬化有特效。");
						arrayList.add("对纤维芽细胞有增殖、组织单倍酶活性促进等作用显示，可增强皮肤细胞新陈代谢，有抗衰功能；对荧光素酶活性有促进作用，说明有抗炎作用。");
						arrayList.add("在化妆品中不仅可做高档化妆品的香精使用，同时其他组分亦可使皮肤得到改善。对组织蛋白酶活性的促进和对胰蛋白酶的抑制作用显示，可增强皮肤细胞的新陈代谢，有抗衰作用；同时还有抗炎、抑制毛发生长和美白的作用。");
						arrayList.add("对弹性蛋白酶活性的抑制和对内皮蛋白生成的促进作用显示，可减缓弹性蛋白的降解，可维持皮肤弹性，可用于抗皱化妆品；对一些细菌有一定的抑制作用，具有抗菌消炎的作用。");
						arrayList.add("对组成蛋白纤维的胶原蛋白、弹性蛋白的生成均有刺激作用，显示其增强皮肤活性的能力，结合抗氧化性，可用于抗衰防皱化妆品；对脂肪分解有强烈的促进作用，可用于减肥化妆品。");
						arrayList.add("蔷薇花挥发油可用于抵挡香精的调配。对非雄性激素过高而引起的脱发有防治作用；对B-16细胞活性的促进作用，可增加黑色素的分泌，生发的同时，防治白发出现；对血管内皮细胞呈增殖活性，可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可用于抗衰老化妆品。");
						arrayList.add("木犀科、素馨属攀援灌木，中药材中，解毒杀虫。花含挥发油，油中含乙酸苄酯、芳樟醇、茉莉酮、吲哚及邻按基苯甲酸甲酯等。对皮肤有轻微的刺激，能滋润清凉皮肤，抑菌，气味抑制，消除粉刺，能短时间使皮肤收敛毛孔。");
						arrayList.add("有甜美香味，使人心情舒畅。可以温和去除皮肤污垢滋润皮肤，使肌肤光洁靓丽。");
						arrayList.add("茶叶的提取物为茶多酚，又名茶单宁、茶揉质,是一类存在于茶叶中的多经基酚性化合物。其主要成分为儿茶素(黄烷醇类)、黄酮及黄酮贰类、花青素及花白素类和酚酸及缩酚酸类等四类,是三十多种酚类物质组成的复合体。纯天然提取产物，具有健美肌肤、延缓皮肤衰老等功能，能够清除体内的各种自由基，达到抗氧化的效果，其中茶多酚具有收敛性，能够使蛋白质沉淀变性，故具有较强的抗菌作用。");
						arrayList.add("茶叶的提取物为茶多酚，又名茶单宁、茶揉质,是一类存在于茶叶中的多经基酚性化合物。其主要成分为儿茶素(黄烷醇类)、黄酮及黄酮贰类、花青素及花白素类和酚酸及缩酚酸类等四类,是三十多种酚类物质组成的复合体。纯天然提取产物，具有健美肌肤、延缓皮肤衰老等功能，能够清除体内的各种自由基，达到抗氧化的效果，其中茶多酚具有收敛性，能够使蛋白质沉淀变性，故具有较强的抗菌作用。");
						arrayList.add("透明质酸钠属于肌肤调理剂，是一种酸性粘多糖，天然存在于角膜皮中，可吸收其本身重量1000倍水分，以达到通过保留皮肤水分、阻止水分经表皮流失，以及当皮肤受到损伤时的屏障修复，令皮肤使用后不会感到干燥，增加光泽，它可以改善皮肤营养代谢，使皮肤柔、光滑、去皱、增加弹性、防止衰老，在保湿的同时又是良好的透皮吸收促进剂。与其他营养成分配合使用，可以起到促进营养吸收的更理想效果。");
						arrayList.add("有效净化和去除日常污垢、汗液和残留油脂，清洁度高。");
						arrayList.add("源于欧锦葵，具有平衡肌肤油脂分泌，收敛毛孔，柔润肌肤和改善使用肤感等多重功效，显著提升水通道蛋白作用，赋活肌肤，展现透亮肌肤，从内部激活肌肤能量。");
						arrayList.add("来源于欧蓍草的纯天然萃取产物，具有抑菌功效，能用于护发产品中，为头皮解决头屑困扰。同时因其清香气味可作气味抑制剂改善产品的感官品质，促进发汗，将毒素排出体外，改善肌肤健康状态，让肌肤呈现柔润净透态。");
						arrayList.add("羽衣草提取物有广谱的自由基消除功能，可用作理想的化妆品抗氧化剂；对弹性蛋白酶具有较好的抑制性，可用作抗衰和抗皱化妆品中；对脂氧化酶-5活性有抑制，显示具有抗炎性，与羽衣草提取物的对浮肿抑制试验一致，可用作化妆品的抗炎剂。");
						arrayList.add("黄花九轮草是多年生草本植物，花冠黄色，含有维生素，醇类好部分脂类。用于皮肤调理。");
						arrayList.add("从辣薄荷叶中制得，富含薄荷叶黄烷酮，对敏感肌肤可以缓解皮肤过敏症状，具有消炎、镇痛和舒缓作用。可调节肌肤微循环抑制炎症症状，使肌肤感觉清新柔和。");
						arrayList.add("由药用婆婆纳中提取所得，具有镇静收敛肌肤的功效，同时能安抚受刺激的皮肤，治疗受损肌肤，减少皮肤发红症状。");
						arrayList.add("是天然植物提取制得，对人体无毒副作用，可以深层滋养紧致皮肤，平衡脸部多余的油脂，使其长时间保持清爽；同时亦可收细毛孔，令肌肤平滑细嫩。");
						arrayList.add("从草莓中提取，具有草莓特有的清香和营养滋润因子，可以加倍润泽肌肤，促进肌肤收敛，令肌肤红润、细嫩，缔造健康水润细滑的肌肤。");
						arrayList.add("对皮肤具有很高的渗透性，可以保持皮肤表面的水分，促进皮肤新陈代谢，抑制皮肤炎症，辅展性好，滑爽不黏。其作用功效体现在燃烧脂肪，养颜两方面。");
						arrayList.add("集防护和修护双重功能，增加肌肤营养，恢复护肤弹性。");
						arrayList.add("黑莓鲜果中糖、维生素C、 维生素B1.维生素B2的含量与其它果树相当，有机酸、粗蛋白、维生素K和氨基酸高。氨基酸共有18种，8种必需氨基酸都有，且含量较高，还富含γ-氨基丁酸。对人体至关重要的6种矿质元素（钾、钙、钠、镁、锌、铁）含量也较高。维生素E保护细胞和细胞内部结构的完整，防止某些酶和细胞内部成分遭到破坏，延缓衰老；硒能抗氧化、防衰老，提高免疫力，与维生素E共同起拮抗有毒物质的保护剂作用，用于皮肤的综合调理。");
						arrayList.add("蔷薇花挥发油可用于抵挡香精的调配。对非雄性激素过高而引起的脱发有防治作用；对B-16细胞活性的促进作用，可增加黑色素的分泌，生发的同时，防治白发出现；对血管内皮细胞呈增殖活性，可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可用于抗衰老化妆品。");
						break;
					case 0://第二个标题
						arrayList.add("无文字介绍");
						break;
					case 1://第二个标题的内容
						arrayList.add("");
						break;
				}
				break;

			case 29:
				switch(i) {
					case 2://第一个标题
						arrayList.add("甘草酸二钾");
						arrayList.add("茶（CAMELLIA SINENSIS）叶提取物");
						arrayList.add("洋蔷薇（ROSA CENTIFOLIA）花水");
						break;
					case 3://第一个标题的内容
						arrayList.add("甘草酸二钾是作为皮肤调理剂使用，主要为甘草根部及茎部的甘草甜素（甘草酸）成分适合过敏性肌肤，抗刺激及控油，退红肿消炎愈合作用。可有效预防皮肤受刺激时敏感发炎现象。对日照引起的炎症具有消炎镇静作用，常被用于抗过敏及修复化妆品中。");
						arrayList.add("茶叶的提取物为茶多酚，又名茶单宁、茶揉质,是一类存在于茶叶中的多经基酚性化合物。其主要成分为儿茶素(黄烷醇类)、黄酮及黄酮贰类、花青素及花白素类和酚酸及缩酚酸类等四类,是三十多种酚类物质组成的复合体。纯天然提取产物，具有健美肌肤、延缓皮肤衰老等功能，能够清除体内的各种自由基，达到抗氧化的效果，其中茶多酚具有收敛性，能够使蛋白质沉淀变性，故具有较强的抗菌作用。");
						arrayList.add("蔷薇花挥发油可用于抵挡香精的调配。对非雄性激素过高而引起的脱发有防治作用；对B-16细胞活性的促进作用，可增加黑色素的分泌，生发的同时，防治白发出现；对血管内皮细胞呈增殖活性，可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可用于抗衰老化妆品。");
						break;
					case 0://第二个标题
						arrayList.add("无文字介绍");
						break;
					case 1://第二个标题的内容
						arrayList.add("");
						break;
				}
				break;


			//水漾清透系列
			case 30:
				switch(i) {
					case 2://第一个标题
						arrayList.add("线状阿司巴拉妥（ASPALATHUS LINEARIS）提取物");
						arrayList.add("蔷薇（ROSA CANINA）果提取物");
						arrayList.add("姜（ZINGIBER OFFICINALE）根提取物");
						arrayList.add("生育酚（维生素E）");
						break;
					case 3://第一个标题的内容
						arrayList.add("抗酸茶是生长在炎热，干燥的严峻环境中的灌木。线状阿司巴拉妥提取物是从抗酸茶的全草中获取的保湿成分，同时能消除自由基，有抗氧化的功效。");
						arrayList.add("可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可抗衰老。");
						arrayList.add("可增强皮肤的活性，活化肌肤，具有抗氧化，抗衰的作用；同时具有保湿和抑制臭味的作用。");
						arrayList.add("相当强的抗氧化剂，能对抗脂质过氧化反应(有害脂肪的氧化)。");
						break;
					case 0://第二个标题
						arrayList.add("无文字介绍");
						break;
					case 1://第二个标题的内容
						arrayList.add("");
						break;
				}
				break;

			case 31:
				switch(i) {
					case 2://第一个标题
						arrayList.add("亚洲白桦（BETULA PLATYPHYLLA）树汁");
						arrayList.add("透明质酸");
						arrayList.add("中亚苦蒿（ARTEMISIA ABSINTHIUM）提取物");
						arrayList.add("芒果（MANGIFERA INDICA）籽脂");
						break;
					case 3://第一个标题的内容
						arrayList.add("可抑制组胺的释放，有抗敏的作用；对弹性蛋白酶和金属蛋白酶有抑制作用，表明其抗衰的作用较强；与其他成分配合用于调理皮肤、柔滑皮肤、预防皲裂、改善肤色等。");
						arrayList.add("透明质酸钠属于肌肤调理剂，天然存在于角膜皮中，可吸收其本身重量1000倍水分，以达到通过保留皮肤水分、阻止水分经表皮流失，以及当皮肤受到损伤时的屏障修复，令皮肤使用后不会感到干燥，高效抓水，使皮肤柔润有光泽，是目前最佳的水溶性保湿剂。");
						arrayList.add("中亚苦蒿为菊科蒿属的植物。是酿造苦艾酒的原料之一。全草入药，有消炎、健胃、驱虫之效。能有效抑菌，消除粉刺。");
						arrayList.add("取自芒果果核的黄色油脂，芒果脂是很好的皮肤软化剂，具有非常好的保湿效果，能保护皮肤不受到日晒的伤害，能防止皮肤干燥与出现皱纹，减缓皮肤组织老化，恢复弹性。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						arrayList.add("Step5 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("在早晚洁肤后，用无名指取绿豆大小的眼霜，两个无名指指腹相互揉搓，给眼霜加温，使之更容易被肌肤吸收。");
						arrayList.add("以弹钢琴的方式，均匀地轻轻将眼霜拍打在眼周肌肤上。着重在下眼窝和眼尾至太阳穴的延伸部位多加涂抹。");
						arrayList.add("先从眼部下方，由睛明穴向眼尾轻轻按压。然后从眼部上方，由内向外轻轻按压。");
						arrayList.add("用中指指腹从眉头下方开始，轻轻按压。再沿着眼眶，由内向外轻轻按压。");
						arrayList.add("用中指指尖，轻轻按压鼻翼两旁的迎香穴，促进眼部肌肤的血液循环。");
						break;
				}
				break;

			case 32:
				switch(i) {
					case 2://第一个标题
						arrayList.add("亚洲白桦（BETULA PLATYPHYLLA）树汁");
						arrayList.add("透明质酸");
						arrayList.add("山金车（ARNICA MONTANA）花提取物");
						arrayList.add("中亚苦蒿（ARTEMISIA ABSINTHIUM）提取物");
						arrayList.add("芒果（MANGIFERA INDICA）籽脂");
						break;
					case 3://第一个标题的内容
						arrayList.add("可抑制组胺的释放，有抗敏的作用；对弹性蛋白酶和金属蛋白酶有抑制作用，表明其抗衰的作用较强；与其他成分配合用于调理皮肤、柔滑皮肤、预防皲裂、改善肤色等。");
						arrayList.add("透明质酸钠属于肌肤调理剂，天然存在于角膜皮中，可吸收其本身重量1000倍水分，以达到通过保留皮肤水分、阻止水分经表皮流失，以及当皮肤受到损伤时的屏障修复，令皮肤使用后不会感到干燥，高效抓水，使皮肤柔润有光泽，是目前最佳的水溶性保湿剂。");
						arrayList.add("对组织蛋白酶活性的促进作用和对纤维芽细胞的增殖说明，可增强细胞新陈代谢，结合它的抗氧化性，有活肤抗衰作用。");
						arrayList.add("中亚苦蒿为菊科蒿属的植物。是酿造苦艾酒的原料之一。全草入药，有消炎、健胃、驱虫之效。能有效抑菌，消除粉刺。");
						arrayList.add("取自芒果果核的黄色油脂，芒果脂是很好的皮肤软化剂，具有非常好的保湿效果，能保护皮肤不受到日晒的伤害，能防止皮肤干燥与出现皱纹，减缓皮肤组织老化，恢复弹性。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						arrayList.add("Step5 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("在早晚洁肤后，用无名指取绿豆大小的眼啫喱，两个无名指指腹相互揉搓，给眼啫喱加温，使之更容易被肌肤吸收。");
						arrayList.add("弹钢琴的方式，均匀地轻轻将眼啫喱拍打在眼周肌肤上。着重在下眼窝和眼尾至太阳穴的延伸部位多加涂抹。");
						arrayList.add("先从眼部下方，由睛明穴向眼尾轻轻按压。然后从眼部上方，由内向外轻轻按压。");
						arrayList.add("用中指指腹从眉头下方开始，轻轻按压。再沿着眼眶，由内向外轻轻按压。");
						arrayList.add("用中指指尖，轻轻按压鼻翼两旁的迎香穴，促进眼部肌肤的血液循环。");
						break;
				}
				break;

			case 33:
				switch(i) {
					case 2://第一个标题
						arrayList.add("亚洲白桦（BETULA PLATYPHYLLA）树汁");
						arrayList.add("雪莲花（SAUSSUREA INVOLUCRATA）提取物");
						arrayList.add("茶（CAMELLIA SINENSIS）叶提取物");
						arrayList.add("药鼠尾草（SALVIA OFFICINALIS）叶提取物");
						arrayList.add("马鞭草（VERBENA OFFICINALIS）提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("可抑制组胺的释放，有抗敏的作用；对弹性蛋白酶和金属蛋白酶有抑制作用，表明其抗衰的作用较强；与其他成分配合用于调理皮肤、柔滑皮肤、预防皲裂、改善肤色等。");
						arrayList.add("从雪莲花中提取的纯天然产物，能够清除体内自由基，有效延缓肌肤衰老，同时可以帮助肌肤吸收和锁住水分，长效保湿，避免肌肤干燥粗糙。");
						arrayList.add("茶叶的提取物为茶多酚，又名茶单宁、茶揉质,是一类存在于茶叶中的多经基酚性化合物。其纯天然提取产物，具有健美肌肤、延缓皮肤衰老等功能，能够清除体内的各种自由基，达到抗氧化的效果，其中茶多酚具有收敛性，能够使蛋白质沉淀变性，故具有较强的抗菌作用。");
						arrayList.add("具有镇静和舒缓双重功能。具有抗炎性；减少油脂的生成，另外在脂肪水解试验中，可促进油脂的水解，结合对胶原脂肪团的收缩作用，可紧致肌肤；可减少皮层厚度，有防晒效果。");
						arrayList.add("马鞭草提取物中含有鞣质、马鞭草甙等成分，可以软化肌肤角质，令肌肤柔嫩光滑。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量产品放入掌心，加入少量水。");
						arrayList.add("使用另一支手的食指和中指，快速摩擦掌心的洁面膏体，使膏体与水充分混合，打出大量泡沫。");
						arrayList.add("将泡沫涂在脸上，让泡沫遍及整个面部，然后轻轻打圈按摩半分钟左右，注意手法要轻，避免手掌和面部摩擦产生皱纹。");
						arrayList.add("使用温水清洁面部泡沫，以脸部肌肤不涩，不滑腻，自然舒适即可。");
						break;
				}
				break;

			//新水漾系列
			case 34:
				switch(i) {
					case 2://第一个标题
						arrayList.add("线状阿司巴拉妥（ASPALATHUS LINEARIS）提取物");
						arrayList.add("蔷薇（ROSA CANINA）果提取物");
						arrayList.add("药鼠尾草（SALVIA OFFICINALIS）叶提取物");
						arrayList.add("马鞭草（VERBENA OFFICINALIS）提取物");
						arrayList.add("翼籽辣木（MORINGA PTERYGOSPERMA）籽提取物");
						arrayList.add("姜（ZINGIBER OFFICINALE）根提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("抗酸茶是生长在炎热，干燥的严峻环境中的灌木。线状阿司巴拉妥提取物是从抗酸茶的全草中获取的保湿成分，同时能消除自由基，有抗氧化的功效。");
						arrayList.add("可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可抗衰老。");
						arrayList.add("具有镇静和舒缓双重功能。具有抗炎性；减少油脂的生成，另外在脂肪水解试验中，可促进油脂的水解，结合对胶原脂肪团的收缩作用，可紧致肌肤；可减少皮层厚度，说明有防晒效果。");
						arrayList.add("来源于马鞭草，属于非禁限性组分，对人体无毒害作用，提取物中含有鞣质、马鞭草甙等成分，可以软化肌肤角质，令肌肤柔嫩光滑。");
						arrayList.add("对自由基有消除作用，可用作抗氧化剂，具有抗衰的功能；同时能补充水分，在化妆品中作保湿剂。");
						arrayList.add("对皮肤癣菌、白色链珠菌和口腔病菌有一定的抑制作用，但对常见的霉菌等的抑制效果不佳；可增强皮肤的活性，活化肌肤，具有抗氧化，抗衰的作用；还能促进头发的生长，作头发促进剂；同时具有保湿和抑制臭味的作用。浓度稍大的姜提取物外用时有刺激，对损伤型皮肤刺激更大。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量产品放入掌心，加入少量水。");
						arrayList.add("使用另一支手的食指和中指，快速摩擦掌心的洁面膏体，使膏体与水充分混合，打出大量泡沫。");
						arrayList.add("将泡沫涂在脸上，让泡沫遍及整个面部，然后轻轻打圈按摩半分钟左右，注意手法要轻，避免手掌和面部摩擦产生皱纹。");
						arrayList.add("使用温水清洁面部泡沫，以脸部肌肤不涩，不滑腻，自然舒适即可。");
						break;
				}
				break;

			case 35:
				switch(i) {
					case 2://第一个标题
						arrayList.add("马鞭草（VERBENA OFFICINALIS）提取物");
						arrayList.add("药鼠尾草（SALVIA OFFICINALIS）叶提取物");
						arrayList.add("线状阿司巴拉妥（ASPALATHUS LINEARIS）提取物");
						arrayList.add("蔷薇（ROSA CANINA）果提取物");
						arrayList.add("甘草酸二钾");
						arrayList.add("透明质酸钠");
						arrayList.add("翼籽辣木（MORINGA PTERYGOSPERMA）籽提取物");
						arrayList.add("姜（ZINGIBER OFFICINALE）根提取物");
						break;
					case 3://第一个标题的内容
						arrayList.add("马鞭草提取物中含有鞣质、马鞭草甙等成分，可以软化肌肤角质，令肌肤柔嫩光滑。");
						arrayList.add("具有镇静和舒缓双重功能。具有抗炎性；减少油脂的生成，另外在脂肪水解试验中，可促进油脂的水解，结合对胶原脂肪团的收缩作用，可紧致肌肤；可减少皮层厚度，说明有防晒效果。");
						arrayList.add("抗酸茶是生长在炎热，干燥的严峻环境中的灌木。线状阿司巴拉妥提取物是从抗酸茶的全草中获取的保湿成分，同时能消除自由基，有抗氧化的功效。");
						arrayList.add("可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可抗衰老。");
						arrayList.add("甘草酸二钾是作为皮肤调理剂使用，主要为甘草根部及茎部的甘草甜素（甘草酸）成分适合过敏性肌肤，抗刺激及控油，退红肿消炎愈合作用。可有效预防皮肤受刺激时敏感发炎现象。对日照引起的炎症具有消炎镇静作用，常被用于抗过敏及修复化妆品中。");
						arrayList.add("透明质酸钠属于肌肤调理剂，是一种酸性粘多糖，天然存在于角膜皮中，可吸收其本身重量1000倍水分，以达到通过保留皮肤水分、阻止水分经表皮流失，以及当皮肤受到损伤时的屏障修复，令皮肤使用后不会感到干燥，增加光泽，它可以改善皮肤营养代谢，使皮肤柔、光滑、去皱、增加弹性、防止衰老，在保湿的同时又是良好的透皮吸收促进剂。与其他营养成分配合使用，可以起到促进营养吸收的更理想效果。");
						arrayList.add("对自由基有消除作用，可用作抗氧化剂，具有抗衰的功能；同时能补充水分，在化妆品中作保湿剂。");
						arrayList.add("对皮肤癣菌、白色链珠菌和口腔病菌有一定的抑制作用，但对常见的霉菌等的抑制效果不佳；可增强皮肤的活性，活化肌肤，具有抗氧化，抗衰的作用；还能促进头发的生长，作头发促进剂；同时具有保湿和抑制臭味的作用。浓度稍大的姜提取物外用时有刺激，对损伤型皮肤刺激更大。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("使用化妆棉蘸取亮肤水自下而上轻抚脸部，补水的同时还可以起到二次清洁作用。");
						arrayList.add("待产品吸收后，将亮肤水倒在掌心，待掌心温度使之有温感之后再均匀轻拍于脸部肌肤，进行二次补水。");
						break;
				}
				break;

			case 36:
				switch(i) {
					case 2://第一个标题
						arrayList.add("线状阿司巴拉妥（ASPALATHUS LINEARIS）提取物");
						arrayList.add("蔷薇（ROSA CANINA）果提取物");
						arrayList.add("药鼠尾草（SALVIA OFFICINALIS）叶提取物");
						arrayList.add("生育酚（维生素E）");
						break;
					case 3://第一个标题的内容
						arrayList.add("抗酸茶是生长在炎热，干燥的严峻环境中的灌木。线状阿司巴拉妥提取物是从抗酸茶的全草中获取的保湿成分，同时能消除自由基，有抗氧化的功效。");
						arrayList.add("可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可抗衰老。");
						arrayList.add("具有镇静和舒缓双重功能。具有抗炎性；减少油脂的生成，另外在脂肪水解试验中，可促进油脂的水解，结合对胶原脂肪团的收缩作用，可紧致肌肤；可减少皮层厚度，说明有防晒效果。");
						arrayList.add("即是维生素E，其实是对两种化学物质:生育醇与三烯生育醇的总称，这两种化学物质在体内的生物作用大致相同，只是活性不同而已。生育醇与三烯生育醇都各有四种:alpha、 beta、gamma、以及delta，其间的差异是甲基在酚环上的数量与位置;三烯生育醇的差异则在其不饱和的侧链。三烯生育醇 (Tocotrienols)属于维他命E的一种。与维他命E相同，三烯生育醇也是相当强的抗氧化剂，能对抗脂质过氧化反应(有害脂肪的氧化)。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量乳液点涂以下部位：两颊、嘴角两侧、上下颚。");
						arrayList.add("再取适量点涂于T区、两颊，然后均匀涂抹于面部，可以使干燥的部位得到双重滋润。");
						arrayList.add("最后取适量乳液涂抹于颈部。抬起下巴，由下至上轻轻提拉按摩，直至面霜完全吸收。");
						break;
				}
				break;

			case 37:
				switch(i) {
					case 2://第一个标题
						arrayList.add("线状阿司巴拉妥（ASPALATHUS LINEARIS）提取物");
						arrayList.add("蔷薇（ROSA CANINA）果提取物");
						arrayList.add("姜（ZINGIBER OFFICINALE）根提取物");
						arrayList.add("角鲨烷");
						break;
					case 3://第一个标题的内容
						arrayList.add("抗酸茶是生长在炎热，干燥的严峻环境中的灌木。线状阿司巴拉妥提取物是从抗酸茶的全草中获取的保湿成分，同时能消除自由基，有抗氧化的功效。");
						arrayList.add("可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可抗衰老。");
						arrayList.add("可增强皮肤的活性，活化肌肤，具有抗氧化，抗衰的作用；同时具有保湿和抑制臭味的作用。");
						arrayList.add("角鲨烷提取自深海鲨鱼肝脏或橄榄油中，是皮肤一种天然成分，具有较好抗氧化力和刺激免疫力，能快速地与肌肤内的水分和油脂相溶，形成天然的皮肤保护屏障，稳定性极佳，不会与空气、紫外线等发生氧化作用，能有效保护皮肤不受外界物质的侵害，极佳的渗透力，高效的携氧性，能调整肌肤的水油平衡，避免肌肤粗糙、暗沉，恢复肌肤原本的柔嫩触感。尤其适合在寒冷季节中使用，是肌肤的润滑油，能补充皮肤天然皮脂。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("额头 :");
						arrayList.add("I区 :");
						arrayList.add("两颊 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取黄豆粒大小的精华，轻柔、均匀的涂抹于面部。");
						arrayList.add("使用美容指按摩。将面部分成额头、I区、两颊三部分，分别按摩。\n" +
								"（美容指：中指和无名指）");
						arrayList.add("由眉心向发际螺旋状按摩。");
						arrayList.add("鼻梁至下巴，由上往下打圈按摩。");
						arrayList.add("以鼻翼处为出发点至耳际方向，由内到外轻轻打圈按摩再提拉。");
						break;
				}
				break;

			case 38:
				switch(i) {
					case 2://第一个标题
						arrayList.add("线状阿司巴拉妥（ASPALATHUS LINEARIS）提取物");
						arrayList.add("蔷薇（ROSA CANINA）果提取物");
						arrayList.add("姜（ZINGIBER OFFICINALE）根提取物");
						arrayList.add("角鲨烷");
						break;
					case 3://第一个标题的内容
						arrayList.add("抗酸茶是生长在炎热，干燥的严峻环境中的灌木。线状阿司巴拉妥提取物是从抗酸茶的全草中获取的保湿成分，同时能消除自由基，有抗氧化的功效。");
						arrayList.add("可增强血管的强度，可预防皮肤红血丝；对脑酰胺生成的促进作用表明可增加皮脂中脑酰胺的含量，起柔肤作用；对弹性蛋白酶的抑制，反映抗皱的性能，结合具有DOD样活性，可抗衰老。");
						arrayList.add("可增强皮肤的活性，活化肌肤，具有抗氧化，抗衰的作用；同时具有保湿和抑制臭味的作用。");
						arrayList.add("角鲨烷提取自深海鲨鱼肝脏或橄榄油中，是皮肤一种天然成分，具有较好抗氧化力和刺激免疫力，能快速地与肌肤内的水分和油脂相溶，形成天然的皮肤保护屏障，稳定性极佳，不会与空气、紫外线等发生氧化作用，能有效保护皮肤不受外界物质的侵害，极佳的渗透力，高效的携氧性，能调整肌肤的水油平衡，避免肌肤粗糙、暗沉，恢复肌肤原本的柔嫩触感。尤其适合在寒冷季节中使用，是肌肤的润滑油，能补充皮肤天然皮脂。");
						break;
					case 0://第二个标题
						arrayList.add("无文字介绍");
						break;
					case 1://第二个标题的内容
						arrayList.add("");
						break;
				}
				break;

			//清透肌系列
			case 39:
				switch(i) {
					case 2://第一个标题
						arrayList.add("茵陈蒿提取液");
						arrayList.add("黄柏树皮提取液");
						arrayList.add("甘草酸二钾");
						arrayList.add("野蔷薇果提取液");
						arrayList.add("北美金缕梅提取液");
						break;
					case 3://第一个标题的内容
						arrayList.add("具有修复细胞、抗过敏、抗菌作用，防止皮肤粗糙。");
						arrayList.add("具有消炎、收敛、抗菌作用，有紧致肌肤的功效。");
						arrayList.add("具有较高的消炎作用，还具有解毒、抗过敏、抗溃疡的作用。能有效预防皮肤发炎和生成粉刺。（排毒）");
						arrayList.add("含有类黄酮，具有收敛、美白、抗氧化、抗炎症等作用。（收缩毛孔、抗衰老）");
						arrayList.add("具有显著的收敛、消炎效果。（保湿）");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("使用化妆棉蘸取爽肤水自下而上轻抚脸部，补水的同时还可以起到二次清洁作用。");
						arrayList.add("待产品吸收后，将化妆水倒在掌心，待掌心温度使之有温感之后再均匀轻拍于脸部肌肤，进行二次补水。");
						break;
				}
				break;

			case 40:
				switch(i) {
					case 2://第一个标题
						arrayList.add("茵陈蒿提取液");
						arrayList.add("黄柏树皮提取液");
						arrayList.add("甘草酸二钾");
						arrayList.add("野蔷薇果提取液");
						arrayList.add("北美金缕梅提取液");
						break;
					case 3://第一个标题的内容
						arrayList.add("具有修复细胞、抗过敏、抗菌作用，防止皮肤粗糙。");
						arrayList.add("具有消炎、收敛、抗菌作用，有紧致肌肤的功效。");
						arrayList.add("具有较高的消炎作用，还具有解毒、抗过敏、抗溃疡的作用。能有效预防皮肤发炎和生成粉刺。（排毒）");
						arrayList.add("含有类黄酮，具有收敛、美白、抗氧化、抗炎症等作用。（收缩毛孔、抗衰老）");
						arrayList.add("具有显著的收敛、消炎效果。（保湿）");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量乳液点涂以下部位：两颊、嘴角两侧、上下颚；");
						arrayList.add("再取适量点涂于T区、两颊，然后均匀涂抹于面部，可以使干燥的部位得到双重滋润；");
						arrayList.add("最后取适量乳液/面霜涂抹于颈部。抬起下巴，由下至上轻轻提拉按摩，直至乳液/面霜完全吸收。");
						break;
				}
				break;

			case 41:
				switch(i) {
					case 2://第一个标题
						arrayList.add("水杨酸");
						arrayList.add("甘草酸二钾");
						arrayList.add("多孔皮脂吸收粉体");
						break;
					case 3://第一个标题的内容
						arrayList.add("可以温和祛除多余角质。");
						arrayList.add("具有较高的消炎作用，还具有解毒、抗过敏、抗溃疡的作用。能有效预防皮肤发炎和生成粉刺。（排毒）");
						arrayList.add("可以吸收皮肤过盛的皮脂，让肌肤重现润滑触感。");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("额头 :");
						arrayList.add("I区 :");
						arrayList.add("两颊 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取一颗黄豆大小的精华液，轻柔均匀的涂抹于面部。");
						arrayList.add("使用美容指按摩。将面部分成额头、I区、两颊三部分，分别按摩。\n" +
								"（美容指：中指和无名指）");
						arrayList.add("由眉心向发际螺旋状按摩；");
						arrayList.add("鼻梁至下巴，由上往下打圈按摩；");
						arrayList.add("以鼻翼处为出发点至耳际方向，由内到外轻轻打圈按摩再提拉。");
						break;
				}
				break;

			case 42:
				switch(i) {
					case 2://第一个标题
						arrayList.add("茵陈蒿提取液");
						arrayList.add("黄柏树皮提取液");
						arrayList.add("甘草酸二钾");
						arrayList.add("野蔷薇果提取液");
						arrayList.add("北美金缕梅提取液");
						break;
					case 3://第一个标题的内容
						arrayList.add("具有修复细胞、抗过敏、抗菌作用，防止皮肤粗糙。");
						arrayList.add("具有消炎、收敛、抗菌作用，有紧致肌肤的功效。");
						arrayList.add("具有较高的消炎作用，还具有解毒、抗过敏、抗溃疡的作用。能有效预防皮肤发炎和生成粉刺。（排毒）");
						arrayList.add("含有类黄酮，具有收敛、美白、抗氧化、抗炎症等作用。（收缩毛孔、抗衰老）");
						arrayList.add("具有显著的收敛、消炎效果。（保湿）");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("取适量产品放入掌心，加入少量水。");
						arrayList.add("使用另一支手的食指和中指，快速摩擦掌心的洁面膏体，使膏体与水充分混合，打出大量泡沫。");
						arrayList.add("将泡沫涂在脸上，让泡沫遍及整个面部，然后轻轻打圈按摩半分钟左右，注意手法要轻，避免手掌和面部摩擦产生皱纹。");
						arrayList.add("使用温水清洁面部泡沫，以脸部肌肤不涩，不滑腻，自然舒适即可。");
						break;
				}
				break;

			//口红系列
			case 43:
				switch(i) {
					case 2://第一个标题
						arrayList.add("无文字介绍");
						break;
					case 3://第一个标题的内容
						arrayList.add("");
						break;
					case 0://第二个标题
						arrayList.add("Step1 :");
						arrayList.add("Step2 :");
						arrayList.add("Step3 :");
						arrayList.add("Step4 :");
						arrayList.add("Step5 :");
						arrayList.add("Step6 :");
						arrayList.add("Step7 :");
						break;
					case 1://第二个标题的内容
						arrayList.add("先涂上一层润唇膏或防裂膏，这样可以起到护唇防裂的作用，以更好上");
						arrayList.add("在唇部擦上粉底、遮盖膏或气垫，以遮饰嘴唇轮廓。");
						arrayList.add("用唇线笔勾画出理想的轮廓线，嘴唇要自然放松，这样可以更好地观察唇形线的形状。");
						arrayList.add("按上下唇的顺序画。画上唇时要闭上嘴唇，从中央向两边画。下唇线则从两侧向中央画。");
						arrayList.add("如果不想突出唇型，也可以不画唇线。用大拇指和食指捏住唇膏或沾满口红的唇刷，让小指按在下巴上，以便把手固定和支撑好，描画唇山及下唇中央处，以决定唇厚。");
						arrayList.add("先从上嘴唇的两边嘴角向唇中涂，再从下嘴唇的两边嘴角向唇中涂，此时，双唇略张，可画出更完美的线条。注意左右两侧的平衡。涂完外侧后，逐步涂向内侧，直到全部涂满。");
						arrayList.add("以面纸轻压双唇，去除多余油脂。压时，略张双唇，效果可达双唇内侧。在唇中央涂上亮光唇膏或具强调效果的银光口红，可使双唇更显丰满。");
						break;
				}
				break;

			//固体饮料系列
			case 44:
				switch(i) {
					case 2://第一个标题
						arrayList.add("多种蔬果酵素");
						arrayList.add("六种益生菌");
						arrayList.add("专利柑橘幼果萃取");
						break;
					case 3://第一个标题的内容
						arrayList.add("");
						arrayList.add("");
						arrayList.add("");
						break;
					case 0://第二个标题
						arrayList.add("无文字介绍");
						break;
					case 1://第二个标题的内容
						arrayList.add("");
						break;
				}
				break;
			default:
				switch(i) {
					case 2://第一个标题
						arrayList.add("无文字介绍");
						break;
					case 3://第一个标题的内容
						arrayList.add("");
						break;
					case 0://第二个标题
						arrayList.add("无文字介绍");
						break;
					case 1://第二个标题的内容
						arrayList.add("");
						break;
				}
				break;
		}
		return arrayList;
	}

	/**
	 * 初始化希芸的产品列表
	 * @param list
	 * @param series
	 * @return
	 */
	ArrayList<SyrinxItem> checkSyrinxItemIsEqualsNew(ArrayList<SyrinxItem> list,String series)
	{
		if(list.equals(new ArrayList<SyrinxItem>()))
		{
			switch(series)
			{
				case "鲨烷系列":
					list.add(new SyrinxItem("01#","希芸鲨烷气垫BB霜","气垫BB","15g*2","鲨烷系列",79,79,238,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qidianbb,"30秒上妆，3秒补妆，快速打造剔透裸妆效果，含有多种珍贵植物，在美白防护的同时更有效的保养肌肤。","气垫BB可称为“会呼吸的BB霜”，妆感轻薄、自然、服贴。随身携带，随处补妆。使用气垫BB一段时间后会，料体会慢慢沉积于海绵底部,只需将海绵翻转，即可继续按取。",initItemdetails(0,0),initItemdetails(0,1),0,initItemdetails(0,2),initItemdetails(0,3)));
					list.add(new SyrinxItem("02#","希芸鲨烷气垫BB霜","气垫BB","15g*2","鲨烷系列",79,79,238,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qidianbb,"30秒上妆，3秒补妆，快速打造剔透裸妆效果，含有多种珍贵植物，在美白防护的同时更有效的保养肌肤。","气垫BB可称为“会呼吸的BB霜”，妆感轻薄、自然、服贴。随身携带，随处补妆。使用气垫BB一段时间后会，料体会慢慢沉积于海绵底部,只需将海绵翻转，即可继续按取。。",initItemdetails(0,0),initItemdetails(0,1),0,initItemdetails(0,2),initItemdetails(0,3)));
					list.add(new SyrinxItem("滋润型混装","鲨烷气垫BB霜（滋润型）","气垫BB混装","15g*2","鲨烷系列",79,79,238,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qidianbb,"30秒上妆，3秒补妆，快速打造剔透裸妆效果，含有多种珍贵植物，在美白防护的同时更有效的保养肌肤。","气垫BB可称为“会呼吸的BB霜”，妆感轻薄、自然、服贴。随身携带，随处补妆。使用气垫BB一段时间后会，料体会慢慢沉积于海绵底部,只需将海绵翻转，即可继续按取。。",initItemdetails(0,0),initItemdetails(0,1),0,initItemdetails(0,2),initItemdetails(0,3)));
					list.add(new SyrinxItem("S01#","希芸鲨烷气垫BB霜","气垫BB","15g*2","鲨烷系列",79,79,238,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qidianbb,"30秒上妆，3秒补妆，快速打造剔透裸妆效果，含有多种珍贵植物，在美白防护的同时更有效的保养肌肤。","气垫BB可称为“会呼吸的BB霜”，妆感轻薄、自然、服贴。随身携带，随处补妆。使用气垫BB一段时间后会，料体会慢慢沉积于海绵底部,只需将海绵翻转，即可继续按取。。",initItemdetails(0,0),initItemdetails(0,1),0,initItemdetails(0,2),initItemdetails(0,3)));
					list.add(new SyrinxItem("S02#","希芸鲨烷气垫BB霜","气垫BB","15g*2","鲨烷系列",79,79,238,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qidianbb,"30秒上妆，3秒补妆，快速打造剔透裸妆效果，含有多种珍贵植物，在美白防护的同时更有效的保养肌肤。","气垫BB可称为“会呼吸的BB霜”，妆感轻薄、自然、服贴。随身携带，随处补妆。使用气垫BB一段时间后会，料体会慢慢沉积于海绵底部,只需将海绵翻转，即可继续按取。。",initItemdetails(0,0),initItemdetails(0,1),0,initItemdetails(0,2),initItemdetails(0,3)));
					list.add(new SyrinxItem("清爽型混装","鲨烷气垫BB霜（清爽型）","气垫BB","15g*2","鲨烷系列",79,79,238,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qidianbb,"30秒上妆，3秒补妆，快速打造剔透裸妆效果，含有多种珍贵植物，在美白防护的同时更有效的保养肌肤。","气垫BB可称为“会呼吸的BB霜”，妆感轻薄、自然、服贴。随身携带，随处补妆。使用气垫BB一段时间后会，料体会慢慢沉积于海绵底部,只需将海绵翻转，即可继续按取。。",initItemdetails(0,0),initItemdetails(0,1),0,initItemdetails(0,2),initItemdetails(0,3)));
					list.add(new SyrinxItem("（清爽型/滋润版）","希芸鲨烷保湿水嫩面膜","面膜","25ml","鲨烷系列",3.9f,3.9f,15,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_suinengmianmo,"利用覆盖在脸部的短暂时间，暂时隔离外界的空气与污染，提高肌肤温度，皮肤的毛孔扩张，促进汗腺分泌与新陈代谢，使肌肤的含氧量上升，有利于肌肤排除表皮细胞新陈代谢的产物和累积的油脂类物质，面膜中的水分渗入表皮的角质层，皮肤变得柔软，肌肤自然光亮有弹性。","补水保湿型面膜贴的时间应控制在10—15分钟，敷面膜的时间过长，脸上的水分及养分反而会被膜纸倒吸，令肌肤变得越来越干燥。",initItemdetails(1,0),initItemdetails(1,1),R.mipmap.syrinx_suinengmianmo_use,initItemdetails(1,2),initItemdetails(1,3)));
					list.add(new SyrinxItem("（铁盒）","希芸深海凝萃保湿面膜","面膜","25ml*8片","鲨烷系列",89,89,238,"淘宝不上架",new ArrayList<String>(),true,R.mipmap.syrinx_shenghainengcui,"提高皮肤保湿和皮肤弹性，维持长时间的清爽感。还有海洋深层水中的矿物质，能给皮肤细胞带来活性，在防止皮肤老化上亦有效果。","补水保湿型面膜贴的时间应控制在10—15分钟，敷面膜的时间过长，脸上的水分及养分反而会被膜纸倒吸，令肌肤变得越来越干燥。",initItemdetails(2,0),initItemdetails(2,1),R.mipmap.syrinx_shenghainengcui_use,initItemdetails(2,2),initItemdetails(2,3)));
					list.add(new SyrinxItem("I","希芸肌初美容液","美容液","100ml","鲨烷系列",98,98,298,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_jichumeirongye,"调节肌肤水油平衡和皮肤酸碱值，修护皮肤，抗氧化，使皮肤透亮、光滑、有弹力，也可以镇定因日晒或者冻伤所引起的皮肤红、热，防止肌肤敏感。\n" +
							"肌初美容液I号（清爽型）\n" +
							"迅速渗透肌肤，触感清爽无黏腻感，渗透后，肌肤柔润富有弹力。\n" +
							"肌初美容液II号（滋润型）\n" +
							"添加可使用的天然保湿成分（壳聚糖），使用感盈润保湿，令肌肤柔软润泽、水嫩Q弹。","使用小贴士：洁面后毛孔处于张开状态，此时需要使用爽肤水迅速补水\n" +
							"（爽肤水也有“化妆水”“美容液”“柔肤水”等不同称呼）\n" +
							"使用爽肤水时，避免遗漏面部“死角”，眼角、鼻翼、嘴角等位置。",initItemdetails(3,0),initItemdetails(3,1),0,initItemdetails(3,2),initItemdetails(3,3)));
					list.add(new SyrinxItem("II","希芸肌初美容液","美容液","100ml","鲨烷系列",98,98,298,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_jichumeirongye,"调节肌肤水油平衡和皮肤酸碱值，修护皮肤，抗氧化，使皮肤透亮、光滑、有弹力，也可以镇定因日晒或者冻伤所引起的皮肤红、热，防止肌肤敏感。\n" +
							"肌初美容液I号（清爽型）\n" +
							"迅速渗透肌肤，触感清爽无黏腻感，渗透后，肌肤柔润富有弹力。\n" +
							"肌初美容液II号（滋润型）\n" +
							"添加可使用的天然保湿成分（壳聚糖），使用感盈润保湿，令肌肤柔软润泽、水嫩Q弹。","使用小贴士：洁面后毛孔处于张开状态，此时需要使用爽肤水迅速补水\n" +
							"（爽肤水也有“化妆水”“美容液”“柔肤水”等不同称呼）\n" +
							"使用爽肤水时，避免遗漏面部“死角”，眼角、鼻翼、嘴角等位置。",initItemdetails(3,0),initItemdetails(3,1),0,initItemdetails(3,2),initItemdetails(3,3)));
					list.add(new SyrinxItem("铁盒版","希芸鲨烷精纯美容油","美容液","20ml","鲨烷系列",99,99,217,"淘宝不上架",new ArrayList<String>(),true,R.mipmap.syrinx_jingcunmeirongyou,"能抑制皮肤脂质的过氧化，给肌肤供氧；能有效渗透入肌肤，并促进皮肤基底细胞的增殖，对延缓皮肤老化，改善并消除黄褐斑均有明显的生理效果。\n" +
							"还可使皮肤毛孔张开，促进血液微循环，增进细胞的新陈代谢，帮助修复破损细胞。\n" +
							"敏感肌肤的福音，对于修复红血丝有明显效果。亦可用于晒后修复。\n" +
							"高度的滋润性和保湿性；修复皮肤弹性；赋予皮肤光泽，肌肤软化剂。","鲨烷美容油能在皮肤表面形成一层天然的屏障，阻止无形的水分流失。",initItemdetails(4,0),initItemdetails(4,1),0,initItemdetails(4,2),initItemdetails(4,3)));
					list.add(new SyrinxItem("新","希芸鲨烷精纯美容油","美容液","20ml","鲨烷系列",89,89,217,"淘宝不上架",new ArrayList<String>(),true,R.mipmap.syrinx_jingcunmeirongyou,"能抑制皮肤脂质的过氧化，给肌肤供氧；能有效渗透入肌肤，并促进皮肤基底细胞的增殖，对延缓皮肤老化，改善并消除黄褐斑均有明显的生理效果。\n" +
							"还可使皮肤毛孔张开，促进血液微循环，增进细胞的新陈代谢，帮助修复破损细胞。\n" +
							"敏感肌肤的福音，对于修复红血丝有明显效果。亦可用于晒后修复。\n" +
							"高度的滋润性和保湿性；修复皮肤弹性；赋予皮肤光泽，肌肤软化剂。","鲨烷美容油能在皮肤表面形成一层天然的屏障，阻止无形的水分流失。",initItemdetails(4,0),initItemdetails(4,1),0,initItemdetails(4,2),initItemdetails(4,3)));
					list.add(new SyrinxItem("护手霜套盒","希芸鲨烷柔嫩护手霜套盒","护手霜","30ml+75ml","鲨烷系列",29,29,89,"暂无",new ArrayList<String>(),false,R.mipmap.syrinx_rounenghushoushuang,"保湿滋润、修护防护我们的双手，令手部肌肤柔嫩亮泽。","手膜对于改善手部粗糙，加强保湿，隐退干纹有明显的效果。\n" +
							"手部清洁和去角质护理之后，在手上厚厚的涂上一层护手霜并悉心按摩，\n" +
							"待充分吸收之后，再去一些手霜涂抹于手背肌肤均匀涂抹开，带上手套\n" +
							"或者敷上保鲜膜就可以了，15分钟之后手部肌肤的细致程度就足以媲美\n" +
							"面部美肌了。",initItemdetails(5,0),initItemdetails(5,1),R.mipmap.syrinx_rounenghushoushuang_use,initItemdetails(5,2),initItemdetails(5,3)));
					break;
				case "修护系列":
					list.add(new SyrinxItem("水凝","希芸水凝修护液","修护液","40ml+10ml","修护系列",89,89,195,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_suiningxiuhuye,"最大限度打开皮肤通道，让细胞更新，加速新陈代谢，增强肌肤抵抗力。\n" +
							"巩固胶原质的含水与柔软性，让肌肤亮泽水润。","修护液是护肤程序中的第三步，也就是在洁面→爽肤水之后。\n" +
							"修护液作为基底液可以帮助后续护肤品更好的吸收，所以敷面膜前使用修护液涂抹于面部，效果更佳。",initItemdetails(6,0),initItemdetails(6,1),0,initItemdetails(6,2),initItemdetails(6,3)));
					list.add(new SyrinxItem("嫩白","希芸嫩白修护液","修护液","40ml+10ml","修护系列",89,89,195,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_nengbaixiuhuye,"由内到外提亮肤色，深入肌底淡化黑色素，并给肌肤及时补水。\n" +
							"最大限度打开皮肤通道，让细胞更新，加速新陈代谢，增强肌肤抵抗力。","修护液是护肤程序中的第三步，也就是在洁面→爽肤水之后。\n" +
							"修护液作为基底液可以帮助后续护肤品更好的吸收，所以敷面膜前使用修护液涂抹于面部，效果更佳。",initItemdetails(7,0),initItemdetails(7,1),0,initItemdetails(7,2),initItemdetails(7,3)));
					list.add(new SyrinxItem("御痕","希芸御痕修护液","修护液","40ml+10ml","修护系列",125,125,195,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_yuhengxiuhuye,"最大限度打开皮肤通道，让细胞更新，加速新陈代谢，增强肌肤抵抗力。\n" +
							"促进皮肤的自我更新和修护，提升并改善肌肤的光泽与机理，紧致肌肤，平滑\n" +
							"肤质，使用后让你再次感受圆润柔滑的青春触感。","修护液是护肤程序中的第三步，也就是在洁面→爽肤水之后。\n" +
							"修护液作为基底液可以帮助后续护肤品更好的吸收，所以敷面膜前使用修护液涂抹于面部，效果更佳。",initItemdetails(8,0),initItemdetails(8,1),0,initItemdetails(8,2),initItemdetails(8,3)));
					break;
				case "幻时凝润系列":
					list.add(new SyrinxItem("泡沫","幻时凝润泡沫洁面膏","洁面膏","100ml","幻时凝润系列",59,59,80,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_paomojiemiangao,"性质柔和，彻底清洁肌肤，湿润肌肤，柔滑不紧绷，并散发自然健康的水润触感。","洗面奶在脸上停留的时间避免超过1分钟。",initItemdetails(9,0),initItemdetails(9,1),R.mipmap.syrinx_paomojiemiangao_use,initItemdetails(9,2),initItemdetails(9,3)));
					list.add(new SyrinxItem("亲肤水","幻时凝润亲肤水","亲肤水","120ml","幻时凝润系列",115,115,150,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qinfushui,"清润精透的精华质地，快速整顿肌肤纹理，恢复肌肤弹性，触感丝滑，为您缔造水润弹力的肌肤。","洁面后毛孔处于张开状态，此时需要使用亲肤水迅速补水\n" +
							"（亲肤水也有“化妆水”“美容液”“柔肤水”等不同称呼）\n" +
							"使用爽肤水时，避免遗漏面部“死角”，眼角、鼻翼、嘴角等位置。",initItemdetails(10,0),initItemdetails(10,1),R.mipmap.syrinx_qinfushui_use,initItemdetails(10,2),initItemdetails(10,3)));
					list.add(new SyrinxItem("亲肤乳","幻时凝润亲肤乳","亲肤乳","120ml","幻时凝润系列",128,128,160,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qinfuru,"蕴含紧致抗皱成分的润泽乳液，能深入皮肤，减淡细纹及深度皱纹，回复平滑紧致，全面抵御肌肤老化。","使用乳液时，建议先涂抹最干燥部位。\n" +
							"涂抹时要用指腹的力量，指腹的力度适中而且具有弹性，肤感最舒适。",initItemdetails(11,0),initItemdetails(11,1),R.mipmap.syrinx_qinfuru_use,initItemdetails(11,2),initItemdetails(11,3)));
					list.add(new SyrinxItem("活力","幻时凝润活力保湿霜","保湿霜","50g","幻时凝润系列",136,136,170,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_baoshishuang,"丰润的质地，可快速被肌肤吸收，起到强力紧肤、提高弹性的功效，修复受损的纤维组织，改善老化肤质，提升、紧致肌肤。","使用面霜时，建议先涂抹最干燥部位。\n" +
							"\n" +
							"涂抹时要用指腹的力量，指腹的力度适中而且具有弹性，肤感最舒适。",initItemdetails(12,0),initItemdetails(12,1),R.mipmap.syrinx_qinfuru_use,initItemdetails(12,2),initItemdetails(12,3)));
					list.add(new SyrinxItem("修护","幻时凝润修护眼霜","眼霜","30g","幻时凝润系列",145,145,180,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_huyanshuang,"含特效滋养精华及长效活性保湿成份，丝润柔滑的霜状质地，赋予眼部肌肤深度滋养，将营养直达细胞，以恢复肌肤弹性与韧度。展现双蛑娇嫩无恒光彩。","眼霜对眼袋、眼部细纹、鱼尾纹等有一定的效果。",initItemdetails(13,0),initItemdetails(13,1),0,initItemdetails(13,2),initItemdetails(13,3)));
					list.add(new SyrinxItem("眼啫喱","幻时凝润眼啫喱","眼啫喱","30ml","幻时凝润系列",145,145,190,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_yanzheli,"营养浓缩啫喱产品，快速补充营养及水分，平滑眼部细纹，淡化黑眼圈，此眼啫喱质地清爽，不会长脂肪粒，可做眼膜使用（可以一个星期使用两次）","眼啫喱对眼部脂肪粒、黑眼圈等有一定的效果。",initItemdetails(14,0),initItemdetails(14,1),0,initItemdetails(14,2),initItemdetails(14,3)));
					break;
				case "净颜修容系列":
					list.add(new SyrinxItem("01#","净颜修容干湿两用粉饼","粉饼","12g","净颜修容系列",39,39,128,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_ganshiliangyongfenbin,"多孔性粉粒体，每一粉粒都有无数小孔，能让肌肤呼吸畅通，不堵塞毛孔，妆效清爽更持久。有效隐藏面部毛孔瑕疵，真正做到细致无暇。","涂抹粉饼时，应少量多次的沾取粉饼，顺着毛孔生长的方向进行涂抹，能够让粉饼更服帖。",initItemdetails(15,0),initItemdetails(15,1),0,initItemdetails(15,2),initItemdetails(15,3)));
					list.add(new SyrinxItem("02#","净颜修容干湿两用粉饼","粉饼","12g","净颜修容系列",39,39,128,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_ganshiliangyongfenbin,"多孔性粉粒体，每一粉粒都有无数小孔，能让肌肤呼吸畅通，不堵塞毛孔，妆效清爽更持久。有效隐藏面部毛孔瑕疵，真正做到细致无暇。","涂抹粉饼时，应少量多次的沾取粉饼，顺着毛孔生长的方向进行涂抹，能够让粉饼更服帖。",initItemdetails(15,0),initItemdetails(15,1),0,initItemdetails(15,2),initItemdetails(15,3)));
					list.add(new SyrinxItem("50#（新）","净颜修容隔离霜","隔离霜","35ml","净颜修容系列",39,39,95,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_xiuronggelishuang,"50#适合肌肤暗黄的MM，有助于调和肤色，遮住瑕疵，提亮面部光泽。\n" +
							"70#适合肌肤底子比较好的MM，有效遮盖面部红血丝，可令面部气色红润有光泽。","涂抹隔离霜的时候，尽量轻薄，否则妆感容易不自然，也会造成肌肤负担。\n" + "肤色暗沉，可使用紫色隔离霜；面部有红血丝，可使用绿色隔离霜。",initItemdetails(16,0),initItemdetails(16,1),0,initItemdetails(16,2),initItemdetails(16,3)));
					list.add(new SyrinxItem("70#（新）","净颜修容隔离霜","隔离霜","35ml","净颜修容系列",39,39,95,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_xiuronggelishuang,"50#适合肌肤暗黄的MM，有助于调和肤色，遮住瑕疵，提亮面部光泽。\n" +
							"70#适合肌肤底子比较好的MM，有效遮盖面部红血丝，可令面部气色红润有光泽。","涂抹隔离霜的时候，尽量轻薄，否则妆感容易不自然，也会造成肌肤负担。\n" +
							"肤色暗沉，可使用紫色隔离霜；面部有红血丝，可使用绿色隔离霜。",initItemdetails(16,0),initItemdetails(16,1),0,initItemdetails(16,2),initItemdetails(16,3)));
					list.add(new SyrinxItem("清新冰爽","希芸清新冰爽防晒乳","防晒乳","30ml","净颜修容系列",39,39,88,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_bingshuangfangshairu,"质地清爽易涂抹，长时间防水、防汗、防脱妆、阻隔紫外线对皮肤的伤害。","使用一款新的防晒产品前，建议先在耳后涂抹少量产品，如感不适，建议先停止使用。\n" +
							"注* 假设一支30毫升的防晒霜，建议在一个月内使用完。",initItemdetails(17,0),initItemdetails(17,1),0,initItemdetails(17,2),initItemdetails(17,3)));
					list.add(new SyrinxItem("魔力纤长","希芸魔力纤长睫毛膏","睫毛膏","3.5g*2支","净颜修容系列",39,39,129,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_molixianchangjiemaog,"细刷头设计，可将隐藏的细小睫毛能够被刷到，打造眼部美丽神韵！\n" +
							"纤维成分，从睫毛根部开始刷染，根根卷翘分明、纤长浓密，防水不催泪。","在使用睫毛膏之前先使用睫毛夹，将睫毛夹出弧度，之后再使用睫毛膏能令睫毛卷翘。\n" +
							"使用后：想要让睫毛呈现浓密的感觉，可以等第一次涂抹的睫毛膏干后，再重复涂抹一次。",initItemdetails(18,0),initItemdetails(18,1),0,initItemdetails(18,2),initItemdetails(18,3)));
					break;
				case "臻白焕彩系列":
					list.add(new SyrinxItem("亮肤水","臻白焕彩亮肤水","亮肤水","120ml","臻白焕彩系列",45,45,95,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_liangfushui,"二次清洁皮肤，高保湿配方，利于后续产品吸收，\n" +
							"\n" +
							"可以在晚上修护液之后把这个当水膜使用，去黄效果更好。","洁面后毛孔处于张开状态，此时需要使用亮肤水迅速补水\n" +
							"\n" +
							"（亮肤水也有“化妆水”“美容液”“柔肤水”等不同称呼）\n" +
							"\n" +
							"使用爽肤水时，避免遗漏面部“死角”，眼角、鼻翼、嘴角等位置。",initItemdetails(23,0),initItemdetails(23,1),R.mipmap.syrinx_liangfushui_use,initItemdetails(23,2),initItemdetails(23,3)));
					list.add(new SyrinxItem("亮肤乳","臻白焕彩亮肤乳","亮肤乳","100ml","臻白焕彩系列",69,69,95,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_liangfuru,"美白又补水。调动肌肤调湿功能，高效补水保湿，使肌肤长久润泽，呈现无可比拟的水嫩白皙。","使用乳液时，建议先涂抹最干燥部位。\n" +
							"\n" +
							"涂抹时要用指腹的力量，指腹的力度适中而且具有弹性，肤感最舒适。",initItemdetails(20,0),initItemdetails(20,1),R.mipmap.syrinx_liangfuru_use,initItemdetails(20,2),initItemdetails(20,3)));
					list.add(new SyrinxItem("修护霜","臻白焕彩修护霜","修护霜","50g","臻白焕彩系列",75,75,100,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_huaicaixiufushuang,"多重美白保湿，24小时循环再生，淡化黑色素，在夏天可以做晚霜使用，整套使用效果更明显。","使用面霜时，建议先涂抹最干燥部位。\n" +
							"涂抹时要用指腹的力量，指腹的力度适中而且具有弹性，肤感最舒适。",initItemdetails(21,0),initItemdetails(21,1),R.mipmap.syrinx_huancaixiufushuang_use,initItemdetails(21,2),initItemdetails(21,3)));
					list.add(new SyrinxItem("精华露","臻白焕彩精华露","精华露","35ml","臻白焕彩系列",78,78,125,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_huancaijinghualu,"全方位立体美白，渗入真皮层，阻断黑色素转移，分解黑色素，提亮肤色只需28天就能看到效果，和嫩白修护液搭配淡斑效果更佳。","精华是护肤程序中的第三步，也就是在洁面→爽肤水之后。\n" +
							"\n" +
							"精华液作为基底液可以帮助后续护肤品更好的吸收，所以敷面膜前使用精华液涂抹于面部，效果更佳。",initItemdetails(22,0),initItemdetails(22,1),R.mipmap.syrinx_huancaijinghualu_use,initItemdetails(22,2),initItemdetails(22,3)));
					list.add(new SyrinxItem("泡沫","焕彩泡沫洁面膏","洁面膏","100ml","臻白焕彩系列",29,29,65,"不包邮",new ArrayList<String>(),false,R.mipmap.syrinx_jiemiangao,"低敏感配方，质地柔和，不紧绷，做好美白基础，泡沫丰富，在手心可以打出大量泡沫（倒过来不会掉就好）","洗面奶在脸上停留的时间避免超过1分钟。",initItemdetails(19,0),initItemdetails(19,1),R.mipmap.syrinx_jiemiangao_use,initItemdetails(19,2),initItemdetails(19,3)));
					break;
				case "纯净清颜系列":
					list.add(new SyrinxItem("卸妆油","纯净清颜卸妆油","卸妆油","150ml","纯净清颜系列",90,90,90,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_cunjingqingyanxiezhuangyou,"卸妆同时有效清除黑头及粉刺(针对粉刺一次到位)，易清洗，不油腻，有一定的收细毛孔效果。","无",initItemdetails(24,0),initItemdetails(24,1),0,initItemdetails(24,2),initItemdetails(24,3)));
					list.add(new SyrinxItem("卸妆乳","纯净清颜卸妆乳","卸妆乳","150ml","纯净清颜系列",45,45,60,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_cunjingqingyanxiezhuangru,"pH5.5，O∕W剂型，完美卸除彩妆及污垢，无负担，适合干性，敏感性，老年性肌肤，超温和，清洁干净。","",initItemdetails(25,0),initItemdetails(25,1),0,initItemdetails(25,2),initItemdetails(25,3)));
					list.add(new SyrinxItem("睡眠面膜","纯净清颜睡眠面膜","睡眠面膜","120ml","纯净清颜系列",69,69,90,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_cunjingqingyanshuimianmianmo,"含丰富的草本生物精华，高含量保湿成分，在夜晚持续提供营养，集中保湿、使肌肤免受有害环境的影响。\n" +
							"在保湿补水的基础上，营养成分渗入有棘层后持久吸附成熟的色素分\n" +
							"子，通过血液或汗腺循环排出体外。","",initItemdetails(26,0),initItemdetails(26,1),R.mipmap.syrinx_cunjingqingyanshuimianmianmo_use,initItemdetails(26,2),initItemdetails(26,3)));
					list.add(new SyrinxItem("活肤","纯净清颜活肤按摩霜","按摩霜","120ml","纯净清颜系列",42,42,55,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_cunjingqingyananmoshuang,"含天然香薰及植物精华，清爽而不油腻，不刺激皮肤，适合各种肌肤使用\n" +
							"通过按摩霜按摩，促进血液循环，加速皮肤吸收，滋润肌肤，柔润肌肤，改善皱纹；干性粗糙肌肤实现丝滑般效果","",initItemdetails(27,0),initItemdetails(27,1),R.mipmap.syrinx_cunjingqingyananmoshuang_use,initItemdetails(27,2),initItemdetails(27,3)));
					list.add(new SyrinxItem("活力","纯净清颜活力去角质霜","去角质霜","100ml","纯净清颜系列",39,39,55,"暂无",new ArrayList<String>(),false,R.mipmap.syrinx_cunjingqingyanqujiaozhishuang,"弱酸性植物去角质霜，补水保湿的基础上去除老废角质，温和柔软安全无刺激，改善黯沉无光的皮肤，保住皮肤的光滑细腻和光泽。","",initItemdetails(28,0),initItemdetails(28,1),0,initItemdetails(28,2),initItemdetails(28,3)));
					list.add(new SyrinxItem("眼唇卸妆液（新）","纯净清颜眼唇卸妆液（新）","眼唇卸妆液","100ml","纯净清颜系列",39,39,80,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_cunjingqingyanyancunxiezhuangye,"深度乳化技术彻底清除眼唇部彩妆，具有卓越平滑的卸妆功效，卸妆彻底眼部不易长皱纹，唇部不易出现唇纹。","",initItemdetails(29,0),initItemdetails(29,1),0,initItemdetails(29,2),initItemdetails(29,3)));
					break;
				case "水漾沁透系列":
					list.add(new SyrinxItem("爽肤乳","水漾沁透爽肤乳","爽肤乳","100ml","水漾沁透系列",50,50,80,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_shuangfuru,"质地温和，以清爽的触感，渗透入肌肤。均衡肌肤水油分布，持续滋润肌肤零负担。","",initItemdetails(30,0),initItemdetails(30,1),0,initItemdetails(30,2),initItemdetails(30,3)));
					list.add(new SyrinxItem("眼霜","水漾沁透眼霜","眼霜","30g","水漾沁透系列",89,89,120,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_cunjingqingyanyanshuang,"质地营养丰润，高度保湿，使用质感清凉，缓解眼部疲劳，守护眼部肌肤免受干燥的困扰，展现明亮清澈的双眸。","眼霜对眼袋、眼部细纹、鱼尾纹等有一定的效果。",initItemdetails(31,0),initItemdetails(31,1),0,initItemdetails(31,2),initItemdetails(31,3)));
					list.add(new SyrinxItem("营养","水漾沁透爽营养眼啫喱","眼啫喱","30ml","水漾沁透系列",69,69,95,"暂无",new ArrayList<String>(),true,R.mipmap.app2,"添加多种活性粒子，亲水配方，让肌肤快速吸收各种养分,改善及预防各种眼部问题的产生，同时防止脂肪粒的形成，让双眸恢复晶亮光彩。","眼啫喱对眼部脂肪粒、黑眼圈等有一定的效果。",initItemdetails(32,0),initItemdetails(32,1),0,initItemdetails(32,2),initItemdetails(32,3)));
					list.add(new SyrinxItem("泡沫","水漾沁透爽泡沫洁面膏","洁面膏","100ml","水漾沁透系列",9.9f,9.9f,55,"不包邮",new ArrayList<String>(),false,R.mipmap.syrinx_shuiyangqingtoujiemiangao,"适用于所有肤质，主要成分为白桦树提取物，温和洁面，无刺激不紧绷。希芸特色泡沫，细腻绵密，减弱手掌与面部之间的摩擦。","洗面奶在脸上停留的时间避免超过1分钟。",initItemdetails(33,0),initItemdetails(33,1),R.mipmap.syrinx_shuiyangqingtoujiemiangao_use,initItemdetails(33,2),initItemdetails(33,3)));
					break;
				case "新水漾系列":
					list.add(new SyrinxItem("洁面膏","新水漾洁面膏","洁面膏","100ml","新水漾系列",59,59,80,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_xinshuiyangjiemianru,"这款洁面产品在保证清洁力好的基础上，配方里面还添加了50%以上的保湿成分，且不含其他的活性剂，使清洁后的肌肤不会干燥，非常舒适。","洗面奶在脸上停留的时间避免超过1分钟。",initItemdetails(34,0),initItemdetails(34,1),R.mipmap.syrinx_xinshuiyangjiemianru_use,initItemdetails(34,2),initItemdetails(34,3)));
					list.add(new SyrinxItem("柔肤水/保湿液","新水漾柔肤水/保湿液","柔肤水/保湿液","150ml","新水漾系列",69,69,95,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_xinshuiyangbaoshiye,"清润晶透的精华质地，二次清洁同时补充肌肤所需养分及水分。\n" +
							"触感滋润 ，易于延展，在肌肤表面形成保护膜，使肌肤如丝般柔滑，由内而外塑造饱满而富有弹性的肌肤。","洁面后毛孔处于张开状态，此时需要使用柔肤水迅速补水\n" +
							"（柔肤水也有“化妆水”“美容液”“爽肤水”等不同称呼）\n" +
							"使用爽肤水时，避免遗漏面部“死角”，眼角、鼻翼、嘴角等位置。",initItemdetails(35,0),initItemdetails(35,1),R.mipmap.syrinx_xinshuiyangbaoshiye_use,initItemdetails(35,2),initItemdetails(35,3)));
					list.add(new SyrinxItem("柔肤乳/保湿乳","新水漾柔肤乳/保湿乳","柔肤乳/保湿乳","100ml","新水漾系列",69,69,95,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_xinshuiyangroufuru,"增加角质层含水量，保护弹力蛋白，提高肌肤自身的修护能力，改善角质堆积，补充和储存水分。","使用乳液时，建议先涂抹最干燥部位。\n" +
							"涂抹时要用指腹的力量，指腹的力度适中而且具有弹性，肤感最舒适。",initItemdetails(36,0),initItemdetails(36,1),R.mipmap.syrinx_xinshuiyangroufuru_use,initItemdetails(36,2),initItemdetails(36,3)));
					list.add(new SyrinxItem("精华露","新水漾精华露","精华露","40ml","新水漾系列",89,89,125,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_xinshuiyangjinghualu,"使用感水润，有透明感，课作为导入美容液适用于清洁后的素肌，也可在化妆水后使用。\n" +
							"\n" +
							"能瞬时渗透滋润角质层，油性成分和高分子形成保护膜覆在肌肤表面，保护肌肤免受干燥的侵害。配合保湿成分（多元醇，糖类）和滋润成分，使用感清爽水润，涂抹后，瞬时渗入肌肤，没有黏腻感，令肌肤变得光滑，调理肌肤保湿平衡。配合质感轻盈的油性成分（角鲨烷），与100%水系成分的同类产品相比，使用后的保湿感强，且持久保湿。","精华是护肤程序中的第三步，也就是在洁面→爽肤水之后。\n" +
							"\n" +
							"精华液作为基底液可以帮助后续护肤品更好的吸收，所以敷面膜前使用精华液涂抹于面部，效果更佳。",initItemdetails(37,0),initItemdetails(37,1),R.mipmap.syrinx_xinshuiyangjinghualu_use,initItemdetails(37,2),initItemdetails(37,3)));
					list.add(new SyrinxItem("保湿霜","新水漾保湿霜","保湿霜","50g","新水漾系列",69,69,100,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_xinshuiyangbaishishuang,"甘油及植物萃取的保湿成分，能赋予肌肤滋润。矿物油及角鲨烷的滋润成分，能抑制水分蒸发，调整角质层屏障功能，保持肌肤健康状态。","暂无",initItemdetails(38,0),initItemdetails(38,1),0,initItemdetails(38,2),initItemdetails(38,3)));
					break;
				case "清透肌系列":
					list.add(new SyrinxItem("爽肤水","清透肌爽肤水","爽肤水","150ml","清透肌系列",89,89,116,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qingtouji_qingfushui,"二次清洁肌肤，具有防止肌肤粗糙作用的尿囊素，能有效抑制面部细菌，控制炎症，保护肌肤。与肌肤的亲和性极佳，超强渗透力，使肌肤清爽不粘腻。","洁面后毛孔处于张开状态，此时需要使用爽肤水迅速补水\n" +
							"（爽肤水也有“化妆水”“美容液”“柔肤水”等不同称呼）\n" +
							"使用爽肤水时，避免遗漏面部“死角”，眼角、鼻翼、嘴角等位置。",initItemdetails(39,0),initItemdetails(39,1),R.mipmap.syrinx_qingtouji_qingfushui_use,initItemdetails(39,2),initItemdetails(39,3)));
					list.add(new SyrinxItem("爽肤乳","清透肌爽肤乳","爽肤乳","100ml","清透肌系列",89,89,118,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qingtouji_shuangfuru,"无油配方，适合对油敏感的肌肤。滋润肌肤，清爽不粘腻，令肌肤有弹力感。透明质酸，滋润保湿，有光滑的使用感。","使用乳液/面霜时，建议先涂抹最干燥部位。\n" +
							"涂抹乳液时要用指腹的力量，指腹的力度适中而且具有弹性，肤感最舒适。",initItemdetails(40,0),initItemdetails(40,1),R.mipmap.syrinx_qinfuru_use,initItemdetails(40,2),initItemdetails(40,3)));
					list.add(new SyrinxItem("舒痘修护","清透肌舒痘修护精华露","精华露","20ml","清透肌系列",95,95,128,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qudoujinghua,"敌痘专家，修复舒缓。特别针对发炎状态的痘痘，恢复皮脂正常新陈代谢。","",initItemdetails(41,0),initItemdetails(41,1),R.mipmap.syrinx_qudoujinghua_use,initItemdetails(41,2),initItemdetails(41,3)));
					list.add(new SyrinxItem("洁面乳","清透洁面乳","洁面乳","100ml","清透肌系列",59,59,118,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_qingtoujiemianru,"泡沫柔软丰富细腻，且有弹性。祛除脸上的皮脂等污物，保持面部清洁。","使用小贴士：洗面奶在脸上停留的时间避免超过1分钟。",initItemdetails(42,0),initItemdetails(42,1),R.mipmap.syrinx_qingtoujiemianru_use,initItemdetails(42,2),initItemdetails(42,3)));
					break;
				case "口红系列":
					list.add(new SyrinxItem("101#鸽血红","丝绒盈润唇膏鸽血红","唇膏口红","3.5g","口红系列",49,49,109,"暂无",new ArrayList<String>(),false,R.mipmap.syrinx_kouhong,"希芸丝绒盈润唇膏可达到滋润、保护嘴唇，增加面部美感及修正嘴唇轮廓，是女性必备的美容化妆品之一，可显出女性之气质、妩媚。","1.七大时尚颜色，专为亚洲人设计\n2.易上色，不凝块\n3.保湿力强，可以当润唇膏用\n4.天然油脂，食品级原料\n5.高显色度，遮盖唇纹",initItemdetails(43,0),initItemdetails(43,1),R.mipmap.syrinx_kouhong_yanse,initItemdetails(43,2),initItemdetails(43,3)));
					list.add(new SyrinxItem("102#玛瑙红","丝绒盈润唇膏玛瑙红","唇膏口红","3.5g","口红系列",49,49,109,"暂无",new ArrayList<String>(),false,R.mipmap.syrinx_kouhong,"希芸丝绒盈润唇膏可达到滋润、保护嘴唇，增加面部美感及修正嘴唇轮廓，是女性必备的美容化妆品之一，可显出女性之气质、妩媚。","1.七大时尚颜色，专为亚洲人设计\n" +
							"2.易上色，不凝块\n" +
							"3.保湿力强，可以当润唇膏用\n" +
							"4.天然油脂，食品级原料\n" +
							"5.高显色度，遮盖唇纹",initItemdetails(43,0),initItemdetails(43,1),R.mipmap.syrinx_kouhong_yanse,initItemdetails(43,2),initItemdetails(43,3)));
					list.add(new SyrinxItem("103#石榴红","丝绒盈润唇膏石榴红","唇膏口红","3.5g","口红系列",49,49,109,"暂无",new ArrayList<String>(),false,R.mipmap.syrinx_kouhong,"希芸丝绒盈润唇膏可达到滋润、保护嘴唇，增加面部美感及修正嘴唇轮廓，是女性必备的美容化妆品之一，可显出女性之气质、妩媚。","1.七大时尚颜色，专为亚洲人设计\n" +
							"2.易上色，不凝块\n" +
							"3.保湿力强，可以当润唇膏用\n" +
							"4.天然油脂，食品级原料\n" +
							"5.高显色度，遮盖唇纹",initItemdetails(43,0),initItemdetails(43,1),R.mipmap.syrinx_kouhong_yanse,initItemdetails(43,2),initItemdetails(43,3)));
					list.add(new SyrinxItem("201#水晶粉","丝绒盈润唇膏水晶粉","唇膏口红","3.5g","口红系列",49,49,109,"暂无",new ArrayList<String>(),false,R.mipmap.syrinx_kouhong,"希芸丝绒盈润唇膏可达到滋润、保护嘴唇，增加面部美感及修正嘴唇轮廓，是女性必备的美容化妆品之一，可显出女性之气质、妩媚。","1.七大时尚颜色，专为亚洲人设计\n" +
							"2.易上色，不凝块\n" +
							"3.保湿力强，可以当润唇膏用\n" +
							"4.天然油脂，食品级原料\n" +
							"5.高显色度，遮盖唇纹",initItemdetails(43,0),initItemdetails(43,1),R.mipmap.syrinx_kouhong_yanse,initItemdetails(43,2),initItemdetails(43,3)));
					list.add(new SyrinxItem("301#琥珀橙","丝绒盈润唇膏琥珀橙","唇膏口红","3.5g","口红系列",49,49,109,"暂无",new ArrayList<String>(),false,R.mipmap.syrinx_kouhong,"希芸丝绒盈润唇膏可达到滋润、保护嘴唇，增加面部美感及修正嘴唇轮廓，是女性必备的美容化妆品之一，可显出女性之气质、妩媚。","1.七大时尚颜色，专为亚洲人设计\n" +
							"2.易上色，不凝块\n" +
							"3.保湿力强，可以当润唇膏用\n" +
							"4.天然油脂，食品级原料\n" +
							"5.高显色度，遮盖唇纹",initItemdetails(43,0),initItemdetails(43,1),R.mipmap.syrinx_kouhong_yanse,initItemdetails(43,2),initItemdetails(43,3)));
					list.add(new SyrinxItem("302#珊瑚橙","丝绒盈润唇膏珊瑚橙","唇膏口红","3.5g","口红系列",49,49,109,"暂无",new ArrayList<String>(),false,R.mipmap.syrinx_kouhong,"希芸丝绒盈润唇膏可达到滋润、保护嘴唇，增加面部美感及修正嘴唇轮廓，是女性必备的美容化妆品之一，可显出女性之气质、妩媚。","1.七大时尚颜色，专为亚洲人设计\n" +
							"2.易上色，不凝块\n" +
							"3.保湿力强，可以当润唇膏用\n" +
							"4.天然油脂，食品级原料\n" +
							"5.高显色度，遮盖唇纹",initItemdetails(43,0),initItemdetails(43,1),R.mipmap.syrinx_kouhong_yanse,initItemdetails(43,2),initItemdetails(43,3)));
					list.add(new SyrinxItem("501#羊脂裸","丝绒盈润唇膏羊脂裸","唇膏口红","3.5g","口红系列",49,49,109,"暂无",new ArrayList<String>(),false,R.mipmap.syrinx_kouhong,"希芸丝绒盈润唇膏可达到滋润、保护嘴唇，增加面部美感及修正嘴唇轮廓，是女性必备的美容化妆品之一，可显出女性之气质、妩媚。","1.七大时尚颜色，专为亚洲人设计\n" +
							"2.易上色，不凝块\n" +
							"3.保湿力强，可以当润唇膏用\n" +
							"4.天然油脂，食品级原料\n" +
							"5.高显色度，遮盖唇纹",initItemdetails(43,0),initItemdetails(43,1),R.mipmap.syrinx_kouhong_yanse,initItemdetails(43,2),initItemdetails(43,3)));
					break;
				case "礼盒系列":
					list.add(new SyrinxItem("I","肌初悦享限量礼盒","礼盒","盒","礼盒系列",256,256,0,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_ganshiliangyongfenbin,"暂无","暂无",initItemdetails(-1,0),initItemdetails(-1,1),0,initItemdetails(-1,2),initItemdetails(-1,3)));
					list.add(new SyrinxItem("II","肌初悦享限量礼盒","礼盒","盒","礼盒系列",256,256,0,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_ganshiliangyongfenbin,"暂无","暂无",initItemdetails(-1,0),initItemdetails(-1,1),0,initItemdetails(-1,2),initItemdetails(-1,3)));
					list.add(new SyrinxItem("III","肌初悦享限量礼盒","礼盒","盒","礼盒系列",256,256,0,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_ganshiliangyongfenbin,"暂无","暂无",initItemdetails(-1,0),initItemdetails(-1,1),0,initItemdetails(-1,2),initItemdetails(-1,3)));
					list.add(new SyrinxItem("I(悦悦版)","肌初悦享限量礼盒","礼盒","盒","礼盒系列",256,256,0,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_ganshiliangyongfenbin,"暂无","暂无",initItemdetails(-1,0),initItemdetails(-1,1),0,initItemdetails(-1,2),initItemdetails(-1,3)));
					list.add(new SyrinxItem("II(悦悦版)","肌初悦享限量礼盒","礼盒","盒","礼盒系列",256,256,0,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_ganshiliangyongfenbin,"暂无","暂无",initItemdetails(-1,0),initItemdetails(-1,1),0,initItemdetails(-1,2),initItemdetails(-1,3)));
					list.add(new SyrinxItem("III(悦悦版)","肌初悦享限量礼盒","礼盒","盒","礼盒系列",256,256,0,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_ganshiliangyongfenbin,"暂无","暂无",initItemdetails(-1,0),initItemdetails(-1,1),0,initItemdetails(-1,2),initItemdetails(-1,3)));
					break;
				case "固体饮料系列":
					list.add(new SyrinxItem("果蔬粉","乳酸菌复合发酵果蔬粉","果蔬粉","10g*15袋","固体饮料系列",113,113,150,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_guoshufen,"希芸乳酸菌综合发酵果蔬粉，可以有效的净化肠道排出毒素、维持肠道菌群平衡、提高人体免疫力、抑制脂肪产生控制体重。\n" +
							"适合人群有: 免疫力低下、肠胃功能弱、便秘、缺乏养分等想改善身体健康的人群。","",initItemdetails(44,0),initItemdetails(44,1),0,initItemdetails(44,2),initItemdetails(44,3)));
					list.add(new SyrinxItem("姜茶","黑糖桂圆姜茶","8g/包*12包","固体饮料系列","茶",79,79,0,"暂无",new ArrayList<String>(),true,R.mipmap.syrinx_heiguiyuancha,"Q：希芸黑糖桂圆姜茶——正确的饮用方式？\n" +
							"A：建议每袋使用100ml~150ml的温水稀释，搅拌后即可饮用。可根据自身实际情况而加量冲泡。\n" +
							"\n" +
							"Q：希芸黑糖桂圆姜茶——适合哪类人群饮用？\n" +
							"A：除了三岁以下儿童，以及孕妇不建议饮用，其他人群皆可饮用。尤其是手脚冰冷、体寒、体虚的女性,以及想要加强新陈代谢者。\n" +
							"\n" +
							"Q：与市面上的产品差别？\n" +
							"A：希芸黑糖桂圆姜茶成分中，特添加了台湾高良月桃。高良月桃萃取液能够有效的提升细胞能量的产生，提高人体体温、促进新陈代谢，达到燃烧热量、瘦身的效果，所以您大可放心饮用。\n" +
							"\n" +
							"Q： 喝热巧克力可舒缓经痛？\n" +
							"A：温热食物有助于减缓疼痛，但甜食不宜过量摄取，否则会造成肥胖。\n" +
							"\n" +
							"Q：痛经时服用止痛药止痛效果最好？\n" +
							"A：是的，但是，止痛药不仅伤身更伤胃，并且少数人还会被引发过敏情况。止痛药虽止痛，但一定要谨慎服用，并且视个人体质来决定使用与否。","暂无",initItemdetails(-1,0),initItemdetails(-1,1),0,initItemdetails(-1,2),initItemdetails(-1,3)));
					break;
				case "中小样系列":
					list.add(new SyrinxItem("柔肤水","极致水漾柔肤水","柔肤水","20ml","中小样系列",8,8,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_roufushui,"清润晶透的精华质地，二次清洁同时补充肌肤所需养分及水分。\n" +
							"触感滋润 ，易于延展，在肌肤表面形成保护膜，使肌肤如丝般柔滑，由内而外塑造饱满而富有弹性的肌肤。","洁面后毛孔处于张开状态，此时需要使用柔肤水迅速补水\n" +
							"（柔肤水也有“化妆水”“美容液”“爽肤水”等不同称呼）\n" +
							"使用爽肤水时，避免遗漏面部“死角”，眼角、鼻翼、嘴角等位置。",initItemdetails(35,0),initItemdetails(35,1),R.mipmap.syrinx_roufushui_use,initItemdetails(35,2),initItemdetails(35,3)));
					list.add(new SyrinxItem("柔肤乳","极致水漾柔肤乳","柔肤乳","12ml","中小样系列",8,8,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_xinshuiyangroufuru,"增加角质层含水量，保护弹力蛋白，提高肌肤自身的修护能力，改善角质堆积，补充和储存水分。","使用乳液时，建议先涂抹最干燥部位。\n" +
							"涂抹时要用指腹的力量，指腹的力度适中而且具有弹性，肤感最舒适。",initItemdetails(36,0),initItemdetails(36,1),R.mipmap.syrinx_xinshuiyangroufuru_use,initItemdetails(36,2),initItemdetails(36,3)));
					list.add(new SyrinxItem("精华露","极致水漾精华露","精华露","5ml","中小样系列",8,8,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_xinshuiyangjinghualu,"使用感水润，有透明感，课作为导入美容液适用于清洁后的素肌，也可在化妆水后使用。\n" +
							"能瞬时渗透滋润角质层，油性成分和高分子形成保护膜覆在肌肤表面，保护肌肤免受干燥的侵害。配合保湿成分（多元醇，糖类）和滋润成分，使用感清爽水润，涂抹后，瞬时渗入肌肤，没有黏腻感，令肌肤变得光滑，调理肌肤保湿平衡。配合质感轻盈的油性成分（角鲨烷），与100%水系成分的同类产品相比，使用后的保湿感强，且持久保湿。","精华是护肤程序中的第三步，也就是在洁面→爽肤水之后。\n" +
							"精华液作为基底液可以帮助后续护肤品更好的吸收，所以敷面膜前使用精华液涂抹于面部，效果更佳。",initItemdetails(37,0),initItemdetails(37,1),R.mipmap.syrinx_xinshuiyangjinghualu_use,initItemdetails(37,2),initItemdetails(37,3)));
					list.add(new SyrinxItem("洁面乳","极致水漾洁面乳","洁面乳","10ml","中小样系列",6,6,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_xinshuiyangjiemianru,"这款洁面产品在保证清洁力好的基础上，配方里面还添加了50%以上的保湿成分，且不含其他的活性剂，使清洁后的肌肤不会干燥，非常舒适。","洗面奶在脸上停留的时间避免超过1分钟。",initItemdetails(34,0),initItemdetails(34,1),R.mipmap.syrinx_xinshuiyangjiemianru_use,initItemdetails(34,2),initItemdetails(34,3)));
					list.add(new SyrinxItem("眼霜","水漾沁透眼霜","眼霜","2g","中小样系列",2,2,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_cunjingqingyanyanshuang,"质地营养丰润，高度保湿，使用质感清凉，缓解眼部疲劳，守护眼部肌肤免受干燥的困扰，展现明亮清澈的双眸。","眼霜对眼袋、眼部细纹、鱼尾纹等有一定的效果。",initItemdetails(31,0),initItemdetails(31,1),0,initItemdetails(31,2),initItemdetails(31,3)));
					list.add(new SyrinxItem("嫩白","嫩白修护液","修护液","10ml","中小样系列",19,19,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_nengbaixiuhuye,"由内到外提亮肤色，深入肌底淡化黑色素，并给肌肤及时补水。\n" +
							"最大限度打开皮肤通道，让细胞更新，加速新陈代谢，增强肌肤抵抗力。","修护液是护肤程序中的第三步，也就是在洁面→爽肤水之后。\n" +
							"修护液作为基底液可以帮助后续护肤品更好的吸收，所以敷面膜前使用修护液涂抹于面部，效果更佳。",initItemdetails(7,0),initItemdetails(7,1),0,initItemdetails(7,2),initItemdetails(7,3)));
					list.add(new SyrinxItem("卸痕","卸痕修护液","修护液","10ml","中小样系列",29,29,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_yuhengxiuhuye,"最大限度打开皮肤通道，让细胞更新，加速新陈代谢，增强肌肤抵抗力。\n" +
							"促进皮肤的自我更新和修护，提升并改善肌肤的光泽与机理，紧致肌肤，平滑\n" +
							"肤质，使用后让你再次感受圆润柔滑的青春触感。","修护液是护肤程序中的第三步，也就是在洁面→爽肤水之后。\n" +
							"修护液作为基底液可以帮助后续护肤品更好的吸收，所以敷面膜前使用修护液涂抹于面部，效果更佳。",initItemdetails(8,0),initItemdetails(8,1),0,initItemdetails(8,2),initItemdetails(8,3)));
					list.add(new SyrinxItem("亮肤水","臻白焕采亮肤水","亮肤水","20ml","中小样系列",6,6,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_liangfushui,"二次清洁皮肤，高保湿配方，利于后续产品吸收，\n" +
							"可以在晚上修护液之后把这个当水膜使用，去黄效果更好。","洁面后毛孔处于张开状态，此时需要使用亮肤水迅速补水\n" +
							"（亮肤水也有“化妆水”“美容液”“柔肤水”等不同称呼）\n" +
							"使用爽肤水时，避免遗漏面部“死角”，眼角、鼻翼、嘴角等位置。",initItemdetails(23,0),initItemdetails(23,1),R.mipmap.syrinx_liangfushui_use,initItemdetails(23,2),initItemdetails(23,3)));
					list.add(new SyrinxItem("亮肤乳","臻白焕采亮肤乳","亮肤乳","20ml","中小样系列",7,7,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_liangfuru,"美白又补水。调动肌肤调湿功能，高效补水保湿，使肌肤长久润泽，呈现无可比拟的水嫩白皙。","使用乳液时，建议先涂抹最干燥部位。\n" +
							"\n" +
							"涂抹时要用指腹的力量，指腹的力度适中而且具有弹性，肤感最舒适。",initItemdetails(20,0),initItemdetails(20,1),R.mipmap.syrinx_liangfuru_use,initItemdetails(20,2),initItemdetails(20,3)));
					list.add(new SyrinxItem("泡沫","焕采泡沫洁面膏","洁面膏","15ml","中小样系列",6,6,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_jiemiangao,"低敏感配方，质地柔和，不紧绷，做好美白基础，泡沫丰富，在手心可以打出大量泡沫（倒过来不会掉就好）","洗面奶在脸上停留的时间避免超过1分钟。",initItemdetails(19,0),initItemdetails(19,1),R.mipmap.syrinx_jiemiangao_use,initItemdetails(19,2),initItemdetails(19,3)));
					list.add(new SyrinxItem("睡眠面膜","纯净清颜睡眠面膜","睡眠面膜","15ml","中小样系列",5,5,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_cunjingqingyanshuimianmianmo,"含丰富的草本生物精华，高含量保湿成分，在夜晚持续提供营养，集中保湿、使肌肤免受有害环境的影响。\n" +
							"在保湿补水的基础上，营养成分渗入有棘层后持久吸附成熟的色素分\n" +
							"子，通过血液或汗腺循环排出体外。","",initItemdetails(26,0),initItemdetails(26,1),R.mipmap.syrinx_cunjingqingyanshuimianmianmo_use,initItemdetails(26,2),initItemdetails(26,3)));
					list.add(new SyrinxItem("卸妆乳","纯净清颜卸妆乳","卸妆乳","30ml","中小样系列",8,8,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_cunjingqingyanxiezhuangru,"pH5.5，O∕W剂型，完美卸除彩妆及污垢，无负担，适合干性，敏感性，老年性肌肤，超温和，清洁干净。","暂无",initItemdetails(25,0),initItemdetails(25,1),0,initItemdetails(25,2),initItemdetails(25,3)));
					list.add(new SyrinxItem("爽肤水","清透肌爽肤水","爽肤水","20ml","中小样系列",8,8,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_qingtouji_qingfushui,"二次清洁肌肤，具有防止肌肤粗糙作用的尿囊素，能有效抑制面部细菌，控制炎症，保护肌肤。与肌肤的亲和性极佳，超强渗透力，使肌肤清爽不粘腻。","洁面后毛孔处于张开状态，此时需要使用爽肤水迅速补水\n" +
							"（爽肤水也有“化妆水”“美容液”“柔肤水”等不同称呼）\n" +
							"使用爽肤水时，避免遗漏面部“死角”，眼角、鼻翼、嘴角等位置。",initItemdetails(39,0),initItemdetails(39,1),R.mipmap.syrinx_qingtouji_qingfushui_use,initItemdetails(39,2),initItemdetails(39,3)));
					list.add(new SyrinxItem("爽肤乳","清透肌爽肤乳","爽肤乳","12ml","中小样系列",8,8,0,"买三包邮买五送一",new ArrayList<String>(),false,R.mipmap.syrinx_qingtouji_shuangfuru,"无油配方，适合对油敏感的肌肤。滋润肌肤，清爽不粘腻，令肌肤有弹力感。透明质酸，滋润保湿，有光滑的使用感。","使用乳液/面霜时，建议先涂抹最干燥部位。\n" +
							"涂抹乳液时要用指腹的力量，指腹的力度适中而且具有弹性，肤感最舒适。",initItemdetails(40,0),initItemdetails(40,1),R.mipmap.syrinx_qinfuru_use,initItemdetails(40,2),initItemdetails(40,3)));
					break;
				default:return null;
			}
		}
		return list;
	}
}
