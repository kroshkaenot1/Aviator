# Тестовое задание
Данная игра разработана в качестве тестового задания на вакансию Junior Android Developer.

## Описание
Игра нацелена на устранение вражеских самолетов путем выстрела и получение очков. В процессе игры 
с некоторым шансом могут появляться аптечки (маленькая и большая), которые помогут восстановить 
здоровье. За каждый сбитый самолет +10 очков. Здоровье состоит из 12 очков. За каждое попадание врага снимается 1.

## Используемый стек
* Hilt
* Coroutines
* Navigation component
* Jetpack compose
* Shared Preferences

Приложение написано с
  соблюдением [Clean Architecture](https://github.com/ImangazalievM/CleanArchitectureManifest/blob/master/README-RU.md)
  и [MVVM](https://en.wikipedia.org/wiki/Model–view–viewmodel) подхода.


## Flow приложения
Точка старта приложения - открывается меню c предложением начать игру

<img alt="img.jpg" height="600" src="readme_images/1.jpg" width="290"/>

При нажатии на GO начинается игра

<img alt="img.jpg" height="600" src="readme_images/2.jpg" width="290"/>

При потерe всего количества здоровья открывается меню с задним фоном в зависимости от результата

<img alt="img.jpg" height="600" src="readme_images/3.jpg" width="290"/>
<img alt="img.jpg" height="600" src="readme_images/4.jpg" width="290"/>

