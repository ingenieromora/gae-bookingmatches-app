package org.utn.edu.ar.model.response;

/**
 * Created by tom on 10/20/15.
 */
public class FbIdNameResponse {

  private String id;
  private String name;

  public FbIdNameResponse(){}

  public FbIdNameResponse(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FbIdNameResponse that = (FbIdNameResponse) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    return !(name != null ? !name.equals(that.name) : that.name != null);

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
