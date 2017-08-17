package com.yammer.tests;

import static org.testng.Assert.assertTrue;

import com.yammer.business_objects.Group;
import com.yammer.business_objects.User;
import com.yammer.steps.GroupSteps;
import com.yammer.steps.LoginSteps;
import com.yammer.steps.PostSteps;
import com.yammer.steps.UserSteps;
import com.yammer.utils.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BookmarkTests {

  private Browser browser;
  private GroupSteps groupSteps;
  private LoginSteps loginSteps;
  private PostSteps postSteps;
  private UserSteps userSteps;
  private User user;
  private Group wildBamboleosGroup;

  @BeforeMethod
  public void setUp() throws Exception {
    user = new User("ararat@epam.com", "conditional");
    wildBamboleosGroup = new Group("Wild Bamboleos");
    browser = Browser.getBrowserInstance();
    groupSteps = new GroupSteps();
    postSteps = new PostSteps();
    userSteps = new UserSteps();
    loginSteps = new LoginSteps();
    browser.open("https://yammer.com/epam.com");
    loginSteps.login(user);
  }

  @Test
  public void bookmarkTest() {
    groupSteps.openGroup(wildBamboleosGroup);
    postSteps.addPostToBookmarks();
    userSteps.goToProfile();
    userSteps.openBookmarksFolder();
    assertTrue(userSteps.isPostInBookmark());
    postSteps.deletePostFromBookmark();
  }

  @AfterMethod
  public void tearDown() throws Exception {
    Browser.kill();
  }
}
