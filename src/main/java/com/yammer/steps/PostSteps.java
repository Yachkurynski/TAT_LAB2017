package com.yammer.steps;

import com.yammer.business_objects.Group;
import com.yammer.business_objects.Post;
import com.yammer.pages.*;
import org.openqa.selenium.WebElement;

public class PostSteps {

  GroupPage groupPage;
  HomePage homePage;
  ShareConversationPage shareConversationPage;

  public PostSteps() {
    homePage = new HomePage();
    groupPage = new GroupPage();
    shareConversationPage = new ShareConversationPage();
  }

  public void createPost(Post post) {
    groupPage.clickCreatePost();
    groupPage.setPostTextArea(post.getBody());
    groupPage.clickPostButton();
  }

  public void addPostToBookmarks() {
    groupPage.clickOptions();
    groupPage.clickAddToBookmarkButton();
  }

  public void deletePostFromBookmark() {
    groupPage.clickOptions();
    groupPage.clickRemoveFromBookmarkButton();
  }

  public void sharePostToAnotherGroup(Group group) {
    groupPage.clickShareLastPostButton();
    shareConversationPage.setAddresseeGroup(group.getGroupName());
    shareConversationPage.clickSharePost();
  }

  public boolean checkSharedPostInGroup() {
    return groupPage.checkPostInGroup();
  }

  public void deleteLastPost() {
    groupPage.clickOptions();
    groupPage.clickDeleteButton();
  }

  public String getLastPostText() {
    return groupPage.getLastPostBody();
  }
}
