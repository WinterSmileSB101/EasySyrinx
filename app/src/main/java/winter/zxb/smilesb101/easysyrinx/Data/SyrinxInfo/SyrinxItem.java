package winter.zxb.smilesb101.easysyrinx.Data.SyrinxInfo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 项目名称：EasySyrinx
 * 类描述：希芸物品信息类
 * 创建人：SmileSB101
 * 创建时间：2017/2/1 0001 22:29
 * 修改人：Administrator
 * 修改时间：2017/2/1 0001 22:29
 * 修改备注：
 */

public class SyrinxItem implements Serializable{
	/**
	 * 用于进行键值对封装时作为键
	 */
	public final static String NAME_STRING = "name";
	public final static String CODE_STRING = "code";
	public final static String KIND_STRING = "kind";
	public final static String SERIES_STRING = "series";
	public final static String IN_PRICE_STRING = "in_price";
	public final static String OUT_PRICE_STRING = "out_price";
	public final static String WORD_PRICES_STRING = "wordPrice";
	public final static String INTRODUCE_STRING = "introduce";
	public final static String TAGLIST_STRING = "tagList";
	public final static String FREEPOST_STRING = "freePost";
	public final static String ITEMEFFECTS_STRING = "itemEffects";
	public final static String TIPS_STRING = "tips";
	public final static String HOWTOUSE_STRING = "howToUse";
	public final static String MAJORCONTENT_STRING = "majorContent";


	private String name;
	private String code;
	private String kind;
	private String format;
	private String series;
	private float in_price;
	private float out_price;
	private String introduce;
	private ArrayList<String> tagList;
	private boolean freePost;
	private int imageId;
	private float wordPrice;
	private int useImageID;

	private String itemEffects;
	private String tips;
	private ArrayList<String> howToUse_title;
	private ArrayList<String> howToUse;
	private ArrayList<String> majorContent;
	private ArrayList<String> majorContent_title;

	public SyrinxItem(){
		this.tagList = new ArrayList<>();
		howToUse = new ArrayList<>();
		majorContent = new ArrayList<>();
		this.in_price = 0;
		this.out_price = 0;
		this.wordPrice = 0;
		this.useImageID = 0;
		freePost = false;
	}

	/**
	 * 全参数构造方法
	 * @param code 商品编号
	 * @param name 商品名称
	 * @param kind 商品种类
	 * @param format 商品规格
	 * @param series 商品系列
	 * @param in_price 商品
	 * @param out_price 卖价
	 * @param introduce 介绍
	 * @param tagList 标签列表
	 * @param freePost 包邮
	 */
	public SyrinxItem(String code,String name,String kind,String format,String series,float in_price,float out_price,float wordPrice,String introduce,ArrayList<String> tagList,boolean freePost,int imageId,String itemEffects,String tips,ArrayList<String> howToUse_title,ArrayList<String> howToUse,int useImageID,ArrayList<String> majorContent_title,ArrayList<String> majorContent){
		this.code = code;
		this.name = name;
		this.kind = kind;
		this.format = format;
		this.series = series;
		this.in_price = in_price;
		this.out_price = out_price;
		this.wordPrice = wordPrice;
		this.introduce = introduce;
		this.tagList = tagList;
		this.freePost = freePost;
		this.imageId = imageId;
		this.itemEffects = itemEffects;
		this.tips = tips;
		this.howToUse_title = howToUse_title;
		this.howToUse = howToUse;
		this.useImageID = useImageID;
		this.majorContent_title = majorContent_title;
		this.majorContent = majorContent;
	}

	public float getWordPrice(){
		return wordPrice;
	}

	public void setWordPrice(float wordPrice){
		this.wordPrice = wordPrice;
	}

	public int getUseImageID(){
		return useImageID;
	}

	public void setUseImageID(int useImageID){
		this.useImageID = useImageID;
	}

	public ArrayList<String> getHowToUse_title(){
		return howToUse_title;
	}

	public void setHowToUse_title(ArrayList<String> howToUse_title){
		this.howToUse_title = howToUse_title;
	}

	public ArrayList<String> getMajorContent_title(){
		return majorContent_title;
	}

	public void setMajorContent_title(ArrayList<String> majorContent_title){
		this.majorContent_title = majorContent_title;
	}

	public String getItemEffects(){
		return itemEffects;
	}

	public void setItemEffects(String itemEffects){
		this.itemEffects = itemEffects;
	}

	public String getTips(){
		return tips;
	}

	public void setTips(String tips){
		this.tips = tips;
	}

	public ArrayList<String> getHowToUse(){
		return howToUse;
	}

	public void setHowToUse(ArrayList<String> howToUse){
		this.howToUse = howToUse;
	}

	public ArrayList<String> getMajorContent(){
		return majorContent;
	}

	public void setMajorContent(ArrayList<String> majorContent){
		this.majorContent = majorContent;
	}

	public int getImageId(){
		return imageId;
	}

	public void setImageId(int imageId){
		this.imageId = imageId;
	}

	public String getFormat(){
		return format;
	}

	public void setFormat(String format){
		this.format = format;
	}

	/**
	 * 获取是否包邮
	 * @return
	 */
	public String isFreePost(){
		return freePost?"包邮":"不包邮";
	}

	/**
	 * 设置是否包邮
	 * @param freePost
	 */
	public void setFreePost(boolean freePost){
		this.freePost = freePost;
	}

	/**
	 * 获取系列名称
	 * @return
	 */
	public String getSeries(){
		return series;
	}

	/**
	 * 设置系列名称
	 * @param series
	 */
	public void setSeries(String series){
		this.series = series;
	}

	/**
	 * 获取类型（商品颜色或者分类等）
	 * @return
	 */
	public String getKind(){
		return kind;
	}

	/**
	 * 设置类型（商品颜色或者分类等）
	 * @param kind
	 */
	public void setKind(String kind){
		this.kind = kind;
	}

	/**
	 * 获取卖出价格
	 * @return
	 */
	public float getOut_price(){
		return out_price;
	}

	/**
	 * 设置卖出价格
	 * @param out_price
	 */
	public void setOut_price(float out_price){
		this.out_price = out_price;
	}

	/**
	 * 获取物品名称
	 * @return 物品名称
	 */
	public String getName(){
		return name;
	}

	/**
	 * 设置物品名称
	 * @param name 物品民称
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 获取商品编号
	 * @return 商品编号
	 */
	public String getCode(){
		return code;
	}

	/**
	 * 设置商品编号
	 * @param code
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * 获取商品进价
	 * @return
	 */
	public float getIn_price(){
		return in_price;
	}

	/**
	 * 设置商品进价
	 * @param in_price
	 */
	public void setIn_price(float in_price){
		this.in_price = in_price;
	}

	/**
	 * 获取商品介绍
	 * @return
	 */
	public String getIntroduce(){
		return introduce;
	}

	/**
	 * 设置商品介绍
	 * @param introduce
	 */
	public void setIntroduce(String introduce){
		this.introduce = introduce;
	}

	/**
	 * 获取商品标签（特性）
	 * @return
	 */
	public ArrayList<String> getTagList(){
		return tagList;
	}

	/**
	 * 设置商品特性
	 * @param tagList
	 */
	public void setTagList(ArrayList<String> tagList){
		this.tagList = tagList;
	}

	/**
	 * 设置商品特性
	 * @param tag
	 */
	public void setTagList(String tag)
	{
		this.tagList.add(tag);
	}

	/**
	 * 获取此实例的json字符串
	 * @return
	 */
	public String ToJsonString()
	{
		return "Syrinx ["+NAME_STRING+"="+getName()+","
				+CODE_STRING+"="+getCode()+","
				+KIND_STRING+"="+getKind()+","
				+SERIES_STRING+"="+getSeries()+","
				+IN_PRICE_STRING+"="+getIn_price()+","
				+OUT_PRICE_STRING+"="+getOut_price()+","
				+INTRODUCE_STRING+"="+getIntroduce()+","
				+TAGLIST_STRING+"="+getTagList()+","
				+FREEPOST_STRING+"="+isFreePost()+","
				+ITEMEFFECTS_STRING+"="+getItemEffects()+","
				+TIPS_STRING+"="+getTips()+","
				+HOWTOUSE_STRING+"="+getHowToUse()+","
				+MAJORCONTENT_STRING+"="+getMajorContent()
				+"]";
	}
}
