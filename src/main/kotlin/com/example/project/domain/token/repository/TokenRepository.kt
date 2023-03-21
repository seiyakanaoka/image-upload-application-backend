package com.example.project.domain.token.repository

import com.example.project.domain.token.entity.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TokenRepository : JpaRepository<Token, String> {
  fun existsByToken(token: String): Boolean
}