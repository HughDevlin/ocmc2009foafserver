<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:foaf="http://xmlns.com/foaf/0.1/"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:html='http://www.w3.org/1999/xhtml'
	xmlns='http://www.w3.org/1999/xhtml'>
	<xsl:output method="xml"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" indent="yes" />
	<xsl:template match="rdf:RDF">
		<html>
			<head>
				<title> Welcome to the OCMC 2009</title>
			</head>
			<body>
				<h1>Welcome to the OCMC 2009 homepage</h1>
				<h2>Attendees:</h2>
				<xsl:apply-templates select="foaf:Person">
					<xsl:sort select="foaf:familyName"/>
				</xsl:apply-templates>
				<h2>Participating Organizations:</h2>
				<xsl:apply-templates select="foaf:Group">
					<xsl:sort select="foaf:name"/>
				</xsl:apply-templates>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="foaf:Person">
			<a>
				<xsl:attribute name="href">
							<xsl:value-of select="@rdf:about" />
				</xsl:attribute>
				<xsl:value-of select="foaf:name" />
			</a>
			(
			<a>
				<xsl:attribute name="href">
					<xsl:value-of select="foaf:mbox/@rdf:resource" />
				</xsl:attribute>
				<xsl:value-of
					select="fn:substring-after(foaf:mbox/@rdf:resource,'mailto:')" />
			</a>
			)
			<br/>
	</xsl:template>
	<xsl:template match="foaf:Group">
			<a>
				<xsl:attribute name="href">
							<xsl:value-of select="@rdf:about" />
				</xsl:attribute>
				<xsl:value-of select="foaf:name" />
			</a>
			<br/>
	</xsl:template>
</xsl:stylesheet>