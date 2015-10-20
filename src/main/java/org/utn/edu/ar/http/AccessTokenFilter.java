package org.utn.edu.ar.http;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by tomas on 20/10/15.
 */
public class AccessTokenFilter implements Filter {

  private String ACCESS_TOKEN = "access-token";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
          throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;

    // If user is going to validate himself against FB Graph, continue.
    if(!httpRequest.getPathInfo().contains("validate")) {

      Enumeration headerNames = httpRequest.getHeaderNames();
      String potentialToken = fetchHeaderIfExists(ACCESS_TOKEN, headerNames, httpRequest);

      if (potentialToken.equals("")) {
        // Prepare response for validation error.
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.reset();
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.getWriter().print("You need an access token to continue using this API.");
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
  private String fetchHeaderIfExists(String headerName, Enumeration headerNames, HttpServletRequest httpRequest) {
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      if(key.equals(headerName))
        return httpRequest.getHeader(headerName);
    }
    return "";
  }
}
