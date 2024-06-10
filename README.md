Introducción
Este repositorio contiene el código fuente de una aplicación de gestión para un lavadero de vehículos, desarrollada en Java utilizando Java Swing para la interfaz gráfica de usuario, JPA (Java Persistence API) Controller para la gestión de la persistencia de datos, y una base de datos MySQL para el almacenamiento de información. La aplicación sigue los principios de la Programación Orientada a Objetos (POO).

Funcionalidades Principales
La aplicación ofrece una serie de funcionalidades organizadas en diferentes pantallas y botones en la interfaz principal:

1. Pantalla Principal:
   - Botón "Ingresar un Vehículo":
     - Al hacer clic en este botón, se abre una nueva pantalla para registrar la información de un vehículo.
     - En esta pantalla, se dispone de un botón para agregar los datos del dueño. Al presionar este botón, se abre otra pantalla donde se puede ingresar el nombre y otros datos relevantes del dueño.
     - La pantalla de ingreso de vehículos permite seleccionar el tipo de vehículo, ingresar la placa, modelo, color, el nombre del servicio solicitado, el valor del servicio y seleccionar un dueño de una lista desplegable que muestra los dueños almacenados en la base de datos.
     - Botones disponibles: "Guardar" para almacenar los datos del vehículo y "Regresar" para volver a la pantalla principal.

   - Botón "Ver Vehículos":
     - Al hacer clic en este botón, se solicita el ingreso de un usuario y contraseña.
     - Si las credenciales son correctas, se abre una pantalla donde se muestran todos los vehículos registrados en la base de datos, junto con la información de sus respectivos dueños.
     - En esta pantalla se encuentran los siguientes botones:
       - "Eliminar Vehículo": Permite eliminar un vehículo seleccionado de la base de datos.
       - "Editar Vehículo": Permite modificar la información de un vehículo seleccionado.
       - "Ver Total Ingresado del Día": Muestra el total de ingresos generados en el día.
       - "Borrar Vehículos del Día": Elimina todos los registros de vehículos del día actual.
       - "Gestionar Usuarios": Abre una pantalla adicional donde se pueden editar y eliminar usuarios del sistema.
       - "Editar Usuario/Contraseña": Permite modificar las credenciales del login actual.
       - "Regresar": Vuelve a la pantalla principal.

   - Botón "Salir":
     - Este botón cierra la aplicación de forma segura.

Tecnologías Utilizadas
- Java Swing: Utilizado para la creación de la interfaz gráfica de usuario (GUI).
- JPA (Java Persistence API) Controller: Utilizado para la gestión de la persistencia de datos y las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la base de datos.
- MySQL: Base de datos relacional utilizada para almacenar toda la información de los vehículos y dueños.
- Programación Orientada a Objetos (POO): Paradigma de programación utilizado para estructurar el código de manera modular y reutilizable.

Estructura del Proyecto
- src/main/java: Contiene el código fuente principal de la aplicación, organizado en paquetes según las diferentes funcionalidades y componentes de la aplicación.
- src/main/resources: Incluye archivos de configuración y recursos necesarios para la ejecución de la aplicación.
- pom.xml: Archivo de configuración de Maven para la gestión de dependencias y compilación del proyecto.

Instalación y Ejecución
1. Clonar el Repositorio.
2. Configuración de la Base de Datos:
   - Crear una base de datos MySQL y configurar las credenciales en el archivo de configuración correspondiente.
3. Compilar y Ejecutar.

Conclusión
Esta aplicación proporciona una solución integral para la gestión de un lavadero de vehículos, facilitando el ingreso, visualización y administración de la información de vehículos y sus dueños. Con una interfaz intuitiva y funcionalidades robustas, permite una gestión eficiente y organizada del negocio.
