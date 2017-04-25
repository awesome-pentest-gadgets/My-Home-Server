# My Home Server
My Home Server is an easy to use server using Raspberry PI.

# Install steps
## Locale steps
1. Install Raspbian following the instructions: https://www.raspberrypi.org/downloads/raspbian/
2. Start your Raspberry PI
3. Login with pi/raspberry
4. Start the Raspbian config with: sudo raspi-config
5. In the menu "Interfacing Options", select "Enable SSH server" and quit the config tool
6. Use ifconfig to know your IP
7. close the local session and use a remote client to connect to the Raspberry PI (like putty)

## Remote steps
1. Install git with: sudo apt-get install git-core
2. Create a directory for the GIT repository: sudo mkdir /opt/mhs
3. Import the repository: cd /opt/mhs/ && sudo git clone https://github.com/stefv/My-Home-Server.git
4. Checkout the develop branch: cd /opt/mhs/My-Home-Server/ && sudo git checkout develop
