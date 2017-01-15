# My Home Server
My Home Server is an operating system based on Linux with a user friendly user interface to install, configure et manage extensions like NAS, Web Server, FTP.
# Continuous integration
If you want to develop this project, maybe you will need to create your continuous integration server to create your ISO file to install _My Home Server_. Because _My Home Server_ is using GNU/Linux Debian as a base, the continous integration is using GNU/Linux Debian. To install this continuous integration, you need to have an access to internet to download the dependances. If you need to use a proxy, you will need to add some settings at the boot start of the install.
To test this continuous integration, you can use a virtual machine (like VMWare Workstation). You will need to use an USB drive to store the preseed file.
## Install the virtual machine
I tested this continuous integration with a virtual machine. The virtual machine I used is [VMWare Worstation Player](http://www.vmware.com/products/player/playerpro-evaluation.html) free for non commercial use. If you want to use it with you own physical server, beware because this documentation will show the way using the entire disk space.
Start to download __VMWare Workstation Player__ and install it on your computer.
## Download GNU/Linux Debian
To install the continuous integration, you will need the first ISO of the DVD of the install for GNU/Linux Debian Jessie (version 8.x). You can download it here (use the closest mirror): [Download the CD or DVD](https://www.debian.org/CD/http-ftp/#stable)
You will use this ISO image later with VMWare.
## Download the preseed file
To automate the install, you will use a preseed file. This preseed file is containing the main settings to install all the requirement of the continuous integration server. The only steps you will have to do will be to select the partition and to set the boot sector. You can download the preseed here:
* [Preseed to have the install in french with a french keyboard](https://github.com/stefv/My-Home-Server/blob/develop/develop/preseed.fr.cfg),
* TODO English preseed file

Copy this preseed file to the root of your USB drive.
## Create a virtual machine
In VMWare and create a virtual machine. Allocate 5GiB for the disk space to store the operating system. In the virtual CD/DVD Rom, select the ISO downloaded earlier. In the removable devices, select your USB drive.
## Perform the install
Start your guest OS. You will have the GNU/Linux Debian menu. Press the ESC key and enter this statement: _auto file=/media/preseed.en.cfg_ (for english) or _file=/media/preseed.fr.cfg_ (for french). Press enter.
After some minutes (if everything if well), you will have to select the partition. Select the virtual disk drive (for me sdb). Please, not this partition name because you will need it later for the boot sector. Confirm the choice.
Some manute later, give the patition where to install the boot sector. In my case: /dev/sdb
The system will restart.
## First login
You will start with Gnome. The login is _install_ and the password _install_. Because it's the first time, you will need to download the repository with the sources manually. Open a terminal and enter this command: __git clone https://github.com/stefv/My-Home-Server.git /opt/mhs/__
TODO Add the automatic build
