package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.request.Facebook;
import org.utn.edu.ar.model.response.MessageResponse;

/**
 * Created by tom on 10/27/15.
 */
@Api(
        name = "facebook",
        scopes = Constants.EMAIL_SCOPE,
        clientIds = Constants.WEB_CLIENT_ID
)
public class FacebookController {

  @ApiMethod(
          path = "facebook/app-access-token",
          name = "appToken",
          httpMethod = ApiMethod.HttpMethod.GET
  )
  public MessageResponse getAppAccessToken(){
    return new MessageResponse(Facebook.getAppAccessToken());
  }
}
