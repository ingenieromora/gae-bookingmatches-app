package org.utn.edu.ar.model.request;

import org.apache.commons.io.IOUtils;
import org.utn.edu.ar.Constants;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

/**
 * Created by tom on 10/18/15.
 */
public class Facebook {

  private static final String graphURL = "https://graph.facebook.com/";
  private static final String getAppTokenURL = graphURL +
                  "oauth/access_token?"                 +
                  "client_id="+     Constants.APP_ID    +
                  "&client_secret="+Constants.APP_SECRET+
                  "&grant_type=client_credentials";

  public static String getAppAccessToken(){
    URL url;
    try {
      url = new URL(getAppTokenURL);
      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
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
