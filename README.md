<div id="top"></div>

<div align="center">
  <img src="/resources/Logo.png" alt="Logo" width="75%">
  <h3 align="center">Noroff Assignment 6</h3>
  <p align="center">
    Data access and display
    <br />
    <a href="https://noroff-assignment7.herokuapp.com/swagger-ui/index.html/">View Demo</a>
  </p>
</div>

# Table of Contents
1. [About the Project](#about-the-project)
2. [Install](#install)
3. [Usage](#usage)
4. [Build & Deploy](#build--deploy)
5. [Demo](#demo)
7. [Maintainers](#maintainers)
8. [Contributing](#contributing)
9. [Conventions](#conventions)
10. [License](#license)
11. [Contact](#contact)

# About the project
## Data access with JDBC and Thymeleaf
Build a Spring Boot application in Java. Follow the guidelines given below, feel free to expand on the functionality. It 
must meet the minimum requirements prescribed.
```
NOTE: This assignment is to be completed in groups of 2.
In addition to this, you should complete this assignment using Pair Programming.
```

<ol>
  <li>
    <p>Set up the development environment.</p>
    <p>Make sure you have installed at least the following tools:</p>
    <ul>
      <li>
        IntelliJ with Java 17.
      </li>
    </ul>
  </li>
  <li>
    <p>Use plain Java to create a Spring Boot Web API, and use Thymeleaf to create a view with the following minimum requirements (See Appendix A-B for details):</p>
    <ol>
      <li>Access the Chinook SQL Lite database through JDBC. This should be done according to Appendix A.</li>
      <li>A Thymeleaf view to show database data according to Appendix B.</li>
      <li>This should all be in one project.</li>
      <li>The application must be published as a Docker container on Heroku.</li>
    </ol>
  </li>
</ol>

<a href="resources/Assignment%202_Java_Data%20access%20and%20display.pdf">Assignment 2_Java_Data access and display.pdf</a>

<p align="right">(<a href="#top">back to top</a>)</p>

# Install
```
git clone https://github.com/Cusatelli/Noroff-Assignment_6.git
cd Noroff-Assignment_6
```

<p align="right">(<a href="#top">back to top</a>)</p>

# Usage
```
Open IntelliJ IDEA navigate to /src/main/java/com/access_and_expose/noroffassignment_6/Main.java
Rigth click & press 'Run Noroff-Assignment_6Application.java'
```

<p align="right">(<a href="#top">back to top</a>)</p>

# Build & Deploy
Navigate to `cd Noroff-Assignment_6` & run the following commands to build & deploy the application:
## Build
```
./gradlew build
docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-docker .
```

## Deploy
```
heroku login
heroku container:login

heroku create

heroku container:push web
heroku container:release web
```

<p align="right">(<a href="#top">back to top</a>)</p>

# Demo
Heroku App: <a href="https://noroff-assignment7.herokuapp.com/swagger-ui/index.html">https://noroff-assignment7.herokuapp.com/swagger-ui/index.html</a>

<p align="right">(<a href="#top">back to top</a>)</p>

# Maintainers
[@Cusatelli](https://github.com/Cusatelli)  
[@Haruberi](https://github.com/Haruberi)

<p align="right">(<a href="#top">back to top</a>)</p>

# Contributing
[@Cusatelli](https://github.com/Cusatelli)  
[@Haruberi](https://github.com/Haruberi)

<p align="right">(<a href="#top">back to top</a>)</p>

# Conventions

Format: `<type>(<scope>): <subject>`

`<scope>` is optional

- `feat`: (new feature for the user, not a new feature for build script)
- `fix`: (bug fix for the user, not a fix to a build script)
- `docs`: (changes to the documentation)
- `style`: (formatting, missing semi colons, etc; no production code change)
- `refactor`: (refactoring production code, eg. renaming a variable)
- `test`: (adding missing tests, refactoring tests; no production code change)
- `chore`: (updating grunt tasks etc; no production code change)

Read more: [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) v1.0.0

<p align="right">(<a href="#top">back to top</a>)</p>

# License
No active license.

<p align="right">(<a href="#top">back to top</a>)</p>

# Contact
Cusatelli: <a href="mailto:github.cusatelli@gmail.com">github.cusatelli@gmail.com</a>  
Haruberi: <a href="https://github.com/Haruberi">github.com/Haruberi</a>

<p align="right">(<a href="#top">back to top</a>)</p>
