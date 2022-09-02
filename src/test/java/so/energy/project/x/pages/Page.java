package so.energy.project.x.pages;

import org.openqa.selenium.WebDriver;
import so.energy.project.x.infrastructure.Hooks;
import so.energy.project.x.infrastructure.Wait;

public abstract class Page {
    protected final WebDriver driver;
    protected Wait wait;

    public Page() {
        this.driver = new Hooks().get();
        this.wait = new Wait(this.driver);
    }
}
