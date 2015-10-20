package org.utn.edu.ar.model.request;

import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.response.FbIdNameResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by tom on 10/18/15.
 */
public class Facebook {

  // Jackson mapper to parse the response from fb's validation to a class.
  private static final ObjectMapper mapper = new ObjectMapper();

  // This could be extracted to Constants, but I dunno.
  private static final String apiVersion = "v2.4/";
  private static final String graphURL = "https://graph.facebook.com/";

  private static final String validateUserURL = graphURL + apiVersion + "/me?access_token=";
  private static final String getAppTokenURL = graphURL +
                  "oauth/access_token?"                 +
                  "client_id="+     Constants.APP_ID    +
                  "&client_secret="+Constants.APP_SECRET+
                  "&grant_type=client_credentials";

  /** This method is responsible for returning the user of the app a response telling
   * that he/she has been authenticated and is being kept within a cache holding the pair
   * Access Token / FbId
   *
   * @param rq the ValidateRequest to validate.
   * @return OK if the user is authenticated, FAILED otherwise.
   */
  public static String authenticate(final ValidateRequest rq){
    URL url;
    try {
      url = new URL(validateUserURL + rq.getAccessToken());
      URLConnection conn = url.openConnection();
      InputStream in =conn.getInputStream();
      FbIdNameResponse response = mapper.readValue(in, FbIdNameResponse.class);
      if(rq.getFbId().equals(response.getId())) return "OK";
    } catch (IOException e){
      e.printStackTrace();
    }
    return "FAILED";
  }

  public static String getAppAccessToken(){
    URL url;
    try {
      url = new URL(getAppTokenURL);
      URLConnection conn = url.openConnection();
      InputStream in =conn.getInputStream();
      StringWriter writer = new StringWriter();
      IOUtils.copy(in, writer);
      return writer.toString().replaceAll("(access_token=)","");
    } catch (IOException e){
      e.printStackTrace();
    }
    return "";
  }
}
