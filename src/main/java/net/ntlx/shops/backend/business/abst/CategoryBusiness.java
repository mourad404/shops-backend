package net.ntlx.shops.backend.business.abst;

import java.util.List;

import org.springframework.http.ResponseEntity;

import net.ntlx.shops.backend.model.Category;

public interface CategoryBusiness {

	public List<Category> listCategories();

	public ResponseEntity<Category> addCategoy(Category c);

	public Category searchCategory(Long idc);

}
