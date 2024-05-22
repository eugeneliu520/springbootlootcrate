# Why springbootlootcrate
When developing a system using Domain-Driven Design (DDD), we structure it into layered architecture. 
In most cases, the layers closer to the foundation experience less change. 
This implies that lower layers, which often encompass fundamental infrastructure services and utilities, 
see fewer modifications compared to higher layers dealing with more domain-specific business logic. 
Such a layered approach facilitates managing changes, enhancing maintainability and flexibility of the system.

However, as the complexity and scale of business logic grow, 
this leads to an increase in the volume of code within the Domain Layer, making the logic more intricate.

To solve this issue, DDD advocates for employing strategies such as continuous refactoring, layered architecture, 
Bounded Contexts to define clear boundaries, code reviews, and thorough documentation.

now springbootlootcrate provides a solution to this issue.

### Basic logic behind springbootlootcrate:
The logic behind springbootlootcrate is to package separable business logic code into a standalone JAR package, 
which is then dynamically loaded within a container created within the host Spring Boot application. 
Based on declared dependency information, the dependent services are injected into the newly created container.
This design enables business JAR packages to access host services on-demand, 
achieving clearer dependencies and finer-grained permission controls.

### Why don't  user springboot hierarchy context？
- https://docs.spring.io/spring-boot/docs/2.5.2/reference/htmlsingle/#features.spring-application.fluent-builder-api
- the documentation  said There are some restrictions when creating an ApplicationContext hierarchy. For example, Web components must be contained within the child context, and the same Environment is used for both parent and child contexts.

### Theoretical of springbootlootcrate：
- Microkernel architecture, also known as a modular or plug-in architecture.
- we user container to load business logic jar.
- we designed a mechanism where the host space initiates requests, routes them to a specific plug-in container as needed, and then switches to the container space to execute the business logic.

### Features of springbootlootcrate include:
- clear business layer packaging
- isolation among business modules
- Offers flexible control of business layer's access to underlying service capabilities.
- Business logic can easily be offline at any time.
- Effortlessly integrated via starters, with non-intrusive operation on the host application.

These features provide the ability for business isolation and confidentiality among development of different business functionalities.
You can even outsource the development of your business logic code without concerns of leaking underlying service logic.

## Building
The build requires java8 because of some required libraries that are java8.

## Support
feel free to open an issue with your question, the maintainers are looking over these periodically. 

## Contact 
eugeneliu520@outlook.com

## TODO
- admin page
- documentation 
