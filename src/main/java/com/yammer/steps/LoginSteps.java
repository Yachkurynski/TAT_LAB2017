package com.yammer.steps;

import com.yammer.business_objects.User;
import com.yammer.pages.LoginPage;

public class LoginSteps {

  LoginPage loginPage;

  public LoginSteps() {
    loginPage = new LoginPage();
  }

  public void login(User user) {
    loginPage.setLogin(user.getUserName());
    if (!loginPage.isLoad()) {
      loginPage.setPassword(user.getPassword());
      loginPage.clickSubmit();
    }
  }
  /*
  public void login(User user){
    loginPage.setLogin(user.getUserName());
    loginPage.setPassword(user.getPassword());
    loginPage.clickSubmit();
  }
  */
}
