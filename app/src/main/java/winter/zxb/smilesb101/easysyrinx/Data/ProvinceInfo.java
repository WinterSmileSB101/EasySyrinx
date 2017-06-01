package winter.zxb.smilesb101.easysyrinx.Data;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 项目名称：EasySyrinx
 * 类描述：省份信息
 * 创建人：SmileSB101
 * 创建时间：2017/5/26 0026 09:29
 * 修改人：Administrator
 * 修改时间：2017/5/26 0026 09:29
 * 修改备注：
 */

public class ProvinceInfo extends DataSupport implements Serializable{
	String name;
	int id;
	int own_id;

	public ProvinceInfo(String name,int id,int own_id){
		this.name = name;
		this.id = id;
		this.own_id = own_id;
	}

	public int getOwn_id(){
		return own_id;
	}

	public void setOwn_id(int own_id){
		this.own_id = own_id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}
}
