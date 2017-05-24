package winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo;

import android.support.annotation.IntegerRes;

import java.util.ArrayList;

/**
 * 项目名称：EasySyrinx
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/5/17 0017 21:12
 * 修改人：Administrator
 * 修改时间：2017/5/17 0017 21:12
 * 修改备注：
 */

public class ShoppingCart{

	String cartName;
	ArrayList<SyrinxItem> syrinxItemArrayList;
	ArrayList<Integer> itemNum;
	ArrayList<Float> itemPrice;
	float price;

	public ShoppingCart()
	{
		syrinxItemArrayList = new ArrayList<>();
		itemNum = new ArrayList<>();
		itemPrice = new ArrayList<>();
		price = 0;
		cartName="";
	}

	public void addSyrinxItem(SyrinxItem syrinxItem)
	{
		if(syrinxItem!=null) {
			if(!syrinxItemArrayList.contains(syrinxItem)) {
				syrinxItemArrayList.add(syrinxItem);
				itemNum.add(1);
				itemPrice.add(syrinxItem.getOut_price());
				return ;
			}
			else{
				int index = syrinxItemArrayList.indexOf(syrinxItem);
				itemNum.set(index,itemNum.get(index)+1);
				itemPrice.set(index,itemPrice.get(index)+syrinxItem.getOut_price());
			}
		}
	}

	public void reduceSyrinxItem(SyrinxItem item)
	{
		int index = syrinxItemArrayList.indexOf(item);
		if(index!=-1)
		{
			int num = itemNum.get(index);
			num--;
			if(num<0)
			{
				itemNum.remove(index);
				itemPrice.remove(index);
				syrinxItemArrayList.remove(index);
			}
			else
			{
				itemNum.set(index,num);
				reducePrices(item.getOut_price());
				itemPrice.set(index,itemPrice.get(index) - item.getOut_price());
			}
		}
	}

	public void removeSyrinxItem(SyrinxItem syrinxItem)
	{
		if(syrinxItem!=null)
		{
			syrinxItemArrayList.remove(syrinxItem);
			int index = syrinxItemArrayList.indexOf(syrinxItem);
			if(index!=-1) {
				itemNum.set(index,itemNum.get(index) - 1);
				itemPrice.set(index,itemPrice.get(index) - syrinxItem.getOut_price());
				if(itemNum.get(index) == 0) {
					itemNum.remove(index);
				}

				if(itemPrice.get(index) <= 0) {
					itemPrice.remove(index);
				}
			}
		}
	}

	public void addPrices(float price)
	{
		if(price > 0)
		{
			this.price+=price;
		}
	}

	public void reducePrices(float price)
	{
		if(price>0)
		{
			this.price-=price;
			if(this.price<0)
			{
				this.price = 0;
			}
		}
	}

	public ArrayList<SyrinxItem> getSyrinxItemArrayList(){
		return syrinxItemArrayList;
	}

	public void setSyrinxItemArrayList(ArrayList<SyrinxItem> syrinxItemArrayList){
		if(syrinxItemArrayList!=null) {
			this.syrinxItemArrayList = syrinxItemArrayList;
		}
	}

	public float getPrice(){
		return price;
	}

	public void setPrice(float price){
		this.price = price;
	}

	public int getNum()
	{
		if(syrinxItemArrayList!=null) {
			return syrinxItemArrayList.size();
		}
		return 0;
	}

	public int getAllItemNum()
	{
		int num = 0;
		if(syrinxItemArrayList!=null)
		{
			for(SyrinxItem item:syrinxItemArrayList)
			{
				num+=getItemNum(item);
			}
		}
		return num;
	}

	public String getCartName(){
		return cartName;
	}

	public void setCartName(String cartName){
		this.cartName = cartName;
	}

	public SyrinxItem getItem(int index)
	{
		if(syrinxItemArrayList.size()>index) {
			return syrinxItemArrayList.get(index);
		}
		return null;
	}

	public Integer getItemNum(SyrinxItem item){
		if(item!=null) {
			int index = syrinxItemArrayList.indexOf(item);
			if(index!=-1) {
				return itemNum.get(index);
			}
		}
		return -1;
	}

	public int getItemNum(String name)
	{
		for(SyrinxItem item :syrinxItemArrayList)
		{
			if(item.getName().equals(name))
			{
				return itemNum.get(syrinxItemArrayList.indexOf(item));
			}
		}
		return -1;
	}

	public Integer getItemNum(int index){
		if(index>=0&&index<itemNum.size()) {
			return itemNum.get(index);
		}
		return 0;
	}

	public Float getItemPrice(int index)
	{
		if(index>=0&&index<itemNum.size()) {
			return itemPrice.get(index);
		}
		return 0f;
	}

	public Float getItemPrice(SyrinxItem item){
		if(item!=null) {
			int index = syrinxItemArrayList.indexOf(item);
			return itemPrice.get(index);
		}
		return 0f;
	}
}
