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
      <head> <title>REST Controller</title>
  </head>
  <body>
    <h3>REST Controller</h3>
        <xsl:apply-templates />
      </body>
    </html>
  </xsl:template>
<xsl:template match="id">
   <p>Angebotsnummer:
      <xsl:apply-templates /></p>
   
  </xsl:template>
  <xsl:template match="anganz">
   <p>Anzahl der Angebote:
      <xsl:apply-templates /></p>
   
  </xsl:template>
  <xsl:template match="angkurzbeschreibung">
   <p>Kurzbeschreibung:
      <xsl:apply-templates /></p>
   
  </xsl:template>
  <xsl:template match="anglagebeschreibung">
   <p>Lagebeschreibung:
      <xsl:apply-templates /></p>
   
  </xsl:template>
   <xsl:template match="angaufdatum">
   <p>Aufnahmedatum:
      <xsl:apply-templates /></p>
   
  </xsl:template>
  <xsl:template match="objekte">
   <p>dazugehörende Objekte
      <xsl:apply-templates /></p>
   
  </xsl:template>
    <xsl:template match="objekt">
   <p>
      <xsl:apply-templates /></p>
   
  </xsl:template>
   <xsl:template match="nachweise">
   <p>Nachweise</p>
   <table>
   <tr><td>NachweisId</td><td>Xtyp</td><td>Datum</td><td>Kundennr</td><td>Telefon</td><td>Name</td><td>Unterlagen1</td><td>Unterlagen2</td><td>Unterlagen3</td><td>Unterlagen4</td></tr>
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
   <xsl:template match="nachtelefone">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template>
  
   <xsl:template match="nachtelefon"/>
  
    <xsl:template match="tns:telefoneModel">
    
    <table><xsl:apply-templates /></table>
    </xsl:template>
    
      <xsl:template match="tns:telefon">
    
    <tr><td><xsl:value-of select="./tns:art" /></td><td><xsl:value-of select="./tns:telefon" /></td></tr>
    </xsl:template>
  
      <xsl:template match="nachkundnr">
   
   <td>
      <xsl:apply-templates />
   </td>
  </xsl:template>
  
  
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
