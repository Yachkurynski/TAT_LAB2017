package com.yammer.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GroupPage extends AbstractPage {

  private static final By BUTTON_JOIN_GROUP_LOCATOR = By
      .xpath("//button[@data-qaid = 'join_group_button']");
  private static final By BUTTON_LEAVE_GROUP_LOCATOR = By
      .xpath("//button[@data-qaid = 'leave_group_button']");
  private static final By LAST_POST_LOCATOR = By.xpath("//li[@data-qaid = 'thread'][1]");
  private static final By BUTTON_NEW_POST_LOCATOR = By
      .xpath("//button[parent::li[@data-name = 'updatePublisher']]");
  private static final By TEXT_AREA_NEW_POST_LOCATOR = By
      .xpath("//textarea[ancestor::div[contains(@class, 'update-publisher')]]");
  private static final By BUTTON_POST_LOCATOR = By
      .xpath("//button[@data-qaid = 'post_button' and ancestor::div[contains(@class, 'update')]]");
  private static final By BUTTON_SHARE_POST_LOCATOR = By
      .xpath("//button[contains(@class, 'share') and ancestor::div[contains(@class, 'starter')]]");
  private static final By BUTTON_POST_OPTIONS_LOCATOR = By
      .xpath("//button[contains(@class, 'menu') and ancestor::div[contains(@class, 'starter')]]");
  private static final By LAST_REPLY_LOCATOR = By
      .xpath("//li[contains(@class, 'thread-reply-list')][last()]");
  private static final By BUTTON_REPLY_POST_LOCATOR = By
      .xpath("//*[@data-qaid = 'reply_action' and ancestor::div[contains(@class, 'starter')]]");
  private static final By TEXT_FIELD_REPLY_LOCATOR = By
      .xpath("//textarea[ancestor::div[contains(@class, 'reply-publisher')]]");
  private static final By BUTTON_REPLY_LOCATOR = By
      .xpath("//button[@data-qaid = 'post_button' and ancestor::div[contains(@class, 'reply')]]");
  private static final By GROUP_SETTINGS_LOCATOR = By
      .xpath("//*[@data-qaid=\"settings_button\"]");
  private static final By BUTTON_REPLY_OPTIONS_LOCATOR = By
      .xpath("//button[contains(@class, 'menu') and ancestor::div[contains(@class, 'replies')]]");
  private static final By BUTTON_DELETE_LOCATOR = By
      .xpath("//button[@title='delete this message']");
  private static final By BUTTON_UNBOOKMARK_POST_LOCATOR = By
      .xpath("//button[@title='remove this message from bookmarks']");
  private static final By BUTTON_BOOKMARK_POST_LOCATOR = By
      .xpath("//button[@title='bookmark this message']");
  private static final By GROUP_NAME_LOCATOR = By.xpath("//span[contains(@class, 'group-name')]");
  private static final By MESSAGE_DELETE_GROUP_LOCATOR = By.xpath("//*[@class='notice']");
  private static final By MESSAGE_TEXT_LOCATOR = By.xpath(".//span[@data-qaid = 'message-text']");

  public void clickJoinGroup() {
    browser.click(BUTTON_JOIN_GROUP_LOCATOR);
  }

  public void clickLeaveGroup() {
    browser.click(BUTTON_LEAVE_GROUP_LOCATOR);
  }

  public void clickCreatePost() {
    browser.click(BUTTON_NEW_POST_LOCATOR);
  }

  public void clickPostButton() {
    browser.click(BUTTON_POST_LOCATOR);
    browser.refresh();
  }

  public void clickCreateReplyButtonForLastPost() {
    browser.refresh();
    browser.getElement(BUTTON_REPLY_POST_LOCATOR).click();
  }

  public void clickSendReplyButton() {
    browser.click(BUTTON_REPLY_LOCATOR);
  }

  public void setPostTextArea(String content) {
    browser.type(TEXT_AREA_NEW_POST_LOCATOR, content);
  }

  public void setReplyTextField(String content) {
    browser.type(TEXT_FIELD_REPLY_LOCATOR, content);
  }

  public void clickGroupSettingsButton() {
    browser.click(GROUP_SETTINGS_LOCATOR);
  }

  public void clickLastReplyActionsButton() {
    browser.getElement(LAST_REPLY_LOCATOR).findElement(BUTTON_REPLY_OPTIONS_LOCATOR).click();
  }

  public void clickDeleteButton() {
    browser.click(BUTTON_DELETE_LOCATOR);
    browser.submitAlert();
  }

  public void clickOptions() {
    browser.refresh();
    browser.click(BUTTON_POST_OPTIONS_LOCATOR);
  }

  public void clickAddToBookmarkButton() {
    browser.click(BUTTON_BOOKMARK_POST_LOCATOR);
  }

  public void clickRemoveFromBookmarkButton() {
    browser.click(BUTTON_UNBOOKMARK_POST_LOCATOR);
  }

  public String getGroupName() {
    return browser.getElement(GROUP_NAME_LOCATOR).getText();
  }

  public void clickShareLastPostButton() {
    browser.click(BUTTON_SHARE_POST_LOCATOR);
  }

  public String getDeletedGroupIdentifier() {
    return browser.getElement(MESSAGE_DELETE_GROUP_LOCATOR).getText();
  }

  public boolean checkPostInGroup() {
    return browser.isDisplayed(LAST_POST_LOCATOR);
  }

  public boolean isMember() {
    browser.refresh();

    try {
      return browser.getElement(BUTTON_LEAVE_GROUP_LOCATOR) != null;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  public String getLastCommentBody() {
    browser.refresh();
    return browser.getElement(LAST_REPLY_LOCATOR).findElement(MESSAGE_TEXT_LOCATOR).getText();
  }

  public WebElement getLastPostComment() {
    browser.refresh();

    try {
      return browser.getElement(LAST_POST_LOCATOR).findElement(LAST_REPLY_LOCATOR);
    } catch (NoSuchElementException ex) {
      return null;
    }
  }

  public String getLastPostBody() {
    browser.refresh();
    return browser.getElement(LAST_POST_LOCATOR).findElement(MESSAGE_TEXT_LOCATOR).getText();
  }

  public WebElement getLastPost() {
    browser.refresh();

    try {
      return browser.getElement(LAST_POST_LOCATOR);
    } catch (NoSuchElementException ex) {
      return null;
    }
  }
}
