package com.example.project.exception

class UserExistsException(val httpStatusCode: Int, message: String? = null) : RuntimeException(message)