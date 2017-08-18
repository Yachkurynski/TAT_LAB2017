package com.yammer.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {

  private static final By BUTTON_SETTING_AND_MORE_LOCATOR = By
      .xpath("//button[@title='Setting and More']");
  private static final By BUTTON_USER_PAGE_LOCATOR = By
      .xpath("//a[@class='yj-global-sidebar--nav-user--name']");
  private static final By BUTTON_CREATE_GROUP_LOCATOR = By
      .xpath("//button[@title='Create a new group']");
  private static final By SEARCH_FIELD_LOCATOR = By
      .xpath("//div[@class='yj-nav-menu--search']//input");
  private static final By BUTTON_INBOX_PAGE_LOCATOR = By.xpath("//a[@title = 'Inbox']");
  private static final By LIST_OF_GROUPS_LOCATOR = By
      .xpath("//*[contains(@class,'text-wrapper') and ancestor::li[contains(@class,'group')]]");

  public void clickSettingAndMoreButton() {
    browser.getElement(BUTTON_SETTING_AND_MORE_LOCATOR).click();
  }

  public void clickUserPageButton() {
    browser.getElement(BUTTON_USER_PAGE_LOCATOR).click();
  }

  public void clickCreateGroupButton() {
    browser.getElement(BUTTON_CREATE_GROUP_LOCATOR).click();
  }

  public void search(String searchString) {
    browser.type(SEARCH_FIELD_LOCATOR, searchString);
    browser.type(SEARCH_FIELD_LOCATOR, Keys.PAUSE);
    browser.type(SEARCH_FIELD_LOCATOR, Keys.ENTER);
  }

  public void clickInboxPageButton() {
    browser.click(BUTTON_INBOX_PAGE_LOCATOR);
  }

  public void openGroupByName(String groupName) {
    List<WebElement> groups = browser.getElements(LIST_OF_GROUPS_LOCATOR);

    for (WebElement group : groups) {
      if (group.getText().equals(groupName)) {
        group.click();
        break;
      }
    }
    browser.refresh();
  }
}
