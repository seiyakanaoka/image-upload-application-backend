package com.example.project.aws.s3.repository

import com.example.project.aws.s3.entity.AwsS3
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AwsS3Repository : JpaRepository<AwsS3, Long> {
}