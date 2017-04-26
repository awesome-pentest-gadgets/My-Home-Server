#!/bin/bash
VERSION=0.0.1-alpha1
ARCH=armhf
FILENAME=mhs-${VERSION}_${ARCH}.deb

# Create the package
dpkg-deb --build mhs ${FILENAME}

# Help message
echo To install: sudo dpkg -i ${FILENAME}

exit 0
