package FrameWorkTestClass;

import TestComponent.BaseClassTest;
import FraneWorkPageObjectFiles.CareerPage;
import FraneWorkPageObjectFiles.JobPage;
import FraneWorkPageObjectFiles.MatchUserProfile;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Assignment1  extends BaseClassTest {


    @Test
    public void RunTest() {
        try {
            CareerPage careerPage = new CareerPage(driver);
            JobPage jobPage = new JobPage(driver);
            MatchUserProfile matchUserProfile = new MatchUserProfile(driver);

            landingPage.login("Qa.animesh85@gmail.com", "Ani@8790163278");


            careerPage.CareerTABClick();
            careerPage.selectJob();
            jobPage.ChoosingJob();

            boolean messageAlreadyPresent = jobPage.checkForMessage();
            boolean userMatchesProfile = false;

            if (!messageAlreadyPresent) {
                jobPage.sendMessage();
                messageAlreadyPresent = jobPage.checkForMessage();
                Assert.assertTrue(messageAlreadyPresent, "Message was not sent successfully.");

                jobPage.clickKeepConvoGoingButton();
                userMatchesProfile = matchUserProfile.matchUserProfile();
                Assert.assertTrue(userMatchesProfile, "User profile does not match the expected criteria.");
            } else {
                System.out.println("Query already submitted, you can continue with messages.");
                jobPage.clickKeepConvoGoingButton();
                userMatchesProfile = matchUserProfile.matchUserProfile();
                Assert.assertTrue(userMatchesProfile, "User profile does not match the expected criteria.");
            }

            if (userMatchesProfile) {
                matchUserProfile.performAdditionalActions();
            }
            Thread.sleep(3000);
        } catch (Exception e) {
            // Handle exceptions as needed (e.g., log, report, rethrow)
            e.printStackTrace();
        }
    }
}