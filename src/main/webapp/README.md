#TACS-2C2015 - FrontEnd
TP cuatrimestral [TACS](https://docs.google.com/document/d/1QVK2Ua9IBlcdvLbIWmJsNY2Ev5bSSBdmugOwHuchMhE/pub)

##Instalación (para Ubuntu / Debian)

###Instalar Node, NVM y NPM
Ver posibilidades de instalación en [http://nodejs.org/](http://nodejs.org/). Debajo se listan los comandos a utilizar para instalarlo en un entorno Ubuntu / Debian

__1) Instalar NVM__

```
curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.25.3/install.sh | bash
```
__2) Instalar Versión de NVM__

```
nvm install 0.12
```

__3) Instalación de bower y grunt__

```
sudo npm install -g grunt-cli bower
```

Grunt se utilizará para que bower pueda agregar las dependencias directamente al `index.html`. Para eso, ejecutar el comando `grunt wiredep` luego de instalar alguna dependencia.

*__Nota:__ Por ahora solo funciona con los archivos JS. En caso de necesitar un CSS hay que agregarlo a mano*

__4) Descarga de dependencias__

```
cd /path/to/project/gae-bookingmatches-app/src/main/webapp/vendor
npm install
```
El comando `npm install` está configurado para correr también el comando `bower install`, el cual puede ser usado individualmente en caso de ser necesario.

Luego de este paso, debería haber 2 carpetas con las dependencias:

* `node_modules`
* `bower_components`

###Instalación de nuevas dependencias
Para todas las dependencias para ser usadas por el frontend, buscar primero en [bower](http://bower.io/#install-packages) y luego en [npm](https://docs.npmjs.com/cli/install).

No olvidar el `--save` para que cuando otro actualice el repo le aparezca también la dependencia.

##Testing
Los tests están escritos en [Jasmine](http://jasmine.github.io/) y correrlos se utilzará [Karma](http://karma-runner.github.io/)

Hay 2 formas de correr los tests:

####Corrida simple
```
npm run test-single-run
```

####Corrida permanente
```
npm test
```

En este modo, los tests se correrán cada vez que se modifique un archivo JS.
