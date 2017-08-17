package com.yammer.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.yammer.business_objects.Group;
import com.yammer.business_objects.Post;
import com.yammer.business_objects.User;
import com.yammer.steps.GroupSteps;
import com.yammer.steps.LoginSteps;
import com.yammer.steps.PostSteps;
import com.yammer.steps.UserSteps;
import com.yammer.utils.Browser;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class PostTests {

  private Browser browser;
  private GroupSteps groupSteps;
  private LoginSteps loginSteps;
  private PostSteps postSteps;
  private UserSteps userSteps;
  private User user;
  private Group wildBamboleosGroup;

  @BeforeClass
  public void beforeClass() {
    user = new User("ararat@epam.com", "conditional");
    wildBamboleosGroup = new Group("Wild Bamboleos");
  }

  @Before
  public void setUp() throws Exception {
    browser = Browser.getBrowserInstance();
    groupSteps = new GroupSteps();
    loginSteps = new LoginSteps();
    postSteps = new PostSteps();
    userSteps = new UserSteps();
    browser.open("https://yammer.com/epam.com");
    loginSteps.login(user);
  }

  @Test
  public void sharePostTest() {
    Group wbGroup = new Group("WB Group");
    groupSteps.openGroup(wildBamboleosGroup);
    postSteps.sharePostToAnotherGroup(wbGroup);
    groupSteps.openGroup(wbGroup);
    assertTrue(postSteps.checkSharedPostInGroup());
    postSteps.deleteLastPost();
  }

  @Test
  public void createPostTest() {
    user.setPost(new Post("CreatePostTest"));
    groupSteps.openGroup(wildBamboleosGroup);
    postSteps.createPost(user.getPost());
    assertEquals(postSteps.getLastPostText(), user.getPost().getBody());
    postSteps.deleteLastPost();
  }

  @Test
  public void deletePostTest() {
    user.setPost(new Post("CreatePostTest"));
    groupSteps.openGroup(wildBamboleosGroup);
    postSteps.createPost(user.getPost());
    postSteps.deleteLastPost();
    assertNotEquals(postSteps.getLastPostText(), user.getPost().getBody());
  }

  @After
  public void tearDown() throws Exception {
    Browser.kill();
  }
}
