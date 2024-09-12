public class Ticket {
    int rowNum,seatNum;
    double price;
    person person;

public Ticket(int rowNum, int seatNum, double price, person person){
    //creating constructor Ticket
    this.rowNum=rowNum;
    this.seatNum=seatNum;
    this.price=price;
    this.person = person;

}

//getters
public int getRowNum(){
    return rowNum;
}

public int getSeatNum(){
    return seatNum;

}
public double getPrice(){
    return price;

}

public void print(){
    //printing ticket details
    System.out.println("name: "+ person.getName());
    System.out.println("surname: "+ person.getSurName());
    System.out.println("email: "+ person.getEmail());
    System.out.println("Row number: "+(rowNum+1));
    System.out.println("Seat number: "+(seatNum+1));
    System.out.println("price: "+price);
}
}
