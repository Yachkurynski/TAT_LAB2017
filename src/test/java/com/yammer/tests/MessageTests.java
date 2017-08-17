package com.yammer.tests;

import static org.junit.Assert.assertTrue;

import com.yammer.business_objects.Message;
import com.yammer.business_objects.User;
import com.yammer.steps.LoginSteps;
import com.yammer.steps.MessageSteps;
import com.yammer.utils.Browser;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MessageTests {

  private Browser browser;
  private LoginSteps loginSteps;
  private MessageSteps messageSteps;
  private User user;

  @BeforeClass
  public void beforeClass() {
    user = new User("ararat@epam.com", "conditional");
  }

  @Before
  public void setUp() throws Exception {
    browser = Browser.getBrowserInstance();
    loginSteps = new LoginSteps();
    messageSteps = new MessageSteps();
    browser.open("https://yammer.com/epam.com");
    loginSteps.login(user);
  }

  @Test
  public void privateMessageTest() {
    messageSteps.openInboxPage();
    user.setMessage(new Message("Maksim Zaretski", "test"));
    messageSteps.createNewPrivateMessage();
    messageSteps.fillMessage(user.getMessage());
    messageSteps.sendMessage();
    assertTrue(messageSteps.checkSentMessage(user.getMessage()));
  }

  @After
  public void tearDown() throws Exception {
    Browser.kill();
  }
}
