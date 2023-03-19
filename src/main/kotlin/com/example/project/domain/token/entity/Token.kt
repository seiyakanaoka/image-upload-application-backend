package com.example.project.domain.token.entity

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "token")
data class Token(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long, val token: String
)
