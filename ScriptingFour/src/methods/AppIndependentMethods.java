package methods;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.FileInputStream;
import java.util.*;

public class AppIndependentMethods {
    /******************************************************************
     * Method Name          : launchBrowser()
     * Purpose              : to launch the required browsers viz., chrome/firefox/edge
     * Author               : sg Tester
     * Params               : String browserName
     * return Type          : WebDriver
     * Created Date         :
     * Modified Date        :
     * Reviewed By          :
     ******************************************************************/
    public static WebDriver launchBrowser(String browserName) {
        WebDriver oDriver = null;
        try{
            switch(browserName.toLowerCase()){
                case "chrome":
                    oDriver = new ChromeDriver();
                    break;
                case "firefox":
                    oDriver = new FirefoxDriver();
                    break;
                case "edge":
                    oDriver = new EdgeDriver();
                    break;
                default:
                    System.out.println("Invalid browser Name '"+browserName+"' was provided. The supported browsers are chrome/Firefox/Edge only");
            }

            if(oDriver!=null){
                oDriver.manage().window().maximize();
                System.out.println("The '"+browserName+"' browser was launched successful");
                return oDriver;
            }else{
                System.out.println("Failed to open the '"+browserName+"' browser");
                return null;
            }
        }catch(Exception e){
            System.out.println("Exception in 'launchBrowser()' method. "+ e);
            return null;
        }
    }


    /******************************************************************
     * Method Name          : clickObject()
     * Purpose              : to perform click operation on the elements
     * Params               : WebDriver oBrowser, By objBy
     * Return Type          : boolean
     ******************************************************************/
    public static boolean clickObject(WebDriver oBrowser, By objBy){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.click();
                System.out.println("The element '"+objBy+"' was clicked successful");
                return true;
            }else{
                System.out.println("Failed to find the element '"+objBy+"' in the DOM");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'clickObject()' method. " + e);
            return false;
        }
    }


    /******************************************************************
     * Method Name          : setObject()
     * Purpose              : to perform sendKeys() operation on the elements
     * Params               : WebDriver oBrowser, By objBy, String strData
     * Return Type          : boolean
     ******************************************************************/
    public static boolean setObject(WebDriver oBrowser, By objBy, String strData){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.sendKeys(strData);
                System.out.println("The data '"+strData+"' was set in the element '"+objBy+"' successful");
                return true;
            }else{
                System.out.println("Failed to find the element '"+objBy+"' in the DOM");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'setObject()' method. " + e);
            return false;
        }
    }



    /******************************************************************
     * Method Name          : clearAndSetObject()
     * Purpose              : to perform sendKeys() operation on the elements after clearing the exisitng values
     * Params               : WebDriver oBrowser, By objBy, String strData
     * Return Type          : boolean
     ******************************************************************/
    public static boolean clearAndSetObject(WebDriver oBrowser, By objBy, String strData){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.clear();
                oEle.sendKeys(strData);
                System.out.println("The data '"+strData+"' was set in the element '"+objBy+"' successful");
                return true;
            }else{
                System.out.println("Failed to find the element '"+objBy+"' in the DOM");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'clearAndSetObject()' method. " + e);
            return false;
        }
    }


    /******************************************************************
     * Method Name          : compareExactValues()
     * Purpose              : to perform exact comparision between actual & expected values
     * Params               : WebDriver oBrowser, By objBy, String strData
     * Return Type          : boolean
     ******************************************************************/
    public static boolean compareExactValues(WebDriver oBrowser, String actualValue, String expectedValue) {
        try{
            if(actualValue.equals(expectedValue)){
                System.out.println("Both actual '"+actualValue+"' & expected '"+expectedValue+"' values are matching");
                return true;
            }else{
                System.out.println("Mis-match in both actual '"+actualValue+"' & expected '"+expectedValue+"' values");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'compareExactValues()' method. " + e);
            return false;
        }
    }


    /******************************************************************
     * Method Name          : comparePartialValues()
     * Purpose              : to perform partial comparision between actual & expected values
     * Params               : WebDriver oBrowser, By objBy, String strData
     * Return Type          : boolean
     ******************************************************************/
    public static boolean comparePartialValues(WebDriver oBrowser, String actualValue, String expectedValue) {
        try{
            if(actualValue.contains(expectedValue)){
                System.out.println("Both actual '"+actualValue+"' & expected '"+expectedValue+"' values are matching");
                return true;
            }else{
                System.out.println("Mis-match in both actual '"+actualValue+"' & expected '"+expectedValue+"' values");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'comparePartialValues()' method. " + e);
            return false;
        }
    }


    /******************************************************************
     * Method Name          : verifyElementPresent()
     * Purpose              : to validate the presence of element in the DOM
     * Params               : WebDriver oBrowser, By objBy
     * Return Type          : boolean
     ******************************************************************/
    public static boolean verifyElementPresent(WebDriver oBrowser, By objBy) {
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                System.out.println("The element '"+objBy+"' exist in the DOM");
                return true;
            }else{
                System.out.println("Failed to locate the element '"+objBy+"' in the DOM");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'verifyElementPresent()' method. " + e);
            return false;
        }
    }



    /******************************************************************
     * Method Name          : verifyElementNotPresent()
     * Purpose              : to validate non-presence of element in the DOM
     * Params               : WebDriver oBrowser, By objBy
     * Return Type          : boolean
     ******************************************************************/
    public static boolean verifyElementNotPresent(WebDriver oBrowser, By objBy) {
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                System.out.println("The element '"+objBy+"' still exist in the DOM");
                return false;
            }else{
                System.out.println("The element '"+objBy+"' doesnot exist in the DOM");
                return true;
            }
        }catch(Exception e){
            System.out.println("Exception in 'verifyElementNotPresent()' method. " + e);
            return false;
        }
    }



    /******************************************************************
     * Method Name          : verifyOptionalElementPresent()
     * Purpose              : to validate presence ofoptional element in the DOM
     * Params               : WebDriver oBrowser, By objBy
     * Return Type          : boolean
     ******************************************************************/
    public static boolean verifyOptionalElementPresent(WebDriver oBrowser, By objBy) {
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'verifyOptionalElementPresent()' method. " + e);
            return false;
        }
    }


    /******************************************************************
     * Method Name          : verifyText()
     * Purpose              : to validate text from the element
     * Params               : WebDriver oBrowser, By objBy, String objectType, String expectedValue
     * Return Type          : boolean
     ******************************************************************/
    public static boolean verifyText(WebDriver oBrowser, By objBy, String objectType, String expectedValue){
        WebElement oEle = null;
        Select oSelect = null;
        String actual = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                switch(objectType.toLowerCase()){
                    case "text":
                        actual = oEle.getText();
                        break;
                    case "value":
                        actual = oEle.getAttribute("value");
                        break;
                    case "dropdown":
                        oSelect = new Select(oEle);
                        actual = oSelect.getFirstSelectedOption().getText();
                        break;
                    default:
                        System.out.println("Invalid object type '"+objectType+"' was specified. The supported ones are text/value/dropdown");
                        return false;
                }

                return compareExactValues(oBrowser, actual, expectedValue);
            }else{
                System.out.println("Failed to find the element '"+objBy+"' in the DOM");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'verifyText()' method. " + e);
            return false;
        }
    }


    /******************************************************************
     * Method Name          : getPropFileData()
     * Purpose              : to read the PropFile data as Map<String, String>
     * Params               : String propFileName
     * Return Type          : Map<String, String>
     ******************************************************************/
    public static Map<String, String> getPropFileData(String propFileName){
        FileInputStream fin = null;
        Properties prop = null;
        Map<String, String> objData = null;
        try{
            fin = new FileInputStream(System.getProperty("user.dir") +"\\TestData\\"+propFileName+".properties");
            prop = new Properties();
            prop.load(fin);
            objData = new HashMap<String, String>();

            Set<Map.Entry<Object, Object>> both = prop.entrySet();
            Iterator<Map.Entry<Object, Object>> it = both.iterator();
            while(it.hasNext()){
                Map.Entry<Object, Object> mp = it.next();
                objData.put(mp.getKey().toString(), mp.getValue().toString());
            }
            return objData;
        }catch(Exception e){
            System.out.println("Exception in 'getPropFileData()' method. " + e);
            return null;
        }
        finally
        {
            try{
                fin.close();
                fin = null;
                prop = null;
            }catch(Exception e){
            }
        }
    }


    /******************************************************************
     * Method Name          : getExelTestData()
     * Purpose              : to read the Excel data as Map<String, String>
     * Params               : String filePath, String sheetName, String logicalName
     * Return Type          : Map<String, String>
     ******************************************************************/
    public static Map<String, String> getExelTestData(String fileName, String sheetName, String logicalName){
        FileInputStream fin = null;
        Workbook wb = null;
        Sheet sh = null;
        Row row1 = null;
        Row row2 = null;
        Cell cell1 = null;
        Cell cell2 = null;
        int rowNum = 0;
        Map<String, String> objMap = null;
        String strKey = null;
        String strValue = null;
        Calendar cal = null;
        String sDay = null;
        String sMonth = null;
        String sYear = null;
        try{
            objMap = new HashMap<String, String>();
            fin = new FileInputStream(System.getProperty("user.dir")+ "\\TestData\\"+fileName+".xlsx");
            wb = new XSSFWorkbook(fin);
            sh = wb.getSheet(sheetName);
            if(sh==null){
                System.out.println("Failed to find the sheet '"+sheetName+"' in the Excel");
                return null;
            }

            //find the rowNum of the given logicalName name "TS_102"
            int rows = sh.getPhysicalNumberOfRows();
            for(int r=0; r<rows; r++){
                row1 = sh.getRow(r);
                cell1 = row1.getCell(0);
                if(cell1.getStringCellValue().equalsIgnoreCase(logicalName)){
                    rowNum = r;
                    break;
                }
            }

            if(rowNum > 0){
                row1 = sh.getRow(0);
                row2 = sh.getRow(rowNum);
                for(int c=0; c<row1.getPhysicalNumberOfCells(); c++){
                    cell1 = row1.getCell(c);
                    cell2 = row2.getCell(c);
                    strKey = cell1.getStringCellValue();

                    if(cell2==null || cell2.getCellType()== CellType.BLANK){
                        strValue = "";
                    }else if(cell2.getCellType()==CellType.STRING){
                        strValue = cell2.getStringCellValue();
                    }else if(cell2.getCellType()==CellType.BOOLEAN){
                        strValue = String.valueOf(cell2.getBooleanCellValue());
                    }else if(cell2.getCellType()==CellType.NUMERIC){
                        if(DateUtil.isCellDateFormatted(cell2) == true){
                            double dt = cell2.getNumericCellValue();
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
                            strValue = String.valueOf(cell2.getNumericCellValue());
                        }
                    }
                    objMap.put(strKey, strValue);
                }
            }else{
                System.out.println("Failed to find the logicalName '"+logicalName+"' in the Excel");
                return null;
            }

            return objMap;
        }catch(Exception e){
            System.out.println("Exception in 'getExelTestData()' method. " + e);
            return null;
        }
        finally
        {
            try{
                fin.close();
                fin = null;
                cell1 = null;
                cell2 = null;
                row1 = null;
                row2 = null;
                sh = null;
                wb.close();
                wb = null;
            }catch(Exception e){
            }
        }
    }
}
