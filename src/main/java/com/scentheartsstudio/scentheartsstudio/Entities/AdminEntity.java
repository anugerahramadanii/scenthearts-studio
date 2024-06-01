package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "m_admin")
public class AdminEntity extends BaseProperties {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long biodata_id;

	@Column(length = 10)
	private String code;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBiodata_id() {
		return this.biodata_id;
	}

	public void setBiodata_id(Long biodata_id) {
		this.biodata_id = biodata_id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
