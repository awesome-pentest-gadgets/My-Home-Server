#!/bin/bash
ETC_CONF=/etc/ntp.conf
SAVED_CONF=/opt/mhs/ntp/ntp.conf.ORIG

# Save the original /etc/ntp.conf file
if [ ! -f $SAVED_CONF ]; then
	cp $ETC_CONF $SAVED_CONF
fi

exit 0
