# BMI Calculator App

A simple Android application written in **Kotlin** to calculate the Body Mass Index (BMI). Users can input their weight and height using various units, and the app calculates and displays the BMI along with a personalized message based on BMI classification.

---

## Features

### **1. User Input**
- Users can input their:
  - **Weight**: Supports both **kilograms (kg)** and **pounds (lb)**.
  - **Height**: Supports both **centimeters (cm)** and **inches**.
- Input is automatically converted to the correct units for BMI calculation.

### **2. BMI Calculation**
- Formula used:
  ```text
  BMI = Weight (kg) / Height (m)^2
The app converts:

- Weight from pounds to kilograms, if necessary.
- Height from centimeters or inches to meters.

### 3. BMI Classification
The app categorizes BMI results into the following ranges:

- Underweight: BMI < 18.5
- Normal weight: 18.5 ≤ BMI ≤ 24.9
- Overweight: 25 ≤ BMI ≤ 29.9
- Obesity: BMI ≥ 30
- 
Each classification is displayed with a color-coded message:

- Red: For extreme cases (underweight or obesity).
- Green: For normal weight.
- Yellow: For overweight or invalid inputs.

### 4. User Interface

- Dropdowns (Spinners) for selecting weight and height units.
- Calculate Button to trigger the BMI calculation.
- Dynamic result message displayed with color feedback.

## Code Highlights

MainActivity.kt
onCreate:

- Initializes UI components, including Button, TextView, and Spinners.
- Sets up listeners for unit selection and BMI calculation.

## Input Validation:

- Checks for valid numeric input in height and weight fields.
- Displays an error message if inputs are invalid.

## Unit Conversion:

- Converts weight to kilograms and height to meters when necessary.
  
## BMI Calculation and Classification:

- Computes BMI and determines the corresponding classification.
- Updates the UI with the result message and color coding.


## Requirements:

- Android Studio: Chipmunk or later.
- Minimum SDK: API 21 (Android 5.0)
- Kotlin: 1.7 or later

##Future Improvements:

- Add support for more weight and height units.
- Implement adaptive icons for a better visual experience.
- Provide recommendations based on BMI results.


