package scripts;

import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class TestScripts {
    //TC_101: Login and logout functionality
    @Test
    public void TS_LoginAndLogout() {
        WebDriver oBrowser = null;
        Map<String, String> objData = new HashMap<String, String>();
        try{
            objData = AppIndependentMethods.getExelTestData("TestData", "TestData", "TS_101");
            oBrowser = AppIndependentMethods.launchBrowser(objData.get("browserName"));
            Assert.assertTrue(AppDependentMethods.navigateURL(oBrowser, objData.get("appURL")));
            Assert.assertTrue(AppDependentMethods.loginToActiTime(oBrowser, objData.get("userName"), objData.get("password")));
            Assert.assertTrue(AppDependentMethods.logoutFromActiTime(oBrowser));
        }catch(Exception e){
            System.out.println("Exception in 'TS_LoginAndLogout()' testScript: "+ e);
        }finally
        {
            oBrowser.close();
            oBrowser = null;
        }
    }



    //TC_102: Create and Delete user functionality
    @Test
    public void TS_CreateAndDeleteUser() {
        WebDriver oBrowser = null;
        Map<String, String> objData = new HashMap<String, String>();
        try{
            objData = AppIndependentMethods.getExelTestData("TestData", "TestData", "TS_102");
            oBrowser = AppIndependentMethods.launchBrowser(objData.get("browserName"));
            Assert.assertTrue(AppDependentMethods.navigateURL(oBrowser, objData.get("appURL")));
            Assert.assertTrue(AppDependentMethods.loginToActiTime(oBrowser, objData.get("userName"), objData.get("password")));
            String userName = AppDependentMethods.createUser(oBrowser, objData);
            Assert.assertTrue(AppDependentMethods.deleteUser(oBrowser, userName));
            Assert.assertTrue(AppDependentMethods.logoutFromActiTime(oBrowser));
        }catch(Exception e){
            System.out.println("Exception in 'TS_CreateAndDeleteUser()' testScript: "+ e);
        }finally{
            oBrowser.close();
            oBrowser = null;
        }
    }
}
