import java.util.*;
import java.io.*;
import java.util.Scanner;
class main
{
    public static void main(String args[])
    {     
        // for colored output
        final String COLOR_OFF = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        // final String YELLOW = "\u001B[33m";

        String fileName = "./common_passwords.txt";
        String [] commonPasswords = new String[countLinesInFile(fileName)];
        String password = "";
        String loopChoice = "y";

        Scanner sc = new Scanner(System.in);	// for user input

        loadFileDataIntoStringArray(fileName, commonPasswords);

        while(!loopChoice.equals("n"))
        {

        
            System.out.println();

            System.out.print("Please type in a password to check its strength: ");
            password = sc.nextLine();

            System.out.println(passwordStrength(password));

            if(isPasswordCommon(password, commonPasswords))
                System.out.println(RED + "That password is in the common password database." + COLOR_OFF);
            else
                System.out.println(GREEN+ "That password is not in the common password database." + COLOR_OFF);

            System.out.println();

            System.out.print("Would you like to check another password? (y/n) ");
            loopChoice = sc.nextLine();

	    }
        sc.close();
        System.out.println("Exiting program.");
    }

// --- functions -----------------------------------------------------------------------------
   
public static void loadFileDataIntoStringArray(String fileName, String [] arr) {
    File file = new File(fileName);
    try
    {
        Scanner sc = new Scanner(file);
        for(int i = 0; sc.hasNextLine(); i++)
        {
            String line = sc.nextLine();
            arr[i] = line;
        } 
        sc.close();
    }
    catch (FileNotFoundException e)
    {
        System.err.println("File not found: " + fileName);
        e.printStackTrace();
    }
 }
 
 public static int countLinesInFile(String fileName)
 {
    File file = new File(fileName);
    try
    {
        Scanner sc = new Scanner(file);
        int count = 0; 
        while(sc.hasNextLine()) {
            count++;
            sc.nextLine();
        } 
        return count;
    }
    catch (FileNotFoundException e) {
        System.err.println("File not found: " + fileName);
        e.printStackTrace();
    }
    return 0;
 }
 
    public static boolean isPasswordCommon (String password, String [] commonPasswords)
    {
        for(int counter=0; counter < commonPasswords.length; counter++)
            if(password.equals(commonPasswords[counter]))
                return true;
        
        return false;
    }
    

    public static String passwordStrength(String input)
    {
        int strengthScore = 0;
    
        // Length Check
        if(input.length()>= 8)
            strengthScore++;

        // Extra Length Check
        if(input.length()>= 15)
            strengthScore++;

        // If password contains a special character
        if(input.contains("!") || input.contains("@") || input.contains("#") || input.contains("$") || input.contains("%") || input.contains("^") || input.contains("&") || input.contains("*"))
            strengthScore++;
            
        switch(strengthScore)
        {
            case 0:
                return "Strength: 0/3  Very Weak";
    
            case 1:
                return "Strength: 1/3  Weak";
            
            case 2:
                return "Strength: 2/3  Average";

            case 3:
            return "Strength: 3/3  Strong";
    
            default:
                return "Password Check Failure";
        }
    }

}


