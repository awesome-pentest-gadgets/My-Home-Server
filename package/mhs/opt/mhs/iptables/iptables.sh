#!/bin/bash

# Delete all the existing rules
iptables -F

# Set default chain policies
#iptables -P INPUT DROP
#iptables -P FORWARD DROP

iptables -A INPUT -p tcp --dport ssh -j ACCEPT

iptables -A INPUT -p tcp --dport https -j ACCEPT
iptables -A INPUT -p tcp --sport https -j ACCEPT
iptables -A INPUT -p tcp --dport http -j ACCEPT
iptables -A INPUT -p tcp --sport http -j ACCEPT

# Accept the DNS requests
iptables -A INPUT --protocol udp --source-port domain -j ACCEPT
iptables -A OUTPUT --protocol udp --destination-port domain -j ACCEPT
iptables -A INPUT --protocol tcp --source-port domain -j ACCEPT
iptables -A OUTPUT --protocol tcp --destination-port domain -j ACCEPT

# Logs the unknowns inputs
iptables -A INPUT -j LOG --log-prefix='[netfilter] '

# Drop all the other inputs
iptables -P INPUT DROP

exit 0
