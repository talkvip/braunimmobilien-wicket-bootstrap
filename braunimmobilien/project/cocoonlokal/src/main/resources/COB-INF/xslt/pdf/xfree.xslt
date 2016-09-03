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
<column color="white" weight="bold">Info</column>
<column color="white"><xsl:value-of select="current()/info"/></column>
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
<column color="white" weight="bold">Firma</column>
<column color="white"><xsl:value-of select="current()/firma"/></column>
<column color="white" weight="bold">Telefone</column>
<column color="white"><xsl:apply-templates select="current()/telefone"/></column>
</row>
 <xsl:apply-templates select="kunden"/>
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
<row><column number-columns-spanned="4" align="center">K u n d e</column></row>
<row>
<column weight="bold" color="white">Kundennummer</column>
<column color="white"><xsl:value-of select="current()/id"/></column>
<column weight="bold" color="white">Kundenstatus</column>
<column color="white"></column>
 <xsl:apply-templates select="nachweise"/>
</row>
</xsl:template>
 <xsl:template match="nachweise">
 <row><column number-columns-spanned="4" align="center">N a c h w e i s e</column></row>
      <xsl:apply-templates />
  </xsl:template>

 <xsl:template match="angebot">
 <xsl:apply-templates select="angebot"/>
<row><column color="white"></column><column color="white"><xsl:value-of select="current()/angnr"/> </column>
<column color="white" ><xsl:value-of select="current()/angkurzbeschreibung"/></column><column color="white"><xsl:value-of select="current()/anglagebeschreibung"/></column></row>
<row><column number-columns-spanned="4" align="center">O b j e k t e</column></row>
      <xsl:apply-templates />

</xsl:template>

  <xsl:template match="nachtelefone">
      <xsl:apply-templates />
  </xsl:template>
 <xsl:template match="tns:telefoneModel">
    
    <table><xsl:apply-templates /></table>
    </xsl:template>
    
      <xsl:template match="tns:telefon">
    
    <row><column color="white"><xsl:value-of select="./tns:art" /></column><column color="white"><xsl:value-of select="./tns:telefon" /></column></row>
    </xsl:template>
    
    
    <xsl:template match="objekt">

<row><column number-columns-spanned="4" align="center"> <xsl:apply-templates /></column></row>




</xsl:template>
    
    
</xsl:stylesheet>
