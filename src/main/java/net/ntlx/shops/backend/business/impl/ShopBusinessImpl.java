package net.ntlx.shops.backend.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.ntlx.shops.backend.business.abst.BlacklistBusiness;
import net.ntlx.shops.backend.business.abst.ShopBusiness;
import net.ntlx.shops.backend.model.Blacklist;
import net.ntlx.shops.backend.model.Shop;
import net.ntlx.shops.backend.repository.ShopRepository;

@Service
public class ShopBusinessImpl implements ShopBusiness {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private BlacklistBusiness blacklistBusiness;

	@Override
	public ResponseEntity<Page<Shop>> listShops(Long idc, Integer page, Integer size) {

		final List<Blacklist> blacklists = blacklistBusiness.listBlacklist(idc);
		Date now = new Date();
		/**
		 * I removed all blacklists created before the previous 2 hours ::
		 */
		for (Blacklist bl : blacklists) {
			if (now.getTime() - bl.getDislike_date().getTime() > 60000) {
				blacklistBusiness.deleteBlacklist(bl.getId());
			}
		}
		final Pageable p = PageRequest.of(page, size);
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(shopRepository.listShopsNoBmBL(idc, p));
	}

	@Override
	public ResponseEntity<Shop> addShop(Shop s) {

		final Shop newShop = shopRepository.save(s);
		if (newShop == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(newShop);
		}
	}

	@Override
	public Shop searchShop(Long ids) {

		final Shop newShop = shopRepository.findById(ids).get();
		if (newShop == null)
			throw new RuntimeException("The shop (id: " + ids + ") is not found !!");
		return newShop;
	}

}
