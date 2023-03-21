package com.example.project.domain.user.entity

import com.example.project.domain.token.entity.Token
import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "user")
data class User(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long,
  var email: String,
  var password: String,
)
