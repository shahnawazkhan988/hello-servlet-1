package com.examples.helloservlet;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HelloWorldServletE2E {

	private static final String BASE_URL =
		"http://localhost:" +
		Integer.parseInt(System.getProperty("tomcat.http.port", "8080")) +
		"/hello-servlet";

	private ChromeDriver driver;

	@BeforeClass
	public static void setupClass() {
		Logger.
			getLogger(HelloWorldServletE2E.class.toString()).
			info("Using URL: " + BASE_URL);
		// setup Chrome Driver
		WebDriverManager.chromedriver().setup();
	}

	@Before
	public void setupTest() {
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void sayHelloLink() throws Exception {
		driver.get(BASE_URL);

		driver.findElement(By.linkText("Hello")).click();

		assertThat(driver.findElement(By.tagName("body")).getText())
			.isEqualTo("Served at: /hello-servlet");
	}

	@Test
	public void sayHelloButton() throws Exception {
		driver.get(BASE_URL);

		driver.findElement(By.id("say-hello-text-input"))
			.sendKeys("World");
		driver.findElement(By.id("say-hello-button")).click();

		assertThat(driver.getTitle())
			.isEqualTo("Hello Page");
		assertThat(driver.findElement(By.tagName("h2")).getText())
			.isEqualTo("Hello, World!");
	}
}
