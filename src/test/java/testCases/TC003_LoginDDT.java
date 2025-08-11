package testCases; //Step 6

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.BaseClass;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import utilities.DataProviders;

/* Data is valid = login success= test passed= logout
 * Data is invalid= login failed= test fail
  
 * Data is invalid= login success= test failed= logout
 * Data is invalid= login fail= test passed
  */
public class TC003_LoginDDT extends BaseClass
{
	

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven") // getting dataProvider from different class & different package
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		logger.info("*** Starting TC003_LoginDDT ***");
		try {
		     //HomePage
				HomePage hp=new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
			//LoginPage
				LoginPage lp=new LoginPage(driver);
				lp.setEmail(email);
				lp.setPassword(pwd);
				lp.clickLogin();
						
			//MyAccountPage
				MyAccountPage mp=new MyAccountPage(driver);
				boolean targetPage=mp.isMyAccountPageExists();
				
				if(exp.equalsIgnoreCase("Valid"))
				{
					if(targetPage==true)
					{
						mp.linkLogout();
						Assert.assertTrue(true);
						
					}
					else
					{
						Assert.assertTrue(false);
					}
				}
				if(exp.equalsIgnoreCase("Invalid"))
				{
					if(targetPage==true)
					{
						mp.linkLogout();
						Assert.assertTrue(false);
					}
					else
					{
						Assert.assertTrue(true);
					}
				}
		}catch(Exception e)
		{
			Assert.fail();
		}
				
			logger.info("*** Ending TC003_LoginDDT ***");	
				
	}
}
