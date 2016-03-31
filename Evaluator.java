package reco2;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

public class Evaluator {

	public static void init(String file_path, String split, double evaluate_percent) throws IOException, TasteException {
		// TODO Auto-generated method stub
		
		final int algo_size = 4;
		
		String algo_name[] = new String[algo_size];
		
		algo_name[0] = "TanimotoCoefficientSimilarity";
		algo_name[1] = "PearsonCorrelationSimilarity";
		algo_name[2] = "LogLikelihoodSimilarity";
		algo_name[3] = "EuclideanDistanceSimilarity"; 
		
		
		DataModel model = new FileDataModel(new File(file_path), split);
		
		UserSimilarity userSimilarity[] = new UserSimilarity[algo_size];
 	  	userSimilarity[0] = new TanimotoCoefficientSimilarity(model); //3.4, medium
 	  	userSimilarity[1] = new PearsonCorrelationSimilarity(model); //2.85, fast
 	  	userSimilarity[2] = new LogLikelihoodSimilarity(model); //1.98, slowwwwwww
 	  	userSimilarity[3] = new EuclideanDistanceSimilarity(model); // 3.04, fast
		
 	  	
		
        RecommenderBuilder userSimRecBuilder[] = new RecommenderBuilder[algo_size];
         
        for(int i=0;i<algo_size;i++)
        {
        	 userSimRecBuilder[i] = RecoBuilder.init(userSimilarity[i]);
        	 
        	 try {
                 
                 //Use this only if the code is for unit tests and other examples to guarantee repeated results
                 RandomUtils.useTestSeed();
                
                 //Creating a data model to be passed on to RecommenderEvaluator - evaluate method
                 FileDataModel dataModel = new FileDataModel(new File(file_path), split);
                
                 /*Creating an RecommenderEvaluator to get the evaluation done
                 you can use AverageAbsoluteDifferenceRecommenderEvaluator() as well*/
                 RMSRecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
                
                 //for obtaining User Similarity Evaluation Score
                 double userSimEvaluationScore = evaluator.evaluate(userSimRecBuilder[i],null,dataModel, 0.8, evaluate_percent);//0.05);
                 
                 Writer.write( algo_name[i] + ": \t" + userSimEvaluationScore);
                                              
        	 } catch (IOException e) {
                 // TODO Auto-generated catch block
           	
                 e.printStackTrace();
        	 } catch (TasteException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
        	 }
        }
         
        
         
		
		
	}
	
	

}
