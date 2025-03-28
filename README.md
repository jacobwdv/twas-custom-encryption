How to Create a customer encryption class: 

go to twas and copy out the jar <WAS_HOME>/dev/was_public.jar

install this jar into your local maven repository (there might be a smarter way to do this): 
```
mvn install:install-file \
-Dfile=./was_public.jar \
-DgroupId=com.ibm.wsspi.security.crypto \
-DartifactId=custompasswordencryption \
-Dversion=1.0.0 \
-Dpackaging=jar
```

open eclipse, new -> maven project -> next -> use maven-archtetype-quickstart from org.apache.maven.archetypes -> create a package and artifact id
open pom.xml and add the dependency for the ibm jar you installed above: 
```
		<dependency>
			<groupId>com.ibm.wsspi.security.crypto</groupId>
			<artifactId>custompasswordencryption</artifactId>
			<version>1.0.0</version>
		</dependency>
```

go to src/main/java and open the sample java file. Rename the class however you want but add `implements  CustomPasswordEncryption`. Eclipse should automatically pick up the imports needed 

implement the necessary methods and then when you're done, right click pom.xml->run as->maven install. This will create a target directory that contains a snapshot jar you can use. 

Copy the jar and go to your twas installation root. 
Create a directory named 'classes' paste the custom jar inside ex: /opt/IBM/WebSphere/AppServer/classes/customencryption-0.0.1-SNAPSHOT.jar

In order to read secrets, server.xml's that use the custom class must set two jvm properties within genericJvmArguments like so:
```
<jvmEntries xmi:id="JavaVirtualMachine_1" verboseModeClass="false" verboseModeJNI="false" runHProf="false" hprofArguments="" debugMode="false" debugArgs="-Djava.compiler=NONE -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=7792" genericJvmArguments="-Dcom.ibm.wsspi.security.crypto.customPasswordEncryptionClass=com.jake.custom.security.customencryption.WasCustomerPasswordEncryption -Dcom.ibm.wsspi.security.crypto.customPasswordEncryptionEnabled=true">
```
In order to enable encryption you can use wsadmin.sh: 

```
/opt/IBM/WebSphere/AppServer/bin/wsadmin.sh -conntype none -javaoption -Dcom.ibm.wsspi.security.crypto.customPasswordEncryptionClass=com.jake.custom.security.customencryption.WasCustomerPasswordEncryption -javaoption -Dcom.ibm.wsspi.security.crypto.customPasswordEncryptionEnabled=true  -lang jython -c "AdminTask.enablePasswordEncryption()"
```
