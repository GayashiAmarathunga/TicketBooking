import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Theatre {
    private static final int [][] seatingArea = new int [3][];//main seating area array
    private static  int totalSeats = 0;
    private static  int availableSeats = 0;
    private static ArrayList<Ticket> tickets = new ArrayList<>();//new array for ticket class
    public static void main(String[] args) {
        seatingArea[0] = new int[12]; // 12 seats in row 1
        seatingArea[1] = new int[16]; //16 seats in row 2
        seatingArea[2] = new int[20]; // 20 seats in row 3
        for (int[] ints : seatingArea) {
            totalSeats += ints.length;
            availableSeats += ints.length;
        }
        //welcome msg
        System.out.println("Welcome to New Theatre");
        Scanner scanner = new Scanner(System.in);
        while (true){
            // menu
            System.out.println("----------------------------------------------------------------");
            System.out.println("please select an option: ");
            System.out.println("1)buy a ticket");
            System.out.println("2)printing seating area");
            System.out.println("3)cancel ticket");
            System.out.println("4)List of available seats");
            System.out.println("5)save");
            System.out.println("6)Load");
            System.out.println("7)print ticket information and total price");
            System.out.println("8)sort tickets by surname");
            System.out.println("0)quit");
            System.out.println("-------------------------------------------------------------");
            System.out.println("Enter option:");


            try{
                int option = scanner.nextInt();   //get option input
                //user option customize
                switch (option) {
                    case 1 ->
                        //buy ticket
                            buy_ticket();
                    case 2 ->
                        //printing seating area
                            print_seating_area();
                    case 3 ->
                        // cancel ticket
                            cancel_ticket();
                    case 4 ->
                        //availableSeats
                            show_available();
                    case 5 ->
                        //save
                            save();
                    case 6 ->
                        //load
                            load();
                    case 7 ->
                        //ticket info
                            show_tickets_info();
                    case 8 ->
                        //sort ticket
                            sort_tickets();
                    case 0 -> {
                        //exit
                        System.out.println("Thank you using New theatre...");
                        System.out.println("Have a nice day...");
                        return;
                    }
                    default -> System.out.println("iInvalid option. Please select a valid option from the menu ");
                }
            }catch (InputMismatchException e){
                System.out.println("invalid input try again..");
                scanner.nextLine();
            }

        }
    }

    public static void print_seating_area(){
        //Method for printing available seating area
        System.out.println("     *********");
        System.out.println("    *  STAGE  *");
        System.out.println("     *********");
        for (int rowCount = 0; rowCount < seatingArea.length; rowCount++) {
            //This loop for design the seating area
            int count = 0;
            if (seatingArea[rowCount].length == 12){
                System.out.print("    ");
            } else if (seatingArea[rowCount].length == 16) {
                System.out.print("  ");
            }

            for (int seatCount = 0; seatCount < seatingArea[rowCount].length; seatCount++) {
                // this loop print available seats
                if (count==(seatingArea[rowCount].length)/2){
                    System.out.print(" ");
                }
                if (seatingArea[rowCount][seatCount] == 0) {
                    System.out.print("0");
                } else {
                    System.out.print("X");
                }
                count++;

            }
            System.out.println();
        }
    }

    public static void buy_ticket(){
        //this method for buying tickets
        print_seating_area();
        System.out.println("0 = Available seats & X = Already Booked seats ");
        System.out.print("Enter Row Number : ");
        Scanner scanner = new Scanner(System.in);
        int rowNum = scanner.nextInt() -1 ;
        if (rowNum<0 || rowNum>2){
            System.out.println("sorry invalid row number");
            return;
        }
        System.out.print("Enter Seat Number : ");
        int seatNum = scanner.nextInt() -1 ;
        if(seatNum < 0 || seatNum>=seatingArea[rowNum].length){
            System.out.println("sorry invalid seat number");
            return;
        }
        if (seatingArea[rowNum][seatNum] != 0){
            System.out.println("sorry already book");
            return;
        }
        try{
            //this try for storing inputs for tickets array
            System.out.print("Please enter your name:");
            String name = scanner.next();
            System.out.print("Please enter your surname:");
            String surname = scanner.next();
            System.out.print("Please enter your email:");
            String email = scanner.next();
            System.out.print("Please enter the ticket price:");
            double price = scanner.nextDouble();

            person obj = new person(name,surname,email);
            Ticket obj2 = new Ticket(rowNum,seatNum,price,obj);
            tickets.add(obj2);

        }catch (InputMismatchException e){
            System.out.println("Invalid input. Please try again.");
            return;
        }
        seatingArea[rowNum][seatNum]=1;
        availableSeats--;
        System.out.println("Ticket purchased for row " + (rowNum +1) + ", seat " + (seatNum+1) + ".");

    }


    public static void cancel_ticket(){
        //this method for cancel tickets
        System.out.print("please enter the row");
        Scanner scanner = new Scanner(System.in);
        int rowNum = scanner.nextInt() -1;

        if (rowNum<0 || rowNum>2){
            System.out.println("sorry invalid row number");
            return;
        }
        System.out.print("please enter the seat");
        int seatNum = scanner.nextInt()-1;

        if(seatNum < 0 || seatNum>=seatingArea[rowNum].length){
            System.out.println("sorry invalid seat number");
            return;
        }

        if (seatingArea[rowNum][seatNum] == 0){
            System.out.println("This seat hasn't book or Seat already cancel");
            return;
        }

        seatingArea[rowNum][seatNum] = 0;
        availableSeats ++;
        for (int i = 0; i < tickets.size(); i++) {
            //this loop for remove data from the ticket array
            Ticket ticket = tickets.get(i);
            if (ticket.getRowNum() == rowNum && ticket.getSeatNum() == seatNum) {
                tickets.remove(i);
            }
            System.out.println("Ticket cancelled successfully!");
        }


    }

    public static void show_available(){
        //this method for showing available seat numbers
        for(int rowCount= 0; rowCount < seatingArea.length;rowCount++){
            System.out.print("Seats available in row ");
            System.out.print(rowCount+1 + ": ");
            for(int seatCount = 0;seatCount < seatingArea[rowCount].length;seatCount++){
                if(seatingArea[rowCount][seatCount] == 0){
                    System.out.print(seatCount+1 +",");
                }else{
                    System.out.print("");
                }
            }
            System.out.println(" ");

        }
        
    }

    public static void save() {
        //this method for saving buying or available tickets to seatFile
        try {
            File file = new File("seatFile.txt");
            FileWriter seatWriter = new FileWriter(file);

            // Write the seating area data to the file
            for (int rowCount = 0; rowCount < seatingArea.length; rowCount++) {
                for (int seatCount = 0; seatCount < seatingArea[rowCount].length; seatCount++) {
                    seatWriter.write(seatingArea[rowCount][seatCount] + ",");
                }
                seatWriter.write("\n");
            }
            seatWriter.close();
            System.out.println("Data saved successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while saving the data: ");
        }
    }

    public static void load() {
        //this method for loading data from seatFile
        try{
            File file = new File("seatFile.txt");
            Scanner scanner = new Scanner(file);
            int rowCount = 0;
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] values = line.split(",");
                for (int seatCount = 0; seatCount < values.length; seatCount++) {
                    seatingArea[rowCount][seatCount] = Integer.parseInt(values[seatCount]);
                }
                rowCount++;
            }
            scanner.close();
            System.out.println("Seats data loaded successfully");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    public static void show_tickets_info() {
        //this method for showing ticket information
        double totalPrice = 0.0;
        System.out.println("\nTicket information:\n");
        //if no tickets uploaded
        if(tickets.size()==0){
            System.out.println("No Tickets");
            return;
        }

        for (Ticket ticket : tickets) {
            ticket.print();
            System.out.println("_________________________________________");
            totalPrice += ticket.getPrice();//calculate total price
        }
        System.out.println("Total price of all tickets: " + totalPrice);//printing total price
    }

    public static void sort_tickets() {
        //method for sort the ticket array
        ArrayList<Ticket> sortedTickets = new ArrayList<Ticket>(tickets);
        sortedTickets.sort(Comparator.comparingDouble(Ticket::getPrice));
        //if no tickets uploaded
        if(tickets.size()==0){
            System.out.println("No Tickets");
            return;
        }

        // print the sorted tickets
        System.out.println("\nSorted tickets by price (cheapest first):\n");
        for (Ticket ticket : sortedTickets) {
            ticket.print();
            System.out.println("_________________________________________");
        }


    }





}