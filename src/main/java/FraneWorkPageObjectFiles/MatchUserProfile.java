package FraneWorkPageObjectFiles;

import AbstractClass.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MatchUserProfile extends AbstractClass {

    WebDriver driver;
    String userProfileXPath = "//p[@role='heading']";
    By userProfileElement = By.xpath(userProfileXPath);

    String nameMatchXPath = "//div[@class='rich-text ql-editor']";
    By nameMatchElement = By.xpath(nameMatchXPath);

    String conversationXPath = "//div[@class='job-connection-request-info']";
    By conversationElement = By.xpath(conversationXPath);

    @FindBy(xpath = "//li[@aria-label='Conversation with Bill OKon']")
    WebElement dropdown;

    public MatchUserProfile(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean matchUserProfile() {
        try {
            WebElementToAppear(userProfileElement);
            String userProfileName = driver.findElement(userProfileElement).getText().split(" ")[0];

            if (INPUT_DATA.isEmpty()) {
                System.out.println("InputData is empty. Skipping text comparison.");
                return true;
            }

            WebElementToAppear(nameMatchElement);
            String nameMatchText = driver.findElement(nameMatchElement).getText().trim();
            INPUT_DATA = INPUT_DATA.trim();

            if (nameMatchText.equals(INPUT_DATA)) {
                System.out.println("ConversationHeaderName is equal to InputData: " + INPUT_DATA);
            } else {
                System.out.println("ConversationHeaderName is not equal to InputData and It is returning: " + nameMatchText);
            }

            WebElementToAppear(conversationElement);
            String conversationText = driver.findElement(conversationElement).getText();

            if (conversationText.contains(userProfileName)) {
                System.out.println("First name '" + userProfileName + "' is present in the conversation.");
                System.out.println("ConversationUser is matching with profile user.");
                return true;
            } else {
                System.out.println("First name '" + userProfileName + "' is not present in the conversation.");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void performAdditionalActions() {
        List<WebElement> dropdownItems = dropdown.findElements(By.tagName("li"));

        // Find all the <li> elements inside the dropdown
        String userDetails = "";

        // Loop through the elements and print the desired format
        for (WebElement item : dropdownItems) {
            String itemText = item.getText().trim();
            if (!itemText.isEmpty()) {
                userDetails += itemText + "\n";
            }
        }

        // Print "End of all information" after all users
        System.out.println(" Other Available User Informations are :");
        System.out.println(userDetails);
        System.out.println(" ");
        System.out.println("End of all information");
    }
}
