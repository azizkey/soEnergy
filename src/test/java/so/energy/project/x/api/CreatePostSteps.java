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

public class CreatePostSteps {
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";
    private RequestSpecification request;
    private Response response;
    private Post post;

    @Given("User with id {int} wants to create a new post")
    public void userWithIdWantsToCreateANewPost(final int userId) {
        RestAssured.baseURI = "%s/posts".formatted(CreatePostSteps.BASE_URI);
        this.post = new Post();
        this.post.setUserId(userId);
        this.request = RestAssured.given();
    }

    @When("User provides title with {string}")
    public void userProvidesTitleWith(final String title) {
        this.post.setTitle(title);
    }

    @And("User provides body with {string}")
    public void userProvidesBodyWith(final String body) {
        this.post.setBody(body);
    }

    @Then("New post with id {int} should be created")
    public void newPostWithIdShouldBeCreated(final int postId) {
        this.request
                .contentType(ContentType.JSON)
                .body(this.post)
                .post().then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("id", equalTo(postId));
    }
}

