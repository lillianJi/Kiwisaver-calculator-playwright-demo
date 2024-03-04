package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.TestContext;
import org.junit.Assert;
import pages.CalculatorPage;

public class CalculatorSteps {
    CalculatorPage calculatorPage = new CalculatorPage(DriverFactory.getPage());
    TestContext testContext = new TestContext();

    @Given("^user navigates to \"([^\"]*)\"$")
    public void navigateToUrl(String url) {
        calculatorPage.navigateToUrl(url);
    }

    @When("user opens the calculator")
    public void userOpenTheCalculator() {
        calculatorPage.openTheCalculator();
    }

    @When("user enters their \"([^\"]*)\"$")
    public void userEnterInFirstQuestion(String age) {
        testContext.setAge(age);
        calculatorPage.inputAgeAndGoToNextQuestion(age);
        calculatorPage.goToNextQuestion();
        if (Integer.parseInt(age) < 16) {
            Assert.assertTrue(calculatorPage.isUnder18MessageDisplay());
            //TODO assert the message content
            calculatorPage.closeUnder18MessagePopUpWindows();
        }
        //TODO assert Things to know
    }

    @And("user uses KiwiSaver savings for {string}")
    public void userChoosePurposeForSecondQuestion(String purpose) {
        testContext.setSavingPurpose(purpose);
        calculatorPage.chooseSavingPurposeForSecondQuestion(purpose);
        //TODO assert Things to know
    }


    @And("user expects to purchase first home in {string} years")
    public void userExpectToPurchaseFirstHomeInYears(String numberOfYears) {
        testContext.setNumberOfYears(numberOfYears);
        calculatorPage.selectNumberOfYear(numberOfYears);
        //TODO verify the years based on the age, if age is 60, only max 4 years to select
        //TODO assert Things to know
    }

    @And("user is {string}")
    public void userIs(String employmentStatus) {
        testContext.setEmploymentStatus(employmentStatus);
        calculatorPage.selectEmploymentStatus(employmentStatus);
        //TODO assert Things to know
    }

    @And("user enter {string} with {string}")
    public void userEnterIncome(String income, String incomeFrequency) {
        testContext.setIncomeFrequency(incomeFrequency);
        testContext.setIncome(income);
        calculatorPage.enterIncomeWithFrequency(income, incomeFrequency);
        //TODO assert Things to know
    }


    @And("user enters current KiwiSaver {string}")
    public void userEntersCurrentKiwiSaverBalance(String balance) {
        testContext.setKiwiSaverBalance(balance);
        calculatorPage.enterCurrentKiwiSaverBalance(balance);
        //TODO assert Things to know
    }

    @And("user choose contribution rate {string}")
    public void userChooseContributionRate(String rate) {
        testContext.setContributionRate(rate);
        calculatorPage.selectContributionRate(rate);
    }

    @And("user is in {string} currently")
    public void userIsInCurrently(String typeOfFund) {
        testContext.setTypeOfFund(typeOfFund);
        calculatorPage.chooseFundType(typeOfFund);

    }

    @Then("It should show user the projection for growth correctly")
    public void itShouldShowUserTheProjectionForGrowthCorrectly() {
        Assert.assertTrue(calculatorPage.isProjectionDisplayed());
        //calculate the value for projection
        if (testContext.savingPurpose.equals("First Home")){
            String withDrawAmount = "89";
            String retirementIncome = "1";
            String numberOfYears = testContext.numberOfYears.replaceAll("[^0-9]", "");
            String firstHomeProjectText = "In "+numberOfYears+" years time we estimate you could withdraw $" + withDrawAmount +
                    " from your KiwiSaver savings to help with the purchase of your first home." +
                    " Along with this, you could have a retirement income of $" + retirementIncome +
                    " p.w if you were to retire at 65. This income would last you until age 90 and is not including NZ Super.";
            Assert.assertEquals(firstHomeProjectText,calculatorPage.getTextOfProjection());
        }else {
            String retirementIncome = "1";
            String retirementProjectText = "You could have a retirement income of $" + retirementIncome +
                    " p.w if you were to retire at 65. This income would last you until age 90 and is not including NZ Super.";
            Assert.assertEquals(retirementProjectText,calculatorPage.getTextOfProjection());
        }

    }

    @Then("It should pop up we are here to help message")
    public void itShouldPopUpWeAreHereToHelpMessage() {
        Assert.assertTrue(calculatorPage.isAbove65MessageDisplayed());
        calculatorPage.closeAbove65MessagePopWindows();
    }
}
