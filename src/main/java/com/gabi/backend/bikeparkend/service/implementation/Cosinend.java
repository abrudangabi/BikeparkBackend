package com.gabi.backend.bikeparkend.service.implementation;

import java.util.Arrays;

public class Cosinend {

    public static double similarity(int vec1[],int vec2[])
    {

        int dop=0;
        for (int n=0;n<vec1.length;n++)
            dop +=vec1[n]*vec2[n] ;

        double mag1=0.0,mag2=0.0;
        for (int n=0;n<vec1.length;n++)
        {
            mag1 += Math.pow(vec1[n],2) ;
            mag2 += Math.pow(vec2[n],2) ;
        }

        mag1=Math.sqrt(mag1);
        mag2=Math.sqrt(mag2);

        double csim = dop / (mag1 * mag2);
        return csim;
    }


    public static double similarity(double vec1[],double vec2[])
    {
        double dop=0;
        //System.out.println("Lundgime vector " + vec1.length);
        for (int n=0;n<vec1.length;n++)
            dop +=vec1[n]*vec2[n] ;

        double mag1=0.0,mag2=0.0;
        for (int n=0;n<vec1.length;n++)
        {
            mag1 +=  Math.pow(vec1[n],2) ;
            mag2 +=  Math.pow(vec2[n],2) ;
        }

        mag1=Math.sqrt(mag1);
        mag2=Math.sqrt(mag2);

        double csim = dop / (mag1 * mag2);
        return csim;
    }

    public static void main(String[] args)
    {

        int vector1 [] = {2,2,3,5};
        int vector2 [] = {4,1,2,1};
        double csim1= Cosinend.similarity(vector1, vector2);

        System.out.println("\n Cosine  similarity between two N-D vectors");
        System.out.println("integer N-D vectors");
        System.out.println("Vector 1 :" + Arrays.toString(vector1));
        System.out.println("Vector 2 :" + Arrays.toString(vector2));
        System.out.println("similarity value :" + csim1);


        double vector3 [] = {5.2,2.6,1.4,5.0};
        double vector4 [] = {9.8,7.6,3.2,4.6};
        System.out.println("double ND vectors");
        double csim2=Cosinend.similarity(vector3, vector4);
        System.out.println("Vector 1 :" + Arrays.toString(vector3));
        System.out.println("Vector 2 :" + Arrays.toString(vector4));
        System.out.println("similarity value :" + csim2);
    }
}
