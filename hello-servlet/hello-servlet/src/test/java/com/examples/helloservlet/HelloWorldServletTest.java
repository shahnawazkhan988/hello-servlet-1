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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HelloWorldServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private RequestDispatcher requestDispatcher;

	private HelloWorldServlet helloWorldServlet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		helloWorldServlet = new HelloWorldServlet();
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
	public void doPostWithoutName() throws Exception {
		when(request.getRequestDispatcher("response.jsp")).
			thenReturn(requestDispatcher);

		helloWorldServlet.doPost(request, response);

		verify(request).setAttribute("user", "World");
		verify(requestDispatcher).forward(request, response);
	}

	@Test
	public void doPostWithName() throws Exception {
		when(request.getParameter("name"))
			.thenReturn("A Name");
		when(request.getRequestDispatcher("response.jsp")).
			thenReturn(requestDispatcher);

		helloWorldServlet.doPost(request, response);

		verify(request).setAttribute("user", "A Name");
		verify(requestDispatcher).forward(request, response);
	}
}