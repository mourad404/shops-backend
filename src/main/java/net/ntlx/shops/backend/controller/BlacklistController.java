package net.ntlx.shops.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ntlx.shops.backend.business.abst.BlacklistBusiness;
import net.ntlx.shops.backend.model.Blacklist;
import net.ntlx.shops.backend.model.Shop;

@RestController
public class BlacklistController {

	@Autowired
	private BlacklistBusiness blacklistBusiness;

	@Secured(value = { "ROLE_USER" })
	@PostMapping("/costumers/{idc}/shops/{ids}")
	public ResponseEntity<Blacklist> addBlacklist(@PathVariable("idc") Long idc, @PathVariable("ids") Long ids) {
		return blacklistBusiness.addBlacklist(idc, ids);
	}

	@Secured(value = { "ROLE_USER" })
	@GetMapping("/costumers/{idc}/shopsBL")
	public List<Shop> listBalcklistShops(@PathVariable("idc") Long idc) {
		return blacklistBusiness.listBlacklistShops(idc);
	}
}
