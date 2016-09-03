<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet
  version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:sql="http://apache.org/cocoon/SQL/2.0"
 xmlns:fd="http://apache.org/cocoon/forms/1.0#definition" exclude-result-prefixes="sql">
 
<xsl:template match="liste">

<page>
  
<header>Nachweise zu <xsl:value-of select="current()/id"/> vom <xsl:value-of select="current()/jetzt"/></header>
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
<column color="white" >Kurzbeschreibung</column>
<column color="white" number-columns-spanned="3"><xsl:value-of select="current()/angkurzbeschreibung"/></column></row>
<row><column color="white">Lagebeschreibung</column>
<column color="white" number-columns-spanned="3"><xsl:value-of select="current()/anglagebeschreibung"/></column>
</row>




<xsl:apply-templates select="objekte"/>
<row><column number-columns-spanned="4" align="center">N a c h w e i s e</column></row></table>
<table>
<table-header>
<format>
<width>6cm</width>
<width>10cm</width>
<width>3cm</width>
</format><ueberschriften>
<ueberschrift>Name</ueberschrift>
<ueberschrift>XTyp</ueberschrift>
<ueberschrift>Datum</ueberschrift>
</ueberschriften>
</table-header>

<xsl:apply-templates select="nachweise"/>
 </table>
</page>
</xsl:template>
 
<xsl:template match="mitarbeiter">
<row><column><xsl:value-of select="current()/id"/></column><column><xsl:value-of select="current()/mitarbeitername"/></column><column><xsl:value-of select="current()/vondatum"/></column><column><xsl:value-of select="current()/bisdatum"/></column><!--<column><xsl:value-of select="current()/aktuell"/></column>--></row> 
</xsl:template>


<xsl:template match="kunde">
<row><column number-columns-spanned="4" align="center">K u n d e</column></row>
<row>
<column weight="bold" color="white">Kundennummer</column>
<column color="white"><xsl:value-of select="current()/id"/></column>
<column weight="bold" color="white">Kundenstatus</column>
<column color="white"><xsl:value-of select="current()/kundenstatus"/></column>
</row>
<row>
<column weight="bold" color="white">Kundenart</column>
<column color="white"><xsl:value-of select="current()/kundenart"/></column>
<column weight="bold" color="white">Aufnahmedatum Kunde</column>
<column color="white"><xsl:value-of select="current()/kundaufdatum"/></column>
</row>
<row><column number-columns-spanned="4" align="center">M i t a r b e i t e r</column></row>
<xsl:apply-templates select="mitarbeiters"/>
</xsl:template>

<xsl:template match="person">
<row><column number-columns-spanned="4" align="center">P e r s o n</column></row>
<row>
<column color="white" weight="bold">Id</column>
<column color="white"><xsl:value-of select="current()/id"/></column>
<column color="white" weight="bold">ZuordnungsartId</column>
<column color="white"><xsl:value-of select="current()/zuordnungsartid"/></column>
</row>
<row>
<column color="white" weight="bold">Personid</column>
<column color="white"><xsl:value-of select="current()/personid"/></column>
<column color="white" weight="bold">Adresse</column>
<column color="white"><xsl:value-of select="current()/adress"/></column>
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
<column color="white"><xsl:value-of select="current()/status"/></column>
</row>
<row>
<column color="white" weight="bold">Briefanrede</column>
<column color="white"><xsl:value-of select="current()/briefanrede"/></column>
<column color="white" weight="bold">Datum Aufnahme Person</column>
<column color="white"><xsl:value-of select="current()/aufnahmedatum"/></column>
</row>
<row>
<column color="white" weight="bold">email</column>
<column color="white"><xsl:value-of select="current()/email"/></column>
<column color="white" weight="bold">Datum letzter Kontakt Person</column>
<column color="white" ><xsl:value-of select="current()/datumletztkontakt"/></column>
</row>
<row><column number-columns-spanned="4" align="center">K u n d e n</column></row>
<xsl:apply-templates select="kunden"/>
</xsl:template>


<xsl:template match="nachweis">

<row><column color="white"><xsl:value-of select="current()/nachperson"/> </column>
<column color="white"><xsl:value-of select="current()/xtyplang"/></column><column color="white"><xsl:value-of select="current()/nachdatum"/></column></row>
</xsl:template>



<xsl:template match="objekt">



<row><column number-columns-spanned="4" color="white"><xsl:value-of select="current()/objektart"/><xsl:text> </xsl:text><xsl:value-of select="current()/adresse"/></column>
</row>

</xsl:template>
</xsl:stylesheet>
