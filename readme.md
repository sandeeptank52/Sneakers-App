## Sneaker Store App

The Sneaker Store App is an Android native mobile application developed using Kotlin. It allows users to browse and view details of the top 100 sneakers.
Users can add sneakers to a cart for checkout and remove items from the cart. The app features a grid view of sneakers, a sneaker details page, and a checkout page.

### Features

- Display a grid view of the top 100 sneakers, with each item showing the sneaker's image, price, and name.
- Tap on a sneaker item to view its details on a separate page.
- Sneaker details page displays the title, name, image, brand, year of release, and price of the selected sneaker.
- Add selected sneakers to the cart by tapping the "Add to Cart" button on the details page.
- View the cart page to see a list of added sneakers, including their images and prices.
- Remove items from the cart by clicking on them.
- The total price of all sneakers in the cart is displayed on the checkout page.

### Technologies Used

- Programming Language: Kotlin
- Android Navigation: Used Android Navigation for seamless navigation between the grid view and sneaker details page.
- Dependency Injection: Utilized Hilt for dependency injection, providing a more modular and maintainable codebase.
- Material Components: Incorporated Material Design components for consistent and appealing UI.
- Image Loading: Utilized Glide for efficient and smooth loading of sneaker images.

### Assumptions and Decisions Made

- Data Source: A DummyServer within the app provides the information about the top 100 sneakers. No external API or data source is used.
- Search Feature: Basic text search functionality is implemented to search for sneakers.
- Validation and Error Handling: Currently, there is no validation or error handling implemented when adding sneakers to the cart. Duplicate items can be added.
- Cart Management: The app uses a simple click action to remove items from the cart. No confirmation dialogs or animations are involved.
- State Management: No state management libraries or patterns are used for managing the cart and its items.
- UI/UX Design: The app follows Android design guidelines for consistent and intuitive user experience.
- Additional Functionality: Apart from the mentioned features, no additional functionality or features are implemented in the app.

### Setup and Installation

1. Clone the repository from [GitHub](https://github.com/sandeeptank52/Sneakers-App.git).
2. Open the project in Android Studio.
3. Build and run the project on an Android emulator or physical device.

## Project Structure

The project follows the MVVM (Model-View-ViewModel) architecture and utilizes the recommended Single Activity structure as recommended by Android.

The project structure is organized as follows:

- **data**: This directory contains the data-related components of the app.
    - **models**: This directory holds the data models or data classes used within the app.
    - **remote**: This directory contains code related to fetching data from remote sources, such as API clients or data service classes.

- **di**: This directory holds the dependency injection components or modules for the app. It may include files for configuring and providing dependencies throughout the app.

- **domain**: This directory represents the domain layer of the app.
    - **repo**: This directory includes repository classes that interact with the data layer and provide the necessary data to the app.

- **ui**: This directory contains the user interface components of the app.
    - **cart**: This directory includes files related to the cart screen or functionality.
    - **home**: This directory holds files related to the home or main screen of the app.
    - **product_detail**: This directory includes files related to the product detail screen or functionality.

- **utils**: This directory contains utility files or helper classes that provide common functionality or reusable code snippets.


### Future Enhancements

- Add Pagination for smooth performance.
- Implement validation handling when adding sneakers to the cart.
- Enhance the search feature by adding filtering and sorting options.
- Implement a backend API or data source to fetch real-time information about sneakers.
- Improve the user interface by incorporating popular styling frameworks or libraries.

