package com.example.riskanalysis.component;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.riskanalysis.RiskAnalysisApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LoggerMiddleware extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        filterChain.doFilter(request, response);
        RiskAnalysisApplication.log.info(request.getRemoteAddr() + " - " + request.getMethod()
                + " " + request.getRequestURI() + " " + response.getStatus() + " "
                + HttpStatus.valueOf(response.getStatus()).getReasonPhrase());

    }

}
