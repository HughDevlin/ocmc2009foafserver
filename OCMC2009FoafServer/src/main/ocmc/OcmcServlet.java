package ocmc;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import org.w3c.dom.Document;
import org.xml.sax.*;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

/**
 * Servlet implementation class Ocmc
 * 
 * @author Hugh
 */
public class OcmcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DOMParser parser = new DOMParser();
	private static final TransformerFactory tFactory = TransformerFactory.newInstance();
	private static ServletContext servletContext = null;

	/**
	 * get servlet context, set transform factory
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		servletContext = getServletContext();
	}

	/**
	 * get an input stream from a local foaf file
	 * 
	 * @return a stream from a local foaf file
	 * @param servletPath
	 *            Person or Group
	 * @param pathInfo
	 *            unique node identifier
	 * @throws FileNotFoundException
	 */
	private FileInputStream getRdfInputStream(String servletPath,
			String pathInfo) throws FileNotFoundException {
		String rdfFilePath = servletContext.getRealPath("rdf" + servletPath
				+ pathInfo);
		return new FileInputStream(new File(rdfFilePath));
	}

	/**
	 * html request handler
	 * 
	 * @throws IOException
	 * @throws TransformerException
	 * @throws SAXException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private void getHtml(HttpServletResponse response, String servletPath,
			String pathInfo) throws TransformerException, IOException,
			SAXException {
		// Get rdf (xml)
		InputSource iSrc = new InputSource(getRdfInputStream(servletPath,
				pathInfo + "/foaf.rdf"));
		try {
			parser.parse(iSrc);
			Document doc = parser.getDocument();
			Source xmlSource = new DOMSource(doc);
			// Get xsl
			String xslFilePath = servletContext.getRealPath("/xsl"
					+ servletPath + ".xsl");
			Source xslSource = new StreamSource(new File(xslFilePath));
			// apply XSL transform of XML Document into servlet output stream
			Transformer t = tFactory.newTransformer(xslSource);
			response.setContentType("text/html; charset=UTF-8");
			t.transform(xmlSource, new StreamResult(response.getOutputStream()));
		} finally {
			iSrc.getByteStream().close();
		}
	}

	/**
	 * indicate file not found
	 * 
	 * @param response
	 * @throws IOException
	 */
	private static void send404(HttpServletResponse response)
			throws IOException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	/**
	 * delegate html and rdf requests
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		if (!(pathInfo == null)) {
			if (pathInfo.endsWith("/foaf.rdf")) {
				try {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/rdf"
							+ servletPath + pathInfo);
					dispatcher.forward(request, response); // Tomcat default servlet
				} catch (IOException e) {
					send404(response);
				}
			} else if (!pathInfo.endsWith("/")) {
				try {
					getHtml(response, servletPath, pathInfo);
				} catch (TransformerException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					send404(response);
				}
			} else {
				send404(response);
			}
		} else {
			send404(response);
		}
		response.flushBuffer();
	}
}
