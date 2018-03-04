package com.davidcampelo.ai;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Simple recommender using example from MAHOUT
 *
 */
public class SampleRecommender 
{
    public static void main( String[] args ) throws IOException, TasteException
    {
		File file = new File("src/main/resources/data.csv");
		DataModel dataModel = new FileDataModel(file);
        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
        
        ThresholdUserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);
        
        UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
        
        List<RecommendedItem> list = recommender.recommend(2, 3);
        for (RecommendedItem item : list) {
        	System.out.println(item);
		}
    }
}
