<?xml version="1.0" encoding="UTF-8"?>
<intern xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:text>ich bin der Opa von Kevin, dem Klassenkameraden </xsl:text><xsl:value-of select="//document/variablen/unterlagen2"/>
<xsl:text> an der Friesenschule.

Ich bin seit Jahrzehnten als Renditemakler tätig.
Bei diesen Investitionen geht es darum in Zeiten
hoher Abgabenlast abgabenbefreit Vermögen aufzubauen
um davon in Zeiten niedriger Abgabenlast zu leben.
Dazu braucht man aber kein Haus zu kaufen.
Das kann man auch mit staatlich geförderten Sparverträgen erreichen.

Wenn sie dieses Thema interessiert würde ich Sie gerne im Rahmen
eines kurzen Besuchs über die Möglichkeiten informieren.

Ich werde Sie dazu in den nächsten Tagen kurz telefonisch kontaktieren.
</xsl:text>
<xsl:for-each select="//document/variablen/angebot/objekte/objekt"> 
<xsl:value-of select="."/><xsl:text>
</xsl:text>
 </xsl:for-each>

<xsl:text>
Mit freundlichen Grüßen</xsl:text>
</intern>