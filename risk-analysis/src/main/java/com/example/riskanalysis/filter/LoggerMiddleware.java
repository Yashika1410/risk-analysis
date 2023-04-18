package com.example.riskanalysis.filter;

import com.example.riskanalysis.RiskAnalysisApplication;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter class used to log each request.
 */
@Component
public class LoggerMiddleware extends OncePerRequestFilter {
  private Logger log = LoggerFactory.getLogger(
            RiskAnalysisApplication.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
       FilterChain filterChain)
            throws ServletException, IOException {

    filterChain.doFilter(request, response);
    log.info(request.getRemoteAddr() + " - " + request.getMethod()
                + " " + request.getRequestURI() + " " + response.getStatus() + " "
                + HttpStatus.valueOf(response.getStatus()).getReasonPhrase());

  }

}
