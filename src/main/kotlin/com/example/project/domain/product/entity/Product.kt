package com.example.project.domain.product.entity

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "product")
data class Product(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long,
  var title: String,
  var price: Int,
)
