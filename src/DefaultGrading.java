import java.text.DecimalFormat;

public class DefaultGrading extends GradeCalculation{
    private static final DecimalFormat formatNum = new DecimalFormat("0.00");
    public double[] calcGradeWeightages() {
        //Jodhveer's Code

        double[] GradeWeights = new double[super.NumOfAssessments];

        for (int i = 0; i < super.NumOfAssessments; i++) {
            GradeWeights[i] = 20;

        }

        return GradeWeights;

    }

    public void calcStudentLetterGrade(double[]GradeWeights) {
        // TODO Auto-generated method stub
        UserInput obj=new UserInput();
        int j =obj.readFile("src\\grades.txt");
        double percent;
        int i=0;
        System.out.println("\n\n***************************GRADE RESULTS***************************" );
        System.out.println("*******************Using KPU's Default Schema**********************\n" );
        System.out.println("StudentNo.\tFirstName\tLastName\tTotal%\tLetterGrade" );
        System.out.println("--------------------------------------------------------------------" );
        super.myList.add("\n\n***************************GRADE RESULTS***************************");
        super.myList.add("StudentNo.\tFirstName\tLastName\tTotal%\tLetterGrade" );
        super.myList.add("--------------------------------------------------------------------" );
        while(i<j){
            percent=(GradeWeights[0]*obj.getQuiz1(i)/100)+(GradeWeights[1]*obj.getQuiz2(i)/100)+(GradeWeights[2]*obj.getMidterm(i)/100)
                    +(GradeWeights[3]*obj.getAssignment(i)/100)+(GradeWeights[4]*obj.getFinalExam(i)/100);
            obj.setPercentage(percent);


            if(percent<=100 &&percent>=90)
            {
                super.setLetterGrade("A+");
            }
            else if(percent<=89 &&percent>=85)
            {
                super.setLetterGrade("A");
            }
            else if(percent<=84 &&percent>=80)
            {
                super.setLetterGrade("A-");
            }
            else if(percent<=79 &&percent>=76)
            {
                super.setLetterGrade("B+");
            }
            else if(percent<=75 &&percent>=72)
            {
                super.setLetterGrade("B");
            }
            else if(percent<=71 &&percent>=68)
            {
                super.setLetterGrade("B-");
            }
            else if(percent<=67 &&percent>=64)
            {
                super.setLetterGrade("C+");
            }
            else if(percent<=63 &&percent>=60)
            {
                super.setLetterGrade("C");
            }
            else if(percent<=59 &&percent>=56)
            {
                super.setLetterGrade("C-");
            }
            else if(percent<=55 &&percent>=50)
            {
                super.setLetterGrade("D");
            }
            else if(percent<=49)
            {
                super.setLetterGrade("F");
            }
            else
            {
                System.out.println("Invalid Input");
            }
            super.myList.add(obj.getStudentNumber(i)+"\t"+obj.getFirstName(i)+ "\t"+ obj.getLastName(i)
                    +"\t\t"+ formatNum.format(obj.getPercentage(i))+ "\t   "+super.getLetterGrade() );

            System.out.println(obj.getStudentNumber(i)+"\t"+obj.getFirstName(i)+ "\t"+ obj.getLastName(i)
                    +"\t\t" + formatNum.format(obj.getPercentage(i))+ "\t   "+super.getLetterGrade() );
            i++;
        }

        super.printGrades();

    }


}
