package net.ntlx.shops.backend.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.ntlx.shops.backend.business.abst.CategoryBusiness;
import net.ntlx.shops.backend.model.Category;
import net.ntlx.shops.backend.repository.CategoryRepository;

@Service
public class CategoryBusinessImpl implements CategoryBusiness {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> listCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public ResponseEntity<Category> addCategoy(Category c) {

		final Category newCategory = categoryRepository.save(c);
		if (newCategory == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
		}
	}

	@Override
	public Category searchCategory(Long idc) {

		final Category newCategory = categoryRepository.findById(idc).get();
		if (newCategory == null)
			throw new RuntimeException("The category (id: " + idc + ") is not found !!");
		return newCategory;
	}

}
