package winter.zxb.smilesb101.easysyrinx.Net;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import winter.zxb.smilesb101.easysyrinx.Data.CityInfo;
import winter.zxb.smilesb101.easysyrinx.Data.ProvinceInfo;
import winter.zxb.smilesb101.easysyrinx.Data.TownInfo;

/**
 * 项目名称：EasySyrinx
 * 类描述：网络工具类
 * 创建人：SmileSB101
 * 创建时间：2017/5/26 0026 09:27
 * 修改人：Administrator
 * 修改时间：2017/5/26 0026 09:27
 * 修改备注：
 */

public class HttpWork{

	static String TAG = "HttpWork";

	public interface ProvinceListener{
		void onSuccess(List<ProvinceInfo> infoList);
		void onError(String error);
	}
	public interface CityListener{
		void onSuccess(List<CityInfo> infoList);
		void onError(String error);
	}
	public interface TownListener{
		void onSuccess(List<TownInfo> infoList);
		void onError(String error);
	}

	/**
	 * 获取国内省份信息并且存入数据库（或者直接从数据库中读取返回）
	 * @return
	 */
	public static void getChinaProvince(final ProvinceListener listener)
	{
		final SQLiteDatabase db = Connector.getDatabase();
		if(db!=null)
		{
			DataSupport.deleteAll(ProvinceInfo.class);
//			DataSupport.deleteAll(CityInfo.class);
//			DataSupport.deleteAll(TownInfo.class);
			List<ProvinceInfo> infos = DataSupport.findAll(ProvinceInfo.class);
			if(infos!=null&&infos.size()>0)
			{
				listener.onSuccess(infos);
			}
			else
			{
				//不存在，通过网络访问获取并且存入数据库
				Retrofit retrofit = new Retrofit.Builder()
						.baseUrl("http://guolin.tech/api/")
						.build();
				API api = retrofit.create(API.class);
				Call<ResponseBody> call = api.getChinaProvinces();
					call.enqueue(new Callback<ResponseBody>(){
						@Override
						public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
							String s = null;
							try {
								s = response.body().string();
								List<ProvinceInfo> infoList = new Gson().fromJson(s,new TypeToken<List<ProvinceInfo>>(){}.getType());
								//存入数据库
								for(int i = 0;i<infoList.size();i++)
								{
									ProvinceInfo info = infoList.get(i);
									info.setOwn_id(info.getId());
									infoList.set(i,info);
								}
								DataSupport.saveAll(infoList);//保存所有数据到数据库
								listener.onSuccess(infoList);
							} catch(IOException e) {
								e.printStackTrace();
							}
						}

						@Override
						public void onFailure(Call<ResponseBody> call,Throwable t){
							listener.onError(t.getMessage().toString());
						}
					});
			}
		}
	}

	/**
	 * 获取国内城市信息并且存入数据库（或者直接从数据库中读取返回）
	 * @return
	 */
	public static void getChinaCity(int id,final CityListener listener)
	{
		final SQLiteDatabase db = Connector.getDatabase();
		if(db!=null)
		{
			//DataSupport.deleteAll(CityInfo.class);
			List<CityInfo> infos = DataSupport.findAll(CityInfo.class);
			if(infos!=null&&infos.size()>0) {
				Log.e(TAG,"getChinaCity: 数据库中存在，返回");
				listener.onSuccess(infos);

			}
			else {
				//不存在，通过网络访问获取并且存入数据库
				Retrofit retrofit = new Retrofit.Builder()
						.baseUrl("http://guolin.tech/api/")
						.build();
				API api = retrofit.create(API.class);
				Call<ResponseBody> call = api.getCitys(id);
				call.enqueue(new Callback<ResponseBody>(){
					@Override
					public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
						String s = null;
						try {
							s = response.body().string();
							List<CityInfo> infoList = new Gson().fromJson(s,new TypeToken<List<CityInfo>>(){
							}.getType());
							//存入数据库
							for(int i = 0;i<infoList.size();i++)
							{
								CityInfo info = infoList.get(i);
								info.setOwn_id(info.getId());
								infoList.set(i,info);
							}
							DataSupport.saveAll(infoList);//保存所有数据到数据库
							Log.e(TAG,"getChinaCity: 通过网络访问，返回");
							listener.onSuccess(infoList);
						} catch(IOException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(Call<ResponseBody> call,Throwable t){
						listener.onError(t.getMessage().toString());
					}
				});
			}
		}
	}

	/**
	 * 获取国内城市信息并且存入数据库（或者直接从数据库中读取返回）
	 * @return
	 */
	public static void getChinaCity(int provinceId,int cityId,final TownListener listener)
	{
		final SQLiteDatabase db = Connector.getDatabase();
		if(db!=null)
		{
			//DataSupport.deleteAll(TownInfo.class);
			List<TownInfo> infos = DataSupport.findAll(TownInfo.class);
			if(infos!=null&&infos.size()>0)
			{
				listener.onSuccess(infos);
			}
			else {
				//不存在，通过网络访问获取并且存入数据库
				Retrofit retrofit = new Retrofit.Builder()
						.baseUrl("http://guolin.tech/api/")
						.build();
				API api = retrofit.create(API.class);
				Call<ResponseBody> call = api.getTown(provinceId,cityId);
				call.enqueue(new Callback<ResponseBody>(){
					@Override
					public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
						String s = null;
						try {
							s = response.body().string();
							List<TownInfo> infoList = new Gson().fromJson(s,new TypeToken<List<TownInfo>>(){
							}.getType());
							//存入数据库
							for(int i = 0;i<infoList.size();i++)
							{
								TownInfo info = infoList.get(i);
								info.setOwn_id(info.getId());
								infoList.set(i,info);
							}
							DataSupport.saveAll(infoList);//保存所有数据到数据库
							listener.onSuccess(infoList);
						} catch(IOException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(Call<ResponseBody> call,Throwable t){
						listener.onError(t.getMessage().toString());
					}
				});
			}
		}
	}

}
