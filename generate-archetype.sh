#!/bin/bash

mvn clean archetype:create-from-project -DpropertyFile=etc/archetype.properties
find target -name 'etc' | xargs rm -rf
find target -name '.settings' | xargs rm -rf
find target -name '.project' | xargs rm -rf
find target -name '.classpath' | xargs rm -rf
find target -name '.gitignore' | xargs rm -rf
