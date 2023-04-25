# AppPedal

AppPedal é um aplicativo para o agendamento de "Pedais", reuniões para passeios de bicicleta em conjunto.

## Requisitos
1.Java11 ou superior


## Instalação

Clone ou baixe este repositório no diretório destino.

```bash
git clone https://github.com/vitimon/Agendador
```

## Uso

O aplicativo ainda não tem uma interface de uso direto e por padrão usa um banco de dados em memória (H2), mas tem no arquivo "application.properties" pré configurado o uso do postgres(comentado).

Com o aplicativo já iniciado novos pedais podem ser incluidos via requisições HTTP(post) no endereço "localhost:8080/pedal" e acessados pela mesma URI com HTTP(get).

As inclusões devem estar no formato

```json
{"title":"night ride",
"date":"05-05-2023 20:06",
"description":"local: cinelândia, levar amigos e alegria "}
```

