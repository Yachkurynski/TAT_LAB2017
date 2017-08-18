package com.yammer.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.yammer.business_objects.Group;
import com.yammer.business_objects.User;
import com.yammer.steps.LoginSteps;
import com.yammer.steps.SearchSteps;
import com.yammer.steps.UserSteps;
import com.yammer.utils.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests {

  private Browser browser;
  private LoginSteps loginSteps;
  private SearchSteps searchSteps;
  private UserSteps userSteps;
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
    searchSteps = new SearchSteps();
    userSteps = new UserSteps();
    browser.open("https://yammer.com/epam.com");
    loginSteps.login(user);
  }

  @Test
  public void searchGroupTest() {
    user.setSearchQuery("Wild Bamboleos");
    searchSteps.search(user.getSearchQuery());
    searchSteps.openGroupsFolder();
    assertTrue(searchSteps.checkGroupInResult(user.getSearchQuery()));
  }

  @Test
  public void unfollowTest() {
    user.setSearchQuery("Maksim Zaretski");
    searchSteps.search(user.getSearchQuery());
    searchSteps.chooseUser();
    userSteps.follow();
    userSteps.goToProfile();
    userSteps.unfollow(user.getSearchQuery());
    browser.refresh();
    assertFalse(userSteps.checkUserInFollowings(user.getSearchQuery()));
  }

  @Test
  public void followTest() {
    user.setSearchQuery("Maksim Zaretski");
    searchSteps.search(user.getSearchQuery());
    searchSteps.chooseUser();
    userSteps.follow();
    userSteps.goToProfile();
    assertTrue(userSteps.checkUserInFollowings(user.getSearchQuery()));
    browser.refresh();
    userSteps.unfollow(user.getSearchQuery());
  }

  @AfterMethod
  public void tearDown() throws Exception {
    Browser.kill();
  }
}
