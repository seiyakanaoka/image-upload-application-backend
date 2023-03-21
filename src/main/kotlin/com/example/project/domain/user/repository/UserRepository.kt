package com.example.project.domain.user.repository

import com.example.project.domain.user.entity.User
import com.example.project.domain.token.entity.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
  fun findByEmailAndPassword(email: String, password: String): User?

  @Query("select t from User u inner join Token t on u.id = t.user.id where u.id = :id")
  fun findUserAndTokens(@Param("id") id: Long): List<Token>
}