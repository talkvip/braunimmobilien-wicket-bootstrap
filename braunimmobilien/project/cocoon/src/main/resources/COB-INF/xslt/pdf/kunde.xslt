<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
  version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:tns="http://braunimmobilien/webapp/person">
 
<xsl:template match="liste">

<page>
  
<header>Kunde <xsl:value-of select="current()/id"/> aufgenommen am <xsl:value-of select="current()/datum"/></header>
 <table>
<table-header>
<format>
<width>4cm</width>
<width>5cm</width>
<width>4cm</width>
<width>5cm</width>
</format><ueberschriften>
<ueberschrift></ueberschrift>
<ueberschrift></ueberschrift>
<ueberschrift></ueberschrift>
<ueberschrift></ueberschrift>
</ueberschriften>
</table-header>

<row>
<column weight="bold" color="white">Kundennummer</column>
<column color="white"><xsl:value-of select="current()/id"/></column>
<column weight="bold" color="white">Kundenstatus</column>
<column color="white"><xsl:value-of select="current()/status"/></column>
</row>
<row>
<column weight="bold" color="white">Kundenart</column>
<column color="white"><xsl:value-of select="current()/kundenart"/></column>
<column weight="bold" color="white">Aufnahmedatum Kunde</column>
<column color="white"><xsl:value-of select="current()/datum"/></column>
</row>
<row>
<column color="white" weight="bold">Personid</column>
<column color="white"><xsl:value-of select="current()/personid"/></column>
<column color="white" weight="bold">Adresse</column>
<column color="white"><xsl:value-of select="current()/adresse"/></column>
</row>
<row>
<column color="white" weight="bold">Name</column>
<column color="white"><xsl:value-of select="current()/name"/></column>
<column color="white" weight="bold">Telefon</column>
<column color="white"><xsl:value-of select="current()/telefon"/></column>
</row>
<row>
<column color="white" weight="bold">Aktuell</column>
<column color="white"><xsl:value-of select="current()/aktuell"/></column>
<column color="white" weight="bold">Status</column>
<column color="white"><xsl:value-of select="current()/eigtstatus"/></column>
</row>
<row>
<column color="white" weight="bold">Briefanrede</column>
<column color="white"><xsl:value-of select="current()/briefanrede"/></column>
<column color="white" weight="bold">Datum Aufnahme Person</column>
<column color="white"><xsl:value-of select="current()/aufdat"/></column>
</row>
<row>
<column color="white" weight="bold">email</column>
<column color="white"><xsl:value-of select="current()/email"/></column>
<column color="white" weight="bold">Datum letzter Kontakt Person</column>
<column color="white" ><xsl:value-of select="current()/letztdat"/></column>
</row>
<row><column number-columns-spanned="4" align="center">N a c h w e i s e</column></row></table>
<table>
<table-header>
<format>
<width>2cm</width>
<width>5cm</width>
<width>1cm</width>
<width>3cm</width>
<width>4cm</width>
</format><ueberschriften>
<ueberschrift>Angebotsnr.</ueberschrift>
<ueberschrift>Nachweisid</ueberschrift>
<ueberschrift>XTyp</ueberschrift>
<ueberschrift>Datum</ueberschrift>
<ueberschrift></ueberschrift>
</ueberschriften>
</table-header>

<xsl:apply-templates select="nachweise"/>
 </table>
</page>
</xsl:template>
 








<xsl:template match="nachweis">

<row><column color="white"><xsl:value-of select="current()/angnr"/> </column><column color="white"><xsl:value-of select="current()/nachweisid"/> </column>
<column color="white" ><xsl:value-of select="current()/xtyp"/></column><column color="white"><xsl:value-of select="current()/nachdatum"/></column><column color="white"><xsl:apply-templates select="current()/nachtelefone"/></column></row>
</xsl:template>
 
  

</xsl:stylesheet>
