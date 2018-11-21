package net.ntlx.shops.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"CATEGORY\"")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7149846792097800735L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID\"")
	private Long id;

	// @NotBlank(message="La catégorie doit etre renseignée")
	// @Size(max=50)
	@Column(name = "\"LABEL\"", length = 50)
	private String label;


}
