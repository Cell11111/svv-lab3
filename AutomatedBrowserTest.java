package lambdaDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AutomatedBrowserTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.get("https://data.gov");
            System.out.println("Page Title: " + driver.getTitle());

            String[][] navLinks = {
                {"Data", "https://catalog.data.gov"}, // ✅ correct expected URL
                {"Metrics", "https://data.gov/metrics/"},
                {"Open Government", "https://data.gov/open-gov/"},
                {"Contact", "https://data.gov/contact/"}
            };

            for (String[] link : navLinks) {
                String linkText = link[0];
                String expectedUrl = link[1];

                try {
                    // Wait for the element to be clickable (matches <span> inside <a>)
                    WebElement navLink = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[.//span[text()='" + linkText + "']]")
                    ));

                    System.out.println("Clicking on: " + linkText);
                    navLink.click();

                    // Wait for the URL to change and validate
                    Thread.sleep(2000); // You can also use wait.until(ExpectedConditions.urlContains(...)) here

                    String currentUrl = driver.getCurrentUrl();
                    if (currentUrl.startsWith(expectedUrl)) {
                        System.out.println("✅ Navigated to: " + currentUrl);
                    } else {
                        System.out.println("❌ Navigation mismatch. Expected: " + expectedUrl + " but got: " + currentUrl);
                    }

                    driver.navigate().back();
                    Thread.sleep(1000); // Wait for page to reload
                } catch (Exception e) {
                    System.out.println("❌ Could not click on link: " + linkText);
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
