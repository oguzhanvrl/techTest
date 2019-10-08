package com.trendyol.gui.test.common.customcut;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class CustomScreenshot {

    private final String SCREENSHOT_FOLDER_PATH = "src/target/screenshots/";

    public CustomScreenshot(WebDriver driver, String filename){
        // checks screenshot folder is exist or not, if not creates a new one
        File screenshotFolder = new File(SCREENSHOT_FOLDER_PATH);
        if(screenshotFolder.exists() && screenshotFolder.isDirectory()){
            takeScreenshot(driver, filename);
        }else{
            boolean isFolderCreated = new File(SCREENSHOT_FOLDER_PATH).mkdirs();
            if(!isFolderCreated){
                // TODO : write log if the screenshot folder can not create!
                System.out.println("The screenshot folder could not create!");
                return;
            }else{
                takeScreenshot(driver, filename);
            }
        }
    }

    private void takeScreenshot(WebDriver driver, String filename){
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenShot.toPath(),
                    new FileOutputStream(SCREENSHOT_FOLDER_PATH + filename + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}