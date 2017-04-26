#!/bin/bash

# Create the target directory
if [ ! -d "target" ]; then
        mkdir target
fi

# Create the control.tar.gz file
cd control
tar -cvzf ../target/control.tar.gz *
cd ..

# Create the debian-binary file
if [ ! -a target/debian-binary ]; then
	printf "2.0\n" > target/debian-binary
fi

exit 0
