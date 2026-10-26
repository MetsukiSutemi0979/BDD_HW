# BDD_HW — тесты переводов между картами

[![Java CI with Gradle](https://github.com/MetsukiSutemi0979/BDD_HW/actions/workflows/gradle.yml/badge.svg)](https://github.com/MetsukiSutemi0979/BDD_HW/actions/workflows/gradle.yml)

Домашняя работа курса «Тестировщик»: E2E-сценарии переводов между картами личного кабинета банка.

## Что покрыто
- Авторизация (логин → верификация по коду) и вход в личный кабинет
- Перевод с одной карты на другую: валидная сумма, проверка балансов до/после
- Негативные сценарии: сумма больше баланса, нулевая сумма и пр.
- Балансы карт читаются из статического источника тестовых данных (`DataHelper`)

## Стек
- Java 11, Gradle, JUnit 5
- Selenide 6.17 + Page Object (`LoginPage`, `VerificationPage`, `DashboardPage`, `TransferPage`)
- Lombok для генерации тестовых данных
- CI: GitHub Actions (бейдж выше)

## Запуск
```PowerShell
java -jar ./artifacts/app-ibank-build-for-testers.jar   # SUT на localhost:9999
./gradlew clean test
```