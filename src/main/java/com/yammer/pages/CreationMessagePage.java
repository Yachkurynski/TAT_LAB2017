package com.yammer.pages;

import org.openqa.selenium.By;

public class CreationMessagePage extends AbstractPage {

  private static final By BUTTON_PRIVATE_MESSAGE_LOCATOR = By
      .xpath("//li[@data-name='userPublisher']/button");
  private static final By FIELD_ADD_PARTICIPANTS_LOCATOR = By.xpath(
      "//div[contains(@class,'yj-global-recipient-input')]//input[@placeholder='Add participants']");
  private static final By TEXT_AREA_MESSAGE_LOCATOR = By
      .xpath("//div[contains(@class,'yj-global-private-publisher')]//textarea");
  private static final By BUTTON_SEND_MESSAGE_LOCATOR = By
      .xpath("//button[@class='yj-global-submit-button yj-btn']");
  private static final By FIRST_PARTICIPANT_LOCATOR = By
      .xpath("//div[@class='yj-ta-result-list']//span[@class='yj-ta-name']/span");

  public void clickPrivateMessageType() {
    browser.click(BUTTON_PRIVATE_MESSAGE_LOCATOR);
  }

  public void addParticipant(String participant) {
    browser.type(FIELD_ADD_PARTICIPANTS_LOCATOR, participant);
    browser.click(FIRST_PARTICIPANT_LOCATOR);
  }

  public void typeMessageBody(String body) {
    browser.type(TEXT_AREA_MESSAGE_LOCATOR, body);
  }

  public void clickSendMessageButton() {
    browser.click(BUTTON_SEND_MESSAGE_LOCATOR);
  }
}
