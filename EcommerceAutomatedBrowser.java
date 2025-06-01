package lambdaDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class EcommerceAutomatedBrowser {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.get("https://www.pixeltouch.com.my/");
            System.out.println("Page Title: " + driver.getTitle());

            String[] menuItems = {
                "Home",
                "Our Blog",
                "Contact Us",
                "Our Social Media",
                "Refund and Returns Policy"
            };

            for (String item : menuItems) {
                try {
                    // XPath to match the span inside <a> tag with exact visible text
                    String xpath = "//a[span[normalize-space(text())='" + item + "']]";
                    WebElement menuLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

                    System.out.println("Clicking on menu item: " + item);
                    menuLink.click();

                    Thread.sleep(2000); // Let the page load
                    System.out.println("✅ Navigated to: " + driver.getCurrentUrl());

                    driver.navigate().back();
                    Thread.sleep(1000); // Wait for homepage to reload
                } catch (Exception e) {
                    System.out.println("❌ Failed to navigate to: " + item);
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
