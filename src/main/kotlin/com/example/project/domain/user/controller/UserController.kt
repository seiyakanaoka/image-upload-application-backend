package com.example.project.domain.user.controller

import com.example.project.domain.token.dto.TokenDto
import com.example.project.domain.user.entity.User
import com.example.project.domain.user.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
class UserController(private val userService: UserService) {
  /**
   * ユーザーログイン認証
   * @param user
   * */
  @PostMapping("/login")
  fun userLogin(@RequestBody user: User): TokenDto {
    return userService.createCertification(user)
  }
}