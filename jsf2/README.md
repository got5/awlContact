JSF 2 AwlContact
===========================

A JSF 2 implementation of the AwlContact project (a simple CRUD webapp) based on Primefaces anf Richfaces library 

Stack
-----

- Web framework           : JSF 2 [Mojarra](https://javaserverfaces.java.net/) 2.2.11 | [MyFaces](https://myfaces.apache.org/) 2.2.8
- Web component library   : [Primefaces](http://www.primefaces.org) 5.2 | [Richfaces](http://richfaces.jboss.org) 4.5.8.Final
- IOC                     : [JBoss Weld CDI](http://weld.cdi-spec.org/) 2.2.14.Final
- Persistence layer (JPA) : Hibernate 4.3.10.Final
- Bean Validation         : validation-api-1.1.0.Final / Hibernate-validator 5.1.3.Final
- Database                : Hsqldb 2.3.2 (embedded) 
- Application server      : Jetty 9.3.0.M2


Installation
------------

To install the project , just do "__mvn clean install__"

Run
---

- Myfaces : "__mvn jetty:run -Pmyfaces__" (default)
- Mojarra : "__mvn jetty:run -Pmojarra__"

then go to  : http://localhost:8080/awlcontact/  
click on the top left link to choose the UI implementation, between "[Primefaces](http://www.primefaces.org)" (default) and "[Richfaces](http://richfaces.jboss.org)". 
