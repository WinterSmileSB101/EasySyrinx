package winter.zxb.smilesb101.easysyrinx.Logic.DataRead;

import android.util.Log;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 项目名称：EasySyrinx
 * 类描述：数据读取帮助类
 * 创建人：SmileSB101
 * 创建时间：2017/2/5 0005 15:23
 * 修改人：Administrator
 * 修改时间：2017/2/5 0005 15:23
 * 修改备注：
 */

public class DataReadHelp{

	public static final String FILETYPE_XLS = "xls";
	public static final String FILETYPE_XLSX = "xlsx";

	private static final String TAG = "DataReadHelp.java";
	/**
	 * 读取excel文件的内容（版本或许有限制97-03 xls文件）
	 * @param file 需要读取的excel文件
	 * @return json字符串（键值对间由:分隔，不同键值对由,分隔，数组由{}分隔）
	 */
	public static String ReadExcelFile(File file)
	{
		String result = "";
		try
		{
			StringBuilder sb = new StringBuilder();
			jxl.Workbook workBook = Workbook.getWorkbook(file);
			Sheet[] sheets = workBook.getSheets();
			for(Sheet s : sheets)
			{
				for(int i = 0;i<s.getRows();i++)
				{
					Cell[] cells = s.getRow(i);
					for(int j = 0;j<cells.length;j++)
					{
						sb.append(cells[j].getContents()+":");
					}
					sb.replace(sb.lastIndexOf(":"),sb.length(),",");
				}
			}
			sb.replace(sb.lastIndexOf(":"),sb.length(),"");
			result = sb.toString();
		}
		catch(Exception e)
		{
			Log.i(TAG,"ReadExcelFile: ERROR: "+e.getMessage());
		}
		return result;
	}

	/**
	 * 通过POI类库读取Excel文件
	 * @param file 文件
	 * @param fileType 文件类型（xls或者xlsx）
	 * @return json字符串
	 */
	public static String readExcelByPOI(File file,String fileType)
	{
		String result = "";
		/*try {
			InputStream ios = new FileInputStream(file);

			org.apache.poi.ss.usermodel.Workbook workbook = null;
			switch(fileType) {
				case FILETYPE_XLS:
					//workbook = new HSSFWorkbook(ios);
					break;
				case FILETYPE_XLSX:
					workbook = new XSSFWorkbook(ios);
					break;
				default:
					return result;
			}
			if(workbook!=null)
			{

			}
		}catch(FileNotFoundException e) {
			Log.i(TAG,"readExcelByPOI: 文件不存在！");
		} catch(IOException e) {
			e.printStackTrace();
		}*/
		return result;
	}

	public static boolean paseXMLToJsonWithPull()
	{
		boolean flag = false;
		return flag;
	}

	public static String paseJsonToXMLWithPull(String filePath,String fileName,String jsonStr)
	{
		String result = "";
		return result;
	}

}
