package com.yammer.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Singleton Browser-class. Include many methods for implementation some actions (click, type,
 * refresh and more). Consist javascript executors methods.
 *
 * @author Siarhei_Tuzhyk
 */
public class Browser {

  private WebDriver driver;
  private Actions actions;
  private static Browser instance;
  private static final int WAIT_ELEMENT_TIMEOUT = 10;
  private static final int PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS = 40;
  private static final int COMMAND_DEFAULT_TIMEOUT_SECONDS = 20;

  private Browser(WebDriver driver) {
    this.driver = driver;
    actions = new Actions(this.driver);
  }

  public static Browser getBrowserInstance() {
    if (instance != null) {
      return instance;
    }
    return instance = init();
  }

  private static Browser init() {
    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    WebDriver driver = new ChromeDriver();

    try {
      driver.manage().timeouts()
          .implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
      driver.manage().timeouts()
          .pageLoadTimeout(PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    } catch (TimeoutException ex) {
      System.out.println("Timeout exception");
    }
    return new Browser(driver);
  }

  public WebDriver getWebDriver() {
    return driver;
  }

  public void waitForElementVisible(By locator) {
    new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT)
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  }

  public void waitForElementEnabled(By locator) {
    new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT)
        .until(ExpectedConditions.elementToBeClickable(locator));
  }

  public void waitTextToBePresentInElement(By locator, String text) {
    new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT)
        .until(ExpectedConditions.textToBePresentInElement(driver.findElement(locator), text));
  }

  public void highlightElement(By locator) {
    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].style.border='5px solid green'", getElement(locator));
  }

  public void unHighlightElement(By locator) {
    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].style.border='0px'", getElement(locator));
  }

  public void open(String url) {
    //driver.get(url);
    driver.navigate().to(url);
  }

  public void clickElementByJsScript(By locator) {
    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click()", getElement(locator));
  }

  public void click(final By locator) {
    waitForElementEnabled(locator);
    getElement(locator).click();
  }

  public void submit(final By locator) {
    getElement(locator).submit();
  }

  public void submitAlert() {
    driver.switchTo().alert().accept();
  }

  public void type(final By locator, String text) {
    waitForElementEnabled(locator);
    highlightElement(locator);
    getElement(locator).sendKeys(text);
    unHighlightElement(locator);
  }

  public void type(final By locator, Keys key) {
    waitForElementEnabled(locator);
    highlightElement(locator);
    getElement(locator).sendKeys(key);
    unHighlightElement(locator);
  }

  public void navigateTo(final By locator) {
    actions.moveToElement(driver.findElement(locator)).build().perform();
  }

  public boolean isDisplayed(By locator) {
    return !getElements(locator).isEmpty();
  }

  public List<WebElement> getElements(By locator) {
    return driver.findElements(locator);
  }

  public WebElement getElement(By locator) {
    return driver.findElement(locator);
  }

  public void refresh() {
    driver.navigate().refresh();
  }

  public static void kill() {
    if (instance != null) {
      try {
        instance.driver.quit();
      } catch (Exception e) {
        System.err.println("Can not kill browser");
      } finally {
        instance = null;
      }
    }
  }
}
