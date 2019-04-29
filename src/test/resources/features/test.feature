Feature: Test initial setup

    @only
    Scenario: Open Google Page
        Given I am on Google Page
        When I submit "TestNG"
        Then I will get a results page for "TestNG"
