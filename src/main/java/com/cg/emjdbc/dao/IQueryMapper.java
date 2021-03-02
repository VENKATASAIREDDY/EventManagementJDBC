package com.cg.emjdbc.dao;

public interface IQueryMapper {

	public static final String ADD_BOOK_QRY = 
			"INSERT INTO events(id,  title, sdate, location, cost) VALUES(?,?,?,?,?)";
	public static final String MODIFY_BOOK_QRY = 
			"UPDATE events SET title=?,price=?,pdate=? WHERE bcode=?";
	public static final String DEL_BOOK_QRY = 
			"DELETE FROM events WHERE id=?";
	public static final String GET_ALL_BOOKS_QRY = 
			"SELECT * FROM events";
	public static final String GET_ASCENDING_ORDER_DATE_QRY = 
			"SELECT * FROM events ORDER BY sdate";
	public static final String GET_ALPHABETICAL_ORDER_LOCATION_QRY = 
			"SELECT * FROM events ORDER BY location";
	public static final String GET_PARTICULAR_LOCATION_QRY = 
			"SELECT * FROM events WHERE location=?";
	public static final String GET_BOOK_QRY = 
			"SELECT * FROM events WHERE id=?";
	public static final String GET_PARTICULE_DATE_QRY = 
			"SELECT * FROM events WHERE sdate=?";
}