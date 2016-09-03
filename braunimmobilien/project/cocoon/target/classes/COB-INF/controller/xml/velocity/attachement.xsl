<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:email="http://apache.org/cocoon/transformation/sendmail"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:xi="http://www.w3.org/2001/XInclude"> 
  <xsl:template match="document">
  <document>
<email:attachment mime-type="application/pdf"  name="x2m.pdf">
<xsl:attribute name="src">cocoon:///exposees/de_DE/<xsl:value-of select="//document/variablen/angebot/angnr"/>.fo</xsl:attribute>
<xsl:attribute name="name"><xsl:value-of select="//document/variablen/angebot/angnr"/>.pdf</xsl:attribute>
</email:attachment>
  <xsl:if test="string-length(//document/variablen/anlage1)>0">
 <xsl:if test="contains(//document/variablen/anlage1,'.pdf')">
 <email:attachment mime-type="application/pdf">
 <xsl:attribute name="name"><xsl:value-of select="//document/variablen/anlage1"/></xsl:attribute>
<xsl:attribute name="src">cocoon:///anhang/<xsl:value-of select="//document/variablen/angebot/angnr"/>/<xsl:value-of select="//document/variablen/anlage1"/></xsl:attribute>
</email:attachment>
 </xsl:if>
 <xsl:if test="contains(//document/variablen/anlage1,'.fo')">
 <email:attachment mime-type="application/pdf">
 <xsl:attribute name="name"><xsl:value-of select="substring-before(//document/variablen/anlage1,'.fo')"/>.pdf</xsl:attribute>
<xsl:attribute name="src">cocoon:///anhang/de_DE/<xsl:value-of select="//document/variablen/anlage1"/></xsl:attribute>
</email:attachment>
 </xsl:if>
</xsl:if>
 <xsl:if test="string-length(//document/variablen/anlage2)>0">
 <xsl:if test="contains(//document/variablen/anlage2,'.pdf')">
 <email:attachment mime-type="application/pdf">
 <xsl:attribute name="name"><xsl:value-of select="//document/variablen/anlage2"/></xsl:attribute>
<xsl:attribute name="src">cocoon:///anhang/<xsl:value-of select="//document/variablen/angebot/angnr"/>/<xsl:value-of select="//document/variablen/anlage2"/></xsl:attribute>
</email:attachment>
 </xsl:if>
 <xsl:if test="contains(//document/variablen/anlage2,'.fo')">
 <email:attachment mime-type="application/pdf">
 <xsl:attribute name="name"><xsl:value-of select="substring-before(//document/variablen/anlage2,'.fo')"/>.pdf</xsl:attribute>
<xsl:attribute name="src">cocoon:///anhang/de_DE/<xsl:value-of select="//document/variablen/anlage2"/></xsl:attribute>
</email:attachment>
 </xsl:if>
</xsl:if>
 <xsl:if test="string-length(//document/variablen/anlage3)>0">
 <xsl:if test="contains(//document/variablen/anlage3,'.pdf')">
 <email:attachment mime-type="application/pdf">
 <xsl:attribute name="name"><xsl:value-of select="//document/variablen/anlage3"/></xsl:attribute>
<xsl:attribute name="src">cocoon:///anhang/<xsl:value-of select="//document/variablen/angebot/angnr"/>/<xsl:value-of select="//document/variablen/anlage3"/></xsl:attribute>
</email:attachment>
 </xsl:if>
 <xsl:if test="contains(//document/variablen/anlage3,'.fo')">
 <email:attachment mime-type="application/pdf">
 <xsl:attribute name="name"><xsl:value-of select="substring-before(//document/variablen/anlage3,'.fo')"/>.pdf</xsl:attribute>
<xsl:attribute name="src">cocoon:///anhang/de_DE/<xsl:value-of select="//document/variablen/anlage3"/></xsl:attribute>
</email:attachment>
 </xsl:if>
</xsl:if>
 <xsl:if test="string-length(//document/variablen/anlage4)>0">
 <xsl:if test="contains(//document/variablen/anlage4,'.pdf')">
 <email:attachment mime-type="application/pdf">
 <xsl:attribute name="name"><xsl:value-of select="//document/variablen/anlage4"/></xsl:attribute>
<xsl:attribute name="src">cocoon:///anhang/<xsl:value-of select="//document/variablen/angebot/angnr"/>/<xsl:value-of select="//document/variablen/anlage4"/></xsl:attribute>
</email:attachment>
 </xsl:if>
 <xsl:if test="contains(//document/variablen/anlage4,'.fo')">
 <email:attachment mime-type="application/pdf">
 <xsl:attribute name="name"><xsl:value-of select="substring-before(//document/variablen/anlage4,'.fo')"/>.pdf</xsl:attribute>
<xsl:attribute name="src">cocoon:///anhang/de_DE/<xsl:value-of select="//document/variablen/anlage4"/></xsl:attribute>
</email:attachment>
 </xsl:if>
</xsl:if>
 </email:sendmail>
    </document>
  
  
  
  </xsl:template>
 <xsl:template match="variablen"/>


<xsl:template match="xdoc"/>

<xsl:template match="xdocuments"/>

<xsl:template match="xvor"/>

  <xsl:template match="intern"/>
   
  
  
  <xsl:template match="@*|node()" priority="-2">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template match="text()" priority="-1">
    <xsl:value-of select="."/>
  </xsl:template>
            </xsl:stylesheet>
