package org.utn.edu.ar.model.response;

/**
 * Created by tomas on 20/10/15.
 */
public class MessageResponse {

  private String message;

  public MessageResponse(){}

  public MessageResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MessageResponse that = (MessageResponse) o;

    return !(message != null ? !message.equals(that.message) : that.message != null);

  }

  @Override
  public int hashCode() {
    return message != null ? message.hashCode() : 0;
  }
}
