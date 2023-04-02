package com.example.project.domain.aws.s3.entity

import com.example.project.domain.user.entity.User
import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "userImage")
data class AwsS3(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long,
  var imageUrl: String,
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  val user: User
)