<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:foaf="http://xmlns.com/foaf/0.1/"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
	xmlns:fn="http://www.w3.org/2005/xpath-functions"
	exclude-result-prefixes="fn">
	<xsl:output method="xml" indent="yes" name="xml" />
	<xsl:strip-space elements="*" />
	<xsl:template match="foaf:Person[@rdf:about]|foaf:Group[@rdf:about]">
		<xsl:result-document href="../WebContent/rdf/{@rdf:about}/foaf.rdf"
			format="xml">
			<rdf:RDF xml:base="http://www.example.com/">
				<foaf:PersonalProfileDocument
					rdf:about="">
					<foaf:primaryTopic rdf:resource="{@rdf:about}" />
				</foaf:PersonalProfileDocument>
				<xsl:copy copy-namespaces="no">
					<xsl:apply-templates select="@*" />
					<xsl:apply-templates select="node()">
						<xsl:sort select="." />
					</xsl:apply-templates>
				</xsl:copy>
			</rdf:RDF>
		</xsl:result-document>
	</xsl:template>
	<!-- identity -->
	<xsl:template match="@*|node()">
		<xsl:copy copy-namespaces="no">
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>