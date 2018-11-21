package net.ntlx.shops.backend.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"COSTUMER\"")
public class Costumer implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1593921992516923803L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID\"")
	private Long id;

	@Column(name = "\"FIRSTNAME\"")
	private String firstname;

	@Column(name = "\"LASTNAME\"")
	private String lastname;

	@Column(name = "\"USERNAME\"")
	private String username;

	@Column(name = "\"PASSWORD\"")
	private String password;

	/**
	 * The collection below is a is a list of the liked shops !!
	 */
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "\"BOOKMARKS\"", joinColumns = { @JoinColumn(name = "\"ID_COSTUMER\"") }, inverseJoinColumns = {
			@JoinColumn(name = "\"ID_SHOP\"") })
	private Collection<Shop> bookmarks;

	/**
	 * The collection below is a is a list of the disliked shops !!
	 */
	@OneToMany(mappedBy = "costumer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Collection<Blacklist> blacklists;

	/**
	 * I used @JsonIgnore to not display lists of bookmarks and blacklists
	 */
	@JsonIgnore
	public Collection<Blacklist> getBlacklists() {
		return blacklists;
	}

	@JsonIgnore
	public Collection<Shop> getBookmarks() {
		return bookmarks;
	}
}
