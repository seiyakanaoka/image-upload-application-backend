package com.example.project.exception

class UserNotExistsException(val httpStatusCode: Int, message: String? = null) : RuntimeException(message)