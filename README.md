# Login Form Project

A small responsive login form built with HTML, CSS, and JavaScript. It includes a Docker image served by Nginx and Selenium checks for successful, rejected, and empty login submissions.

## Demo login

- Username: `admin`
- Password: `12345`

This is a classroom demo only. The credentials are checked in browser JavaScript and are visible in the source. Do not use this pattern for a real account or production authentication.

## Run the page locally

Open `index.html` in a browser, or serve the project with any static file server.

## Build and run with Docker

From this folder:

```powershell
docker build -t login-app .
docker run --rm -d -p 8080:80 --name login-container login-app
```

Open <http://localhost:8080>. Stop the container with:

```powershell
docker stop login-container
```

## Run the Selenium tests

Requirements: Java 17 or later, Maven 3.9+, Google Chrome, a running Docker engine, and the app running at `http://localhost:8080`.

```powershell
cd selenium-tests
mvn test
```

The tests run Chrome in headless mode. Selenium Manager downloads a compatible driver on first run. To test another URL:

```powershell
mvn test "-Dlogin.app.url=http://localhost:8080"
```

## Project files

- `index.html` — responsive login page and client-side validation.
- `Dockerfile` — packages the page behind Nginx.
- `selenium-tests/` — Maven project with three Selenium/JUnit tests.
