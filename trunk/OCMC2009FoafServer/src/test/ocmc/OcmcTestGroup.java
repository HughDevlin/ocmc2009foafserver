package ocmc;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.*;

import com.meterware.httpunit.*;

import junit.framework.*;

/**
 * test Group resources
 * @author Hugh
 *
 */
public class OcmcTestGroup extends OcmcTestCase {

	public OcmcTestGroup() throws IOException, SAXException {
		super();
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static TestSuite suite() {
		return new TestSuite(OcmcTestGroup.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		WebRequest request = new GetMethodWebRequest(baseUrl
				+ "/Group/Northwestern_University");
		response = webConversation.getResponse(request);
		parser.parse(new InputSource(response.getInputStream()));
		doc = parser.getDocument();
	}

	public void testResponseHtml() throws XPathExpressionException {
		testResponseCodeOk(response);
		testIsHtml(response);
		testContentType(response, "text/html");
		testHtmlElement(doc);
	}

	public void testTitle() throws SAXException, XPathExpressionException  {
		testTitle(response, "Northwestern University");
		testTitleXPath(doc, "Northwestern University");
	}

	public void testGetFoafIcon() throws Exception {
		WebImage img = response.getImageWithAltText("Friend-of-a-friend icon");
		assertTrue("Foaf icon", img.getSource().endsWith("Foaf.png"));
	}

	public void testGetFoafLink() throws Exception {
		testGetFoafLink(response);
	}

	public void testGetFoaf() throws Exception {
		testGetFoaf("Group", "Northwestern_University");
	}

	public void test404() throws Exception {
		test404(baseUrl + "/Group/foo");
	}
}
