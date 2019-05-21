# KotlinCryptoKtx
Android sample app with Arch Components and Kotlin Coroutine Support. Using a simple api (Nomics Api: https://nomics.com/) wanted to use build a sandbox Android application that allowed me to test out new features and libraries.
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
  - Provide a way for continous integration using CircleCI


# Notes

- Used Koin for simplification of dependency injection. 
  - Can convert to Dagger at a later date, but wanted to reduce time of setup with a smaller project like this to focus on other aspects of Android Development.
