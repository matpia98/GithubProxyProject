# GitHubProxyProject

The GitHub Proxy Project is a Spring Boot-based application designed to serve as a proxy between GitHub's API and the end-user. It allows users to retrieve repositories and their respective branches from GitHub in a simplified manner.

## Description

This project is aimed at developers who need to interact with the GitHub API without dealing with the complexity of direct API calls. It simplifies the process by providing an easy-to-use RESTful interface. Users can get information about repositories, branches, and commits for a given GitHub user.

## Getting Started

To get this project up and running on your local machine for development and testing, follow these instructions.

### Prerequisites

Before you begin, ensure you have the following installed:
- Java JDK 17 or newer
- Maven
- An IDE of your choice (e.g., IntelliJ IDEA, Eclipse)

### Installing

Clone the repository to your local machine:

```bash
git clone https://github.com/yourusername/githubproxyproject.git
```

Navigate to the project directory:

```bash
cd githubproxyproject
```

Compile and package the application using Maven:

```bash
mvn clean install
```

Run the application:

```bash
java -jar target/githubproxyproject-0.0.1-SNAPSHOT.jar
```

The application should now be running on <http://localhost:8080>.

## Usage

To retrieve all repositories from a user, send a GET request to the endpoint with the username:

```
GET /{username}
```

For example, using curl:

```
curl -X GET http://localhost:8080/{username} -H "Accept: application/json"
```

Replace `{username}` with the actual GitHub username.

## Contributing

If you'd like to contribute, please fork the repository and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks!

## License

This project is open-sourced under the MIT License. See the [LICENSE](LICENSE.md) file for details.

## Contact

- Developer: [Mateusz Piasecki](https://github.com/matpia98))
- Project Link: [githubproxyproject](https://github.com/matpia98/githubproxyproject)

We welcome your questions and contributions!
