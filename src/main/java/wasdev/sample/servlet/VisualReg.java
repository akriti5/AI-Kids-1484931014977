package wasdev.sample.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.codec.binary.Base64;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

/**
 * Servlet implementation class VisualReg
 */
@WebServlet("/VisualReg")
public class VisualReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("9e2192f009e24752d514a16d5e1152fe54417f65");
        
        String filename=request.getParameter("name");
        
        File directory = new File("C://Users//akriti//Downloads//"+filename);
        
        
        System.out.println("Classify using the shapeRecog classifier");
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().images(directory)
            .classifierIds("circle").build();
        VisualClassification result = service.classify(options).execute();
        
       
        System.out.println(result);
        String imgCls=result.getImages().get(0).getClassifiers().get(0).getClasses().get(0).getName();
        System.out.println(imgCls);
        
        
        response.getWriter().print(imgCls);
        
       

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
}
