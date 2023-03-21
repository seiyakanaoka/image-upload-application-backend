package com.example.project.domain.token.entity

import com.example.project.domain.user.entity.User
import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "token")
data class Token(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long,
  val token: String,
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  val user: User
)
