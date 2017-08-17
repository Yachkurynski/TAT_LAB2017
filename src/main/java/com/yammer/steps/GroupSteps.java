package com.yammer.steps;

import com.yammer.business_objects.Group;
import com.yammer.pages.CreationGroupPage;
import com.yammer.pages.GroupPage;
import com.yammer.pages.GroupSettingsPage;
import com.yammer.pages.HomePage;

public class GroupSteps {

  private GroupSettingsPage groupSettingsPage;
  private CreationGroupPage creationGroupPage;
  private GroupPage groupPage;
  private HomePage homePage;

  public GroupSteps() {
    homePage = new HomePage();
    groupPage = new GroupPage();
    groupSettingsPage = new GroupSettingsPage();
    creationGroupPage = new CreationGroupPage();
  }

  public void createGroup(Group group) {
    homePage.clickCreateGroupButton();
    creationGroupPage.choosePrivateGroupType();
    creationGroupPage.typeNameOfGroup(group.getGroupName());
    creationGroupPage.clickCreateGroupButton();
  }

  public void joinGroup() {
    groupPage.clickJoinGroup();
  }

  public void leaveGroup() {
    groupPage.clickLeaveGroup();
  }

  public void deleteGroup() {
    groupPage.clickGroupSettingsButton();
    groupSettingsPage.clickDeleteGroupButton();
    groupSettingsPage.clickApproveDeleteGroupButton();
  }

  public boolean checkGroupName(Group group) {
    return groupPage.getGroupName().equals(group.getGroupName());
  }

  public boolean checkDeletedIdentifier(String expectedMessage) {
    return groupPage.getDeletedGroupIdentifier().equals(expectedMessage);
  }

  public void openGroup(Group group) {
    homePage.openGroupByName(group.getGroupName());
  }

  public boolean isGroupMember() {
    return groupPage.isMember();
  }
}
