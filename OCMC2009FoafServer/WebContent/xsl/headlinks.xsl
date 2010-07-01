<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:foaf="http://xmlns.com/foaf/0.1/"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:template name="headlinks">
		<xsl:param name="foafUrl" />
		<link rel="meta" type="application/rdf+xml" title="FOAF" href="{$foafUrl}" />
		<link rel="alternate" type="application/rdf+xml" title="FOAF"
			href="{$foafUrl}" />
	</xsl:template>
</xsl:stylesheet>