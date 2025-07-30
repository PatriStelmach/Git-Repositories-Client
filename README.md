GitHub Repo Client

A Spring Boot-based application that connects to the GitHub API and retrieves public repository and branch data for a given user. The application is designed to be part of a recruitment project, following clean code principles and professional standards.

Features
--------
- Fetches all public repositories for a given GitHub username.
- Retrieves all branches for each repository along with the latest commit SHA.
- Handles invalid usernames and improper GitHub credentials gracefully.

Technologies Used
-----------------
- Java 21
- Spring Boot 3.5.4
- OpenFeign (for GitHub API communication)
- JUnit 5 Jupiter API  (integration testing)
- Maven

Prerequisites
-------------
- Java 21 installed
- Maven installed

Running the Application
-----------------------
You can run the application in two ways:

1. Downloading Jar file present in "demo" folder
   - to run your jar file type: java -jar demo-GitHub-Client-API.jar in jar direcotry.
2. Compile the code by yourself


Building a JAR file
-------------------
To package the project as a JAR:

    mvn clean package
    or 
    mvn clean install

The JAR will be available in the target/ directory:

    java -jar target/Git-Repositories-Client-0.0.1-SNAPSHOT.jar


Running Tests
-------------
To execute all tests (unit and integration):

    mvn test

If a test hangs or you want debug info:

    mvn test -X

API Endpoint
------------
GET ${localhost}/github/getRepos/{username}

Response:

[
  {
    "repositoryName": "RepositoryName",
    "owner": {
      "login": "Username"
    },
    "branches": [
      {
        "name": "main",
        "commit": {
          "sha": "latestCommitSha"
        }
      }
    ]
  }
}

Errors:
- 404 Not Found – If the user does not exist or has no public repositories.
- 403 / 500 for exceeding GitHub api responses limit 


Notes
-----
- This project is intended as a recruitment assignment.
- Only public repositories and branches are fetched – no authentication for private resources is performed.
- You can change the port of localhost in 'application.properties' file, the default one is 8090.

License
-------
This project is provided for educational/recruitment purposes and is not licensed for commercial use.
