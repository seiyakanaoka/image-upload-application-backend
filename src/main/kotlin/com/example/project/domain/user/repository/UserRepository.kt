package com.example.project.domain.user.repository

import com.example.project.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
  fun findByEmailAndPassword(email: String, password: String): User?
}