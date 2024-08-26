package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseProperties {
	@Column(nullable = false)
	private Long created_by;

	@Column(nullable = false)
	private Date created_on;

	@Column
	private Long modified_by;

	@Column
	private Date modified_on;

	@Column
	private Long deleted_by;

	@Column
	private Date deleted_on;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean is_delete = false;
}
