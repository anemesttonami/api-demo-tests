<div align="center">

# Проект API автотестов для сайта REQRES

<img src="images/REQRES.png" width="350">
</div>

> **REQRES** — это бесплатный публичный тестовый API сервис, предназначенный для разработки, тестирования и обучения.

---
## 📖 Содержание

- [🧩 Используемые технологии в проекте](#технологии)
- [🧪 Тест-кейсы](#тест-кейсы)
- [🚀 Запуск автотестов через терминал](#запуск-автотестов-через-терминал)
- [⚙️ Сборка Jenkins](#сборка-jenkins)
- [📊 Allure](#allure)
- [📊+🧪 Allure TestOps](#testops)
- [🔔 Telegram](#telegram)

---
<div align="center">

<a id="технологии"></a>
## Используемые технологии в проекте

<p  align="center">

  <img width="5%" title="Java" src="images/java-logo.svg">    
  <img width="5%" title="Github" src="images/git-logo.svg">
  <img width="5%" title="Gradle" src="images/gradle-logo.svg ">
  <img width="5%" title="RestAssured" src="images/rest-assured.png">
  <img width="5%" title="Lombok" src="images/lombok.png">
  <img width="5%" title="IntelliJ IDEA" src="images/IDEA-logo.svg">
  <img width="5%" title="JUnit5" src="images/junit5-logo.svg">
  <img width="5%" title="Allure Report" src="images/allure.svg">
  <img width="5%" title="Allure TestOps" src="images/allureTestOps.svg">
  <img width="5%" title="Jenkins" src="images/jenkins.svg">
  <img width="5%" title="Telegram" src="images/telegram.svg">

</p>
</div>




---

<a id="тест-кейсы"></a>
## 🧪 Тест-кейсы

### Тесты на эндпоинт "/api/users"

 - ✅ Максимальный id в списке пользователей.
 - ✅ Создание пользователя и генерация времени создания пользователя.
 - ✅ Удаление пользователя.
 - ✅ Ответ с задержкой и консистентность тела ответа (различные проверки).

### Тесты на эндпоинт "/api/login"

- ✅ Авторизация.

___
<a id="запуск-автотестов-через-терминал"></a>
## 🚀 Запуск автотестов через терминал

### Запустить все тесты локально 

```bash
  ./gradlew clean test
```
Также есть возможность вместо **test** указать **smoke** или **regress** и запустятся соответствующие тесты 🤖

### Получить Allure отчёт

```bash
  ./gradlew allureServe
```
___

<a id="сборка-jenkins"></a>
## <img width="40" style="vertical-align:middle" src="images/jenkins.svg"> </a> Сборка в <a target="_blank" href="https://jenkins.autotests.cloud/"> Jenkins </a>
Jenkins job-а для запуска автотестов этого проекта располагается <a target="_blank" href="https://jenkins.autotests.cloud/job/C36-andreyalbaev-ui-21"> здесь</a>. Для запуска сборки необходимо нажать на <code>Build with parameters</code>, выбрать необходимые параметры и нажать кнопку <code>Build</code>.

### Параметры сборки в Jenkins:

- TESTS_TO_RUN
- COMMENT

<div align="center">

<img src="images/JenkinsParams.png" width="500">

</div>


❗После выполнения сборки формируются <code>Allure Report</code> - HTML отчёт о прошедших тестах и тестовые прогоны в TMS <code>Allure TestOps</code>.

---
<a id="allure"></a>
## <img width="35" style="vertical-align:middle" src="images/allure.svg"> </a>  Allure
### Пример отчёта

<img src="images/allureReportExampl.png" width="1500">

Как уже было сказано ранее, отчёт формируется автоматически после прогона автотестов.

### Allure attachments

Также после выполнения тестов к отчёту добавляются отформатированные:

1) Информация о запросе
2) Информация об ответе

<img src="images/allureAttachments.png" width="1500">

---
<a id="testops"></a>
## <img width="35" style="vertical-align:middle" src="images/allureTestOps.svg"> </a>  Allure TestOps
Это TMS, которую можно интегрировать, например, с Jenkins и Jira, в данном проекте интегрировано с Jenkins: в TestOps автоматически выгружаются результаты прохождения автотестов после выполнения job-ы в Jenkins.

<div align="center">
<img src="images/jenkinsAllureExample.jpg" width="750">

</div>
<div align="center">
<img src="images/testopsResult.png" width="750">

</div>

---

<a id="telegram"></a>

## <img width="35" style="vertical-align:middle" src="images/telegram.svg"> </a>  Telegram уведомления
К проекту также подключен бот , который по завершении прогона автотестов посылает краткую сводку и ссылку на allure отчёт в telegram<a target="_blank" href="https://t.me/esttonami"> чат</a>.


<div align="center">
<img src="images/tg.png" width="500">

</div>
