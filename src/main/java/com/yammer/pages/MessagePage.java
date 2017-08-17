package com.yammer.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MessagePage extends AbstractPage {

  private static final By LIST_PARTICIPANTS_LOCATOR = By
      .xpath("//ul[@class='yj-conversation-participants--list']/li");
  private static final By BODY_MESSAGE_LOCATOR = By
      .xpath("//span[@class='yj-message-list-item--body-message yj-message']");

  private List<WebElement> getAddresseeList() {
    return browser.getElements(LIST_PARTICIPANTS_LOCATOR);
  }

  private boolean isAddresseeInParticipants(String addressee) {
    for (WebElement user : getAddresseeList()) {
      if (user.getText().contains(addressee)) {
        return true;
      }
    }
    return false;
  }

  public boolean checkMessageForEqual(String addressee, String body) {
    String messageBody = browser.getElement(BODY_MESSAGE_LOCATOR).getText();
    return isAddresseeInParticipants(addressee) && messageBody.equals(body);
  }
}
