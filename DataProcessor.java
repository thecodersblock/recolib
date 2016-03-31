package reco2;

import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;

public class DataProcessor {

	public static void init(String file_path, String split, double evaluate_percent ) throws IOException, TasteException {
		// TODO Auto-generated method stub
		
		Writer.write("User Similarity Evaluation scores (without SVD):\n ");
		Evaluator.init(file_path, split, evaluate_percent);
		
		Writer.write("User Similarity Evaluation scores (with SVD):\n ");
		String u_file_path = SVD.getUMat(file_path, split, evaluate_percent);
		Evaluator.init(u_file_path, ",", evaluate_percent);
		
	}
	
	

}
