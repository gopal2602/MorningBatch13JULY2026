package driver;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Calendar;

public class DriverClass {
    public static void main(String[] args) {
        Class cls = null;
        Object obj = null;
        Method script = null;
        int count = 0;
        try{
            int rowNum = getRowCount("ExecutionController", "Scripts");
            for(int r=0; r<rowNum; r++){
                String scriptName = getCellData("ExecutionController", "Scripts", "ScriptName", r+1);
                String className = getCellData("ExecutionController", "Scripts", "ClassName", r+1);
                String executeTest = getCellData("ExecutionController", "Scripts", "ExecuteTest", r+1);

                if(executeTest.equalsIgnoreCase("Yes")){
                    cls = Class.forName(className);
                    obj = cls.getDeclaredConstructor().newInstance();
                    script = obj.getClass().getMethod(scriptName);
                    script.invoke(obj);
                    count++;
                }
            }

            if(count==0){
                System.out.println("No Script is selcted for execution. Please select atleast one test Script");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cls = null;
            obj = null;
        }
    }


    //getRowCount()
    public static int getRowCount(String fileName, String sheetName){
        FileInputStream fin = null;
        Workbook wb = null;
        Sheet sh = null;
        try{
            fin = new FileInputStream(System.getProperty("user.dir") + "\\controller\\"+fileName+".xlsx");
            wb = new XSSFWorkbook(fin);
            sh = wb.getSheet(sheetName);
            if(sh==null){
                System.out.println("The sheet '"+sheetName+"' doesnot exist");
                return -1;
            }
            return sh.getPhysicalNumberOfRows()-1;
        }catch(Exception e){
            System.out.println("Exception in 'getRowCount()' method. "+e);
            return -1;
        }
        finally
        {
            try{
                fin.close();
                fin = null;
                wb.close();
                wb = null;
            }catch(Exception e){}
        }
    }



    //getCellData()
    public static String getCellData(String fileName, String sheetName, String columnName, int rowNum) {
        FileInputStream fin = null;
        Workbook wb = null;
        Sheet sh = null;
        Row row = null;
        Cell cell = null;
        int colNum = -1;
        String strValue = null;
        Calendar cal = null;
        String sDay = null;
        String sMonth = null;
        String sYear = null;
        try{
            fin = new FileInputStream(System.getProperty("user.dir") + "\\controller\\"+fileName+".xlsx");
            wb = new XSSFWorkbook(fin);
            sh = wb.getSheet(sheetName);
            if(sh==null){
                System.out.println("The sheet '"+sheetName+"' doesnot exist");
                return null;
            }

            //find the column number based on the column name
            row = sh.getRow(0);
            for(int c=0; c<row.getPhysicalNumberOfCells(); c++){
                cell = row.getCell(c);
                if(cell.getStringCellValue().equalsIgnoreCase(columnName)){
                    colNum = c;
                    break;
                }
            }

            if(colNum >= 0){
                row = sh.getRow(rowNum);
                cell = row.getCell(colNum);

                if(cell==null || cell.getCellType()== CellType.BLANK){
                    strValue = "";
                }else if(cell.getCellType()==CellType.STRING){
                    strValue = cell.getStringCellValue();
                }else if(cell.getCellType()==CellType.BOOLEAN){
                    strValue = String.valueOf(cell.getBooleanCellValue());
                }else if(cell.getCellType()==CellType.NUMERIC){
                    if(DateUtil.isCellDateFormatted(cell) == true){
                        double dt = cell.getNumericCellValue();
                        cal.setTime(DateUtil.getJavaDate(dt));

                        //If day is less than 10, then prefix with zero
                        if(cal.get(Calendar.DAY_OF_MONTH) < 10){
                            sDay = "0" +  cal.get(Calendar.DAY_OF_MONTH);
                        }else{
                            sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
                        }

                        //If month is less than 10, then prefix with zero
                        if((cal.get(Calendar.MONTH) +1) < 10){
                            sMonth = "0" +  (cal.get(Calendar.MONTH) +1);
                        }else{
                            sMonth = String.valueOf((cal.get(Calendar.MONTH) +1));
                        }

                        sYear = String.valueOf(cal.get(Calendar.YEAR));
                        strValue = sDay + "/" + sMonth + "/" + sYear;
                    }else{
                        strValue = String.valueOf(cell.getNumericCellValue());
                    }
                }
            }else{
                System.out.println("Failed to find the Column name '"+columnName+"' in the excel sheet '"+sheetName+"'");
                return null;
            }
            return strValue;
        }catch(Exception e){
            System.out.println("Exception in 'getCellData()' method. "+e);
            return null;
        }
        finally
        {
            try{
                fin.close();
                fin = null;
                cell = null;
                row = null;
                sh = null;
                wb.close();
                wb = null;
            }catch(Exception e){}
        }
    }

}
