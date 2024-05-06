# springbootlootcrate
When developing a system using Domain-Driven Design (DDD), we structure it into layered architecture. 
In most cases, the layers closer to the foundation experience less change. 
This implies that lower layers, which often encompass fundamental infrastructure services and utilities, 
see fewer modifications compared to higher layers dealing with more domain-specific business logic. 
Such a layered approach facilitates managing changes, enhancing maintainability and flexibility of the system.

However, as the complexity and scale of business logic grow, 
this leads to an increase in the volume of code within the Domain Layer, making the logic more intricate.

To address this issue, DDD advocates for employing strategies such as continuous refactoring, layered architecture, 
Bounded Contexts to define clear boundaries, code reviews, and thorough documentation.

springbootlootcrate provides a solution to this issue.

The logic behind springbootlootcrate is to package separable business logic code into a standalone JAR package, 
which is then dynamically loaded within a container created within the host Spring Boot application. 
This JAR package has the capability to leverage underlying services provided by the host application.

features of springbootlootcrate include:
- clear business layer packaging
- isolation among business modules
- Offers flexible control of business layer's access to underlying service capabilities.
- Business logic can easily be offline at any time.
- Effortlessly integrated via starters, with non-intrusive operation on the host application.

These features provide the ability for business isolation and confidentiality among development of different business functionalities.
You can even outsource the development of your business logic code without concerns of leaking underlying service logic.



通过领域驱动设计方式(DDD)开发系统的时候，我们会把系统分层架构。在大多数情况下，越靠近基础层变化越小。这种分层方法有利于管理变更，增强系统的可维护性和灵活性。
但是随着业务逻辑的复杂性和规模的增长，这会导致业务层（Domain Layer）的代码量增加，逻辑变得复杂。
为了应对这个问题，DDD提倡采用以下策略：持续重构，分层架构，界Bounded Contexts，代码审查和文档。
springbootlootcrate 为这个问题提供了一个解决方案。

springbootlootcrate 的逻辑是把可以分割的独立业务层代码整体打包成一个jar包，在宿主springboot应用中动态创建一个container加载这个jar包，这个jar包能够引用到宿主应用中的底层服务。
- 清晰的业务层逻辑包，
- 业务之间隔离
- 提供灵活控制业务层访问底层服务能力
- 业务逻辑可以随时下线
- 通过starter非常方便集成，对宿主应用无侵入

这些特性提供了业务开发之间业务隔离以及保密的能力，你甚至可以让外包开发你的业务逻辑代码而不用担心底层服务逻辑被泄密。
