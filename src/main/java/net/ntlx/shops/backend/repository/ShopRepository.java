package net.ntlx.shops.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ntlx.shops.backend.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

	/**
	 * This method lists all shops except Those who are Liked or disliked (- 2hours)
	 */
	@Query(value = "SELECT s.* FROM shop s WHERE s.id NOT IN (SELECT bm.id_shop FROM bookmarks bm WHERE bm.id_costumer = :idc) "
			+ "AND s.id NOT IN (SELECT bl.id_shop FROM blacklist bl WHERE bl.id_costumer = :idc)", nativeQuery = true)
	public Page<Shop> listShopsNoBmBL(@Param("idc") Long idc, Pageable p);

}
