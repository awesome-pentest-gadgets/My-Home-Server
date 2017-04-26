#!/bin/bash

# Delete all the existing rules
iptables -F

# Set default chain policies
#iptables -P INPUT DROP
#iptables -P FORWARD DROP

iptables -A INPUT -p tcp --dport ssh -j ACCEPT

iptables -A INPUT -p tcp --dport https -j ACCEPT
iptables -A INPUT -p tcp --sport https -j ACCEPT

iptables -A INPUT -p udp --sport domain -j ACCEPT

# Logs the unknowns inputs
iptables -A INPUT -j LOG --log-prefix='[netfilter] '

# Drop all the other inputs
iptables -P INPUT DROP

exit 0
