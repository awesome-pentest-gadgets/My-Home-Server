#!/bin/bash

# Create the target directory
if [ ! -d "target" ]; then
        mkdir target
fi

# Create the control.tar.gz file
cd control
tar -cvzf ../target/control.tar.gz *

cd ..

exit 0
