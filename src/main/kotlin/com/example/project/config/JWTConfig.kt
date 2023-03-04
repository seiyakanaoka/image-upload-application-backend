//package com.example.project.config
//
//import io.jsonwebtoken.security.Keys
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import java.security.Key
//
//@Configuration
//class JwtConfig {
//    @Value("\${jwt.secret}")
//    private lateinit var secret: String
//
//    @Bean
//    fun secretKey(): Key {
//        return Keys.hmacShaKeyFor(secret.toByteArray())
//    }
//}
