package com.bachhoaxanh.product;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for Product operations with VARCHAR(50) PK.
 */
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(String maSanPham) {
        return productRepository.findById(maSanPham);
    }

    public List<Product> getAllActiveProducts() {
        return productRepository.findAllActive();
    }

    public List<Product> getProductsByCategory(String categoryId) {
        return productRepository.findByLoaiSanPhamMaLoaiSanPhamAndDeletedFalse(categoryId);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.searchByName(keyword);
    }

    public Product updateProduct(String maSanPham, Product product) {
        return null;
    }

    public void deleteProduct(String maSanPham) {
        productRepository.softDeleteById(maSanPham);
    }
}
