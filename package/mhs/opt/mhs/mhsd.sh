#!/bin/bash
PID_FILE=/var/run/mhs.pid
SERVER_LOG_FILE=/var/log/mhs-server.log
ACCESS_LOG_FILE=/var/log/mhs-access.log
PORT=5000

# Tests if a PID file exists
if [ -f LOG_FILE ]; then
		echo "Another process exists already for MHS."
		exit 1
fi

# Start the server
java -jar *.war -DserverLog=$SERVER_LOG_FILE -DaccessLog=$ACCESS_LOG_FILE -Dport=$PORT

exit 0
