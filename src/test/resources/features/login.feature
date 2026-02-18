Feature: Customer Login

  As a registered customer
  I want to log into my account
  So that I can access my shopping dashboard

  Background:
    Given the customer is on the login page

  Scenario: Successful login using valid credentials
    When the customer logs in with username and password
    Then the customer should be redirected to the home page
