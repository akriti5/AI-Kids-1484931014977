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
    	System.out.println(name);
    	response.setContentType("text/html");
        //response.getWriter().print(name);
        
              
       
        service.setUsernameAndPassword("e6f169f2-a91a-4744-bf09-4705c00bcdc4", "YA5EZAAXH65s");

        Classification classification = service.classify("ff18c7x157-nlc-5270", name).execute();
        response.getWriter().print(classification.getTopClass());
        
    }

}
