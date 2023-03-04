package com.example.project.token.repository

import com.example.project.token.entity.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    fun existsByToken(token: String): Boolean
}