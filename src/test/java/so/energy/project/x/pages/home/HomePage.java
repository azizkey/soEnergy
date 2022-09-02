package so.energy.project.x.pages.home;

import org.apache.commons.text.WordUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import so.energy.project.x.pages.Page;

import java.util.List;

public class HomePage extends Page {
    private final String URL = System.getProperty("test.url", "http://automationpractice.com/index.php");

    @FindBy(css = "#block_top_menu")
    private WebElement menu;

    @FindBy(css = "i.icon-th-list")
    private WebElement listIcon;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private WebElement checkoutButton;

    @FindBy(css = "#email")
    private WebElement username;

    @FindBy(css = "#passwd")
    private WebElement password;

    @FindBy(css = "#SubmitLogin")
    private WebElement loginButton;

    HomePage() {
        PageFactory.initElements(this.driver, this);
    }

    public void gotoHome() {
        this.driver.get(this.URL);
        this.wait.forLoading(5);
    }

    public void hover(final String tab, final String dress) {
        final WebElement submenu = menu.findElement(By.xpath("(//a[@title='%s']/..)[2]".formatted(WordUtils.capitalizeFully(tab))));
        Actions action = new Actions(this.driver);
        action.moveToElement(submenu).perform();

        final WebElement selectedMenu = submenu.findElement(By.xpath("(//a[@title='%s'])[2]".formatted(WordUtils.capitalizeFully(dress))));
        selectedMenu.click();
    }

    public void changeToListView() {
        this.listIcon.click();
    }

    public void addToCart(final int product) {
        final WebElement cartButton = this.driver.findElement(By.xpath("//a[@data-id-product=%d]".formatted(product)));
        cartButton.click();
    }

    public void proceedToCheckout() {
        this.checkoutButton.click();
    }

    public void increaseQuantityAndContinue(final int quantity) {
        final WebElement addButton = this.driver.findElement(By.xpath("//a[@title='Add']"));
        final WebElement nextButton = this.driver.findElement(By.xpath("(//a[@title='Proceed to checkout'])[2]"));

        for (int i = 1; i < quantity; i++) addButton.click();

        nextButton.click();
    }

    public void login(final String username, final String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        loginButton.click();
    }

    public void confirmAddresses(final String address) {
        List<WebElement> elements = this.driver.findElements(By.cssSelector("li.address_address1"));

        for (WebElement element : elements) {
            Assert.assertEquals(address, element.getText());
        }

        WebElement next = this.driver.findElement(By.xpath("//button[@name='processAddress']"));
        next.click();
    }

    public void checkout() {
        WebElement acceptTermsCheck = this.driver.findElement(By.cssSelector("#cgv"));
        acceptTermsCheck.click();

        WebElement next = this.driver.findElement(By.xpath("//button[@name='processCarrier']"));
        next.click();
    }

    public void displayCorrectInfo(final String name, final double price) {
        WebElement productName = this.driver.findElement(By.cssSelector("p.product-name > a"));
        Assert.assertEquals(name, productName.getText());

        WebElement productPrice = this.driver.findElement(By.cssSelector("#total_price"));
        Assert.assertEquals("$%.2f".formatted(price), productPrice.getText());

        System.out.println("productName = " + productName.getText());
        System.out.println("productPrice = " + productPrice.getText());
    }
}
