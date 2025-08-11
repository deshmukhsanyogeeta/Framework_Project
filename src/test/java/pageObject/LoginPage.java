package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
     public LoginPage(ChromeDriver driver) // Constructor
     {
    	 super(driver);
     }
     
     @FindBy(xpath="//input[@id='input-email']") WebElement txtEmailAddress;
     @FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
     @FindBy(xpath="//input[@value='Login']") WebElement btnLogin;
     
     // Actions methods
     public void setEmail(String email)
     {
    	 txtEmailAddress.sendKeys(email);
     }
     
     public void setPassword(String pwd)
     {
    	 txtPassword.sendKeys(pwd);
     }
     
     public void clickLogin()
     {
    	 btnLogin.click();
     }
	
}
