package com.trendyol.gui.test.componentobject.login;

import com.trendyol.gui.test.common.customcut.CustomWait;
import com.trendyol.gui.test.componentobject.common.BaseComponent.BaseModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseModal {

    //region loginPagePaths
    @FindBy(how = How.ID, using = "loginSubmit")
    private WebElement modalLoginButton;

    @FindBy(how = How.XPATH, using = "//span[@class='forgot-password-link']/a")
    private WebElement modalForgotPasswordLink;

    @FindBy(how = How.ID, using = "not-logged-in-container")
    private WebElement headerLoggedContainerSpan;

    @FindBy(how = How.CLASS_NAME, using = "user-email")
    private WebElement headerMailDiv;

    @FindBy(how = How.XPATH, using = "//a[contains(@href,'/authentication/logout')]")
    private WebElement closeButtonLink;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickLoggedSpan(){
        CustomWait.waitUntilElementClickable(driver,headerLoggedContainerSpan);
        headerLoggedContainerSpan.click();
    }

    public void clickCloseBtn(){
        CustomWait.waitUntilElementClickable(driver,closeButtonLink);
        closeButtonLink.click();
    }


    public void clickLoginBtn(){
        modalWait();
        CustomWait.waitUntilElementClickable(driver,modalLoginButton);
        modalLoginButton.click();
    }

    public String getLoginUserEmail(){
        modalWait();
        driver.findElement(By.className("login-container")).click();
        CustomWait.waitUntilElementVisible(driver,headerMailDiv);
        return  headerMailDiv.getText();
    }

    public void clickForgotPassBtn(){
        modalWait();
        CustomWait.waitUntilElementClickable(driver,modalForgotPasswordLink);
        modalForgotPasswordLink.click();
    }

    public void logInTrendYol(String emailValue, String passValue){
        clickLoggedSpan();
        logIn(emailValue,passValue);
        //baseModalMethod...

        clickLoginBtn();
        //loginPageMethod...
    }

    public void logInOthers(String loginType){
        clickLoggedSpan();
        logIn(loginType);
        //baseModalMethod...
    }  //Facebook - Google

}
