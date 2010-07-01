<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:foaf="http://xmlns.com/foaf/0.1/"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:template name="footer">
		<xsl:param name="foafUrl" />
		<p>
			<a href="{$foafUrl}" title="Friend-of-a-friend info available">
				<img src="../images/Foaf.png" alt="Friend-of-a-friend icon"
					border="0" />
				Friend-of-a-friend info available
			</a>
		</p>
		<p>
			<a href="{$foafUrl}" title="RDF available">
				<img src="../images/rdf_flyer.24.gif" alt="RDF icon" border="0" />
				RDF available
			</a>
		</p>
		<p>
			<a href="http://validator.w3.org/check?uri=referer">
				<img src="http://www.w3.org/Icons/valid-xhtml-rdfa-blue" alt="Valid XHTML + RDFa"
					border="0" />
				Valid XHTML + RDFa
			</a>
		</p>
	</xsl:template>
</xsl:stylesheet>