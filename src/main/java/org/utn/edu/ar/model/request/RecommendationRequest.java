package org.utn.edu.ar.model.request;

/** Recommendation Request representation in the UI.
 *
 * @author tom
 */
public class RecommendationRequest {

  private Integer matchId;
  private String emitterId;
  private String receiverId;

  public RecommendationRequest(){}

  public RecommendationRequest(Integer matchId, String emitterId, String receiverId) {
    this.matchId = matchId;
    this.emitterId = emitterId;
    this.receiverId = receiverId;
  }

  public Integer getMatchId() {
    return matchId;
  }

  public void setMatchId(Integer matchId) {
    this.matchId = matchId;
  }

  public String getEmitterId() {
    return emitterId;
  }

  public void setEmitterId(String emitterId) {
    this.emitterId = emitterId;
  }

  public String getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(String receiverId) {
    this.receiverId = receiverId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RecommendationRequest that = (RecommendationRequest) o;

    if (matchId != null ? !matchId.equals(that.matchId) : that.matchId != null) return false;
    if (emitterId != null ? !emitterId.equals(that.emitterId) : that.emitterId != null) return false;
    return !(receiverId != null ? !receiverId.equals(that.receiverId) : that.receiverId != null);

  }

  @Override
  public int hashCode() {
    int result = matchId != null ? matchId.hashCode() : 0;
    result = 31 * result + (emitterId != null ? emitterId.hashCode() : 0);
    result = 31 * result + (receiverId != null ? receiverId.hashCode() : 0);
    return result;
  }
}
