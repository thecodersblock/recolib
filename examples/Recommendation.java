package examples;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import recolib.Recolib;

public class Recommendation {

	public static void main(String[] args) throws IOException, TasteException {
		// TODO Auto-generated method stub
		String file_path = "data/ratings.dat";
		String split = "::";
		
		//String file_path = "data/paperData.csv";
		//String split = ",";
		
		
		DataModel m = new FileDataModel(new File(file_path), split);

		UserSimilarity us = Recolib.PearsonCorrelationSimilarityWithSVD(m);

		System.out.println(Recolib.evaluate(us, m, 0.8, 0.01) + "\nRecommeded items: ");
		Recolib.recommendAlgorithm(m, 1);

	}

}
