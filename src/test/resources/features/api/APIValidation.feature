Feature: API Validation

  @api
  Scenario: User should be able to get comments belong to a particular post
    Given User wants to get all the comments
    When User provides postId 3
    Then 5 results should be returned

  @api
  Scenario: User should be able to create new posts
    Given User with id 1 wants to create a new post
    When User provides title with "Foo"
    And User provides body with "Bar"
    Then New post with id 101 should be created

  @api
  Scenario: User should be able to update a particular post
    Given User wants to update an existing post with postId 3
    When User provides new body with "Baz"
    Then The post should have the new updated body "Baz"
