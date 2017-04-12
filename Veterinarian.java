import java.util.*;
import java.io.*;

public class Veterinarian
{
    public static void main (String[] args)
    {
        Choice choices = new Choice();
        Scanner input = new Scanner(System.in),
                inputStream = null;
        
        try
        {
            inputStream = new Scanner (new FileInputStream("Animal Hospital Employees.txt."));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Animal Hospital Employees.txt was not found" +
                               "or could not be opened.");
            System.exit(0);
        }
        
        int i = 0;
        int count = 0;
        
        List<String> lines = new ArrayList<String>();
        
        while (inputStream.hasNextLine())
        {
            lines.add(inputStream.nextLine());
            count++;
        }
        inputStream.close();
        
        String[] arr = lines.toArray(new String[0]);
        
        System.out.println("\t\tWelcome to the Animal Hospital's Employee System\n");
        System.out.println("What would you like to do today?");
        
        choices.selection(arr, lines);
    }
}

class Choice
{
    private char choiceChar;
    private int numChoice;
    private String[] occupation;
    
    public Choice()
    {
        choiceChar = ' ';
        numChoice = 0;
        occupation = new String [] {"Veterinarian", "Technician",
                                    "Veterinarian's Assistant", 
                                    "Receptionist"};
    }
    
    public void selection(String[] arr, List<String> lines)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("1 - Print\n2 - Delete\n3 - Add\n4 - Undo\n5 - Exit");
        System.out.print("\nChoice: ");
        numChoice = input.nextInt();
        
        while (numChoice > 5 || numChoice < 1)
        {
            System.out.println("\nPlease enter a valid number: ");
            System.out.print("Choice: ");
            numChoice = input.nextInt();
        }
        
        switch (numChoice)
        {
            case 1: print(arr, lines); break;
            case 2: delete(arr, lines); break;
            case 3: add(arr); break;
            case 4: undo(arr, lines); break;
            //case 5: overwriteData(arr); //change various employee data 
            case 5: System.exit(0); 
        }
    }
    
    public void print(String[] arr, List<String> lines)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter 'a' to print the number of employees." + //1
                         "\nEnter 'b' to print the names of the veterinarians that work here." + //1
                         "\nEnter 'c' to print the names of the technicians that work here." + //2
                         "\nEnter 'd' to print the names of the vet's assistances that work here." + //3
                         "\nEnter 'e' to print the names of the receptionists that work here." + //4
                         "\nEnter 'f' to print the number of patients that a vet treated." + //2
                         "\nEnter 'g' to print the number of patients that a technician treated." + //3
                         "\nEnter 'h' to print the number of patients that a vet's assistant's handled." + //4
                         "\nEnter 'i' to print an employee's ID number." +
                         "\nEnter 'j' to print the number of patients treated today." +
                         "\nEnter 'k' to return to the selection menu."); //5
        System.out.print("\nChoice: ");
        choiceChar = input.next().charAt(0);
        
        while (choiceChar != 'a' && choiceChar != 'b' && choiceChar != 'c' && choiceChar != 'd' && choiceChar != 'e'
               && choiceChar != 'f' && choiceChar != 'g' && choiceChar != 'h' && choiceChar != 'i' && choiceChar != 'j'
               && choiceChar != 'k')
        {
            System.out.println("\nPlease enter a valid letter.");
            System.out.print("Choice: ");
            choiceChar = input.next().charAt(0);
        }
        
        switch (choiceChar)
        {
            case 'a': printNumbers(arr, 1, lines); break;
            case 'b': printNames(arr, 1, lines); break; //vet
            case 'c': printNames(arr, 2, lines); break; //technician
            case 'd': printNames(arr, 3, lines); break; //vet's assistant
            case 'e': printNames(arr, 4, lines); break; //receptionist
            case 'f': printNumbers(arr, 2, lines); break;
            case 'g': printNumbers(arr, 3, lines); break; //
            case 'h': printNumbers(arr, 4, lines); break;
            case 'i': printIDS(arr, lines); break;
            case 'j': printNumbers(arr, 5, lines); break;
            case 'k': System.out.println(); selection(arr, lines); break;
        }
    }
   
    public void printNumbers(String[] arr, int n, List<String> lines)
    {
        int numOfEmp = 0,
            petsTreated = 0;
        String oDSN = " "; //one-digit String number
        
        if (n == 1)
        {
            numOfEmp = arr.length;
            System.out.println("Number of employees: " + numOfEmp);
            System.out.println();
            selection(arr, lines);
        }
        
        if (n == 2) //for vets
        {
            int i = 0;
            Scanner input = new Scanner(System.in),
                  scanner = new Scanner(arr[i]);
            List<String> occupantName = new ArrayList<String>();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupation[0]) && !arr[i].contains(occupation[2]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    occupantName.add(scanner.next());
                }
            }
            
            String[] occupantNameArray = occupantName.toArray(new String[0]);
            
            System.out.println("\nWhose information whould you like to see? ");
            for (i = 0; i < occupantNameArray.length; i++)
            {
                System.out.println("Enter " + (i + 1) + " for " + occupantNameArray[i]);
            }
            System.out.print("Choice: ");
            numChoice = input.nextInt();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupantNameArray[numChoice - 1]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    scanner.next();
                    oDSN = scanner.next();
                    petsTreated = Integer.parseInt(oDSN);
                    System.out.println(occupantNameArray[numChoice - 1] + " treated " + 
                                       petsTreated + " patients.");
                    System.out.println();
                }
            }
            selection(arr, lines);
        }
        
        if (n == 3) //for technician
        {
            int i = 0;
            Scanner input = new Scanner(System.in),
                  scanner = new Scanner(arr[i]);
            List<String> occupantName = new ArrayList<String>();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupation[1]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    occupantName.add(scanner.next());
                }
            }
            
            String[] occupantNameArray = occupantName.toArray(new String[0]);
            
            System.out.println("\nWhose information whould you like to see? ");
            for (i = 0; i < occupantNameArray.length; i++)
            {
                System.out.println("Enter " + (i + 1) + " for " + occupantNameArray[i]);
            }
            System.out.print("Choice: ");
            numChoice = input.nextInt();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupantNameArray[numChoice - 1]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    scanner.next();
                    oDSN = scanner.next();
                    petsTreated = Integer.parseInt(oDSN);
                    System.out.println(occupantNameArray[numChoice - 1] + " treated " + 
                                       petsTreated + " patients.");
                    System.out.println();
                }
            }
            selection(arr, lines);
        }
        
        if (n == 4) //for vet's assistant
        {
            int i = 0;
            Scanner input = new Scanner(System.in),
                  scanner = new Scanner(arr[i]);
            List<String> occupantName = new ArrayList<String>();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupation[2]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    scanner.next();
                    occupantName.add(scanner.next());
                }
            }
            
            String[] occupantNameArray = occupantName.toArray(new String[0]);
            
            System.out.println("\nWhose information whould you like to see? ");
            for (i = 0; i < occupantNameArray.length; i++)
            {
                System.out.println("Enter " + (i + 1) + " for " + occupantNameArray[i]);
            }
            System.out.print("Choice: ");
            numChoice = input.nextInt();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupantNameArray[numChoice - 1]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    scanner.next();
                    scanner.next();
                    oDSN = scanner.next();
                    petsTreated = Integer.parseInt(oDSN);
                    System.out.println(occupantNameArray[numChoice - 1] + " handled " + 
                                       petsTreated + " patients.");
                    System.out.println();
                }
            }
            selection(arr, lines);
        }
        
        if (n == 5) //overall number of patients treated
        {
            int i = 0;
            Scanner input = new Scanner(System.in),
                  scanner = new Scanner(arr[i]);
            List<String> occupantName = new ArrayList<String>();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupation[0]) && !arr[i].contains(occupation[2]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    scanner.next();
                    oDSN = scanner.next();
                    petsTreated += Integer.parseInt(oDSN);
                }
            }
            System.out.println(petsTreated + " patients were treated today.\n");
            selection(arr, lines);
        }
    }
    
    public void printNames(String[] arr, int n, List<String> lines)
    {
        if (n == 1) //veterinarian
        {
            int i = 0;
            Scanner input = new Scanner(System.in),
                  scanner = new Scanner(arr[i]);
            List<String> occupantName = new ArrayList<String>();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupation[0]) && !arr[i].contains(occupation[2]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    occupantName.add(scanner.next());
                }
            }
            
            String[] occupantNameArray = occupantName.toArray(new String[0]);
            
            System.out.println("\nVeterinarian(s)");
            for (i = 0; i < occupantNameArray.length; i++)
            {
                System.out.println(occupantNameArray[i]);
            }
            System.out.println();
            selection(arr, lines);
        }
        
        if (n == 2) //technician
        {
            int i = 0;
            Scanner input = new Scanner(System.in),
                  scanner = new Scanner(arr[i]);
            List<String> occupantName = new ArrayList<String>();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupation[1]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    occupantName.add(scanner.next());
                }
            }
            
            String[] occupantNameArray = occupantName.toArray(new String[0]);
            
            System.out.println("\nTechnician(s)");
            for (i = 0; i < occupantNameArray.length; i++)
            {
                System.out.println(occupantNameArray[i]);
            }
            System.out.println();
            selection(arr, lines);
        }
        
        if (n == 3) //vet's assistant
        {
            int i = 0;
            Scanner input = new Scanner(System.in),
                  scanner = new Scanner(arr[i]);
            List<String> occupantName = new ArrayList<String>();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupation[2]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    scanner.next();
                    occupantName.add(scanner.next());
                }
            }
            
            String[] occupantNameArray = occupantName.toArray(new String[0]);
            
            System.out.println("\nVet's Assistance(s)");
            for (i = 0; i < occupantNameArray.length; i++)
            {
                System.out.println(occupantNameArray[i]);
            }
            System.out.println();
            selection(arr, lines);
        }
        
        if (n == 4)
        {
            int i = 0;
            Scanner input = new Scanner(System.in),
                  scanner = new Scanner(arr[i]);
            List<String> occupantName = new ArrayList<String>();
            
            for (i = 0; i < arr.length; i++)
            {
                if (arr[i].contains(occupation[3]))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    occupantName.add(scanner.next());
                }
            }
            
            String[] occupantNameArray = occupantName.toArray(new String[0]);
            
            System.out.println("\nReceptionist(s)");
            for (i = 0; i < occupantNameArray.length; i++)
            {
                System.out.println(occupantNameArray[i]);
            }
            System.out.println();
            selection(arr, lines);
        }
    }
    
    public void printIDS(String[] arr, List<String> lines)
    {
        int i = 0;
        long iD = 0;
        String iDN = " "; //String ID number
        Scanner input = new Scanner(System.in),
              scanner = new Scanner(arr[i]);
        List<String> occupantName = new ArrayList<String>();
        
        for (i = 0; i < arr.length; i++)
        {
            if (!arr[i].contains(occupation[2]))
            {
                scanner = new Scanner(arr[i]);
                scanner.next();
                occupantName.add(scanner.next());
            }
            
            if (arr[i].contains(occupation[2]))
            {
                scanner = new Scanner(arr[i]);
                scanner.next();
                scanner.next();
                occupantName.add(scanner.next());
            }
        }
        
        String[] occupantNameArray = occupantName.toArray(new String[0]);
        
        System.out.println("\nEmployees");
        for (i = 0; i < occupantNameArray.length; i++)
        {
            System.out.println((i + 1) + " - " + occupantNameArray[i]);
        }
        System.out.print("Choice: ");
        numChoice = input.nextInt();
        
        for (i = 0; i < arr.length; i++)
        {
            if (arr[i].contains(occupantNameArray[numChoice - 1]))
            {
                if (!arr[i].contains("Veterinarian's Assistant") && !arr[i].contains("Receptionist"))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    scanner.next();
                    scanner.next();
                    iDN = scanner.next();
                    iD = Integer.parseInt(iDN);
                    System.out.println(occupantNameArray[numChoice - 1] + "'s ID: #" + iD);
                    System.out.println();
                }
                
                if (arr[i].contains("Veterinarian's Assistant"))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    scanner.next();
                    scanner.next();
                    scanner.next();
                    iDN = scanner.next();
                    iD = Integer.parseInt(iDN);
                    System.out.println(occupantNameArray[numChoice - 1] + "'s ID: #" + iD);
                    System.out.println();
                }
                
                if (arr[i].contains("Receptionist"))
                {
                    scanner = new Scanner(arr[i]);
                    scanner.next();
                    scanner.next();
                    iDN = scanner.next();
                    iD = Integer.parseInt(iDN);
                    System.out.println(occupantNameArray[numChoice - 1] + "'s ID: #" + iD);
                    System.out.println();
                }
            }
        }
        selection(arr, lines);
    }
    
    public void delete(String[] arr, List<String> lines) //delete line of employee
    {
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter 'a' to delete an employee." +
                         "\nEnter 'b' to return to the selection menu.");
        System.out.print("\nChoice: ");
        choiceChar = input.next().charAt(0);
        
        while (choiceChar != 'a' && choiceChar != 'b')
        {
            System.out.println("\nPlease enter a valid letter.");
            System.out.print("Choice: ");
            choiceChar = input.next().charAt(0);
        }
        
        switch (choiceChar)
        {
            case 'a': deleteEmployee(arr, lines); break;
            case 'b': System.out.println(); selection(arr, lines); break;
        }
    }
    
    public void deleteEmployee(String[] arr, List<String> lines) //delete line of employee and store line in array list
    {
        int i = 0;
        long iD = 0;
        String iDN = " ", //String ID number
               deletedLine = " "; //
        Scanner input = new Scanner(System.in),
              scanner = new Scanner(arr[i]);
        List<String> occupantName = new ArrayList<String>(),
                     deletedLinesFromFile = new ArrayList<String>(),
                     retainedLines = new ArrayList<String>();
        
        for (i = 0; i < arr.length; i++)
        {
            if (!arr[i].contains(occupation[2]))
            {
                scanner = new Scanner(arr[i]);
                scanner.next();
                occupantName.add(scanner.next());
            }
            
            if (arr[i].contains(occupation[2]))
            {
                scanner = new Scanner(arr[i]);
                scanner.next();
                scanner.next();
                occupantName.add(scanner.next());
            }
        }
        
        String[] occupantNameArray = occupantName.toArray(new String[0]);
        
        System.out.println("\nEmployees");
        for (i = 0; i < occupantNameArray.length; i++)
        {
            System.out.println((i + 1) + " - " + occupantNameArray[i]);
        }
        System.out.print("Choice: ");
        numChoice = input.nextInt();
        
        for (i = 0; i < arr.length; i++)
        {
            if (arr[i].contains(occupantNameArray[numChoice - 1]))
            {
                //deletedLinesFromFile = Arrays.asList(arr[i]); //either one
                deletedLinesFromFile.add(arr[i]);               //either one
                arr[i] = null;
            }
        }
        
        for (i = 0; i < arr.length; i++)
        {
            if(arr[i] != null)
            retainedLines.add(arr[i]);
        }
        
        String[] newArr = retainedLines.toArray(new String[0]);
        
        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter (new FileOutputStream("Animal Hospital Employees.txt."));
            for (i = 0; i < newArr.length; i++)
            {
                outputStream.println(newArr[i]);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening the file Animal Hospital Employees.txt.");
            System.exit(0);
        }
        
        System.out.println("The file Animal Hospital Employees.txt has been updated.");
        outputStream.close();
        System.out.println();
        selection(newArr, deletedLinesFromFile);
    }
    
    public void add(String[] arr) //add employee(s)
    {
        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<String>();
        String info = " ";
        
        System.out.println("\nWould you like to add new employee info?");
        System.out.print("Enter 'y' to add an employee." +
                         "\nEnter 'n' to return to the selection menu.");
        System.out.print("\nChoice: ");
        choiceChar = input.next().charAt(0);
        
        if (choiceChar == 'y')
        {
            System.out.println("\nOccupation\n1 - Veterinarian\n2 - Technician\n3 - Vet's Assistant\n4 - Receptionist");
            System.out.print("\nChoice: ");
            numChoice = input.nextInt();
            
            while (numChoice > 4 || numChoice < 1)
            {
                System.out.println("\nPlease enter a valid number: ");
                System.out.print("Choice: ");
                numChoice = input.nextInt();
            }
            
            switch (numChoice)
            {
                case 1: info = addVet(); break;///*newEmployeeInfo = "Veterinarian";*/ addVet("Veterinarian"); break;
                case 2: info = addTech(); break;///*newEmployeeInfo = "Technician";*/ addTech("Technician");break;
                case 3: info = addVetAsst(); break;//*newEmployeeInfo = "Vet's Assistant";*/ addVetAsst("Vet's Assistant");break;
                case 4: info = addRecept(); break;///*newEmployeeInfo = "Receptionist";*/ addRecept("Receptionist");break; 
            }
            
            PrintWriter outputStream = null;
            try
            {
                outputStream = new PrintWriter (new FileOutputStream("Animal Hospital Employees.txt."));
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Error opening the file Animal Hospital Employees.txt.");
                System.exit(0);
            }
            
            List<String> allNew = new ArrayList<String>(Arrays.asList(arr));
        
            allNew.add(info);
            
            String[] h = allNew.toArray(new String[0]);
            for (int i = 0; i < h.length; i++)
            {
                outputStream.println(h[i]);
            }
            
            System.out.println("The file Animal Hospital Employees.txt has been updated.");
            outputStream.close();
            System.out.println();
            selection(h, allNew);
        }
        
        System.out.println();
        if (choiceChar == 'n')
        selection(arr, lines);
    }
    
    public String addVet()
    {
        Scanner input = new Scanner(System.in);
        String name = " ", newEmployeeInfo = " ", b = " ", petsTreated = " ", iD = " ";
        System.out.print("Name: ");
        name = input.nextLine();
        
        while (name.length() < 2)
        {
            System.out.print("Enter a name with at least two letters: ");
            name = input.nextLine();
        }
        
        System.out.print("Pet's Treated: ");
        petsTreated = input.nextLine();
        System.out.print("Employee ID: #");
        iD = input.nextLine();
        newEmployeeInfo = "Veterinarian " + name + " " + petsTreated + " " + iD;
        System.out.println(newEmployeeInfo);
        return newEmployeeInfo;
    }
    
    public String addTech()
    {
        Scanner input = new Scanner(System.in);
        String name = " ", newEmployeeInfo = " ", petsTreated = " ", iD = " ";
        System.out.print("Name: ");
        name = input.nextLine();
        
        while (name.length() < 2)
        {
            System.out.print("Enter a name with at least two letters: ");
            name = input.nextLine();
        }
        
        System.out.print("Pet's Treated: ");
        petsTreated = input.nextLine();
        System.out.print("Employee ID: #");
        iD = input.nextLine();
        newEmployeeInfo = "Technician " + name + " " + petsTreated + " " + iD;
        return newEmployeeInfo;
    }
    
    public String addVetAsst()
    {
        Scanner input = new Scanner(System.in);
        String name = " ", newEmployeeInfo = " ", petsHandled = " ", iD = " ";
        System.out.print("Name: ");
        name = input.nextLine();
        
        while (name.length() < 2)
        {
            System.out.print("Enter a name with at least two letters: ");
            name = input.nextLine();
        }
        
        System.out.print("Pet's Handled: ");
        petsHandled = input.nextLine();
        System.out.print("Employee ID: #");
        iD = input.nextLine();
        newEmployeeInfo = "Vet's Assistant " + name + " " + petsHandled + " " + iD;
        return newEmployeeInfo;
    }
    
    public String addRecept()
    {
        Scanner input = new Scanner(System.in);
        String name = " ", newEmployeeInfo = " ", iD = " ";
        System.out.print("Name: ");
        name = input.nextLine();
        
        while (name.length() < 2)
        {
            System.out.print("Enter a name with at least two letters: ");
            name = input.nextLine();
        }
        
        System.out.print("Employee ID: #");
        iD = input.nextLine();
        newEmployeeInfo = "Receptionist " + name + " " + iD;
        return newEmployeeInfo;
    }
                    //retained lines, deleted lines
    public void undo(String[] newArr, List<String> lines) //undo deletion of employee(s) info and remember to update
    {
        String[] l = lines.toArray(new String[0]); //deleted line going into string array
        List<String> allNew = new ArrayList<String>(Arrays.asList(l)); //deleted line array going into array list
        
        for (int i = 0; i < newArr.length; i++)
        {
            allNew.add(newArr[i]);
        }
        
        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter (new FileOutputStream("Animal Hospital Employees.txt."));
         }
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening the file Animal Hospital Employees.txt.");
            System.exit(0);
        }
        
        String[] h = allNew.toArray(new String[0]);
        for (int i = 0; i < h.length; i++)
        {
            outputStream.println(h[i]);
        }
        System.out.println("The file Animal Hospital Employees.txt has been updated.");
        outputStream.close();
        System.out.println();
        selection(h, lines);
    }
}