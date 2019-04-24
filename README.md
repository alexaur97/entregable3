#En el presente documento recogeremos todas las aclaraciones necesarias para el proyecto Acme Hacker-Rank.

#1 Cread un paquete llamado "miscellaneous" y una clase llamada "Utils.java" en la que definimos
métodos que necesitemos utilizar en momentos determinados y que se reutilizan numerosas veces.

#2 El patrón del VAT seguirá el siguiente formato: (ES)ÑNNNNNNNÑ donde (ES) es una particula opcional, las Ñ representan
letras mayúsculas o números, y las N representan números.

#3 Cuando se cancela una position, está seguirá mostrandose en el sistema aunque no se pueda aplicar a ella, pero debe mostrarse
para dejar constancia de que existe.

#4 Respecto a los mensajes, como aclaración, al crearse cualquier tipo de mensaje, este creará una versión para el actor que lo envía y otra version para el que lo recibe. La diferencia que habrá en estos dos es que la propiedad "owner" indicará quién es el "propietario" del mensaje y por lo tanto al que se le mostrará en sus mensajes.

#5 El criterio elegido para catalogar un mensaje como spam es el siguiente:
- Cuando un mensaje recibido por un usario contiene en el asunto alguna de las palabras spam.
- Cuando un mensaje recibido por un usuario contiene en el cuerpo alguna de las palabras spam.
Además lo que conlleva el marcar un mensaje como spam, es que su booleano "SPAM" se ponga a "true"

#6 Hemos considerado que el sender de un Message puede ser nulo para cuando se dé el caso en
que un Actor reciba una notificación automática del sistema, en cuyo caso ese mensaje no lo ha enviado
ningún Actor.

#7 Las notificaciones automáticas para cuando se publica una nueva Position que coincide con 
el criterio de búsqueda de un Hacker funcionan de la siguiente manera: cuando una Position es 
publicada, es decir, pasa a modo FINAL, se recorren los Finders de cada Hacker. Por cada Finder cuyos
campos de búsqueda coincidan con las características de la nueva Position, se enviará una notificación
al Hacker propietario de ese Finder

