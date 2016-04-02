package recolib;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

import reco2.RecoBuilder;
import reco2.Writer;

public class Recolib {

	public static UserSimilarity TanimotoCoefficientSimilarityWithSVD(DataModel model) throws IOException, TasteException {

		DataModel SVDModel = SVD.getU(ModelToMatrix.init(model));

		UserSimilarity us = new TanimotoCoefficientSimilarity(SVDModel);
		return us;
	}

	public static UserSimilarity PearsonCorrelationSimilarityWithSVD(DataModel model)
			throws TasteException, IOException {

		DataModel SVDModel = SVD.getU(ModelToMatrix.init(model));

		UserSimilarity us = new PearsonCorrelationSimilarity(SVDModel);
		return us;
	}

	public static UserSimilarity LogLikelihoodSimilarityWithSVD(DataModel model) throws IOException, TasteException {

		DataModel SVDModel = SVD.getU(ModelToMatrix.init(model));

		UserSimilarity us = new LogLikelihoodSimilarity(SVDModel);
		return us;
	}

	public static UserSimilarity EuclideanDistanceSimilarityWithSVD(DataModel model) throws TasteException, IOException {

		DataModel SVDModel = SVD.getU(ModelToMatrix.init(model));

		UserSimilarity us = new EuclideanDistanceSimilarity(SVDModel);
		return us;
	}

	public static void recommendAlgorithm(DataModel model, int userID) throws TasteException, IOException {

		UserSimilarity similarity = new PearsonCorrelationSimilarity(model); // PearsonCorrelationSimilarityWithSVD(model);
		// DataModel SVDModel = model;//
		// ParallelSVD.getU(ModelToMatrix.init(model));

		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

		long[] neighbors = neighborhood.getUserNeighborhood(userID);

		System.out.println("Neighbours are: " + neighbors.length);
		for (int i = 0; i < neighbors.length; i++)
			System.out.print(neighbors[i] + ", ");

		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(userID, 3);

		System.out.println("Recommendations are: ");
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}

	}

	public static double evaluate(UserSimilarity userSimilarity, DataModel dataModel, double trainingPercentage,
			double evaluationPercentage) {

		RecommenderBuilder userSimRecBuilder;
		double userSimEvaluationScore = -1;

		userSimRecBuilder = RecoBuilder.init(userSimilarity);

		// Use this only if the code is for unit tests and other examples to
		// guarantee repeated results
		RandomUtils.useTestSeed();

		// Creating a data model to be passed on to RecommenderEvaluator -
		// evaluate method
		// FileDataModel dataModel = new FileDataModel(new File(file_path),
		// split);

		/*
		 * Creating an RecommenderEvaluator to get the evaluation done you can
		 * use AverageAbsoluteDifferenceRecommenderEvaluator() as well
		 */
		RMSRecommenderEvaluator evaluator = new RMSRecommenderEvaluator();

		// for obtaining User Similarity Evaluation Score
		try {
			userSimEvaluationScore = evaluator.evaluate(userSimRecBuilder, null, dataModel, trainingPercentage,
					evaluationPercentage);
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userSimEvaluationScore;

	}

}
