package com.scentheartsstudio.scentheartsstudio.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging<T> {
	private Integer page;
	private Integer total_data;
	private Integer total_page;
	private T list;
}
