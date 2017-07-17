

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Servlet implementation class weainfo
 */
@WebServlet("/weainfo")
public class weainfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public weainfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String city = request.getParameter("city");
		String allData = "<h4>"+"<font color=#AE0000>"+"here is weather forecast ablout "+city+"</font>"+"</h4>"+"<br>";
		String key = "6c5a2a8a28314f13592ccfbd03bcf049";
		String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&mode=xml&appid=" + key;
		System.out.println(url);
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
				
		try{
			HttpEntity entity1 = response1.getEntity();
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read(url);
			Element root = doc.getRootElement(); //root is weatherdata
			System.out.println("root is "+root.getName());
			
			Element foo = null;

			for(Iterator i = root.elementIterator();i.hasNext();){ //first child node
				foo = (Element)i.next();
//				System.out.println(foo.getName() + "-1");
				for(Iterator j =foo.elementIterator();j.hasNext();){ //second child node
					Element foo2 = (Element)j.next();
					if(foo2.getName().equals("time")){
//					System.out.println(foo2.getName() +"-2");
					}
					for(Iterator k = foo2.attributeIterator();k.hasNext();){//print time from-to
						Attribute attribute = (Attribute)k.next();
						if(foo2.getName().equals("time")){ 
						System.out.println(attribute.getName()+"--"+attribute.getValue());
						allData = allData +"<font color=#000000>"+"&nbsp;&nbsp;"+attribute.getName()+" "+attribute.getValue()+"</font>";
						}
						for(Iterator t = foo2.elementIterator();t.hasNext();){ //get third child node
							Element foo3 = (Element) t.next();
							if(foo3.getName().equals("temperature")){
								System.out.println(foo3.getName());
//								allData = allData +"<h4>temperature</h4>";
							}	
							for(Iterator m = foo3.attributeIterator();m.hasNext();){//print attribute of third child								
								Attribute att = (Attribute)m.next();
								if(foo3.getName().equals("temperature") && attribute.getName().equals("to")){
								System.out.println(att.getName()+"----"+att.getValue());
										if(att.getName().equals("min")){
											allData = allData +"&nbsp;&nbsp;&nbsp;"+ "<font color=#BB3D00>"+"Minimum temperature: "+att.getValue()+" kelvin"+"</font>";
										}
										if(att.getName().equals("max")){
											allData = allData + "<font color=#BB3D00>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"Maximum temperature: "+att.getValue()+" kelvin"+"</font>"+"<br></br>";
										}
								}
							}	
						}
					}
				}
			}
			EntityUtils.consume(entity1);	
		}
		catch(DocumentException e){
			e.printStackTrace();
		}
		finally{
			response1.close();
		}
			
		HttpSession session = request.getSession();
		
//		if(allData==null){
//			allData = "Weather Forecast";
//		}
		    session.setAttribute("city", city);
			session.setAttribute("content", allData);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
