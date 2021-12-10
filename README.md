# Buddies.com

Engineering degree thesis project **V2**. Create an account to find your dream place to live, without spending all your
money. Find buddies that will match your personality, to split the cost of living. More money in the pocket, more
friends in life. Win-win.

## V1 vs V2

First iteration was finished by November 2020 and the development time was limited - I have spent 4 (intense...) weeks
developing API and an Angular Front-End app (there will be link once I decide to redo that as well). Thanks to short
time period, some of the decisions in that project were, lets say, *suboptimal*. Also, due to the legal reasons, as the
thesis and everything developed in the process of writing is co-owned by the University, V2 will be a way of cutting
those limitations off, thus allowing for a public git repository, so I can show my work.

(Planned) key differences:

- Dockerize it! ✓
- Hexagon pattern, DDD approach
- Gradle instead of Maven ✓
- Unit, Integration and Functional Tests with Groovy
- Dividing context for running tests, to optimise the test phase times
- Javadocs, readme files
- OpenAPI ✓
- Implement CI/CD pipeline
- QueryDSL, in-memory testing database, renovate-bot, and many more!

## Installation

```bash
./gradlew clean build
```

```bash
./gradlew docker-compose up
```

1.

```bash
./gradlew docker
```

```bash
./gradlew dockerRun
```

**OR**

2. run BuddiesApiApplication as an SpringBoot app

## Usage

To see the Swagger contract, either:

1.

```bash
./gradlew clean generateOpenApiDocs
```

2. open generated file at:

```xpath
build/OpenAPI/buddiesAPI.json
```

OR

1) Run the application
2) Open in the browser:

   [/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

   or

   [/v3/api-docs](http://localhost:8080/v3/api-docs/)

## Contributing

Contribution is appreciated, but first I would like to fully import all the functionalities of the first version. This
will be removed if I consider my work finished - don't have much time, so probably it will be my project to
procrastinate on.

## License

[MIT](https://choosealicense.com/licenses/mit/)