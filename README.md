 BotonTraductor - Componente de Traducción para Swing

## 🛠️ Métodos y Propiedades Relevantes

### Constructor
```java
public BotonTraductor()
```
- Inicializa el combobox con los idiomas disponibles ("Español", "Inglés", "Francés")
- Configura el tamaño preferido del componente
- Añade el ActionListener para manejar los cambios de idioma

### Métodos de Traducción
```java
private void traducirEtiquetas(String idiomaDestino)
```
Coordina el proceso de traducción de todas las etiquetas

```java
private void traducirComponentes(Container contenedor, String idiomaDestino)
```
Recorre recursivamente los componentes buscando JLabels para traducir

```java
private String traducir(String texto, String idiomaOrigen, String idiomaDestino)
```
Realiza la llamada a la API MyMemory para obtener la traducción  
Maneja la codificación URL y el parsing de la respuesta JSON

### Propiedades Principales
```java
private static final String API_URL
```
Endpoint de la API de MyMemory Translate

```java
private final String[] codigosIdiomas
```
Códigos ISO de los idiomas soportados (es, en, fr)

## 📌 Descripción del Componente

El `BotonTraductor` es un JComboBox especializado que permite traducir dinámicamente los textos de las JLabels en una interfaz gráfica Swing. 

**Características principales:**
- ✅ Integración con API externa (MyMemory Translate)
- 🔄 Traducción automática de JLabels
- 🖥️ Interfaz sencilla tipo combobox
- ⚡ Procesamiento asíncrono (no bloqueante)
- ❌ Manejo visual de errores

## 🚀 Instrucciones de Uso

### Requisitos
- JDK 8+
- Dependencia `org.json`
- Conexión a Internet

### Configuración básica
```java
// Agregar a un contenedor
JPanel panel = new JPanel();
BotonTraductor traductor = new BotonTraductor();
panel.add(traductor);
```

### Personalización
```java
// Cambiar tamaño
traductor.setPreferredSize(new Dimension(200, 30));

// Cambiar fuente
traductor.setFont(new Font("Arial", Font.BOLD, 14));
```

## 💡 Consejos de Uso
- ✂️ Usar textos cortos (mejor rendimiento)
- 💾 Implementar caché para muchas traducciones
- 🔐 Considerar APIs premium para producción
- 🏷️ Usar JLabels directamente en contenedores principales

## ⚠️ Limitaciones
- 📶 Requiere conexión estable a Internet
- 🚧 Límites en API gratuita
- ✂️ Posible truncamiento de textos largos

## 📹 Video Demostrativo
[![Ver demostración](https://img.youtube.com/vi/A8xkXDZQa8o/0.jpg)](https://youtu.be/A8xkXDZQa8o)
```
