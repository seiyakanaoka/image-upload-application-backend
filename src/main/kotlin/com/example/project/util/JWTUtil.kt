package com.example.project.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.project.user.entity.User

class JWTUtil {
    /**
     * トークンを発行する
     */
    fun createJWTToken(user: User): String {
        val secretKey = "secret"
        println("")
        return JWT.create()
            .withSubject(user.id.toString())
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