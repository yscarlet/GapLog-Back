package com.gaplog.server.domain.category.api;

import com.gaplog.server.domain.category.dto.request.CategorySaveRequest;
import com.gaplog.server.domain.category.dto.request.CategoryUpdateRequest;
import com.gaplog.server.domain.category.domain.Category;
import com.gaplog.server.domain.category.application.CategoryService;
import com.gaplog.server.domain.category.dto.response.CategoryTreeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//To Do: closure table을 활용한 쿼리 작성
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Category API")
public class CategoryApi {

    private final CategoryService categoryService;

    @GetMapping("users/{user_id}")
    @Operation(summary = "유저 카테고리 조회", description = "유저의 카테고리 정보를 얻습니다.")
    public ResponseEntity<List<CategoryTreeResponse>> getCategoryTree(@PathVariable("user_id") Long userId) {
        List<CategoryTreeResponse> categoryTree = categoryService.getCategoryTree(userId);
        return new ResponseEntity<>(categoryTree, HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    @Operation(summary = "카테고리 게시글 조회", description = "카테고리의 게시글 정보를 얻습니다.")
    // To Do: Post Api 완성 후 Post 반환하도록 변경
    public ResponseEntity<List<Category>> getSubCategories(@PathVariable("category_id") Long categoryId) {
        List<Category> subCategories = categoryService.getSubCategories(categoryId);
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "카테고리 추가", description = "카테고리를 추가합니다.")
    public ResponseEntity<Category> addCategory(@RequestBody CategorySaveRequest request) {
        Category newCategory = categoryService.addCategory(request.getUserId(), request.getAncestorId(), request.getName());
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Operation(summary = "카테고리 수정", description = "카테고리를 수정합니다.")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryUpdateRequest request) {
        categoryService.updateCategory(request.getCategoryId(), request.getAncestorId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    @Operation(summary = "하위 카테고리 삭제", description = "카테고리를 삭제합니다.")
    public ResponseEntity<Void> deleteCategory(@PathVariable("category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
