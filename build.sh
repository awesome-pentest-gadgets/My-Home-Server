#!/bin/bash

# Update time
sudo /etc/init.d/ntp restart

# If the install directory is present, remove it
if [ -d install/ ]; then
	sudo rm -rf install/
fi

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

# Prepare the boot menu to start immediatly
cp -R ../config/isolinux ./config/includes.binary/

# Copie the install webapp
cp ../config/hooks/0900-create-webapp.hook.chroot config/hooks/

# Build the live Debian
sudo lb build --verbose > ../logs/build.log

# Move the ISO to the NAS directory
rm -rf ../*.iso
mv *.iso ../

# End
cd ..
echo "Done !"

