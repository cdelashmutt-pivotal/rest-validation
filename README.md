# Rest Validation
A simple example to show the minimum needed to use JSR 303 Bean Validation in a Spring Boot RestController.

## Build the Application
Clone this repo locally, and then use your favorite, Gradle enabled IDE or execute the following from the command line:
```
./gradlew build
```

## Run the Application
Launch the io.pivotal.pa.restvalidation.RestValidationApplication class from your IDE, or execute the following from the command line:
```
./gradlew bootRun
```

## Tests
Review the unit tests to see how the validation failure is reflected back to the client.  You can run the tests in your IDE, or execute them from the command line and view the requests and responses with the following command:
```
./gradlew clean test -i
```