#!/bin/bash
PID_FILE=/var/run/mhs.pid
LOG_FILE=/var/log/mhs.log

# Tests if a PID file exists
if [ -f LOG_FILE ]; then
		echo "Another process exists already for MHS."
		exit 1
fi

# Start the server
java -jar *.war

exit 0
