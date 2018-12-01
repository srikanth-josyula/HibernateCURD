# HibernateCURD
This Project performs basic CURD Operation of PersonEntity using Hibernate ORM
There are three Entity Classes 
    1. Person Entity   : Which has the basic person information 
    2. Company Entity  : This class is mapped as OneToOne Mapping for the person Entity
    3. Reportee Entity : This is a parent class to Internal and External Reportees which are mapped to Person by ManyToOne Mapping
