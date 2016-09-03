<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="">
  
  <xsl:strip-space elements="*"/>
  
 <xsl:template match="variablen"/>
 
  <xsl:template match="document">
    <xsl:apply-templates/>
  </xsl:template>
  
<xsl:template match="intern">
    <xsl:apply-templates/>
  </xsl:template>
  
  <xsl:template match="xdocument">
    <xsl:apply-templates/>
  </xsl:template>
  
  <xsl:template match="xdoc">
    <xsl:apply-templates/>
  </xsl:template>
  
    <xsl:template match="datum">
    <xsl:value-of select="//document/variablen/datum"/></xsl:template>
  <xsl:template match="unterlagen1">
   <xsl:if test="string-length(//document/variablen/unterlagen1)>0">
   <xsl:value-of select="//document/variablen/unterlagen1"/></xsl:if> </xsl:template> 
   <xsl:template match="unterlagen2">
   <xsl:if test="string-length(//document/variablen/unterlagen2)>0">
   <xsl:value-of select="//document/variablen/unterlagen2"/></xsl:if> </xsl:template> 
<xsl:template match="unterlagen3">
   <xsl:if test="string-length(//document/variablen/unterlagen3)>0">
   <xsl:value-of select="//document/variablen/unterlagen3"/></xsl:if> </xsl:template> 
<xsl:template match="unterlagen4">
   <xsl:if test="string-length(//document/variablen/unterlagen4)>0">
   <xsl:value-of select="//document/variablen/unterlagen4"/></xsl:if> </xsl:template> 

  <xsl:template match="anlage1">
   <xsl:if test="string-length(//document/variablen/anlage1)>0">
   <xsl:value-of select="//document/variablen/anlage1"/></xsl:if> </xsl:template> 
   <xsl:template match="anlage2">
   <xsl:if test="string-length(//document/variablen/anlage2)>0">
   <xsl:value-of select="//document/variablen/anlage2"/></xsl:if> </xsl:template> 
<xsl:template match="anlage3">
   <xsl:if test="string-length(//document/variablen/anlage3)>0">
   <xsl:value-of select="//document/variablen/anlage3"/></xsl:if> </xsl:template> 
<xsl:template match="anlage4">
   <xsl:if test="string-length(//document/variablen/anlage4)>0">
   <xsl:value-of select="//document/variablen/anlage4"/></xsl:if> </xsl:template> 



  <xsl:template match="unterlagen">
   <xsl:if test="string-length(//document/variablen/unterlagen1)>0">
   <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>-<xsl:value-of select="//document/variablen/unterlagen1"/></fo:inline></fo:block></xsl:if>
   <xsl:if test="string-length(//document/variablen/unterlagen2)>0">
   <fo:block font-family="Arial" font-size="12pt" language="DE"><fo:inline>-<xsl:value-of select="//document/variablen/unterlagen2"/></fo:inline></fo:block></xsl:if>
   <xsl:if test="string-length(//document/variablen/unterlagen3)>0">
   <fo:block font-family="Arial" font-size="12pt" language="DE"><fo:inline>-<xsl:value-of select="//document/variablen/unterlagen3"/></fo:inline></fo:block></xsl:if>
   <xsl:if test="string-length(//document/variablen/unterlagen4)>0">
   <fo:block font-family="Arial" font-size="12pt" language="DE"><fo:inline>-<xsl:value-of select="//document/variablen/unterlagen4"/></fo:inline></fo:block></xsl:if>
  </xsl:template> 
    
     <xsl:template match="anlagen"> 
       
     <xsl:if test="//document/variablen/xtypmitexposee='true'">
     <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>	
    Anlagen:</fo:inline></fo:block>
   
    <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>	
    Exposee <xsl:value-of select="//document/variablen/angebot/angnr"/></fo:inline></fo:block>
    <xsl:if test="string-length(//document/variablen/angebot1/angnr)>0">
    <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>Exposee <xsl:value-of select="//document/variablen/angebot1/angnr"/></fo:inline></fo:block>
    </xsl:if> 
   </xsl:if>
     
     <xsl:if test="string-length(//document/variablen/anlage1)>0">
      <xsl:if test="//document/variablen/xtypmitexposee='false'">
     <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>	
    Anlagen:</fo:inline></fo:block>  
    </xsl:if>
       <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>




                      <xsl:choose>
<xsl:when test="contains(//document/variablen/anlage1,'.fo')"><xsl:value-of select="substring-before(//document/variablen/anlage1,'.fo')"/></xsl:when>	
    <xsl:when test="contains(//document/variablen/anlage1,'.pdf')"><xsl:value-of select="substring-before(//document/variablen/anlage1,'.pdf')"/></xsl:when>
 <xsl:otherwise>
<xsl:value-of select="//document/variablen/anlage1"/>
</xsl:otherwise>
</xsl:choose>

</fo:inline></fo:block>
   
     <xsl:if test="string-length(//document/variablen/anlage2)>0">
       <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>


               <xsl:choose>
<xsl:when test="contains(//document/variablen/anlage2,'.fo')"><xsl:value-of select="substring-before(//document/variablen/anlage2,'.fo')"/></xsl:when>	
    <xsl:when test="contains(//document/variablen/anlage2,'.pdf')"><xsl:value-of select="substring-before(//document/variablen/anlage2,'.pdf')"/></xsl:when>
 <xsl:otherwise>
<xsl:value-of select="//document/variablen/anlage2"/>
</xsl:otherwise>
</xsl:choose>


</fo:inline></fo:block></xsl:if>
  
   <xsl:if test="string-length(//document/variablen/anlage3)>0">
       <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>

               <xsl:choose>
<xsl:when test="contains(//document/variablen/anlage3,'.fo')"><xsl:value-of select="substring-before(//document/variablen/anlage3,'.fo')"/></xsl:when>	
    <xsl:when test="contains(//document/variablen/anlage3,'.pdf')"><xsl:value-of select="substring-before(//document/variablen/anlage3,'.pdf')"/></xsl:when>
 <xsl:otherwise>
<xsl:value-of select="//document/variablen/anlage3"/>
</xsl:otherwise>
</xsl:choose>


</fo:inline></fo:block></xsl:if>
  
   <xsl:if test="string-length(//document/variablen/anlage4)>0">
       <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>


               <xsl:choose>
<xsl:when test="contains(//document/variablen/anlage4,'.fo')"><xsl:value-of select="substring-before(//document/variablen/anlage4,'.fo')"/></xsl:when>	
    <xsl:when test="contains(//document/variablen/anlage4,'.pdf')"><xsl:value-of select="substring-before(//document/variablen/anlage4,'.pdf')"/></xsl:when>
 <xsl:otherwise>
<xsl:value-of select="//document/variablen/anlage4"/>
</xsl:otherwise>
</xsl:choose>

</fo:inline></fo:block></xsl:if>
  
  
   </xsl:if>
<!--
    <xsl:if test="string-length(//document/variablen/anlage2)>0">
     <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>	<xsl:value-of select="//document/variablen/anlage2"/></fo:inline></fo:block></xsl:if>
    
    <xsl:if test="string-length(//document/variablen/anlage3)>0">
     <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>	<xsl:value-of select="//document/variablen/anlage3"/></fo:inline></fo:block></xsl:if>
   
    <xsl:if test="string-length(//document/variablen/anlage4)>0">
      <fo:block font-family="Arial" font-size="12pt" language="DE">
	<fo:inline>	<xsl:value-of select="//document/variablen/anlage4"/></fo:inline></fo:block></xsl:if>
 -->
</xsl:template>
   
  <xsl:template match="plz">
  <xsl:if test="string-length(//document/variablen/person/adresseort)>0">
        <xsl:value-of select="concat(//document/variablen/person/adresseort,' ')"/></xsl:if>
  </xsl:template>
 
  <xsl:template match="strasse">
      <xsl:if test="string-length(//document/variablen/person/adressestrasse)>0">
        <xsl:value-of select="concat(//document/variablen/person/adressestrasse,' ')"/>
      </xsl:if>
  </xsl:template>
  
  
     <xsl:template match="anschrift">
    <fo:table>
  <fo:table-column column-width="12cm"/>
    <fo:table-body>
 <xsl:for-each select="//document/variablen/person/anschrift/zeilen/zeile">
   <fo:table-row >
      <fo:table-cell>
        <fo:block text-align="left">
 <xsl:value-of select="."/></fo:block>
      </fo:table-cell>
 </fo:table-row>
 </xsl:for-each>
  </fo:table-body>
</fo:table> 
    </xsl:template> 
    
       <xsl:template match="facebookhomepage">
 <xsl:value-of select="//document/variablen/ich/fanpage"/>
    </xsl:template> 
     <xsl:template match="firmahomepage">
 <xsl:value-of select="//document/variablen/ich/homepage"/>
    </xsl:template>
     <xsl:template match="firmatel">
 <xsl:value-of select="//document/variablen/ich/tel"/>
    </xsl:template>
   <xsl:template match="firmaemail">
 <xsl:value-of select="//document/variablen/ich/email"/>
    </xsl:template>
     <xsl:template match="firmaname">
 <xsl:value-of select="//document/variablen/ich/name"/>
    </xsl:template>
     <xsl:template match="firma">
 <xsl:value-of select="//document/variablen/ich/firma"/>
    </xsl:template>
     <xsl:template match="firmastrasse">
 <xsl:value-of select="//document/variablen/ich/strasse"/>
    </xsl:template>
     <xsl:template match="firmaort">
 <xsl:value-of select="//document/variablen/ich/ort"/>
    </xsl:template>  
    
    
    
    
 
   
   
   
   
   
   
   
     <xsl:template match="betreff">
 <xsl:value-of select="//document/variablen/betreff"/>
    </xsl:template>
   
  <xsl:template match="ind">
    <fo:table>
  <fo:table-column column-width="12cm"/>
    <fo:table-body>
 <xsl:for-each select="//document/variablen/ind/zeilen/zeile">
   <fo:table-row >
      <fo:table-cell>
        <fo:block text-align="left">
 <xsl:value-of select="."/></fo:block>
      </fo:table-cell>
 </fo:table-row>
 </xsl:for-each>
  </fo:table-body>
</fo:table> 
    </xsl:template>
    
 <xsl:template match="objekt">
 <xsl:value-of select="//document/variablen/objekt"/>
    </xsl:template>
 
 <xsl:template match="objekte">
 <xsl:for-each select="//document/variablen/angebot/objekte/objekt"> 
<fo:inline font-weight="bold">
<fo:block/>
</fo:inline>
<fo:inline font-weight="bold">
<fo:leader leader-length="0pt"/>
 <xsl:value-of select="."/>
</fo:inline>
<fo:inline font-weight="bold">
<fo:block/>
</fo:inline>
 </xsl:for-each>
 
    </xsl:template>
    
    <xsl:template match="objekte1">
 <xsl:for-each select="//document/variablen/angebot1/objekte/objekt"> 
<fo:inline font-weight="bold">
<fo:block/>
</fo:inline>
<fo:inline font-weight="bold">
<fo:leader leader-length="0pt"/>
 <xsl:value-of select="."/>
</fo:inline>
<fo:inline font-weight="bold">
<fo:block/>
</fo:inline>
 </xsl:for-each>
 
    </xsl:template>
    
 
  <xsl:template match="kurzbeschreibung">
   <xsl:value-of select="//document/variablen/angebot/angkurzbeschreibung"/>
  </xsl:template>
   
  <xsl:template match="lagebeschreibung">
      <xsl:value-of select="//document/variablen/angebot/anglagebeschreibung"/>
  </xsl:template>
  
  
  
  <xsl:template match="ich"/>
  
  
  
  <xsl:template match="angnr">
      <xsl:value-of select="//document/variablen/angebot/angnr"/>
  </xsl:template>
  
   <xsl:template match="kurzbeschreibung1">
   <xsl:value-of select="//document/variablen/angebot1/angkurzbeschreibung"/>
  </xsl:template>
   
  <xsl:template match="lagebeschreibung1">
      <xsl:value-of select="//document/variablen/angebot1/anglagebeschreibung"/>
  </xsl:template>
  
  <xsl:template match="angnr1">
      <xsl:value-of select="//document/variablen/angebot1/angnr"/>
  </xsl:template>
  
  
   <xsl:template match="kurzbeschreibung2">
   <xsl:value-of select="//document/variablen/angebot1/angkurzbeschreibung"/>
  </xsl:template>
   
  <xsl:template match="lagebeschreibung2">
      <xsl:value-of select="//document/variablen/angebot1/anglagebeschreibung"/>
  </xsl:template>
  
  <xsl:template match="angnr2">
      <xsl:value-of select="//document/variablen/angebot2/angnr"/>
  </xsl:template>
  
  <xsl:template match="xtyp">
      <xsl:value-of select="//document/variablen/xtyp"/>
  </xsl:template>
  
  <xsl:template match="briefanrede">
      <xsl:value-of select="//document/variablen/person/briefanrede"/>
  </xsl:template>
   
  <xsl:template match="mitarbeiter">
       <xsl:value-of select="//document/variablen/mitarbeiter"/>
  </xsl:template>
 
  <xsl:template match="xtk">
      <xsl:value-of select="//document/variablen/xtyp"/>
  </xsl:template>
  
  <xsl:template match="@*|node()" priority="-2">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  
  <xsl:template match="text()" priority="-1">
    <xsl:value-of select="."/>
  </xsl:template>

</xsl:stylesheet>
