package net.ntlx.shops.backend.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ntlx.shops.backend.business.abst.BlacklistBusiness;
import net.ntlx.shops.backend.model.Blacklist;
import net.ntlx.shops.backend.model.Costumer;
import net.ntlx.shops.backend.model.Shop;
import net.ntlx.shops.backend.repository.BlacklistRepository;
import net.ntlx.shops.backend.repository.CostumerRepository;
import net.ntlx.shops.backend.repository.ShopRepository;

@Service
public class BlacklistBusinessImpl implements BlacklistBusiness {

	@Autowired
	private BlacklistRepository blacklistRepository;

	@Autowired
	private CostumerRepository costumerRepository;

	@Autowired
	private ShopRepository shopRepository;

	@Override
	@Transactional
	public ResponseEntity<Blacklist> addBlacklist(Long idc, Long ids) {

		final Shop shop = shopRepository.findById(ids).get();
		final Costumer costumer = costumerRepository.findById(idc).get();
		if (blacklistRepository.blacklistExists(idc, ids) != null) {
			throw new RuntimeException("this blacklist already exists !!");
		} else {
			final Blacklist blacklist = new Blacklist();
			blacklist.setShop(shop);
			blacklist.setCostumer(costumer);
			blacklist.setDislike_date(new Date());
			final Blacklist newBL = blacklistRepository.save(blacklist);
			if (newBL == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(newBL);
			}
		}
	}

	@Override
	public Blacklist searchBlacklist(Long idb) {

		final Blacklist newBL = blacklistRepository.findById(idb).get();
		if (newBL == null)
			throw new RuntimeException("The blacklist - shop (id: " + idb + ") is not found !!");
		return newBL;
	}

	@Override
	public ResponseEntity<String> deleteBlacklist(Long idb) {

		blacklistRepository.deleteById(idb);
		if (blacklistRepository.existsById(idb)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("The blacklist - shop (" + idb + ") is not deleted !!");
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@Override
	public List<Shop> listBlacklistShops(Long idc) {
		return blacklistRepository.listBlacklistShops(idc);
	}

	@Override
	public List<Blacklist> listBlacklist(Long idc) {
		return blacklistRepository.listBlacklist(idc);
	}
}
