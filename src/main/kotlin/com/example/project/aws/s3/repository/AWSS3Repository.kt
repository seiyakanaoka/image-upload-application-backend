package com.example.project.aws.s3.repository

import com.example.project.aws.s3.entity.AWSS3
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AWSS3Repository : JpaRepository<AWSS3, Long> {
}