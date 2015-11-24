/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

/**
 * Clase FXMLDocumentController
 * Esta clase es el controlador de la interfaz. Aqui es donde se manejan todos los atributos y metodos a usar con la interfaz grafica.
 * Los atributos y metodos pueden ser generados manualmente, o generados directamente desde Scene Builder.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 6.02, 20/11/2015
 */
public class FXMLDocumentController implements Initializable 
{
    
    private ColaJugadores _queue; //Representa la cola de jugadores a ser vaciada en el torneo
    private ListaAnos _anos; //Representa la lista de anos a ser guardada/recuperada que contiene toda la informacion
    private NodoAnos _anoImplementado; //Año en el que se esta consultando/modificando la informacion
    
    
    @FXML
    private Button clickCrear;
    @FXML
    private Button crearTorneo;
    @FXML
    private TextField textEdad;
    @FXML
    private TextField textCedula;
    @FXML
    private TextField textSexo;
    @FXML
    private TextField textEquipo;
    @FXML
    private Button botonAñadir;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textCategoria;
    @FXML
    private Button clickEncuentros;
    @FXML
    private Button eliminarCat3;
    @FXML
    private Button eliminarCat4;
    @FXML
    private Button eliminarCat5;
    @FXML
    private Button eliminarCat6;
    @FXML
    private Button eliminarEquipo;
    @FXML
    private Button salvarCambiosEquipo;
    @FXML
    private TextField textPuntos;
    @FXML
    private TextField textRanking;
    @FXML
    private TextField textAño;
    @FXML
    private Button clickModificar;
    @FXML
    private TabPane paneConsultar;
    @FXML
    private Pane paneEncuentros;
    @FXML
    private Pane paneCrear;
    @FXML
    private ComboBox<NodoEquipos> comboEquipo;
    @FXML
    private GridPane grid3M;
    @FXML
    private GridPane grid3F;
    @FXML
    private GridPane grid4M;
    @FXML
    private GridPane grid4F;
    @FXML
    private GridPane grid5M;
    @FXML
    private GridPane grid5F;
    @FXML
    private GridPane grid6M;
    @FXML
    private GridPane grid6F;
    @FXML
    private ComboBox<NodoJugadores> comboJugador;
    @FXML
    private TextField textJuegosGanados;
    @FXML
    private TextField textJuegosPerdidos;
    @FXML
    private TextField textNumeroJuegos;
    @FXML
    private TextField textPuntosAcumulados;
    @FXML
    private Button salvarCambiosJugador;
    @FXML
    private Button eliminarJugador;
    
    /**
    * Metodo initialize
    * Metodo que inicializa diversos atributos/variables al lanzar el programa. Tambien es capaz de ejecutar metodos.
    * 
    */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        _queue = new ColaJugadores(null);
        _anos = new ListaAnos();
    }    
    
    /**
    * Metodo clickCrear
    * Metodo que hace visible la pantalla de crear el torneo, e invisible las pantallas de consultar/modificar informacion y 
    * encuentros.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Crear Torneo)
    */
    @FXML
    private void clickCrear(ActionEvent event) 
    {
        paneCrear.setVisible(true);
        paneConsultar.setVisible(false);
        paneEncuentros.setVisible(false);
    }
    
    /**
    * Metodo clickConsultar
    * Metodo que hace visible la pantalla de consultar/modificar la informacion, e invisible las pantallas de crear el torneo y 
    * encuentros. Tambien, crear un popup para elegir el ano a consultar.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Consultar/modificar informacion)
    */
    @FXML
    private void clickConsultar(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        elegirAno();
        paneConsultar.setVisible(true);
        paneCrear.setVisible(false);
        paneEncuentros.setVisible(false);
    }
    
    /**
    * Metodo clickEncuentros
    * Metodo que hace visible la pantalla de encuentros, e invisible las pantallas de crear el torneo y consultar/modificar la
    * informacion.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Encuentros)
    */
    @FXML
    private void clickEncuentros(ActionEvent event) 
    {
        paneEncuentros.setVisible(true);
        paneConsultar.setVisible(false);
        paneCrear.setVisible(false);
    }
    
    /**
    * Metodo elegirAno
    * Metodo para elegir el ano a consultar.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Encuentros)
    */
    private void elegirAno() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        int ano = elegirAnoPopup();
        NodoAnos nodAno = new NodoAnos(ano); //crea un nodo NodoAnos con el ano a consultar para buscar su equivalente en la lista de anos
        
        _anoImplementado = _anos.buscarAnos(nodAno); //consigue el equivalente de nodAno y lo guarda en el atributo anoImplementado
        
        recuperarInformacionAño();
    }
    
    /**
    * Metodo elegirAnoPopup
    * Metodo que despliega un popup con un DialogChoice que contiene los anos donde se han creado torneos. Estos son recuperados
    * del archivo de objetos, como una ListaAnos que contiene toda la informacion del programa.
    * 
    */
    private int elegirAnoPopup() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        NodoAnos aux = new NodoAnos(1); //instancia un nodoAnos con un numero de ano '1' para que entre en el ciclo while.
        List<String> arrayAnos = new ArrayList<>(); //crea un Arraylist para recuperar la informacion de los anos donde existen torneos
        
        _anos = _anos.leerArchivo(_anos); //recupera la ListaAnos del archivo de objetos y la asigna al atributo anos.
        
        while(aux != null) //ciclo para recorrer los años
        {
            aux = _anos.llenarChoiceDialog(aux); //devuelve los NodoAnos con anos donde se crearon los torneos.
            
            if(aux != null) //condicion para evitar un NullPointerException al tratar de añadir un NodoAnos null
                arrayAnos.add(Integer.toString(aux.getAno())); //anade einformacion al arrayList que contiene los anos donde se crearon torneos como un String
        }
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Elegir año", arrayAnos); //instancia un ChoiceDialog con los anos que se han hecho torneos
        dialog.setTitle("Consultar Informacion");
        dialog.setHeaderText("Por favor, introduzca la informacion requerida");
        dialog.setContentText("Introduzca el año a consultar:");

        Optional<String> result = dialog.showAndWait(); //deja el popup en pantalla hasta que se elija una opcion
        return Integer.parseInt(result.get()); //retorna el resultado elegido como un entero
    }

    /**
    * Metodo llenarCola
    * llena la cola de jugadores a traves de la informacion introducida por el usuario.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Anadir)
    */
    @FXML
    private void llenarCola(ActionEvent event) 
    {
        //convierte alguno de los Strings introducidos en los TextFields en enteros.
        int cedula = Integer.parseInt(textCedula.getText());
        int edad = Integer.parseInt(textEdad.getText());
        int categoria = Integer.parseInt(textCategoria.getText());
        
        //condicion para asegurar que los TextFields de sexo no sean diferentes a 'm' o 'f', y que la categoria introducida este entre 3 y 6
        if( ((!textSexo.getText().equalsIgnoreCase("m")) && (!textSexo.getText().equalsIgnoreCase("f"))) ||  (categoria < 3) || (categoria > 6) )
        {
            popupEntrada(false); //muestra un popup de error
        }
        else
        {
            popupEntrada(true); //muestra un popup de exito al introducir la informacion correctamente
            
            //crea un jugador con la informacion introducida por el usuario
            NodoJugadores jugador = new NodoJugadores(textNombre.getText(), cedula, textSexo.getText(), edad, textEquipo.getText(), categoria, 0, 0, 0, null);
            _queue.encolar(jugador); //inserta al jugador en la cola
        }
        
        clearText(); //limpia todos los TextFields
        
    }
    
    /**
    * Metodo popupEntrada
    * Genera un popup Alert de error o de exito, dependiendo de la informacion que el usuario haya introducido referente a los
    * jugadores que se inscriben en el torneo
    * 
    * @param bool indica si debe aparecer el popup de error o de exito
    */
    private void popupEntrada(boolean bool)
    {
        if(bool == false)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error de Entrada");
            alert.setContentText("Ha introducido informacion invalida. Por favor, intentelo de nuevo.");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Exito!");
            alert.setHeaderText(null);
            alert.setContentText("Ha introducido al jugador exitosamente.");
            alert.showAndWait();
        }
    }
    
    /**
    * Metodo clearText
    * Metodo que limpia todos los TextFields una vez se haya introducido la informacion del jugador y presionado el boton de anadir
    * 
    */
    private void clearText()
    {
        textNombre.clear();
        textCedula.clear();
        textSexo.clear();
        textEdad.clear();
        textEquipo.clear();
        textCategoria.clear();
    }
    
    /**
    * Metodo llenarTorneo
    * Metodo que crea el torneo una vez se haya terminado de introducir los jugadores que participaran
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Crear Torneo)
    */
    @FXML
    private void llenarTorneo(ActionEvent event) throws IOException, ClassNotFoundException, Exception 
    {
        int numAno = Integer.parseInt(textAño.getText());
        NodoJugadores jugador; //representa el jugador a ser desencolado
        NodoAnos nodAno = new NodoAnos(numAno); //crea un NodoAnos con el ano a ser introducido a la lista
        NodoAnos aux; //NodoAnos retornado si el ano en el que se quiere crear el torneo ya existe.
        
        _anos = _anos.leerArchivo(_anos); //lee el archivo de objetos que retorna la lista que contiene la informacion de todos los torneos
        aux = _anos.buscarAnos(nodAno); //busca que el ano en el que se trata de crear el torneo no exista
        
        if(aux != null)
        {
            System.out.println("Este año ya existe");
        }
        else
        {
            _anos.insertarPrimero(nodAno); //inserta el ano en el que se va a crear el torneo
            
            while(_queue.getCabeza() != null) //ciclo para desencolar e introducir a todos los jugadores en el torneo
            {
                jugador = _queue.desencolar(); //desencola al jugador
                nodAno.sortEquipo(jugador); //envia al jugador a validar su informacion en la lista de equipos
            }
        }
        
        _anos.crearArchivo(_anos); //Anade al archivo de objetos la nueva lista de anos con la informacion recien anadida
    }

    /**
    * Metodo recuperarInformacionAño
    * Metodo que recupera la informacion del año elegido a visualizar.
    * 
    * 
    */
    private void recuperarInformacionAño()
    {
        ListaEquipos listaEquipo = new ListaEquipos(); //lista donde se buscara el equipo elegido
        
        listaEquipo = _anoImplementado.getEquipos(); //llena lista de equipos, con los equipos que jugaron el ano elegido (e.g: 2013)
        
        llenarComboBoxEquipo(listaEquipo); //llena el ComboBox de los equipos que participaron este año
        llenarComboBoxJugadores(listaEquipo); //llena el ComboBox  de los jugadores que participaron este año
    }
    
    /**
    * Metodo llenarComboBoxJugadores
    * Metodo que llena el ComboBox de jugadores, con elementos NodoJugadores, de todos los equipos que participaron en el torneo este año.
    * 
    * @param listaEquipo representa la lista de equipos del año a consultar.
    */
    public void llenarComboBoxJugadores(ListaEquipos listaEquipo)
    {
        ObservableList<NodoJugadores> ol = FXCollections.observableArrayList(); 
        NodoEquipos equipos = listaEquipo.getCabeza(); 
        
        while(equipos != null) //ciclo para recorrer los equipos
        {
            NodoCategorias categorias = equipos.getCategorias().getCabeza(); //instancia un nodo con las categorias del equipo a recorrer
            ABBJugadores arbol = categorias.getJugadores(); //instancia un nodo con el ABB las categorias a recorrer
            
            while(categorias != null) //ciclo para recorrer las categorias
            {
                NodoJugadores aux = arbol.getRaiz(); //instancia un nodo para obtener la raiz de cada ABB a recorrer
                ArrayList<NodoJugadores> array = arbol.retornarEnOrden(aux); //devuelve un ArrayList con los jugadores
            
                for(int i = 0 ; i < array.size() ; i++) //ciclo para insertar los jugadores en la ObservableList
                    ol.add(array.get(i));
            
                categorias = categorias.getProximo(); //avanza a la siguiente categoria
            }
            
            equipos = equipos.getProximo(); //avanza al siguiente equipo
        }
        
        comboJugador.setItems(ol); //inserta la ObservableList con los elementos NodoJugadores en el ComboBox de jugadores
        
        //Expresion lambda que define la lista de valores a renderizar en el ComboBox
        comboJugador.setCellFactory((comboBox) -> 
        {
            return new ListCell<NodoJugadores>() 
            {
                @Override
                protected void updateItem(NodoJugadores item, boolean empty) 
                {
                    super.updateItem(item, empty);

                    if (item == null || empty) 
                    {
                        setText(null);
                    } 
                    else 
                    {
                        setText(item.getNombre());
                    }
                }
            };
        });
        
        //define los valores especificos a renderizar en el ComboBox
        comboJugador.setConverter(new StringConverter<NodoJugadores>() 
        {
            @Override
            public String toString(NodoJugadores nodJugadores) 
            {
                    return nodJugadores.getNombre();
            }

            @Override
            public NodoJugadores fromString(String personString) 
            {
                return null; // No conversion fromString needed.
            }
        });
    }
    
    /**
    * Metodo eleccionComboJugador
    * Metodo que muestra la informacion del jugador a consultar.
    * 
    * @param event Significa que este metodo es llamado al elegir un valor del ComboBox de jugadores
    */
    @FXML
    private void eleccionComboJugador(ActionEvent event)
    {
        NodoJugadores jugadorElegido = comboJugador.getSelectionModel().getSelectedItem(); //valor del ComboBox elegido
        
        textJuegosGanados.setText(Integer.toString(jugadorElegido.getJuegosGanados())); //imprime los juegos ganados del jugador en el TextField
        textJuegosPerdidos.setText(Integer.toString(jugadorElegido.getJuegosPerdidos())); //imprime los juegos perdidos del jugador en el TextField
        textNumeroJuegos.setText(Integer.toString(jugadorElegido.getJuegosGanados() + jugadorElegido.getJuegosPerdidos())); //imprime la cantidad de juegos en el que el jugador ha participado en el TextField
        textPuntosAcumulados.setText(Integer.toString(jugadorElegido.getPuntos())); //imprime los puntos acumulados del jugador en el TextField
    }
    
    /**
    * Metodo llenarComboBoxEquipo
    * Metodo que llena el ComboBox de equipos, con elementos NodoEquipos.
    * 
    * @param listaEquipo representa la lista de equipos del año a consultar
    */
    private void llenarComboBoxEquipo(ListaEquipos listaEquipo)
    {
        ObservableList<NodoEquipos> ol = FXCollections.observableArrayList();
        NodoEquipos equipos = listaEquipo.getCabeza();
        
        while(equipos != null) //ciclo que recorre los equipos
        {
            ol.add(equipos); //añade el equipo a la ObservableList
            equipos = equipos.getProximo(); //avanza al siguiente equipo
        }
        
        comboEquipo.setItems(ol); //inserta la ObservableList en el ComboBox
        
        
        
        //Expresion lambda que define la lista de valores a renderizar en el ComboBox
        comboEquipo.setCellFactory((comboBox) -> 
        {
            return new ListCell<NodoEquipos>() 
            {
                @Override
                protected void updateItem(NodoEquipos item, boolean empty) 
                {
                    super.updateItem(item, empty);

                    if (item == null || empty) 
                    {
                        setText(null);
                    } 
                    else 
                    {
                        setText(item.getUniversidad());
                    }
                }
            };
        });

        //define los valores especificos a renderizar en el ComboBox
        comboEquipo.setConverter(new StringConverter<NodoEquipos>() 
        {
            @Override
            public String toString(NodoEquipos nodEquipo) 
            {
                    return nodEquipo.getUniversidad();
            }

            @Override
            public NodoEquipos fromString(String personString) 
            {
                return null; // No conversion fromString needed.
            }
        });
    }
    
    /**
    * Metodo eleccionComboEquipos
    * Metodo que muestra la informacion del equipo a consultar.
    * 
    * @param event Significa que este metodo es llamado al elegir un valor del ComboBox de equipos.
    */
    @FXML
    private void eleccionComboEquipos(ActionEvent event)
    {
        clearGrid(); //vacia los hijos de los GridPane en caso de que se haya consultado otro equipo anteriormente
        
        NodoEquipos equipoElegido = comboEquipo.getSelectionModel().getSelectedItem(); //equipo a consultar elegido en el ComboBox
        NodoCategorias categoria = equipoElegido.getCategorias().getCabeza(); //instancia un nodo con las categorias del equipo
        
        ABBJugadores arbol = categoria.getJugadores(); //instancia una lista con el ABB de jugadores del equipo
        
        textRanking.setText(Integer.toString(equipoElegido.getRanking())); //imprime el Ranking del equipo en el TextField
        textPuntos.setText(Integer.toString(equipoElegido.getPuntos())); //imprime los puntos del equipo en el TextField
        
        
        while(categoria != null) //recorre las categorias del equipo
        {
            NodoJugadores jugador = categoria.getJugadores().getRaiz(); //instancia un nodo en la raiz del ABB de jugadores del equipo.
            
            ArrayList<NodoJugadores> array = arbol.retornarEnOrden(jugador); //retorna un ArrayList con los jugadores
            
            //bloque para ordenar a los jugadores en sus GridPane especificos, dependiendo de su categoria y sexo (puede ser optimizado)
            if( (jugador.getCategoria() == 3) && (jugador.getSexo().equalsIgnoreCase("m")))
                llenarGrid(array, grid3M);
            else if( (jugador.getCategoria() == 3) && (jugador.getSexo().equalsIgnoreCase("f")))
                llenarGrid(array, grid3F);
            else if( (jugador.getCategoria() == 4) && (jugador.getSexo().equalsIgnoreCase("m")))
                llenarGrid(array, grid4M);
            else if( (jugador.getCategoria() == 4) && (jugador.getSexo().equalsIgnoreCase("f")))
                llenarGrid(array, grid4F);
            else if( (jugador.getCategoria() == 5) && (jugador.getSexo().equalsIgnoreCase("m")))
                llenarGrid(array, grid5M);
            else if( (jugador.getCategoria() == 5) && (jugador.getSexo().equalsIgnoreCase("f")))
                llenarGrid(array, grid5F);
            else if( (jugador.getCategoria() == 6) && (jugador.getSexo().equalsIgnoreCase("m")))
                llenarGrid(array, grid6M);
            else if( (jugador.getCategoria() == 6) && (jugador.getSexo().equalsIgnoreCase("f")))
                llenarGrid(array, grid6F);
            
            categoria = categoria.getProximo(); //avanza a la siguiente categoria
        }
    }
    
    /**
    * Metodo clearGrid
    * Metodo que vacia los GridPane de jugadores cuando se cambia de equipo a consultar
    * 
    * @param event Significa que este metodo es llamado al elegir un valor del ComboBox de jugadores
    */
    public void clearGrid()
    {
        grid3M.getChildren().clear();
        grid3F.getChildren().clear();
        grid4M.getChildren().clear();
        grid4F.getChildren().clear();
        grid5M.getChildren().clear();
        grid5F.getChildren().clear();
        grid6M.getChildren().clear();
        grid6F.getChildren().clear();
        
    }
    
    /**
    * Metodo llenarGrid
    * Metodo que llena los GridPane con la informacion de los jugadores.
    * 
    * @param array ArrayList que contiene a los jugadores de una categoria especifica del equipo
    * @param grid el GridPane a ser llenado con los jugadores en el ArrayList
    */
     public void llenarGrid(ArrayList<NodoJugadores> array, GridPane grid)
    {
        for(int i = 0 ; i < array.size() ; i++) //ciclo que llena el GridPane de jugadores
        {
            TextField text = new TextField(array.get(i).getNombre()); //instancia un TextField con el nombre del jugador
            text.setId(array.get(i).getNombre()); //asigna un FX:ID al TextField, siendo este el mismo nombre del jugador que muestra.
            grid.add(text, i, 0); //lo añade al Grid en el indice especificado
        }   
    }
    
    /**
    * Metodo sobreescribirJugadores
    * Metodo que sobreescribe los jugadores que se hayan modificado en el historial de equipos.
    * 
    * @param grid representa uno de los 8 grid de jugadores en el historial de equipos.
    */
    public void sobreescribirJugadores(GridPane grid)
    {
        NodoEquipos equipoElegido = comboEquipo.getSelectionModel().getSelectedItem(); //equipo a consultar elegido en el ComboBox
        
        for(int i = 0 ; i < grid.getChildren().size() ; i++) //ciclo para recorrer todos los hijos del GridPane
        {
            Node nodo = grid.getChildren().get(i); //instanciamos un objeto Node con uno de los hijos del GridPane, dependiendo de en que indice nos encontremos.
            
            if(nodo instanceof TextField) //condicional para chequear si el hijo del GridPane es un objeto TextField
            {
                TextField text = (TextField) nodo; //casteamos el objeto Nodo a TextField
            
                NodoCategorias nodCat = equipoElegido.getCategorias().getCabeza(); //instanciamos un nodo con las categorias del equipo elegido en el ComboBox
                NodoJugadores jugador = nodCat.getJugadores().getRaiz(); //instanciamos un nodo con los jugadores del equipo elegido en el ComboBox
                
                nodCat.getJugadores().sobreescribirJugador(jugador, text); //sobreescribimos al jugador modificado
            }
        }
    }
    
    /**
    * Metodo salvarCambiosEquipo
    * Metodo que Salva los cambios realizados en el historial de equipos.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Salvar Cambios)
    */
    @FXML
    private void salvarCambiosEquipo(ActionEvent event) throws IOException
    {
        NodoEquipos equipoElegido = comboEquipo.getSelectionModel().getSelectedItem(); //equipo a consultar elegido en el ComboBox
        NodoCategorias categoria = equipoElegido.getCategorias().getCabeza(); //instanciamos un nodo con las categorias del equipo elegido en el ComboBox
        
        equipoElegido.setRanking(Integer.parseInt(textRanking.getText())); //salva el valor modificado en textRanking en el atributo ranking del jugador
        equipoElegido.setPuntos(Integer.parseInt(textPuntos.getText())); //salva el valor modificado en textPuntos en el atributo puntos del jugador
        
        while(categoria != null) //ciclo para recorrer las categorias del equipo
        {
            NodoJugadores jugador = categoria.getJugadores().getRaiz(); //instancia un nodo en la raiz del ABB de jugadores del equipo.
            
            //bloque para sobreescribir los jugadores modificados en los GridPane de jugadores (puede ser optimizado)
            if( (jugador.getCategoria() == 3) && (jugador.getSexo().equalsIgnoreCase("m")))
                sobreescribirJugadores(grid3M);
            else if( (jugador.getCategoria() == 3) && (jugador.getSexo().equalsIgnoreCase("f")))
                sobreescribirJugadores(grid3F);
            else if( (jugador.getCategoria() == 4) && (jugador.getSexo().equalsIgnoreCase("m")))
                sobreescribirJugadores(grid4M);
            else if( (jugador.getCategoria() == 4) && (jugador.getSexo().equalsIgnoreCase("f")))
                sobreescribirJugadores(grid4F);
            else if( (jugador.getCategoria() == 5) && (jugador.getSexo().equalsIgnoreCase("m")))
                sobreescribirJugadores(grid5M);
            else if( (jugador.getCategoria() == 5) && (jugador.getSexo().equalsIgnoreCase("f")))
                sobreescribirJugadores(grid5F);
            else if( (jugador.getCategoria() == 6) && (jugador.getSexo().equalsIgnoreCase("m")))
                sobreescribirJugadores(grid6M);
            else if( (jugador.getCategoria() == 6) && (jugador.getSexo().equalsIgnoreCase("f")))
                sobreescribirJugadores(grid6F);
            
            categoria = categoria.getProximo(); //avanza a la siguiente categoria
        }
        
        _anos.crearArchivo(_anos); //salva los cambios al archivo de objetos
    }
    
    /**
    * Metodo salvarCambiosJugador
    * Metodo que Salva los cambios realizados en el historial de jugadores.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Salvar Cambios)
    */
    @FXML
    private void SalvarCambiosJugador(ActionEvent event) throws IOException
    {
        NodoJugadores jugadorElegido = comboJugador.getSelectionModel().getSelectedItem();
        
        jugadorElegido.setJuegosGanados(Integer.parseInt(textJuegosGanados.getText())); //salva el valor modificado en textJuegosGanados al atributo juegosGanados del jugador
        jugadorElegido.setJuegosPerdidos(Integer.parseInt(textJuegosPerdidos.getText())); //salva el valor modificado en textJuegosPerdidos al atributo juegosPerdidos del jugador
        jugadorElegido.setPuntos(Integer.parseInt(textPuntosAcumulados.getText())); //salva el valor modificado en textJuegosAcumulados al atributo puntos del jugador
        
        _anos.crearArchivo(_anos); //salva los cambios al archivo de objetos
    }
    
    /**
    * Metodo eliminarEquipo
    * Metodo que elimina un equipo del torneo.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Salvar Cambios)
    */
    @FXML
    private void eliminarEquipo(ActionEvent event) throws IOException
    {
        NodoEquipos equipoEliminado = comboEquipo.getSelectionModel().getSelectedItem(); //equipo a eliminar elegido en el ComboBox
        
        _anoImplementado.getEquipos().eliminarEquipo(equipoEliminado); //elimina el nodo del equipo en la lista de equipos
        
        _anos.crearArchivo(_anos); //salva los cambios al archivo de objetos
    }
    
    /**
    * Metodo eliminarJugador
    * Metodo que elimina un jugador del torneo. //No lo pude terminar a tiempo
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Salvar Cambios)
    */
    @FXML
    private void eliminarJugador(ActionEvent event) 
    {
        NodoJugadores jugadorEliminado = comboJugador.getSelectionModel().getSelectedItem(); //jugador a eliminar elegido en el ComboBox
        ListaEquipos listaEquipo = _anoImplementado.getEquipos();
    }
}
