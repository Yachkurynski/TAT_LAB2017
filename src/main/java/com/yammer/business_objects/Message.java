package com.yammer.business_objects;

public class Message {
  private String addressee;
  private String body;

  public Message(String addressee, String body) {
    this.addressee = addressee;
    this.body = body;
  }

  public String getAddressee() {
    return addressee;
  }

  public void setAddressee(String addressee) {
    this.addressee = addressee;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
