package com.trendyol.gui.test.componentobject.common.BaseComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage extends BaseCommon{

    protected WebDriver driver;

    protected BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }



    // #headerDiv
            // +header-top
            // +sticky-header
                    //div
                    //div #autoComplete
                    //div #autoComplete

    // #navigation

    // #footer
            // .popular-brand-category
            // .site-footer
            // .footerBandInfo



}


