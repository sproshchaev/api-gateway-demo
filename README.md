# api-gateway-demo

Демонстрационный проект для изучения работы API Gateway (на примере Spring Cloud Gateway) и принципов маршрутизации 
запросов в микросервисной архитектуре.

## 🗂️ Структура проекта

```
api-gateway-demo/
├── api-gateway/          # API Gateway на Spring Cloud Gateway
├── user-service/         # Микросервис для работы с пользователями
└── order-service/        # Микросервис для работы с заказами
```

### Микросервисы

- **`user-service`**: Слушает порт `8081`. Обрабатывает запросы на пути `/users/{id}`.
- **`order-service`**: Слушает порт `8082`. Обрабатывает запросы на пути `/orders/{id}`.

### API Gateway

- **`api-gateway`**: Слушает порт `8080`.
- Предоставляет единый вход для клиентов.
- Маршрутизирует запросы к соответствующим микросервисам.
- Использует фильтр `StripPrefix=1` для корректного перенаправления путей.

#### Маршруты

| Путь в Gateway         | Целевой сервис | Путь в сервисе    | Фильтр         |
| ---------------------- | -------------- | ----------------- | -------------- |
| `/api/users/**`        | `user-service` | `/users/{id}`     | `StripPrefix=1`|
| `/api/orders/**`       | `order-service`| `/orders/{id}`    | `StripPrefix=1`|

**Почему `StripPrefix=1`?**
Когда клиент обращается к `http://localhost:8080/api/users/123`:
1. Gateway сопоставляет путь с шаблоном `/api/users/**`.
2. Применяется фильтр `StripPrefix=1`, который удаляет **первую часть** пути (`/api`).
3. Остается путь `/users/123`.
4. Этот путь перенаправляется в `user-service` по адресу `http://localhost:8081/users/123`.
5. `user-service` обрабатывает запрос и возвращает ответ.
6. Gateway передает ответ клиенту.

Если бы мы использовали `StripPrefix=2`, то удалялись бы две части (`/api` и `/users`), и запрос шёл бы в `user-service` как `http://localhost:8081/123`, что привело бы к ошибке `404 Not Found`, так как `user-service` не обрабатывает путь `/123`.

## 🚀 Запуск

1. Запустите `user-service`:
```bash
./gradlew :user-service:bootRun
```
2. Запустите `order-service`:
```bash
./gradlew :order-service:bootRun
```
3. Запустите `api-gateway`:
```bash
./gradlew :api-gateway:bootRun
```

## 🧪 Примеры вызовов

### Получить информацию о пользователе через Gateway
```bash
curl -v http://localhost:8080/api/users/123
```

### Получить информацию о заказе через Gateway
```bash
curl -v http://localhost:8080/api/orders/456
```

### Получить информацию о пользователе напрямую (для сравнения)
```bash
curl -v http://localhost:8081/users/123
```

### Получить информацию о заказе напрямую (для сравнения)
```bash
curl -v http://localhost:8082/orders/456
```
