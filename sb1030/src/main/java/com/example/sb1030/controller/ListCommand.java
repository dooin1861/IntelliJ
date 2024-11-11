package com.example.sb1030.controller;

import java.time.LocalDate;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ListCommand {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate from;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate to;

}
