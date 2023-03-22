package com.example.project.domain.aws.s3.service

import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.project.config.aws.AwsS3Config
import com.example.project.domain.aws.s3.dto.AwsS3DTO
import com.example.project.domain.aws.s3.entity.AwsS3
import com.example.project.domain.aws.s3.repository.AwsS3Repository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.net.URL
import java.util.*

@Service
class AwsS3Service(private val awsS3Config: AwsS3Config, private val awsS3Repository: AwsS3Repository) {

  private val s3Client = awsS3Config.s3Client()

  @Value("\${aws.s3.image.bucketName}")
  private val bucketName = ""

  /**
   * S3の指定したバケットの画像urlを取得する
   * @param bucketName バケット名
   * @param objectKey オブジェクトキー名
   * @param expirationDate 有効期限(指定しなかった場合、1時間を有効期限とする)
   * */
  fun getImageURL(objectKey: String, expirationDate: Date = Date(System.currentTimeMillis() + 3600000)): AwsS3DTO {
    val imageUrlRequest = GeneratePresignedUrlRequest(bucketName, objectKey)
      .withExpiration(expirationDate)
    val url: URL = s3Client.generatePresignedUrl(imageUrlRequest)
    s3Client.shutdown()
    return AwsS3DTO(url.toString())
  }

  /**
   * S3に画像をアップロードする
   * @param prefix 画像のprefix
   * @param multipartFile アップロードする画像
   */
  fun uploadImage(prefix: String, multipartFile: MultipartFile): Unit {
    // アップロードする画像のヘッダー情報
    val metadata = ObjectMetadata()
    metadata.contentLength = multipartFile.size
    metadata.contentType = multipartFile.contentType

    val inputStream = ByteArrayInputStream(multipartFile.bytes)
    val objectKey = prefix + "-" + UUID.randomUUID().toString()

    val request = PutObjectRequest(bucketName, objectKey, inputStream, metadata)
    s3Client.putObject(request)
    s3Client.shutdown()
    val awsS3 = AwsS3(0, objectKey)
    awsS3Repository.save(awsS3)
  }
}