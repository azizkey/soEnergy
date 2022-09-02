package so.energy.project.x;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/api"},
        plugin = {"pretty",
                "json:target/cucumber_json_reports/search.json",
                "html:target/api.html"},
        glue = {"so.energy.project.x.infrastructure",
                "so.energy.project.x.api"},
        tags = "@api"
)
public class APIValidationTest {
}
