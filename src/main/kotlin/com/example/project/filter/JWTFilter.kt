package com.example.project.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm.HMAC256
import com.example.project.domain.token.repository.TokenRepository
import com.example.project.util.JWTUtil
import com.auth0.jwt.algorithms.Algorithm
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
class JWTFilter(private val tokenRepository: TokenRepository) : Filter {
  override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, chain: FilterChain?) {
    var request = servletRequest as? HttpServletRequest
    var response = servletResponse as? HttpServletResponse
    val header = request?.getHeader("Authorization")
    // Authorizationヘッダーがnullの場合はエラー
    if (header == null) {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしてください。")
      return;
    }
    // トークンがなかったらエラー
    val token = header.replaceFirst("Bearer ", "");
    if (token == "") {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしてください。")
      return;
    }
    // トークンテーブルにトークンが存在してなかったらエラー
    if (!tokenRepository.existsByToken(token)) {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしてください。")
      return;
    }
    // デコードできなかったらエラー
    if (JWTUtil().decodeToken(token) == null) {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "不正な操作です。")
      return;
    }
    chain?.doFilter(servletRequest, servletResponse);

  }

  @Bean
  fun filter(): FilterRegistrationBean<JWTFilter>? {
    val bean: FilterRegistrationBean<JWTFilter> = FilterRegistrationBean<JWTFilter>()
    bean.filter = JWTFilter(tokenRepository)
    bean.addUrlPatterns("/api/product/*") //または、 `setUrlPatterns()`を使用します
    return bean
  }
}