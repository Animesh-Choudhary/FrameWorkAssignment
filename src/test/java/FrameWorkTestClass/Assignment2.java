package FrameWorkTestClass;

import FraneWorkPageObjectFiles.CareerPage;
import FraneWorkPageObjectFiles.HomePage;
import FraneWorkPageObjectFiles.JobPage;
import FraneWorkPageObjectFiles.MatchUserProfile;
import TestComponent.BaseClassTest;
import org.testng.annotations.Test;

import java.util.List;

public class Assignment2 extends BaseClassTest {


    @Test
    public void RunTest() {
        try {
            CareerPage careerPage = new CareerPage(driver);
            JobPage jobPage = new JobPage(driver);
            MatchUserProfile matchUserProfile = new MatchUserProfile(driver);
            HomePage homePage = new HomePage(driver);

            landingPage.login("Qa.animesh85@gmail.com", "Ani@8790163278");
            careerPage.CareerTABClick();
            careerPage.selectCareer();
            List<String> selectedJobTitles = careerPage.selectJobTitles();
            homePage.RadioBtnMatch();
            homePage.validateRecentSearch(selectedJobTitles);



            Thread.sleep(1000);
        } catch (Exception e) {
            // Handle exceptions as needed (e.g., log, report, rethrow)
            e.printStackTrace();
        }
    }


}