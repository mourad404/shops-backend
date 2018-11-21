package net.ntlx.shops.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

	@Secured(value = { "ROLE_USER" })
	@GetMapping("/costumers")
	public List<Costumer> listCostumers() {
		return costumerBusiness.listCostumers();
	}

	@PostMapping("/public/costumers")
	public ResponseEntity<Costumer> addCostumer(@RequestBody Costumer c) {
		return costumerBusiness.addCostumer(c);
	}

	@Secured(value = { "ROLE_USER" })
	@GetMapping("/costumers/{idc}")
	public Costumer searchCostumer(@PathVariable("idc") Long id) {
		return costumerBusiness.searchCostumer(id);
	}

	/**
	 * BOOKMARK
	 */
	@Secured(value = { "ROLE_USER" })
	@PutMapping("/costumers/{idc}/shops/{ids}")
	public Costumer addOrDeleteBookmark(@PathVariable("idc") Long idc, @PathVariable("ids") Long ids) {
		return costumerBusiness.addOrDeleteBookmark(idc, ids);
	}

	/**
	 * BOOKMARK
	 */
	@Secured(value = { "ROLE_USER" })
	@GetMapping("/costumers/{idc}/shopsBM")
	public List<Shop> listBookmarks(@PathVariable("idc") Long id) {
		return costumerBusiness.listBookmarks(id);
	}
}
