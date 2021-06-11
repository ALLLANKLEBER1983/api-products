package com.compassouol.productms.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DefaultError {
	
	private int status_code;
	private String message;

}
