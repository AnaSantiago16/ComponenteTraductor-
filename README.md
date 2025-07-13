
Listado y código de algunos métodos o propiedades más relevantes
  -constructor
  Inicializa el combobox con los idiomas disponibles ("Español", "Inglés", "Francés")
  Configura el tamaño preferido del componente
  Añade el ActionListener para manejar los cambios de idioma
  
  -Metodos de traduccion
  private void traducirEtiquetas(String idiomaDestino)
    coordina el porceso de traduccion de todas las etiquetas
    
  private void traducirComponentes(Container contenedor, String idiomaDestino)
    Recorre recursivamente los componentes buscando JLabels para traducir  
    
  private String traducir(String texto, String idiomaOrigen, String idiomaDestino)
    Realiza la llamada a la API MyMemory para obtener la traducción
    Maneja la codificación URL y el parsing de la respuesta JSON

  -Propiedades Principales
  private static final String API_URL
    Endpoint de la API de MyMemory Translate
    
  private final String[] codigosIdiomas
    Códigos ISO de los idiomas soportados (es, en, fr)


Breve explicación del componente :
  El BotonTraductor es un JComboBox especializado que permite traducir dinámicamente los      textos de las JLabels en una interfaz gráfica Swing. Sus características principales son:

  -  Integración con API externa: Utiliza el servicio gratuito MyMemory Translate
  -  Traducción automática: Detecta y traduce todos los JLabels encontrados
  -  Interfaz sencilla: Se integra como un combobox normal en cualquier contenedor Swing
  -  Procesamiento asíncrono: Las traducciones se realizan en segundo plano sin bloquear la UI
  -  Manejo de errores: Muestra claramente cuando una traducción falla

Instrucciones de uso:

 Requisitos
  - JDK 8 o superior
  - Dependencia org.json (para parsear respuestas JSON)
  - Conexión a Internet (para acceder a la API)
Configuración opcional
  // Cambiar tamaño
    traductor.setPreferredSize(new Dimension(200, 30));

  // Cambiar fuente
    traductor.setFont(new Font("Arial", Font.BOLD, 14));
    
  Consejos de Uso

  - Para mejores resultados, usa textos cortos (la API gratuita tiene limitaciones)
  - Si necesitas traducir muchos textos, considera implementar un sistema de caché
  - En entornos productivos, evalúa usar una API con clave (como DeepL o Google Translate)
  - El componente funciona mejor con JLabels directamente en el contenedor principal

  Limitaciones Conocidas
  - La API gratuita tiene límites de uso
  - Las traducciones muy largas pueden truncarse
  - Requiere conexión a Internet estable



Video: 
https://youtu.be/A8xkXDZQa8o
