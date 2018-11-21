package net.ntlx.shops.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.ntlx.shops.backend.business.abst.CategoryBusiness;
import net.ntlx.shops.backend.model.Category;

@RestController
public class CategoryController {

	@Autowired
	private CategoryBusiness categoryBusiness;

	@Secured(value = { "ROLE_USER" })
	@GetMapping("/categories")
	public List<Category> listCategories() {
		return categoryBusiness.listCategories();
	}

	@Secured(value = { "ROLE_USER" })
	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestBody Category c) {
		return categoryBusiness.addCategoy(c);
	}

	@Secured(value = { "ROLE_USER" })
	@GetMapping("/categories/{idc}")
	public Category searchCategory(@PathVariable("idc") Long id) {
		return categoryBusiness.searchCategory(id);
	}
}
