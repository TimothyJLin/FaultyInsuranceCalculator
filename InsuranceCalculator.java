import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
public class InsuranceCalculator {
    public static void main(String args[]) {
        InsuranceCalculator ic=new InsuranceCalculator();

        Scanner scanner=new Scanner(System.in);
        System.out.println("Input premium: ");
        double premium=scanner.nextDouble();

        System.out.println("Input savings: ");
        double savings=scanner.nextDouble();

        System.out.println("Input years: ");
        double years=scanner.nextDouble();

        System.out.println("Input interest(in decimal, for example: 5% is 0.05): ");
        double interest=scanner.nextDouble();

        System.out.println("how many risk factors: ");
        int numberOfRiskFactors=scanner.nextInt();
        System.out.println("number of risk factors "+numberOfRiskFactors);
        double[][] riskFactorArray=new double[numberOfRiskFactors][2];
        

        for (int i=0; i<numberOfRiskFactors; i++) {
            System.out.println("Expected number of years before risk "+(i+1));
            double yearsBetweenRisk=scanner.nextDouble();
            System.out.println("What is the monetary damage of your risk "+(i+1));
            double magnitudeOfRisk=scanner.nextDouble();
            riskFactorArray[i][0]=yearsBetweenRisk;
            riskFactorArray[i][1]=magnitudeOfRisk;
        }

        System.out.println("Without insurance: "+ic.withoutInsurance(riskFactorArray, savings, interest, years));
        System.out.println("With insurance: "+ic.withInsurance(premium, savings, interest, years));

        /** 
        System.out.println("Input out of pocket cost: ");
        double outOfPocketCost=scanner.nextDouble();

        System.out.println("Input deductibles: ");
        double input=scanner.nextDouble();
        */

    }
    
    public double withoutInsurance(double[][] riskFactorArray, double savings, double interest, double years) {
        double disaster=0;
        for (int i=1; i<years+1; i++) {
            for (int j=0; j<riskFactorArray.length; j++) {
                if (i%riskFactorArray[j][0]==0) { //if the number of years is a multiple of the yearsBetweenRisk
                    disaster+=riskFactorArray[j][1];
                }
            }
            savings=(savings*interest+savings)-disaster;
//            System.out.println("savings without insurance: "+savings);
            disaster=0;
        }
        return savings;
    }

    public double withInsurance(double premium, double savings, double interest, double years) {
        for (int i=0; i<years; i++) {
            savings=(savings*interest+savings)-premium;
           // System.out.println("savings with insurance: "+savings);

        }
        return savings;
    }

}
