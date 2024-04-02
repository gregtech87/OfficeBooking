#!/bin/bash


 systemctl start glassfish
 systemctl status glassfish

/opt/glassfish7/bin/asadmin --port 4848 change-admin-password

When prompted to type the admin password, just hit Enter and then type and verify the new password. Once that’s taken care of, enable secure login with:

/opt/glassfish7/bin/asadmin --port 4848 enable-secure-admin

[Unit]
Description = GlassFish Server v7
After = syslog.target network.target

[Service]
User = root
ExecStart = /usr/bin/java -jar /opt/glassfish7/glassfish/lib/client/appserver-cli.jar start-domain
ExecStop = /usr/bin/java -jar /opt/glassfish7/glassfish/lib/client/appserver-cli.jar stop-domain
ExecReload = /usr/bin/java -jar /opt/glassfish7/glassfish/lib/client/appserver-cli.jar restart-domain
Type = forking

[Install]
WantedBy = multi-user.targe











