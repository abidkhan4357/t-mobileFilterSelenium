import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectFilterTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        Select select = new Select(driver);
        driver.get("https://www.t-mobile.com/tablets");
        Thread.sleep(5000);

        select.selectFilter("Brands", "Apple", "Samsung", "TCL");

        //select.selectFilter("Deals", "New");

        //select.selectFilter("Operating System", "iPadOS");

        //select.selectFilter("Brands", "all");

        //select.selectFilter("Deals", "all");

       // select.selectFilter("Operating System", "all");
        //driver.quit();
    }
}
