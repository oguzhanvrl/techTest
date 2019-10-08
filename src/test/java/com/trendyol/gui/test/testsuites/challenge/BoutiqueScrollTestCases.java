package com.trendyol.gui.test.testsuites.challenge;

import com.opencsv.CSVWriter;
import com.trendyol.gui.test.common.customcut.CustomWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BoutiqueScrollTestCases {
    @Test
    public void boutiqueLinksChallengeTestCase() throws IOException {

        List<String> butikLinksAndCodes = new ArrayList<String>();
        int code=0;
        String link;

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.trendyol.com/");
        CustomWait.waitForLoad(driver);

        driver.findElement(By.xpath("//a[@class='fancybox-item fancybox-close']")).click();
        new Actions(driver).moveToElement(driver.findElement(By.id("footer"))).perform();
        CustomWait.waitForTrendyol(driver);

        List<WebElement>  boutiqueBigElements = driver.findElements(By.xpath("//article/a[@class='campaign campaign-big']"));
        for (WebElement element : boutiqueBigElements) {

            link = element.getAttribute("href");

            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            code = connection.getResponseCode();

            butikLinksAndCodes.add("Link : "+ link +" \r \n Code :" + code +" \r \n ");
        }

        List<WebElement> boutiqueSmallElements = driver.findElements(By.xpath("//article/a[@class='campaign campaign-small']"));
        for (WebElement element : boutiqueSmallElements) {

            link = element.getAttribute("href");

            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            code = connection.getResponseCode();

            butikLinksAndCodes.add("Link : "+ link +" \r \n Code :" + code +" \r \n ");
        }

        String harFilePath = System.getProperty("user.dir") + "/target/" + "csvTestResult2";
        File file = new File(harFilePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = {"link - statusCode"};
            writer.writeNext(header);

            String[] data= butikLinksAndCodes.stream().toArray((String[]::new));

            // add data to csvx
            writer.writeNext(data);

            // closing writer connection
            writer.close();
            driver.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
