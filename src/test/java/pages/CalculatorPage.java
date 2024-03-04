package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.Assert;
import utils.ConfigReader;
import utils.WebActions;

public class CalculatorPage {
    private Page page;
    private final Locator OPEN_CALCULATOR;
    private final Locator AGE_LOCATOR;
    private final Locator NEXT_QUESTION_BUTTON;
    private final Locator FIRST_HOME_OPTION_LOCATOR;
    private final Locator RETIREMENT_OPTION_LOCATOR;
    private final Locator NUMBER_OF_YEARS_LOCATOR;
    private final Locator EMPLOYMENT_STATUS_LOCATOR;
    private final Locator INCOME_FREQUENCY_LOCATOR;
    private final Locator INCOME_INPUT_LOCATOR;
    private final Locator KIWISAVER_BALANCE_LOCATOR;
    private final Locator CONTRIBUTION_RATE_LOCATOR;
    private final Locator FUND_TYPE_LOCATOR;
    private final Locator UNDER_18_MESSAGE;
    private final Locator UNDER_18_MESSAGE_YES_BUTTON;
    private final Locator ABOVE_65_MESSAGE;
    private final Locator ABOVE_65_MESSAGE_CLOSE_BUTTON;
    public final Locator PROJECTION_TITLE;
    private final Locator PROJECTION_TEXT_LOCATOR;


    public CalculatorPage(Page page) {
        this.page = page;
        this.OPEN_CALCULATOR = page.getByText("Open the calculator");
        this.NEXT_QUESTION_BUTTON = page.getByText("Next  Question");
        this.AGE_LOCATOR = page.locator("#text-QUESTION_AGE");
        this.FIRST_HOME_OPTION_LOCATOR= page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("First Home"));
        this.RETIREMENT_OPTION_LOCATOR=page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Retirement"));
        this.NUMBER_OF_YEARS_LOCATOR = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Choose option"));
        this.EMPLOYMENT_STATUS_LOCATOR = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Choose option")).last();
        this.INCOME_FREQUENCY_LOCATOR = page.getByText("per year");
        this.INCOME_INPUT_LOCATOR=page.locator("#text-QUESTION_INCOME");
        this.KIWISAVER_BALANCE_LOCATOR=page.locator("#text-QUESTION_KIWISAVER_BALANCE");
        this.CONTRIBUTION_RATE_LOCATOR = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Choose option")).last();
        this.FUND_TYPE_LOCATOR = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Choose fund"));
        this.UNDER_18_MESSAGE = page.getByRole(AriaRole.DIALOG,new Page.GetByRoleOptions().setName("WE’RE HERE TO HELP"));
        this.UNDER_18_MESSAGE_YES_BUTTON = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Ok, I understand"));
        this.ABOVE_65_MESSAGE = page.getByRole(AriaRole.DIALOG,new Page.GetByRoleOptions().setName("WE’RE HERE TO HELP"));
        this.ABOVE_65_MESSAGE_CLOSE_BUTTON = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Close")).first();
        this.PROJECTION_TITLE = page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Your current projection"));
        this.PROJECTION_TEXT_LOCATOR = page.locator("p:has-text(\"not including NZ Super\")");

    }

    public void navigateToUrl(String url) {
        this.page.navigate(ConfigReader.getProperty(url));
    }

    public void openTheCalculator(){
        this.OPEN_CALCULATOR.click();
    }

    public void goToNextQuestion(){
        this.NEXT_QUESTION_BUTTON.click();
    }

    public void inputAgeAndGoToNextQuestion(String age){
        this.AGE_LOCATOR.fill(age);
    }

    public void chooseSavingPurposeForSecondQuestion(String purpose){
        if (purpose.equals("First Home")){
            this.FIRST_HOME_OPTION_LOCATOR.click();
        }else {
            this.RETIREMENT_OPTION_LOCATOR.click();
        }
    }

    public void selectNumberOfYear(String numberOfYear){
        this.NUMBER_OF_YEARS_LOCATOR.click();
        this.page.getByText(numberOfYear, new Page.GetByTextOptions().setExact(true)).click();
    }

    public void selectEmploymentStatus(String employmentStatus){
        this.EMPLOYMENT_STATUS_LOCATOR.click();
        this.page.getByText(employmentStatus, new Page.GetByTextOptions().setExact(true)).click();
    }

    public void enterIncomeWithFrequency(String income, String frequency){
        if (!frequency.equals("per year")){
            this.INCOME_FREQUENCY_LOCATOR.click();
            this.page.getByText(frequency, new Page.GetByTextOptions().setExact(true)).click();
        }
        this.INCOME_INPUT_LOCATOR.fill(income);
        this.NEXT_QUESTION_BUTTON.last().click();
    }

    public void enterCurrentKiwiSaverBalance(String balance){
        this.KIWISAVER_BALANCE_LOCATOR.fill(balance);
        this.NEXT_QUESTION_BUTTON.last().click();
    }
    public void selectContributionRate(String rate){
        this.CONTRIBUTION_RATE_LOCATOR.click();
        this.page.getByText(rate, new Page.GetByTextOptions().setExact(true)).click();
    }

    public void chooseFundType(String typeOfFund){
        this.FUND_TYPE_LOCATOR.click();
        this.page.getByText(typeOfFund, new Page.GetByTextOptions().setExact(true)).click();
    }

    public boolean isUnder18MessageDisplay(){
        return this.UNDER_18_MESSAGE.isVisible();
    }

    public String getTextFromUnder18MessagePopUp(){
        return this.UNDER_18_MESSAGE.innerText();
    }

    public void closeUnder18MessagePopUpWindows(){
        this.UNDER_18_MESSAGE_YES_BUTTON.click();
    }

    public boolean isAbove65MessageDisplayed(){
        return this.ABOVE_65_MESSAGE.isVisible();
    }

    public void closeAbove65MessagePopWindows(){
        this.ABOVE_65_MESSAGE_CLOSE_BUTTON.click();
    }

    public boolean isProjectionDisplayed(){
        WebActions.waitUntilElementDisplayed(this.PROJECTION_TITLE,4000);
        return this.PROJECTION_TITLE.isVisible();
    }

    public String getTextOfProjection(){
        return this.PROJECTION_TEXT_LOCATOR.innerText();
    }

}
