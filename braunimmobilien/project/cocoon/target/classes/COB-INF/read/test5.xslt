<?xml version="1.0"?>
<!-- Author: Hans-Heinrich Braun "Hannover@kbr-immobilien.de" -->
<!-- Version: 1.0 -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
   <page> <title>Angstatus</title>
   <content> <xsl:apply-templates/></content>
    </page>
    </xsl:template>
     <xsl:template match="id">
   <para> 
    <xsl:apply-templates/>
    </para>
    </xsl:template>

     <xsl:template match="statustext">
   <para> 
    <xsl:apply-templates/>
    </para>
    </xsl:template>
 </xsl:stylesheet>
