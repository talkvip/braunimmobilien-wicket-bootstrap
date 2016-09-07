<?xml version="1.0" encoding="UTF-8"?>
<intern xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:text>Sie baten uns, Ihnen geeignete Objekte nachzuweisen bzw. zu vermitteln.
Wir boten Ihnen wunschgemäß folgendes Objekt bereits mündlich an:

</xsl:text>

<xsl:value-of select="//document/variablen/angebot/angnr"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/angkurzbeschreibung"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/anglagebeschreibung"/>
<xsl:text>
</xsl:text>
 <xsl:for-each select="//document/variablen/angebot/objekte/objekt"> 
 <xsl:value-of select="."/><xsl:text>
</xsl:text>
 </xsl:for-each>

<xsl:text>
Alles Nähere ersehen Sie aus beiliegenden Erläuterungen. 
Wir haben unsere Angebote nochmals durchgesehen und bieten Ihnen noch Folgendes mit Erläuterungen an :

</xsl:text>

<xsl:value-of select="//document/variablen/angebot1/angnr"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot1/angkurzbeschreibung"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot1/anglagebeschreibung"/>
<xsl:text>
</xsl:text>
 <xsl:for-each select="//document/variablen/angebot1/objekte/objekt"> 
 <xsl:value-of select="."/><xsl:text>
</xsl:text>
 </xsl:for-each>
<xsl:text>
Gebühren vor Vertragsabschluß erheben wir nicht.
Erst wenn in Folge unserer Tätigkeit ein Abschluß zustande kommt,
ist von Ihnen die aus dem Exposee ersichtliche Provision zu zahlen.

Benachrichtigen Sie uns bitte, wenn Sie eine Besichtigung vornehmen wollen,
damit wir uns zur Verfügung halten können.


Mit freundlichen Grüßen</xsl:text>
</intern>
