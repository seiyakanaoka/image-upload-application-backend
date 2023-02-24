package com.example.project.product.controller

import com.example.project.product.dto.ProductDTO
import com.example.project.product.entity.Product
import com.example.project.product.service.ProductService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RequestMapping("api/product")
@RestController
@RequiredArgsConstructor
class ProductController(
    private val productService: ProductService
) {

    /**
     * 全件取得
     */
    @GetMapping
    public fun index(@RequestParam(value = "title", defaultValue = "") title: String): List<ProductDTO> {
        return productService.findAll()
    }

    /**
     * 1件検索
     * @param id
     */
    @GetMapping("{id}")
    public fun findById(@PathVariable id: Long): ProductDTO {
        return productService.findById(id)
    }

    /**
     * 投稿処理("post")
     * @param todo
     */
    @PostMapping
    public fun create(@RequestBody todo: Product): ProductDTO {
        return productService.save(todo)
    }

    /**
     * 更新処理
     * @param id
     */
    @PutMapping("/put/{id}")
    public fun put(@RequestBody todo: Product, @PathVariable id: Long): ProductDTO {
        return productService.put(id, todo)
    }

    /**
     * 削除処理
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public fun deleteById(@PathVariable id: Long): Unit {
        return productService.deleteById(id)
    }

    /**
     * 画像パスを取得しBase64の画像情報を返却する
     */
    @PostMapping("/image")
    fun productImageBase64(@RequestParam("productImage") productImage: MultipartFile): HttpEntity<ByteArray> {
        return productService.convertProductImageToBase64(productImage)
    }
}