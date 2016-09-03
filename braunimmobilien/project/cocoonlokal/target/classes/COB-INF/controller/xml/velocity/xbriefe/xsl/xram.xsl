<?xml version="1.0" encoding="UTF-8"?>
<intern xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:text>
wie Ich Ihnen bereits telefonisch mitgeteilt habe interessiert sich ein Investor
für Ihre Immobilie :

</xsl:text>
<xsl:value-of select="//document/variablen/objekt"/>
<xsl:text>

Er hat mich gebeten mich mit Ihnen in Verbindung zu setzen und Ihnen mitzuteilen,
daß er obige Immobilie zu einem für Sie attraktiven Preis erwerben möchte.

Ich betreibe die klassische Immobilienvermittlung in dem Schwerpunktbereich Verkauf: Rendite.


Sie erreichen mich wie folgt:

               </xsl:text>
<xsl:value-of select="//document/variablen/ich/firma"/>
<xsl:text>
               </xsl:text>
<xsl:value-of select="//document/variablen/ich/strasse"/>
<xsl:text>
               </xsl:text>
<xsl:value-of select="//document/variablen/ich/ort"/>
<xsl:text>
               </xsl:text>
<xsl:value-of select="//document/variablen/ich/tel"/>
<xsl:text>
               </xsl:text>
<xsl:value-of select="//document/variablen/ich/email"/>
<xsl:text>
                          
Ich würde mich freuen, wenn Sie auch in Zukunft beim Verkauf oder Ankauf von
Immobilien mit mir zusammenarbeiten würden.


Mit freundlichen Grüßen</xsl:text>
</intern>