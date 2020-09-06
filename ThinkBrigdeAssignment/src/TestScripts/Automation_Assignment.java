package TestScripts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import FunctionLibrary.CommonFunctions;
import FunctionLibrary.ExcelManager;
import ObjectRepository.SignUpOR;

public class Automation_Assignment implements SignUpOR 
{
	
	public static void main(String[] args) throws Throwable 
	{
		//initializations
		ExcelManager excelManager = new ExcelManager();
		Map<String, String> hm = new HashMap<String, String>();
		Map<String, String> hm1 = new HashMap<String, String>();
		hm = excelManager.readExcelByRow("..\\ThinkBrigdeAssignment\\src\\TestData\\Browser.xls", "Sheet1",
				1);
		hm1 = excelManager.readExcelByRow("..\\ThinkBrigdeAssignment\\src\\TestData\\Data.xls", "Sheet1",
				1);
		WebDriver driver= DriverReturn.createInstance(hm.get("Browser_Type"), "http://jt-dev.azurewebsites.net/#SignUp");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		CommonFunctions commonFunctions = new CommonFunctions(driver);
		
		
		//Check if Dutch is present
		commonFunctions.fnWebElementClick("xpath", language);
		
		List<WebElement> nameInList=commonFunctions.fnGetWebElementList("xpath", search);
		if (!nameInList.isEmpty()) {
			for (WebElement elm : nameInList) {
				if (CommonFunctions.fnWebElementClick(elm)) {
					elm.clear();
					elm.sendKeys("Dutch");
					CommonFunctions.fnWait(2);
					elm.sendKeys(Keys.ENTER);
					
					break;
				}
			}}
		
		
		CommonFunctions.fnWait(2);
		String lang1=commonFunctions.fnWebElementGetText("xpath", language);
		
		System.out.println(lang1);
		if (lang1.equalsIgnoreCase("Dutch"))
		{
			System.out.println("Dutch Langage is present");
		}
		else 
		{
			System.out.println("Dutch Langage is not present");
		}
		
		//Check if ellish is present 
		commonFunctions.fnWebElementClick("xpath", language);
		
		List<WebElement> nameInList2=commonFunctions.fnGetWebElementList("xpath", search);
		if (!nameInList2.isEmpty()) {
			for (WebElement elm : nameInList2) {
				if (CommonFunctions.fnWebElementClick(elm)) {
					elm.clear();
					elm.sendKeys("English");
					CommonFunctions.fnWait(2);
					elm.sendKeys(Keys.ENTER);
					
					break;
				}
			}}

		String lang2=commonFunctions.fnWebElementGetText("xpath", language);
		
		System.out.println(lang1);
		if (lang2.equalsIgnoreCase("English"))
		{
			System.out.println("English Langage is present");
		}
		
		else
		{
			System.out.println("English Langage is not present");
		}
		
		
		//Enter Details
		commonFunctions.fnWebElementEnterText("xpath", name, hm1.get("Name"));
		commonFunctions.fnWebElementEnterText("xpath", orgName, hm1.get("OrgName"));
		commonFunctions.fnWebElementEnterText("xpath", email, hm1.get("Email"));
		commonFunctions.fnWebElementClick("xpath", agreeCheckBox);
		
		//Click on sign up
		commonFunctions.fnWebElementClick("xpath", signUpBtn);
		
		//Verify if Email is sent
		
		if (commonFunctions.fnWebElementDisplayed("xpath", emailNotification))
		{
			System.out.println("Email is sent Successfully");
		}
		else
		{
			System.out.println("Email has not been sent, please verify Email ID");
		}
		
		//Close the browser
		driver.close();
	}
	
}
