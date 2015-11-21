package org.utn.edu.ar.http;

import com.google.api.server.spi.Strings;
import org.utn.edu.ar.model.PlayerService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by tomas on 20/10/15.
 */
public class AccessTokenFilter implements Filter {

  private static final String ACCESS_TOKEN = "access-token";

  @Override
  public void init(final FilterConfig aFilterConfig) throws ServletException {
  }

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
          throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;

    // If user is going to validate himself against FB Graph, continue.
    if(!httpRequest.getRequestURI().endsWith("validate")) {

      Enumeration headerNames = httpRequest.getHeaderNames();
      String potentialToken = fetchHeaderIfExists(ACCESS_TOKEN, headerNames, httpRequest);

      if (Strings.isEmptyOrWhitespace(potentialToken)) {
        // Prepare response for validation error.
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.reset();
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.getWriter().print("You need an access token to continue using this API.\nPlease" +
                " validate yourself going to /players/validate.");
        return;
      }

    }

    filterChain.doFilter(request, response);
  }

  @Override
  public void destroy(){}

  /** This function is responsible for returning the `access-token` header if
   * it's contained inside the request's headers.
   *
   * @param headerName
   * @param headerNames
   * @param httpRequest
   * @return token if it's available, "" otherwise.
   */
  private String fetchHeaderIfExists(
          final String headerName,
          final Enumeration headerNames,
          final HttpServletRequest httpRequest) {
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      if(key.equals(headerName))
        return httpRequest.getHeader(headerName);
    }
    return "";
  }
}
