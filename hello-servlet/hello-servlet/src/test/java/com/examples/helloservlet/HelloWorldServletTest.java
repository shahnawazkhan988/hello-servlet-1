package com.examples.helloservlet;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.examples.helloservlet.services.HelloNameService;

public class HelloWorldServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private RequestDispatcher requestDispatcher;
	@Mock
	private HelloNameService helloNameService;

	@InjectMocks
	private HelloWorldServlet helloWorldServlet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void doGet() throws Exception {
		StringWriter stringWriter = new StringWriter();
		when(response.getWriter())
			.thenReturn(new PrintWriter(stringWriter));
		when(request.getContextPath())
			.thenReturn("/");

		helloWorldServlet.doGet(request, response);

		assertThat(stringWriter.toString()).isEqualTo("Served at: /");
	}

	@Test
	public void doPost() throws Exception {
		when(request.getParameter("name"))
			.thenReturn("A Name");
		when(request.getRequestDispatcher("response.jsp")).
			thenReturn(requestDispatcher);
		when(helloNameService.processName(anyString()))
			.thenReturn("A Name Processed");

		helloWorldServlet.doPost(request, response);

		verify(request).setAttribute("user", "A Name Processed");
		verify(requestDispatcher).forward(request, response);
	}
}