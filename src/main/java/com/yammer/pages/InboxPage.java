package com.yammer.pages;

import org.openqa.selenium.By;

public class InboxPage extends AbstractPage {

  private static final By BUTTON_NEW_MESSAGE_LOCATOR = By
      .xpath("//div[@class = 'yj-create-message-button']/button");
  private static final By PRIVATE_MESSAGES_FOLDER_LOCATOR = By
      .xpath("//a[@data-feed-type = 'private']");
  private static final By ALL_MESSAGES_FOLDER_LOCATOR = By
      .xpath("//a[@data-feed-type = 'all']");
  private static final By LAST_SENT_MESSAGE_LOCATOR = By
      .xpath("//ul[@data-qaid='inbox-list-messages']/li[1]");

  public void openPrivateMessagesFolder() {
    browser.refresh();
    browser.click(PRIVATE_MESSAGES_FOLDER_LOCATOR);
  }

  public void openLastSentMessage() {
    browser.click(LAST_SENT_MESSAGE_LOCATOR);
  }

  public void clickNewMessageButton() {
    browser.click(BUTTON_NEW_MESSAGE_LOCATOR);
  }
}
