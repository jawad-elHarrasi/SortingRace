# Sorting Algorithm Comparison - Bubble Sort vs Merge Sort

## Description

This project is a JavaFX application that compares the execution speed of two popular sorting algorithms: **Bubble Sort** and **Merge Sort**. The application allows users to input an array of integers and visualize the sorting process for both algorithms. The goal is to demonstrate the time complexity and efficiency of each algorithm by showing the sorting duration and the number of operations performed.

### Sorting Algorithms

1. **Bubble Sort**: A simple comparison-based sorting algorithm that repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. It has a time complexity of **O(n²)**.
   
2. **Merge Sort**: A divide-and-conquer sorting algorithm that splits the array into smaller subarrays, sorts them, and merges them back together. It has a time complexity of **O(n log n)**.

## Features

- **Array Input**: Users can input their own array of integers or generate a random array.
- **Visual Sorting**: The application visually shows the sorting process step-by-step for both algorithms.
- **Time Comparison**: The time taken by each algorithm to sort the array is displayed, along with the number of comparisons and swaps made.
- **Responsive UI**: Built using **JavaFX**, the application provides a user-friendly and interactive graphical interface.

## Technologies Used

- **Java**: Core language for implementing the sorting algorithms and logic.
- **JavaFX**: Used to create the graphical user interface (GUI) for visualizing the sorting process.
- **FXML**: For UI layout design in combination with JavaFX.

## Prerequisites

To run or develop this project, you will need:

- **Java 8 or higher**
- **JavaFX SDK** (if not bundled with the JDK)
- **Maven** (optional for managing dependencies)

## Installation and Setup

To set up and run the project locally, follow these steps:

1. **Clone the Repository**

```bash
git clone https://github.com/jawad-elHarrasi/SortingRace.git
cd sortingRace
