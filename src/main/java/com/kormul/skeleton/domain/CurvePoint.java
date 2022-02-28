package com.kormul.skeleton.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "curvepoint")
@AllArgsConstructor
@NoArgsConstructor
public class CurvePoint {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "must not be null")
	@Column(name = "curveId")
	private Integer curveId;

	@Column(name = "asOfDate")
	private Timestamp asOfDate;

	@Column(name = "term")
	private Double term;

	@Column(name = "value")
	private Double value;

	@Column(name = "creationDate")
	private Timestamp creationDate;

	public CurvePoint(int curveId, double term, double value) {
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

}
