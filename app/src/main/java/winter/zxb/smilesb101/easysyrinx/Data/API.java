package winter.zxb.smilesb101.easysyrinx.Data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 项目名称：CoderHome
 * 类描述：API接口
 * 创建人：SmileSB101
 * 创建时间：2017/6/1 0001 08:04
 * 修改人：Administrator
 * 修改时间：2017/6/1 0001 08:04
 * 修改备注：
 */

public interface API{

	public static final String APP_ID = "39402";
	public static final String APP_SIGN = "0c5c3fa059844041b12d6095c5d9c258";

	/**
	 * 获取今日的必应图片（一张）
	 * http://route.showapi.com/1287-1
	 * @return
	 */
	@GET("1287-1")
	Call<ResponseBody> getBingToday(@Query("showapi_appid") String appID,@Query("showapi_sign") String appSign);
}
