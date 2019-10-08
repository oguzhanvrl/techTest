package com.trendyol.gui.test.common.customcut;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWait {

    private static final int TIMEOUT_WAIT_SECONDS = 10;

    public static void waitUntilTextToBePresentInElementValue(WebDriver driver,WebElement element, String value) {
        try {
            boolean myElementt = (new WebDriverWait(driver,TIMEOUT_WAIT_SECONDS))
                    .until(ExpectedConditions.textToBePresentInElement(element,value));
        } catch (TimeoutException e) {
            //TODO : log
            throw e;
        }
    }

    public static void waitUntilElementVisible(WebDriver driver, WebElement element){
        try {
            WebElement myDynamicElement = (new WebDriverWait(driver, TIMEOUT_WAIT_SECONDS))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch(TimeoutException e) {
            // TODO : log
            throw e;
        }
    }

    public static void waitUntilElementClickable(WebDriver driver, WebElement element){
        try {
            WebElement myDynamicTextElement = (new WebDriverWait(driver, TIMEOUT_WAIT_SECONDS))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch(TimeoutException e) {
            // TODO : log
            throw e;
        }
    }

    public static void waitUntilElementInvisible(WebDriver driver, WebElement element){
        try {
            Boolean myDynamicElement = (new WebDriverWait(driver, TIMEOUT_WAIT_SECONDS))
                    .until(ExpectedConditions.invisibilityOf(element));
        } catch(TimeoutException e) {
            // TODO : log
            throw e;
        }
    }

    public static void waitUntilElementNotDisplayed(WebDriver driver, WebElement webElement, int... TIMEOUT_SECONDS) {
        int TIME_OUT = (TIMEOUT_SECONDS.length > 0) ? TIMEOUT_SECONDS[0] : TIMEOUT_WAIT_SECONDS;
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    webElement.isDisplayed();
                    return false;
                }
                catch (NoSuchElementException e ) {
                    return true;
                }
                catch (StaleElementReferenceException f) {
                    return true;
                }
            }
        };
        wait.until(elementIsDisplayed);
    }

    public static void waitForLoad(WebDriver driver) {
        waitForajaxLoad(driver);
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e) {}
        new WebDriverWait(driver, TIMEOUT_WAIT_SECONDS).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static  void waitForajaxLoad(WebDriver driver){
            int delay=0;
            delay = TIMEOUT_WAIT_SECONDS;
            Boolean isJqueryUsed = (Boolean)((JavascriptExecutor)driver).executeScript("return (typeof(jQuery) != 'undefined')");
            if(isJqueryUsed){
                while (delay > 0){
                    // JavaScript test to verify jQuery is active or not
                    Boolean ajaxIsComplete = (Boolean)(((JavascriptExecutor)driver).executeScript("return jQuery.active == 0"));
                    if (ajaxIsComplete) break;
                    try{
                        Thread.sleep(100);
                        delay--;
                    }catch (InterruptedException e) {}
                }
            }
    }


    public static void waitForTrendyol(WebDriver driver) {

       // Pattern r = Pattern.compile("\\d[0-9]*");
       //String countt = r.matcher(curUrl).toMatchResult().toString();

        /*String curPiCount, tempPiCount;

        int delay =0;
        delay=10;
        while (delay>0){

            waitForLoad(driver);
          curPiCount = GetUrlPiCount(driver);

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e) {}

            delay--;
            tempPiCount = GetUrlPiCount(driver);

            if (curPiCount.equals(tempPiCount)){
                break;
            }
        }*/

        int delay =0;
        delay=30;
        while (delay>0) {
            if (driver.getCurrentUrl().equals("https://www.trendyol.com/?pi=40"))
                break;

                try{
                    Thread.sleep(1000);
                    delay--;
                }catch (InterruptedException e) {}
            //total max wait : 30sn.
        }
    }

    public static String GetUrlPiCount(WebDriver driver){
        String[] urlSplit = driver.getCurrentUrl().split("=");
        return urlSplit[urlSplit.length-1];
    }


}