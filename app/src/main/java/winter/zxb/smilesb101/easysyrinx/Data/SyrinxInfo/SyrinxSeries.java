package winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo;

import java.io.Serializable;

/**
 * 项目名称：EasySyrinx
 * 类描述：希芸系列类
 * 创建人：SmileSB101
 * 创建时间：2017/2/2 0002 20:45
 * 修改人：Administrator
 * 修改时间：2017/2/2 0002 20:45
 * 修改备注：
 */

public class SyrinxSeries implements Serializable{
	private String name;
	private String code;
	private int imageId;
	private int num;
	private boolean freePost;

	public SyrinxSeries(){
		freePost = false;
		num = 0;
		code = "0";
		name = "";
	}

	public SyrinxSeries(String name,String code,int imageId,int num,boolean freePost){
		this.name = name;
		this.code = code;
		this.imageId = imageId;
		this.num = num;
		this.freePost = freePost;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public int getImageUrl(){
		return imageId;
	}

	public void setImageUrl(int imageId){
		this.imageId = imageId;
	}

	public String isFreePost(){
		return freePost?"包邮":"不包邮";
	}

	public void setFreePost(boolean freePost){
		this.freePost = freePost;
	}

	public int getNum(){
		return num;
	}

	public void setNum(int num){
		this.num = num;
	}
}
