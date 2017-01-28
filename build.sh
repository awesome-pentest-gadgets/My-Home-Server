#!/bin/bash

MAVEN_VERSION=3.3.9
MAVEN_DIR=apache-maven-$MAVEN_VERSION

echo "Current path: $PWD"

# Update time
sudo /etc/init.d/ntp restart

# Install Maven 3.1+ if it doesn't exist
if [ ! -d $MAVEN_DIR/ ]; then
	wget http://wwwftp.ciril.fr/pub/apache/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz
	tar xvzf apache-maven-$MAVEN_VERSION-bin.tar.gz
fi
export M2_HOME=$PWD/$MAVEN_DIR
export M2=$M2_HOME/bin
export MAVEN_OPTS="-Xms256m -Xmx512m"
export PATH=$M2:$PATH

# Build the webapp
pushd .
cd webapp-install
mvn clean install
RESULT=$?
popd
if [ $? -ne 0 ]; then
	echo "Build error during the webapp install".
	exit 2
fi

# If the install directory is present, remove it
if [ -d install/ ]; then
	sudo rm -rf install/
	echo "install directory deleted."
fi

# Create the directory
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

# Copy the webapp
sudo cp -R ../webapp-install/target/mhs-install-webapp ./config/includes.chroot/opt/

# Prepare the boot menu to start immediatly
cp -R ../config/isolinux ./config/includes.binary/

# Copie the install webapp
cp ../config/hooks/0900-create-webapp.hook.chroot config/hooks/

# Build the live Debian
sudo lb build --verbose

# Move the ISO to the NAS directory
rm -rf ../*.iso
mv *.iso ../

# End
cd ..
echo "Done !"

