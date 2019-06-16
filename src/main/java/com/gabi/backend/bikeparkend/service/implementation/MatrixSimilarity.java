package com.gabi.backend.bikeparkend.service.implementation;

import java.util.Scanner;

public class MatrixSimilarity {
    private double[][] m;

    //TODO RANDURILE CORESPUND ITEMILOR => ROWS = USERI
    private int numberOfRows;
    private int numberOfColumns;

//Constructors: Two constructors were created. The first, takes two ints as parameters for the shape of the matrix and initializes the elements to 0. The second has no parameters, but instead asks the user to supply the shape and element values via scanner.

    /*public MatrixSimilarity(int rows, int columns)
    {
        m = new double[rows][columns];

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                m[i][j] = 0;
            }
        }
    }*/

    public MatrixSimilarity() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter the number of rows: ");
        numberOfRows = keyboard.nextInt();
        System.out.print("Please enter the number of columns: ");
        numberOfColumns = keyboard.nextInt();
        m = new double[numberOfRows][numberOfColumns];
        System.out.println("Please assign the following elements: ");
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print("[" + i + "]" + "[" + j + "]: ");
                m[i][j] = keyboard.nextDouble();
            }
        }
    }

    public int getRows(){return numberOfRows;}
    public int getColumns(){return numberOfColumns;}
    public double getElement(int rows, int columns){return m[rows][columns];}

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }
    //Mutators: A private void mutator was created so that the value of a particular element within m, could be asigned a value later within the addition/subtraction/multiplication methods.

    public void assignElement(double value, int i, int j){m[i][j] = value;}

    public double getValue(int i, int j){
        return m[i][j];
    }


//Methods: Addition, subtraction and mutiplication operations were written as methods in order to operate on matricices. Both static and non-static methods were constructed.
//The static versions take two matricies as parameters, while the non-static counterparts only take one and operate it against m.
//Within all six methods a test is performed in order to check whether or not the two matrcicies are compatible or not.
//For addiition and subtraction the number of rows and columns must be the same for both matricies and a temporary matrix is created to match those parameters.
//For multiplication, the number of columns of the first matrix must be equivalent to the number of rows of the second matrix and a temporary matrix is created with the number of rows of the first matrix and
//the number of columns of the second matrix as parameters. If the matricies are not compatable an error message is printed.

    /*public static MatrixSimilarity staticAdd(MatrixSimilarity x, MatrixSimilarity y)
    {
        MatrixSimilarity z = new MatrixSimilarity(x.getRows(), x.getColumns());
        double value;
        System.out.println();
        System.out.println("The sum of the matricices is: ");

        if(x.getRows() == y.getRows() && x.getColumns() == y.getColumns())
        {
            for(int i = 0; i < x.getRows(); i++)
            {
                for(int j = 0; j < y.getColumns(); j++)
                {
                    value = x.getElement(i,j) + y.getElement(i,j);
                    z.assignElement(value,i,j);
                    System.out.print("[" + z.getElement(i,j) + "]");
                }
                System.out.print('\n');
            }
            return z;
        }
        else{System.out.println("ERROR: The number of rows and columns of the matricies are not equal."); return z;}
    }*/

    public MatrixSimilarity(int rows, int columns) {
        numberOfRows = rows;
        numberOfColumns = columns;
        m = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                m[i][j] = 1;
            }
        }
    }

    public void afisare(){
        for(int i = 0; i < numberOfRows; i++)
        {
            for(int j = 0; j < numberOfColumns; j++)
            {
                System.out.print(m[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public String toString()
    {
        String es = new String();

        for(int i = 0; i < numberOfRows; i++)
        {
            for(int j = 0; j < numberOfColumns; j++)
            {
                es += "[";
                es += m[i][j];
                es += "]";
            }
            es += '\n';
        }
        System.out.println();
        System.out.println("Your matrix: ");
        return es;
    }
}
