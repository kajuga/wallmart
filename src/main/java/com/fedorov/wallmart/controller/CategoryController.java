package com.fedorov.wallmart.controller;

import com.fedorov.wallmart.model.CategoryModel;
import com.fedorov.wallmart.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Api(value="onlinestore", description="Operations pertaining to categories in Wallmart online store")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search a category with an ID", response = CategoryModel.class)
    @ApiResponses(value = { @ApiResponse(code = 100, message = "100 is the message"),
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 666, message = "666 custom status"),
            @ApiResponse(code = 400, message = "Huyssful")})
    public ResponseEntity<CategoryModel> getCategory(@PathVariable("id") Long categoryModelId) {
        if (categoryModelId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CategoryModel categoryModel = categoryService.getById(categoryModelId);
        if (categoryModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CategoryModel>(categoryService.getById(categoryModelId), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a NEW category")
    //TODO проверка на наличие категории с идентичным названием
    public ResponseEntity<CategoryModel> saveCategory(@RequestBody CategoryModel categoryModel) {
        HttpHeaders headers = new HttpHeaders();
        if (categoryModel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        categoryService.create(categoryModel);
        return new ResponseEntity<>(categoryModel, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a category")
    public ResponseEntity<CategoryModel> updateCategory(@RequestBody @Validated CategoryModel categoryModel, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        if (categoryModel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long id = categoryModel.getId();
        categoryService.update(categoryModel, id);
        return new ResponseEntity<>(categoryModel, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a category")
    public ResponseEntity<CategoryModel> deleteCategory(@PathVariable("id") Long categoryModelId) {
        if (categoryService.getById(categoryModelId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(categoryModelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available categories",response = Iterable.class)
    public ResponseEntity<List<CategoryModel>> getAllCategorys() {
        List<CategoryModel> categoryModelList = categoryService.readAll();
        if (categoryModelList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryModelList, HttpStatus.OK);
    }
}