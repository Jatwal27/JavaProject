
import java.text.DecimalFormat;
import java.util.*;

public class CustomGrading extends GradeCalculation {
    private static final DecimalFormat formatNum = new DecimalFormat("0.00");
    //method calcGradeWeightages
    public double[] calcGradeWeightages() {
        // TODO Auto-generated method stub
        //Jodhveer's Code

        Scanner UserIn = new Scanner(System.in);
        ArrayList<String> AssessmentNames = new ArrayList<String>();
        AssessmentNames.add("Quiz 1");
        AssessmentNames.add("Quiz 2");
        AssessmentNames.add("The Midterm");
        AssessmentNames.add("The Assignment");
        AssessmentNames.add("The Final");
        double[] GradeWeights = new double[super.NumOfAssessments];
        boolean TotalError = false,ChoiceError = false;
        do {
            double arraySum = 0;
            for (int i = 0; i < super.NumOfAssessments; i++) {
                System.out.println("Enter the weight of " + AssessmentNames.get(i)+":");
                GradeWeights[i] = UserIn.nextDouble();
                arraySum += GradeWeights[i];
                ChoiceError = false;
            }
            if (arraySum != 100) {
                System.out.println("Error, the Assessment weights do not add up to 100%!\n");
                TotalError = true;
            }
            if (arraySum == 100) {
                TotalError = false;
            }
        } while (TotalError);
        return GradeWeights;

    }

    //method calcStudentLetterGrade
    public void calcStudentLetterGrade(double[] GradeWeights) {


        ArrayList<String> customLetterGrade=new ArrayList<String>();
        ArrayList<Double> customPercentMin=new ArrayList<Double>();
        customPercentMin.add(100.0);
        UserInput obj=new UserInput();
        int j =obj.readFile("src\\grades.txt");
        int i=0;double percent;

        Scanner input = new Scanner(System.in);
        String userInput;
        char choice;
        int var=0;
        double minPercent;
        //start of loop
        do {

            System.out.println("Please enter letter grade name:");
            userInput=input.next();
            customLetterGrade.add(userInput);
            System.out.println("Please enter minimum percentage a student "
                    + "would need to have to get this grade (should be less than or equal to 100%): ");

            minPercent=input.nextDouble();

            if(minPercent<=100&&minPercent>=0)
            {
                if(minPercent<customPercentMin.get(var))
                {
                    customPercentMin.add(minPercent);

                }
                else {
                    do {

                        System.out.println("Invalid Percentage!! Please enter a percentage smaller than the last one you entered ");
                        minPercent=input.nextDouble();
                    }
                    while(minPercent>=customPercentMin.get(var));
                    customPercentMin.add(minPercent);
                }

            }
            else
            {
                do
                {
                    System.out.println("Invalid percentage");
                    System.out.println("Please enter minimum percentage a student "
                            + "would need to have to get this grade (should be <=100% and >=0): ");
                    minPercent=input.nextDouble();

                }while(!(minPercent<=100&&minPercent>=0));
                customPercentMin.add(minPercent);
            }


            System.out.println("Would you like to enter another grade? (Y/N)" );
            choice=input.next().charAt(0);
            var++;
        }
        while (choice!='n'&&choice!='N');
        //end of loop


        System.out.println("\n\n***************************GRADE RESULTS***************************" );
        System.out.println("StudentNo.\tFirstName\tLastName\tTotal%\tLetterGrade" );
        System.out.println("--------------------------------------------------------------------" );
        super.myList.add("\n\n***************************GRADE RESULTS***************************");
        super.myList.add("StudentNo.\tFirstName\tLastName\tTotal%\tLetterGrade" );
        super.myList.add("--------------------------------------------------------------------" );

        while(i<j){
            // System.out.println(i+" < "+j);
            percent=(GradeWeights[0]*obj.getQuiz1(i)/100)+(GradeWeights[1]*obj.getQuiz2(i)/100)+(GradeWeights[2]*obj.getMidterm(i)/100)
                    +(GradeWeights[3]*obj.getAssignment(i)/100)+(GradeWeights[4]*obj.getFinalExam(i)/100);

            //saving student total percentages
            obj.setPercentage(percent);
            // System.out.println(obj.getPercentage(i));

            for (int h = 1; h < customPercentMin.size(); h++) {

                if (obj.getPercentage(i) <= customPercentMin.get(h-1) && obj.getPercentage(i) > customPercentMin.get(h))
                {
                    super.myList.add(obj.getStudentNumber(i)+"\t"+obj.getFirstName(i)+ "\t"+ obj.getLastName(i)
                            +"\t\t"+ formatNum.format(obj.getPercentage(i))+ "\t"+ customLetterGrade.get(h-1));

                    System.out.println(obj.getStudentNumber(i)+"\t"+obj.getFirstName(i)+ "\t"+ obj.getLastName(i)
                            +"\t\t"+ formatNum.format(obj.getPercentage(i))+ "\t"+ customLetterGrade.get(h-1));
                }
                else if(customPercentMin.get((customPercentMin.size())-1)>obj.getPercentage(i))
                {
                    super.myList.add(obj.getStudentNumber(i)+"\t"+obj.getFirstName(i)+ "\t"+ obj.getLastName(i)
                            +"\t\t"+ formatNum.format(obj.getPercentage(i))+ "\tNot-Provided");

                    System.out.println(obj.getStudentNumber(i)+"\t"+obj.getFirstName(i)+ "\t"+ obj.getLastName(i)
                            +"\t\t"+ formatNum.format(obj.getPercentage(i))+ "\tNot-Provided");
                    continue;
                }
            }


            i++;
        }
        super.printGrades();

    }

}
