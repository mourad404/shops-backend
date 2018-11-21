package net.ntlx.shops.backend.business.abst;

import java.util.List;

import org.springframework.http.ResponseEntity;

import net.ntlx.shops.backend.model.Blacklist;
import net.ntlx.shops.backend.model.Shop;

public interface BlacklistBusiness {

	public ResponseEntity<Blacklist> addBlacklist(Long idc, Long ids);

	public Blacklist searchBlacklist(Long idb);

	public ResponseEntity<String> deleteBlacklist(Long idb);

	public List<Shop> listBlacklistShops(Long idc);

	public List<Blacklist> listBlacklist(Long idc);
}