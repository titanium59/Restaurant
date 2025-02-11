# Restaurant App

## Overview
This is a Jetpack Compose-based Android application that fetches and displays meal and drink categories from TheMealDB and TheCocktailDB APIs. Users can toggle between meals and drinks using a switch and navigate to detailed views for each category.

## Features Implemented
- **Jetpack Compose UI:** The app is fully built using Jetpack Compose for a modern UI experience.
- **Retrofit for Networking:** API calls are made using Retrofit to fetch meal and drink categories.
- **ViewModel & State Management:** The app follows the MVVM architecture using `ViewModel` and `mutableStateOf` for state management.
- **Simultaneous API Calls:** Uses Kotlin Coroutines (`async` & `await`) to fetch meal and drink categories concurrently.
- **LazyColumn for Lists:** Efficiently renders lists of meals and drinks using `LazyColumn`.
- **Shimmer Effect for Loading:** A shimmer effect is displayed while fetching data to enhance the user experience.
- **Error Handling:** Displays an error message if API calls fail.
- **Navigation Support:** Clicking on a meal or drink navigates to its detailed view.

## Challenges Faced
- **Sequential API Calls:** Initially, meal and drink categories were fetched sequentially, increasing load time. This was optimized by using `async` and `await`.
- **State Management Issues:** Ensuring state updates correctly when switching between meals and drinks required careful handling of `mutableStateOf`.
- **Shimmer Implementation:** Integrating the Accompanist Shimmer library and ensuring a smooth transition from loading state to displaying data.
- **UI Layout Adjustments:** Maintaining proper image aspect ratios and handling different screen sizes effectively.

## Assumptions Made
- The API responses are always in the expected format (`categories` for meals and `drinks` for drinks).
- Each meal and drink category has a unique image URL and name.
- Network calls succeed most of the time, with occasional failures handled by displaying an error message.

## How to Run the Project
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/restaurant-app.git
   ```
2. Open the project in Android Studio.
3. Ensure you have an active internet connection.
4. Run the app on an emulator or a physical device.

## Future Enhancements
- **Detailed Screens:** Implement detail views for meals and drinks.
- **Offline Caching:** Use Room Database to cache API responses for offline access.
- **Search & Filters:** Add a search bar and filters to refine meal and drink categories.
- **Dark Mode Support:** Implement theme switching for better accessibility.

---
This project demonstrates expertise in Jetpack Compose, Retrofit, Coroutines, and state management while building a practical restaurant application. 🚀

