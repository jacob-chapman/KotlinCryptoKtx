# Sandbox
Android sample app with Arch Components and Kotlin Coroutine Support. Using a simple api (Nomics Api: https://nomics.com/) wanted to use build a sandbox Android application that allowed me to test out new features and libraries.

Application is split into two main features as of 6/5/19.
- Cryptocurrency prices with minimal dashboard navigation
- News Article list

These features are implemented using modular desing with clean architecture. However, they are down slightly different to compare two current implementations of this app design approach. 

#### Prices Feature
The price feature is implemented using different modules for each layer of Clean Architecture. 

- app (ui)
- domain (business logic)
- data (api/database)

The app layer hosts the fragments, adapters, layout resources and view models for a feature. This could also be split out into a smaller android library for the Prices features ui only. However, as it currently stands this is lumped into the main app module.
The domain layer hosts the use cases, repository interfaces, and the entity models.
The data layer is where the repository implementation is defined and where the data logic is placed. The repo can call either a local or remote data source here.

#### News Feature
The news feature is implemented using a single module to host the entire Clean Architecture structure. Inside that module are different packages to define which part we are working in (data, domain, ui). Each section is similar to how the price feature is set up in different modules. 
Still allows the ability to test each layer individually if desired by setting up tests in that android library. Can isolate the tests to that specific module. Separates features into vertical slices.

##### Sources:
  - https://medium.com/androiddevelopers/a-patchwork-plaid-monolith-to-modularized-app-60235d9f212e
  - https://medium.com/androiddevelopers/dependency-injection-in-a-multi-module-project-1a09511c14b7
  - https://proandroiddev.com/intro-to-app-modularization-42411e4c421e

# Main Goals
- Kotlin Coroutines
- Android Architecture Components
  - Room
  - Navigation
  - LiveData
  - ViewModel
  - Paging (todo)
  - Lifecycle
  - Data Binding
  - Work Manager (todo)
- Mvvm with Clean Architecture 
  - Develop the app according to this structure: https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
- CircleCI
  - Provide a way for continuous integration using CircleCI

# Dagger 
###### 6/5/19
Structure is currently two components: Application Component which contains all the dependencies for 

# Notes

- Used Koin for simplification of dependency injection. 
  - Can convert to Dagger at a later date, but wanted to reduce time of setup with a smaller project like this to focus on other aspects of Android Development.
