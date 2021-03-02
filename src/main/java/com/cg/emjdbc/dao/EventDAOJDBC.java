package com.cg.emjdbc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.cg.emjdbc.exception.EventManagementException;
import com.cg.emjdbc.model.Event;
import com.cg.emjdbc.util.ConnectionProvider;

public class EventDAOJDBC implements IEventDAO {
	
	ConnectionProvider conProvider;
	private static DateTimeFormatter dtFormater;


	public EventDAOJDBC() throws EventManagementException {


		try {
			conProvider = ConnectionProvider.getInstance();
		}catch (ClassNotFoundException | IOException exp) {
			throw new EventManagementException("Database is not reachable");
		}
	}

	
	@Override
	public String add(Event event) throws EventManagementException {
		String id = null;
		if (event != null) {
			
			try (Connection con = conProvider.getConnection();
				PreparedStatement pInsert = con.prepareStatement(IQueryMapper.ADD_BOOK_QRY)) {
				//System.out.println(conProvider.getConnection());
				pInsert.setString(1, event.getId());
				pInsert.setString(2, event.getTitle());
				pInsert.setDate(3, Date.valueOf(event.getScheduledDate()));
				pInsert.setString(4, event.getLocation());
				pInsert.setDouble(5, event.getCost());
				int rowCount = pInsert.executeUpdate();

				if (rowCount == 1) {
					id = event.getId();
				}
			} catch (SQLException exp) {
				throw new EventManagementException("Event is not inserted");
			}
		}
		return id;
	}

	@Override
	public boolean delete(String id) throws EventManagementException {
		boolean isDone = false;

		try (Connection con = conProvider.getConnection();
				PreparedStatement pDelete = con.prepareStatement(IQueryMapper.DEL_BOOK_QRY)) {

			pDelete.setString(1, id);

			int rowCount = pDelete.executeUpdate();

			if (rowCount == 1) {
				isDone = true;
			}
		} catch (SQLException exp) {

			throw new EventManagementException("Event is not inserted");
		}

		return isDone;
	}

	@Override
	public Event get(String id) throws EventManagementException {
		Event event=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con.prepareStatement(IQueryMapper.GET_BOOK_QRY)) {
			

			pSelect.setString(1, id);

			ResultSet rs = pSelect.executeQuery();
			
			if(rs.next()){
				event = new Event();
				event.setId(rs.getString("Id"));
				event.setTitle(rs.getString("title"));
				
				event.setScheduledDate(rs.getDate("sdate").toLocalDate());
				event.setLocation(rs.getString("location"));
				event.setCost(rs.getDouble("cost"));
			}
			
		} catch (SQLException exp) {

			throw new EventManagementException("Event is not available");
		}		
		return event;
	}

	@Override
	public List<Event> getAll() throws EventManagementException {
		List<Event> events=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con.prepareStatement(IQueryMapper.GET_ALL_BOOKS_QRY)) {
			//System.out.println(IQueryMapper.GET_ALL_BOOKS_QRY);

			ResultSet rs = pSelect.executeQuery();
			
			events = new ArrayList<Event>();
			
			while(rs.next()){
				Event event = new Event();
				event.setId(rs.getString("Id"));
				event.setTitle(rs.getString("title"));
				
				event.setScheduledDate(rs.getDate("sdate").toLocalDate());
				event.setLocation(rs.getString("location"));
				event.setCost(rs.getDouble("cost"));
				events.add(event);
			}
			
		} catch (SQLException exp) {

			throw new EventManagementException("No Events are available.");
		}		
		return events;	
	}

	@Override
	public List<Event> getAscendibgOrderDateScheduled() throws EventManagementException {
		List<Event> events=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con.prepareStatement(IQueryMapper.GET_ASCENDING_ORDER_DATE_QRY)) {
			//System.out.println(IQueryMapper.GET_ALL_BOOKS_QRY);

			ResultSet rs = pSelect.executeQuery();
			
			events = new ArrayList<Event>();
			
			while(rs.next()){
				Event event = new Event();
				event.setId(rs.getString("Id"));
				event.setTitle(rs.getString("title"));
				event.setScheduledDate(rs.getDate("sdate").toLocalDate());
				event.setLocation(rs.getString("location"));
				event.setCost(rs.getDouble("cost"));
				events.add(event);
			}
			
		} catch (SQLException exp) {

			throw new EventManagementException("No Events are available.");
		}		
		return events;	
	}
	
	@Override
	public List<Event> getAlphabeticalOrderLocation() throws EventManagementException {
		List<Event> events=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con.prepareStatement(IQueryMapper.GET_ALPHABETICAL_ORDER_LOCATION_QRY)) {
			//System.out.println(IQueryMapper.GET_ALL_BOOKS_QRY);

			ResultSet rs = pSelect.executeQuery();
			
			events = new ArrayList<Event>();
			
			while(rs.next()){
				Event event = new Event();
				event.setId(rs.getString("Id"));
				event.setTitle(rs.getString("title"));

				event.setScheduledDate(rs.getDate("sdate").toLocalDate());
				event.setLocation(rs.getString("location"));
				event.setCost(rs.getDouble("cost"));
				events.add(event);
			}
			
		} catch (SQLException exp) {

			throw new EventManagementException("No Events are available.");
		}		
		return events;	

	}
	
	@Override
	public List<Event> getParticularLocation(String location) throws EventManagementException {
		List<Event> events=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con.prepareStatement(IQueryMapper.GET_PARTICULAR_LOCATION_QRY)) {
			//System.out.println(IQueryMapper.GET_ALL_BOOKS_QRY);
			
			pSelect.setString(1, location);

			ResultSet rs = pSelect.executeQuery();
			
			events = new ArrayList<Event>();
			
			while(rs.next()){
				Event event = new Event();
				event.setId(rs.getString("Id"));
				event.setTitle(rs.getString("title"));

				event.setScheduledDate(rs.getDate("sdate").toLocalDate());
				event.setLocation(rs.getString("location"));
				event.setCost(rs.getDouble("cost"));
				events.add(event);
			}
			
		} catch (SQLException exp) {

			throw new EventManagementException("No Events are available.");
		}		
		return events;	
	}
	
	@Override
	public List<Event> getParticularDateScheduled(String dateScheduled) throws EventManagementException {
		List<Event> events=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con.prepareStatement(IQueryMapper.GET_PARTICULE_DATE_QRY)) {
			//System.out.println(IQueryMapper.GET_ALL_BOOKS_QRY);
			dtFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate scheduled=LocalDate.parse(dateScheduled, dtFormater);
			pSelect.setDate(1, Date.valueOf(scheduled));

			ResultSet rs = pSelect.executeQuery();

			
			events = new ArrayList<Event>();
			
			while(rs.next()){
				Event event = new Event();
				event.setId(rs.getString("Id"));
				event.setTitle(rs.getString("title"));

				event.setScheduledDate(rs.getDate("sdate").toLocalDate());
				event.setLocation(rs.getString("location"));
				event.setCost(rs.getDouble("cost"));
				events.add(event);
			}
			
		} catch (SQLException exp) {

			throw new EventManagementException("No Events are available.");
		}		
		return events;	
	}
	
	@Override
	public void persist() throws EventManagementException {
		
	}
		
}