<?xml version="1.0" encoding="UTF-8"?>
<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tns="http://braunimmobilien/webapp/person" version="1.0">

  

  <xsl:template match="/">
    <html>
      <head> <title>Nachweisliste Kunde</title>
  </head>
  <body>
    <h3>Nachweisliste Kunde</h3>
        <xsl:apply-templates />
      </body>
    </html>
  </xsl:template>
  
<xsl:template match="id">
   <p>Kundennr:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  <xsl:template match="kundenart">
   <p>Kundenart:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  <xsl:template match="status">
   <p>Status:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  <xsl:template match="datum">
   <p>Aufnahmedatum:
      <xsl:apply-templates /></p>
   
  </xsl:template>
   <xsl:template match="name">
   <p>Name:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  
  <xsl:template match="adresse">
   <p>Adresse:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  <xsl:template match="anschrift">
   <p>Anschrift:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  
  <xsl:template match="info">
   <p>Status:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  <xsl:template match="firma">
   <p>Firma:
      <xsl:apply-templates /></p>
  </xsl:template>
  
   <xsl:template match="telefone">
   <p>Telefone:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  <xsl:template match="eigtstatus">
   <p>Person  Status:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  <xsl:template match="telefon">
   <p>Telefon:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  <xsl:template match="email">
   <p>Email:
      <xsl:apply-templates /></p>
   
  </xsl:template>
  <xsl:template match="aktuell">
   <p>Datensatz aktuell:
      <xsl:apply-templates /></p>
  </xsl:template>
  
   <xsl:template match="aufdat">
   <p>Aufnahmedatum:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  
  <xsl:template match="letztdat">
   <p>Datum letzter Kontakt:
      <xsl:apply-templates /></p>
  </xsl:template>
  
  <xsl:template match="vordat">
   <p>Datum Wiedervorlage:
      <xsl:apply-templates /></p>
  </xsl:template>
 
  
   <xsl:template match="nachweise">
   <p>Nachweise</p>
   <table>
   <tr><td>NachweisId</td><td>Xtyp</td><td>Datum</td><td>Angnr</td><td>angnr1</td><td>angnr2</td><td>Unterlagen1</td><td>Unterlagen2</td><td>Unterlagen3</td><td>Unterlagen4</td></tr>
      <xsl:apply-templates />
   </table>
  </xsl:template>
  <xsl:template match="nachweis">
   
   <tr>
      <xsl:apply-templates />
   </tr>
  </xsl:template>
  <xsl:template match="nachweisid">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template>
  <xsl:template match="nachdatum">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template>
  
   <xsl:template match="xtyplang"/>
  
  <xsl:template match="xtyp">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template>
   <xsl:template match="angnr">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template>
  
   <xsl:template match="angnr1"/>
  
    <xsl:template match="tns:telefoneModel">
    
    <table><xsl:apply-templates /></table>
    </xsl:template>
    
      <xsl:template match="tns:telefon">
    
    <tr><td><xsl:value-of select="./tns:art" /></td><td><xsl:value-of select="./tns:telefon" /></td></tr>
    </xsl:template>
  
      <xsl:template match="angnr2"/>
  
  
   <xsl:template match="unterlagen1">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template>
 
  <xsl:template match="unterlagen2">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template>
  
   <xsl:template match="unterlagen3">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template>
  
   <xsl:template match="unterlagen4">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template> 
  
  <xsl:template match="nachperson">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template>
  
  
  
    
</xsl:stylesheet>
