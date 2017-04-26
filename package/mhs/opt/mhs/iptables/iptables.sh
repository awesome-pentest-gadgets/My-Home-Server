#!/bin/bash

iptables -F INPUT
iptables -A INPUT -p tcp --dport ssh -j ACCEPT
iptables -P INPUT DROP

iptables -N LOG_DROP
iptables -A LOG_DROP -j LOG --log-prefix '[IPTABLES DROP] : '
iptables -A LOG_DROP -j DROP

exit 0
