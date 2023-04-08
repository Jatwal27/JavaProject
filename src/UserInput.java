import java.io.File;
import java.util.*;
public class UserInput{
    public double [] weights;
    private ArrayList<String> studentNumber=new ArrayList<String>();
    private ArrayList<String> firstName=new ArrayList<String>();
    private ArrayList<String> lastName=new ArrayList<String>();
    private ArrayList<Double> quiz1=new ArrayList<Double>();
    private ArrayList<Double> quiz2=new ArrayList<Double>();
    private ArrayList<Double> midterm=new ArrayList<Double>();
    private ArrayList<Double> assignment=new ArrayList<Double>();
    private ArrayList<Double> finalExam=new ArrayList<Double>();
    private ArrayList<Double> percentage=new ArrayList<Double>();
    private ArrayList<String> letterGrade=new ArrayList<String>();
    private ArrayList<String> fileGrades=new ArrayList<String>();

    DefaultGrading obj=new DefaultGrading();
    CustomGrading obj1=new CustomGrading();


    public String getStudentNumber(int i) {
        return this.studentNumber.get(i);
    }

    public String getFirstName(int i) {
        return this.firstName.get(i);
    }

    public String getLastName(int i) {
        return this.lastName.get(i);
    }

    public Double getQuiz1(int i) {
        return this.quiz1.get(i);
    }

    public Double getQuiz2(int i) {
        return this.quiz2.get(i);
    }

    public Double getMidterm(int i) {
        return this.midterm.get(i);
    }

    public Double getAssignment(int i) {
        return this.assignment.get(i);
    }

    public Double getFinalExam(int i) {
        return this.finalExam.get(i);
    }

    public Double getPercentage(int i) {
        return this.percentage.get(i);
    }

    public void setPercentage(double i) {
        this.percentage.add(i);
    }

    public String getLetterGrade(int i) {
        return this.letterGrade.get(i);
    }

    public void setLetterGrade(String i) {
        this.letterGrade.add(i);
    }
    public String getFileGrades(int i) {
        return this.fileGrades.get(i);
    }

    public void setFileGrades(String i) {
        this.fileGrades.add(i);}

    public int readFile(String fileName)
    {int count=0;
        try
        {
            File f1=new File(fileName);

            Scanner reader=new Scanner(f1);
            while (reader.hasNext())
            {
                this.studentNumber.add(reader.next());
                this.firstName.add(reader.next());
                this.lastName.add(reader.next());
                this.quiz1.add(reader.nextDouble());
                this.quiz2.add(reader.nextDouble());
                this.midterm.add(reader.nextDouble());
                this.assignment.add(reader.nextDouble());
                this.finalExam.add(reader.nextDouble());

                this.fileGrades.add(this.getStudentNumber(count)+"\t"+this.getFirstName(count)+"\t"+this.getLastName(count)+"\t        "+
                        this.getQuiz1(count)+"\t"+this.getQuiz2(count)+"\t"+this.getMidterm(count)+"\t "+this.getAssignment(count)+"\t        "+this.getFinalExam(count));
                count++;
            }

        }
        catch(Exception e)
        {
            System.out.print("error reading count "+ count);
            System.out.print(e);

        }

        return count;
    }

    public void printFile(String fName) {
        try {
            File f1 = new File(fName);
            Scanner reader = new Scanner(f1);
            String data;
            while (reader.hasNextLine()) {
                data=reader.nextLine();
                String[] arrString = data.split(" ", 8);
                for(String i: arrString)
                {
                    if(i.length()>5)
                    {
                        System.out.print(i+"\t");
                    }
                    else
                    {
                        System.out.print(i+"\t       ");
                    }

                }
                System.out.println();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void getWeightageInput()
    {
        //Jodhveer's Code
        boolean TotalError = false, ChoiceError;
        Scanner UserIn = new Scanner(System.in);
        do {

            System.out.println("How would like to weight your assessments? \n(1)Equal weighting for all assessments\n(2)Custom weighting");
            char choice = UserIn.next().charAt(0);
            if (choice == '2') {
                this.weights=obj1.calcGradeWeightages();
                ChoiceError=false;
            }
            else if (choice == '1') {

                this.weights=obj.calcGradeWeightages();
                ChoiceError=false;
            }
            else {
                ChoiceError = true;
                System.out.println("Error, please enter a valid choice!");

            }

        }while(ChoiceError);

//End of Jodhveer's Code
    }

    public void getSchemaInput()
    {

        boolean choice=true;
        Scanner input=new Scanner(System.in);
        do
        {
            System.out.println("Which letter grade schema would you like to use? (Please select one) \n(1)KPU's Default Letter Grade Schema "
                    + "\n(2)Custom Letter Grade Schema ");
            int userChoice=input.nextInt();
            if(userChoice==1)
            {
                choice=false;
                obj.calcStudentLetterGrade(weights);

            }
            else if (userChoice==2)
            {
                choice=false;
                obj1.calcStudentLetterGrade(weights);
            }
            else
            {
                choice=true;
                System.out.println("Invalid Choice");
            }
        }
        while(choice);

    }
}
