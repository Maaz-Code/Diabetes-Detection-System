package CS3;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class NaiveBayesRunner {
	public static void main(String args[]) throws IOException {
		Scanner file = new Scanner(
				new File(
						"C:/Users/A/Desktop/imp files/NaiveBayesSolver-to-predict-risk-of-Diabetes--master/NaiveBayesSolver-to-predict-risk-of-Diabetes--master/Training.dat"));
		Scanner fileD = new Scanner(
				new File(
						"C:/Users/A/Desktop/imp files/NaiveBayesSolver-to-predict-risk-of-Diabetes--master/NaiveBayesSolver-to-predict-risk-of-Diabetes--master/decission.dat"));
		String dString;
		String InputString;
		String keyword0 = "yes";
		String keyword1 = "no";

		int diabeticYes_Count = 0;
		int diabeticNo_Count = 0;

		int db_obese_Count = 0;
		int db_overweight_Count = 0;
		int db_normalweight_Count = 0;
		int db_underweight_Count = 0;
		int no_db_obese_Count = 0;
		int no_db_overweight_Count = 0;
		int no_db_normalweight_Count = 0;
		int no_db_underweight_Count = 0;

		int db_BShigh_Count = 0;
		int db_BSnormal_Count = 0;
		int db_BSlow_Count = 0;
		int no_db_BShigh_Count = 0;
		int no_db_BSnormal_Count = 0;
		int no_db_BSlow_Count = 0;

		int db_AChigh_Count = 0;
		int db_ACmoderate_Count = 0;
		int db_ACpoor_Count = 0;
		int no_db_AChigh_Count = 0;
		int no_db_ACmoderate_Count = 0;
		int no_db_ACpoor_Count = 0;

		float Prob_diabetic_Yes = 0;
		float Prob_diabetic_No = 0;

		float Pdiabetic_Yes_obese = 0;
		float Pdiabetic_Yes_overweight = 0;
		float Pdiabetic_Yes_normalweight = 0;
		float Pdiabetic_Yes_underweight = 0;
		float Pdiabetic_NO_obese = 0;
		float Pdiabetic_NO_overweight = 0;
		float Pdiabetic_NO_normalweight = 0;
		float Pdiabetic_NO_underweight = 0;

		float Pdiabetic_Yes_BShigh = 0;
		float Pdiabetic_Yes_BSnormal = 0;
		float Pdiabetic_Yes_BSlow = 0;
		float Pdiabetic_NO_BShigh = 0;
		float Pdiabetic_NO_BSnormal = 0;
		float Pdiabetic_NO_BSlow = 0;

		float Pdiabetic_Yes_AChigh = 0;
		float Pdiabetic_Yes_ACmoderate = 0;
		float Pdiabetic_Yes_ACpoor = 0;
		float Pdiabetic_NO_AChigh = 0;
		float Pdiabetic_NO_ACmoderate = 0;
		float Pdiabetic_NO_ACpoor = 0;

		NaiveBayesClassifer NB = new NaiveBayesClassifer();
		// Read each record to find weather it is classified as diabetic or not.
		// counting the number of records as diabetic classifiers yes or no
		// counting the number of attribute classifiers with in diabetic yes or no
		// classifier
		while (file.hasNextLine()) {
			InputString = file.nextLine();
			Boolean Yes_found = Arrays.asList(InputString.split(",")).contains(keyword0);
			if (Yes_found) {
				diabeticYes_Count++;
				String WeightClassfier = NB.findWeightClasffier(InputString);
				switch (WeightClassfier) {
					case "obese":
						db_obese_Count++;
						break;
					case "over":
						db_overweight_Count++;
						break;
					case "normal":
						db_normalweight_Count++;
						break;
					case "under":
						db_underweight_Count++;
						break;

				}
				String BlodSugarClasffier = NB.findBloodSugarClasffier(InputString);
				switch (BlodSugarClasffier) {
					case "BS_high":
						db_BShigh_Count++;
						break;
					case "BS_normal":
						db_BSnormal_Count++;
						break;
					case "BS_low":
						db_BSlow_Count++;
						break;
				}
				String ActivityClasffier = NB.findActivityClasffier(InputString);
				switch (ActivityClasffier) {
					case "AC_high":
						db_AChigh_Count++;
						break;
					case "AC_moderate":
						db_ACmoderate_Count++;
						break;
					case "AC_poor":
						db_ACpoor_Count++;
						break;
				}

			} else {
				Boolean No_found = Arrays.asList(InputString.split(",")).contains(keyword1);
				if (No_found) {
					diabeticNo_Count++;
					String WeightType = NB.findWeightClasffier(InputString);
					switch (WeightType) {
						case "obese":
							no_db_obese_Count++;
							break;
						case "over":
							no_db_overweight_Count++;
							break;
						case "normal":
							no_db_normalweight_Count++;
							break;
						case "under":
							no_db_underweight_Count++;
							break;
					}
					String BlodSugarClasffier = NB.findBloodSugarClasffier(InputString);
					switch (BlodSugarClasffier) {
						case "BS_high":
							no_db_BShigh_Count++;
							break;
						case "BS_normal":
							no_db_BSnormal_Count++;
							break;
						case "BS_low":
							no_db_BSlow_Count++;
							break;
					}
					String ActivityClasffier = NB.findActivityClasffier(InputString);
					switch (ActivityClasffier) {
						case "AC_high":
							no_db_AChigh_Count++;
							break;
						case "AC_moderate":
							no_db_ACmoderate_Count++;
							break;
						case "AC_poor":
							no_db_ACpoor_Count++;
							break;
					}

				}

			}

			// System.out.println(InputString);

		}
		file.close();
		int parameter_1 = 0;
		int parameter_2 = 0;
		int parameter_3 = 0;
		// Computing probabilities for main classifiers as well as attribute classifiers
		Prob_diabetic_Yes = NB.computeProbability_for_Clasffier(diabeticYes_Count, (diabeticYes_Count + diabeticNo_Count),
				parameter_3);
		Prob_diabetic_No = NB.computeProbability_for_Clasffier(diabeticNo_Count, (diabeticYes_Count + diabeticNo_Count),
				parameter_3);

		Pdiabetic_Yes_obese = NB.computeProbability_for_Clasffier(db_obese_Count, diabeticYes_Count, parameter_3);
		Pdiabetic_Yes_overweight = NB.computeProbability_for_Clasffier(db_overweight_Count, diabeticYes_Count,
				parameter_3);
		Pdiabetic_Yes_normalweight = NB.computeProbability_for_Clasffier(db_normalweight_Count, diabeticYes_Count,
				parameter_3);
		Pdiabetic_Yes_underweight = NB.computeProbability_for_Clasffier(db_underweight_Count, diabeticYes_Count,
				parameter_3);
		Pdiabetic_NO_obese = NB.computeProbability_for_Clasffier(db_obese_Count, diabeticNo_Count, parameter_3);
		Pdiabetic_NO_overweight = NB.computeProbability_for_Clasffier(db_overweight_Count, diabeticNo_Count,
				parameter_3);
		Pdiabetic_NO_normalweight = NB.computeProbability_for_Clasffier(db_normalweight_Count, diabeticNo_Count,
				parameter_3);
		Pdiabetic_NO_underweight = NB.computeProbability_for_Clasffier(db_underweight_Count, diabeticNo_Count,
				parameter_3);

		Pdiabetic_Yes_BShigh = NB.computeProbability_for_Clasffier(db_BShigh_Count, diabeticYes_Count, parameter_3);
		Pdiabetic_Yes_BSnormal = NB.computeProbability_for_Clasffier(db_BSnormal_Count, diabeticYes_Count,
				parameter_3);
		Pdiabetic_Yes_BSlow = NB.computeProbability_for_Clasffier(db_BSlow_Count, diabeticYes_Count, parameter_3);
		Pdiabetic_NO_BShigh = NB.computeProbability_for_Clasffier(no_db_BShigh_Count, diabeticYes_Count, parameter_3);
		Pdiabetic_NO_BSnormal = NB.computeProbability_for_Clasffier(no_db_BSnormal_Count, diabeticYes_Count,
				parameter_3);
		Pdiabetic_NO_BSlow = NB.computeProbability_for_Clasffier(no_db_BSlow_Count, diabeticYes_Count, parameter_3);

		Pdiabetic_Yes_AChigh = NB.computeProbability_for_Clasffier(no_db_AChigh_Count, diabeticYes_Count, parameter_3);
		Pdiabetic_Yes_ACmoderate = NB.computeProbability_for_Clasffier(db_ACmoderate_Count, diabeticYes_Count,
				parameter_3);
		Pdiabetic_Yes_ACpoor = NB.computeProbability_for_Clasffier(db_ACpoor_Count, diabeticYes_Count, parameter_3);
		Pdiabetic_NO_AChigh = NB.computeProbability_for_Clasffier(no_db_AChigh_Count, diabeticYes_Count, parameter_3);
		Pdiabetic_NO_ACmoderate = NB.computeProbability_for_Clasffier(no_db_ACmoderate_Count, diabeticYes_Count,
				parameter_3);
		Pdiabetic_NO_ACpoor = NB.computeProbability_for_Clasffier(no_db_ACpoor_Count, diabeticYes_Count, parameter_3);

		
		

		// Using bayes formulae compute the probability of each diabetic classifier (yes
		// or no) for the given attributes
		// which ever has greater probability will be as output of greater likelihood of
		// the scenario in near future.
		while (fileD.hasNextLine()) {
			float pWeight_Yes = 0;
			float pWeight_NO = 0;
			float pBS_Yes = 0;
			float pBS_NO = 0;
			float pAC_Yes = 0;
			float pAC_No = 0;

			dString = fileD.nextLine();
			String dWeightClassfier = NB.findWeightClasffier(dString);
			String dBlodSugarClasffier = NB.findBloodSugarClasffier(dString);
			String dActivityClasffier = NB.findActivityClasffier(dString);
			switch (dWeightClassfier) {
				case "obese":
					pWeight_Yes = Pdiabetic_Yes_obese;
					pWeight_NO = Pdiabetic_NO_obese;
					break;
				case "over":
					pWeight_Yes = Pdiabetic_Yes_overweight;
					pWeight_NO = Pdiabetic_NO_overweight;
					break;
				case "normal":
					pWeight_Yes = Pdiabetic_Yes_normalweight;
					pWeight_NO = Pdiabetic_NO_normalweight;
					break;
				case "under":
					pWeight_Yes = Pdiabetic_Yes_underweight;
					pWeight_NO = Pdiabetic_NO_underweight;
					break;
			}
			switch (dBlodSugarClasffier) {
				case "BS_high":
					pBS_Yes = Pdiabetic_Yes_BShigh;
					pBS_NO = Pdiabetic_NO_BShigh;
					break;
				case "BS_normal":
					pBS_Yes = Pdiabetic_Yes_BSnormal;
					pBS_NO = Pdiabetic_NO_BSnormal;
					break;
				case "BS_low":
					pBS_Yes = Pdiabetic_Yes_BSlow;
					pBS_NO = Pdiabetic_NO_BSlow;
					break;
			}
			switch (dActivityClasffier) {
				case "AC_high":
					pAC_Yes = Pdiabetic_Yes_AChigh;
					pAC_No = Pdiabetic_NO_AChigh;
					break;
				case "AC_moderate":
					pAC_Yes = Pdiabetic_Yes_ACmoderate;
					pAC_No = Pdiabetic_NO_ACmoderate;
					break;
				case "AC_poor":
					pAC_Yes = Pdiabetic_Yes_ACpoor;
					pAC_No = Pdiabetic_NO_ACpoor;
					break;
			}
			float yes_dnumber = pWeight_Yes * pBS_Yes * pAC_Yes * Prob_diabetic_Yes;
			float no_dnumber = pWeight_NO * pBS_NO * pAC_No * Prob_diabetic_No;
			System.out.println(" ");
			System.out.println(" ");

			if (yes_dnumber > no_dnumber) {
				System.out.println("Input record by the user with the following details :" );
				System.out.println(dString);
				System.out.println(" ");
				System.out.println("Result: ");
				System.out.println("The patient has HIGH likelihood of developing diabetes");
						
						
			} else {
				System.out.println("Input record by the user with the following details :" );
				System.out.println(dString);
				System.out.println(" ");
				System.out.println("Result: ");
				System.out.println("The patient is not diabetic and has LOW likelihood of developing diabetes in the future");
						
			}
			
			
		
			System.out.println(" ");
		
		}
		fileD.close();
	}
}
