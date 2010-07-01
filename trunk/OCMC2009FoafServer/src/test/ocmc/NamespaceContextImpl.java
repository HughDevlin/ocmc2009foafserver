/**
 * 
 */
package ocmc;

import java.util.*;

import javax.xml.namespace.NamespaceContext;

/**
 * NamespaceContext convenience implementation for use with XPath
 * @author Hugh
 * 
 */
public class NamespaceContextImpl implements NamespaceContext {
	
	/**
	 * encapsulated namespace hash
	 */
	private Map<String, String> map = new HashMap<String, String>();

	/**
	 * @param namespaces array of prefix, uri pairs
	 */
	public NamespaceContextImpl(String[][] namespaces) {
		for(int i=0;i<namespaces.length;i++)
			map.put(namespaces[i][0], namespaces[i][1]);
	};

	/**
	 * hash map look-up
	 * 
	 * @see
	 * javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		return map.get(prefix);
	}

	/**
	 * null implementation
	 * 
	 * @see javax.xml.namespace.NamespaceContext#getPrefix(java.lang.String)
	 */
	@Override
	public String getPrefix(String namespaceURI) {
		return null;
	}

	/**
	 * null implementation
	 * 
	 * @see javax.xml.namespace.NamespaceContext#getPrefixes(java.lang.String)
	 */
	@Override
	public Iterator<?> getPrefixes(String namespaceURI) {
		return null;
	}

}
