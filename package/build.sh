#!/bin/bash
VERSION=0.0.1-alpha1
ARCH=armhf
FILENAME=mhs-${VERSION}_${ARCH}.deb

# Create the webapp
pushd .
cd ../mhs_server/
mvn clean install
result=$?
popd
if [ $result -ne 0 ]; then
	echo Can''t create the webapp.
	exit 1
else
	rm ./mhs/opt/mhs/*.war
	cp ../mhs_server/target/*.war ./mhs/opt/mhs/
fi

# Create the package
dpkg-deb --build mhs ${FILENAME}

# Help message
echo DONE !
echo 
echo To install: sudo dpkg -i ${FILENAME}

exit 0
