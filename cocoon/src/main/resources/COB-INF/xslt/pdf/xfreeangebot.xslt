<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
  version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:tns="http://braunimmobilien/webapp/person">
 
<xsl:template match="liste">

<page>

<header>Freitextsuche vom <xsl:value-of select="current()/jetzt"/></header>
 
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
 <xsl:apply-templates />
 </table>
</page>
</xsl:template>

 <xsl:template match="person">
<row><column number-columns-spanned="4" align="center">P e r s o n</column></row>


<row>
<column color="white" weight="bold"><xsl:value-of select="current()/name"/></column>
<column color="white"><xsl:value-of select="current()/telefon"/></column>
<column color="white" weight="bold"><xsl:value-of select="current()/firma"/></column>
<column color="white"><xsl:apply-templates select="current()/telefone"/></column>
</row>

</xsl:template>

 <xsl:template match="telefone">
      <xsl:apply-templates />
  </xsl:template>
  
 <xsl:template match="tns:telefoneModel">
    
    <table><xsl:apply-templates /></table>
    </xsl:template>
    
      <xsl:template match="tns:telefon">
    
    <row><column color="white"><xsl:value-of select="./tns:art" /></column><column color="white"><xsl:value-of select="./tns:telefon" /></column></row>
    </xsl:template>
 
 <xsl:template match="kunde">
<row><column number-columns-spanned="4" align="center">K u n d e <xsl:value-of select="current()/id"/></column></row>

<xsl:apply-templates select="person"/>

</xsl:template>


 <xsl:template match="nachweise">
      <xsl:apply-templates />
  </xsl:template>

 <xsl:template match="angebot">
 <row><column number-columns-spanned="4" align="center">------------------------------------------------------------------------------</column></row>
 <row><column number-columns-spanned="4" align="center">A n g e b o t <xsl:value-of select="current()/id"/></column></row>
 <row><column number-columns-spanned="4" align="center">------------------------------------------------------------------------------</column></row>
 
<row><column color="white"></column><column color="white"><xsl:value-of select="current()/angnr"/> </column>
<column color="white" ><xsl:value-of select="current()/angkurzbeschreibung"/></column><column color="white"><xsl:value-of select="current()/anglagebeschreibung"/></column></row>
<row><column number-columns-spanned="4" align="center">O b j e k t e</column></row>
      <xsl:apply-templates select="objekte"/>
<row><column number-columns-spanned="4" align="center">N a c h w e i s e</column></row>
      <xsl:apply-templates select="nachweise"/>
</xsl:template>
<!--
  <xsl:template match="nachtelefone">
      <xsl:apply-templates />
  </xsl:template>
 <xsl:template match="tns:telefoneModel">
    
    <table><xsl:apply-templates /></table>
    </xsl:template>
    
      <xsl:template match="tns:telefon">
    <row><column color="white"><xsl:value-of select="./tns:art" /></column><column color="white"><xsl:value-of select="./tns:telefon" /></column></row>
    </xsl:template>
    -->
    <xsl:template match="objekt">
<row><column number-columns-spanned="4" align="center"> <xsl:apply-templates /></column></row>
</xsl:template>
 
 <xsl:template match="nachweis">

<row><column color="white"><xsl:value-of select="current()/id"/> </column><column color="white"><xsl:value-of select="current()/id"/> </column>
<column color="white" ><xsl:value-of select="current()/xtyp"/></column><column color="white"><xsl:value-of select="current()/nachdatum"/></column></row>
 <xsl:apply-templates select="kunde"/>
</xsl:template>   
    
</xsl:stylesheet>
