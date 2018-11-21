package net.ntlx.shops.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.ntlx.shops.backend.business.abst.CostumerBusiness;
import net.ntlx.shops.backend.model.Costumer;
import net.ntlx.shops.backend.model.Shop;

@RestController
public class CostumerController {

	@Autowired
	private CostumerBusiness costumerBusiness;

	@GetMapping("/costumers")
	public List<Costumer> listCategories() {
		return costumerBusiness.listCostumers();
	}

	@PostMapping("/costumers")
	public ResponseEntity<Costumer> addCostumer(@RequestBody Costumer c) {
		return costumerBusiness.addCostumer(c);
	}

	@GetMapping("/costumers/{idc}")
	public Costumer searchCostumer(@PathVariable("idc") Long id) {
		return costumerBusiness.searchCostumer(id);
	}

	/**
	 * BOOKMARK
	 */
	@PutMapping("/costumers/{idc}/shops/{ids}")
	public Costumer addOrDeleteBookmark(@PathVariable("idc") Long idc, @PathVariable("ids") Long ids) {
		return costumerBusiness.addOrDeleteBookmark(idc, ids);
	}

	/**
	 * BOOKMARK
	 */
	@GetMapping("/costumers/{idc}/shopsBM")
	public List<Shop> listBookmarks(@PathVariable("idc") Long id) {
		return costumerBusiness.listBookmarks(id);
	}
}
