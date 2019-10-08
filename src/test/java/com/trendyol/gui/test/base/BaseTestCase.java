package com.trendyol.gui.test.base;

import com.trendyol.gui.test.common.customcut.CustomGenerator;
import com.trendyol.gui.test.common.customcut.CustomScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.util.ArrayList;

public abstract class BaseTestCase {

    //private static SetUpTestDriver setupTestDriver;
    protected static WebDriver driver ;


    public void runBeforeAnyTests(){
    }

    public void runAfterAnyTests(){
    }

    public void afterMethod(ITestResult result){
       addResultToAllure(result);
        closeDriver();
    }

    public void addResultToAllure(ITestResult result){
        String caseId = (String) result.getAttribute(result.getName());
        String message=null;

        switch (result.getStatus()){
            case ITestResult.SUCCESS:
                message = "Success";
                //allureADD
                break;
            case ITestResult.FAILURE:
                if (result.getThrowable()!= null){
                    Throwable testResultThrowable = result.getThrowable();
                    message= testResultThrowable.getMessage() != null ? testResultThrowable.getMessage() :
                            testResultThrowable.getCause().getMessage();
                    if (message.equals(null)){
                        message = "Test Failed!";
                    }
                    //take screenshot and save it to the screenshots
                    new CustomScreenshot(driver, CustomGenerator.getTimestamp());
                }
                //allureADD
                break;
            case ITestResult.SKIP:
                message = "Skipped in Automation";
                //allureADD
                break;
        }

    }

    public void beforeMethod(){
        /*driver=new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.get(PropertiesFile.getInstance().getProperty(PropertiesData.TRENDYOL_LOGIN_URL));
        driver.get("https://www.trendyol.com/");*/
    }

    public void beforeMethodWithLogin() {
        // BeforeMethod();
        // Login();
    }

    public void login(){
        // PropertiesFile.getInstance().getProperty(PropertiesData.LOGIN_VALID_USERNAME);
        // PropertiesFile.getInstance().getProperty(PropertiesData.LOGIN_VALID_PASSWORD);
    }

    public void switchToTab(int tabIndex){
        ArrayList<String> tabs= new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabIndex));
    }

    public void closeDriver(){
        if (driver!=null)
            driver.close();
        driver.quit();
    }

}
