package com.yammer.pages;

import org.openqa.selenium.By;

public class CreationGroupPage extends AbstractPage {

  private static final By BUTTON_CREATE_GROUP_LOCATOR = By
      .xpath("//button[contains(@class, 'submit-button')]");
  private static final By STATUS_CHECKING_GROUP_NAME_LOCATOR = By
      .xpath("//span[parent::div[contains(@class, 'name-status')]]");
  private static final By FIELD_GROUP_NAME_LOCATOR = By
      .xpath("//input[@class='yj-create-group-form--name-input']");
  private static final By BUTTON_PRIVATE_GROUP_LOCATOR = By
      .xpath("//input[contains(@aria-label, 'Private Access')]");

  public void typeNameOfGroup(String nameOfGroup) {
    browser.type(FIELD_GROUP_NAME_LOCATOR, nameOfGroup);
  }

  public void choosePrivateGroupType() {
    browser.click(BUTTON_PRIVATE_GROUP_LOCATOR);
  }

  public void clickCreateGroupButton() {
    browser.waitTextToBePresentInElement(STATUS_CHECKING_GROUP_NAME_LOCATOR, "Name is available.");
    browser.click(BUTTON_CREATE_GROUP_LOCATOR);
  }
}
