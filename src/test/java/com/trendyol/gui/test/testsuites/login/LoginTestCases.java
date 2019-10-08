package com.trendyol.gui.test.testsuites.login;

import com.trendyol.gui.test.base.BaseTestCase;
import com.trendyol.gui.test.base.SetUpTestDriver;
import com.trendyol.gui.test.base.properties.PropertiesData;
import com.trendyol.gui.test.base.properties.PropertiesFile;
import com.trendyol.gui.test.componentobject.login.LoginPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class LoginTestCases extends BaseTestCase {

    private LoginPage loginPage;
    String username, pass;

    @BeforeClass(alwaysRun = true)
    @Parameters({"os", "browser", "url", "node"})
    public void setUp(String os, String browser, String url, String node) throws MalformedURLException {
        SetUpTestDriver setupTestDriver = new SetUpTestDriver(os, browser, url, node);
        driver = setupTestDriver.getDriver();
        driver.get("https://www.trendyol.com");

        //CustomLogger.startLog("test is starting!");
    }

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();
        loginPage = new LoginPage(driver);
        username = PropertiesFile.getInstance().getProperty(PropertiesData.LOGIN_VALID_USERNAME);
        pass = PropertiesFile.getInstance().getProperty(PropertiesData.LOGIN_VALID_PASSWORD);
    }

    @Test
    public void testWithValidCredentials(){
        // Reporter.getCurrentTestResult().setAttribute(curMethod.getName(),"1");
        loginPage.logInTrendYol("trendyoltestuser@yopmail.com","123456789");
        Assert.assertEquals("trendyoltestuser@yopmail.com", loginPage.getLoginUserEmail());
    }

    @Test
    public void testWithInValidCredentials(){
        // Reporter.getCurrentTestResult().setAttribute(curMethod.getName(),"1");
        loginPage.logInTrendYol("oguzhanvarol@outlook.com.trS","asdwasdw");
        Assert.assertTrue(loginPage.getModalErroMessage().indexOf("Hatalı E-Posta / Şifre. Tekrar Deneyin.")!=-1);
    }

    @Test
    public void testWithEmptyCredentials(){
        // Reporter.getCurrentTestResult().setAttribute(curMethod.getName(),"1");
        loginPage.logInTrendYol("","");
        Assert.assertTrue(loginPage.getModalErroMessage().indexOf("Lütfen email adresinizi giriniz.")!=-1);
    }


    @Test
    public void testWithCheckPasswordMasked(){
        // Reporter.getCurrentTestResult().setAttribute(curMethod.getName(),"1");
        loginPage.logInTrendYol("oguzhanvarol@outlook.com.trS","asdwasdw");
        loginPage.enterPasswordInput("123");
        Assert.assertTrue(
                loginPage.modalPasswordInput.getAttribute("type").contains("password")
                        && loginPage.modalPasswordInput.getText().indexOf("123")==-1);
    }


    @Test
    public void testWithCheckIfAfterLoginTakeBackToTheLoginPage(){
        // Reporter.getCurrentTestResult().setAttribute(curMethod.getName(),"1");
        loginPage.logInTrendYol("trendyoltestuser@yopmail.com","123456789");
        loginPage.clickCloseBtn();
        loginPage.logInTrendYol("trendyoltestuser@yopmail.com","123456789");
        Assert.assertEquals("trendyoltestuser@yopmail.com", loginPage.getLoginUserEmail());
    }

    @Test
    public void testWithCheckSignOnAttemptsLimit(){
        // Reporter.getCurrentTestResult().setAttribute(curMethod.getName(),"1");
        int count = 50;
        boolean status =false;
        while (count >0){
            loginPage.logInTrendYol("oguzhanvarol@outlook.com.tr","asdwasdw");
            if (loginPage.getModalErroMessage().indexOf("Çok sayıda başarısız deneme yaptınız.")!=-1){
                status = true;
                break;
            }
            count--;
        }
        Assert.assertTrue(status);
    }


    @AfterMethod
    public void afterMethod(ITestResult curResult) { super.afterMethod(curResult);}
}
