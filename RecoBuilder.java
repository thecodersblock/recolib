package reco2;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
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

public class RecoBuilder {

	public static RecommenderBuilder init(final UserSimilarity userSimilarity) {
		// TODO Auto-generated method stub
		
		RecommenderBuilder userSimRecBuilder = new RecommenderBuilder() {
               @Override
               public Recommender buildRecommender(DataModel model)throws TasteException
               {
             	  
                     int neighbourhoodSize = 6;
					/*The Neighborhood algorithms used in your recommender
                      not required if you are evaluating your item based recommendations*/
                     UserNeighborhood neighbourhood = new NearestNUserNeighborhood(neighbourhoodSize, userSimilarity, model);
                    
                     //Recommender used in your real time implementation
                     Recommender recommender = new GenericBooleanPrefUserBasedRecommender(model, neighbourhood, userSimilarity);
                     
                     return recommender;
               }
         };
		
		return userSimRecBuilder;
	}
	
	

}
