package org.utn.edu.ar.util;

import com.google.common.base.Function;

/**
 * Created by tom on 11/8/15.
 */
public class Utils {

  public static Function<Integer, Integer> successor =
    new Function<Integer, Integer>() {
      @Override
      public Integer apply(Integer integer) {
        return integer + 1;
      }
    };
}
