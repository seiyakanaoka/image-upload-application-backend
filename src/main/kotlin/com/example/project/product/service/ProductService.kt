package com.example.project.product.service

import com.example.project.product.dto.ProductDTO
import com.example.project.product.entity.Product
import com.example.project.product.repository.ProductRepository
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
@RequiredArgsConstructor
class ProductService(private val todoRepository: ProductRepository) {

    /**
     * 全データ検索
     */
    fun findAll(): List<ProductDTO> {
        return todoRepository.findAll().map { it -> ProductDTO(it) }
    }

    /**
     * 1件検索
     */
    fun findById(id: Long): ProductDTO {
        val product = todoRepository.findById(id).orElseThrow()
        return ProductDTO(product)
    }

    /**
     * １件保存
     * @param product
     */
    fun save(product: Product): ProductDTO {
        val newProduct = todoRepository.save(product)
        return ProductDTO(newProduct)
    }

    /**
     * 1件更新
     * @param product
     */
    fun put(id: Long, product: Product): ProductDTO {
        val newProduct = Product(id, product.title, product.price)
        val productDTO = todoRepository.save(newProduct)
        return ProductDTO(productDTO)
    }

    /**
     * 1件削除
     * @param id
     */
    fun deleteById(id: Long) = todoRepository.deleteById(id)

    /**
     * 全データ検索
     */
    fun convertProductImageToBase64(productImage: MultipartFile): HttpEntity<ByteArray> {
        val productImage = Base64.getEncoder().encodeToString(productImage.bytes)
        val imageBytes = Base64.getDecoder().decode(productImage)

        // レスポンスデータとして返却
        val headers = HttpHeaders()
        headers.contentType = MediaType.IMAGE_JPEG
        headers.contentLength = imageBytes.size.toLong()

        val response = HttpEntity<ByteArray>(imageBytes, headers)

        println("response : $response")

        return response
    }

}