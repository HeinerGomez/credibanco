# Prueba CrediBanco

Se presenta una aplicación que simula transacciones bancarias, en las que se puede aprobar o anular,
también hacer busquedas y ver el detalle de la transacción

## Tecnologias y Arquitecturas implementadas

- Jetpack Compose
- Clean Architecture
- Coroutines
- MutableState
- MVVM
- Retrofit
- Dagger Hilt
- RoomDB
- Lottie
- Project Modularization
- Gradle KTS
- DataSource

## Video Demostrativo


https://github.com/HeinerGomez/credibanco/assets/27563731/a094047f-9af0-4a96-8fca-ebb82ebd2bbe



## Instalación

- *PASO 1:* Clonar el proyecto y tenerlo de manera local
- *PASO 2:* Abrir el proyecto en android studio y dejar que se descarguen las dependencias
- *PASO 3:* Ir a Android Studio > Settings > En el apartado de Gradle, verificar que se este usando la versión de java 18.0.2 o superior
- *PASO 4:* Ir a File > Project Structure, Verificar en el apartado de project que se este usando la versión 8.4 de gradle.
- *PASO 5:* Sincronizar el proyecto y ya se podría ejecutar.

## Notas

- procesos en segundo plano se manejaron con coroutines
- el proyecto quedó modularizado creando los siguientes módulos: app, shared, presentation, buildSrc(para configuración del proyecto), data, domain
- se crearon componentes reutilizables en jetpack compose
- Implementación de RoomDB
