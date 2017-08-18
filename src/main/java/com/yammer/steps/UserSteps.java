package com.yammer.steps;

import com.yammer.pages.HomePage;
import com.yammer.pages.ProfilePage;
import com.yammer.pages.UserPage;
import org.openqa.selenium.WebElement;

public class UserSteps {

  HomePage homePage;
  UserPage userPage;
  ProfilePage profilePage;

  public UserSteps() {
    homePage = new HomePage();
    userPage = new UserPage();
    profilePage = new ProfilePage();
  }

  public void goToProfile() {
    homePage.clickSettingAndMoreButton();
    homePage.clickUserPageButton();
  }

  public void follow() {
    userPage.clickFollowButton();
  }

  public void unfollow(String userName) {
    profilePage.openFollowingUsers();
    profilePage.clickUnfollowUserButton(userName);
  }

  public boolean checkUserInFollowings(String followingUserName) {
    profilePage.openFollowingUsers();

    for (WebElement user : profilePage.getFollowingList()) {
      if (user.getText().contains(followingUserName)) {
        return true;
      }
    }
    return false;
  }

  public void openBookmarksFolder() {
    profilePage.openBookmarksFolder();
  }

  public boolean isPostInBookmark() {
    return profilePage.isPostInBookmark();
  }
}
