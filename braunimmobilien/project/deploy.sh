#!/bin/bash -x
mvn clean install
mvn deploy:deploy-file -DgroupId=braunimmobilien -DartifactId=webapp-bootstrap -Dversion=1.0-SNAPSHOT -DgeneratePom=false -Dpackaging=jar -DrepositoryId=snapshots-repo -Durl=http://nexus-ichueberallde.rhcloud.com/nexus/content/repositories/snapshots/ -DpomFile=webapp-bootstrap/target/pom-install-deploy-fix/pom.xml -Dfile=webapp-bootstrap/target/webapp-bootstrap-1.0-SNAPSHOT.jar

mvn deploy:deploy-file -DgroupId=braunimmobilien -DartifactId=model -Dversion=1.0-SNAPSHOT -DgeneratePom=false -Dpackaging=jar -DrepositoryId=snapshots-repo -Durl=http://nexus-ichueberallde.rhcloud.com/nexus/content/repositories/snapshots/ -DpomFile=model/target/pom-install-deploy-fix/pom.xml -Dfile=model/target/model-1.0-SNAPSHOT.jar

mvn deploy:deploy-file -DgroupId=braunimmobilien -DartifactId=cocoon -Dversion=1.0-SNAPSHOT -DgeneratePom=false -Dpackaging=jar -DrepositoryId=snapshots-repo -Durl=http://nexus-ichueberallde.rhcloud.com/nexus/content/repositories/snapshots/ -DpomFile=cocoon/target/pom-install-deploy-fix/pom.xml -Dfile=cocoon/target/cocoon-1.0-SNAPSHOT.jar

curl -v -u admin:admin123 --upload-file target/pom-install-deploy-fix/pom.xml http://nexus-ichueberallde.rhcloud.com/nexus/content/repositories/snapshots/braunimmobilien/project/1.0-SNAPSHOT/pom-1.0-SNAPSHOT.xml



