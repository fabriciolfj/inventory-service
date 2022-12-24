# Microservice inventory-service
Essa aplicação tem por objetivo controlar as entradas e saidas de um inventário. Utilize-se spring boot 3 com webflux.
Afim de manter a comunicação reativa com a base de dados, fazemos o uso do r2dbc para postgres.

## Pontos técnicos
- por ser tratar de um controle de inventario, não permitimos multiplas transações sobre um registro afim de garantir a integridade das informações.
- desta forma o nivel de isolamento utilizado é:
```
isolation = Isolation.SERIALIZABLE
```