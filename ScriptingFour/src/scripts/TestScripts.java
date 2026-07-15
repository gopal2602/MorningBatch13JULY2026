package scripts;

import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;

public class TestScripts {
    public static void main(String[] args) {
        TS_LoginAndLogout();
        TS_CreateAndDeleteUser();
    }

    //TC_101: Login and logout functionality
    static void TS_LoginAndLogout() {
        WebDriver oBrowser = null;
        Map<String, String> objData = new HashMap<String, String>();
        try{
            objData = AppIndependentMethods.getExelTestData("TestData", "TestData", "TS_101");
            oBrowser = AppIndependentMethods.launchBrowser(objData.get("browserName"));
            AppDependentMethods.navigateURL(oBrowser, objData.get("appURL"));
            AppDependentMethods.loginToActiTime(oBrowser, objData.get("userName"), objData.get("password"));
            AppDependentMethods.logoutFromActiTime(oBrowser);
        }catch(Exception e){
            System.out.println("Exception in 'TS_LoginAndLogout()' testScript: "+ e);
        }finally
        {
            oBrowser.close();
            oBrowser = null;
        }
    }



    //TC_102: Create and Delete user functionality
    static void TS_CreateAndDeleteUser() {
        WebDriver oBrowser = null;
        Map<String, String> objData = new HashMap<String, String>();
        try{
            objData = AppIndependentMethods.getExelTestData("TestData", "TestData", "TS_102");
            oBrowser = AppIndependentMethods.launchBrowser(objData.get("browserName"));
            AppDependentMethods.navigateURL(oBrowser, objData.get("appURL"));
            AppDependentMethods.loginToActiTime(oBrowser, objData.get("userName"), objData.get("password"));
            String userName = AppDependentMethods.createUser(oBrowser, objData);
            AppDependentMethods.deleteUser(oBrowser, userName);
            AppDependentMethods.logoutFromActiTime(oBrowser);
        }catch(Exception e){
            System.out.println("Exception in 'TS_CreateAndDeleteUser()' testScript: "+ e);
        }finally{
            oBrowser.close();
            oBrowser = null;
        }
    }
}
