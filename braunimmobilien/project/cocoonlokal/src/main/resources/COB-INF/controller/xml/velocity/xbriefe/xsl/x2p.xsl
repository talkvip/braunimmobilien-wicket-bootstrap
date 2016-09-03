<?xml version="1.0" encoding="UTF-8"?>
<intern xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:text>wir freuen uns, Ihnen mitteilen zu können, daß die Immobilie:

</xsl:text>
<xsl:for-each select="//document/variablen/angebot/objekte/objekt"> 
 <xsl:value-of select="."/><xsl:text>

</xsl:text>
 </xsl:for-each>
<xsl:text>eine erwähnenswerte Preissenkung erfahren hat

alter Preis : </xsl:text>
<xsl:if test="string-length(//document/variablen/unterlagen1)>0">
<xsl:value-of select="//document/variablen/unterlagen1"/></xsl:if>
 <xsl:if test="string-length(//document/variablen/unterlagen2)>0">
 <xsl:text>
neuer Preis : </xsl:text><xsl:value-of select="//document/variablen/unterlagen2"/></xsl:if>

<xsl:text>

Sollte aufgrund dieser Tatsache Ihr Interesse geweckt worden sein,
setzen Sie sich bitte mit uns in Verbindung. 
Für eine Besichtigung und weitere Verhandlungen 
stehen wir Ihnen jederzeit gern zur Verfügung.

Mit freundlichen Grüßen</xsl:text>
</intern>