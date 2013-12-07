<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:param name="number" />
	<xsl:param name="date" />
	<xsl:param name="importe" />

	<xsl:template match="/">

		Armando 's Bookstore
		<br />
		<p>This is an invoice generated from Armando 's bookstore.</p>
		Invoice
		<br />
		<ul>
			<li>
				Number:
				<xsl:value-of select="/invoice/number" />
			</li>
			<li>
				Date:
				<xsl:value-of select="/invoice/date" />
			</li>
		</ul>
		Books invoiced
		<br />
		<table>
			<tr>
				<th>ISBN</th>
				<th>Name</th>
			</tr>

			<xsl:for-each select="/invoice/books/book">
				<tr>
					<td>
						<xsl:value-of select="isbn" />
					</td>
					<td>
						<xsl:value-of select="name" />
					</td>
				</tr>
			</xsl:for-each>

			<tr>
				<td>
					Importe :
					<xsl:value-of select="/invoice/importe" />
				</td>
			</tr>
		</table>

	</xsl:template>

</xsl:stylesheet>