package com.example.project.domain.user.service

import com.example.project.domain.token.dto.TokenDto
import com.example.project.domain.token.entity.Token
import com.example.project.domain.token.service.TokenService
import com.example.project.domain.user.entity.User
import com.example.project.domain.user.repository.UserRepository
import com.example.project.exception.UserNotExistsException
import com.example.project.util.JWTUtil
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class UserService(
  private val userRepository: UserRepository,
  private val tokenService: TokenService,
) {
  /**
   * ユーザーを取得する
   * */
  fun getUser(user: User): User? = userRepository.findByEmailAndPassword(user.email, user.password)


  /**
   * ユーザーが存在した場合、JWTトークンを返却する
   * */
  fun createCertification(user: User): TokenDto {
    val uuid = UUID.randomUUID().toString()
    val existUser = getUser(user) ?: throw UserNotExistsException(400, "ユーザーが存在しません。新規登録してください。")
    val jwtToken = JWTUtil().createJWTToken(existUser)
    val token = Token(uuid, jwtToken, existUser)
    tokenService.saveToken(token)
    return TokenDto(jwtToken)
  }

  /**
   * ユーザーに紐付いたトークン一覧を取得する
   * inner joinの練習用
   * */
  fun getUserTokens(id: Long): List<Token> {
    return userRepository.findUserAndTokens(id)
  }
}