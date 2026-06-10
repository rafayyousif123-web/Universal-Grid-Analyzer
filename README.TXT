Universal Grid Analyzer (2D Array Algorithm System)

Student Name: Rafay Yousif  
Class/Section: 11B3  



Description:
This Java program functions as a general-purpose grid analyzer that executes a series of structured algorithms on a 2D integer array dataset without relying on any dynamic data structures like ArrayLists. The system dynamically scales to process any valid dataset with a minimum size of 6x6, utilizing nested loops and specialized tracking methods to manipulate and analyze the data based on user input.



 Algorithms Implemented
1.Row & Column Aggregation: Computes the total sum of each individual row and column, storing the final data in separate 1D arrays.
2.Global Max & Min with Position: Scans the entire grid to locate the absolute maximum and minimum values, tracking and returning their precise (row, col) coordinate positions.
3.Frequency Counter: Prompts the user for a target integer to count its occurrences, while simultaneously tracking how many elements exceed a designated threshold value.
4.Row & Column Comparison: Analyzes the aggregation arrays to isolate and identify the specific row index with the highest overall sum and the column index with the lowest sum.
5.Pattern Detection (Increasing Sequence): Checks the grid horizontally to see if any row contains a strictly increasing sequence of values, returning its index.
6.Grid Transformation: Modifies the grid structure by implementing row rotation (shifting right by 1), swapping two user-specified rows, and reversing the order of elements in a column.
7.Subgrid Processing: Takes coordinate bounds (rowStart, rowEnd, colStart, colEnd) from the user to isolate a specific section of the grid, calculating its total sum and finding its maximum value.
8.Boundary & Diagonal Processing: Isolates and prints the outer boundary elements of the matrix, followed by the main diagonal and the secondary diagonal elements.
9.Validation Algorithm: Scans the data horizontally to validate the grid, returning true or false based on whether any row contains duplicate values.
10.Menu-Driven System: A clean console interface that ties all the algorithms together, allowing interactive navigation until the user chooses to exit.



Challenges Faced
Managing Matrix Transformations: Implementing the logic to rotate a row right by 1 and reversing columns required careful pointer and index management to avoid overwriting elements before they were moved.
Boundary and Diagonal Logic: Crafting the loops for the secondary diagonal required managing inverse coordinate relationships without triggering out-of-bounds errors.



Sample Output:

=== UNIVERSAL GRID ANALYZER MENU ===
1. Display Grid
2. Row/Column Sums (Aggregation)
3. Max/Min with Positions
4. Frequency & Threshold Check
5. Row/Column Comparison
6. Pattern Detection (Increasing Sequence)
7. Transform Grid (Rotate/Swap/Reverse)
8. Subgrid Analysis
9. Boundary & Diagonals
0. Exit
Enter your choice (0-9): 