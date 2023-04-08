package com.example.project.domain.user.controller

import com.example.project.domain.token.dto.TokenDto
import com.example.project.domain.user.form.UserForm
import com.example.project.domain.user.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
class UserController(private val userService: UserService) {


  /**
   * ログイン認証
   * 自作ログイン
   * @param user
   * */
  @PostMapping("/login")
  fun userLogin(@RequestBody userForm: UserForm): TokenDto {
    return userService.isPossibleLogin(userForm)
  }

  /**
   * ユーザーに紐付いたトークンを取得する
   * @param user
   * */
  @GetMapping("/user/{id}/tokens")
  fun userTokens(@PathVariable id: Long) {
    userService.getUserTokens(id)
  }
}