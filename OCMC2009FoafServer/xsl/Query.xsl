<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:foaf="http://xmlns.com/foaf/0.1/"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:html='http://www.w3.org/1999/xhtml'
	xmlns='http://www.w3.org/1999/xhtml'>
	<xsl:output method="text" />
	<xsl:strip-space elements="*" />
	<xsl:template match="rdf:RDF">
SELECT ?x ?y ?z
		<xsl:apply-templates />
	</xsl:template>
	<xsl:template match="rdf:Description">
FROM <xsl:text><![CDATA[<http://localhost:8080/OCMC2009/]]></xsl:text><xsl:value-of select="@rdf:about" /><xsl:text><![CDATA[/foaf.rdf>]]></xsl:text>
	</xsl:template>
	<xsl:template match="text()|@*" />
</xsl:stylesheet>