package com.yammer.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends AbstractPage {

  private static final By USERS_FOLDER_LOCATOR = By.xpath("//button[@data-type='users']");
  private static final By GROUPS_FOLDER_LOCATOR = By.xpath("//button[@data-type='groups']");
  private static final By LIST_USERS_LOCATOR = By
      .xpath("//*[@id='tab-users']//a[@class='yammer-object yj-hovercard-link ']");
  private static final By FIRST_GROUP_NAME_LOCATOR = By
      .xpath("//a[@href and @data-resource-model = 'group']");

  public void clickUsersFolder() {
    browser.click(USERS_FOLDER_LOCATOR);
  }

  public void clickGroupsFolder() {
    browser.click(GROUPS_FOLDER_LOCATOR);
  }

  public List<WebElement> getUsersList() {
    return browser.getElements(LIST_USERS_LOCATOR);
  }

  public boolean checkGroupConsistInResult(String nameGroup) {
    String currentResultGroup = browser.getElement(FIRST_GROUP_NAME_LOCATOR).getText();
    return nameGroup.equals(currentResultGroup);
  }

  public void openFirstGroup() {
    browser.click(FIRST_GROUP_NAME_LOCATOR);
  }
}
