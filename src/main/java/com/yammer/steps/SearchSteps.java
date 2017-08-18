package com.yammer.steps;

import com.yammer.business_objects.Group;
import com.yammer.pages.HomePage;
import com.yammer.pages.SearchResultPage;

public class SearchSteps {

  private HomePage homePage;
  private SearchResultPage searchResultPage;

  public SearchSteps() {
    homePage = new HomePage();
    searchResultPage = new SearchResultPage();
  }

  public void search(String searchRequest) {
    homePage.search(searchRequest);
  }

  public void chooseUser() {
    searchResultPage.clickUsersFolder();
    searchResultPage.getUsersList().get(0).click();
  }

  public void openGroupsFolder() {
    searchResultPage.clickGroupsFolder();
  }

  public boolean checkGroupInResult(String searchQuery) {
    return searchResultPage.checkGroupConsistInResult(searchQuery);
  }

  public void openGroup() {
    searchResultPage.openFirstGroup();
  }
}
