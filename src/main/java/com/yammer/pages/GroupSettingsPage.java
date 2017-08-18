package com.yammer.pages;

import org.openqa.selenium.By;

public class GroupSettingsPage extends AbstractPage {

  private static final By BUTTON_DELETE_GROUP_LOCATOR = By.xpath("//*[@class='group-actions']/a");
  private static final By BUTTON_APPROVE_DELETE_GROUP_LOCATOR = By
      .xpath("//*[@class='buttons']/button");

  public void clickDeleteGroupButton() {
    browser.click(BUTTON_DELETE_GROUP_LOCATOR);
  }

  public void clickApproveDeleteGroupButton() {
    browser.click(BUTTON_APPROVE_DELETE_GROUP_LOCATOR);
  }
}
