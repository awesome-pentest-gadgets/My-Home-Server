#!/bin/bash

iptables –F INPUT
iptables -I INPUT --dport 22 -j ACCEPT
iptables -P INPUT DROP

iptables -N LOG_DROP
iptables -A LOG_DROP -j LOG --log-prefix '[IPTABLES DROP] : '
iptables -A LOG_DROP -j DROP

exit 0
