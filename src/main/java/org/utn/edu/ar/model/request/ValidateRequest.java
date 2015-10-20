package org.utn.edu.ar.model.request;

/**
 * Created by tom on 10/20/15.
 */
public class ValidateRequest {

  private String accessToken;
  private String fbId;

  public ValidateRequest(){}

  public ValidateRequest(String accessToken, String fbId) {
    this.accessToken = accessToken;
    this.fbId = fbId;
  }

  public String getFbId() {
    return fbId;
  }

  public void setFbId(String fbId) {
    this.fbId = fbId;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ValidateRequest that = (ValidateRequest) o;

    if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
    return !(fbId != null ? !fbId.equals(that.fbId) : that.fbId != null);

  }

  @Override
  public int hashCode() {
    int result = accessToken != null ? accessToken.hashCode() : 0;
    result = 31 * result + (fbId != null ? fbId.hashCode() : 0);
    return result;
  }
}
