package com.example.project.exception

class TitleExistException(val httpStatusCode: Int, message: String? = null) : RuntimeException(message)