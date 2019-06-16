package com.gabi.backend.bikeparkend.service.implementation;

import java.util.Scanner;

public class FirstMatrix {
    private double[][] m;

    //TODO RANDURILE CORESPUND ITEMILOR => ROWS = USERI
    private int numberOfRows;
    private int numberOfColumns;

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


    public FirstMatrix(int rows, int columns) {
        numberOfRows = rows;
        numberOfColumns = columns;
        m = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                m[i][j] = 0;
            }
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
