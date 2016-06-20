<xsl:stylesheet xmlns="http://www.w3.org/1999/xhtml" version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
          
            <xsl:template match="/"><text><xsl:apply-templates/></text></xsl:template>
            <xsl:template match="name">$name</xsl:template>
            </xsl:stylesheet>