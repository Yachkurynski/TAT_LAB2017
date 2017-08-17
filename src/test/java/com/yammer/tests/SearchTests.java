package com.yammer.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.yammer.business_objects.Group;
import com.yammer.business_objects.User;
import com.yammer.steps.LoginSteps;
import com.yammer.steps.SearchSteps;
import com.yammer.steps.UserSteps;
import com.yammer.utils.Browser;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class SearchTests {

  private Browser browser;
  private LoginSteps loginSteps;
  private SearchSteps searchSteps;
  private UserSteps userSteps;
  private User user;

  @BeforeClass
  public void beforeClass() {
    user = new User("ararat@epam.com", "conditional");
  }

  @Before
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

  @After
  public void tearDown() throws Exception {
    Browser.kill();
  }
}
