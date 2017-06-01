package winter.zxb.smilesb101.easysyrinx.Data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 项目名称：CoderHome
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/6/1 0001 08:13
 * 修改人：Administrator
 * 修改时间：2017/6/1 0001 08:13
 * 修改备注：
 */

public class IBingTodayModel{

	String TAG = "IBingTodayModel";

	public static final IBingTodayModel I_BING_TODAY_MODEL = new IBingTodayModel();

	public interface BingTodayListener{
		void onSuccess(BingToday bingToday);
		void onError(String error);
	}

	/**
	 * 获取每日必应图片
	 * @param listener 监听回调
	 */
	public void getBingToday(final BingTodayListener listener)
	{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://route.showapi.com/")
				.build();
		API api = retrofit.create(API.class);
		Call<ResponseBody> call = api.getBingToday(API.APP_ID,API.APP_SIGN);
		call.enqueue(new Callback<ResponseBody>(){
			@Override
			public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
				try {
					JSONObject object = new JSONObject(response.body().string());
					//Log.i(TAG,"onResponse: "+object.toString());
					if(!object.getString("showapi_res_code").equals("0"))
					{
						listener.onError("获取错误！");
						return;
					}
					BingToday bingToday = new Gson().fromJson(object.getJSONObject("showapi_res_body").getString("data"),new TypeToken<BingToday>(){}.getType());
					listener.onSuccess(bingToday);
				} catch(IOException e) {
					e.printStackTrace();
					listener.onError("获取错误: "+e.getMessage());
				} catch(JSONException e) {
					e.printStackTrace();
					listener.onError("获取错误: "+e.getMessage());
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call,Throwable t){
				listener.onError("获取错误: "+t.getMessage());
			}
		});
	}
}
