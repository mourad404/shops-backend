package net.ntlx.shops.backend.business.abst;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import net.ntlx.shops.backend.model.Shop;

public interface ShopBusiness {

	public ResponseEntity<Page<Shop>> listShops(Long idc, Integer page, Integer size);

	public ResponseEntity<Shop> addShop(Shop s);

	public Shop searchShop(Long ids);

}
