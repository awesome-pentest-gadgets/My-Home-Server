#!/bin/bash

# Delete all the existing rules
iptables -F

# Set default chain policies
#iptables -P INPUT DROP
#iptables -P FORWARD DROP

#iptables -A INPUT -p tcp --dport ssh -j ACCEPT
#iptables -P INPUT DROP

# Log all the requests
#iptables -N LOG_DROP
#iptables -A LOG_DROP -j LOG --log-prefix '[IPTABLES DROP] : '
#iptables -A LOG_DROP -j DROP

iptables -A INPUT -j LOG --log-prefix='[netfilter] '

exit 0
