import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Select {
    private final WebDriver driver;
    private final Map<String, WebElement> filterOptionsMap;

    public Select(WebDriver driver) {
        this.driver = driver;
        this.filterOptionsMap = new HashMap<>();
    }

    public void selectFilter(String filterType, String... options) {
        WebElement filterGroup = driver.findElement(By.xpath("//legend[contains(text(), '" + filterType + "')]"));
        filterGroup.click();

        WebElement filterPanel = filterOptionsMap.computeIfAbsent(filterType,
                k -> driver.findElement(By.xpath("//legend[contains(text(), '" + filterType + "')]/ancestor::mat-expansion-panel")));

        if (options.length == 1 && options[0].equalsIgnoreCase("all")) {
            selectAllOptions(filterPanel);
        } else {
            selectSpecificOptions(filterPanel, options);
        }
    }

    private void selectAllOptions(WebElement filterPanel) {
        List<WebElement> allOptions = filterPanel.findElements(By.xpath(".//div[@role='group']//mat-checkbox"));

        for (WebElement option : allOptions) {
            WebElement checkbox = option.findElement(By.xpath(".//span[@class='filter-display-name']"));
            checkbox.click();
        }
    }

    private void selectSpecificOptions(WebElement filterPanel, String... options) {
        for (String option : options) {
            if (!option.equalsIgnoreCase("all")) {
                String checkboxLocator = ".//span[contains(@class, 'filter-display-name') and normalize-space(text()) = '" + option + "']";
                WebElement filterOption = filterPanel.findElement(By.xpath(checkboxLocator));
                filterOption.click();
            }
        }
    }
}
