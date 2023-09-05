package FraneWorkPageObjectFiles;

import AbstractClass.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JobPage extends AbstractClass {

    WebDriver driver;
    // Allocating locator
    By SelectJob = By.xpath("(//div[@class='job-role' and text()='QA']/following-sibling::div[@class='company-name' and text()='MediaFire'])[1]");

    By askQuestion = By.xpath("//button[text() = 'Ask a Question']");

    By nameType = By.xpath("//div[contains(@class,'fr-element fr-view')]");

    @FindBy(xpath = "//span[contains(., 'Send')]")
    WebElement SenKey;

    String xpathToText = "//div[@class='personCardSectionWrapper__LtfOH']";
    By element = By.xpath(xpathToText);

    @FindBy(xpath = "//button[normalize-space()='Keep the Convo Going!']")
    WebElement continueConversation;


    public JobPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void ChoosingJob() {
        WebElementToAppear(SelectJob);
        driver.findElement(SelectJob).click();
    }

    public void sendMessage() {
        WebElementToAppear(askQuestion);
        driver.findElement(askQuestion).click();
        WebElementToAppear(nameType);
        driver.findElement(nameType).click();
        driver.findElement(nameType).sendKeys(INPUT_DATA);
        SenKey.click();
    }

    public boolean checkForMessage() {
        try {
            WebElementToAppear(element);
            String elementText = driver.findElement(element).getText();
            String messageToMatch = "You'll get an email when Bill responds.";
            return elementText.contains(messageToMatch);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickKeepConvoGoingButton() {
        continueConversation.click();
    }

}
