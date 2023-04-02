package com.example.project.domain.aws.s3.controller

import com.example.project.domain.aws.s3.dto.AwsS3DTO
import com.example.project.domain.aws.s3.service.AwsS3Service
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RequiredArgsConstructor
@RequestMapping("api/aws/s3")
@RestController
class AwsS3Controller(private val awsS3Service: AwsS3Service) {
  @GetMapping("/user/{id}/images")
  fun getImages(@PathVariable id: Long): List<AwsS3DTO> {
    return awsS3Service.getImages(id)
  }

  @GetMapping("/image")
  fun getImage(@RequestParam("objectKey", required = true) objectKey: String): AwsS3DTO {
    return awsS3Service.getImageURL(objectKey)
  }

  @PostMapping("/image")
  fun postImage(
    @RequestParam("image", required = true) image: MultipartFile
  ) {
    awsS3Service.uploadImage("user", image)
  }
}