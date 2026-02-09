Feature: check create data
Scenario Outline: as a user, I create new data in API CRUD function test
Given set up post url & find json file
When send request and get response for post
Then validate api basic validation
And validate json key "<Key>"
And validate json value "<Value>"

Examples:

|Key |Value |
|employeeId |EMP001 |
|name |David |
|salary |8000 |
|skills |Selenium |
|data.name |John |
|projects.projectName |Banking App |
|projects. projectId |101 |
