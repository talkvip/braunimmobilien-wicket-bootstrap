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
xmlns:fo="http://www.w3.org/1999/XSL/Format"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:xi="http://www.w3.org/2001/XInclude">
  <xsl:strip-space elements="*"/>
  <xsl:template match="document">
  <document>
   <xsl:apply-templates/>
    </document>
   </xsl:template>
<xsl:template match="variablen">
<variablen>
 <xsl:apply-templates/>
    </variablen>
   </xsl:template>
<xsl:template match="xdoc"/>

<xsl:template match="xdocuments"><xsl:apply-templates/></xsl:template>

<xsl:template match="xvor">
<xdocument><xsl:apply-templates/></xdocument></xsl:template>

  <xsl:template match="intdoc">
    <xsl:for-each select="//document/xdocuments/xdoc/intern">
      <xsl:copy-of select="."/>
    </xsl:for-each>
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
