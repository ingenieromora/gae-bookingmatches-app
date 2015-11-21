package org.utn.edu.ar.util;

import com.google.common.base.Function;

/**
 * Created by tom on 11/8/15.
 */
public class Utils {

  public static Function<Long, Long> successor =
    new Function<Long, Long>() {
      @Override
      public Long apply(Long num) {
        return num + 1;
      }
    };


}
