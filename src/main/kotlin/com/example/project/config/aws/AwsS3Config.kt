package com.example.project.config.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsS3Config {
    /**
     * ローカルからクレデンシャル情報を取得してS3を操作する設定
     * ConfigにS3クライアントを設定することで、DIで使用することができる
     * */
    @Bean
    fun s3Client(): AmazonS3 {
        return AmazonS3ClientBuilder.defaultClient()
    }
}

/**
 * 以下のように、クレデンシャルを明示的に指定する方法もある
 */
//@Bean
//fun s3client(): AmazonS3 {
//    val credentials = BasicAWSCredentials(accessKey, secretKey)
//
//    return AmazonS3ClientBuilder.standard()
//        .withRegion(Regions.fromName(region))
//        .withCredentials(AWSStaticCredentialsProvider(credentials))
//        .build()
//}