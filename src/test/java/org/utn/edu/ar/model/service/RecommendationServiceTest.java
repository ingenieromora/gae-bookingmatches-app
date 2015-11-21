package org.utn.edu.ar.model.service;

import org.junit.Assert;
import org.junit.Test;
import org.utn.edu.ar.model.common.BeforeAfterProvider;
import org.utn.edu.ar.model.domain.Recommendation;

/**
 * Created by tom on 11/19/15.
 */
public class RecommendationServiceTest extends BeforeAfterProvider {

  @Test
  public void getAllTest() {
    Assert.assertEquals(2, recommendationService.getAll().size());
  }

  @Test
  public void getByIdTest() {
    Recommendation r = recommendationService.getById(1l);
    Assert.assertEquals(ONE, r.getId());
    Assert.assertEquals(TOM, r.getEmitter().getFbId());
    Assert.assertEquals(LEO, r.getReceiver().getFbId());
  }

  @Test
  public void getForEmitterTest() {
    Assert.assertEquals(1, recommendationService.getForEmitter(TOM).size());
    Assert.assertEquals(1, recommendationService.getForEmitter(LEO).size());
  }

  @Test
  public void getForReceiverTest() {
    Assert.assertEquals(1, recommendationService.getForReceiver(LEO).size());
    Assert.assertEquals(1, recommendationService.getForReceiver(NICO).size());
  }

  @Test
  public void deleteTest() {
    recommendationService.delete(ONE);
    Assert.assertEquals(1, recommendationService.getAll().size());
    Assert.assertEquals(TWO, recommendationService.getForEmitter(LEO).get(0).getId());
  }
}
