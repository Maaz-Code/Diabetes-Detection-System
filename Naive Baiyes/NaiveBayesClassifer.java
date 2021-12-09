package CS3;

import java.util.Arrays;
/*
 * Objective: The goal of this particular program is to usea a machine learning algorithm ( Naive Bayes) to predict whether a given patient specifically a child below the age of 18, is at risk for Diabetes or not.
* The Naive Bayes Classifier algorithm uses training data to analyze the Main Classifier of Diabetes and the Attribute Classifiers of the data 
* (weight, blood sugar, physical activity) in order to predict a patient's risk for diabetes. 
* 
* For example if, input: (under,BS_high,AC_high) underweight, high blood sugar levels, high activity levels
*                 output: The patient has HIGH likelihood of developing diabetes in the near future 
*/
public class NaiveBayesClassifer {
	String inputstring;
	String findWeightClasffier (String inputstring) {
		String ob = "obese";
		String ov = "over";
		String nor = "normal";
		String uw  = "under";
		String nf = "nodata";
		
		Boolean Yes_ob = Arrays.asList(inputstring.split(",")).contains(ob);
		if(Yes_ob){
			return ob;
		}
		Boolean Yes_ov = Arrays.asList(inputstring.split(",")).contains(ov);
		if(Yes_ov){
			return ov;
		}
		Boolean Yes_nor = Arrays.asList(inputstring.split(",")).contains(nor);
		if(Yes_nor){
			return nor;
		}
		Boolean Yes_uw = Arrays.asList(inputstring.split(",")).contains(uw);
		if(Yes_uw){
			return uw;
		}
		return nf;
				
	}
	String findBloodSugarClasffier (String inputstring) {
		String bshi = "BS_high";
		String bsnor = "BS_normal";
		String bslow  = "BS_low";
		String  bsnf = "nodata";
		
		Boolean Yes_bshi = Arrays.asList(inputstring.split(",")).contains(bshi);
		if(Yes_bshi){
			return bshi;
		}
		
		Boolean Yes_bsnor = Arrays.asList(inputstring.split(",")).contains(bsnor);
		if(Yes_bsnor){
			return bsnor;
		}
		Boolean Yes_bslow = Arrays.asList(inputstring.split(",")).contains(bslow);
		if(Yes_bslow){
			return bslow;
		}
		return bsnf;
				
	}
	String findActivityClasffier (String inputstring) {
		String achi = "AC_high";
		String acmod = "AC_moderate";
		String acpoor  = "AC_poor";
		String  acnf = "nodata";
		
		Boolean Yes_achi = Arrays.asList(inputstring.split(",")).contains(achi);
		if(Yes_achi){
			return achi;
		}
		
		Boolean Yes_acmod = Arrays.asList(inputstring.split(",")).contains(acmod);
		if(Yes_acmod){
			return acmod;
		}
		Boolean Yes_acpoor = Arrays.asList(inputstring.split(",")).contains(acpoor);
		if(Yes_acpoor){
			return acpoor;
		}
		return acnf;
				
	}
	float computeProbability_for_Clasffier(int parameter_1, int intparameter_2,int parameter_3) {
		float Probability_for_Clasffier = 0 ;
		Probability_for_Clasffier = (float) parameter_1 / (float) intparameter_2 ;
		return Probability_for_Clasffier;
	}
	
	}
	

