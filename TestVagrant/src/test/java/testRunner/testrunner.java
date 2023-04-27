package testRunner;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Eclipse\\TestVagrant\\src\\test\\resources\\Feature\\Testvagarant.feature", glue = {
		"StepDef" }, monochrome = true, stepNotifications = true,

		plugin = { "pretty", "html:target/HtmlReports.html", "json:target/jsonReports/report.json",
				"junit:target/junitReports/report.xml", })
public class testrunner {
	
}
