package wasdev.sample.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.ibm.watson.developer_cloud.util.CredentialUtils;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private  NaturalLanguageClassifier service = new NaturalLanguageClassifier();
    
    
    
   
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     * 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String name = request.getParameter("name");
    	String imgCls="";
    	System.out.println(name);
    	response.setContentType("text/html");
       
        
              
       
        service.setUsernameAndPassword("e6f169f2-a91a-4744-bf09-4705c00bcdc4", "YA5EZAAXH65s");

        Classification classification = service.classify("cede31x166-nlc-79", name).execute();
        if(classification.getTopClass().equalsIgnoreCase("Boy"))
        	imgCls="spider";
        else
        	imgCls="elsa";
        
        response.getWriter().print(imgCls);
        
       
        
    }

}
