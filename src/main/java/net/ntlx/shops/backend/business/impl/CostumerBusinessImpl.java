package net.ntlx.shops.backend.business.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ntlx.shops.backend.business.abst.CostumerBusiness;
import net.ntlx.shops.backend.model.Costumer;
import net.ntlx.shops.backend.model.Shop;
import net.ntlx.shops.backend.repository.CostumerRepository;
import net.ntlx.shops.backend.repository.ShopRepository;

@Service
public class CostumerBusinessImpl implements CostumerBusiness {

	@Autowired
	private CostumerRepository costumerRepository;

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<Costumer> listCostumers() {
		return costumerRepository.findAll();
	}

	@Override
	@Transactional
	public ResponseEntity<Costumer> addCostumer(Costumer c) {
		if (costumerRepository.getByUsername(c.getUsername()) != null)
			throw new RuntimeException("Cet email est déjà utilisé !!");
		c.setPassword(bCryptPasswordEncoder.encode(c.getPassword()));
		final Costumer newCostumer = costumerRepository.save(c);
		if (newCostumer == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(newCostumer);
		}
	}

	@Override
	public Costumer searchCostumer(Long idco) {

		final Costumer newCostumer = costumerRepository.findById(idco).get();
		if (newCostumer == null)
			throw new RuntimeException("The costumer (id: " + idco + ") is not found !!");
		return newCostumer;
	}

	/**
	 * add or delete a shop to/from the preferred shops list
	 */
	@Override
	@Transactional
	public Costumer addOrDeleteBookmark(Long idc, Long ids) {

		final Shop shop = shopRepository.findById(ids).get();
		final Costumer costumer = costumerRepository.findById(idc).get();
		final Collection<Shop> collecBookmarks = costumer.getBookmarks();
		if (collecBookmarks.contains(shop)) {
			collecBookmarks.remove(shop);
		} else {
			collecBookmarks.add(shop);
		}
		return costumerRepository.save(costumer);
	}

	/**
	 * List of preferred shops
	 */
	@Override
	public List<Shop> listBookmarks(Long idc) {

		final Costumer costumer = costumerRepository.findById(idc).get();
		if (costumer == null)
			throw new RuntimeException("Costumer with (id: " + idc + ") is not found !!");
		final List<Shop> listBookmarks = (List<Shop>) costumer.getBookmarks();
		return listBookmarks;
	}
}
