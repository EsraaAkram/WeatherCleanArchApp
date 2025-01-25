# CLEAN ARCH (MVVM ANDROID APP):

Clean Architecture by Uncle Bob android app to View current weather for a specific location

-<b>This project follows Clean Architecture principles, offering a modular, scalable, and testable
codebase.
It demonstrates how to create an Android application using modern development practices while
focusing on separation of concerns and maintainability.</b>

https://developer.android.com/topic/architecture

### Architecture Overview:

This application follows the Clean Architecture pattern.
The architecture layers include:

-<b>Presentation Layer</b>: Contains the UI and ViewModels.

-<b>Domain Layer</b>: Handles business logic and use cases.

-<b>Data Layer</b>: Manages data sources (e.g., APIs, databases).


### Technologies and Tools:

-<b>Kotlin</b>: The primary programming language, chosen for its conciseness, expressiveness, and
safety.

-<b>Jetpack Compose</b>: Declarative UI toolkit for building modern Android UIs.

-<b>Hilt</b>: Simplifies dependency injection for Android apps.

-<b>Kotlin Coroutines</b>: For asynchronous programming and thread management.

-<b>Flow</b>: A reactive data stream library for handling real-time updates in the app.

-<b>Retrofit</b>: A networking library for seamless API communication.

-<b>Coil</b>: A lightweight image-loading library for efficient and fast image rendering.

### App Design Goals:

The app has been designed with the following objectives:

    -Performance: Ensure smooth and responsive interactions.

    -Readability: Write code that is clean, consistent, and easy to understand.

    -Maintainability: Make it simple to add new features or fix bugs.

    -Scalability: Enable growth without significant refactoring.

    -Simplicity: Keep the user experience and codebase straightforward.

### API Integration

-<b>The app utilizes the Weather API for retrieving real-time weather data. This API provides
endpoints
for current conditions, hourly forecasts, and extended daily forecasts.</b>

### Layers and Modules

The app is divided into multiple modules to enhance modularity:

    -UI Module: Manages the presentation logic, including Jetpack Compose components.
    -Domain Module: Contains the use cases and business logic.
    -Data Module: Handles network requests using Retrofit .

### Setup and Build

    1-Clone the repository.
    2-Open the project in Android Studio.
    3-Sync Gradle files to download dependencies.
    4-Obtain a Weather API key from Weather API.
    5-Add the API key to your local properties or as an environment variable.
    
    6-Build and run the app.

### Contribution

Contributions are welcome! Feel free to open issues or submit pull requests to enhance the project.