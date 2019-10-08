package com.trendyol.gui.test.componentobject.common.BaseComponent;

import com.trendyol.gui.test.common.customcut.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseModal extends BaseCommon{

    //login-register ornegi icin...

    protected WebDriver driver;

    //region baseModalPaths_REGION
    //1
    @FindBy(how = How.CLASS_NAME, using = "fancybox-wrap fancybox-desktop fancybox-type-html default-popup fancybox-opened")
    private WebElement modalDiv;

    @FindBy(how = How.CLASS_NAME, using = "fancybox-item fancybox-close")
    private WebElement modalCloseButton;

    @FindBy(how = How.CLASS_NAME, using = "footer popupDivOut")
    private WebElement modalFooterDiv;

    @FindBy(how = How.CLASS_NAME, using = "text-center popup-header-text")
    private WebElement modalHeaderTextDiv;

    @FindBy(how = How.ID, using = "errorBox")
    private WebElement modalErrorBoxDiv;


    //2
    @FindBy(how = How.ID_OR_NAME, using = "email")
    public WebElement modalEmailInput;

    @FindBy(how = How.ID_OR_NAME, using = "password")
    public WebElement modalPasswordInput;




    //3
    @FindBy(how = How.CLASS_NAME, using = "fbLogin")
    private WebElement modalFacebookLoginDiv;

    @FindBy(how = How.CLASS_NAME, using = "googleLogin g-signin2")
    private WebElement modalGoogleLoginDiv;
    //div[@class='']

    //endregion

    //region baseModalCtor_REGION
    protected BaseModal(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //endregion

    //region baseModalMethods_REGION

    public void modalWait(){
        //CustomWait.waitUntilElementVisible(driver,modalDiv);
    }

    public void clickCloseModalBtn(){
        modalWait();
        CustomWait.waitUntilElementClickable(driver,modalCloseButton);
        modalCloseButton.click();
    }

    public String getModalHeaderText(){
        modalWait();
        return modalHeaderTextDiv.getText();
    }

    public String getModalFooterText(){
        modalWait();
        return modalFooterDiv.getText();
    }

    public String getModalErroMessage(){
        modalWait();
        return modalErrorBoxDiv.getText();
    }

    public void enterEmailInput(String emailValue){
        modalWait();
        CustomWait.waitUntilElementClickable(driver,modalEmailInput);
        modalEmailInput.clear();
        modalEmailInput.sendKeys(emailValue);
    }

    public void enterPasswordInput(String passValue){
        modalWait();
        CustomWait.waitUntilElementClickable(driver,modalPasswordInput);
        modalPasswordInput.clear();
        modalPasswordInput.sendKeys(passValue);
    }

    public void clickFacebookLoginButton(){
        modalWait();
        CustomWait.waitUntilElementClickable(driver,modalFacebookLoginDiv);
        modalFacebookLoginDiv.click();
    }

    public void clickGoogleLoginButton(){
        modalWait();
        CustomWait.waitUntilElementClickable(driver,modalGoogleLoginDiv);
        modalGoogleLoginDiv.click();
    }

    public void logIn(String emailValue, String passValue){
        enterEmailInput(emailValue);
        enterPasswordInput(passValue);

    }

    public void logIn(String loginType){
        switch (loginType){
            case "Facebook":
                clickFacebookLoginButton();
                break;
            case  "Google":
                clickGoogleLoginButton();
                break;
        }
    }

    //endregion
}
