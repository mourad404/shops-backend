package net.ntlx.shops.backend.business.abst;

import java.util.List;

import org.springframework.http.ResponseEntity;

import net.ntlx.shops.backend.model.Costumer;
import net.ntlx.shops.backend.model.Shop;

public interface CostumerBusiness {

	public List<Costumer> listCostumers();

	public ResponseEntity<Costumer> addCostumer(Costumer c);

	public Costumer searchCostumer(Long idco);

	public Costumer addOrDeleteBookmark(Long idc, Long ids);

	public List<Shop> listBookmarks(Long idc);
}
