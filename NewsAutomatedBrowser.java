package lambdaDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NewsAutomatedBrowser {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.get("https://www.bharian.com.my/");
            System.out.println("Title: " + driver.getTitle());

            // Define section names
            String[] sections = {
                "Berita", "Sukan", "Dunia", "Hiburan",
                "Bisnes", "Rencana", "Gaya Hidup", "Sihat"
            };

            for (String section : sections) {
                try {
                    // XPath expression using visible text
                    String xpath = "//a[normalize-space(text())='" + section + "']";
                    
                    WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                    System.out.println("Clicking on section: " + section);
                    link.click();

                    Thread.sleep(2000); // Let the page load

                    System.out.println("✅ Navigated to: " + driver.getCurrentUrl());

                    driver.navigate().back(); // Return to homepage
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("❌ Failed to navigate to section: " + section);
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
