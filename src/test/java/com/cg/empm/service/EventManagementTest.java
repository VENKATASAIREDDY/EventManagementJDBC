package com.cg.empm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cg.emjdbc.exception.EventManagementException;
import com.cg.emjdbc.model.Event;
import com.cg.emjdbc.service.EventManagementServiceImpl;

class EventManagementTest {

	EventManagementServiceImpl service;
	
	@BeforeEach
	void runBeforeAllTestCase() throws EventManagementException {
		service = new EventManagementServiceImpl();
	}
	
	@AfterEach
	void cleanAfterAllTestCase() {
		service=null;
	}
	

	
	@Test
	@DisplayName("EventDetails should throw Exception.")
	void eventDetailsShouldDisplayException() throws EventManagementException {
		Event event=new Event("E102","Marriage",LocalDate.parse("2021-10-19"),"Ongole",6800.0);
		assertThrows(EventManagementException.class,()->{service.add(event);});
	}

	@Test
	@DisplayName("Event delete should execute")
	void eventDeleteShouldDisplay() throws EventManagementException {
		boolean actual = service.delete("E100");
		boolean expected=true;
		assertEquals(expected,actual);
	}
}
