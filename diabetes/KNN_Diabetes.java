
package diabetes;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

class KNN {

    // declaring dataset variables "independent"
    float Glucose, Pregnancies, Blood_Pressure, Skin_Thickness, Insulin, BMI, Diabetes_Pedigree_Func, age;
    // declaring function variable "dependent"
    float Outcome;
    double dist;

    // Constructor for class KNN
    KNN(String Pregnancies, String Glucose, String Blood_Pressure, String Skin_Thickness, String Insulin, String BMI,
            String Diabetes_Pedigree_Func, String age, String val) {
        this.Pregnancies = Float.parseFloat(Pregnancies);
        this.Glucose = Float.parseFloat(Glucose);
        this.Blood_Pressure = Float.parseFloat(Blood_Pressure);
        this.Skin_Thickness = Float.parseFloat(Skin_Thickness);
        this.Insulin = Float.parseFloat(Insulin);
        this.BMI = Float.parseFloat(BMI);
        this.Diabetes_Pedigree_Func = Float.parseFloat(Diabetes_Pedigree_Func);
        this.age = Float.parseFloat(age);
        this.Outcome = Float.parseFloat(val);

    }

    // function to calculate euclidean distance
    void calc_distance(KNN testData) {
        this.dist = Math.sqrt(
                Math.pow((this.Pregnancies - testData.Pregnancies), 2) + Math.pow((this.Glucose - testData.Glucose), 2)
                        + Math.pow((this.Blood_Pressure - testData.Blood_Pressure), 2)
                        + Math.pow((this.Skin_Thickness - testData.Skin_Thickness), 2)
                        + Math.pow((this.Insulin - testData.Insulin), 2) + Math.pow((this.BMI - testData.BMI), 2)
                        + Math.pow((this.Diabetes_Pedigree_Func - testData.Diabetes_Pedigree_Func), 2)
                        + Math.pow((this.age - testData.age), 2));
    }
}

// comparator to compare distance
class KnnComparator implements Comparator<KNN> {

    @Override
    public int compare(KNN o1, KNN o2) {
        if (o1.dist == o2.dist)
            return 0;
        else if (o1.dist > o2.dist)
            return 1;
        else
            return -1;
    }

}

// main class
public class KNN_Diabetes {

    public static void main(String[] args) throws IOException {
        ArrayList<KNN> trainData = new ArrayList<>();
        ArrayList<KNN> testData = new ArrayList<>();
        ArrayList<KNN> sortedTrainData;
        File file = new File("C:/Users/HP/Desktop/KNN/pima_standardized.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        LineNumberReader lr = new LineNumberReader(new FileReader(file));
        String line = null;
        int true_Positive = 0, true_Negative = 0, false_Positive = 0, false_Negative = 0;

        while (lr.getLineNumber() < 500) {
            line = lr.readLine();
            String vals[] = line.split(",");
            KNN temp = new KNN(vals[0], vals[1], vals[2], vals[3], vals[4], vals[5], vals[6], vals[7], vals[8]);
            trainData.add(temp);
        }

        while ((line = lr.readLine()) != null) {
            String vals[] = line.split(",");
            KNN temp = new KNN(vals[0], vals[1], vals[2], vals[3], vals[4], vals[5], vals[6], vals[7], vals[8]);
            testData.add(temp);
        }
        br.close();
        lr.close();
        for (int i = 0; i < testData.size(); i++) {
            KNN test1 = testData.get(i);
            for (KNN temp : trainData) {
                temp.calc_distance(test1);
                // System.out.println(temp.dist);
            }
            sortedTrainData = new ArrayList<>(trainData);
            sortedTrainData.sort(new KnnComparator());

            int k = 35;

            int pos = 0, neg = 0;
            double result = 2.0;

            for (int j = 0; j < k; j++) {
                double val = sortedTrainData.get(j).Outcome;
                if (val == 0.0)
                    neg++;
                else
                    pos++;
            }

            if (neg > pos)
                result = 0.0;
            else if (pos > neg)
                result = 1.0;

            if (result == test1.Outcome) {
                if (result == 1.0)
                    true_Positive++;
                else
                    true_Negative++;
            } else {
                if (result == 1.0)
                    false_Positive++;
                else
                    false_Negative++;

            }
        }

        System.out.println("_________________________________________\n");
        double accuracy = ((double) (true_Positive + true_Negative)
                / (true_Positive + true_Negative + false_Positive + false_Negative)) * 100;
        System.out.println("TruePositive:" + true_Positive + "  FalseNegative:" + false_Negative);
        System.out.println("FalsePositive:" + false_Positive + "  TrueNegative:" + true_Negative);
        System.out.println("\nAccuracy: " + accuracy);

    }
}
