<?xml version="1.0"?>
<!-- Author: Hans-Heinrich Braun "Hannover@kbr-immobilien.de" -->
<!-- Version: 1.0 -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
   <page> <title>Angebot</title>
   <content> <xsl:apply-templates/></content>
    </page>
    </xsl:template>
     <xsl:template match="angebot">
   <para> 
    <xsl:apply-templates/>
    </para>
    </xsl:template>

     <xsl:template match="id">
   <para> 
    <xsl:apply-templates/>
    </para>
    </xsl:template>
    <xsl:template match="angstatus">
   <para> 
    <xsl:apply-templates select="statustext"/>
    </para>
    </xsl:template>
     <xsl:template match="angkurzbeschreibung">
   <para> 
    <xsl:apply-templates/>
    </para>
    </xsl:template>
     <xsl:template match="anglagebeschreibung">
   <para> 
    <xsl:apply-templates/>
    </para>
    </xsl:template>
     <xsl:template match="angaufdatum">
   <para> 
    <xsl:apply-templates/>
    </para>
    </xsl:template>
    <xsl:template match="anganz">
   <para> 
    <xsl:apply-templates/>
    </para>
    </xsl:template>
     <xsl:template match="kondition">
   <para> 
    <xsl:apply-templates select="kontext"/>
    </para>
    </xsl:template>
     <xsl:template match="angobjzuords">
   
    </xsl:template>
 </xsl:stylesheet>
