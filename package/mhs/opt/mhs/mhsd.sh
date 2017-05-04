#!/bin/bash
PID_FILE=/opt/mhs/mhs.pid
SERVER_LOG_FILE=/opt/mhs/logs/mhs-server.log
ACCESS_LOG_FILE=/opt/mhs/logs/mhs-access.log
PORT=5000

# Tests if a PID file exists
if [ -f LOG_FILE ]; then
		echo "Another process exists already for MHS."
		exit 1
fi

# Starts the server
nohup java -DserverLog=$SERVER_LOG_FILE -DaccessLog=$ACCESS_LOG_FILE -Dport=$PORT -jar *.war &
echo $! > PID_FILE

exit 0
