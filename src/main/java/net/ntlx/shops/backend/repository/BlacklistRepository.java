package net.ntlx.shops.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ntlx.shops.backend.model.Blacklist;
import net.ntlx.shops.backend.model.Shop;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {

	@Query("SELECT s FROM Shop s, Blacklist b WHERE s.id = b.shop.id AND b.costumer.id = :idc")
	public List<Shop> listBlacklistShops(@Param("idc") Long idc);

	@Query("SELECT b FROM Blacklist b WHERE b.costumer.id = :idc")
	public List<Blacklist> listBlacklist(@Param("idc") Long idc);

	@Query("SELECT b FROM Blacklist b WHERE b.costumer.id = :idc AND b.shop.id = :ids")
	public Blacklist blacklistExists(@Param("idc") Long idc, @Param("ids") Long ids);
}
