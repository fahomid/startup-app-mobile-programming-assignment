### This is a startup app project specifically built for a mobile programming course assignment!

# Dynamic View Modifier App

## Overview

This mobile application demonstrates dynamic modification of UI components such as TextView, background colors, and more. The app is developed as part of a Computer Programming assignment to showcase the use of various Android views and listeners.

## Features

### 1. Welcome TextView
- The main TextView displays the text "Welcome!" by default.
- The TextView can be dynamically modified through user interactions.

### 2. Options Spinner
- A Spinner with several options:
    - Change Background
    - Change Text Color
    - Change TextView Text Size
    - Edit TextView
    - Reset Views
- Depending on the selected option, additional input fields (such as a color spinner or an EditText) will be shown.

### 3. Color Spinner
- A secondary spinner for selecting colors, which is shown when the user selects "Change Background" or "Change Text Color" from the options spinner.
- Available colors: Red, Green, Blue, Yellow, Black, White, Gray, Cyan, Magenta.

### 4. EditText
- An input field that appears when the user selects "Change TextView Text Size" or "Edit TextView" from the options spinner.
- Allows the user to enter the desired text size in sp or the new text for the TextView.

### 5. Complete Task Button
- Executes the selected task based on the user’s input.
- Provides feedback through Snackbar messages.

### 6. Reset Views Option
- Resets all UI components to their default states:
    - TextView text: "Welcome!"
    - TextView text size: 24sp
    - TextView text color: Black
    - Background color: White

## UI Components Used
- **ConstraintLayout**: For flexible and complex layouts.
- **ScrollView**: To enable scrolling of the TextView content.
- **TextView**: To display and modify text.
- **Spinner**: For selecting different modification options and colors.
- **EditText**: For user input to change text or text size.
- **Button**: To complete the selected task.

## Instructions
1. **Change Background**: Select a color from the color spinner to change the background color of the entire view.
2. **Change Text Color**: Select a color from the color spinner to change the text color of the TextView.
3. **Change TextView Text Size**: Enter a text size (between 14sp and 60sp) to change the size of the TextView text.
4. **Edit TextView**: Enter new text to change the content of the TextView.
5. **Reset Views**: Reset all modifications to their default states.

## How to Run the App
1. Clone the repository from GitHub.
2. Open the project in Android Studio.
3. Build and run the app on an Android device or emulator.
