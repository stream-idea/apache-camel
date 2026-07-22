REFERENCE https://github.com/cyberlogic-consulting/cyberlogic-yt-camel-for-beginners/tree/main/message-mapping

CAMEL DATA FORMAT https://camel.apache.org/components/4.18.x/dataformats/index.html


Apache camel ha many ways to transofrm messages.
The most common are
- processors
- beans
- transform DSL element





.unmarshal().json() 
.marshal().json()

Eseguono il parse / scrittura della stringa senza dover instanziare l'obkject mapper


Usa il metodo process per trasformazioni semplici
Per eseguire trasformazioni più complesse utilizza i bean