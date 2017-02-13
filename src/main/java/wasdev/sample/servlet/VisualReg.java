package wasdev.sample.servlet;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier.VisualClass;

/**
 * Servlet implementation class VisualReg
 */
@WebServlet("/VisualReg")
public class VisualReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MySqlAccess sqlDao;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualReg() {
        super();
        sqlDao=new MySqlAccess();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		Map<String,Double> vsRegDetails=new HashMap<String,Double>();
		
		String name=LoginNameToken.getInstance().getName();
		int id=sqlDao.getLoginData(name);
		
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("9e2192f009e24752d514a16d5e1152fe54417f65");
        
        String filename=request.getParameter("name");
        String imgCls="";
        double score=0.0;
        
        File directory = new File("C://Users//akriti//Downloads//"+filename);
        
        
        System.out.println("Classify using the shapeRecog classifier");
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().images(directory)
            .classifierIds("Shapes_1907709014").build();
        VisualClassification result = service.classify(options).execute();
        
       
        System.out.println(result);
        List<VisualClassifier> classifiers= result.getImages().get(0).getClassifiers();
        
        if(classifiers.size()>0){
        	VisualClassifier classifier=classifiers.get(0);
        	
        	for(VisualClass vClass:classifier.getClasses()){
        		
        		imgCls=vClass.getName();
        		score=vClass.getScore();
        		vsRegDetails.put(imgCls, score);
        		
        	}
        }
        else{
        	imgCls="No class";
        	vsRegDetails.put("Wrong class", 0.0);
        }

        System.out.println(imgCls);
       
        sqlDao.enterVisualData(id, vsRegDetails);
        String clasNm=sqlDao.getLoginDataCls(name);
        
        response.getWriter().print(imgCls+","+clasNm);
        
       

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
}
