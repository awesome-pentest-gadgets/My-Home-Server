#!/bin/bash

# Update time
sudo /etc/init.d/ntp restart

# Create the directory
sudo rm -rf install/
mkdir install/
cd install/

# Create the tree
# FIXME Add link to the manpage
lb config \
	--architectures i386 \
	--distribution jessie \
	--apt apt \
	--archive-areas "main contrib non-free" \
	--mirror-bootstrap http://ftp2.fr.debian.org/debian/ \
	--mirror-binary http://ftp2.fr.debian.org/debian/ \
	--verbose
#	--bootappend-live "boot=live username=user hostname=nasinstall"
#	--binary-images iso-hybrid 
#	--binary-filesystem ext4

# Prepare the packages to install
cp ../config/mhs-install.list.chroot ./config/package-lists/
sudo mkdir ./config/includes.chroot/opt
sudo cp -R ../webapp-install/ ./config/includes.chroot/opt/

exit 0

#cp ../live-config/hooks/0900-create-webapp.hook.binary config/hooks/
cp ../live-config/hooks/0900-create-webapp.hook.chroot config/hooks/

# Build the live Debian
sudo lb build --verbose

# Move the ISO to the NAS directory
rm -rf ../*.iso
mv *.iso ../

# End
cd ..
echo "Done !"

