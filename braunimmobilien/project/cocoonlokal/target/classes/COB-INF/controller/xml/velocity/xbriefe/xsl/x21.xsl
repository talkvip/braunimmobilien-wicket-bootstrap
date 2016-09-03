<?xml version="1.0" encoding="UTF-8"?>
<intern xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:text>soeben  ist ein Angebot bei uns eingegangen, das Ihren Wünschen entsprechen könnte.

Wir erlauben uns, zu offerieren:

</xsl:text>
<xsl:value-of select="//document/variablen/angebot/angnr"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/angkurzbeschreibung"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/anglagebeschreibung"/>
<xsl:text>

Alles Nähere ersehen Sie aus den beigefügten Anhängen.

Auf Wunsch stellen wir Ihnen gern ausführliche Unterlagen zur Verfügung und
halten uns für eine Besichtigung bereit.

Für weitere Informationen sowie Verhandlungen über den Preis stehen wir Ihnen
jederzeit gern zur Verfügung.


Mit freundlichen Grüßen</xsl:text>
</intern>