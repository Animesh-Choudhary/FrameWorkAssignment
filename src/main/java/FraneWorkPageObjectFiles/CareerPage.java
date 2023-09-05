package FraneWorkPageObjectFiles;

import AbstractClass.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CareerPage extends AbstractClass {
    public WebDriver driver;

    private By careerClickBtn = By.xpath("//button[text()='Career']");
    private By dropdown = By.xpath("//ul[@role='menu']");
    private By careerPathsElement = By.xpath("(//p[normalize-space()='Career Paths'])[1]");

    // Element for selecting job titles
    private By jobTitleElement;

    // List to store job title XPaths
    private List<String> jobTitleXPaths;

    public CareerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Initialize job title XPaths and jobTitleElement
        jobTitleXPaths = new ArrayList<>();
        jobTitleXPaths.add("(//a[@class='career-card'])[2]");
        jobTitleXPaths.add("(//a[@class='career-card'])[3]");
        jobTitleXPaths.add("(//a[@class='career-card'])[4]");

        // Set the jobTitleElement to the first XPath by default
        jobTitleElement = By.xpath(jobTitleXPaths.get(0));
    }

    public void CareerTABClick() {
        WebElementToAppear(careerClickBtn);
        driver.findElement(careerClickBtn).click();
    }

    public void selectJob() {
        WebElement listItems = driver.findElement(By.className("productsList"));
        // Loop through the list items to find and select the one with text "Jobs"
        if (listItems.getText().contains("Jobs")) {
            listItems.click();
            System.out.println("From Career TAB selected: Jobs as per Instructed");
        }
    }

    public void selectCareer() {
        WebElementToAppear(dropdown);
        WebElement dropdownList = driver.findElement(dropdown);
        List<WebElement> dropdownItems = dropdownList.findElements(By.tagName("li"));

        // Find all the <li> elements inside the dropdown
        String userDetails = "";

        // Loop through the elements and print the desired format
        for (WebElement item : dropdownItems) {
            String itemText = item.getText().trim();
            if (!itemText.isEmpty()) {
                userDetails += itemText + "\n";
            }
        }

        if (userDetails.contains("Career Paths")) {
            WebElementToAppear(careerPathsElement);
            driver.findElement(careerPathsElement).click();
            System.out.println("Selected Career Paths based on dropdown text.");
        } else {
            // If "Career Paths" is not available, print the selected element
            System.out.println("Selected element based on dropdown text: " + userDetails);
        }
    }

    public List<String> selectJobTitles() {
        List<String> selectedJobTitles = new ArrayList<>();

        // Loop through the XPaths and extract job titles
        for (String jobTitleXPath : jobTitleXPaths) {
            try {
                jobTitleElement = By.xpath(jobTitleXPath);
                WebElementToAppear(jobTitleElement);
                String jobTitle = driver.findElement(jobTitleElement).getText();
                // Add the job title to the list
                selectedJobTitles.add(jobTitle);

                // Click on the job card to view details (add your code here)

                // Go back to the career page
                driver.navigate().back();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return selectedJobTitles;
    }


}