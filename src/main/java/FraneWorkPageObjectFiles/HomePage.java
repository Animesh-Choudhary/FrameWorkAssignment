package FraneWorkPageObjectFiles;

import AbstractClass.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AbstractClass {
    public WebDriver driver;
    By HomeBtn = By.xpath("//button[normalize-space()='Home']");
    By careerStageBtn = By.xpath("(//button[@type='button'])[2]");
    By radioButton = By.xpath("//div[text() = 'Prepare and apply for jobs']");
    By Skipbtn = By.xpath("//button[normalize-space()='Skip']");
    By updatebtn = By.xpath("//button[contains(text(),'Update')]");


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void RadioBtnMatch() throws InterruptedException {

        WebElementToAppear(HomeBtn);
        driver.findElement(HomeBtn).click();
        WebElementToAppear(careerStageBtn);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -200);"); // Adjust the scroll amount as needed

        driver.findElement(careerStageBtn).click();

        WebElementToAppear(radioButton);
        // Check if the radio button is selected
        boolean isSelected = driver.findElement(radioButton).isSelected();

        // Print the result
        if (isSelected) {
            System.out.println("The radio button is selected.");
            WebElementToAppear(Skipbtn);
            Thread.sleep(1000); // Sleep for 8 seconds (adjust as needed)
            driver.findElement(Skipbtn).click();
            // Refresh the page
            driver.navigate().refresh();

        } else {
            driver.findElement(radioButton).click();
            WebElementToAppear(updatebtn);
            driver.findElement(updatebtn).click();
            // Refresh the page
            driver.navigate().refresh();

            System.out.println("The radio button is not selected.");
        }
//
        Thread.sleep(1000);

    }

    public void validateRecentSearch(List<String> selectedJobTitles) {
        WebElementToAppear(HomeBtn);
        driver.findElement(HomeBtn).click();

        // Extract job titles listed in the recent search section
        List<WebElement> recentSearchJobElements = driver.findElements(By.xpath("(//div[contains(@class,'slick-list')])[1]"));
        List<String> recentSearchJobTitles = new ArrayList<>();

        // Iterate through each job element and print the text
        for (WebElement jobElement : recentSearchJobElements) {
            String jobTitle = jobElement.getText();
            recentSearchJobTitles.add(jobTitle);

        }
        if (recentSearchJobTitles.equals(selectedJobTitles)) {
            System.out.println("Recent search section shows the same jobs in the same order.");
        } else {
            System.out.println("Recent search section does not match the selected jobs or order.");
        //    throw new AssertionError("Recent search section does not match the selected jobs or order.");

        }

    }
}
