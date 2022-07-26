package StepDefenitions;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;



public class Steps {

    private static WebDriver driver = new ChromeDriver();

    By directionButton=By.xpath("//div[@id='sidebar']/descendant::a[@href='/directions']");
    By fieldFrom = By.xpath("//div[@id='sidebar']/descendant::input[@id='route_from']");
    By fieldTo = By.xpath("//div[@id='sidebar']/descendant::input[@id='route_to']");
    By roatingEnginesField= By.xpath("//div[@id='sidebar']/descendant::select[@name='routing_engines']");
    By findButton = By.xpath("//div[@id='sidebar']/descendant::input[@value='Найти'][2]");


    public Steps() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2500L));

    }

    @Given("The User is on webpage of calculation distance")
    public void the_user_is_on_calculation_webpage_of_calculation_distance() {

        System.out.println("User Is on webpage of calculation distance ");
        driver.get("https://www.openstreetmap.org/");

    }

    @And("The User click on button of looking for the route")
    public void the_user_click_on_button_of_looking_for_the_route() {
        System.out.println("The User click on button of looking for the route");

        driver.findElement(directionButton).click();

    }

    @When("The User enter data from , to")
    public void the_user_enter_data_from_to() {
        System.out.println("User enter data into From , To");
        driver.findElement(fieldFrom).sendKeys("Радищева, Київ");
        driver.findElement(fieldTo).sendKeys("Кембридж");

    }
    @And("Chose transport")
    public void chose_transport() {
        System.out.println("User chose transport");
        new Select(driver.findElement(roatingEnginesField)).selectByVisibleText("Велосипед (OSRM)");
    }

    @And("Click Go button")
    public void click_button() {
        System.out.println("User click Go button");
        driver.findElement(findButton).click();


    }

    @Then("The User should see of distance")
    public void the_user_should_be_see_of_calculated_distance() {
        System.out.println("User see result of distance calculation");

        WebElement actualDistanceElement = driver.findElement((By.xpath("//p[contains(text(),'Расстояние: ')]")));
        Assertions.assertTrue(actualDistanceElement.getText().contains("2423km"));

        //variant with Distace 2425 will fail because actual distace is 2423(below)
        //Assertions.assertTrue(actualDistanceElement.getText().contains("2425km"));

        driver.close();
        driver.quit();

    }
}
