package com.example.project.error

data class ApplicationError(
    val message: String = "",
    val httpStatus: Int,
    val httpMethod: String,
    val fieldErrors: List<FieldError?> = listOf<FieldError>()
)