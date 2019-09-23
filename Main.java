package com.company;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        Long startTime = System.currentTimeMillis();
        HospitalDataProcessor p1 = new HospitalDataProcessor("C:/Users/Dennis/Desktop/java/files/Hospital_General_Information.txt");
        p1.displayAndWriteSearchResults("C:/Users/Dennis/Desktop/java/files/new.txt");
        Long finishTime = System.currentTimeMillis();

        System.out.println(finishTime-startTime);

    }
}
