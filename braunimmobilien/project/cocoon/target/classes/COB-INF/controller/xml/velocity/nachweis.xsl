<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns="http://www.w3.org/1999/xhtml" version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:xi="http://www.w3.org/2001/XInclude">
          
            <xsl:template match="/"><text>Dies ist der Text: <xsl:apply-templates/></text></xsl:template>
            <xsl:template match="variablen"><xsl:apply-templates/></xsl:template>
<xsl:template match="angebot"><xsl:apply-templates/></xsl:template>
<xsl:template match="angnr">Angnr: <xsl:apply-templates/></xsl:template>
<xsl:template match="angkurzbeschreibung">Kurzbeschreibung : <xsl:apply-templates/></xsl:template>
<xsl:template match="anglagebeschreibung">Lagebeschreibung : <xsl:apply-templates/></xsl:template>
<xsl:template match="xtyp">Xtyp : <xsl:apply-templates/></xsl:template>
<xsl:template match="xtypmitexposee">Mit Exposee :  <xsl:apply-templates/></xsl:template>
<xsl:template match="objekte">Objekte : <xsl:apply-templates/></xsl:template>
<xsl:template match="objekt">Objekt : <xsl:apply-templates/></xsl:template>
<xsl:template match="mitarbeiter">Mitarbeiter : <xsl:apply-templates/></xsl:template>
<xsl:template match="datum">Datum : <xsl:apply-templates/></xsl:template>
<xsl:template match="xdocuments"></xsl:template>
            </xsl:stylesheet>
