package com.yammer.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import com.yammer.business_objects.Group;
import com.yammer.business_objects.Post;
import com.yammer.business_objects.User;
import com.yammer.steps.GroupSteps;
import com.yammer.steps.LoginSteps;
import com.yammer.steps.PostSteps;
import com.yammer.steps.UserSteps;
import com.yammer.utils.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    /*
    String name = System.getProperty("username");
    String password = System.getProperty("password");
    user = new User(name, password);
    */
    user = new User("araratk@epam.com","promololol");
    wildBamboleosGroup = new Group("Wild Bamboleos");
  }

  @BeforeMethod
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

  @AfterMethod
  public void tearDown() throws Exception {
    Browser.kill();
  }
}
