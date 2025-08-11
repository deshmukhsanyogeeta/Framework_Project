package utilities; // step 7

   import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pageObject.BaseClass;


	public class ExtentReportManager implements ITestListener
	{

		public ExtentSparkReporter sparkReporter; //UI of report
		public ExtentReports extent; //populate common info on the report
		public ExtentTest test; //creating test case entry in report & update status of the test methods
		
		 String repName;
		
		 public void onStart(ITestContext testContext) {
			 
			 /*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			 Date dt=new Date();
			 String currentdatetimestamp=df.format(dt);
			 */
			
			 String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // seating time 
			 repName="Test-Report-" + timestamp + ".html"; 
			sparkReporter=new ExtentSparkReporter(".\\reports\\" + repName);
			
			sparkReporter.config().setDocumentTitle("Opencart Automation report");
			sparkReporter.config().setReportName("Opencart Functional testing");
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent=new ExtentReports();
			extent.attachReporter(sparkReporter);
			
			extent.setSystemInfo("Application", "Opencart");
			extent.setSystemInfo("Module", "Adimn");
			extent.setSystemInfo("Sub Module", "Customers");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
			
			String os= testContext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating System",os );
			
			String browser= testContext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser",browser);
			
			java.util.List<String> includedGroups= testContext.getCurrentXmlTest().getIncludedGroups();
			if(! includedGroups.isEmpty()) {
				extent.setSystemInfo("Groups",includedGroups.toString());
			}
			
			
		}
		
		public void onTestSuccess(ITestResult result) {
			
			test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups()); // display groups in reports
			test.log(Status.PASS,result.getName() +"got successfully executed"); //update status 
			
			}
		
		public void onTestFailure(ITestResult result) {
			test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			test.log(Status.FAIL,result.getName()+"got failed");
			test.log(Status.INFO,result.getThrowable().getMessage());
			
			try {
			String imgpath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
			}catch(IOException e1)
			{
				e1.printStackTrace();
			}
			
		}
		
		public void onTestSkipped(ITestResult result) {
			test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP,result.getName()+"got skipped");
			test.log(Status.INFO,result.getThrowable().getMessage());
			
		}
		
		public void onFinish(ITestContext context) {
			
			extent.flush();
			
			String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
			File extentReport=new File(pathOfExtentReport);
			try {
			Desktop.getDesktop().browse(extentReport.toURI());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		

	}


