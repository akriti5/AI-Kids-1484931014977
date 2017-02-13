package wasdev.sample.servlet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.ClassifiedClass;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier.VisualClass;

public class VSRecognition {

	public static void main(String[] args) {
		
		/* VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
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
	        
	       /* VisualClassifier visual= service.getClassifier("Shapes_1907709014").execute();
	        
	        
	        
	        File directory = new File("C://Users//akriti//Downloads//circle6.png");
	        
	        
	        System.out.println("Classify using the shapeRecog classifier");
	        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().images(directory)
	            .classifierIds(visual.getId()).build();
	        VisualClassification result = service.classify(options).execute();
	        
	        List<VisualClassifier> classifiers= result.getImages().get(0).getClassifiers();
	        
	        if(classifiers.size()>0){
	        	VisualClassifier classifier=classifiers.get(0);
	        	
	        	for(VisualClass vClass:classifier.getClasses()){
	        		
	        		System.out.println(vClass.getName());
	        		System.out.println(vClass.getScore());
	        		
	        		
	        	}
	        }
	        
	        
	        //System.out.println(result.getImages().get(0).getClassifiers().get(0).getClasses().get(0).getName());
	        System.out.println(result);*/
		
		NaturalLanguageClassifier service=new NaturalLanguageClassifier();
    

		String listOne = "lamp",
				l1TopCls="";
		Map<String,Double> l1ClsDetails=new HashMap<String,Double>();
				
		
		String listTwo = "naturel", 
			   l2TopCls="";
		Map<String,Double> l2ClsDetails=new HashMap<String,Double>();
		
		
		int resLstOne=0,resLstTwo=1;
		String result="";
    	        
       
        service.setUsernameAndPassword("3d3d4e9e-9479-4efe-b6da-489eb1b41843", "sGDN6YVuNTBS");

        Classification classification = service.classify("f5b42ex171-nlc-3507", listOne).execute();        
        l1TopCls =classification.getTopClass();
        if(l1TopCls.equalsIgnoreCase("pos"))
        	resLstOne=1;        
        for(ClassifiedClass clsClass:classification.getClasses())
        {
        	l1ClsDetails.put(clsClass.getName(),clsClass.getConfidence());
        }
        
        classification = service.classify("f5b42ex171-nlc-3507", listTwo).execute();
        l2TopCls =classification.getTopClass();
        if(l2TopCls.equalsIgnoreCase("pos"))
        	resLstTwo=1;  
        for(ClassifiedClass clsClass:classification.getClasses())
        {
        	l2ClsDetails.put(clsClass.getName(),clsClass.getConfidence());
        }
           
        
        if(resLstOne==1 && resLstTwo==1 )
        	result="Correct Pair";
        else
        	result="Wrong Pair";
        
        //response.getWriter().print(result);

	}

}
