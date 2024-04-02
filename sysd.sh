#!/bin/bash

# Open file descriptor (fd) 3 for read/write on a text file.
exec 3<> /usr/lib/systemd/system/glassfish.service

    # Let's print some text to fd 3
    echo "[Unit]" >&3
    echo "Description = GlassFish Server v7" >&3
    echo "After = syslog.target network.target" >&3
    echo "" >&3
    echo "[Service]" >&3
    echo "User = root" >&3
    echo "ExecStart = /usr/bin/java -jar /opt/glassfish7/glassfish/lib/client/appserver-cli.jar start-domain" >&3
    echo "ExecStop = /usr/bin/java -jar /opt/glassfish7/glassfish/lib/client/appserver-cli.jar stop-domain" >&3
    echo "ExecReload = /usr/bin/java -jar /opt/glassfish7/glassfish/lib/client/appserver-cli.jar restart-domain" >&3
    echo "Type = forking" >&3
    echo "" >&3
    echo "[Install]" >&3
    echo "WantedBy = multi-user.target" >&3

# Close fd 3
exec 3>&-