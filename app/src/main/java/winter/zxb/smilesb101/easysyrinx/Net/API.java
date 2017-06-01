package winter.zxb.smilesb101.easysyrinx.Net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 项目名称：EasySyrinx
 * 类描述：RetrofitAPI
 * 创建人：SmileSB101
 * 创建时间：2017/5/26 0026 09:13
 * 修改人：Administrator
 * 修改时间：2017/5/26 0026 09:13
 * 修改备注：
 */

public interface API{
	//http://guolin.tech/api/china
	@GET("china")
	Call<ResponseBody> getChinaProvinces();

	@GET("china/{id}")
	Call<ResponseBody> getCitys(@Path("id") int id);

	/**
	 * 获取城市的乡镇信息
	 * @param porvinceId
	 * @param cityId
	 * @return
	 */
	@GET("china/{ProvinceId}/{CityId}")
	Call<ResponseBody> getTown(@Path("ProvinceId") int porvinceId,@Path("CityId") int cityId);
}
