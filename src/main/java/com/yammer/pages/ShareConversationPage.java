package com.yammer.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ShareConversationPage extends AbstractPage {

  private static final By FIELD_ADDRESSEE_GROUP_LOCATOR = By
      .xpath("//input[@placeholder='Select a group']");
  private static final By TEXTAREA_BODY_POST_LOCATOR = By
      .xpath("//div[@class='yj-tapf-textarea-container']/textarea");
  private static final By BUTTON_SHARE_LOCATOR = By.xpath("//button[@data-qaid='post_button']");

  public void setAddresseeGroup(String nameGroup) {
    browser.type(FIELD_ADDRESSEE_GROUP_LOCATOR, nameGroup);
    browser.type(FIELD_ADDRESSEE_GROUP_LOCATOR, Keys.TAB);
  }

  public void setBodyPost(String body) {
    browser.type(TEXTAREA_BODY_POST_LOCATOR, body);
  }

  public void clickSharePost() {
    browser.click(BUTTON_SHARE_LOCATOR);
  }
}
