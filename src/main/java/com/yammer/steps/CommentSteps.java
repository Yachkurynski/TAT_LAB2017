package com.yammer.steps;

import com.yammer.business_objects.Comment;
import com.yammer.pages.GroupPage;
import org.openqa.selenium.WebElement;

public class CommentSteps {

  private GroupPage groupPage;

  public CommentSteps() {
    groupPage = new GroupPage();
  }

  public void createComment(Comment comment) {
    groupPage.clickCreateReplyButtonForLastPost();
    groupPage.setReplyTextField(comment.getBody());
    groupPage.clickSendReplyButton();
  }

  public void deleteLastComment() {
    groupPage.clickLastReplyActionsButton();
    groupPage.clickDeleteButton();
  }

  public String getLastCommentText() {
    return groupPage.getLastCommentBody();
  }
}
