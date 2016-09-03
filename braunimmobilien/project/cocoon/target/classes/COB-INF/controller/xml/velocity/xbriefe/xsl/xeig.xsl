<?xml version="1.0" encoding="UTF-8"?>
<intern xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:text>mit Ihrem Einverständnis haben wir von Ihrer Immobilie ein Exposé angefertigt,
das wir in der vorliegenden Form unseren Kunden anbieten.

</xsl:text>

<xsl:value-of select="//document/variablen/angebot/angnr"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/angkurzbeschreibung"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/anglagebeschreibung"/>
<xsl:text>
</xsl:text>
 <xsl:for-each select="//document/variablen/angebot/objekte/objekt"> 
 <xsl:value-of select="."/><xsl:text>
</xsl:text>
 </xsl:for-each>

<xsl:text>

Prüfen Sie bitte Form und Inhalt des Exposés und teilen Sie uns eventuelle
Änderungen mit.

Bitte beachten Sie die Auflagen für den Eigentümer durch die EnEV 2014, wie Sie im Anhang nachlesen können.

Wir bestätigen Ihnen nochmals, daß Sie als Verkäufer keinerlei Kosten zu 
zahlen haben und unsere Provision der Käufer trägt.

Schon jetzt sichern wir Ihnen eine diskrete und korrekte Abwicklung in Bezug
auf den Verkauf Ihrer Immobilie zu.

Für Ihr entgegenkommendes Vertrauen bedanken wir uns und verbleiben

Mit freundlichen Grüßen</xsl:text>
</intern>