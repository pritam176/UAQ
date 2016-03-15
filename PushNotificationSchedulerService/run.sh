#!/bin/bash
echo Running the service. Monitor logs under logs folder.
echo Setting classpath
export CLASSPATH=$CLASSPATH:./lib/apns-1.0.0-with-dependencies.jar:./lib/commons-codec-1.3.jar:./lib/commons-httpclient-3.1.jar:./lib/commons-lang-2.4.jar:./lib/commons-logging-1.1.1.jar:./lib/gson-2.3.1.jar:./lib/httpclient-4.2.1.jar:./lib/httpcore-4.2.2.jar:./lib/httpcore-nio-4.2.2.jar:./lib/jettison-1.3.3.jar:./lib/log4j-1.2.16.jar:./lib/ojdbc6.jar:./lib/slf4j-api-1.5.8.jar:./lib/slf4j-jdk14-1.5.8.jar:./lib/PushNotification.jar
echo Start
java -Xms512m -Xmx1024m com.uaq.pn.service.PushNotificationDeliveryService
echo Completed. Refer to the log file for status.