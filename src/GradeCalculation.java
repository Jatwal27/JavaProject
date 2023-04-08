import java.io.*;
import java.util.*;

public abstract class GradeCalculation {
    public	ArrayList<String> myList = new ArrayList<>();
    private String letterGrade;
    protected int NumOfAssessments = 5;
    public String getLetterGrade() {
        return letterGrade;
    }
    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
    public double[] calcGradeWeightages()
    {
        return null;

    }
    public void calcStudentLetterGrade(double percentage)
    {
    }
    public void printGrades()
    {
        Scanner sc=new Scanner(System.in);

        System.out.println("\nType T to print the elements on the text file, else the results won't be save to local");
        String str= sc.next();
        if(str.equals("T") || str.equals("t")){
            // Printing to a text file
            System.out.println("Printing to a text file...");
            System.out.println("COMPLETED: Results Saved in output.txt");
            try {
                File file = new File("output.txt");
                FileWriter writer = new FileWriter(file);
                for (String letter : this.myList) {
                    writer.write(letter + "\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        }
        else
        {
            System.out.println("Results not saved");
        }

    }


}
