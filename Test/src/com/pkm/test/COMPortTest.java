package com.pkm.test;
import jssc.SerialPortList;
 
public class COMPortTest {
 
    public static void main(String[] args) {
        //Method getPortNames() returns an array of strings. Elements of the array is already sorted.
        String[] portNames = SerialPortList.getPortNames();
        for(int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }
    }
}
