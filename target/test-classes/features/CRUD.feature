Feature: API HTTP CRUD function test
Scenario: As a user, I can get data from my appplication
Given put url
When send get request and response
Then validate data with test cases

Scenario: As a user, I can send or post data from my appplication
Given put post url
When send post request and get response
Then validate data with test cases

Scenario: As a user, I can update or put data from my appplication
Given put update url
When send put request and get response
Then validate put data with test cases

Scenario: As a user, I can remove data from my appplication
Given put delete url
When send delete request and get response
Then validate delete data with test cases