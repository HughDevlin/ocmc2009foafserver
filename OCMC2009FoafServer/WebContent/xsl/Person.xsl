<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:foaf="http://xmlns.com/foaf/0.1/"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:output method="xml"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" indent="yes" />
	<xsl:include href="footer.xsl" />
	<xsl:include href="headlinks.xsl" />
	<xsl:template match="foaf:Person">
		<xsl:variable name="nodeId"
			select="fn:substring-after(fn:string(@rdf:about),'Person/')" />
		<xsl:variable name="foafUrl"
			select="fn:concat('../Person/', $nodeId, '/foaf.rdf')" />
		<html>
			<head>
				<xsl:call-template name="headlinks">
					<xsl:with-param name="foafUrl" select="$foafUrl" />
				</xsl:call-template>
				<title>
					<xsl:value-of select="foaf:name" />
				</title>
			</head>
			<body>
				<h1>
					Welcome to the homepage of
					<xsl:value-of select="foaf:name" />
				</h1>
				<p>
					<a>
						<xsl:attribute name="href"><xsl:value-of
							select="foaf:phone/@rdf:resource" /></xsl:attribute>
						<xsl:value-of select="foaf:phone/@rdf:resource" />
					</a>
				</p>
				<p>
					<a title="e-mail {foaf:givenName}">
						<xsl:attribute name="href"><xsl:value-of
							select="foaf:mbox/@rdf:resource" /></xsl:attribute>
						<xsl:value-of select="foaf:mbox/@rdf:resource" />
					</a>
				</p>
				<xsl:call-template name="footer">
					<xsl:with-param name="foafUrl" select="$foafUrl" />
				</xsl:call-template>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>