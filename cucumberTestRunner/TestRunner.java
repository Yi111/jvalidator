package cucumberTestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",glue={"steptest"},tags = {"@demo1222"},monochrome = true,format = { "pretty","html:cucumber-html-reports",
        "json:cucumber-html-reports/cucumber.json","junit:cucumber-html-reports/cucumber.xml"},dryRun = false
        )
public class TestRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
