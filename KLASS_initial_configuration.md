# KLASS initial configuration #

Hacer el checkout de la carpeta jklass85.
---
El fichero conf/jklass.properties contiene algunos parametros de configuración del KLASS.

```
#Propiedades de configuracion de Java-Klass
#Wed Oct 26 17:31:30 CEST 2011
visorPDF.cmd=evince
compilaPDF.args=-quiet
compilaLTX.args=-src -quiet
visorLTX.cmd=evince
compilaPDF.cmd=pdflatex
visorPDF.args=
visorLTX.args=
matrius.max=100
compilaLTX.cmd=latex
```

Aquí debéis cambiar los valores de acuerdo a vuestro sistema operativo (también se puede hacer desde el KLASS, pero a mí no me funcionó).

---
Crear un nuevo proyecto java, y hacer: boton derecho en el proyecto -> importar -> File System, e importar los fuentes. (Si ponéis un plugin SVN al eclipse podéis crearos el proyecto directamente desde el svn).