<?xml version="1.0" encoding="ISO-8859-1"?>
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
  <email:to>braunimmobilien@online.de</email:to>
    <email:to><xsl:value-of select="//document/variablen/person/email"/></email:to>
   <email:subject><xsl:value-of select="//document/variablen/subject"/></email:subject>
   <email:body mime-type="text/plain"  charset="UTF-8">

<xsl:value-of select="//document/variablen/person/briefanrede"/>,
<xsl:text>
bei der von Ihnen angeforderten Immobilie:

</xsl:text>
<xsl:value-of select="//document/variablen/angebot/angnr"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/angkurzbeschreibung"/><xsl:text>, </xsl:text><xsl:value-of select="//document/variablen/angebot/anglagebeschreibung"/>
<xsl:text>

haben sich neue Aspekte, die die  Vermarktung betreffen, ergeben.

Um hier eine Klärung herbeizuführen benötigen wir noch einige Wochen Zeit.

Ich werde vorraussichltich im </xsl:text><xsl:value-of select="//document/variablen/unterlagen1"/><xsl:text> mit einem überarbeiteten Exposee auf Sie zukommen.

Bitte haben Sie Verständis dafür, daß ich vor Abklärung der neuen Gesichtspunke keine Unterlagen herausgeben kann.

Mit freundlichen Grüßen

 Hans-Heinrich Braun

Braun Immobilien
Eichstraße 33
30161 Hannover
Handy: 0176/34440885
</xsl:text>
<xsl:value-of select="//document/variablen/homepagebraun"/>
<xsl:text>
Anhänge:

Anschreiben xwart.pdf
</xsl:text>
</email:body>
<email:attachment src="cocoon:///de_DE/nachweise.pdf" mime-type="application/pdf"  name="xwart.pdf"/>
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
