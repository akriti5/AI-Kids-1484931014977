package wasdev.sample.servlet;

import java.io.File;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class VSRecognition {

	public static void main(String[] args) {
		
		 VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
	        service.setApiKey("9e2192f009e24752d514a16d5e1152fe54417f65");

	       /* ClassifierOptions classifierOptions = new ClassifierOptions.Builder()
	        .classifierName("Shapes")
	        .addClass("Circle", new File("img/Circle.zip"))
	        .addClass("Triangle", new File("img/Triangle.zip"))
	        .negativeExamples(new File("img/Random.zip"))
	        .build();

	    VisualClassifier shapeClassifier = service.createClassifier(classifierOptions).execute();

	    ClassifyImagesOptions classifyOptions = new ClassifyImagesOptions.Builder()
	        .classifierIds(shapeClassifier.getId())
	        .images(new File ("img/cir2.png"))
	        .build();

	    VisualClassification result = service.classify(classifyOptions).execute();
	    System.out.println(result);*/

	        System.out.println("Classify using the shapeRecog classifier");
	        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().images(new File("img/test.png"))
	            .classifierIds("circle").build();
	        VisualClassification result = service.classify(options).execute();
	        System.out.println(result);
	        //System.out.println(result.getClass());

	        

	}

}
