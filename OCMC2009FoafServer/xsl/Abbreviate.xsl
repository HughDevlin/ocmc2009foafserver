<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:foaf="http://xmlns.com/foaf/0.1/"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
	xmlns:fn="http://www.w3.org/2005/xpath-functions" 
	exclude-result-prefixes="fn">
	<xsl:output method="xml" indent="yes" name="xml" />
	<xsl:strip-space elements="*" />

	<!-- root (copy namespaces) -->
	<xsl:template match="rdf:RDF">
		<xsl:copy copy-namespaces="no">
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>
	<xsl:template match="rdf:Description[@rdf:about]">
		<xsl:element
			name="foaf:{fn:replace(rdf:type/@rdf:resource,'.*/(.*)$','$1')}">
			<xsl:attribute name="rdfs:label" select="rdfs:label" />
			<xsl:copy-of select="@*" copy-namespaces="no" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<!-- ignore -->
	<xsl:template match="rdf:type" />
	<xsl:template match="rdfs:label" />

	<!-- identity -->
	<xsl:template name="identity" match="@*|node()">
		<xsl:copy copy-namespaces="no">
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>