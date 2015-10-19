package org.utn.edu.ar.model.request;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tom on 10/18/15.
 */
public class FacebookTest {

  private final String appTokenExpected = "1496941813951092|1lHi-9z3j6VyUIcfLCpvDMpcdOg";

  @Test
  public void getAppAccessTokenTest(){
    Assert.assertEquals(appTokenExpected, Facebook.getAppAccessToken());
  }
}
