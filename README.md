# SO Energy Assignment

Attached [assignment](Senior_Automation_QA_Test.pdf) contains three-part exercise:

1. First part is to test a [Web Application](http://automationpractice.com/index.php) using `Web Driver`
2. Second part is to test an [API](https://jsonplaceholder.typicode.com/) using `RestAssured`
3. Third part is to give a perspective around how an ideal `CI/CD` pipeline should look like

Within this folder, you can find answers to all these questions. `Part1` and `Part2` will be in the 
form of code base, and `Part3` will be a commentary in this very document.

## 1. Setup

To setup, all you have to do is to download the `zip` file provided with this document and extract it to
a location where you have `Java` and `Maven` configured. Once you have extracted all the files, run below
command to see solutions in action:

```
$ mvn -Dbrowser=chrome-headless test
```

You can also allow any `CI/CD` runner to use above one-liner to allow all the tests to run within Maven lifecycle.

## 2. Project Structure

### Layout

Project codebase has classical maven template folder layout in the form of `src/main/java` and `src/test/java`. 
All of my solutions will be under `src/test/java` and `src/test/resources`. I named the assignment project as `Project X`, hence the package
structure will be under `so.energy.project.x`.

Main test runners are configured with the relevant scenario names `Summer Dresses Checkout` and `API Validation`.

`Cucumber` feature files are located under `src/test/resources` folder. I categorised them under `api` and `gui` folders.

### Foundation/Framework

Project foundation/framework is under `so.energy.project.x.infrastructure` package. I created a `Hooks` class to intercept
`JUnit` runners lifecycles to properly inject the `WebDriver` and allow screenshot logic to capture failing test screenshots.
Also `Wait` helper class allows any test file to use time-consuming or async tasks to be properly handled.

### Page Object Model

Page models are under `so.energy.project.x.pages` package. I have an abstract base page class to consolidate common logic
and respective page models in their respective folders. This allows test project to nicely scale. Also relevant `Cucumber`
step definitions are in the same page folder.

### API Testing

I intentionally put API testing in the same `Maven` project for simplicity reasons. In normal setup, I'd have created a 
new maven project for that one.

## 3. CI/CD Ideal Setup

To be honest, this is an area I'm not super comfortable as in almost all projects I have worked with, there is a 
dedicated `DevOps` who handles these things.

Typically we have something similar to below diagram in ASCII:


┌───────────────────────────┐ ┌──────────────────────────────────┐ ┌─────────────────────────────────────────────┐ ┌─────────────────────────────┐ ┌────────────────────────┐
│                           │ │                                  │ │                                             │ │                             │ │                        │
│   VCS Download / Build    │ │   Unit Tests / Static Analysis   │ │  Deploy to Test / Run Selenium & API Tests  │ │  Deploy to UAT / Pre-prod   │ │  Deploy to Production  │
│                           │ │                                  │ │                                             │ │                             │ │                        │
└───────────────────────────┘ └──────────────────────────────────┘ └─────────────────────────────────────────────┘ └─────────────────────────────┘ └────────────────────────┘

Basically, in each stage:

**VCS Download / Build:** This is where the code base is being pulled from Git and turned into a binary

**Unit Tests / Static Analysis:** This is where developer tests, static analysis and dev bug discovery happens

**Deploy to Test / Run Selenium & API Tests:** This stage where QA/SDET gets involved and their tests run and generates reports

**Deploy to UAT / Pre-prod:** This stage we open for UAT tests with Business stakeholders

**Deploy to Production:** This is where we deploy to production if all goes well.