#!/bin/bash
PID_FILE=/var/run/mhs.pid
LOG_FILE=/var/log/mhs.log

# Tests if a PID file exists, check of the process if running
if [ -f LOG_FILE ]; then
	echo Another process exists already for MHS.
	exit 1
fi



exit 0
