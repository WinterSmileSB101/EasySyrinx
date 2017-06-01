package winter.zxb.smilesb101.easysyrinx.Data;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 项目名称：EasySyrinx
 * 类描述：乡镇信息
 * 创建人：SmileSB101
 * 创建时间：2017/5/26 0026 10:25
 * 修改人：Administrator
 * 修改时间：2017/5/26 0026 10:25
 * 修改备注：
 */

public class TownInfo extends DataSupport implements Serializable{
	int id;
	int own_id;
	String name;
	String weather_id;

	public TownInfo(int id,int own_id,String name,String weather_id){
		this.id = id;
		this.own_id = own_id;
		this.name = name;
		this.weather_id = weather_id;
	}

	public int getOwn_id(){
		return own_id;
	}

	public void setOwn_id(int own_id){
		this.own_id = own_id;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getWeather_id(){
		return weather_id;
	}

	public void setWeather_id(String weather_id){
		this.weather_id = weather_id;
	}
}
