package com.examples.helloservlet.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class HelloNameServiceImplTest {

	private HelloNameService helloNameService;

	@Before
	public void setup() {
		helloNameService = new HelloNameServiceImpl();
	}

	@Test
	public void testProcessNameNull() {
		assertThat(helloNameService.processName(null))
			.isEqualTo("World");
	}

	@Test
	public void testProcessNameEmpty() {
		assertThat(helloNameService.processName(""))
			.isEqualTo("World");
	}

	@Test
	public void testProcessNameNotEmpty() {
		assertThat(helloNameService.processName("A name"))
			.isEqualTo("A name");
	}

}
