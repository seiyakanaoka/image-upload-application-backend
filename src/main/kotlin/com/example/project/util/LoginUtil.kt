package com.example.project.util

import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

class LoginUtil {
  /** パスワードに結合させるソルトを生成する */
  fun generateSalt(): String {
    val random = SecureRandom()
    val salt = ByteArray(16)
    random.nextBytes(salt)
    return Base64.getEncoder().encodeToString(salt)
  }


  fun hashString(input: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    val hashBytes = md.digest(input.toByteArray())
    return hashBytes.joinToString("") { "%02x".format(it) }
  }
}