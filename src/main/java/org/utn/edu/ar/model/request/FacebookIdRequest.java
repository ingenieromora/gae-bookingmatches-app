package org.utn.edu.ar.model.request;


public class FacebookIdRequest {

    private String fbId;

    public FacebookIdRequest(){}

    public FacebookIdRequest(String fbId) {
        this.fbId = fbId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacebookIdRequest that = (FacebookIdRequest) o;

        return !(fbId != null ? !fbId.equals(that.fbId) : that.fbId != null);

    }

    @Override
    public int hashCode() {
        return fbId != null ? fbId.hashCode() : 0;
    }
}
