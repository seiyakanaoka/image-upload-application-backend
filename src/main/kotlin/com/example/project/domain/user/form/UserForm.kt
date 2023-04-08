package com.example.project.domain.user.form

import lombok.Data

@Data
data class UserForm(
  var id: Long,
  var email: String,
  var password: String,
)
