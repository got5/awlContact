JSF 2 AwlContact
===========================

A JSF 2 implementation of the AwlContact project (a simple CRUD webapp) based on PrimeFaces and RichFaces libraries 

Stack
-----

- Web framework           : JSF 2 [Mojarra](https://javaserverfaces.java.net/) (2.2.11) | [MyFaces](https://myfaces.apache.org/) (2.2.8)
- Web UI                  : [Primefaces](http://www.primefaces.org) (5.2) | [Richfaces](http://richfaces.jboss.org) (4.5.8.Final) | [Bootsfaces](http://bootsfaces.net/) (0.6.6)
- IOC                     : [JBoss Weld CDI](http://weld.cdi-spec.org/) (2.2.14.Final)
- Security layer          : [Apache Shiro](http://shiro.apache.org/) (1.2.4) / [shiro-faces](http://deluan.github.io/shiro-faces/) (2.0-SNAPSHOT)
- Testing framework       : [Arquillian](http://arquillian.org/) (1.1.8.Final) / [Drone](https://docs.jboss.org/author/display/ARQ/Drone) (1.3.1.Final) / [Graphene 2](https://docs.jboss.org/author/display/ARQGRA2/Home) (2.0.3.Final)
- Persistence layer (JPA) : Hibernate (4.3.10.Final)
- Bean Validation         : validation-api (1.1.0.Final) / Hibernate-validator (5.1.3.Final)
- Database                : Hsqldb (2.3.2) 
- Application server      : Jetty (9.3.0.M2)


Installation
------------

To install the project , just do "__mvn clean install__"

Run
---

- Myfaces : "__mvn jetty:run -Pmyfaces__" (default)
- Mojarra : "__mvn jetty:run -Pmojarra__"

then go to  : http://localhost:8080/awlcontact/  
after login (credential displayed on home page), click on the top left link to choose the UI implementation, between "[Primefaces](http://www.primefaces.org)" (default), "[Richfaces](http://richfaces.jboss.org)" and "[Bootsfaces](http://bootsfaces.net/)". 
