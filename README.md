## Trabalho para a diciplina de `AWS Serverless Cloud Native Java RESTful API`

## Tecnologias utilizadas

* AWS CLI 
* Java SE Development Kit 8 installed
* Docker installed
* Maven
* SAM CLI
* Python 3.8 (para poder instalar o SAM CLI)

Obs: Para a execuxao dos passos que seguem, voce devera ter o CLI da AWS configurado com as informacoes da sua conta na maquina local

## Passos para o deploy e teste local

1 - Execute o comando que segue para empacotar e instalar as dependencias


```bash
mvn install
```

2 - Inicie o DynamoDB localmente utilizando o dockercompose
  
```bash
docker-compose up -d
```
  
3 - Crie a tabela no Dynamo executando o seguinte script 

```bash
aws dynamodb create-table --table-name trip --attribute-definitions AttributeName=country,AttributeType=S AttributeName=travelDate,AttributeType=S --key-schema AttributeName=country,KeyType=HASH AttributeName=travelDate,KeyType=RANGE --billing-mode PAY_PER_REQUEST --endpoint-url http://localhost:8000
```

4 - Inicie o SAM API rodando o seguinte script de acordo com o seu SO 

 - Mac: `sam local start-api --env-vars src/test/resources/evt_mac.json`
 - Windows: `sam local start-api --env-vars src/test/resources/evt_win.json`
 - Linux: `sam local start-api --env-vars src/test/resources/evt_linux.json`

5 - Para testar o app, importe o a collection no postman que esta no seguinte dir do projeto: 

```bash
src/test/resources/trips.postman_collection.json 
```
## Deploy na AWS


1 - Crie um bucket no S3 da sua conta, executando o script que segue:


```bash
aws s3api create-bucket --bucket trips-bucket --region us-east-1
```

2 - Suba o pacote para o bucket do S3 recen criado 

```bash
sam package \
    --template-file template.yaml \
    --output-template-file packaged.yaml \
    --s3-bucket trips-bucket \
    --region us-east-1
```

3 - Execute o comando que segue para realizar o deploy!

```bash
sam deploy \
    --template-file packaged.yaml \
    --stack-name trips-app \
    --capabilities CAPABILITY_IAM \
    --region us-east-1
```

4 - Verifique os endpoints disponibilizados para teste

4.1 - rode o script que segue para ter acesso as informacoes da sua lambda deployada

```bash
aws apigateway get-rest-apis
```

4.2 - com base nas informacoes retornadas, monte a url de teste conforme o exemplo que segue:

```bash
https://<restApiId>.execute-api.us-east-1.amazonaws.com/Prod/trips
```