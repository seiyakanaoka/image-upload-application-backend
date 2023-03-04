package com.example.project.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component

@Component
class JWTFilter : Filter {
    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, chain: FilterChain?) {
        println("filter起動")
        var request = servletRequest as? HttpServletRequest
        var response = servletResponse as? HttpServletResponse
        val header = request?.getHeader("Authorization")
        if (header == null) {
            println("通った")
            response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.")
        } else {
            chain?.doFilter(servletRequest, servletResponse);
        }
    }
}