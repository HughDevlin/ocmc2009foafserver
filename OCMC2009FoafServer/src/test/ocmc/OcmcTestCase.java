package ocmc;

import java.io.IOException;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.*;
import org.w3c.dom.Document;
import org.xml.sax.*;
import org.junit.Ignore;
import junit.framework.TestCase;
import com.meterware.httpunit.*;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

/**
 * superclass for OCMC test cases common behaviors
 * @author Hugh
 *
 */
@Ignore
public class OcmcTestCase extends TestCase {
	protected static final XPathFactory xpf = XPathFactory.newInstance();
	protected static final String[][] namespaces = {
			{ "foaf", "http://xmlns.com/foaf/0.1/" },
			{ "rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#" } };
	protected static final NamespaceContext nsc = new NamespaceContextImpl(
			namespaces);
	protected static final String baseUrl = "http://localhost:8080/OCMC2009FoafServer";
	protected static WebConversation webConversation = new WebConversation();
	protected WebResponse response;
	protected DOMParser parser = new DOMParser();
	protected XPath xp = xpf.newXPath();
	protected Document doc;

	public OcmcTestCase() throws IOException, SAXException {
		parser.setFeature(
				"http://apache.org/xml/features/nonvalidating/load-external-dtd",
				false);
		xp.setNamespaceContext(nsc);
	}

	protected void test404(String resource) throws Exception {
		try {
			response = webConversation.getResponse(resource);
			fail(resource);
		} catch (HttpNotFoundException e) {
		}
	}

	public void testResponseCodeOk(WebResponse response) {
		assertEquals("Response code", 200, response.getResponseCode());
	}

	public void testIsHtml(WebResponse response) {
		assertTrue("isHTML", response.isHTML());
	}
	
	public void testContentType(WebResponse response, String contentType) {
		assertEquals("Content type", contentType, response.getContentType());
	}
	
	public void testHtmlElement(Document doc) throws XPathExpressionException {
		assertNotNull("html element", xp.evaluate("//html", doc));
	}

	public void testTitle(WebResponse response, String title) throws SAXException {
		assertEquals("WebResponse title", title, response.getTitle());
	}

	public void testTitleXPath(Document doc, String title) throws XPathExpressionException  {
		assertEquals("XPath title", title, xp.evaluate("//title", doc));
	}

	public void testGetFoafLink(WebResponse response) throws SAXException {
		WebLink link = response.getLinkWithImageText("Friend-of-a-friend icon");
		assertEquals("Foaf link", ".."
			+ response.getURL().toString().split("OCMC2009FoafServer")[1]
			+ "/foaf.rdf", link.getURLString());
	}

	public void testGetFoaf(String className, String nodeId) throws Exception {
		String uri = className + "/" + nodeId;
		WebLink link = response.getLinkWithImageText("Friend-of-a-friend icon");
		response = link.click();
		doc = response.getDOM();
		assertEquals("Content type", "application/rdf+xml",
				response.getContentType());
		assertEquals(
				"Foaf primary topic",
				uri,
				xp.evaluate(
						"//foaf:PersonalProfileDocument/foaf:primaryTopic/@rdf:resource",
						doc));
		assertEquals("Foaf Person", uri, xp.evaluate("//foaf:" + className
				+ "/@rdf:about", doc));
	}

}