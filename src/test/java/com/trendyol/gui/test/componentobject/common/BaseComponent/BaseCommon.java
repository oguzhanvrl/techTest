package com.trendyol.gui.test.componentobject.common.BaseComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class BaseCommon {

    protected WebDriver driver;

    //region ScrollToElement_REGION
    public void scrollToElement(WebElement element){
        new Actions(driver).moveToElement(element).perform();
    }

    public void scrollToElement(By element){
        new Actions(driver).moveToElement(driver.findElement(element)).perform();
    }
    //endregion

    //region SelectElement_REGION

    //element select islemleri

    //endregion

}
