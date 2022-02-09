package testScripts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllySampleTest {
	WebDriver driver;

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		//driver.get("https://demo.opencart.com/");
		//driver.get("https://www.ea.com/sports");
		//driver.get("https://www.selenium.dev/");
		driver.get("https://www.amazon.com/ref=nav_logo");
	}

	@Test(enabled = false)
	public void sampleOneTest() {
		String reportFile = "AllyTestReport";
		AxeBuilder builder = new AxeBuilder();
		Results results = builder.analyze(driver);
		List<Rule> violations = results.getViolations();
		if(violations.size() == 0) {
			Assert.assertTrue(true, "No violations found");
		}else {
		System.out.println("Number of Violations :" + violations.size());
		AxeReporter.writeResultsToJsonFile(reportFile, results);
		AxeReporter.writeResultsToTextFile(reportFile, violations);
		Assert.assertEquals(violations.size(), 5, violations.size() + " Violations are found." );
		}

	}
	
	@Test(enabled = false)
	public void testWithSelectors() {
		String reportFile = "TestWithSelector";
		List<String> selectors = new ArrayList<String>();
		selectors.add("title");
		AxeBuilder builder = new AxeBuilder();
		builder.include(selectors);
		Results results = builder.analyze(driver);
		List<Rule> violations = results.getViolations();
		//Assert.assertTrue(true, "No violations found");
		System.out.println("Number of Violations with selectors :" + violations.size());
		AxeReporter.writeResultsToJsonFile(reportFile, results);
		AxeReporter.writeResultsToTextFile(reportFile, violations);
		Assert.assertEquals(violations.size(), 5, violations.size() + " Violations are found." );
		
	}
	
	@Test
	public void testWebElement() {
		String reportFile = "TestWithWeb";
		List<String> selectors = new ArrayList<String>();
		selectors.add("title");
		AxeBuilder builder = new AxeBuilder();
		//Results results = builder.analyze(driver, driver.findElement(By.tagName("div")));
		Results results = builder.analyze(driver, driver.findElement(By.tagName("div")));
		List<Rule> violations = results.getViolations();
		System.out.println("Number of Violations with in tag name :" + violations.size());
		AxeReporter.writeResultsToJsonFile(reportFile, results);
		AxeReporter.writeResultsToTextFile(reportFile, violations);
		//Assert.assertEquals(violations.size(), 5, violations.size() + " Violations are found." );
		
	}
}
