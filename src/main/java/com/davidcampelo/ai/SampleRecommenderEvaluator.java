package com.davidcampelo.ai;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;



public class SampleRecommenderEvaluator {
	public static void main(String[] args) throws IOException {
		
		RandomUtils.useTestSeed();
		
        
        RecommenderEvaluator recommenderEvaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

		File file = new File("src/main/resources/data.csv");
		DataModel dataModel = new FileDataModel(file);
        		
        double error = -1;
		try {
			error = recommenderEvaluator.evaluate(new RecommenderBuilder() {
				
				public Recommender buildRecommender(DataModel dataModel) throws TasteException {

					UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
			        
			        ThresholdUserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);
			        
			        UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
			        
			        return recommender;
				}
			}, null, dataModel, 0.9, 1.0);
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(error);
	}

}
