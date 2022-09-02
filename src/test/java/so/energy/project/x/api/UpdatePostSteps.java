package so.energy.project.x.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import so.energy.project.x.api.models.Post;

import static org.hamcrest.CoreMatchers.equalTo;

public class UpdatePostSteps {
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";
    private RequestSpecification request;
    private Response response;
    private Post post;

    @Given("User wants to update an existing post with postId {int}")
    public void userWithIdWantsToUpdateAnExistingPost(final int postId) {
        RestAssured.baseURI = "%s/posts/%d".formatted(UpdatePostSteps.BASE_URI, postId);
        this.request = RestAssured.given();
    }

    @When("User provides new body with {string}")
    public void userProvidesNewBodyWith(final String body) {
        this.post = new Post();
        this.post.setBody(body);
    }

    @Then("The post should have the new updated body {string}")
    public void thePostWithIdShouldHaveTheNewUpdatedBody(final String newBody) {
        this.request
                .contentType(ContentType.JSON)
                .body(this.post)
            .put().then()
                .statusCode(HttpStatus.SC_OK)
                .body("body", equalTo(newBody));
    }
}
