package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.BaseClass;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;

public class TC002_LoginTest extends BaseClass
{
  
	@Test(groups= {"Sanity","Master"})
	public void verify_Login()
	{
		logger.info("*** Starting TC002_LoginTest ***");
		
		try {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
				
		//MyAccountPage
		MyAccountPage mp=new MyAccountPage(driver);
		boolean targetPage=mp.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage, true);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("*** Finished TC002_LoginTest ***");
	}
}
