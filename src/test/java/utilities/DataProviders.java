package utilities; // Step6

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx"; // taking xl file from testData 
		ExcelUtility xlutil=new ExcelUtility(path); // creating an object for xlUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		String loginData[][]=new String[totalrows][totalcols]; //creating two dimensional array
		for(int i=1;i<totalrows;i++) // i is rows 
		{
			for(int j=0;j<totalcols;j++)
			{
				loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData; //Returning an array
	}
	
	// DataProvider 2
	
	// DataProvider 3
	
	// DataProvider 4
}
