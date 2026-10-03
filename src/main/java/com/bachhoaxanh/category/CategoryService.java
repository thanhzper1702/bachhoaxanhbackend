package com.bachhoaxanh.category;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for Category operations with VARCHAR(50) PK.
 */
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(String maLoaiSanPham) {
        return categoryRepository.findById(maLoaiSanPham);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAllActive();
    }

    public Category updateCategory(String maLoaiSanPham, Category category) {
        return null;
    }

    public void deleteCategory(String maLoaiSanPham) {
        categoryRepository.softDeleteById(maLoaiSanPham);
    }
}
