package winter.zxb.smilesb101.easysyrinx.Logic.DataRead;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 项目名称：EasySyrinx
 * 类描述：Gson工具类
 * 创建人：SmileSB101
 * 创建时间：2017/2/6 0006 14:26
 * 修改人：Administrator
 * 修改时间：2017/2/6 0006 14:26
 * 修改备注：
 */

public class GsonUtils{
	/**
	 * 映射Jsondata到指定实体类
	 * @param jsonData json数据
	 * @param type 实体类定义
	 * @param <T> 类型
	 * @return
	 */
	public static <T> T parseJsonWithGson(String jsonData,Class<T> type)
	{
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData,type);
		return result;
	}

	/**
	 * 把Json数据映射成对象数组
	 * @param jsonData json数据
	 * @param type 实体类定义
	 * @param <T> 类型
	 * @return
	 */
	public static <T> List<T> paseJsonArrayWithGson(String jsonData,Class<T> type)
	{
		Gson gson = new Gson();
		List<T>  resultArray = gson.fromJson(jsonData,new TypeToken<List<T>>(){}.getType());
		return resultArray;
	}
}
