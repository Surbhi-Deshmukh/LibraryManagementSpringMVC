package com.nagarro.advjavaassgn5a.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

	@Id
	String bookCode;
	String bookName;
	@ManyToOne(fetch = FetchType.LAZY)
	Author author;
	Date addedDate;

}
