package com.kormul.skeleton.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "trade")
@AllArgsConstructor
public class Trade {
	
	@Id
	@Column(name = "tradeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tradeId;
	
	@NotBlank(message = "Account is mandatory")
	@Column(name = "account")
	private String account;
	
	@NotBlank(message = "Type is mandatory")
	@Column(name = "type")
	private String type;
	
	@Column(name = "buyQuantity")
	private Double buyQuantity;
	
	@Column(name = "sellQuantity")
	private Double sellQuantity;
	
	@Column(name = "buyPrice")
	private Double buyPrice;
	
	@Column(name = "sellPrice")
	private Double sellPrice;
	
	@Column(name = "benchmark")
	private String benchmark;
	
	@Column(name = "tradeDate")
	private Timestamp tradeDate;
	
	@Column(name = "security")
	private String security;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "trader")
	private String trader;
	
	@Column(name = "book")
	private String book;
	
	@Column(name = "creationName")
	private String creationName;
	
	@Column(name = "creationDate")
	private Timestamp creationDate;
	
	@Column(name = "revisionName")
	private String revisionName;
	
	@Column(name = "revisionDate")
	private Timestamp revisionDate;
	
	@Column(name = "dealName")
	private String dealName;
	
	@Column(name = "dealType")
	private String dealType;
	
	@Column(name = "sourceListId")
	private String sourceListId;
	
	@Column(name = "side")
	private String side;

	public Trade(@NotBlank(message = "Account is mandatory") String account, @NotBlank(message = "Type is mandatory") String type) {
		this.account = account;
		this.type = type;
	}
}
