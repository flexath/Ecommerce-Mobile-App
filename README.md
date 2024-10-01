# Ecommerce-Mobile-App

App Release Version Download Link: https://drive.google.com/file/d/10-3XuR6AaU0jo0LBm2L3PuDRWuCB5E5S/view?usp=sharing

### Ecommerce Mobile App is an Android application built using Jetpack Compose. 

#### Components Used
### 1. Clean Architecture
The app follows Clean Architecture, which separates concerns into different layers for better maintainability, scalability, and testability.
##### Layers:
    Data Layer: Handles the data operations, including network requests and local database management.
    Domain Layer: Contains the business logic and use cases that define how the data is used.
    Presentation Layer: Manages the UI and user interactions using Jetpack Compose.

### 2. Dagger Hilt for Dependency Injection
Dagger Hilt is used to manage dependency injection across the app, simplifying the setup and management of dependencies like ViewModels, Repositories, and Use Cases.

### 3. Retrofit for Remote API Calls
The app utilizes Retrofit to make API requests to fetch ecommerce related data from a remote server. Retrofit simplifies network operations and integrates well with Kotlin Coroutines for asynchronous data fetching.

### 4. Kotlin Coroutine Flows for Reactive Data Handling
Kotlin Coroutine Flows are used to manage reactive data streams, ensuring that the UI is updated in real-time when data changes.
