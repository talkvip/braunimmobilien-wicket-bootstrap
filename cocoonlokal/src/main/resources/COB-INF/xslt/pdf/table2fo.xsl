<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:fo="http://www.w3.org/1999/XSL/Format">

  <xsl:template match="page">
   <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
   
   <fo:layout-master-set>
    <fo:simple-page-master master-name="first"
          margin-right="1.5cm"
          margin-left="1.5cm"
          margin-bottom="2cm"
          margin-top="1cm"
          page-width="21cm"
          page-height="29.7cm">
      <fo:region-body margin-top="1cm"/>
      <fo:region-before extent="7cm"/>
      <fo:region-after extent="1.5cm"/>
    </fo:simple-page-master>
  </fo:layout-master-set>


  <fo:page-sequence master-reference="first">
    <fo:static-content flow-name="xsl-region-before">
      <fo:block line-height="14pt" font-size="10pt"
    text-align="end">Seite <fo:page-number/></fo:block>
    </fo:static-content>
    <fo:static-content flow-name="xsl-region-after">
      <fo:block line-height="14pt" font-size="10pt"
    text-align="end">Seite <fo:page-number/></fo:block>
    </fo:static-content>

    <fo:flow flow-name="xsl-region-body">

      <fo:block space-before.optimum="0cm">
     <xsl:value-of select="current()/header"/>
      </fo:block>
   <xsl:apply-templates/>
 </fo:flow>
  </fo:page-sequence>
</fo:root>
 </xsl:template>
 <xsl:template match="title">
    <fo:block font-size="36pt" space-before.optimum="24pt" text-align="center"><xsl:apply-templates/></fo:block>
  </xsl:template>
 
 <xsl:template match="table">
  <fo:table>

<xsl:apply-templates select="current()/table-header"/>
  <fo:table-body>
 <xsl:apply-templates select="current()/row"/>
 </fo:table-body>
      </fo:table>
   
</xsl:template>

<xsl:template match="table-header">
<xsl:apply-templates/>
</xsl:template>

<xsl:template match="row">
<fo:table-row>
<xsl:apply-templates/>
</fo:table-row>
</xsl:template>

<xsl:template match="ueberschriften">
<fo:table-header>
 <fo:table-row>
<xsl:apply-templates/>
</fo:table-row>
</fo:table-header>
</xsl:template>


<xsl:template match="format">
<xsl:apply-templates/>
</xsl:template>


<xsl:template match="ueberschrift">
<fo:table-cell>
        <fo:block font-weight="bold" text-align="center" vertical-align="middle"
      border-width="1pt" border-color="black" background-color="white">
      <xsl:apply-templates/>
   </fo:block>
      </fo:table-cell>
</xsl:template>

 <xsl:template match="width">
<fo:table-column><xsl:attribute name="column-width"><xsl:apply-templates/></xsl:attribute></fo:table-column>
</xsl:template>


<xsl:template match="column">
<fo:table-cell>
<xsl:attribute name="number-columns-spanned"><xsl:choose>
      <xsl:when test="@number-columns-spanned"><xsl:value-of select="@number-columns-spanned"/></xsl:when>
      <xsl:otherwise>1</xsl:otherwise></xsl:choose></xsl:attribute>
        <fo:block border-right-width="0.5pt"  vertical-align="middle" >
        <xsl:attribute name="background-color"><xsl:choose>
      <xsl:when test="@color"><xsl:value-of select="@color"/></xsl:when>
      <xsl:otherwise>white</xsl:otherwise></xsl:choose></xsl:attribute>
        <xsl:attribute name="text-align"><xsl:choose>
      <xsl:when test="@align"><xsl:value-of select="@align"/></xsl:when>
      <xsl:otherwise>left</xsl:otherwise></xsl:choose></xsl:attribute>
       <xsl:attribute name="font-weight"><xsl:choose>
      <xsl:when test="@weight"><xsl:value-of select="@weight"/></xsl:when>
      <xsl:otherwise>normal</xsl:otherwise></xsl:choose></xsl:attribute>
 <xsl:apply-templates/></fo:block>
      </fo:table-cell>
</xsl:template>

 <xsl:template match="para">
    <fo:block font-size="12pt" space-before.optimum="12pt" text-align="center"><xsl:apply-templates/></fo:block>
  </xsl:template>


</xsl:stylesheet>
