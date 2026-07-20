
## broker dockerizzato - APACHE ARTEMIS
docker run --detach --name broker_for_camel -p 61616:61616 -p 8161:8161 --rm apache/artemis:latest-alpine


## APACHE ARTEMIS
message-oriented middleware o broker di messaggistica scirtto in java che dispone di un client completo
JMS (java message service). Implementa i seguenti protocolli message queue
1. OpenWire
2. AMQP (advanced message queuing protocol)
3. MQTT
4. STOMP


## accedi poi alla pagina

http://localhost:8161

controlla sempre le porte dal terminale di docker


# DOCUMENTAZIONE A QUESTO LINK
https://artemis.apache.org/components/artemis/documentation/latest/docker.html#official-images

# login
come da documentazione inserire 
artemis
sia come username sia come password



## JMS
## JAVA MESSAGE SERVICE
https://camel.apache.org/components/4.18.x/jms-component.html



## per eseguire i task di compilazione di VS code
Ctrl + Shift + B


## per creare i task di build
Ctrl + Shift + P
Scegli Tasks:Configure Task
Seleziona Configure Default Build task
e metti questo come body di tasks.json

{
    // See https://go.microsoft.com/fwlink/?LinkId=733558
    // for the documentation about the tasks.json format
    "version": "2.0.0",
    "tasks":  [
        {
            "label": "Maven Build and Resolve",
            "type": "shell",
            "command": "mvn clean install && mvn dependency:resolve",
            "group": {
                "kind": "build",
                "isDefault": true
            },
            "problemMatcher": []
        }
    ]
}




https://camel.apache.org/components/4.18.x/languages/simple-language.html


## message routing patterns
https://camel.apache.org/components/4.18.x/eips/enterprise-integration-patterns.html#_message_routing