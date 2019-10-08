package com.trendyol.gui.test.testsuites.challenge;

import com.opencsv.CSVWriter;
import com.trendyol.gui.test.common.customcut.CustomWait;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoutiqueTestCases {

    @Test
    public void boutiqueChallengeTestMethod() throws IOException {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(); // can specify a port here if you like
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.newHar("TrendyolTech");

        // get the selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        WebDriver driver = new ChromeDriver(capabilities);
        driver.navigate().to("https://www.trendyol.com/");
        CustomWait.waitForLoad(driver);

        driver.findElement(By.xpath("//a[@class='fancybox-item fancybox-close']")).click();
        new Actions(driver).moveToElement(driver.findElement(By.id("footer"))).perform();
        CustomWait.waitForTrendyol(driver);

        List<HarEntry> harList = proxy.getHar().getLog().getEntries();
        List<String> responseCodesAndTime = new ArrayList<String>();
        harList.stream().filter(link -> link.getRequest().getUrl().contains(".jpg")
                | link.getRequest().getUrl().contains(".jpg"))
                //diger (ico-gif) uzantilar eklenebilir "|" operatoru eklenerek
                .forEach(img -> {
                    responseCodesAndTime.add(
                            "URL:" + String.valueOf(img.getRequest().getUrl()) + "\r \n " +
                                    "TIME: " + String.valueOf(img.getTimings().getWait()) + " ms \r \n " +
                                    "STATUS: " + String.valueOf(img.getResponse().getStatus()) + "\r \n");
                });

        String harFilePath = System.getProperty("user.dir") + "/target/" + "csvTestResult1";
        File file = new File(harFilePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = {"url - time - status"};
            writer.writeNext(header);
            String[] csvxData = responseCodesAndTime.stream().toArray(String[]::new);

            // add data to csvx
            writer.writeNext(csvxData);

            // closing writer connection
            writer.close();
            driver.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
