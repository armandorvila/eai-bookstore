<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<br>
			Armando 's Bookstore -
			<xsl:value-of select="/notification/subject" />
		</br>
		<p>
			Dear
			<xsl:value-of select="/notification/customer/name" />
			:
		</p>
		<p>
			<xsl:value-of select="/notification/message" />
		</p>
	</xsl:template>
</xsl:stylesheet>