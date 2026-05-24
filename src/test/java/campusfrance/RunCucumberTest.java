package campusfrance;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = "cucumber.glue",
        value = "campusfrance.stepdefinitions"
)
@ConfigurationParameter(
        key = "cucumber.plugin",
        value = "pretty, html:target/cucumber-reports/report.html"
)
@ConfigurationParameter(
        key = "cucumber.filter.tags",
        value = "not @ignore"
)
public class RunCucumberTest {
}
