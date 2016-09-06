<?xml version="1.0" encoding="UTF-8"?>
<!--
  Copyright 1999-2004 The Apache Software Foundation

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<xsl:stylesheet version="1.0" 
xmlns:email="http://apache.org/cocoon/transformation/sendmail"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:xi="http://www.w3.org/2001/XInclude">
  
  <xsl:template match="document">
  <document>
  <email:sendmail>
 <!--  <email:to><xsl:value-of select="//document/variablen/person/email"/></email:to>
<email:to><xsl:value-of select="//document/variablen/emailbraun"/></email:to>
   
   <email:subject><xsl:value-of select="//document/variablen/subject"/></email:subject>-->
   <email:body mime-type="text/plain"  charset="UTF-8">

<xsl:value-of select="//document/variablen/person/briefanrede"/>,
<xsl:text>
wunschgemäß bieten wir ihnen nachfolgendes Objekt an:

</xsl:text>
<xsl:value-of select="//document/variablen/angebot/angnr"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/angkurzbeschreibung"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/anglagebeschreibung"/>
<xsl:text>
</xsl:text>
 <xsl:for-each select="//document/variablen/angebot/objekte/objekt"> 
 <xsl:value-of select="."/><xsl:text>
</xsl:text>
 </xsl:for-each>

<xsl:text>

Die Daten und Bedingungen wollen Sie bitte aus beiliegenden Anhängen entnehmen

Benachrichtigen Sie uns bitte, wenn Sie eine Besichtigung vornehmen wollen,
damit wir uns zur Verfügung halten können.

Mit freundlichen Grüßen
    </xsl:text>
<xsl:value-of select="//document/variablen/unterschriftbraun"/><xsl:text>
</xsl:text>
<xsl:value-of select="//document/variablen/firmabraun"/><xsl:text>
</xsl:text>
<xsl:value-of select="//document/variablen/strassebraun"/><xsl:text>
</xsl:text>
<xsl:value-of select="//document/variablen/ortbraun"/><xsl:text>
</xsl:text>
<xsl:value-of select="//document/variablen/telefonbraun"/><xsl:text>
</xsl:text>
<xsl:value-of select="//document/variablen/homepagebraun"/>
<xsl:text>

Anhänge:

Anschreiben </xsl:text><xsl:value-of select="//document/variablen/xtyp"/><xsl:text>.pdf
Exposee </xsl:text><xsl:value-of select="//document/variablen/angebot/angnr"/>.pdf<xsl:text>
</xsl:text>
 <xsl:if test="string-length(//document/variablen/anlage1)>0">
 <xsl:if test="contains(//document/variablen/anlage1,'.pdf')">
 <xsl:text></xsl:text> <xsl:value-of select="//document/variablen/anlage1"/></xsl:if>
<xsl:if test="contains(//document/variablen/anlage1,'.fo')">
 <xsl:text></xsl:text> <xsl:value-of select="substring-before(//document/variablen/anlage1,'.fo')"/>.pdf</xsl:if><xsl:text>
</xsl:text>
</xsl:if>
 <xsl:if test="string-length(//document/variablen/anlage2)>0">
 <xsl:if test="contains(//document/variablen/anlage2,'.pdf')">
 <xsl:text></xsl:text> <xsl:value-of select="//document/variablen/anlage2"/></xsl:if>
<xsl:if test="contains(//document/variablen/anlage2,'.fo')">
 <xsl:text></xsl:text> <xsl:value-of select="substring-before(//document/variablen/anlage2,'.fo')"/>.pdf</xsl:if><xsl:text>
</xsl:text>
</xsl:if>
 <xsl:if test="string-length(//document/variablen/anlage3)>0">
 <xsl:if test="contains(//document/variablen/anlage3,'.pdf')">
 <xsl:text></xsl:text> <xsl:value-of select="//document/variablen/anlage3"/></xsl:if>
<xsl:if test="contains(//document/variablen/anlage3,'.fo')">
 <xsl:text></xsl:text> <xsl:value-of select="substring-before(//document/variablen/anlage3,'.fo')"/>.pdf</xsl:if><xsl:text>
</xsl:text>
</xsl:if>
 <xsl:if test="string-length(//document/variablen/anlage4)>0">
 <xsl:if test="contains(//document/variablen/anlage4,'.pdf')">
 <xsl:text></xsl:text> <xsl:value-of select="//document/variablen/anlage4"/></xsl:if>
<xsl:if test="contains(//document/variablen/anlage4,'.fo')">
 <xsl:text></xsl:text> <xsl:value-of select="substring-before(//document/variablen/anlage4,'.fo')"/>.pdf</xsl:if><xsl:text>
</xsl:text>
</xsl:if>
</email:body>
<email:attachment src="cocoon:///de_DE/nachweise.pdf" mime-type="application/pdf"  name="x2m.pdf"/>
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
