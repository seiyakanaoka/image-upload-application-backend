package com.example.project.user.service

import com.example.project.exception.UserNotExistsException
import com.example.project.token.entity.Token
import com.example.project.token.repository.TokenRepository
import com.example.project.user.entity.User
import com.example.project.user.repository.UserRepository
import com.example.project.util.JWTUtil
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(private val userRepository: UserRepository, private val tokenRepository: TokenRepository) {
    /**
     * ユーザーを取得する
     * */
    fun getUser(user: User): User? = userRepository.findByEmailAndPassword(user.email, user.password)


    /**
     * ユーザーが存在した場合、JWTトークンを返却する
     * */
    fun createCertification(user: User): String {
        val existUser = getUser(user) ?: throw UserNotExistsException(400, "ユーザーが存在しません。新規登録してください。")
        val jwtToken = JWTUtil().createJWTToken(existUser)
        val token = Token(1, jwtToken)
        tokenRepository.save(token)
        return jwtToken
    }
}