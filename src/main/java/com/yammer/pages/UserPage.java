package com.yammer.pages;

import org.openqa.selenium.By;

public class UserPage extends AbstractPage {

  private static final By BUTTON_FOLLOW_LOCATOR = By
      .xpath("//*[@class='yj-user-profile-header--follow-button']/button");

  public void clickFollowButton() {
    browser.click(BUTTON_FOLLOW_LOCATOR);
  }
}
