package com.example.project.domain.token.service

import com.example.project.domain.token.entity.Token
import com.example.project.domain.token.repository.TokenRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class TokenService(private val tokenRepository: TokenRepository) {
  /**
   * トークンを保存する
   * @param token Token
   */
  fun saveToken(token: Token) = tokenRepository.save(token)
}