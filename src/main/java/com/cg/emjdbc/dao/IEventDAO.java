package com.cg.emjdbc.dao;

import java.util.List;

import com.cg.emjdbc.exception.EventManagementException;
import com.cg.emjdbc.model.Event;

public interface IEventDAO {
	String add(Event event) throws EventManagementException;
	boolean delete(String id)throws EventManagementException;
	Event get(String id) throws EventManagementException;
	List<Event> getAll() throws EventManagementException;
	List<Event> getAscendibgOrderDateScheduled() throws EventManagementException;
	List<Event> getAlphabeticalOrderLocation() throws EventManagementException;
	List<Event> getParticularLocation(String location) throws EventManagementException;
	List<Event> getParticularDateScheduled(String dateScheduled) throws EventManagementException;
	void persist() throws EventManagementException;
}
