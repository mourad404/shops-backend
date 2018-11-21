package net.ntlx.shops.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ntlx.shops.backend.business.abst.ShopBusiness;
import net.ntlx.shops.backend.model.Shop;

@RestController
public class ShopController {

	@Autowired
	private ShopBusiness shopBusiness;

	@Secured(value = { "ROLE_USER" })
	@GetMapping("/costumers/{idc}/shops")
	public ResponseEntity<Page<Shop>> listShops(@PathVariable("idc") Long idc,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "4") Integer size) {

		return shopBusiness.listShops(idc, page, size);
	}

	@PostMapping("/public/shops")
	public ResponseEntity<Shop> ajouterEntreprise(@RequestBody Shop s) {
		return shopBusiness.addShop(s);
	}

	@Secured(value = { "ROLE_USER" })
	@GetMapping("/shops/{ids}")
	public Shop searchShop(@PathVariable("ids") Long id) {
		return shopBusiness.searchShop(id);
	}
}
