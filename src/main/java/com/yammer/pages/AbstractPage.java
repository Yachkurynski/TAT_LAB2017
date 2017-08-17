package com.yammer.pages;

import com.yammer.utils.Browser;

public class AbstractPage {

    protected Browser browser;

    AbstractPage() {
        browser = Browser.getBrowserInstance();
    }
}
