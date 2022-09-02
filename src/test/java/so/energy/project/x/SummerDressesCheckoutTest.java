package so.energy.project.x;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/gui"},
        plugin = {"pretty",
                "json:target/cucumber_json_reports/search.json",
                "html:target/summer-dresses-checkout.html"},
        glue = {"so.energy.project.x.infrastructure",
                "so.energy.project.x.pages.home"},
        tags = "@gui"
)
public class SummerDressesCheckoutTest {
}
