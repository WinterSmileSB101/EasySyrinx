package winter.zxb.smilesb101.easysyrinx.UI.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.TimerTask;

import winter.zxb.smilesb101.easysyrinx.R;

/**
 * 项目名称：EasySyrinx
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/2/23 0023 18:33
 * 修改人：Administrator
 * 修改时间：2017/2/23 0023 18:33
 * 修改备注：
 */

public class Timer extends View{

	private static final String TAG = "timer";
	private int needleColor,timeColor;
	private int needleSize,timeSize;
	private boolean show_TickMark;

	private Rect mround;
	private Paint needlePaint,timePaint,paintHour,paintMinute,paintSecond;
	private float x,y,r;

	private String[] Hours = new String[]{"12","1","2","3","4","5","6","7","8","9","10","11"};
	private float hour,minute,sceond;


	public Timer(Context context){
		this(context,null);
	}

	public Timer(Context context,AttributeSet attrs){
		this(context,attrs,0);
	}

	public Timer(Context context,AttributeSet attrs,int defStyleAttr){
		super(context,attrs,defStyleAttr);
		TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.Timer,defStyleAttr,0);
		int n = array.getIndexCount();
		for(int i = 0;i < n;i++)
		{
			int arr = array.getIndex(i);
			switch(arr)
			{
				case R.styleable.Timer_needle_color:
					needleColor = array.getColor(arr,Color.BLUE);
				    break;
				case R.styleable.Timer_needle_size:
					//默认设置为16sp，TypeValue也可以把sp转化为px
					needleSize = array.getDimensionPixelSize(arr,(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,16,getResources().getDisplayMetrics()));
		break;
		case R.styleable.Timer_time_color:
		timeColor = array.getColor(arr,Color.GREEN);
		break;
		case R.styleable.Timer_time_size:
		timeSize = array.getDimensionPixelSize(arr,(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,22,getResources().getDisplayMetrics()));
		break;
		case R.styleable.Timer_show_tickMark:
		//默认显示刻度线（小的）
		show_TickMark = array.getBoolean(arr,true);
		break;
	}
}

array.recycle();

		//设置画笔
		needlePaint = new Paint();
		needlePaint.setTextSize(needleSize);
		needlePaint.setColor(needleColor);

		timePaint = new Paint();
		timePaint.setColor(timeColor);
		timePaint.setTextSize(timeSize);

		mround = new Rect();
		timePaint.getTextBounds("12", 0,"12".length(), mround);

		hour = 2;
		minute = 45;
		sceond = 46;

		java.util.Timer timer = new java.util.Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				Log.i(TAG,"run: 进入任务");
				sceond++;
				minute+=sceond/3600;
				hour+=minute/43200;
				sceond%=60;
				minute%=60;
				hour%=12;
				postInvalidate();
			}
		},1000,1000);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec,heightMeasureSpec);
		int height = getMeasuredHeight();
		int width = getMeasuredWidth();
		x = width / 2;
		y = height / 2;
		//圆的半径即为view宽度的一半，在减5会使得边缘好看些
		r = (int) x - 5;

	}

	@Override
	protected void onDraw(Canvas canvas){
		//绘画出时钟界面
		Log.i(TAG,"onDraw: 颜色：" + timeColor);
		Paint p = new Paint();
		p.setColor(Color.BLACK);
		canvas.drawCircle(x,y,10,p);
		drawTickMark(canvas);
		drawOrigin(canvas,hour,minute,sceond);
	}
	//画点
	void drawPoint(Canvas canvas)
	{
		int pos = 12;
		for(int i = 0;i < 60;i++)
		{
			Log.i(TAG,"drawPoint: "+pos);
			if(i%5==0)
			{
				pos = pos%12;
				canvas.drawText(Hours[pos],x-10, r-50, timePaint);
				//画大点
				canvas.drawCircle(x,r,10,timePaint);
				pos++;
			}
			else
			{
				//画小点
				canvas.drawCircle(x,r,2,timePaint);
			}
			canvas.rotate(6,x,y);
		}
	}

	//画刻度
	void drawTickMark(Canvas canvas){
		int pos = 12;
		for(int i = 0;i < 60;i++)
		{
			Log.i(TAG,"drawPoint: "+pos);
			if(i%5==0)
			{
				pos = pos%12;
				canvas.drawText(Hours[pos],x-10, r-50, timePaint);
				//画大刻度
				timePaint.setStrokeWidth(8);
				canvas.drawLine(x,r,x,r+20,timePaint);
				pos++;
			}
			else
			{
				//画小刻度
				timePaint.setStrokeWidth(2);
				canvas.drawLine(x,r,x,r+10,timePaint);
			}
			//旋转6度
			canvas.rotate(6,x,y);
		}
	}

	void drawOrigin(final Canvas canvas,float hour,float minute,float second)
	{
		paintHour = new Paint();
		paintHour.setColor(Color.BLACK);
		paintHour.setStrokeWidth(10);
		paintMinute = new Paint();
		paintMinute.setColor(Color.BLACK);
		paintMinute.setStrokeWidth(6);
		paintSecond = new Paint();
		paintSecond.setColor(Color.RED);
		paintSecond.setStrokeWidth(1);

		//旋转30*小时的到时针的角度
		canvas.rotate(30*hour,x,y);
		canvas.drawLine(x,y,x,r+150,paintHour);
		//6*分钟找到分针的角度
		canvas.rotate(6*minute,x,y);
		canvas.drawLine(x,y,x,r+100,paintMinute);
		//6*秒钟找到秒针的角度
		canvas.rotate(6*second,x,y);
		canvas.drawLine(x,y,x,r+5,paintSecond);
	}
}
