<?xml version="1.0" encoding="UTF-8"?>
    		<xsl:stylesheet version="1.0" 
    		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    		xmlns:xi="http://www.w3.org/2001/XInclude"
    		xmlns:fo="http://www.w3.org/1999/XSL/Format" >
    		<xsl:template match="/ | @* | node()">
    		<xsl:copy>            
    		<xsl:apply-templates select="@* | node()" />
    		</xsl:copy>
    		</xsl:template>
    		</xsl:stylesheet>