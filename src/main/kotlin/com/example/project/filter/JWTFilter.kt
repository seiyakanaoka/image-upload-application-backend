package com.example.project.filter

import com.example.project.user.service.UserService
import com.example.project.util.JWTUtil
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
@RequiredArgsConstructor
class JWTFilter(private val userService: UserService) : Filter {
    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, chain: FilterChain?) {
        var request = servletRequest as? HttpServletRequest
        var response = servletResponse as? HttpServletResponse
        val header = request?.getHeader("Authorization")
        // Authorizationヘッダーがnullの場合はエラー
        if (header == null) {
            response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしていません。")
            return;
        }
        val token = header.replaceFirst("Bearer ", "");
        if (token == "") {
            response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしていません。")
            return;
        }
//        val user = userService.
        val decodeToken = JWTUtil().decodeToken(token)
        println("aaa  ${decodeToken?.subject}")
        chain?.doFilter(servletRequest, servletResponse);

    }

    @Bean
    fun filter(): FilterRegistrationBean<JWTFilter>? {
        val bean: FilterRegistrationBean<JWTFilter> = FilterRegistrationBean<JWTFilter>()
        bean.filter = JWTFilter(userService)
        bean.addUrlPatterns("/api/product/*") //または、 `setUrlPatterns()`を使用します
        return bean
    }
}