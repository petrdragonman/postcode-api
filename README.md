# Postcode_api

{add test badges here, all projects you build from here on out will have tests, therefore you should have github workflow badges at the top of your repositories: [Github Workflow Badges](https://docs.github.com/en/actions/monitoring-and-troubleshooting-workflows/adding-a-workflow-status-badge)}

## Demo & Snippets

```bash
{
  "Status": "SUCCESS",
  "Locations": [
    {
      "Name": "FOREST LODGE",
      "Postcode": "2037",
      "State": "NEW SOUTH WALES",
      "StateShort": "NSW"
    },
    {
      "Name": "GLEBE",
      "Postcode": "2037",
      "State": "NEW SOUTH WALES",
      "StateShort": "NSW"
    }
  ]
}
```

---

## Requirements / Purpose

- MVP - back-end only
- Create an API in Java that allows mobile clients to retrieve and add suburb and postcode combinations. You do not have to write the mobile app!
- Implement:
  - An API that allows mobile clients to retrieve the suburb information by postcode.
  - An API that allows mobile clients to retrieve a postcode given a suburb name
  - A secured API to add new suburb and postcode combinations (you'll have to work out how this should work)
  - Some form of persistence (a database)
  - Testing for controller / service layers

---

## Build Steps

- how to build / run project

```bash
spring.application.name=postcode_api
spring.datasource.url=jdbc:mysql://localhost:3306/postcodes
spring.datasource.username=root
# spring.datasource.password=MyPass
spring.jpa.properties.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.profiles.active=dev
```

---

## Design Goals / Approach

- Version control using GitHub
- Project management using GitHub Issues and Linear
- API-First approach using OpenAPI and SwaggerHub
- Test-driven development TDO: Red, Green, Refactor development cycle
- Continuous integration/ continuous deployment using GitHub Actions

- I chose to use ModelMapper in this project.
- I chose to use JavaFaker for data seeding
- I am using Abstract class as a BaseEntity class holding ID, createdAt and updatedAt, which is then inherited by the child
- For testing I chose RestAssure for end to end testing and Mockito for unit testing

---

## Features

- CRUD

---

## Known issues

---

## Future Goals

---

## What did you struggle with?

---

## Licensing Details

- Public, free
