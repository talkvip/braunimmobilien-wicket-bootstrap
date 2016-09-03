<?xml version="1.0" encoding="UTF-8"?>
<intern xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:text>wir boten Ihnen wunschgemäß folgendes Objekt bereits mündlich an:

</xsl:text>

<xsl:value-of select="//document/variablen/angebot/angnr"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/angkurzbeschreibung"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/anglagebeschreibung"/>
<xsl:text>
</xsl:text>
 <xsl:for-each select="//document/variablen/angebot/objekte/objekt"> 
 <xsl:value-of select="."/><xsl:text>
</xsl:text>
 </xsl:for-each>

<xsl:text>
Die Daten und Bedingungen wollen Sie bitte aus beiliegenden Erläuterungen
entnehmen.

Interessant ist noch:

</xsl:text>

<xsl:value-of select="//document/variablen/angebot1/angnr"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot1/angkurzbeschreibung"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot1/anglagebeschreibung"/>
<xsl:text>
</xsl:text>
 <xsl:for-each select="//document/variablen/angebot1/objekte/objekt"> 
 <xsl:value-of select="."/><xsl:text>
</xsl:text>
 </xsl:for-each>
<xsl:text>
Benachrichtigen Sie uns bitte, wenn Sie eine Besichtigung vornehmen wollen,
damit wir uns zur Verfügung halten können.


Mit freundlichen Grüßen</xsl:text>
</intern>