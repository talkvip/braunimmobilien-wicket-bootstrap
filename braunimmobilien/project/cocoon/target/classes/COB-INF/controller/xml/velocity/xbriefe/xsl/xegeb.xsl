<?xml version="1.0" encoding="UTF-8"?>
<intern xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:text>pflichtgemäß teilen wir Ihnen mit, daß einer unserer Kunden für Ihr 
Anwesen : 

</xsl:text>
<xsl:for-each select="//document/variablen/angebot/objekte/objekt"> 
 <xsl:value-of select="."/><xsl:text>
</xsl:text>
 </xsl:for-each>
 <xsl:text>
 ein Gebot in Höhe von </xsl:text><xsl:value-of select="//document/variablen/unterlagen2"/><xsl:text> abgegeben hat.

Wir bitten Sie uns mitzuteilen, ob Sie bereit sind, mit unserem
Kaufinteressenten auf dieser Basis in Verhandlungen
einzutreten, oder ob wir ein Gegengebot abgeben sollen.

In der Hoffnung bald von Ihnen zu hören, verbleiben wir


mit freundlichen Grüßen</xsl:text>
</intern>
