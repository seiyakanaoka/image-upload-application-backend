package com.example.project.domain.user.controller

import com.example.project.domain.token.dto.TokenDto
import com.example.project.domain.user.entity.User
import com.example.project.domain.user.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*
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

  /**
   * ユーザーに紐付いたトークンを取得する
   * @param user
   * */
  @GetMapping("/user/{id}/tokens")
  fun userTokens(@PathVariable id: Long) {
    println("通った？ : $id")
    userService.getUserTokens(id)
  }
}