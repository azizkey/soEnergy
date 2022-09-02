package so.energy.project.x.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import static org.hamcrest.CoreMatchers.equalTo;

public class GetCommentSteps {
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";
    private RequestSpecification request;
    private Response response;

    @Given("User wants to get all the comments")
    public void userWantsToGetAllTheCommentsWithoutAuthorisation() {
        RestAssured.baseURI = "%s/comments".formatted(GetCommentSteps.BASE_URI);
        this.request = RestAssured.given();
    }

    @When("User provides postId {int}")
    public void userWantsToSeeTheCommentsBelongToPostId(final int post) {
        this.response = this.request.when().param("postId", post).contentType(ContentType.JSON).get();
    }

    @Then("{int} results should be returned")
    public void resultsShouldBeReturned(final int numberOfResults) {
        this.response.then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", equalTo(numberOfResults));
    }
}
