package winter.zxb.smilesb101.easysyrinx.Data;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 项目名称：EasySyrinx
 * 类描述：用户地址的bean类
 * 创建人：SmileSB101
 * 创建时间：2017/5/24 0024 21:21
 * 修改人：Administrator
 * 修改时间：2017/5/24 0024 21:21
 * 修改备注：
 */

public class UserAddressInfo extends DataSupport implements Serializable{
	String userName;
	String userPhone;
	boolean isDefault;
	String userAddress;

	public UserAddressInfo(){
	}

	public UserAddressInfo(String userName,String userPhone,boolean isDefault,String userAddress){
		this.userName = userName;
		this.userPhone = userPhone;
		this.isDefault = isDefault;
		this.userAddress = userAddress;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserPhone(){
		return userPhone;
	}

	public void setUserPhone(String userPhone){
		this.userPhone = userPhone;
	}

	public boolean isDefault(){
		return isDefault;
	}

	public void setDefault(boolean aDefault){
		isDefault = aDefault;
	}

	public String getUserAddress(){
		return userAddress;
	}

	public void setUserAddress(String userAddress){
		this.userAddress = userAddress;
	}

	public boolean equals(UserAddressInfo obj)
	{
		if(obj!=null
		&&userName.equals(obj.getUserName())
				&&userPhone.equals(obj.getUserPhone())
				&&userAddress.equals(obj.getUserAddress()))
		{
			return true;
		}
		return false;
	}
}
