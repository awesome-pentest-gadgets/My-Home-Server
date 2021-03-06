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

# Accept the acces to the administration server
iptables -A INPUT -p tcp --dport 5000 -j ACCEPT
iptables -A OUTPUT -p tcp --sport 5000 -j ACCEPT

# Accept the DNS requests
iptables -A INPUT --protocol udp --source-port domain -j ACCEPT
iptables -A OUTPUT --protocol udp --destination-port domain -j ACCEPT
iptables -A INPUT --protocol tcp --source-port domain -j ACCEPT
iptables -A OUTPUT --protocol tcp --destination-port domain -j ACCEPT

# Reject some bad IP (trying to login)
iptables -A INPUT --protocol udp -s 61.177.172.60/24 -j REJECT
iptables -A INPUT --protocol tcp -s 61.177.172.60/24 -j REJECT
iptables -A INPUT --protocol udp -s 116.31.116.45/24 -j REJECT
iptables -A INPUT --protocol tcp -s 116.31.116.45/24 -j REJECT

# Logs the unknowns inputs
iptables -A INPUT -j LOG --log-prefix='[netfilter] '

# Drop all the other inputs
iptables -P INPUT DROP

exit 0
