package winter.zxb.smilesb101.easysyrinx.Data;

/**
 * 项目名称：CoderHome
 * 类描述：今日必应的图片Bean类
 * 创建人：SmileSB101
 * 创建时间：2017/6/1 0001 08:07
 * 修改人：Administrator
 * 修改时间：2017/6/1 0001 08:07
 * 修改备注：
 */

public class BingToday{
	String title;
	String img_1920;
	String description;
	String subtitle;
	String copyright;
	String date;
	String img_1366;

	public BingToday(String title,String img_1920,String description,String subtitle,String copyright,String date,String img_1366){
		this.title = title;
		this.img_1920 = img_1920;
		this.description = description;
		this.subtitle = subtitle;
		this.copyright = copyright;
		this.date = date;
		this.img_1366 = img_1366;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getImg_1920(){
		return img_1920;
	}

	public void setImg_1920(String img_1920){
		this.img_1920 = img_1920;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getSubtitle(){
		return subtitle;
	}

	public void setSubtitle(String subtitle){
		this.subtitle = subtitle;
	}

	public String getCopyright(){
		return copyright;
	}

	public void setCopyright(String copyright){
		this.copyright = copyright;
	}

	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getImg_1366(){
		return img_1366;
	}

	public void setImg_1366(String img_1366){
		this.img_1366 = img_1366;
	}
}
