package net.ntlx.shops.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ntlx.shops.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
