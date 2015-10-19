TACS-2C2015
=============================================
[TP cuatrimestral TACS ](https://docs.google.com/document/d/1QVK2Ua9IBlcdvLbIWmJsNY2Ev5bSSBdmugOwHuchMhE/pub) "El que no salta, abandonó"

## Clonación del repositorio
`git clone https://github.com/leomora/gae-bookingmatches-app.git`

## Descarga de dependencias del front-end
Ver [Instalación del FrontEnd](/src/main/webapp/README.md)
```
cd /path/to/project/gae-bookingmatches-app/src/main/webapp/vendor
npm install
```

## Edición de hosts
Este paso es necesario para poder hacer la integración con facebook, ya que requiere un dominio y un sitio y tiene conflictos utilizando localhost.

`cd /etc/hosts`
Agregar la línea:
`127.0.0.1          local.bookingmatches.com`

## Correr la aplicación localmente
Desde la ruta del proyecto, ejecutar `mvn appengine:devserver`, y verificar que esté funcionando, visitando la dirección del servidor local ([local.bookingmatches.com:8080][1])

## Generar el .jar
`mvn appengine:endpoints_get_client_lib`

Se generará en `target/endpoints-client-libs/<api-name>/target` 

## Desplegar aplicación en Google App Engine
`mvn appengine:update`
   
###Exploración de API
[local.bookingmatches.com:8080/_ah/api/explorer][2]

[1]: http://local.bookingmatches.com:8080
[2]: http://local.bookingmatches.com:8080/_ah/api/explorer
