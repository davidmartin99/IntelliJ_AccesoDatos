<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>LISTADO DE CANCIONES</title>
                <style>
                    table {
                    border: 4px solid green; /* Color del borde de la tabla (azul clarito) */
                    border-collapse: collapse; /* Colapsar bordes */
                    }
                    th, td {
                    padding: 8px; /* Espaciado interno */
                    border: 2px solid black; /* Borde de las celdas */
                    }
                    th {
                    background-color: #ccffcc; /* Color de fondo para la fila de encabezados (verde muy clarito) */
                    font-size: 25
                    }
                </style>
            </head>
            <body>
                <h1>LISTA DE CANCIONES</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Año</th>
                        <th>Título</th>
                        <th>Artista</th>
                        <th>Duración</th>
                        <th>¿Es Española?</th>
                    </tr>
                    <xsl:apply-templates select="Canciones/Cancion"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="Cancion">
        <tr>
            <td><xsl:value-of select="ID"/></td>
            <td><xsl:value-of select="Año"/></td>
            <td><xsl:value-of select="Titulo"/></td>
            <td><xsl:value-of select="Artista"/></td>
            <td><xsl:value-of select="Duracion"/></td>
            <td><xsl:value-of select="EsEspañola"/></td>
        </tr>
    </xsl:template>

</xsl:stylesheet>


