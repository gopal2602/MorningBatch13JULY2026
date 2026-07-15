package methods;

import locators.ObjectLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Map;

public class AppDependentMethods implements ObjectLocators {
    /******************************************************************
     * Method Name          : navigateURL()
     * Purpose              : to launch the required browsers viz., chrome/firefox/edge
     * Params               : WebDriver oBrowser, String strURL
     * Return Type          : boolean
     ******************************************************************/
    public static boolean navigateURL(WebDriver oBrowser, String strURL){
        try{
            oBrowser.navigate().to(strURL);
            Thread.sleep(2000);
            return AppIndependentMethods.verifyText(oBrowser, obj_LoginPage_Header, "Text", "Please identify yourself");
        }catch(Exception e){
            System.out.println("Exception in 'navigateURL()' method. "+ e);
            return false;
        }
    }


    /******************************************************************
     * Method Name          : loginToActiTime()
     * Purpose              : to login to actiTime application
     * Params               : WebDriver oBrowser, String userName, String password
     * Return Type          : boolean
     ******************************************************************/
    public static boolean loginToActiTime(WebDriver oBrowser, String userName, String password){
        try{
            AppIndependentMethods.setObject(oBrowser, obj_UserName_Edit, userName);
            AppIndependentMethods.setObject(oBrowser, obj_Password_Edit, password);
            AppIndependentMethods.clickObject(oBrowser, obj_Login_Button);
            Thread.sleep(2000);
            if(AppIndependentMethods.verifyElementPresent(oBrowser, obj_SaveChanges_Button)){
                System.out.println("Login to ActiTime was successful");
                if(AppIndependentMethods.verifyOptionalElementPresent(oBrowser, obj_Shortcut_Dialog)){
                    AppIndependentMethods.clickObject(oBrowser, obj_Shortcut_Close_Button);
                }
                return true;
            }else{
                System.out.println("Failed to login to the actiTime application");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'loginToActiTime()' method. "+ e);
            return false;
        }
    }


    /******************************************************************
     * Method Name          : createUser()
     * Purpose              : to create the new user in actiTime
     * Params               : WebDriver oBrowser, Map<String, String> objData
     * Return Type          : String
     ******************************************************************/
    public static String createUser(WebDriver oBrowser, Map<String, String> objData){
        String userName = null;
        try{
            AppIndependentMethods.clickObject(oBrowser, obj_USERS_Menu);
            Thread.sleep(2000);
            AppIndependentMethods.clickObject(oBrowser, obj_AddUser_Button);
            Thread.sleep(2000);
            AppIndependentMethods.verifyText(oBrowser, obj_AddUser_Label, "Text", "Add User");

            AppIndependentMethods.setObject(oBrowser, obj_User_FirstName_Edit, objData.get("user_FirstName"));
            AppIndependentMethods.setObject(oBrowser, obj_User_LastName_Edit, objData.get("user_LastName"));
            AppIndependentMethods.setObject(oBrowser, obj_User_Email_Edit, objData.get("user_email"));
            AppIndependentMethods.setObject(oBrowser, obj_User_UserName_Edit, objData.get("user_UserName"));
            AppIndependentMethods.setObject(oBrowser, obj_User_Password_Edit, objData.get("user_password"));
            AppIndependentMethods.setObject(oBrowser, obj_User_RetypePassword_Edit, objData.get("user_RetypePassword"));
            AppIndependentMethods.clickObject(oBrowser, obj_CreateUser_Button);
            Thread.sleep(2000);
            userName = objData.get("user_LastName") + ", " + objData.get("user_FirstName");
            if(AppIndependentMethods.verifyElementPresent(oBrowser, By.xpath(String.format(obj_UserName_Link, userName)))){
                System.out.println("The new user is created successful");
                return userName;
            }else{
                System.out.println("Failed to create the user");
                return null;
            }
        }catch(Exception e){
            System.out.println("Exception in 'createUser()' method. "+ e);
            return null;
        }
    }


    /******************************************************************
     * Method Name          : delteUser()
     * Purpose              : to delete the user from actiTime
     * Params               : WebDriver oBrowser
     * Return Type          : boolean
     ******************************************************************/
    public static boolean deleteUser(WebDriver oBrowser, String userName){
        try{
            AppIndependentMethods.clickObject(oBrowser, By.xpath(String.format(obj_UserName_Link, userName)));
            Thread.sleep(2000);
            AppIndependentMethods.clickObject(oBrowser, obj_DeleteUser_Button);
            Thread.sleep(2000);
            oBrowser.switchTo().alert().accept();
            Thread.sleep(2000);

            if(AppIndependentMethods.verifyElementNotPresent(oBrowser, By.xpath(String.format(obj_UserName_Link, userName)))) {
                System.out.println("The user is deleted from the actiTime successful");
                return true;
            }else{
                System.out.println("Failed to delete the user from actiTime");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'deleteUser()' method. "+ e);
            return false;
        }
    }


    /******************************************************************
     * Method Name          : logoutFromActiTime()
     * Purpose              : to logout from the actiTime application
     * Params               : WebDriver oBrowser
     * Return Type          : boolean
     ******************************************************************/
    public static boolean logoutFromActiTime(WebDriver oBrowser){
        try{
            AppIndependentMethods.clickObject(oBrowser, obj_Logout_Link);
            Thread.sleep(2000);
            if(AppIndependentMethods.verifyElementPresent(oBrowser, obj_LoginPageLogo_Image)){
                System.out.println("Logout for the actiTime was successful");
                return true;
            }else{
                System.out.println("Failed to logut form the actiTime");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'logoutFromActiTime()' method. "+ e);
            return false;
        }
    }
}
