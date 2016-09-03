<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="customer">
   <page> <title>Customer</title><xsl:apply-templates/></page>
    </xsl:template>
<xsl:template match="content">
    <xsl:apply-templates/>
    </xsl:template>
<xsl:template match="age">
   <para> <xsl:apply-templates/></para>
    </xsl:template>
<xsl:template match="name">
   <para> <xsl:apply-templates/></para>
    </xsl:template>


 </xsl:stylesheet>
