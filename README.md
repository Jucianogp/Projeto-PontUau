# ✈️ Projeto-PontUau

Sistema para **previsão de atrasos de voos**, integrando:

- **API principal:** desenvolvida em Spring Boot  
- **Modelo de Machine Learning:** servido via FastAPI  
- **API de clima:** também via FastAPI  

O endpoint principal **`/predict`** combina dados de voo e meteorologia para fornecer previsões mais precisas.

---

## 🏗 Estrutura do Projeto

```text
flight-on-time-system/
├─ flight-on-time-api/        # API principal (Spring Boot)
├─ flight-prediction-model/  # Modelo de ML (FastAPI)
├─ weather-api/              # API de clima (FastAPI)
├─ docker-compose.yml        # Configuração dos containers
├─ test_services.py          # Script de teste em Python
└─ README.md
````

---

## ⚡ Requisitos

* Docker
* Docker Compose
* Python 3.10+ (para executar `test_services.py`)
* Conexão com a internet (para consultar a Weather API)

---

## 🚀 Executando o Sistema

### 1️⃣ Subir os containers

No terminal:

```bash
cd flight-on-time-system
docker-compose up --build
```

### 🔌 Portas dos Serviços

| Serviço       | Porta |
| ------------- | ----- |
| API Principal | 8000  |
| Modelo ML     | 8001  |
| Weather API   | 8002  |
| MySQL         | 3306  |

Para acompanhar os logs da API principal:

```bash
docker-compose logs -f flight-api
```

---

## 2️⃣ Testando os Serviços

### 🔹 Via Python

```bash
python test_services.py
```

**Exemplo de saída:**

```text
[200] API principal
[200] Modelo ML
[200] Weather API
[200] Swagger Docs
```

---

### 🔹 Via Swagger UI

Abra no navegador:

```text
http://localhost:8000/swagger-ui/index.html
```

Selecione o endpoint **`/predict`**, clique em **Try it out** e preencha com o JSON de exemplo:

```json
{
  "icao_empresa": "AZU",
  "icao_aerodromo_origem": "SBRF",
  "icao_aerodromo_destino": "SBRJ",
  "partida_prevista": "2025-11-12T22:30:00",
  "tempo_voo_estimado_hr": 1.2,
  "distancia_km": 50.0
}
```

Clique em **Execute** para obter a previsão.

---

## ⚙️ Configuração do Docker

### Variáveis de Ambiente Importantes

| Variável                | Descrição                 |
| ----------------------- | ------------------------- |
| `SPRING_DATASOURCE_URL` | Conexão com o banco MySQL |
| `MODEL_API_URL`         | URL do modelo de ML       |
| `WEATHER_API_URL`       | URL da API de clima       |
| `OPENWEATHER_API_KEY`   | Chave da OpenWeather API  |

O `docker-compose` utiliza **healthchecks** para garantir que cada serviço só inicie quando suas dependências estiverem prontas.

---

## 📝 Observações

* O endpoint **`/predict`** integra dados do **modelo de ML** e da **API de clima**
* Em caso de erro **500** ou **conexão abortada**:

  * Verifique se `model-api` e `weather-api` estão rodando
  * Consulte os logs da `flight-api`

---

## 📚 Referências

* Spring Boot — [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
* FastAPI — [https://fastapi.tiangolo.com/](https://fastapi.tiangolo.com/)
* Docker Compose — [https://docs.docker.com/compose/](https://docs.docker.com/compose/)
* Swagger UI — [https://swagger.io/tools/swagger-ui/](https://swagger.io/tools/swagger-ui/)
