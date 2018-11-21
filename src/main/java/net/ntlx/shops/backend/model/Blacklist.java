package net.ntlx.shops.backend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"BLACKLIST\"")
public class Blacklist implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5040807388352613092L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID\"")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "\"ID_COSTUMER\"")
	private Costumer costumer;

	@ManyToOne
	@JoinColumn(name = "\"ID_SHOP\"")
	private Shop shop;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "\"DISLIKE_DATE\"")
	private Date dislike_date;
}
