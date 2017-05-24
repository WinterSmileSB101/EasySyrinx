package winter.zxb.smilesb101.easysyrinx.CustomAnimation;

import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

/**
 * 项目名称：EasySyrinx
 * 类描述：二次贝塞尔曲线Evaluator（抛物线动画）
 * 创建人：SmileSB101
 * 创建时间：2017/5/16 0016 19:47
 * 修改人：Administrator
 * 修改时间：2017/5/16 0016 19:47
 * 修改备注：
 */

public class BezierEvaluator implements TypeEvaluator<PointF>{

	private String TAG = "贝塞尔抛物线";
	private PointF controllPoint;

	public BezierEvaluator(PointF controllPoint){
		this.controllPoint = controllPoint;
	}

	@Override
	public PointF evaluate(float t,PointF startValue,PointF endValue){
		int x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * controllPoint.x + t * t * endValue.x);
		int y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * controllPoint.y + t * t * endValue.y);
		return new PointF(x, y);
	}
}
