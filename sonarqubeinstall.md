1.install java
2. cd /tmp
wget https://repo.mysql.com/mysql-community-release-el7.rpm
sudo rpm -ivh mysql-community-release-el7.rpm
3.sudo grep 'temporary password' /var/log/mysqld.log
4.mysql -u root -p
5.mysql_secure_installation

configure linux system for sonarqube
6.echo "vm.max_map_count=262144" | sudo tee -a /etc/sysctl.conf
sudo sysctl -p
7.  echo '* - nofile 8000' >>/etc/security/limits.conf
8.  sudo sed -i -e '/query_cache_size/d' -e '$a query_cache_size = 15M' /etc/my.cnf
9.  grep query_cache_size /etc/my.cnf
10. systemctl restart mysqld

## CONFIGURE DATABASE FOR SONARQUBE
11.mysql -u root -p
12.create database sonarqube
;
13.show databases 
;
14.CREATE USER 'sonarqube'@'localhost' IDENTIFIED BY 'Sonar@123';
15.GRANT ALL PRIVILEGES ON sonarqube.* TO 'sonarqube'@'localhost';
16.FLUSH PRIVILEGES;

## INSTALL SONARQUBE
17.yum install unzip -y
18.cd /opt
sudo wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-9.9.3.79811.zip
sudo unzip sonarqube-9.9.3.79811.zip
sudo mv sonarqube-9.9.3.79811 sonarqube

## CONFIGURE SONARQUBE
19.cd /opt/sonarqube/conf
20. sed -i -e '/^sonarqube.jdbc.username/ d' -e '/^sonarqube.jdbc.url/ d' -e '/^sonarqube.web.host/ d' -e '/^sonarqube.web.port/ d' /opt/sonarqube/conf/sonar.properties
21. 
sudo sed -i \
-e '/#sonar.jdbc.username/ a sonar.jdbc.username=sonar' \
-e '/#sonar.jdbc.password/ a sonar.jdbc.password=Ashi@1996' \
-e '/#sonar.jdbc.url/ a sonar.jdbc.url=jdbc:mysql://localhost:3306/sonarqube?useUnicode=true\&characterEncoding=utf8\&rewriteBatchedStatements=true\&useConfigs=maxperformance' \
-e '/#sonar.web.host/ a sonar.web.host=0.0.0.0' \
/opt/sonarqube/conf/sonar.properties
22. useradd sonar 
23. chown sonar /opt/sonarqube/ -R
24. sed -i -e '/RUN_AS_USER/ c RUN_AS_USER=sonar' /opt/sonarqube/bin/linux-x86-64/sonar.sh

25. cd /opt/sonarqube/bin/linux-x86-64
26.   ./sonar.sh start
27. 