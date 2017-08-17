package com.yammer.tests;


import static org.junit.Assert.assertEquals;

import com.yammer.business_objects.Comment;
import com.yammer.business_objects.Group;
import com.yammer.business_objects.Post;
import com.yammer.business_objects.User;
import com.yammer.steps.CommentSteps;
import com.yammer.steps.GroupSteps;
import com.yammer.steps.LoginSteps;
import com.yammer.steps.PostSteps;
import com.yammer.utils.Browser;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CommentTests {

  private Browser browser;
  private LoginSteps loginSteps;
  private CommentSteps commentSteps;
  private GroupSteps groupSteps;
  private PostSteps postSteps;
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
    commentSteps = new CommentSteps();
    groupSteps = new GroupSteps();
    postSteps = new PostSteps();
    loginSteps = new LoginSteps();
    browser.open("https://yammer.com/epam.com");
    loginSteps.login(user);
  }

  @Test
  public void createCommentTest() {
    user.setPost(new Post("Test"));
    user.setComment(new Comment("trst comment"));
    groupSteps.openGroup(wildBamboleosGroup);
    postSteps.createPost(user.getPost());
    commentSteps.createComment(user.getComment());
    assertEquals(commentSteps.getLastCommentText(), user.getComment().getBody());
    commentSteps.deleteLastComment();
    postSteps.deleteLastPost();
  }

  @Test
  public void deleteCommentTest() {
    user.setPost(new Post("CreateCommentTest"));
    user.setComment(new Comment("comment"));
    groupSteps.openGroup(wildBamboleosGroup);
    postSteps.createPost(user.getPost());
    commentSteps.createComment(user.getComment());
    assertEquals(commentSteps.getLastCommentText(), user.getComment().getBody());
    commentSteps.deleteLastComment();
    postSteps.deleteLastPost();
  }

  @After
  public void tearDown() throws Exception {
    Browser.kill();
  }
}
