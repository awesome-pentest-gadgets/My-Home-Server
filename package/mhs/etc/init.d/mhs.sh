#!/bin/bash
USER=pi
NAME=mhs
DAEMON=/opt/mhs/mhsd.sh
OPTIONS=

start() {
	log_daemon_msg "Starting system $NAME Daemon"
	start-stop-daemon --background --name $NAME --start --quiet --chuid $USER --exec $DAEMON -- $OPTIONS
	log_end_msg $?
}

stop() {
	log_daemon_msg "Stopping system $NAME Daemon"
    start-stop-daemon --name $NAME --stop --retry 5 --quiet --name $NAME
	log_end_msg $?
}

case "$1" in

        start)
			start();
        ;;

        stop)
			stop();
        ;;

        restart|reload|force-reload)
			stop();
			start();
        ;;

        force-stop)
			stop();
			killall -q $NAME || true
			sleep 2
            killall -q -9 $NAME || true
        ;;

		status)
            status_of_proc "$NAME" "$DAEMON" "system-wide $NAME" && exit 0 || exit $?
            ;;
		
		*)
			echo "Usage: /etc/init.d/$NAME {start|stop|force-stop|restart|reload|force-reload|status}"
            exit 1
        ;;
				
esac

exit 0
