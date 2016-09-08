#!/bin/bash -x
mvn deploy:deploy-file -DgroupId=braunimmobilien -DartifactId=webapp-bootstrap -Dversion=$(VERSION_NUMBER) -DgeneratePom=false -Dpackaging=jar -DrepositoryId=releases-repo -Durl=http://nexus-ichueberallde.rhcloud.com/nexus/content/repositories/releases/ -DpomFile=webapp-bootstrap/target/pom-install-deploy-fix/pom.xml -Dfile=webapp-bootstrap/target/webapp-bootstrap-$(VERSION_NUMBER).jar

