<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>LISTADO DE ALUMNOS</title>
            </head>
            <body>
                <h1>LISTA DE ALUMNOS</h1>
                <table border="1">
                    <tr>
                        <th>Nombre</th>
                        <th>Edad</th>
                    </tr>
                    <xsl:apply-templates select="listadealumnos/alumno"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="alumno">
        <tr>
            <td><xsl:value-of select="nombre"/></td>
            <td><xsl:value-of select="edad"/></td>
        </tr>
    </xsl:template>

</xsl:stylesheet>
