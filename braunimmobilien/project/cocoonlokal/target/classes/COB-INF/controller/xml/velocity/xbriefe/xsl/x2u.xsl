<?xml version="1.0" encoding="UTF-8"?>
<intern xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:text>Ihrem Wunsch entsprechend übersenden wir Ihnen anbei die Unterlagen für das

Objekt : </xsl:text>
<xsl:value-of select="//document/variablen/angebot/angnr"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/angkurzbeschreibung"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/anglagebeschreibung"/>
<xsl:text>

</xsl:text>
<xsl:for-each select="//document/variablen/angebot/objekte/objekt"> 
 <xsl:value-of select="."/><xsl:text>
</xsl:text>
 </xsl:for-each>
<xsl:if test="string-length(//document/variablen/unterlagen1)>0">
 <xsl:text>
</xsl:text><xsl:value-of select="//document/variablen/unterlagen1"/></xsl:if>
 <xsl:if test="string-length(//document/variablen/unterlagen2)>0">
 <xsl:text>
</xsl:text><xsl:value-of select="//document/variablen/unterlagen2"/></xsl:if>
<xsl:if test="string-length(//document/variablen/unterlagen3)>0">
 <xsl:text>
</xsl:text><xsl:value-of select="//document/variablen/unterlagen3"/></xsl:if>
<xsl:if test="string-length(//document/variablen/unterlagen4)>0">
 <xsl:text>
</xsl:text><xsl:value-of select="//document/variablen/unterlagen4"/></xsl:if><xsl:text>

Zu weiteren Verhandlungen stehen wir Ihnen jederzeit gern zur Verfügung.

In der Hoffnung , daß Sie zu einer positiven Beurteilung kommen, verbleiben wir


mit freundlichen Grüßen</xsl:text>
</intern>