package com.yammer.steps;

import com.yammer.business_objects.Message;
import com.yammer.pages.CreationMessagePage;
import com.yammer.pages.HomePage;
import com.yammer.pages.InboxPage;
import com.yammer.pages.MessagePage;

public class MessageSteps {

  HomePage homePage;
  InboxPage inboxPage;
  CreationMessagePage creationMessagePage;
  MessagePage messagePage;

  public MessageSteps() {
    homePage = new HomePage();
    inboxPage = new InboxPage();
    creationMessagePage = new CreationMessagePage();
    messagePage = new MessagePage();
  }

  public void openInboxPage() {
    homePage.clickInboxPageButton();
  }

  public void createNewPrivateMessage() {
    inboxPage.clickNewMessageButton();
  }

  public void fillMessage(Message message) {
    creationMessagePage.clickPrivateMessageType();
    creationMessagePage.addParticipant(message.getAddressee());
    creationMessagePage.typeMessageBody(message.getBody());
  }

  public void sendMessage() {
    creationMessagePage.clickSendMessageButton();
  }

  public boolean checkSentMessage(Message message) {
    inboxPage.openPrivateMessagesFolder();
    inboxPage.openLastSentMessage();
    return messagePage.checkMessageForEqual(message.getAddressee(), message.getBody());
  }
}
