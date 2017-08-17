package com.yammer.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.yammer.business_objects.Group;
import com.yammer.business_objects.User;
import com.yammer.steps.GroupSteps;
import com.yammer.steps.LoginSteps;
import com.yammer.steps.SearchSteps;
import com.yammer.utils.Browser;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GroupTests {

  private Browser browser;
  private GroupSteps groupSteps;
  private LoginSteps loginSteps;
  private SearchSteps searchSteps;
  private User user;

  @BeforeClass
  public void beforeClass() {
    user = new User("ararat@epam.com", "conditional");
  }

  @Before
  public void setUp() throws Exception {
    browser = Browser.getBrowserInstance();
    groupSteps = new GroupSteps();
    loginSteps = new LoginSteps();
    searchSteps = new SearchSteps();
    browser.open("https://yammer.com/epam.com");
    loginSteps.login(user);
  }

  @Test
  public void createGroupTest() {
    user.setGroup(new Group("New_group_for_check_creatingTest"));
    groupSteps.createGroup(user.getGroup());
    assertTrue(groupSteps.checkGroupName(user.getGroup()));
    groupSteps.deleteGroup();
  }

  @Test
  public void deleteGroupTest() {
    user.setGroup(new Group("New_group_for_check_creatingTest"));
    groupSteps.createGroup(user.getGroup());
    groupSteps.deleteGroup();
    assertTrue(groupSteps.checkDeletedIdentifier("This group has been deleted."));
  }

  @Test
  public void joinGroupTest() {
    user.setSearchQuery("Hakuna-Matata");
    searchSteps.search(user.getSearchQuery());
    searchSteps.openGroupsFolder();
    searchSteps.openGroup();
    groupSteps.joinGroup();
    assertTrue(groupSteps.isGroupMember());
    groupSteps.leaveGroup();
  }

  @Test
  public void leaveGroupTest() {
    user.setSearchQuery("Hakuna-Matata");
    searchSteps.search(user.getSearchQuery());
    searchSteps.openGroupsFolder();
    searchSteps.openGroup();
    groupSteps.joinGroup();
    groupSteps.leaveGroup();
    assertFalse(groupSteps.isGroupMember());
  }

  @After
  public void tearDown() throws Exception {
    Browser.kill();
  }
}
