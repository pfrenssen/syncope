/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.console;

import org.junit.Test;

public class UserTestITCase extends AbstractTest {

    @Test
    @SuppressWarnings("SleepWhileHoldingLock")
    public void browseCreateModal() {
        selenium.click("css=img[alt=\"Users\"]");

        selenium.waitForCondition("selenium.isElementPresent(\"//div[@id='tabs']\");", "30000");

        selenium.click("//div[@id='tabs-1']/a");

        selenium.waitForCondition("selenium.isElementPresent(\"//iframe\");", "30000");
        selenium.selectFrame("index=0");

        selenium.waitForCondition("selenium.isElementPresent("
                + "\"//span[contains(text(),'Attributes')]\");", "30000");

        selenium.click("//div/form/div[3]/div[1]/span[2]/div/div[2]/span");
        
        selenium.click("//div[@id='tabs']/ul/li[2]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[3]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[4]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[5]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[6]/a/span");

        seleniumDriver.switchTo().defaultContent();

        selenium.click("css=a.w_close");
    }

    @Test
    @SuppressWarnings("SleepWhileHoldingLock")
    public void browseEditModal() {
        selenium.click("css=img[alt=\"Users\"]");

        selenium.waitForCondition("selenium.isElementPresent(\"//div[@id='tabs']\");", "30000");

        //Edit vivaldi
        selenium.click("//*[@id=\"users-contain\"]//*[div=3]/../td[5]/div/span[13]/a");

        selenium.waitForCondition("selenium.isElementPresent(\"//iframe\");", "30000");
        selenium.selectFrame("index=0");

        selenium.waitForCondition("selenium.isElementPresent(" + "\"//input[@value='Antonio Vivaldi']\");", "30000");

        selenium.waitForCondition("selenium.isElementPresent(" + "\"//input[@value='Vivaldi']\");", "30000");

        selenium.click("//div[@id='tabs']/ul/li[2]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[3]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[4]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[5]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[6]/a/span");

        seleniumDriver.switchTo().defaultContent();

        selenium.click("css=a.w_close");
    }

    @Test
    public void search() {
        selenium.click("css=img[alt=\"Users\"]");

        selenium.waitForCondition("selenium.isElementPresent(\"//div[@id='tabs']\");", "30000");

        selenium.click("link=Search");
        selenium.select("//td[2]/select", "label=MEMBERSHIP");

        selenium.waitForCondition("selenium.isElementPresent(\"//td[3]/select[option='3 citizen']\");", "30000");

        selenium.select("//td[3]/select", "label=3 citizen");
        selenium.click("//form/a");

        selenium.waitForCondition("selenium.isElementPresent(\"//*[@id='users-contain']//*[div=2]\");", "30000");
    }

    @Test
    public void delete() {
        selenium.click("css=img[alt=\"Users\"]");

        selenium.waitForCondition("selenium.isElementPresent(\"//div[@id='tabs']\");", "30000");

        selenium.click("//*[@id=\"users-contain\"]//*[div=4]/../td[5]/div/span[15]/a");

        assertTrue(selenium.getConfirmation().equals("Do you really want to delete the selected item(s)?"));

        selenium.waitForCondition("selenium.isElementPresent(" + "\"//div[@id='propagation']/span\");", "30000");

        selenium.waitForCondition("selenium.isElementPresent(\"//iframe\");", "30000");
        selenium.selectFrame("index=0");

        selenium.click("//*[@id=\"users-contain\"]/a");

        seleniumDriver.switchTo().defaultContent();

        selenium.waitForCondition("selenium.isTextPresent(" + "\"Operation executed successfully\");", "30000");
    }

    @Test
    public void browseProvisioningFeatures() {
        selenium.click("css=img[alt=\"Users\"]");

        selenium.waitForCondition("selenium.isElementPresent(\"//div[@id='tabs']\");", "30000");

        //Edit vivaldi
        selenium.click("//*[@id=\"users-contain\"]//*[div=3]/../td[5]/div/span[2]/a");
        selenium.waitForCondition("selenium.isElementPresent(" + "\"//td[div='ws-target-resource-1']\");", "30000");
        selenium.waitForCondition("selenium.isElementPresent(" + "\"//td[div='resource-testdb']\");", "30000");

        selenium.selectFrame("index=0");

        selenium.click("//div[@class='navigator']/div/span[4]/a");
        selenium.waitForCondition("selenium.isElementPresent(" + "\"//td[div='resource-ldap']\");", "30000");

        selenium.click("//div[@class='navigator']/div/span/a");
        selenium.waitForCondition("selenium.isElementPresent(" + "\"//td[div='ws-target-resource-1']\");", "30000");

        seleniumDriver.switchTo().defaultContent();

        selenium.click("css=a.w_close");
    }

    @Test
    @SuppressWarnings("SleepWhileHoldingLock")
    public void issueSYNCOPE495() {
        selenium.click("css=img[alt=\"Users\"]");

        selenium.waitForCondition("selenium.isElementPresent(\"//div[@id='tabs']\");", "30000");

        selenium.click("//*[@id=\"users-contain\"]//*[div=3]/../td[5]/div/span[13]/a");

        selenium.waitForCondition("selenium.isElementPresent(\"//iframe\");", "30000");
        selenium.selectFrame("index=0");

        selenium.waitForCondition("selenium.isElementPresent(" + "\"//input[@value='Antonio Vivaldi']\");", "30000");

        selenium.waitForCondition("selenium.isElementPresent(" + "\"//input[@value='Vivaldi']\");", "30000");

        selenium.click("//div[@id='tabs']/ul/li[2]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[3]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[4]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[5]/a/span");
        selenium.click("//div[@id='tabs']/ul/li[6]/a/span");

        selenium.click("//span[2]/a/span");

        selenium.waitForCondition("selenium.isElementPresent(\"//div[@class='infolabel']\");", "30000");
        selenium.selectFrame("relative=up");
        selenium.click("css=a.w_close");
    }
}
