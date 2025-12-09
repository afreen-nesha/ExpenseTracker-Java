import java.util.*;

abstract class Expense{
    private String Id;
    private String Title;
    private int Amount;
    private String dateOfExpense;

    public Expense(String Id, String Title, int Amount, String dateOfExpense){
    this.Id = Id;
    this.Title = Title;
    this.Amount = Amount;
    this.dateOfExpense = dateOfExpense;
    }

    abstract void displayDetails();

    public String getId(){
        return Id;
    }
    public String getTitle(){
        return Title;
    }
    public int getAmount(){
        return Amount;
    }
    public String getdateOfExpense(){
        return dateOfExpense;
    }
}

interface Recurring{
    public void totalFortheYear();
}

class FoodExpense extends Expense{
    private String MealType;

    public FoodExpense(String Id, String Title, int Amount, String dateOfExpense,String MealType){
        super(Id,Title,Amount,dateOfExpense);
        this.MealType=MealType;
    }

    public String getMealType(){
        return MealType;
    }

    @Override
    public void displayDetails(){
        System.out.println("Title: "+getTitle()+"Amount: "+getAmount()+"date: "+getdateOfExpense()+"type: "+getMealType());
    }
}

class TravelExpense extends Expense{
    public TravelExpense(String Id, String Title, int Amount, String dateOfExpense){
        super(Id,Title,Amount,dateOfExpense);
    }

    @Override
    public void displayDetails(){
        System.out.println("Title: "+getTitle()+"Amount: "+getAmount()+"date: "+getdateOfExpense());
    }
}

class RentExpense extends Expense implements Recurring{
    public RentExpense(String Id, String Title, int Amount, String dateOfExpense){
        super(Id,Title,Amount,dateOfExpense);
    } 
    @Override
    public void displayDetails(){
        System.out.println("Title: "+getTitle()+"Amount: "+getAmount()+"date: "+getdateOfExpense());
    }

    @Override 
    public void totalFortheYear(){
        int rent=getAmount()*12;
        System.out.println("total for the year is: "+rent);
    }

}

public class ExpenseTracker{
    public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    ArrayList<Expense> expenses = new ArrayList<>();

    while (true) {
        System.out.println("Expense Tracker Menu");
        System.out.println("1. Add Food Expense");
        System.out.println("2. Add Travel Expense");
        System.out.println("3. Add Rent Expense");
        System.out.println("4. Display All Expenses");
        System.out.println("5. Exit");
        System.out.println("6. Show Total of All Expenses");
        System.out.println("7. Show Category-wise Totals");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();
        sc.nextLine();

        if (ch == 5) break;

        String Id, Title, Date, MealType;
        int Amount;

        switch (ch) {
            case 1:
                System.out.print("Enter ID: ");
                Id = sc.nextLine();
                System.out.print("Enter Title: ");
                Title = sc.nextLine();
                System.out.print("Enter Amount: ");
                Amount = sc.nextInt(); sc.nextLine();
                System.out.print("Enter Date: ");
                Date = sc.nextLine();
                System.out.print("Enter Meal Type: ");
                MealType = sc.nextLine();
                expenses.add(new FoodExpense(Id, Title, Amount, Date, MealType));
                break;

            case 2:
                System.out.print("Enter ID: ");
                Id = sc.nextLine();
                System.out.print("Enter Title: ");
                Title = sc.nextLine();
                System.out.print("Enter Amount: ");
                Amount = sc.nextInt(); sc.nextLine();
                System.out.print("Enter Date: ");
                Date = sc.nextLine();
                expenses.add(new TravelExpense(Id, Title, Amount, Date));
                break;

            case 3:
                System.out.print("Enter ID: ");
                Id = sc.nextLine();
                System.out.print("Enter Title: ");
                Title = sc.nextLine();
                System.out.print("Enter Amount: ");
                Amount = sc.nextInt(); sc.nextLine();
                System.out.print("Enter Date: ");
                Date = sc.nextLine();
                expenses.add(new RentExpense(Id, Title, Amount, Date));
                break;

            case 4:
                System.out.println("\n--- All Expenses ---");
                for (Expense e : expenses) {
                    e.displayDetails();
                }
                break;

            case 6:
                int total = 0;
                for (Expense e : expenses) {
                    total += e.getAmount();
                }
                System.out.println("Grand Total Expense: " + total);
                break;

            case 7:
                int foodTotal = 0, travelTotal = 0, rentTotal = 0;

                for (Expense e : expenses) {
                    if (e instanceof FoodExpense) foodTotal += e.getAmount();
                    else if (e instanceof TravelExpense) travelTotal += e.getAmount();
                    else if (e instanceof RentExpense) rentTotal += e.getAmount();
                }

                System.out.println("Category Wise Totals");
                System.out.println("Food Total: " + foodTotal);
                System.out.println("Travel Total: " + travelTotal);
                System.out.println("Rent Total: " + rentTotal);
                System.out.println("Grand Total: " + (foodTotal + travelTotal + rentTotal));
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }

    sc.close();
}




