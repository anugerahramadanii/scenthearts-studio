package com.scentheartsstudio.scentheartsstudio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "m_admin")
@Getter
@Setter
public class AdminEntity extends BaseProperties {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long biodata_id;

	@Column(length = 10)
	private String code;
}
