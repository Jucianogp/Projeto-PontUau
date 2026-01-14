import requests

# -----------------------------
# URLs
# -----------------------------
API_URL = "http://localhost:8000"
MODEL_URL = "http://localhost:8001/predict"
WEATHER_URL = "http://localhost:8002/forecast"

# -----------------------------
# Payload
# -----------------------------
payload = {
    "icao_empresa": "AZU",
    "icao_aerodromo_origem": "SBRF",
    "icao_aerodromo_destino": "SBRJ",
    "partida_prevista": "2025-11-12T22:30:00",
    "tempo_voo_estimado_hr": 1.2,
    "distancia_km": 50.0
}

headers = {
    "Content-Type": "application/json"
}

# -----------------------------
# Função de teste aprimorada
# -----------------------------
def test_service(name, method, url, **kwargs):
    try:
        r = requests.request(method, url, timeout=5, **kwargs)
        print(f"[{r.status_code}] {name} ({url})")
        # tenta imprimir JSON, se não for JSON imprime texto puro
        try:
            print("     Resposta JSON:", r.json())
        except Exception:
            print("     Resposta texto:", r.text)
    except requests.exceptions.RequestException as e:
        print(f"[❌] {name} erro: {e}")

# -----------------------------
# Testes
# -----------------------------

print("\n1️⃣ Testando API principal (Spring Boot)...")
test_service(
    "API principal",
    "POST",
    f"{API_URL}/predict",  # ajuste se seu endpoint real for diferente
    json=payload,
    headers=headers
)

print("\n2️⃣ Testando Modelo ML direto (FastAPI)...")
test_service(
    "Modelo ML",
    "POST",
    MODEL_URL,
    json=payload
)

print("\n3️⃣ Testando Weather API...")
weather_query = f"?origem={payload['icao_aerodromo_origem']}&destino={payload['icao_aerodromo_destino']}&data={payload['partida_prevista']}"
test_service(
    "Weather API",
    "GET",
    WEATHER_URL + weather_query
)

print("\n4️⃣ Testando Swagger Docs (Spring Boot)...")
test_service(
    "Swagger Docs",
    "GET",
    f"{API_URL}/swagger-ui/index.html"
)
