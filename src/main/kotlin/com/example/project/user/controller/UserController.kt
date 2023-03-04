package com.example.project.user.controller

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.project.user.entity.User
import com.example.project.user.service.UserService
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
    fun userLogin(@RequestBody user: User): String {
        val token = createJWTToken(user)
        val decode = decodeToken(token)?.getClaim("username")
        if (decode != null) {
            println("decode : ${decode.asString()}")
        }
        return userService.createCertification(user)
    }


    /**
     * トークンを発行する
     */
    fun createJWTToken(user: User): String {
        val secretKey = "secret"
        return JWT.create()
            .withJWTId(user.id.toString())
            .withIssuer("kanaokaseiya") //発行者
            .withClaim("username", user.email) //keyに対してvalueの設定。汎用的な様々な値を保持できる
            .sign(Algorithm.HMAC256(secretKey)); // 利用アルゴリズムを指定してJWTを新規作成
    }

    /**
     * トークンをデコードする
     */
    fun decodeToken(token: String): DecodedJWT? {
        // アルゴリズムを指定して検証用（Tokenが適切なJWTフォーマットか、著名が一致するかなど）オブジェクトを作成する
        val verifier = JWT.require(Algorithm.HMAC256("secret")).build();
        // Tokenを検証してデコードされたJWTを取得する
        // もしも著名が無効だったりアルゴリズムが等しくないなどの場合は例外が発生します
        return verifier.verify(token);
    }
}