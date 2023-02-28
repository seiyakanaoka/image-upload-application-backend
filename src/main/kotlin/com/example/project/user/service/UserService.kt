package com.example.project.user.service

import com.example.project.user.entity.User
import com.example.project.user.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(private val userRepository: UserRepository) {
    /**
     * 特定のユーザーが存在するか判定する
     * */
    fun isExistUser(user: User): Boolean = userRepository.existsByEmailAndPassword(user.email, user.password)


    /**
     * ユーザーが存在した場合、JWTトークンを返却する
     * */
    fun createCertification(user: User): String {
        return if (isExistUser(user)) "JWTトークン発行" else "トークン発行しない"
    }
}