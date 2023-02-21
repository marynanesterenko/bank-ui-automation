package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage{

    @FindBy (xpath = "//*[text()=' Sign Up Here']")
    WebElement signUpHereLink;

    public void verifySignUpHereLinkPresent (){
        Assert.assertTrue("the Sign Up Link is not present on the page", signUpHereLink.isDisplayed());
    }

    public void clickOnSignUpHereLink() {
        signUpHereLink.click();
    }

    public void verifyUserIsOnFirstSignUpPage() {
        String firstSignUpFormLink = driver.getCurrentUrl();
        Assert.assertTrue("The Sign Up here Link did not work", firstSignUpFormLink.equalsIgnoreCase("http://18.116.88.132:8080/bank/signup"));
    }
}
