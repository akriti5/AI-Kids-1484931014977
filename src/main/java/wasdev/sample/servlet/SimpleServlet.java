package wasdev.sample.servlet;

import java.io.File;
import java.io.IOException;

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
        response.setContentType("text/html");
        response.getWriter().print("HelloAk World!\t");
        
       
        service.setUsernameAndPassword("3473e18e-53f7-4bd0-85be-b706e1a1f7a7", "xUmrc2iYQLrR");

        Classification classification = service.classify("ff1b44x158-nlc-5055", "bulldog").execute();
        response.getWriter().print(classification);
        System.out.println(classification.getText());
        //classification
    }

}
