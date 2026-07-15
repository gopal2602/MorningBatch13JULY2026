package locators;

import org.openqa.selenium.By;

public interface ObjectLocators {
    public static By obj_LoginPage_Header = By.xpath("//td[@id='headerContainer']");
    public static By obj_UserName_Edit = By.xpath("//input[@name='username']");
    public static By obj_Password_Edit = By.xpath("//input[@name='pwd']");
    public static By obj_Login_Button = By.xpath("//a[@id='loginButton']");
    public static By obj_SaveChanges_Button = By.xpath("//span/input[@value='Save Changes']");
    public static By obj_Shortcut_Dialog = By.xpath("//div[@style='display: block;']");
    public static By obj_Shortcut_Close_Button = By.id("gettingStartedShortcutsMenuCloseId");
    public static By obj_USERS_Menu = By.xpath("//div[text()='USERS']");
    public static By obj_AddUser_Button = By.xpath("//div[text()='Add User']");
    public static By obj_AddUser_Label = By.xpath("//span[text()='Add User']");
    public static By obj_User_FirstName_Edit = By.xpath("//input[@name='firstName']");
    public static By obj_User_LastName_Edit = By.xpath("//input[@name='lastName']");
    public static By obj_User_Email_Edit = By.xpath("//input[@name='email']");
    public static By obj_User_UserName_Edit = By.xpath("//input[@name='username']");
    public static By obj_User_Password_Edit = By.xpath("//input[@name='password']");
    public static By obj_User_RetypePassword_Edit = By.xpath("//input[@name='passwordCopy']");
    public static By obj_CreateUser_Button = By.xpath("//span[text()='Create User']");
    public static String obj_UserName_Link = "//div[@class='name']/span[text()='%s']";
    public static By obj_DeleteUser_Button = By.xpath("//button[contains(text(), 'Delete User')]");
    public static By obj_Logout_Link = By.xpath("//a[@id='logoutLink']");
    public static By obj_LoginPageLogo_Image = By.xpath("//img[contains(@src, 'timer.png')]");
}
