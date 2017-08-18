package com.yammer.tests;

import static org.testng.Assert.assertTrue;

import com.yammer.business_objects.Group;
import com.yammer.business_objects.Message;
import com.yammer.business_objects.User;
import com.yammer.steps.LoginSteps;
import com.yammer.steps.MessageSteps;
import com.yammer.utils.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MessageTests {

  private Browser browser;
  private LoginSteps loginSteps;
  private MessageSteps messageSteps;
  private User user;

  @BeforeClass
  public void beforeClass() {
    /*
    String name = System.getProperty("username");
    String password = System.getProperty("password");
    user = new User(name, password);
    */
    user = new User("araratk@epam.com","promololol");
  }

  @BeforeMethod
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

  @AfterMethod
  public void tearDown() throws Exception {
    Browser.kill();
  }
}
