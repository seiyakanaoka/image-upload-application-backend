package com.example.project.domain.aws.s3.entity

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "awsS3")
data class AwsS3(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long,
  var imagePrefix: String,
)