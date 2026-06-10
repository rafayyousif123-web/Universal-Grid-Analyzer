import java.util.Scanner;
public class UniversalGridAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        int[][] grid = {
            {12, 23, 34, 45, 56, 67}, 
            {89, 14, 55, 23, 72, 11}, 
            {10, 20, 30, 40, 50, 10}, 
            {44, 88, 22, 11, 66, 33}, 
            {90, 80, 70, 60, 50, 40}, 
            { 5, 15, 25, 35, 45, 55}  
        };

        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice (0-9): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    displayGrid(grid);
                    break;
                case 2:
                    rowColumnAggregation(grid);
                    break;
                case 3:
                    globalMaxMin(grid);
                    break;
                case 4:
                    System.out.print("Enter a specific value to count: ");
                    int target = scanner.nextInt();
                    System.out.print("Enter a threshold value: ");
                    int threshold = scanner.nextInt();
                    frequencyCounter(grid, target, threshold);
                    break;
                case 5:
                    rowColumnComparison(grid);
                    break;
                case 6:
                    int increasingRow = patternDetection(grid);
                    if (increasingRow != -1) {
                        System.out.println("Strictly increasing sequence found at Row Index: " + increasingRow);
                    } else {
                        System.out.println("No strictly increasing rows detected (-1).");
                    }
                    break;
                case 7:
                    handleTransformations(grid, scanner);
                    break;
                case 8:
                    handleSubgrid(grid, scanner);
                    break;
                case 9:
                    boundaryAndDiagonalProcessing(grid);
                    break;
                case 0:
                    System.out.println("Exiting Universal Grid Analyzer. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 0 and 9.");
            }
            System.out.println("\n---------------------------------------------");
        } while (choice != 0);

        scanner.close();
    }

    public static void printMenu() {
        System.out.println("=== UNIVERSAL GRID ANALYZER MENU ===");
        System.out.println("1. Display Grid");
        System.out.println("2. Row/Column Sums (Aggregation)");
        System.out.println("3. Max/Min with Positions");
        System.out.println("4. Frequency & Threshold Check");
        System.out.println("5. Row/Column Comparison");
        System.out.println("6. Pattern Detection (Increasing Sequence)");
        System.out.println("7. Transform Grid (Rotate/Swap/Reverse)");
        System.out.println("8. Subgrid Analysis");
        System.out.println("9. Boundary & Diagonals");
        System.out.println("0. Exit");
    }

    public static void displayGrid(int[][] grid) {
        System.out.println("Current Grid Status:");
        for (int[] row : grid) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    public static void rowColumnAggregation(int[][] grid) {
        int[] rowSums = new int[grid.length];
        int[] colSums = new int[grid[0].length];

        
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                rowSums[r] += grid[r][c];
                colSums[c] += grid[r][c];
            }
        }

        System.out.println("Row Sums:");
        for (int i = 0; i < rowSums.length; i++) {
            System.out.println("Row " + i + ": " + rowSums[i]);
        }

        System.out.println("\nColumn Sums:");
        for (int j = 0; j < colSums.length; j++) {
            System.out.println("Column " + j + ": " + colSums[j]);
        }
    }

    public static void globalMaxMin(int[][] grid) {
        int maxVal = grid[0][0];
        int maxRow = 0, maxCol = 0;

        int minVal = grid[0][0];
        int minRow = 0, minCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] > maxVal) {
                    maxVal = grid[r][c];
                    maxRow = r;
                    maxCol = c;
                }
                if (grid[r][c] < minVal) {
                    minVal = grid[r][c];
                    minRow = r;
                    minCol = c;
                }
            }
        }

        System.out.println("Global Maximum: " + maxVal + " at (" + maxRow + ", " + maxCol + ")");
        System.out.println("Global Minimum: " + minVal + " at (" + minRow + ", " + minCol + ")");
    }

    public static void frequencyCounter(int[][] grid, int target, int threshold) {
        int targetCount = 0;
        int thresholdCount = 0;

        for (int[] row : grid) {
            for (int val : row) {
                if (val == target) {
                    targetCount++;
                }
                if (val > threshold) {
                    thresholdCount++;
                }
            }
        }

        System.out.println("Occurrences of value (" + target + "): " + targetCount);
        System.out.println("Count of values strictly greater than threshold (" + threshold + "): " + thresholdCount);
    }

    public static void rowColumnComparison(int[][] grid) {
        int[] rowSums = new int[grid.length];
        int[] colSums = new int[grid[0].length];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                rowSums[r] += grid[r][c];
                colSums[c] += grid[r][c];
            }
        }

        int maxRowIndex = 0;
        for (int i = 1; i < rowSums.length; i++) {
            if (rowSums[i] > rowSums[maxRowIndex]) {
                maxRowIndex = i;
            }
        }

        int minColIndex = 0;
        for (int j = 1; j < colSums.length; j++) {
            if (colSums[j] < colSums[minColIndex]) {
                minColIndex = j;
            }
        }

        System.out.println("Row with the highest sum: Row " + maxRowIndex + " (Sum: " + rowSums[maxRowIndex] + ")");
        System.out.println("Column with the lowest sum: Column " + minColIndex + " (Sum: " + colSums[minColIndex] + ")");
    }

    public static int patternDetection(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            boolean isIncreasing = true;
            for (int c = 0; c < grid[r].length - 1; c++) {
                if (grid[r][c] >= grid[r][c + 1]) {
                    isIncreasing = false;
                    break; 
                }
            }
            if (isIncreasing) {
                return r; 
            }
        }
        return -1;
    }

    public static void handleTransformations(int[][] grid, Scanner scanner) {
        System.out.println("Transformation Options:");
        System.out.println(" a. Rotate a row right by 1");
        System.out.println(" b. Swap two rows");
        System.out.println(" c. Reverse a column");
        System.out.print("Select transformation option (a/b/c): ");
        char choice = scanner.next().toLowerCase().charAt(0);

        switch (choice) {
            case 'a':
                System.out.print("Enter row index to rotate (0-" + (grid.length - 1) + "): ");
                int rRot = scanner.nextInt();
                if (rRot >= 0 && rRot < grid.length) {
                    rotateRowRight(grid, rRot);
                    System.out.println("Row " + rRot + " rotated successfully.");
                } else {
                    System.out.println("Invalid row index.");
                }
                break;
            case 'b':
                System.out.print("Enter first row index: ");
                int r1 = scanner.nextInt();
                System.out.print("Enter second row index: ");
                int r2 = scanner.nextInt();
                if (r1 >= 0 && r1 < grid.length && r2 >= 0 && r2 < grid.length) {
                    swapRows(grid, r1, r2);
                    System.out.println("Rows swapped successfully.");
                } else {
                    System.out.println("Invalid indices provided.");
                }
                break;
            case 'c':
                System.out.print("Enter column index to reverse (0-" + (grid[0].length - 1) + "): ");
                int colRev = scanner.nextInt();
                if (colRev >= 0 && colRev < grid[0].length) {
                    reverseColumn(grid, colRev);
                    System.out.println("Column " + colRev + " reversed successfully.");
                } else {
                    System.out.println("Invalid column index.");
                }
                break;
            default:
                System.out.println("Unknown option transformation bypassed.");
        }
    }

    public static void rotateRowRight(int[][] grid, int rowIndex) {
        int lastElement = grid[rowIndex][grid[rowIndex].length - 1];
        for (int c = grid[rowIndex].length - 1; c > 0; c--) {
            grid[rowIndex][c] = grid[rowIndex][c - 1];
        }
        grid[rowIndex][0] = lastElement;
    }

    // Variant 6b: Swap two rows
    public static void swapRows(int[][] grid, int r1, int r2) {
        int[] temp = grid[r1];
        grid[r1] = grid[r2];
        grid[r2] = temp;
    }

    public static void reverseColumn(int[][] grid, int colIndex) {
        int startRow = 0;
        int endRow = grid.length - 1;
        while (startRow < endRow) {
            int temp = grid[startRow][colIndex];
            grid[startRow][colIndex] = grid[endRow][colIndex];
            grid[endRow][colIndex] = temp;
            startRow++;
            endRow--;
        }
    }

    public static void handleSubgrid(int[][] grid, Scanner scanner) {
        System.out.print("Enter rowStart index: ");
        int rStart = scanner.nextInt();
        System.out.print("Enter rowEnd index: ");
        int rEnd = scanner.nextInt();
        System.out.print("Enter colStart index: ");
        int colStart = scanner.nextInt();
        System.out.print("Enter colEnd index: ");
        int colEnd = scanner.nextInt();

        // Validating range bounds
        if (rStart >= 0 && rEnd < grid.length && colStart >= 0 && colEnd < grid[0].length && rStart <= rEnd && colStart <= colEnd) {
            subgridProcessing(grid, rStart, rEnd, colStart, colEnd);
        } else {
            System.out.println("Invalid bound inputs provided for subgrid tracking window.");
        }
    }

    public static void subgridProcessing(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        int sum = 0;
        int maxVal = grid[rowStart][colStart];

        for (int r = rowStart; r <= rowEnd; r++) {
            for (int c = colStart; c <= colEnd; c++) {
                sum += grid[r][c];
                if (grid[r][c] > maxVal) {
                    maxVal = grid[r][c];
                }
            }
        }
        System.out.println("Subgrid Metrics Result:");
        System.out.println("  Sum of Subgrid: " + sum);
        System.out.println("  Max within Subgrid: " + maxVal);
    }

  
    public static void boundaryAndDiagonalProcessing(int[][] grid) {
        System.out.print("Boundary Elements: ");
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (r == 0 || r == grid.length - 1 || c == 0 || c == grid[r].length - 1) {
                    System.out.print(grid[r][c] + " ");
                }
            }
        }
        System.out.println();

        System.out.print("Main Diagonal Elements: ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(grid[i][i] + " ");
        }
        System.out.println();

        System.out.print("Secondary Diagonal Elements: ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(grid[i][grid.length - 1 - i] + " ");
        }
        System.out.println();
    }

    public static boolean validationAlgorithm(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c1 = 0; c1 < grid[r].length; c1++) {
                for (int c2 = c1 + 1; c2 < grid[r].length; c2++) {
                    if (grid[r][c1] == grid[r][c2]) {
                        System.out.println("Duplicate element " + grid[r][c1] + " found in Row " + r);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}