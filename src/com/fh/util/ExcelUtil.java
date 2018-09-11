package com.fh.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 导入导出Excel
 * @author wanghuan
 *
 */
public class ExcelUtil {
	 /**
     * 数据文件导入
     * 
     * @param request
     * @param response
	 * @return 
     * @throws IOException
     * @throws FileNotFoundException
     */
    public List<Map<String, ?>> importOCRExcelData(HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException
    {
        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;  
        MultipartFile file = mulRequest.getFile("excelFile");  
        String iphoneCol=request.getParameter("iphoneCol");		//手机号是第几列如果没有写999
        if(StringUtils.isNullOrEmpty(iphoneCol)){
        	iphoneCol="999";
        }
        String filename = file.getOriginalFilename();  
        if (filename == null || "".equals(filename))  
        {  
        }  
        try  
        {  
            InputStream input = file.getInputStream();  
            //excel2007以后的版本格式xlsx
            XSSFWorkbook workBook = new XSSFWorkbook(input);
            
            List<Map<String,?>> sfList = exportListFromExcel(workBook, 0,Integer.parseInt(iphoneCol));
            return sfList;
        }  
        catch (Exception e)  
        {   
        	InputStream input = file.getInputStream(); 
        	//excel2007以前的版本xls
	   	 	HSSFWorkbook hssWB = new HSSFWorkbook(input);
	   	 	List<Map<String,?>> sfList = exportListFromExcel(hssWB, 0,Integer.parseInt(iphoneCol));
   	 		return sfList;
        }
		
}



/**
     * 由指定的Sheet导出至List
     * 
     * @param workbook
     * @param sheetNum
     * @return
     * @throws IOException
     */
    public static List<Map<String,?>> exportListFromExcel(Workbook workbook,
            int sheetNum,int iphoneCol)
    {

        Sheet sheet = workbook.getSheetAt(sheetNum);

        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper()
                .createFormulaEvaluator();

        List<Map<String,?>> list = new ArrayList<Map<String,?>>();

        int minRowIx = sheet.getFirstRowNum();
        int maxRowIx = sheet.getLastRowNum();
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++)
        {
        	Map map=new HashMap();
            Row row = sheet.getRow(rowIx);
            StringBuilder sb = new StringBuilder();

            short minColIx = row.getFirstCellNum();
            short maxColIx = row.getLastCellNum();
            for (short colIx = minColIx; colIx <= maxColIx; colIx++)
            {
                Cell cell = row.getCell(new Integer(colIx));
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue == null)
                {
                    continue;
                }
                Object result = null;
                switch (cell.getCellType())
                {
                   case Cell.CELL_TYPE_BOOLEAN:
                       result = cell.getBooleanCellValue();
                    break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if (HSSFDateUtil.isCellDateFormatted(cell))
                        {   
                            // 处理日期格式、时间格式
                            SimpleDateFormat sdf = null;
                            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm"))
                            {
                                sdf = new SimpleDateFormat("HH:mm");
                            }
                            else if(cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm:ss"))
                            {
                                sdf = new SimpleDateFormat("HH:mm:ss");
                            }
                            else
                            {
                                sdf = new SimpleDateFormat("yyyy-MM-dd");
                            }
                            Date date = cell.getDateCellValue();
                            result = sdf.format(date);
                        }
                        else if (cell.getCellStyle().getDataFormat() == 58)
                        {
                            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            double value = cell.getNumericCellValue();
                            Date date = org.apache.poi.ss.usermodel.DateUtil
                                    .getJavaDate(value);
                            result = sdf.format(date);
                        }
                        else
                        {
                        	
                        	 if(colIx==iphoneCol-1){
    	                         /**
    	                          * 处理：使用POI读excel文件，当遇到特殊格式的字串，比如“13612345678”，等等，
    	                          * 这样的本来是一个字符串，但是POI在读的时候总是以数值型识别，由此，这样的电话号码读出来后总是1.3XXX+E4
    	                          */
    	                        DecimalFormat df = new DecimalFormat("#");
    	                       String  cellValue2=df.format(cell.getNumericCellValue());
    	                       result=cellValue2;
    	                     
    	                         
    	                     }else if(colIx!=iphoneCol-1||iphoneCol==999){
    	                    	 double value = cell.getNumericCellValue();
 	                            CellStyle style = cell.getCellStyle();
 	                            DecimalFormat format = new DecimalFormat();
 	                            String temp = style.getDataFormatString();
 	                            // 单元格设置成常规
 	                            if (temp.equals("General"))
 	                            {
 	                               // format.applyPattern("#");
 	                            }
 	                            result = format.format(value);
    	                     }
                        	
                        	
                        	
                        	
                           
                            
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        result = cell.getRichStringCellValue().toString();
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    default:
                        break;
                }
                map.put("col"+colIx, result);
            }
            list.add(map);
        }
        return list;
    }
   
    /**
     * 创建excel文档，
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<Map<String, Object>> list,String []keys,String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

//        Font f3=wb.createFont();
//        f3.setFontHeightInPoints((short) 10);
//        f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }
}
