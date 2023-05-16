package com.example.rollback;

import java.util.Scanner;

import com.example.rollback.services.Service;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter names for both the tables");
        System.out.print("Name 1: ");
        String name1String = scanner.nextLine();
        System.out.print("\nName 2: ");
        String name2String = scanner.nextLine();
        scanner.close();
        Service service = new Service();
        service.saveAllData(name1String,name2String);
    }
}
