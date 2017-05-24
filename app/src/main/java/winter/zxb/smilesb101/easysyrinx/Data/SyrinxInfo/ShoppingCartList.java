package winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo;

import android.util.Log;

import java.util.ArrayList;

/**
 * 项目名称：EasySyrinx
 * 类描述：购物车列表
 * 创建人：SmileSB101
 * 创建时间：2017/5/18 0018 07:32
 * 修改人：Administrator
 * 修改时间：2017/5/18 0018 07:32
 * 修改备注：
 */

public class ShoppingCartList{
	public static final ShoppingCartList SHOPPING_CART_LIST = new ShoppingCartList();

	String TAG = "ShoppingCartList";

	ArrayList<ShoppingCart> shoppingCarts;

	/**
	 * 清空数据
	 */
	public void cleraShoppingCart()
	{
		shoppingCarts = new ArrayList<>();
	}


	public ArrayList<String> getSeriesArray()
	{
		ArrayList<String> titles = new ArrayList<>();
		for(ShoppingCart cart :shoppingCarts)
		{
			titles.add(cart.getItem(0).getSeries());
		}
		return titles;
	}

	public ArrayList<String> getAllSeriesArray()
	{
		ArrayList<String> titles = new ArrayList<>();
		for(ShoppingCart cart:shoppingCarts)
		{
			for(SyrinxItem item:cart.getSyrinxItemArrayList())
			{
				titles.add(item.getSeries());
			}
		}
		return titles;
	}

	public String getSeriesTitle(int index)
	{
		if(index>-1&&index<shoppingCarts.size())
		{
			return shoppingCarts.get(index).getItem(0).getSeries();
		}
		return "";
	}

	public ArrayList<ShoppingCart> getShoppingCarts(){
		return shoppingCarts;
	}

	public ShoppingCart getShoppingCart(int index)
	{
		if(index > shoppingCarts.size())
		{
			return null;
		}
		return shoppingCarts.get(index);
	}

	/**
	 * 根据系列名称来获取在此表中位置
	 * @param name 系列名称
	 * @return gps_gray -1则为没有找到
	 */
	public int getIndexByCartName(String name)
	{
		for(int i = 0;i<shoppingCarts.size();i++)
		{
			ShoppingCart cart = shoppingCarts.get(i);
			if(cart.getCartName().equals(name))
			{
				return i;
			}
		}
		return -1;
	}

	public void setShoppingCarts(ArrayList<ShoppingCart> shoppingCarts){
		this.shoppingCarts = shoppingCarts;
	}

	public void addShoppingCart(ShoppingCart cart)
	{
		if(shoppingCarts==null)
		{
			shoppingCarts = new ArrayList<>();
		}
		shoppingCarts.add(cart);
	}

	public void removeShoppingCart(ShoppingCart cart)
	{
		if(shoppingCarts==null)
		{
			return ;
		}
		shoppingCarts.remove(cart);
	}

	public void addItemNum(SyrinxItem item)
	{
		for(ShoppingCart cart:shoppingCarts)
		{
			int index = cart.getItemNum(item);
			if(index!=-1)
			{
				cart.addSyrinxItem(item);
				return ;
			}
		}
	}

	public void reduceItemNum(SyrinxItem item)
	{
		for(ShoppingCart cart:shoppingCarts)
		{
			int index = cart.getItemNum(item);
			if(index!=-1)
			{
				cart.reduceSyrinxItem(item);
				return ;
			}
		}
	}

	public void removeShoppingCart(int index)
	{
		if(shoppingCarts.size()<index)
		{
			return ;
		}
		shoppingCarts.remove(index);

	}

	public ShoppingCartList()
	{
		shoppingCarts = new ArrayList<>();
	}

	/**
	 * 获取所有item数量（重复希芸都要算）
	 * @return
	 */
	public int getAllNum()
	{
		int num = 0;
		for(ShoppingCart cart:shoppingCarts)
		{
			num+=cart.getAllItemNum();
		}
		return num;
	}

	public Float getSyrinxItemPrice(int index)
	{
		int num = 0;
		for(ShoppingCart cart:shoppingCarts)
		{
			int temp = num + cart.getNum();
			if(temp>index)
			{
				return cart.getItemPrice(index-num);
			}
			num = temp;
		}
		return 0f;
	}

	/**
	 * 获取希芸物品的条目数量（重复条目只算第一次）
	 * @return
	 */
	public int getSyrinxItemNum()
	{
		int num = 0;
		for(ShoppingCart cart:shoppingCarts)
		{
			num+=cart.getNum();
		}
		return num;
	}

	/**
	 * 通过item获取item num
	 * @param item
	 * @return
	 */
	public int getSyrinxItemNum(SyrinxItem item)
	{
		for(ShoppingCart cart:shoppingCarts)
		{
			int num = cart.getItemNum(item);
			if(num>-1)
			{
				return num;
			}
		}
		return -1;
	}
	public int getSyrinxItemNumByName(String name)
	{
		for(ShoppingCart cart:shoppingCarts)
		{
			int num = cart.getItemNum(name);
			if(num>-1)
			{
				return num;
			}
		}
		return -1;
	}

	public int getListNum()
	{
		return shoppingCarts.size();
	}

	public float getPrices()
	{
		float prices = 0;
		for(ShoppingCart cart:shoppingCarts)
		{
			prices+=cart.getPrice();
		}
		return prices;
	}

	/**
	 * 检查自己的ShoppingCart中是否有为0的条目，有就删除之
	 */
	public void checkSelfList()
	{
		for(ShoppingCart cart:shoppingCarts)
		{
			if(cart.getNum()==0)
			{
				shoppingCarts.remove(cart);
			}
		}
	}

	public SyrinxItem getSyrinxItem(int index)
	{
		//Log.e("购物车表","getSyrinxItem: Index："+index);
		if(index < getSyrinxItemNum()) {
			int num = 0;
			for(ShoppingCart cart : shoppingCarts) {
				int temp = num + cart.getNum();
				if(temp > index) {
					//在这个里面
					return cart.getItem(index - num);
				}
				//不在这个里面
				num = temp;
			}
		}
		return null;
	}

	public int getSyrinxItemNum(int index)
	{
		if(index < getSyrinxItemNum()) {
			int num = 0;
			for(ShoppingCart cart : shoppingCarts) {
				int temp = num + cart.getNum();
				if(temp > index) {
					//在这个里面
					return cart.getItemNum(index - num);
				}
				//不在这个里面
				num = temp;
			}
		}
		return 0;
	}

}
