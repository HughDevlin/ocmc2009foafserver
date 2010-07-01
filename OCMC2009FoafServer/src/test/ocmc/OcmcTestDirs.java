package ocmc;

import java.io.IOException;

import org.xml.sax.*;

import junit.framework.*;

/**
 * verify directory listing are blocked
 * @author Hugh
 *
 */
public class OcmcTestDirs extends OcmcTestCase {

	public OcmcTestDirs() throws IOException, SAXException {
		super();
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static TestSuite suite() {
		return new TestSuite(OcmcTestDirs.class);
	}

	public void testGetPersonDirNotFound1() throws Exception {
		test404(baseUrl + "/Person");
	}

	public void testGetPersonDirNotFound2() throws Exception {
		test404(baseUrl + "/Person/");
	}

	public void testGetPersonDirNotFound3() throws Exception {
		test404(baseUrl + "/Person/ACarmon/");
	}

	public void testGetOrganizationDirNotFound1() throws Exception {
		test404(baseUrl + "/Organization");
	}

	public void testGetOrganizationDirNotFound2() throws Exception {
		test404(baseUrl + "/Organization/");
	}

	public void testGetOrganizationDirNotFound3() throws Exception {
		test404(baseUrl + "/Organization/MITRE/");
	}

}
