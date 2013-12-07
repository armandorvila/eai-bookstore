<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<br>Armando 's Bookstore </br>

		<p>This is a shipment note generated from Armando 's bookstore.</p>

		<br>Shipment </br>

		Shipment type:
		<xsl:if test="/shipment/express = true">
			Express
		</xsl:if>
		<xsl:if test="/shipment/express = false">
			Ordinary
		</xsl:if>

		<br>Customer </br>
		<ul>
			<li>
				Nif:
				<xsl:value-of select="/shipment/customer/nif" />
			</li>
			<li>
				Name:
				<xsl:value-of select="/shipment/customer/name" />
			</li>
			<li>
				Address:
				<xsl:value-of select="/shipment/customer/address" />
			</li>
			<li>
				mail:
				<xsl:value-of select="/shipment/customer/mail" />
			</li>
		</ul>

		<br>Orders</br>
		<table>
			<tr>
				<th>Order ID</th>
				<th>Book</th>
				<th>Amount</th>
			</tr>
			<xsl:for-each select="/shipment/orders/order">
				<tr>
					<td>
						<xsl:value-of select="id" />
					</td>
					<td>
						<xsl:value-of select="book/isbn" />
					</td>
					<td>
						<xsl:value-of select="amount" />
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
		<br>Invoice </br>
		<ul>
			<li>
				Invoice number:
				<xsl:value-of select="/shipment/invoice/number" />
			</li>
			<li>
				Amount ($):
				<xsl:value-of select="/shipment/invoice/importe" />
			</li>
		</ul>
	</xsl:template>
</xsl:stylesheet>