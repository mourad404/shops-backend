package net.ntlx.shops.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "\"SHOP\"")
public class Shop implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5553454354880686116L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID\"")
	private Long id;

	@Column(name = "\"NAME\"")
	private String name;

	/**
	 * The activity domain of the shop !! Example : Restaurant !
	 */
	@ManyToOne
	@JoinColumn(name = "\"ID_CATEGORIE\"")
	private Category category;

	@Column(name = "\"ADDRESS\"")
	private String address;

	/**
	 * Longitude and latitude are needed to confirm the exact location of the shop,
	 * they'll be used in google maps api to calculate distance between current user
	 * location and shops locations.
	 */
	@Column(name = "\"LONGITUDE\"")
	private String longitude;

	@Column(name = "\"LATITUDE\"")
	private String latitude;

	@Column(name = "\"IMAGE\"")
	private String image;

}
